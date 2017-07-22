package com.uss.crist.quieremecix.Controlador;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uss.crist.quieremecix.Controlador.Denuncia.DenunciasActivity;
import com.uss.crist.quieremecix.Controlador.Ubicacion.UbicacionActivity;
import com.uss.crist.quieremecix.Modelo.Unete_Actualizar;
import com.uss.crist.quieremecix.R;
import com.uss.crist.quieremecix.Servicios.Constantes;
import com.uss.crist.quieremecix.Servicios.Servicios;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_perfil, btn_denuncia, btn_adoptame, btn_unete, btn_ubicación, btn_compartir, btn_salir;
    Servicios servicios;
    private String id_persona, nom_ape, email, ban_unete;
    Unete_Actualizar unete_actualizar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //********** Resivir los valores de los parametros ************
        Bundle bundle = getIntent().getExtras();
        id_persona =bundle.getString(Constantes.KEY_ID);
        nom_ape=bundle.getString(Constantes.KEY_NOM_APE);
        email=bundle.getString(Constantes.KEY_EMAIL);
        ban_unete=bundle.getString(Constantes.KEY_UNETE);


        servicios = new Servicios();
        btn_perfil = (Button)findViewById(R.id.btn_perfil);
        btn_perfil.setOnClickListener(this);
        btn_denuncia = (Button)findViewById(R.id.btn_denuncias);
        btn_denuncia.setOnClickListener(this);
        btn_adoptame = (Button)findViewById(R.id.btn_adoptar);
        btn_adoptame.setOnClickListener(this);
        btn_unete = (Button)findViewById(R.id.btn_unete);
        btn_unete.setOnClickListener(this);
        btn_ubicación = (Button)findViewById(R.id.btn_ubicacion);
        btn_ubicación.setOnClickListener(this);
        btn_compartir = (Button)findViewById(R.id.btn_compartir);
        btn_compartir.setOnClickListener(this);
        btn_salir = (Button)findViewById(R.id.btn_salir);
        btn_salir.setOnClickListener(this);

       if(ban_unete.equals("1")) {
           btn_unete.setVisibility(View.GONE);
       }else{
           btn_unete.setVisibility(View.VISIBLE);
       }

       unete_actualizar = new Unete_Actualizar();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_perfil:
                Intent intent = new Intent(new Intent(MenuActivity.this,PerfilActivity.class));
                intent.putExtra(Constantes.KEY_ID, id_persona);
                startActivity(intent);
                break;

            case R.id.btn_denuncias:
                Intent intent2 = new Intent(MenuActivity.this, DenunciasActivity.class);
                intent2.putExtra(Constantes.KEY_ID,id_persona);
                startActivity(intent2);
                break;

            case R.id.btn_adoptar:
                break;

            case R.id.btn_unete:
                unete();
                break;

            case R.id.btn_ubicacion:
                startActivity(new Intent(MenuActivity.this,UbicacionActivity.class));
                break;

            case R.id.btn_compartir:
                servicios.compartirCon(MenuActivity.this);
                break;

            case R.id.btn_salir:
                salir().show();
                break;
        }
    }
/*
    public AlertDialog unete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);

        builder.setTitle("Unete")
                .setIcon(R.drawable.ic_trato)
                .setMessage(getString(R.string.info_unuete))
                .setPositiveButton("ACEPTAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                unete_actualizar.actualizar_persona_uneteID(MenuActivity.this, id_persona);
                            }
                        })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

        return builder.create();
    }*/

    public void unete() {
        new AlertDialog.Builder(MenuActivity.this)
                .setTitle("UNETE")
                .setIcon(R.drawable.ic_trato)
                .setMessage(getString(R.string.info_unuete))
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                unete_actualizar.actualizar_persona_uneteID(MenuActivity.this, id_persona);
                                btn_unete.setVisibility(View.GONE);
                                dialog.cancel();
                            }
                        })
                .setNeutralButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();
    }


    public AlertDialog salir() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);

        builder.setTitle("Aviso")
                .setIcon(R.drawable.ic_warning)
                .setMessage("¿ La sesión finalizará ?")
                .setPositiveButton("CONFIRMAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });

        return builder.create();
    }
}
