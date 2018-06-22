package vn.com.ifca.reportdashboard.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import vn.com.ifca.reportdashboard.Model.Database;
import vn.com.ifca.reportdashboard.R;

public class LogInActivity extends AppCompatActivity {
    private EditText user;
    private EditText pass;
    private CardView LoginBtn;
    private String username;
    private String password;
    Database loginDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        user = findViewById(R.id.txtUsername);
        pass = findViewById(R.id.txtPassword);
        LoginBtn = findViewById(R.id.LoginBtn);
        loginDb = new Database(this);
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = user.getText().toString();
                password = pass.getText().toString();
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(LogInActivity.this, "Enter your username!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LogInActivity.this, "Enter your password!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Hello " + username + "!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


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

}
