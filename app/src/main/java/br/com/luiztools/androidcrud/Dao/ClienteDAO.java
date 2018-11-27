package br.com.luiztools.androidcrud.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.luiztools.androidcrud.Adaptador.DbGateway;
import br.com.luiztools.androidcrud.Modelo.Cliente;

public class ClienteDAO {

    private final String TABLE_CLIENTES = "Clientes";
    private DbGateway gw;

    public ClienteDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

    public List<Cliente> retornarTodos(){
        List<Cliente> clientes = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Clientes", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            String endereco = cursor.getString(cursor.getColumnIndex("Endereco"));
            String numero = cursor.getString(cursor.getColumnIndex("Numero"));
            String bairro = cursor.getString(cursor.getColumnIndex("Bairro"));
            String cep = cursor.getString(cursor.getColumnIndex("Cep"));
            String cidade = cursor.getString(cursor.getColumnIndex("Cidade"));
            String estado = cursor.getString(cursor.getColumnIndex("Estado"));

            clientes.add(new Cliente(id, nome,endereco,numero,bairro,cep,cidade,estado ));
        }
        cursor.close();
        return clientes;
    }

    public Cliente retornarUltimo(){
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Clientes ORDER BY ID DESC", null);
        if(cursor.moveToFirst()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            String endereco = cursor.getString(cursor.getColumnIndex("Endereco"));
            String numero = cursor.getString(cursor.getColumnIndex("Numero"));
            String bairro = cursor.getString(cursor.getColumnIndex("Bairro"));
            String cep = cursor.getString(cursor.getColumnIndex("Cep"));
            String cidade = cursor.getString(cursor.getColumnIndex("Cidade"));
            String estado = cursor.getString(cursor.getColumnIndex("Estado"));
            cursor.close();
            return new Cliente(id, nome,endereco,numero,bairro,cep,cidade,estado );
        }

        return null;
    }

    public boolean salvar(String nome,String endereco, String numero, String bairro,String cep, String cidade,String estado){
        return salvar(0, nome,endereco, numero, bairro, cep,cidade,estado);
    }

    public boolean salvar(int id, String nome,String endereco, String numero, String bairro, String cep,String cidade,String estado){
        ContentValues cv = new ContentValues();
        cv.put("Nome", nome);
        cv.put("Endereco",endereco);
        cv.put("Numero", numero);
        cv.put("Bairro", bairro);
        cv.put("Cep", cep);
        cv.put("Cidade",cidade);
        cv.put("Estado", estado);

        if(id > 0)
            return gw.getDatabase().update(TABLE_CLIENTES, cv, "ID=?", new String[]{ id + "" }) > 0;
        else
            return gw.getDatabase().insert(TABLE_CLIENTES, null, cv) > 0;
    }

    public boolean excluir(int id){
        return gw.getDatabase().delete(TABLE_CLIENTES, "ID=?", new String[]{ id + "" }) > 0;
    }
}
