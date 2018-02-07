package com.eaerostwitter.disenoandroid4;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public int pix=10;
    private View btn;
    private View btn2;
    private final String GREETER="hello from the other side";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);

        //forzar cambio del icono
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_myicon);

        btn = (Button)findViewById(R.id.buttonMain);
        btn2=(Button)findViewById(R.id.btn_Mango);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"click desde codigo", Toast.LENGTH_LONG).show();
                //acceder al segundo activity y mandarle un string
                Intent intend  = new Intent(MainActivity.this, SecondActivity.class);
                //mandamos in inend con el id greeter y la variable GREETER
                intend.putExtra("greeter", GREETER);
                startActivity(intend);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend = new Intent(MainActivity.this, Drawer.class);
                //intend.putExtra("idpix",pix);
                startActivity(intend);
            }
        });
    }


    //llamar un metodo desde xml
    //public void Mimetodo(View v){
    //    Toast.makeText(MainActivity.this,"acabas de dar click", Toast.LENGTH_LONG).show();
    //}
}
