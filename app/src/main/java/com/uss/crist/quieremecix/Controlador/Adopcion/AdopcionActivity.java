package com.uss.crist.quieremecix.Controlador.Adopcion;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.uss.crist.quieremecix.Controlador.Denuncia.DenunciaAdapter;
import com.uss.crist.quieremecix.Controlador.Denuncia.DenunciasActivity;
import com.uss.crist.quieremecix.Controlador.Denuncia.Detalle_DenunciaActivity;
import com.uss.crist.quieremecix.Controlador.Denuncia.NuevaDenunciaActivity;
import com.uss.crist.quieremecix.R;
import com.uss.crist.quieremecix.Servicios.Constantes;

public class AdopcionActivity extends AppCompatActivity {

    // Atributos
    ListView listView;
    AdopcionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopcion);

        // Obtener instancia de la lista
        listView= (ListView) findViewById(R.id.listView_adopcion);

        Bundle bundle = getIntent().getExtras();
        final String id_persona = bundle.getString(Constantes.KEY_ID);

        // Crear adaptador y setear
        adapter = new AdopcionAdapter(this);
        listView.setAdapter(adapter);

        //************ Preguntar **************
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Intent intent = new Intent(AdopcionActivity.this,Detalle_AdopcionActivity.class);
                intent.putExtra(Constantes.KEY_ID,adapter.obtenerAdopcion(position).getId());
                startActivity(intent);

            }
        });

        //**************************************

        //**** Nueva Adopcion ****
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_nueva_adopcion);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdopcionActivity.this,NuevaAdopcionActivity.class);
                intent.putExtra(Constantes.KEY_ID,id_persona);
                startActivity(intent);
            }
        });

    }
}
