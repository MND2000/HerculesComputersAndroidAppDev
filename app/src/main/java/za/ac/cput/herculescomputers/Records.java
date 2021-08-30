package za.ac.cput.herculescomputers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Records extends AppCompatActivity {

    Database db;
    EditText editMan, editName, editPrice, editQuantity;
    Button buttonAdd, buttonView, buttonUpdate, buttonDelete, searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        db = new Database(this);

        editMan = (EditText) findViewById(R.id.editMan);
        editName = (EditText) findViewById(R.id.editName);
        editPrice = (EditText) findViewById(R.id.editPrice);
        editQuantity = (EditText) findViewById(R.id.editQuantity);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        searchButton = (Button) findViewById(R.id.searchButton);

        addData();
        viewData();
        updateData();
        deleteData();
        searchData();

    }

    public void addData () {

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inserted = db.insertDetails(editMan.getText().toString(), editName.getText().toString(), editPrice.getText().toString(), editQuantity.getText().toString());

                if(inserted == true) {

                    Toast.makeText(Records.this, "Data successfully recorded", Toast.LENGTH_SHORT).show();
                }
                else {

                    Toast.makeText(Records.this, "Data was not recorded", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    public void viewData () {
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getAllData();

                if(res.getCount() == 0 ) {
                    showMessage("ERROR", "There was nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Manufacturer: "+res.getString(1)+ "\n");
                    buffer.append("Name: "+res.getString(2)+ "\n");
                    buffer.append("Price: "+res.getString(3)+ "\n");
                    buffer.append("Quantity: "+res.getString(4)+ "\n\n");
                }
                showMessage("Data",buffer.toString());

            }
        });

    }

    public void updateData () {

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean Update = db.updateData(editMan.getText().toString(), editName.getText().toString(), editPrice.getText().toString(), editQuantity.getText().toString());

                if (Update == true) {

                    Toast.makeText(Records.this, "Data was updated", Toast.LENGTH_SHORT).show();

                }
                else {

                    Toast.makeText(Records.this, "Data was not updated", Toast.LENGTH_SHORT).show();

                }

            }

        });

    }

    public void deleteData() {

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows = db.deleteData(editMan.getText().toString());

                if (deleteRows > 0) {
                    Toast.makeText(Records.this, "Data was deleted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Records.this, "Data was not deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void searchData() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getAllData();

                if(res.getCount() == 0 ) {
                    showMessage("ERROR", "There was nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Manufacturer: "+res.getString(1)+ "\n");
                    buffer.append("Name: "+res.getString(2)+ "\n");
                    buffer.append("Price: "+res.getString(3)+ "\n");
                    buffer.append("Quantity: "+res.getString(4)+ "\n\n");
                }

            }
        });
    }



    public void showMessage (String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}