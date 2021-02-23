package com.example.ashirwadvegetablesandfruits;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    com.example.ashirwadvegetablesandfruits.SqlHelper sqlHelper=new com.example.ashirwadvegetablesandfruits.SqlHelper(this);
    Button submit;
    EditText customerName,societyName,paid,balance;
    //String total;
    int total1,a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        customerName=findViewById(R.id.editText1);
        societyName=findViewById(R.id.editText2);
        paid=(EditText) findViewById(R.id.editText3);
        balance=(EditText) findViewById(R.id.editText4);
        submit=findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validationCheck();
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public void validationCheck()
    {
        if(customerName.getText().toString().isEmpty()||societyName.getText().toString().isEmpty()||paid.getText().toString().isEmpty()||balance.getText().toString().isEmpty())
        {
            Toast.makeText(getBaseContext(),"Please fill all the fields",Toast.LENGTH_SHORT).show();
        }
        else {
            insert1();
        }
    }
    public int getTotal()
    {

        a=Integer.parseInt(balance.getText().toString());
        b=Integer.parseInt(paid.getText().toString());
        total1=a+b;
        Log.d("Msg", "onCreate:Print total "+total1);
        //total=Integer.toString(total1);
        return total1;
    }
    public void insert1()
    {
        boolean rs = sqlHelper.insert(customerName.getText().toString(), societyName.getText().toString(), paid.getText().toString(), balance.getText().toString(),getTotal());
        if (rs == true) {
            Toast.makeText(getBaseContext(), "Customer Data inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(), "Customer Data not inserted", Toast.LENGTH_SHORT).show();
        }
    }

}
