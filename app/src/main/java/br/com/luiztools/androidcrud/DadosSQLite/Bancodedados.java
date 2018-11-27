package br.com.luiztools.androidcrud.DadosSQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Bancodedados extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Crud.db";
    private static final int DATABASE_VERSION = 3;
    private final String CREATE_TABLE = "CREATE TABLE Clientes " +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            " Nome TEXT NOT NULL," +
            "Endereco TEXT NOT NULL," +
            " Numero TEXT NOT NULL, " +
            "Bairro TEXT NOT NULL," +
            " Cep TEXT NOT NULL, " +
            "Cidade TEXT NOT NULL," +
            " Estado TEXT NOT NULL);";

    public Bancodedados(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
