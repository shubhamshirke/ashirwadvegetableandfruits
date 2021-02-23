package com.example.ashirwadvegetablesandfruits;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button newCustomer,oldCustomer,Show;
    SqlHelper sqlHelper=new SqlHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newCustomer=findViewById(R.id.newcustomer);
        oldCustomer=findViewById(R.id.oldcustomer);
        Show=findViewById(R.id.show);
        newCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity(view);
            }
        });
        oldCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity1(view);
            }
        });
        display();
    }
    public void nextActivity(View view)
    {
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
    public void nextActivity1(View view)
    {
        Intent intent=new Intent(this,Main3Activity.class);
        startActivity(intent);
    }

    public void display()
    {
        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cu=sqlHelper.fetch_data();

                if(cu.getCount()==0)
                {
                    show("Error","Customer Data not found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (cu.moveToNext())
                {
                    buffer.append("********************\n");
                    buffer.append("id: "+cu.getString(0)+"\n");
                    buffer.append("CustomerName: "+cu.getString(1)+"\n");
                    buffer.append("SocietyName: "+cu.getString(2)+"\n");
                    buffer.append("Paid: "+cu.getString(3)+"\n");
                    buffer.append("Balance: "+cu.getString(4)+"\n");
                    buffer.append("Total: "+cu.getString(5)+"\n");
                    buffer.append("********************\n");
                }
                //  show("***************************","*********************");
                show("Data",buffer.toString());
            }
        });
    }
    public void show(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
