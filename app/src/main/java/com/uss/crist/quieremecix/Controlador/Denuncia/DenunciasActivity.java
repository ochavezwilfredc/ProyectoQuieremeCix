package com.uss.crist.quieremecix.Controlador.Denuncia;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.uss.crist.quieremecix.R;
import com.uss.crist.quieremecix.Servicios.Constantes;

public class DenunciasActivity extends AppCompatActivity {

    // Atributos
    ListView listView;
    DenunciaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncias);
        // Obtener instancia de la lista
        listView= (ListView) findViewById(R.id.listView);

        Bundle bundle = getIntent().getExtras();
        final String id_persona = bundle.getString(Constantes.KEY_ID);

        // Crear adaptador y setear
        adapter = new DenunciaAdapter(this);
        listView.setAdapter(adapter);

        //************ Preguntar **************
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //adapter.obtenerDenuncia(position).getId();
                //Toast.makeText(DenunciasActivity.this,String.valueOf(position),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DenunciasActivity.this,Detalle_DenunciaActivity.class);
                intent.putExtra(Constantes.KEY_ID,adapter.obtenerDenuncia(position).getId());
                startActivity(intent);

            }
        });

        //**************************************

        //**** Nueva denuncia ****
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_nueva_denuncia);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Se presionó el FAB", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(DenunciasActivity.this,NuevaDenunciaActivity.class);
                intent.putExtra(Constantes.KEY_ID,id_persona);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
