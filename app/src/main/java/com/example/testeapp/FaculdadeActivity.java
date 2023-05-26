package com.example.testeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.testeapp.dao.DisciplinaDAO;
import com.example.testeapp.valeuObject.DisciplinaValue;

import java.util.ArrayList;

public class FaculdadeActivity extends AppCompatActivity {

    private DisciplinaValue disciplinaValue = null;
    private ArrayAdapter <String> adapter = null;
    ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculdade);

        DisciplinaDAO disciplinaDAO = new DisciplinaDAO(this);

        DisciplinaValue disciplinaValue1 = new DisciplinaValue();
        disciplinaValue1.setName("Programação I");


        DisciplinaValue disciplinaValue2 = new DisciplinaValue();
        disciplinaValue2.setName("Redes II");



        //disciplinaDAO.salvar(disciplinaValue1);
        //disciplinaDAO.salvar(disciplinaValue2);

        Log.i("Lista Discplinas",disciplinaDAO.getLista().toString());


        //String[] disciplinas = {"Programacao", "Redes", "Mobile"," IHC"};
        int layout = android.R.layout.simple_list_item_1;

        adapter = new ArrayAdapter<String>(this,layout,disciplinaDAO.getLista());
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view,
                                    int posicao, long id) {
                Toast.makeText(FaculdadeActivity.this, "Clicou " + posicao,
                        Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapter,
                                           View view, int posicao, long id) {
                Toast.makeText( FaculdadeActivity.this,
                        adapter.getItemAtPosition(posicao).toString(),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FaculdadeActivity.this, DisciplinasActivity.class);
                disciplinaValue = new DisciplinaValue();
                disciplinaValue.setName(adapter.getItemAtPosition(posicao).toString());
                intent.putExtra("disciplinaSelecionada", disciplinaValue);

                startActivity(intent);

                return false;
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_faculdade, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_new) {
            Intent intent = new Intent(this, DisciplinasActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume(){
        super.onResume();
        DisciplinaDAO dao = new DisciplinaDAO(this);

        ArrayList<String> disciplinas = new ArrayList(dao.getLista());

        dao.close();

        int layout = android.R.layout.simple_list_item_1;
        adapter = new ArrayAdapter<String>(this,layout, disciplinas);
        listView.setAdapter(adapter);



    }

}