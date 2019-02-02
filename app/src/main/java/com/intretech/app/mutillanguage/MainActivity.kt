package com.intretech.app.mutillanguage

import android.os.Bundle
import com.intretech.app.mutillanguage.base.BaseActivity
import com.intretech.app.mutillanguage.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_settings.setOnClickListener {
            ActivityUtils.startActivity(SettingsActivity::class.java)
        }
    }
}
