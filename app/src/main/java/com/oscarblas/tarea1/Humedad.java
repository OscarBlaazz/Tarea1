package com.oscarblas.tarea1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import de.nitri.gauge.Gauge;

public class Humedad extends AppCompatActivity {

    private EditText fecha;
    private int[] arregloValores;
    private int promedioValores;

    LinkedList<Integer> valuesList = new LinkedList<Integer>();
    Gauge gauge;

    String acceso = "a5uEvHZro4";
    String ambiente = "E1yGxKAcrg";
    String ultra ="8IvrZCP3qa";
    String humedad ="VIbSnGKyLW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_humedad);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fecha = findViewById(R.id.fecha);

        Button botonActualizar = (Button) findViewById(R.id.actualizar);
        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceWebHumedad(fecha.getText().toString());
            }
        });

        Button botonAceptar = (Button) findViewById(R.id.aceptar);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceWebHumedad(fecha.getText().toString());
            }
        });

        Button botonVolver = (Button) findViewById(R.id.volver2);
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iniciarOtra;
                iniciarOtra = new Intent(Humedad.this, MainInicio.class);
                startActivity(iniciarOtra);
                finish();
            }
        });
        gauge = (Gauge) findViewById(R.id.humedad);
    }

    private void serviceWebHumedad(final String fecha){
        String WS_URL = " http://arrau.chillan.ubiobio.cl:8075/ubbiot/web/mediciones/medicionespordia/"+acceso+"/"+humedad+"/"+fecha;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(
                Request.Method.POST,
                WS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("LOG WS", response);
                        try {
                            JSONObject respondeJson = new JSONObject(response);
                            readJson3(respondeJson);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LOG WS", error.toString());
            }
        }
        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("Fecha", fecha);
                return params;
            }
        };
        requestQueue.add(request);
    }

    public void readJson3(JSONObject objson) throws JSONException {

        JSONArray arreglo = new JSONArray();
        arreglo = objson.getJSONArray("data");
        int i;
        int suma = 0;
        int numEntero=0;

        arregloValores = new int[110];

        for (i = 0; i < arreglo.length(); i++) {

            int p = 0;
            /*String fecha = arreglo.getJSONObject(i).getString("fecha");
            String hora = arreglo.getJSONObject(i).getString("hora");*/
            String valor = arreglo.getJSONObject(i).getString("valor");
            numEntero = Integer.parseInt(valor);
            valuesList.add(numEntero);
            suma += numEntero;
            gauge.moveToValue(numEntero);


            /*System.out.println("---------------------------------------------------------\n");
            System.out.println("Valor: "+valor);*/

        }
        promedioValores = suma / i;

        System.out.println("Valores del dia: "+i+", promedio: "+promedioValores);


    }

}
