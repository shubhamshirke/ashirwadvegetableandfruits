package com.example.ashirwadvegetablesandfruits;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDelete extends AppCompatActivity {
    EditText name1;
    Button delete;
    SqlHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        name1 =findViewById(R.id.editText);
        delete=findViewById(R.id.Delete);
        sqlHelper=new SqlHelper(this);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=name1.getText().toString();
                if (name.isEmpty())
                {
                    Toast.makeText(getBaseContext(),"Enter the name",Toast.LENGTH_SHORT).show();
                }
                else {
                    int c=sqlHelper.delete(name);
                    if (c<=0)
                    {
                        Toast.makeText(getBaseContext(),"Customer Details not deleted",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(),"Deleted successfully",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
