package com.example.utsha.expensemanager;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class fillInfo extends AppCompatActivity {
Button button;
    EditText edit1;
    EditText edit2;
    EditText edit3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_info);
    }
    button = (Button) findViewById(R.id.button);
    edit1=(EditText)findViewById(R.id.savings);
    edit2=(EditText)findViewById(R.id.budget);
    edit3=(EditText)findViewById(R.id.income);
    button.setOnClickListener(this);
    @Override
    public void  onClick(View view)
    {
        String d=edit1.getText().toString();
        String m=edit2.getText().toString();
        String p=edit3.getText().toString();
        ContentValues values = new ContentValues();
        values.put("INCOME",d );
        values.put("SAVINGS",m);
        values.put("BUDGET",p);

        db.insertOrThrow("med_d", null,values);

    }
}

