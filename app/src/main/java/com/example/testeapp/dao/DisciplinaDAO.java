package com.example.testeapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.testeapp.valeuObject.DisciplinaValue;

import java.util.LinkedList;
import java.util.List;

public class DisciplinaDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "BancoDisciplinas";
    private static final int VERSAO = 1;

    public DisciplinaDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String ddl = "CREATE TABLE Disciplina (id INTEGER PRIMARY KEY,"
                + " nome TEXT UNIQUE NOT NULL);";


        db.execSQL(ddl);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int velha, int nova) {
        String ddl = "DROP TABLE IF EXISTS Disciplina";
        db.execSQL(ddl);
        onCreate(db);
    }

    public void salvar(DisciplinaValue disciplinaValue) {
        ContentValues values = new ContentValues();
        values.put("nome", disciplinaValue.getName());

        getWritableDatabase().insert("Disciplina", null, values);

    }

    public List getLista() {
        List<DisciplinaValue> lista_disciplinas = new LinkedList<DisciplinaValue>();

        String query = "SELECT * FROM " + "Disciplina order by nome";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                DisciplinaValue disciplina = null;
                disciplina = new DisciplinaValue();
                disciplina.setId(Long.parseLong(cursor.getString(0)));
                disciplina.setName(cursor.getString(1));
                lista_disciplinas.add(disciplina);
            } while (cursor.moveToNext());
        }
        return lista_disciplinas;
    }
}





