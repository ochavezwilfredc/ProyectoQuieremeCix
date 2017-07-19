package com.uss.crist.quieremecix.Modelo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.uss.crist.quieremecix.Servicios.Constantes;

import java.util.HashMap;

/**
 * Created by crist on 12/07/2017.
 */

public class Unete_Actualizar {

    
    public void actualizar_persona_uneteID(final Activity activity, final String id){
        
        //Ejecuta hilos secundarios o asincronos
        class editPersonaID extends AsyncTask<Void,Void,String> {

            ProgressDialog loaddin;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loaddin = ProgressDialog.show(activity,"Solicitando","Espere...",false,false);

            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loaddin.dismiss();
                Toast.makeText(activity,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put("id",id);
                Requesthandler rh =  new Requesthandler();
                String res =  rh.sendPostRequest(Constantes.UPDATE_UNETEID,params);
                return res;
            }
        }

        editPersonaID ae =  new editPersonaID();
        ae.execute();

    }

}
