package com.atfalna.loginwithmysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txtun,txtpass;
    Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtun = (EditText) findViewById(R.id.txt_username);
        txtpass = (EditText) findViewById(R.id.txt_password);
        reg = (Button) findViewById(R.id.btn_reg);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });

    }


    public void onLogin(View view) {

        String username = txtun.getText().toString();
        String password = txtpass.getText().toString();

        String type = "login";

        backgroundWorker backgroundWorker = new backgroundWorker(this);
        backgroundWorker.execute(type , username , password);
    }

}