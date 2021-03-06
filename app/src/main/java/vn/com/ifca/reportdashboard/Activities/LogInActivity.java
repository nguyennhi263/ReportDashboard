package vn.com.ifca.reportdashboard.Activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Objects;

import vn.com.ifca.reportdashboard.MainActivity;
import vn.com.ifca.reportdashboard.Model.Database;
import vn.com.ifca.reportdashboard.Model.IP4V;
import vn.com.ifca.reportdashboard.R;

public class LogInActivity extends AppCompatActivity {
    private EditText user;
    private EditText pass;
    private CardView LoginBtn;
    private String username;
    private String password;
    private ImageButton settingBtn;
    ProgressBar pBar;
    Database loginDb;
    IP4V link;
    HashMap<String, String> ip;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide support bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_log_in);
        /*
        *   Initial
        * */
        user = findViewById(R.id.txtUsername);
        pass = findViewById(R.id.txtPassword);
        LoginBtn = findViewById(R.id.LoginBtn);
        loginDb = new Database(this);
        pBar = findViewById(R.id.progress);
        pBar.setVisibility(View.INVISIBLE);
        link = new IP4V(getApplicationContext());
        settingBtn = findViewById(R.id.url_destinationBtn);
        /*
        *   Event
        * */
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = user.getText().toString();
                password = pass.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(LogInActivity.this, "@string/logInToastUsername", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LogInActivity.this, "@string/logInToastPassword", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "@string/logInSuccess" + username + "!", Toast.LENGTH_SHORT).show();
                   // new checkLogIn();
                }
            }
        });


       settingBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final Dialog dialogContractor = new Dialog(LogInActivity.this);
               dialogContractor.setContentView(R.layout.url_options);
               dialogContractor.setCancelable(true);
               // initial
               Button cancelBtn;
               cancelBtn = (Button) dialogContractor.findViewById(R.id.cancel_url);
               final Button urlBtn = dialogContractor.findViewById(R.id.ok_url_entry);
               final EditText urtText = (EditText) dialogContractor.findViewById(R.id.url_entry);
               //get url in db
               ip = link.getIP();
               String URL = ip.get(link.KEY_URL);
               urtText.setText(URL);

               // Save Event
               urlBtn.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if (urlBtn.getText().toString().equals("")) {

                       } else {
                           final String urlText = urtText.getText().toString();
                           link.add_KEYURL(urlText);
                           Toast.makeText(getApplicationContext(), "@string/updateURL", Toast.LENGTH_SHORT).show();
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
           };
       });

    };

}


    //To be majorly edited by uncle google, as this is just a basic design
    //Also if you don't like the progress bar, just delete it. It's in activity_log_in.xml.
   /* private class checkLogIn extends AsyncTask<String, void, String> {
        String username = user.getText().toString();
        String password = user.getText().toString();

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params){

            //code that checks username and password match and if correct, return user ID or something. If not, run below.
            return "false";
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            if (result.equals("false")) {
                Toast.makeText(LogInActivity.this, "Invalid password or username", Toast.LENGTH_SHORT).show();
                pBar.setVisibility(View.INVISIBLE);
            }
            else{
                // Create a session for the specific user using username n stuff
                pBar.setVisibility(View.INVISIBLE);
                finish();
                Intent it = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(it);
            }

        }
    }
*/


    /*
    //Some Database nonsense. Can only be used once the proper settings are included.
    public void addData() {
        (view url button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = (loginDb.insertData( (Where URL IS) ));
                if (isInserted)
                    Toast.makeText(MainActivity.this, "URL Has Been Added", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Please Try Again", Toast.LENGTH_SHORT).show();
            }

    }
    public void viewData() {
        (view url button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor dat = loginDb.getData();
                if (dat.getCount() == 0){
                    message("Error", "No Data Found!");
                    return;
                }
                StringBuffer buff = new StringBuffer();
                while (dat.moveToNext()) {
                    buff.append("ID : " + buff.getString(0) + " URL : " + buff.getString(1) + "\n");
                }
                message("Data", buff.toString());

            }
    }
    public void updateData() {
        (update url button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean update = (loginDb.insertData( (Where ID IS) , (Where URL IS) ));
                if (isInserted)
                    Toast.makeText(MainActivity.this, "URL Has Been Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Please Try Again", Toast.LENGTH_SHORT).show();
            }
    }
    public void deleteData() {
        (delete url button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleted = loginDb.deleteData( (Where ID IS) );
                if (deleted > 0)
                    Toast.makeText(MainActivity.this, "URL Has Been Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Please Try Again", Toast.LENGTH_SHORT).show();
            }
    }
    public void message (String title, String note) {
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setCancelable(True);
        build.setTitle(title);
        build.setMessage(note);
        build.show();
    }

    */


