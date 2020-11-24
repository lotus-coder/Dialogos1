package com.example.dialogos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    private EditText usuario, contrasena;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = findViewById(R.id.txtUsuario);
        contrasena = findViewById(R.id.txtContra);
        btnLogin = findViewById(R.id.btnLogin);
        eventos();
    }


    private void eventos (){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usuario.getText().toString().equals("usuario1") && contrasena.getText().toString().equals("123456")){
                    Intent i = new Intent(MainActivity.this,PestanasMain.class);
                    startActivity(i);
                }
            }
        });

    }



}