package vn.com.ifca.reportdashboard.Model;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class LanguagePf {
    public void changeLanguage (Resources res, String locale)
        {

        Configuration config=new Configuration(res.getConfiguration());
        switch(locale){
        case"en":
        config.locale=new Locale("en");
        break;
        case"vi":
        config.locale=new Locale("vi");
        break;
        }
        res.updateConfiguration(config,res.getDisplayMetrics());
        }
}
