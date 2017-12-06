package com.eaerostwitter.disenoandroid4;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private View btn;
    private final String GREETER="hello from the other side";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);

        btn = (Button)findViewById(R.id.buttonMain);

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
    }


    //llamar un metodo desde xml
    //public void Mimetodo(View v){
    //    Toast.makeText(MainActivity.this,"acabas de dar click", Toast.LENGTH_LONG).show();
    //}
}
