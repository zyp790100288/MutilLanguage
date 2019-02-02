package com.intretech.app.mutillanguage

import android.app.Dialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.intretech.app.mutillanguage.adpater.LanguageAdapter
import com.intretech.app.mutillanguage.base.BaseActivity
import com.intretech.app.mutillanguage.entity.LanguageInfo
import com.intretech.app.mutillanguage.listener.OnOkListener
import com.intretech.app.mutillanguage.utils.ActivityUtils
import com.intretech.app.mutillanguage.utils.LanguageUtils
import com.intretech.app.mutillanguage.utils.SpfUtils
import kotlinx.android.synthetic.main.activity_settings.*
import java.util.*

/**
 *   create by yq06171 on 2019/2/2
 **/
class SettingsActivity : BaseActivity(){
    var mLanguageAdapter: LanguageAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        findViewById()
        setListener()
        init()

    }

    private fun findViewById(){
        mLanguageAdapter = LanguageAdapter(R.layout.item_language, ArrayList<LanguageInfo>())
        rv_language_list.layoutManager = LinearLayoutManager(this@SettingsActivity)
        rv_language_list.adapter = mLanguageAdapter
    }

    private fun setListener() {
        mLanguageAdapter?.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, _, position -> notifyChangeLanguage(adapter as LanguageAdapter, position) }

    }



    protected fun init() {
        val languageInfos = ArrayList<LanguageInfo>()
        for (i in 0 until resources.getStringArray(R.array.language).size) {
            val languageInfo = LanguageInfo(i, i == SpfUtils.getAppLanguage(this@SettingsActivity), resources.getStringArray(R.array.language)[i])
            languageInfos.add(languageInfo)
        }
        mLanguageAdapter?.setNewData(languageInfos)

    }


    private fun notifyChangeLanguage(languageAdapter: LanguageAdapter, languageType: Int) {
        showDefineDialog(getString(R.string.confirm_change_language), getString(R.string.cancel), getString(R.string.ok), object :OnOkListener{
            override fun setOnOk(dialog: Dialog) {
                dialog.dismiss()
                languageAdapter.changeData(languageType)
                LanguageUtils.saveSelectLanguage(this@SettingsActivity, languageType)
                ActivityUtils.startActivity(MainActivity::class.java)
                finish()

            }

        })
    }
}