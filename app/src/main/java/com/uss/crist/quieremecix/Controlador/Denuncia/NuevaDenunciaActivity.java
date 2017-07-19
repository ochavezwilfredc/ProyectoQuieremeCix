package com.uss.crist.quieremecix.Controlador.Denuncia;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.uss.crist.quieremecix.Controlador.LoginActivity;
import com.uss.crist.quieremecix.Controlador.RegistrarActivity;
import com.uss.crist.quieremecix.R;
import com.uss.crist.quieremecix.Servicios.Servicios;

public class NuevaDenunciaActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText textoTitulo, textoLugar, textoRaza, textoColor, textoDescripcion,textoTipoMasc;
    private Button btn_tipoMascota;
    Servicios servicios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuena_denuncia);
        servicios = new Servicios();

        textoTitulo = (EditText)findViewById(R.id.texto_tituloN);
        textoLugar = (EditText)findViewById(R.id.texto_LugarN);
        textoRaza = (EditText)findViewById(R.id.texto_RazaN);
        textoColor = (EditText)findViewById(R.id.texto_ColorN);
        textoTipoMasc = (EditText)findViewById(R.id.texto_TipoMascotaN);
        textoDescripcion = (EditText)findViewById(R.id.texto_DescripcionN);

        btn_tipoMascota = (Button)findViewById(R.id.btn_tipoMascotaN);
        btn_tipoMascota.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_tipoMascotaN:

                break;

            case R.id.btn_aceptar_nuevaD:
                if (validar()){
                    servicios.mensaje(NuevaDenunciaActivity.this,"Todo bien");
                }
                break;

            case R.id.btn_cancelar_nuevaD:
                break;
        }
    }

    public boolean validar(){
        boolean ok=false;
        if (!servicios.validarNombre(textoTitulo.getText().toString())){
            textoTitulo.setError(getString(R.string.error_titulo));
            textoTitulo.findFocus();
        }else{
            ok=true;
        }
        return ok;
    }


    public AlertDialog salir() {
        AlertDialog.Builder builder = new AlertDialog.Builder(NuevaDenunciaActivity.this);

        builder.setTitle("Aviso")
                .setMessage("¿ Esta seguro ?")

                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(NuevaDenunciaActivity.this, LoginActivity.class));
                            }
                        })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

        return builder.create();
    }



}
