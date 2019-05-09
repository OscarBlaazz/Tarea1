package com.oscarblas.tarea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inicio);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button boto1= (Button) findViewById(R.id.ambiente);
        boto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iniciarOtra;
                iniciarOtra = new Intent(MainInicio.this, MainActivity.class);
                startActivity(iniciarOtra);
                finish();
            }
        });

        Button botonUltra= (Button) findViewById(R.id.ultra);
        botonUltra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iniciarOtra;
                iniciarOtra = new Intent(MainInicio.this, Main2Activity.class);
                startActivity(iniciarOtra);
                finish();
            }
        });

        Button botonHumedad= (Button) findViewById(R.id.humedad);
        botonHumedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iniciarOtra3;
                iniciarOtra3 = new Intent(MainInicio.this, Humedad.class);
                startActivity(iniciarOtra3);
                finish();
            }
        });
    }

}
