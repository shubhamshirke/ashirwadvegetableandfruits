package com.example.ashirwadvegetablesandfruits;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    EditText name;
    final Context context = this;
    Button updateAmount, search,message;
    SqlHelper sqlHelper;
    String name1, paid, balance;
    int a, b, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name = findViewById(R.id.editText1);
        search = findViewById(R.id.button2);
        message=findViewById(R.id.button4);
        sqlHelper = new SqlHelper(this);
        updateAmount = findViewById(R.id.button3);

        updateAmount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                nextActivity(arg0);

            }
        });
message.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
nextActivity1(view);
    }
});

        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                name1 = name.getText().toString();
                if (name1.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Enter the name", Toast.LENGTH_SHORT).show();
                }
                Cursor cu = sqlHelper.search_data(name1);
                if (cu.getCount() == 0) {
                    show("Error", "Customer Data not found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (cu.moveToNext()) {
                    buffer.append("********************\n");
                    buffer.append("id: " + cu.getString(0) + "\n");
                    buffer.append("CustomerName: " + cu.getString(1) + "\n");
                    buffer.append("SocietyName: " + cu.getString(2) + "\n");
                    buffer.append("Paid: " + cu.getString(3) + "\n");
                    buffer.append("Balance: " + cu.getString(4) + "\n");
                    buffer.append("Total: " + cu.getString(5) + "\n");
                    buffer.append("********************\n");
                }
                show("***************************", "*********************");
                show("Data", buffer.toString());

            }
        });
    }
            public void show(String title, String message) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle(title);
                builder.setMessage(message);
                builder.show();
            }

            public void nextActivity(View view) {
                Intent intent = new Intent(this, UpdateDelete.class);
                startActivity(intent);
            }

    public void nextActivity1(View view)
    {
        Intent intent=new Intent(this,Message.class);
        startActivity(intent);
    }


    }
