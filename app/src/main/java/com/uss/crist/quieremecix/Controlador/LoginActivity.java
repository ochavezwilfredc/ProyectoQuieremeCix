package com.uss.crist.quieremecix.Controlador;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.uss.crist.quieremecix.Modelo.Requesthandler;
import com.uss.crist.quieremecix.R;
import com.uss.crist.quieremecix.Servicios.Constantes;
import com.uss.crist.quieremecix.Servicios.Hash;
import com.uss.crist.quieremecix.Servicios.Servicios;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, View.OnClickListener {
    Button btn_crear, btn_entrar;
    CheckBox cb_recordarme;
    TextView texto_olvidar;
    private int op;
    Servicios servicios;
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView ac_email;
    private EditText ac_password;
    private View m_progresView;
    private View m_loginForm;
    private View mProgressView;

    //Para comprabar el email_persona de la bd mysql

    String email_persona, clave, id_usu, nom_ape,estado_unete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        texto_olvidar =(TextView)findViewById(R.id.olvidar_text);
        texto_olvidar.setOnClickListener(this);

        btn_crear = (Button)findViewById(R.id.btn_crear);
        btn_crear.setOnClickListener(this);
        btn_entrar= (Button)findViewById(R.id.btn_entrar);
        btn_entrar.setOnClickListener(this);

        ac_email=(AutoCompleteTextView)findViewById(R.id.email) ;
        ac_password=(EditText)findViewById(R.id.password) ;


        SharedPreferences prefe=LoginActivity.this.getSharedPreferences("datos", Context.MODE_PRIVATE);
        ac_email.setText(prefe.getString(Constantes.KEY_EMAIL,""));
        ac_password.setText(prefe.getString(Constantes.KEY_PASS,""));

        cb_recordarme = (CheckBox)findViewById(R.id.recordar_check);

       servicios = new Servicios();


        //**********************

        ac_password = (EditText) findViewById(R.id.password);
        //Adiciona el evento ENTER sobre la caja de texto contraseña
        ac_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    LoginActivity.this.logear();
                    return true;
                }
                return false;
            }
        });
        m_progresView = findViewById(R.id.login_progress);
        m_loginForm = findViewById(R.id.login_form);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_crear:
                startActivity(new Intent(LoginActivity.this,RegistrarActivity.class));
                break;
            case R.id.btn_entrar:
                LoginActivity.this.logear();
                break;
            case R.id.olvidar_text:
                startActivity(new Intent(LoginActivity.this,RecuperarCuentaActivity.class));
                break;
        }
    }

    public void logear(){
        // Loguear...
        if (cb_recordarme.isChecked()) {
            SharedPreferences preferencias=LoginActivity.this.getSharedPreferences("datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=preferencias.edit().clear();
            editor.putString(Constantes.KEY_EMAIL, ac_email.getText().toString().trim());
            editor.putString(Constantes.KEY_PASS, ac_password.getText().toString().trim());
            editor.commit();
        }
        attemptLogin();

    }

    private void getPersonaEmail(){
        final String email_aux =  ac_email.getText().toString().trim();

        //Ejecuta hilos secundarios o asincronos
        class getPersona extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
               // servicios.mensaje(LoginActivity.this,s);
                JSONArray emp = null;
                try {
                    emp = new JSONArray(s);
                    id_usu=emp.getString(0);
                    nom_ape =emp.getString(1);
                    email_persona = emp.getString(2);
                    clave = emp.getString(3);
                    estado_unete = emp.getString(4);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected String doInBackground(Void... v) {
                Requesthandler rh =  new Requesthandler();
                String res =  rh.sendGetRequestParam(Constantes.GET_PERSONA_EMAIL,email_aux);
                return res;
            }
        }

        getPersona ae =  new  getPersona();
        ae.execute();

    }



    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        /**Comprobar si el objeto para el email_persona esta vacio o no*/
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        ac_email.setError(null);
        ac_password.setError(null);

        /**Obtiene y guarda los valores respectivos para el email y el password*/
        Hash hash = new Hash();
        String email = ac_email.getText().toString().trim();
        String password = ac_password.getText().toString().trim();
        String pass;

        /**
         * Bandera evidenciar algun error durante la validación de los datos
         * Variable para contener el campo a ser enfocado
         */
        boolean cancel = false;
        View focusView = null;

        /**Comprobar si el password ingresado no es nulo y es valido*/
        if (!TextUtils.isEmpty(password) && !servicios.isPasswordValid(password)) {
            /**Envia el error a la caja de Texto*/
            ac_password.setError(getString(R.string.error_invalid_password));
            focusView = ac_password;
            cancel = true;
        }

        /**Comprobar si el campo para el Email esta vacio. */
        if (TextUtils.isEmpty(email)) {
            /**Envia el error a la caja de Texto*/
            ac_email.setError(getString(R.string.error_field_required));
            focusView = ac_email;
            cancel = true;

            /**Comprobar si el Email Ingresado es valido. */
        } else if (!servicios.validateEmail(email)) {
            /**Envia el error a la caja de Texto*/
            ac_email.setError(getString(R.string.error_invalid_email));
            focusView = ac_email;
            cancel = true;
        }

        /**Comprobar si hubo un fallo durante el ingreso de datos*/
        if (cancel) {
            /**Enfocar el Campo del Error*/
            focusView.requestFocus();
        } else {
            /**Cargar Animación con una barra de progreso*/
            showProgress(true);
            pass=hash.md5(password);

            LoginActivity.this.getPersonaEmail();

            /**Crea un nuevo Usuario a partir de la clase  mAuthTask*/
            mAuthTask = new UserLoginTask(email, pass);
            /**Lanzar el Hilo para la Autenticación del Usuario*/
            mAuthTask.execute((Void) null);
        }
    }



    /**
     * CARGAR ANIMACION DE UNA BARRA DE PROGRESO CIRCULAR
     * Muestra la interfaz de email_persona de progreso y oculta el formulario de inicio de sesión.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            m_loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
            m_loginForm.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    m_loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            m_progresView.setVisibility(show ? View.VISIBLE : View.GONE);
            m_progresView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    m_progresView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            m_progresView.setVisibility(show ? View.VISIBLE : View.GONE);
            m_loginForm.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    //Consultar Asincronicamente el o los emails almacenados en el perfil del email_persona
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        /**Obtener y Retornar un Cursor que apunta a una tabla con los datos especificados  en la
         * consulta*/
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primar eymail addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        ac_email.setAdapter(adapter);
    }




    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    //==============================================================================================
    //  CLASE PARA ALMACENAR LOS USUARIOS Y METODOS ASICRONOS PARA VALIDAR EL USUARIO INGRESADO
    //==============================================================================================
    //----------------------------------------------------------------------------------------------
    //Clase para Almacenar los Usuarios

    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        //------------------------------------------------------------------------------------------
        //Hilo para validar si el Correo y contraseña ingresados son correctos
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                /**Ejecución del Hilo con un retraso de 2 segundos*/
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            /**Ciclo en el cual se comparan los Emails y Contraseñas alamacenados en el Array tipo
             * string definido al inicio del activity y el email y clave ingresados por el email_persona
             * en el formulario de Login*/
            op=-1;
            if(mEmail.equals(email_persona) && (mPassword.equals(clave))){
                return true;}

            if(mEmail.equals(email_persona)){
                if(!mPassword.equals(clave)){
                    op=0;
                }
            }else{
                op=1;
            }
            return false;
        }

        //------------------------------------------------------------------------------------------
        //Muestra en el Activity actual el resultado de la tarea que se ejecuto en el Hilo

        /**
         * En este caso Abre el Activity Bienvenido si los datos Fueron correctos de lo contrario
         * Lanza un mensaje Evidenciando el problema
         */

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);
            if (success) {
                Intent ventana_menu = new Intent(LoginActivity.this,MenuActivity.class);
                ventana_menu.putExtra(Constantes.KEY_ID,id_usu);
                ventana_menu.putExtra(Constantes.KEY_NOM_APE, nom_ape);
                ventana_menu.putExtra(Constantes.KEY_EMAIL, email_persona);
                ventana_menu.putExtra(Constantes.KEY_UNETE, estado_unete);
                startActivity(ventana_menu);

            } else {
                if (op == 0) {
                    ac_password.setError(getString(R.string.error_incorrect_password));
                    ac_password.requestFocus();
                } else {
                    if(op==1){
                        ac_email.setError(getString(R.string.error_invalid_email));
                        ac_email.requestFocus();
                    }else{
                        Toast toast = Toast.makeText(LoginActivity.this, R.string.alert_not_found_email, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();
                    }
                }
            }
        }

        //------------------------------------------------------------------------------------------
        //En caso de que se cancele la Tarea inmersa en el Hilo
        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }







}
