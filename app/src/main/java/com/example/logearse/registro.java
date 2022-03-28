package com.example.logearse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class registro extends AppCompatActivity {
EditText nombre,usuario,contra,edad;
Button registrar;
String nombre1,usuario1,contra1,edad1;
    RequestQueue requestQueue;
    ProgressDialog progressDialog;
    String HttpUrl = "https://bellicose-factor.000webhostapp.com/anashe.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombre=findViewById(R.id.Editnombre);
        usuario=findViewById(R.id.Editusuario);
        contra=findViewById(R.id.Editcontraseña);
        edad=findViewById(R.id.Editedad);
        registrar=findViewById(R.id.BTNREGIS);
        requestQueue = Volley.newRequestQueue(registro.this);
        progressDialog = new ProgressDialog(registro.this);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Los datos estan siendo registrados.yesss");
                progressDialog.show();
                losvalores();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {
                                progressDialog.dismiss();
                               // Toast.makeText(registro.this, "Felicidades ya se han mandado los datos.yesss", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        },
                        new Response.ErrorListener() {
                            private VolleyError error;
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                progressDialog.dismiss();
                              //  Toast.makeText(registro.this, "Ha fallado el envio", Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Nombre",nombre1);
                        params.put("usuario",usuario1);
                        params.put("paswword",contra1);
                        params.put("edad",edad1);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(registro.this);
                requestQueue.add(stringRequest);
                nombre.setText("");
                usuario.setText("");
                contra.setText("");
                edad.setText("");


            }
        });




    }
    public void losvalores(){
        usuario1 = usuario.getText().toString().trim();
        nombre1 = nombre.getText().toString().trim();
        edad1 = edad.getText().toString().trim();
        contra1 = contra.getText().toString().trim();

    }
}