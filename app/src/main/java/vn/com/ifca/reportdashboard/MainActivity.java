package vn.com.ifca.reportdashboard;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.HashMap;

import vn.com.ifca.reportdashboard.Model.IP4V;
import android.content.res.Configuration;
import android.content.res.Resources;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    IP4V link;
    HashMap<String, String> ip;
    private SharedPreferences langPreferences;
    private String sharedPrefFile = "vn.com.ifca.reportdashboard";
    private String currentLocale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        langPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        String setLang = langPreferences.getString("language", "toast");
        if (!(setLang.equals("toast"))){
            Resources res = this.getResources();
            changeLanguage(res, setLang);
        }
        link = new IP4V(getApplicationContext());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sidebar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was
            case R.id.nav_dashboard:
                Toast.makeText(this,
                        "Dashboard"
                        , Toast.LENGTH_SHORT)
                        .show();
                break;
            // action with ID action_settings was
            case R.id.nav_language:
                final Resources res = this.getResources();
                final Dialog languageContractor = new Dialog(MainActivity.this);
                languageContractor.setContentView(R.layout.language_options);
                languageContractor.setCancelable(true);
                Button cancelBtn = languageContractor.findViewById(R.id.cancel_language);
                final Button okBtn = languageContractor.findViewById(R.id.confirm_language);
                final RadioButton english = languageContractor.findViewById(R.id.english_language_option);
                final RadioButton viet = languageContractor.findViewById(R.id.vietnamese_language_option);
                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (english.isChecked() == false && viet.isChecked() == false) {
                            Toast.makeText(getApplicationContext(), "@string/languageWarning", Toast.LENGTH_SHORT).show();
                        }
                        else if (english.isChecked()) {
                            changeLanguage(res, "en");
                            languageContractor.dismiss();
                        }
                        else {
                            changeLanguage(res, "vi");
                            languageContractor.dismiss();
                        }
                        }


                        });
                    // Cancel
                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            languageContractor.dismiss();
                        }
                    });

                languageContractor.show();
                break;

                case R.id.nav_url:
                    final Dialog dialogContractor = new Dialog(MainActivity.this);
                    dialogContractor.setContentView(R.layout.url_options);
                    dialogContractor.setCancelable(true);
                    // initial
                    cancelBtn = (Button) dialogContractor.findViewById(R.id.cancel_url);
                    final Button urlBtn = dialogContractor.findViewById(R.id.ok_url_entry);
                    final EditText urtText = (EditText) dialogContractor.findViewById(R.id.url_entry);
                    //get url in db
                    ip = link.getIP();
                    final String URL = ip.get(link.KEY_URL);
                    urtText.setText(URL);

                    // Save Event
                    urlBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (urlBtn.getText().toString().equals("")) {

                            } else {
                                final String urlText = urtText.getText().toString();
                                link.add_KEYURL(urlText);
                                Toast.makeText(getApplicationContext(), "Updated URL", Toast.LENGTH_SHORT).show();
                                dialogContractor.dismiss();
                            }

                        }
                    });
                    // Cancel
                    cancelBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogContractor.dismiss();
                        }
                    });
                    dialogContractor.show();
                    break;

                    case R.id.nav_logout:
                        Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;

                }

                return true;
        }
        public void changeLanguage (Resources res, String locale)
        {
            Configuration config = new Configuration(res.getConfiguration());
            switch(locale) {
                case "en":
                    config.locale = new Locale("en");
                    break;
                case "vi":
                    config.locale = new Locale("vi");
                    break;
            }
            SharedPreferences.Editor preferencesEditor = langPreferences.edit();
            preferencesEditor.clear();
            preferencesEditor.putString("language", locale);
            preferencesEditor.apply();
            res.updateConfiguration(config, res.getDisplayMetrics());

        }
    }
