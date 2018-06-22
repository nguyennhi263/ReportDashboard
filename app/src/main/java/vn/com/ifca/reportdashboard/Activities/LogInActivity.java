package vn.com.ifca.reportdashboard.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vn.com.ifca.reportdashboard.R;

public class LogInActivity extends AppCompatActivity {
    private EditText user;
    private EditText pass;
    private CardView LoginBtn;
    private String username;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        user = findViewById(R.id.txtUsername);
        pass = findViewById(R.id.txtPassword);
        LoginBtn = findViewById(R.id.LoginBtn);
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = user.getText().toString();
                password = pass.getText().toString();
                if (TextUtils.isEmpty(username))
                {
                    Toast.makeText(LogInActivity.this, "Enter your username!", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(password)){
                    Toast.makeText(LogInActivity.this, "Enter your password!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Hello " + username + "!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
