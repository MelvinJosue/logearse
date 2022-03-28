package com.example.logearse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText usuario11,contra11;
TextView tv_registrar;
Button iniciar;
String usuario2,contra2;
    String url = "https://bellicose-factor.000webhostapp.com/hellokityy.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_registrar= findViewById(R.id.btnregistrar);
        usuario11=findViewById(R.id.TVUSU);
        contra11=findViewById(R.id.TVCONTRA);
        iniciar=findViewById(R.id.BTNINICI);

        tv_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,registro.class);
                MainActivity.this.startActivity(intent);

            }
        });
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hola();

            }
        });

    }
    public void hola(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Por favor espere,validando");

        progressDialog.show();

        usuario2= usuario11.getText().toString().trim();
        contra2= contra11.getText().toString().trim();


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                if(response.equalsIgnoreCase("Ingreso correctamente")){

                    usuario11.setText("");
                    contra11.setText("");
                    startActivity(new Intent(getApplicationContext(),usuario.class));
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                }

            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("usuario",usuario2);
                params.put("paswword",contra2);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }
}