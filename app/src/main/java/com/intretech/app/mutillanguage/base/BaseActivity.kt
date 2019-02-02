package com.intretech.app.mutillanguage.base

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import com.intretech.app.mutillanguage.R
import com.intretech.app.mutillanguage.listener.OnOkListener
import com.intretech.app.mutillanguage.utils.LanguageUtils
import com.intretech.app.mutillanguage.utils.SpfUtils
import kotlinx.android.synthetic.main.dialog_adjust_app.view.*

/**
 *   create by yq06171 on 2019/2/2
 **/
open class BaseActivity :AppCompatActivity(){

    override fun attachBaseContext(newBase: Context?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            super.attachBaseContext(LanguageUtils.setLocal(newBase, SpfUtils.getAppLanguage(newBase)))
        } else {
            super.attachBaseContext(newBase)
        }
    }

    /**
     * 带标题和确定取消按钮的对话框
     * @param title 标题
     * @param cancel 取消按钮文本
     * @param ok 确定按钮文本
     * @param onOkListener
     */
    fun showDefineDialog(title: String, cancel: String, ok: String, onOkListener: OnOkListener) {
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.dialog_adjust_app, null)
        view.tv_dialog_title.text = title
        view.btn_dialog_left.text = cancel
        view.btn_dialog_right.text = ok
        val aBuilder = showRoundDialog(view)
        view.btn_dialog_right.setOnClickListener(View.OnClickListener { onOkListener.setOnOk(aBuilder) })
        view.btn_dialog_right.setTextColor(resources.getColor(R.color.main_blue))
        view.btn_dialog_left.setOnClickListener(View.OnClickListener { aBuilder.cancel() })
        aBuilder.setCanceledOnTouchOutside(false) // 将dialog设置为触摸空白区域不会消失
        aBuilder.show()
    }


    fun showRoundDialog(view: View): Dialog {
        val aBuilder = Dialog(this, R.style.my_dialog_style)
        val window = aBuilder.window
        aBuilder.setContentView(view)
        // window.setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画
        window!!.setGravity(Gravity.CENTER)
        val m = this.windowManager
        val d = m.defaultDisplay
        val p = window.attributes
        p.width = resources.getDimension(R.dimen.dp_250).toInt();
        p.height = resources.getDimension(R.dimen.dp_120).toInt();
        window.attributes = p
        return aBuilder
    }
}