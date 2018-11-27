package br.com.luiztools.androidcrud;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Spinner;

import br.com.luiztools.androidcrud.Adaptador.ClienteAdapter;
import br.com.luiztools.androidcrud.Dao.ClienteDAO;
import br.com.luiztools.androidcrud.Modelo.Cliente;


public class MainActivity extends AppCompatActivity {

    Cliente clienteEditado = null;

    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //verifica se começou agora ou se veio de uma edição
        Intent intent = getIntent();
        if(intent.hasExtra("cliente")) {
            findViewById(R.id.includemain).setVisibility(View.INVISIBLE);
            findViewById(R.id.includecadastro).setVisibility(View.VISIBLE);
            findViewById(R.id.fab).setVisibility(View.INVISIBLE);
            clienteEditado = (Cliente) intent.getSerializableExtra("cliente");

            EditText txtNome = (EditText) findViewById(R.id.txtNome);
            EditText txtEndereco = (EditText) findViewById(R.id.txtEndereco);
            EditText txtNumero = (EditText) findViewById(R.id.txtNumero);
            EditText txtBairro = (EditText) findViewById(R.id.txtBairro);
            EditText txtCep = (EditText) findViewById(R.id.txtCep);
            EditText txtCidade = (EditText) findViewById(R.id.txtCidade);
            EditText txtEstado = (EditText) findViewById(R.id.txtUF);


            txtNome.setText(clienteEditado.getNome());
            txtEndereco.setText(clienteEditado.getEndereco());
            txtNumero.setText(clienteEditado.getNumero());
            txtBairro.setText(clienteEditado.getBairro());
            txtCep.setText(clienteEditado.getCep());
            txtCidade.setText(clienteEditado.getCidade());
            txtEstado.setText(clienteEditado.getEstado());

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.includemain).setVisibility(View.INVISIBLE);
                findViewById(R.id.includecadastro).setVisibility(View.VISIBLE);
                findViewById(R.id.fab).setVisibility(View.INVISIBLE);
            }
        });

        Button btnCancelar = (Button)findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.includemain).setVisibility(View.VISIBLE);
                findViewById(R.id.includecadastro).setVisibility(View.INVISIBLE);
                findViewById(R.id.fab).setVisibility(View.VISIBLE);
            }
        });

        Button btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //carregando os campos
                EditText txtNome = (EditText)findViewById(R.id.txtNome);
                EditText txtEndereco = (EditText)findViewById(R.id.txtEndereco);
                EditText txtNumero = (EditText) findViewById(R.id.txtNumero);
                EditText txtBairro = (EditText) findViewById(R.id.txtBairro);
                EditText txtCep = (EditText) findViewById(R.id.txtCep);
                EditText txtCidade = (EditText) findViewById(R.id.txtCidade);
                EditText txtEstado = (EditText) findViewById(R.id.txtUF);

                //pegando os valores
                String nome = txtNome.getText().toString();
                String endereco = txtEndereco.getText().toString();
                String numero = txtNumero.getText().toString();
                String bairro = txtBairro.getText().toString();
                String cep= txtCep.getText().toString();
                String cidade = txtCidade.getText().toString();
                String estado = txtEstado.getText().toString();





                //salvando os dados
                ClienteDAO dao = new ClienteDAO(getBaseContext());
                boolean sucesso;
                if(clienteEditado != null)
                    sucesso = dao.salvar(clienteEditado.getId(), nome,endereco, numero,bairro,cep,cidade,estado);
                else
                    sucesso = dao.salvar(nome,endereco, numero,bairro, cep,cidade,estado);

                if(sucesso) {
                    Cliente cliente = dao.retornarUltimo();
                    if(clienteEditado != null)
                        adapter.atualizarCliente(cliente);
                    else
                        adapter.adicionarCliente(cliente);

                    //limpa os campos
                    clienteEditado = null;
                    txtNome.setText("");
                    txtEndereco.setText("");
                    txtNumero.setText("");
                    txtBairro.setText("");
                    txtCep.setText("");
                    txtCidade.setText("");
                    txtEstado.setText("");

                    Snackbar.make(view, "Salvou!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    findViewById(R.id.includemain).setVisibility(View.VISIBLE);
                    findViewById(R.id.includecadastro).setVisibility(View.INVISIBLE);
                    findViewById(R.id.fab).setVisibility(View.VISIBLE);
                }else{
                    Snackbar.make(view, "Erro ao salvar, consulte os logs!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        configurarRecycler();
    }

    RecyclerView recyclerView;
    private ClienteAdapter adapter;

    private void configurarRecycler() {

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        ClienteDAO dao = new ClienteDAO(this);
        adapter = new ClienteAdapter(dao.retornarTodos());
        recyclerView.setAdapter(adapter);

        // Configurando um separador entre linhas, para uma melhor visualização.
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
