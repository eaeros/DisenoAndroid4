package com.eaerostwitter.disenoandroid4;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class basicActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText editTextWeb;
    private ImageButton imgBtnPhone;
    private ImageButton imgBtnWeb;
    private ImageButton imgBtnCamera;
    private final int PHONE_CALL_CODE = 100;
    private final int PICTURE_FROM_CAMARA = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextWeb = (EditText) findViewById(R.id.editTextWeb);
        imgBtnPhone = (ImageButton) findViewById(R.id.imageBtnPhone);
        imgBtnWeb = (ImageButton) findViewById(R.id.imageBtnWeb);
        imgBtnCamera = (ImageButton) findViewById(R.id.imageBtnCamera);

        //Activar flecha ir atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //este es el boton para hacer la llamada
        imgBtnPhone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /*cuando hacemos click en el boton del telefono, revisamos si no esta nulo o vacio
                entra a hacer la llamada y si no significa q esta nulo o vacio y q inserte un numero de telefono
                sin entra a hacer la llamada, se comprueba la version android q se esta corriendo si es mayor o igual
                se solicita permiso si no es una version vieja y se hace la llamada,
                 */
                String phoneNumber = editTextPhone.getText().toString();
                if (phoneNumber != null && !phoneNumber.isEmpty()) {
                    //Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phoneNumber));
                    //startActivity(intentCall);
                    //comprobar version actual de android q estamos corriendo
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        //comprobar si ha aceptado, no ha aceptado, o nunca se le ha preguntado
                        if(CheckPermission(Manifest.permission.CALL_PHONE)){
                            //ha aceptado
                            Intent i = new Intent(Intent.ACTION_CALL,Uri.parse("tel: "+phoneNumber));
                            if (ActivityCompat.checkSelfPermission(basicActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)return;
                            startActivity(i);
                        }
                        else{
                            //ha denegado o es la primera vez q se le pregunta
                            if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
                                //no se le ha preguntado aún
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},PHONE_CALL_CODE);
                            }else {
                                //ha denegado
                                Toast.makeText(basicActivity.this,"Please, enable the permission",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                i.addCategory(Intent.CATEGORY_DEFAULT);
                                i.setData(Uri.parse("package: " + getPackageName()));
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(i);
                            }
                        }
                        //requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    } else {
                        Oldversions(phoneNumber);
                    }
                }
                else{
                    Toast.makeText(basicActivity.this, "Insert a valid phone number",Toast.LENGTH_LONG).show();
                }
            }
            //crear metodo para versiones antiguas y nuevas de solicitud de permisos
            private void Oldversions(String phoneNumber){
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phoneNumber));
                if (CheckPermission(Manifest.permission.CALL_PHONE)){
                    startActivity(intentCall);
                } else {
                    Toast.makeText(basicActivity.this, "you declined the access", Toast.LENGTH_LONG).show();
                }
            }

        });

        //esto es el boton para la direccion web
        imgBtnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editTextWeb.getText().toString();
                String email = "ernestotr@gmail.com";

                if(url != null && !url.isEmpty()){
                    Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+url));
                    /*se puede usar el siguiente codigo en dos pasos separados
                    IntentWeb.setAction(Intent.ACTION_VIEW);
                    intentWeb.setData(Uri.parse("http://"+url));
                    * */
                    //contactos
                    Intent intentContact = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
                    //email rapido
                    Intent intentMailTo = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto: "+email) );
                    //email completo
                    Intent intentMail = new Intent(Intent.ACTION_VIEW, Uri.parse(email));
                    intentMail.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                    intentMail.setType("plain/text");
                    intentMail.putExtra(Intent.EXTRA_SUBJECT, "Mail´s Title");
                    intentMail.putExtra(Intent.EXTRA_TEXT,"Hi this is the text in the mail");
                    intentMail.putExtra(Intent.EXTRA_EMAIL, new String[]{"etorres@uagro.mx", "ernesto.tr@outlook.com"});
                    //como solicitar siempre con q aplicacion aejecutar el intend
                    startActivity(Intent.createChooser(intentMail,"Elige la aplicacion para cliente de correo"));
                    //Mandar a llamar sin solicitar permisos
                    Intent intentPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:555444"));
                    //startActivity(intentMail);
                }
            }
        });
        imgBtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //abrir camara
                Intent intentCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intentCamera, PICTURE_FROM_CAMARA);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case PICTURE_FROM_CAMARA:
                if(resultCode== Activity.RESULT_OK){
                    String result =data.toUri(0);
                    Toast.makeText(this, "Result:"+result ,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "there was an error, try again" ,Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode,data);
        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //caso del telefono, cuando esta el usuario a la espera del permiso
        switch(requestCode){
            case PHONE_CALL_CODE:

                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)){
                    //comprobar si ha sido aceptado o denegado la peticion de permiso
                    if(result==PackageManager.PERMISSION_GRANTED){
                        //concedió su permiso
                        String phoneNumber = editTextPhone.getText().toString();
                        Intent intentCall =new Intent(Intent.ACTION_CALL,Uri.parse("tel: "+phoneNumber));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)return;
                            startActivity(intentCall);
                    }
                    else{
                        //no concedio su permiso
                        Toast.makeText(basicActivity.this, "you declined the access", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }

    }

    //creacion de metodos
    //granted es 0 el denegado es 1 para el metodo de chequeo de permisos
    //aqui comprobamos si el permiso de llamar esta declarado en el manifest
    private boolean CheckPermission(String permission){
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
        //se puede usar == 0 o 1
        //hay q gregar el permiso en el manifest
    }

}
