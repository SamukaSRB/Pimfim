package br.com.luiztools.androidcrud.Adaptador;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.luiztools.androidcrud.DadosSQLite.Bancodedados;

public class DbGateway {

    private static DbGateway gw;
    private SQLiteDatabase db;

    private DbGateway(Context ctx){
        Bancodedados helper = new Bancodedados(ctx);
        db = helper.getWritableDatabase();
    }

    public static DbGateway getInstance(Context ctx){
        if(gw == null)
            gw = new DbGateway(ctx);
        return gw;
    }

    public SQLiteDatabase getDatabase(){
        return this.db;
    }
}
