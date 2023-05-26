package com.example.testeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.testeapp.dao.DisciplinaDAO;
import com.example.testeapp.valeuObject.DisciplinaValue;

public class DisciplinasActivity extends AppCompatActivity {
    private Button button = null;

    private EditText editText =null;
    private DisciplinaValue disciplinaSelecionada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplinas);

        button = (Button) findViewById(R.id.botao_disciplina);
        editText = (EditText)  findViewById(R.id.editText_disciplina);

        Intent intent = getIntent();

        disciplinaSelecionada = (DisciplinaValue) intent.getSerializableExtra("disciplinaSelecionada");
        if (disciplinaSelecionada != null){
            button.setText("Editar");
            editText.setText(disciplinaSelecionada.getName());

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisciplinaDAO disciplinaDAO = new DisciplinaDAO(DisciplinasActivity.this);
                DisciplinaValue disciplinaValue = new DisciplinaValue();
                disciplinaValue.setName(editText.getText().toString());
                disciplinaDAO.salvar(disciplinaValue);
                finish();
            }
        });
    }
}