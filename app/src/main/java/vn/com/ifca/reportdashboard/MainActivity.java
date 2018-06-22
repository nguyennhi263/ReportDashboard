package vn.com.ifca.reportdashboard;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import vn.com.ifca.reportdashboard.Activities.LogInActivity;
import vn.com.ifca.reportdashboard.Model.Database;

public class MainActivity extends AppCompatActivity {
    Database urlDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urlDb = new Database(this);

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
                Toast.makeText(this,
                        "Dashboard"
                        , Toast.LENGTH_SHORT)
                        .show();

                break;
            case R.id.nav_url:
                final Dialog dialogContractor = new Dialog(MainActivity.this);
                dialogContractor.setContentView(R.layout.url_options);
                dialogContractor.setCancelable(true);

                // Cancel
                Button cancelBtn = (Button) dialogContractor.findViewById(R.id.cancel_url);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogContractor.dismiss();
                    }
                });
                final Button urlBtn = dialogContractor.findViewById(R.id.ok_url_entry);
                urlBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted;
                        if (urlBtn.getText().toString().equals(""))
                            isInserted = (urlDb.insertData("http://demo.ifca.com.vn"));
                        else
                            isInserted = (urlDb.insertData(urlBtn.getText().toString()));
                        if (!isInserted) {
                            Toast.makeText(getApplicationContext(), "Error: Unable to Add URL!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialogContractor.show();
                break;
            case R.id.nav_logout:
                Toast.makeText(this,
                        "Logout"
                        , Toast.LENGTH_SHORT)
                        .show();
                break;

            default:
                break;

        }

        return true;
    }
}
