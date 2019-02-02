package com.intretech.app.mutillanguage.base

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import com.intretech.app.mutillanguage.utils.LanguageUtils
import com.intretech.app.mutillanguage.utils.SpfUtils
import com.intretech.app.mutillanguage.utils.Utils

/**
 *   create by yq06171 on 2019/2/2
 **/
class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        Utils.init(applicationContext)
    }

    override fun attachBaseContext(base: Context?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            super.attachBaseContext(LanguageUtils.setLocal(base, SpfUtils.getAppLanguage(base)))
        } else {
            super.attachBaseContext(base)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        //保存系统选择语言
        LanguageUtils.onConfigurationChanged(applicationContext, SpfUtils.getAppLanguage(applicationContext))
    }
}