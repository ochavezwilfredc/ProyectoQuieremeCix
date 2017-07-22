package com.uss.crist.quieremecix.Controlador;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.uss.crist.quieremecix.Modelo.Requesthandler;
import com.uss.crist.quieremecix.R;
import com.uss.crist.quieremecix.Servicios.Constantes;
import com.uss.crist.quieremecix.Servicios.Hash;
import com.uss.crist.quieremecix.Servicios.Servicios;

import java.util.HashMap;

public class ActulizarPasswordActivity extends AppCompatActivity implements View.OnClickListener{
    Servicios servicios;
    Hash hash;
    private EditText et_pass1, et_pass2;
    private Button btn_actualizar_pass, btn_cancelar_pass;

    String id_persona ,password_act;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actulizar_password);

        servicios = new Servicios();
        hash = new Hash();

        Bundle bundle = getIntent().getExtras();
        id_persona = bundle.getString(Constantes.KEY_ID);

        et_pass1 =(EditText)findViewById(R.id.campo_password);
        et_pass2 =(EditText)findViewById(R.id.campo_password2);
        btn_actualizar_pass = (Button)findViewById(R.id.btn_actualizarPass);
        btn_actualizar_pass.setOnClickListener(this);
        btn_cancelar_pass = (Button)findViewById(R.id.btn_cancelar_pass);
        btn_cancelar_pass.setOnClickListener(this);

    }

    public boolean validar()
    {
        boolean ok = false;
        if (et_pass1.getText().toString().trim().length() ==0 ||
                et_pass2.getText().toString().trim().length() ==0){
            et_pass1.setError(getString(R.string.error_incorrect_password_vacio));
            et_pass1.requestFocus();
            return false;
        }

        if (!servicios.validarPasswordIguales(et_pass1.getText().toString().trim(),et_pass2.getText().toString().trim())){
            et_pass1.setError(getString(R.string.error_incorrect_password_iquals));
            et_pass1.requestFocus();
        }else{
            ok=true;
            password_act = hash.md5( et_pass1.getText().toString().trim());

        }

        return ok;
    }

    private void actualizarPassword(){

        //Ejecuta hilos secu ndarios o asincronos
        class editPerfil extends AsyncTask<Void,Void,String> {
            ProgressDialog loaddin;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loaddin = ProgressDialog.show(ActulizarPasswordActivity.this,"Actualizando...","Espere...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loaddin.dismiss();
                servicios.mensaje(ActulizarPasswordActivity.this,s);
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Constantes.KEY_ID,id_persona);
                params.put(Constantes.KEY_CLAVE,password_act);

                Requesthandler rh =  new Requesthandler();
                String res =  rh.sendPostRequest(Constantes.UPDATE_PASSWORD,params);
                return res;
            }
        }

        editPerfil ae =  new editPerfil();
        ae.execute();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_actualizarPass:
                if (validar()){
                    //servicios.mensaje(ActulizarPasswordActivity.this,password_act);
                    actualizarPassword();
                    startActivity(new Intent(ActulizarPasswordActivity.this,LoginActivity.class));
                }
                break;

            case R.id.btn_cancelar_pass:
                salir().show();
                break;
        }


    }

    public AlertDialog salir() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActulizarPasswordActivity.this);

        builder.setTitle("Aviso")
                .setIcon(R.drawable.ic_warning)
                .setMessage("¿ Esta seguro de eliminar los cambios ?")
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                .setPositiveButton("CONFIRMAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

        return builder.create();
    }
}
