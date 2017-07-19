package com.uss.crist.quieremecix.Controlador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.uss.crist.quieremecix.R;

public class MainActivity extends AppCompatActivity {

    Button btn_login;

    ViewFlipper viewFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = (Button)findViewById(R.id.login_boton);
        viewFlipper = (ViewFlipper) this.findViewById(R.id.viewFlipper);
        viewFlipper.startFlipping();
        viewFlipper.setFlipInterval(2000);



    }

    public void OnclickLogin(View view){
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }
}
