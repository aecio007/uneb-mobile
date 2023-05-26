package com.example.testeapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText = null;
    private SharedPreferences myPrefs=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button) findViewById(R.id.button2);

        editText = (EditText) findViewById((R.id.editText));


        myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);

        String name = myPrefs.getString("nome","");
        
        if (name !=null){

            editText.setText(name);

        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "BotaoClicado");
                //editText.setText("Aecio de Oliveira");
                SharedPreferences.Editor ePrefs = myPrefs.edit();
                ePrefs.putString("nome",MainActivity.this.editText.getText().toString());
                ePrefs.commit();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_ola_mundo, menu);
        return true;
    }

}
