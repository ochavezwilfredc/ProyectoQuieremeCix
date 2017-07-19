package com.uss.crist.quieremecix.Controlador.Ubicacion;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;


public class Mapafragmento extends SupportMapFragment {


    public Mapafragmento() {

    }


    public static Mapafragmento newInstance() {

        return new Mapafragmento();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View root=super.onCreateView(inflater,container,savedInstanceState);
        return root;
    }

}
