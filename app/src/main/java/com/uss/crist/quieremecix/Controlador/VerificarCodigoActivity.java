package com.uss.crist.quieremecix.Controlador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.uss.crist.quieremecix.R;
import com.uss.crist.quieremecix.Servicios.Constantes;

public class VerificarCodigoActivity extends AppCompatActivity implements View.OnClickListener{
    private String id, email;
    EditText codigo;
    Button btn_aceptar_codv, btn_cancelar_codv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar_codigo);

        Bundle bundle = getIntent().getExtras();
        id =bundle.getString(Constantes.KEY_ID);
        email=bundle.getString(Constantes.KEY_EMAIL);

        btn_aceptar_codv = (Button)findViewById(R.id.btn_aceptar_codVer);
        btn_aceptar_codv.setOnClickListener(this);

        btn_cancelar_codv = (Button)findViewById(R.id.btn_cancelar_codVer);
        btn_cancelar_codv.setOnClickListener(this);

        codigo = (EditText) findViewById(R.id.texto_codigo_validacion);


    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_aceptar_codVer){
            Intent intent = new Intent(VerificarCodigoActivity.this,ActulizarPasswordActivity.class);
            intent.putExtra(Constantes.KEY_ID,id);
            startActivity(intent);
        }else{
            if (view.getId()==R.id.btn_cancelar_codVer){

            }
        }
    }
}
