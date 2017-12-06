package com.eaerostwitter.disenoandroid4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    //rescatar lo q se manda del primer textview
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

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
    }
}
