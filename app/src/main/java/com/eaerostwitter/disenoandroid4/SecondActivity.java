package com.eaerostwitter.disenoandroid4;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    //rescatar lo q se manda del primer textview
    private TextView txt;
    private Button goBtnFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //Activar flecha ir atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        goBtnFragment= (Button) findViewById(R.id.btnFragment);
        txt = (TextView) findViewById(R.id.textViewMain);
        //tomar los datos del intend, se rescatan desde un bundle
        Bundle bundle =getIntent().getExtras();
        if (bundle != null){
            String greeter = bundle.getString("greeter");
            Toast.makeText(SecondActivity.this,greeter, Toast.LENGTH_LONG).show();
            txt.setText(greeter);
        } else {
            Toast.makeText(SecondActivity.this,"Esta vacio", Toast.LENGTH_LONG).show();
        }
        goBtnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend  = new Intent(SecondActivity.this, basicActivity.class);
                startActivity(intend);
            }
        });




    }
}
