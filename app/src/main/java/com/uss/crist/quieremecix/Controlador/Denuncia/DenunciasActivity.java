package com.uss.crist.quieremecix.Controlador.Denuncia;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.uss.crist.quieremecix.Controlador.ActualizarPerfilActivity;
import com.uss.crist.quieremecix.Controlador.RegistrarActivity;
import com.uss.crist.quieremecix.Modelo.VolleySingleton;
import com.uss.crist.quieremecix.R;
import com.uss.crist.quieremecix.Servicios.Constantes;
import com.uss.crist.quieremecix.Servicios.Servicios;

import java.util.Hashtable;
import java.util.Map;

public class DenunciasActivity extends AppCompatActivity {

    // Atributos
    ListView listView;
    DenunciaAdapter adapter;
    Servicios servicios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncias);
        // Obtener instancia de la lista
        listView= (ListView) findViewById(R.id.listView_denuncias);

        Bundle bundle = getIntent().getExtras();
        final String id_persona = bundle.getString(Constantes.KEY_ID);

        // Crear adaptador y setear
        adapter = new DenunciaAdapter(this);
        listView.setAdapter(adapter);

        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Intent intent = new Intent(DenunciasActivity.this,Detalle_DenunciaActivity.class);
                intent.putExtra(Constantes.KEY_ID,adapter.obtenerDenuncia(position).getId());
                startActivity(intent);

            }
        });

        listView.setLongClickable(true);
        //You can Use this method
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {

                // TODO Auto-generated method stub
                alertDelet(adapter.obtenerDenuncia(pos).getId()).show();
                return true;
            }
        });

        //**** Nueva denuncia ****
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_nueva_denuncia);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DenunciasActivity.this,NuevaDenunciaActivity.class);
                intent.putExtra(Constantes.KEY_ID,id_persona);
                startActivity(intent);
            }
        });

        servicios = new Servicios();
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


    public AlertDialog alertDelet(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DenunciasActivity.this);

        builder.setTitle("Aviso")
                .setIcon(R.drawable.ic_delete)
                .setMessage("¿ Esta seguro de eliminar la denuncia?")
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
                                eliminarDenuncia(id);
                            }
                        });

        return builder.create();
    }


    private void eliminarDenuncia(String id){
        //Mostrar el diálogo de progreso

        final ProgressDialog loading = ProgressDialog.show(this,"Eliminando...","Espere por favor...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constantes.ELIMINAR_ARTICULOID+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Descartar el diálogo de progreso
                        if (loading.isShowing())
                            loading.dismiss();
                        //Mostrando el mensaje de la respuesta
                        servicios.mensaje(DenunciasActivity.this,s);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Descartar el diálogo de progreso
                        if (loading.isShowing())
                            loading.dismiss();

                        //Showing toast
                        servicios.mensaje(DenunciasActivity.this, volleyError.getMessage().toString());
                        finish();
                    }
                }){

        };

        //Agregar solicitud a la cola
        VolleySingleton.getInstance(DenunciasActivity.this).addToRequestQueue(stringRequest);

    }



}
