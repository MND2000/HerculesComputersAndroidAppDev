package za.ac.cput.herculescomputers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    Database db;
    EditText regUsername;
    EditText regPassword;
    EditText confPassword;
    Button regButton;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new Database(this);
        regUsername = findViewById(R.id.regUsername);
        regPassword = findViewById(R.id.regPassword);
        confPassword = findViewById(R.id.confPassword);
        regButton = findViewById(R.id.regButton);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(Registration.this, MainActivity.class);
                startActivity(backIntent);
            }
        });

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = regUsername.getText().toString().trim();
                String pass = regPassword.getText().toString().trim();
                String confPass = confPassword.getText().toString().trim();

                if (pass.equals(confPass)) {
                    long val = db.addUser(user, pass);
                    if (val > 0) {

                    Toast.makeText(Registration.this, "You have successfully registered", Toast.LENGTH_SHORT).show();
                    Intent goToLogin = new Intent(Registration.this, MainActivity.class);
                    startActivity(goToLogin);
                }

                    else {

                        Toast.makeText(Registration.this, "Registration error", Toast.LENGTH_SHORT).show();

                    }


                }




                    else {

                    Toast.makeText(Registration.this, "Password does not match", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}