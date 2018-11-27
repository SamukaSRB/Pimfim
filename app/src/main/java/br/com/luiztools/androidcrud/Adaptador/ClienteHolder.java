package br.com.luiztools.androidcrud.Adaptador;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.luiztools.androidcrud.R;

public class ClienteHolder extends RecyclerView.ViewHolder {

    public TextView nomeCliente,enderecoCliente,numeroCliente,bairroCliente,cepCliente,cidadeCliente,estadoCliente;
    public ImageButton btnEditar;
    public ImageButton btnExcluir;

    public ClienteHolder(View itemView) {
        super(itemView);
        nomeCliente = (TextView) itemView.findViewById(R.id.nomeCliente);
        enderecoCliente = (TextView) itemView.findViewById(R.id.txtEndereco);
        numeroCliente = (TextView) itemView.findViewById(R.id.txtNumero);
        bairroCliente = (TextView) itemView.findViewById(R.id.txtBairro);
        cepCliente = (TextView) itemView.findViewById(R.id.txtCep);
        cidadeCliente = (TextView) itemView.findViewById(R.id.txtCep);
        estadoCliente = (TextView) itemView.findViewById(R.id.txtCidade);




        btnEditar = (ImageButton) itemView.findViewById(R.id.btnEdit);
        btnExcluir = (ImageButton) itemView.findViewById(R.id.btnDelete);
    }
}