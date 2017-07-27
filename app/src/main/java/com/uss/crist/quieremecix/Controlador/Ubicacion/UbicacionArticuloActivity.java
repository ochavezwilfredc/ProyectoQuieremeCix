package com.uss.crist.quieremecix.Controlador.Ubicacion;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.uss.crist.quieremecix.R;
import com.uss.crist.quieremecix.Servicios.Constantes;

import java.math.BigInteger;

public class UbicacionArticuloActivity extends AppCompatActivity  implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener{


    private GoogleMap mMap;
    private CameraUpdate camara;


    public double EXTRA_LATITUD ;
    public double EXTRA_LONGITUD;

    private Marker markerQuiereme;

    private static final int LOCATION_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion_articulo);
        Bundle bundle = getIntent().getExtras();
        EXTRA_LATITUD = bundle.getDouble(Constantes.KEY_LATITUD);
        EXTRA_LONGITUD= bundle.getDouble(Constantes.KEY_LONGITUD);

        // Obtenga el SupportMapFragment y se notificará cuando el mapa esté listo para ser utilizado.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapp_ubi_art);

        // Registrar escucha onMapReadyCallback
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Establesco los tipos de mapa(Hibrido, Normal, Satelite)
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // Añadir un marcador en la Universidad Señor de Sipan y mover la cámara
        LatLng quiereme = new LatLng(EXTRA_LATITUD,EXTRA_LONGITUD);
        int radius = 80;
        markerQuiereme = googleMap.addMarker(new MarkerOptions()
                .position(quiereme)
                .title("Ubicación de su mascota")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location))
        );

        //-----------------------------------------------------------------------------
        CircleOptions circleOptions = new CircleOptions()
                .center(quiereme)
                .radius(radius)
                .strokeColor(Color.parseColor("#0D47A1"))
                .strokeWidth(2)
                .fillColor(Color.argb(32, 33, 150, 243));

        Circle circle = mMap.addCircle(circleOptions);

        //---------------------------------------------------------------------------------

        //Aumento el Zoom de la camara(17%) y las posciciones en la universidad señor de sipán
        //y se lo adicionamos a nuestro mapa
        camara = CameraUpdateFactory.newLatLngZoom(quiereme, 17);
        mMap.animateCamera(camara);

        //Establesco el gps
        // Controles UI
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Mostrar diálogo explicativo
            } else {
                // Solicitar permiso
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_REQUEST_CODE);
            }
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);
        // mMap.setMyLocationEnabled(true);

        // Marcadores
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)));

        //Adicionamos los los botones de ZOOM
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Eventos
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            // ¿Permisos asignados?
            if (permissions.length > 0 &&
                    permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mMap.setMyLocationEnabled(true);
            } else {
                Toast.makeText(this, "Error de permisos", Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (marker.equals(markerQuiereme)) {

            QuieremeDialogFragment.newInstance(marker.getTitle(),
                    getString(R.string.ultima_ubicacion_titulo))
                    .show(getSupportFragmentManager(), null);
        }
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }



}
