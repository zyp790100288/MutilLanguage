# MutilLanguage
Android应用内多语言设置
兼容Android7.0

Android7.0及以上和以下语言切换和获取的方法
   /**
     * 获取系统的locale
     *
     * @return Locale对象
     */
    public static Locale getSystemLocale(Context context) {
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale =  Resources.getSystem().getConfiguration().getLocales().get(0);
        } else {
            locale = Locale.getDefault();
        }
        return locale;
    }
    
    
     /**
     * 设置语言类型
     */
    public static void setApplicationLanguage(Context context, int languageType) {
        Resources resources = context.getApplicationContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        Locale locale = getSetLanguageLocale(context,languageType);
        config.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            config.setLocales(localeList);
            context.getApplicationContext().createConfigurationContext(config);
            Locale.setDefault(locale);
        }
        resources.updateConfiguration(config, dm);
    }

在BaseApplication的方法attachBaseContext中设置
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            super.attachBaseContext(LanguageUtils.setLocal(base, SpfUtils.getAppLanguage(base)))
        } else {
            super.attachBaseContext(base)
        }
        
        onConfigurationChanged方法中设置语言防止系统切换时被重新替换
        LanguageUtils.onConfigurationChanged(applicationContext, SpfUtils.getAppLanguage(applicationContext))
    


在BaseActivity的方法attachBaseContext中
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            super.attachBaseContext(LanguageUtils.setLocal(newBase, SpfUtils.getAppLanguage(newBase)))
        } else {
            super.attachBaseContext(newBase)
        }
        
