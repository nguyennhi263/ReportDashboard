package vn.com.ifca.reportdashboard.Model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Nhi on 3/29/2018.
 */

public class IP4V {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "TestAPI";
    public static final String KEY_GET = "IP_GET";
    public static final String KEY_POST ="IP_POST";
    // Constructor
    public IP4V(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void add_KEYGET (String ip){
        editor.putString(KEY_GET,ip);
        editor.commit();
    }
    public void add_KEYPOST (String ip){
        editor.putString(KEY_POST,ip);
        editor.commit();
    }
    public HashMap<String, String> getIP(){
        HashMap<String, String> defect = new HashMap<String, String>();
        defect.put(KEY_GET,pref.getString(KEY_GET,null));
        defect.put(KEY_POST,pref.getString(KEY_POST,null));
        return defect;
    }
    public void clear(){
        editor.clear();
        editor.commit();
    }
}
