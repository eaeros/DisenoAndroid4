package com.eaerostwitter.disenoandroid4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Controls_Settings extends AppCompatActivity {
    private EditText varTxtPix;
    private View varBtnPix;
    private int pix;
    private String finalnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls__settings);
        varTxtPix = (EditText) findViewById(R.id.editTxtPix);
        varBtnPix = (Button) findViewById(R.id.btnPix);

        /*inicia codigo de bundle
        Bundle bundle =getIntent().getExtras();
        if (bundle != null){
            int greeter = bundle.getInt("idpix2");
            Toast.makeText(Controls_Settings.this,"pix2 vale"+String.valueOf(greeter), Toast.LENGTH_SHORT).show();
            //txt.setText(greeter);

        } else {
            Toast.makeText(Controls_Settings.this,"Esta vacio", Toast.LENGTH_SHORT).show();
        }
        //termina codigo de bundle*/
        varBtnPix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pixNumber = varTxtPix.getText().toString();
                if (pixNumber != null && !pixNumber.isEmpty()) {
                    //pix =Integer.getInteger(pixNumber);
                    Toast.makeText(Controls_Settings.this,"valor enviado: "+pixNumber, Toast.LENGTH_SHORT).show();
                    Intent intend  = new Intent(Controls_Settings.this, Drawer.class);
                    //mandamos in inend con el id greeter y la variable GREETER
                    intend.putExtra("finalNumid", pixNumber);
                    startActivity(intend);
                }
            }
        });
    }
}
