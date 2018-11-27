package br.com.luiztools.androidcrud.Modelo;

import java.io.Serializable;


public class Cliente implements Serializable {

    private int id;
    private String nome;
    private String endereco;
    private String numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;

    public Cliente(int id, String nome,String endereco,String numero, String bairro,String cep,String cidade,String estado){
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    public int getId(){ return this.id; }
    public String getNome(){ return this.nome; }
    public String getEndereco (){ return this.endereco; }
    public String getNumero(){ return this.numero; }
    public String getBairro(){return this.bairro;}
    public String getCep(){return this.cep; }
    public String getCidade(){return this.cidade; }
    public String getEstado(){return this.estado;}

    @Override
    public boolean equals(Object o){
        return this.id == ((Cliente)o).id;
    }

    @Override
    public int hashCode(){
        return this.id;
    }
}
