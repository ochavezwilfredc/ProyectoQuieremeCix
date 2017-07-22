package com.uss.crist.quieremecix.Controlador;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.uss.crist.quieremecix.Modelo.Requesthandler;
import com.uss.crist.quieremecix.R;
import com.uss.crist.quieremecix.Servicios.Constantes;
import com.uss.crist.quieremecix.Servicios.Servicios;

import java.util.HashMap;

public class ActualizarPerfilActivity extends AppCompatActivity implements View.OnClickListener{
    EditText et_nom_ape,  et_correo, et_telefono;
    Button btn_actualizar, btn_cancelar;
    Switch unete_switch;

    String id_persona, nom_ape, tel, email, ban_unete, foto;
    Servicios servicios;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizarperfil);

        servicios = new Servicios();

        //********** Resivir los valores de los parametros ************
        Bundle bundle = getIntent().getExtras();
        id_persona = bundle.getString(Constantes.KEY_ID);
        nom_ape = bundle.getString(Constantes.KEY_NOM_APE);
        tel = bundle.getString(Constantes.KEY_TELEFONO);
        email = bundle.getString(Constantes.KEY_EMAIL);
        ban_unete = bundle.getString(Constantes.KEY_UNETE);
        foto = bundle.getString(Constantes.KEY_IMAGEN);


        et_nom_ape = (EditText)findViewById(R.id.campo_nom_ape);
        et_nom_ape.setText(nom_ape);
        et_correo = (EditText)findViewById(R.id.campo_correo);
        et_correo.setText(email);
        et_telefono = (EditText)findViewById(R.id.campo_telefono);
        et_telefono.setText(tel);

        unete_switch =(Switch)findViewById(R.id.switch_unete);

/*
        if (ban_unete == "1"){
            unete_switch.setChecked(true);
        }else {
            unete_switch.setChecked(false);
        }*/

        btn_actualizar = (Button)findViewById(R.id.btn_aceptar_ap);
        btn_actualizar.setOnClickListener(this);
        btn_cancelar = (Button)findViewById(R.id.btn_cancelar_ap);
        btn_cancelar.setOnClickListener(this);




    }



    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_aceptar_ap){
            if (validar()){
                actualizarPerfil();
                finish();
                //startActivity(new Intent(ActualizarPerfilActivity.this,MenuActivity.class));
            }
        }else{
            if (view.getId() == R.id.btn_cancelar_ap){
                salir().show();
            }
        }
    }

    public boolean validar(){
        Boolean ok = false;
        if(!servicios.validarNombre(et_nom_ape.getText().toString().trim())){
            et_nom_ape.setError(getString(R.string.error_nombre));
            et_nom_ape.requestFocus();
        }else{
            if((!servicios.validateEmail(et_correo.getText().toString().trim()))){
                et_correo.setError(getString(R.string.error_invalid_email));
                et_correo.requestFocus();
            }else{
                if (!servicios.validadTelefono(et_telefono.getText().toString())){
                    et_telefono.setError(getString(R.string.error_invalid_phone));
                    et_telefono.requestFocus();
                }else {
                    ok=true;
                }
            }
        }

        return ok;
    }


    private void actualizarPerfil(){
        final  String nom_ape_per =  et_nom_ape.getText().toString().trim();
        final  String telefono_per =  et_telefono.getText().toString().trim();
        final  String correo_per =  et_correo.getText().toString().trim();

        //Ejecuta hilos secundarios o asincronos
        class editPerfil extends AsyncTask<Void,Void,String> {

            ProgressDialog loaddin;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loaddin = ProgressDialog.show(ActualizarPerfilActivity.this,"Actualizando...","Espere...",false,false);

            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loaddin.dismiss();
                servicios.mensaje(ActualizarPerfilActivity.this,s);
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Constantes.KEY_ID,id_persona);
                params.put(Constantes.KEY_NOM_APE,nom_ape_per);
                params.put(Constantes.KEY_TELEFONO,telefono_per);
                params.put(Constantes.KEY_EMAIL,correo_per);
                Requesthandler rh =  new Requesthandler();
                String res =  rh.sendPostRequest(Constantes.UPDATE_PERFIL,params);
                return res;
            }
        }

        editPerfil ae =  new editPerfil();
        ae.execute();

    }


    public AlertDialog salir() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActualizarPerfilActivity.this);

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
