package com.atfalna.loginwithmysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    EditText name , email , password , gender , phone ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.txt_name);
        email= (EditText) findViewById(R.id.txt_email);
        password= (EditText) findViewById(R.id.txt_password);
        gender= (EditText) findViewById(R.id.txt_gender);
        phone= (EditText) findViewById(R.id.txt_phone);

    }
    public void OnReg(View view)
    {
        String str_name = name.getText().toString();
        String str_email = email.getText().toString();
        String str_password = password.getText().toString();
        String str_gender = gender.getText().toString();
        String str_phone = phone.getText().toString();

        String type = "register";

        backgroundRegister backgroundregister = new backgroundRegister(this);
        backgroundregister.execute(type , str_name , str_email , str_password , str_gender , str_phone);
    }
}
