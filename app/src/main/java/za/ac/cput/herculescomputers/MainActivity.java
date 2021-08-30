package za.ac.cput.herculescomputers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText logUsername;
    EditText logPassword;
    Button logButton;
    TextView registerNow;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database (this);
        logUsername = findViewById(R.id.logUsername);
        logPassword = findViewById(R.id.logPassword);
        logButton = findViewById(R.id.logButton);
        registerNow = findViewById(R.id.registerNow);

        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(MainActivity.this, Registration.class);
                startActivity(regIntent);
            }
        });

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = logUsername.getText().toString().trim();
                String pass = logPassword.getText().toString().trim();
                Boolean res = db.checkUser(user,pass);

                if (res == true)
                {
                    Toast.makeText(MainActivity.this, "You have logged in successfully", Toast.LENGTH_SHORT).show();
                    Intent homePage = new Intent(MainActivity.this, Home.class);
                    startActivity(homePage);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "There was a login error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    {

    }
}