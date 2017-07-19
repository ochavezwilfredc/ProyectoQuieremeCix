package com.uss.crist.quieremecix.Controlador.Denuncia;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.uss.crist.quieremecix.R;

public class NuevaDenunciaActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText textoTitulo, textoLugar, textoRaza, textoColor, textoDescripcion,textoTipoMasc;
    private Button btn_tipoMascota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuena_denuncia);
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
                break;

            case R.id.btn_cancelar_nuevaD:
                break;
        }
    }

  

}
