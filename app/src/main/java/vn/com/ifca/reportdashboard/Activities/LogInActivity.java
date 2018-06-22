package vn.com.ifca.reportdashboard.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import vn.com.ifca.reportdashboard.R;

public class LogInActivity extends AppCompatActivity {
    private EditText user;
    private EditText pass;
    private TextView userdisplay;
    private TextView passdisplay;
    private String username;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        user = findViewById(R.id.txtUsername);
        pass = findViewById(R.id.txtPassword);
        //userdisplay = findViewById(R.id.username);
        //passdisplay = findViewById(R.id.password);
    }
    public void LogInButton(View view){
        username = user.getText().toString();
        password = pass.getText().toString();
        setContentView(R.layout.activity_main);
        //userdisplay.setText(username);
        //passdisplay.setText(password);

    }
}
