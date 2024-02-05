package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.ui.activity.ConstatesActivities.CHAVE_ALUNO;
import static br.com.alura.agenda.ui.activity.ConstatesActivities.TITULO_APPBAR_EDITA_ALUNO;
import static br.com.alura.agenda.ui.activity.ConstatesActivities.TITULO_APPBAR_NOVO_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunoDAO dao = new AlunoDAO();

    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        inicializacaoDosCampos();
        //configuraBotaoSalvar();
        carregaAluno();
    }

    private void carregaAluno() {
        try{
            Intent dados = getIntent();
            if (dados.hasExtra(CHAVE_ALUNO)){
                setTitle(TITULO_APPBAR_EDITA_ALUNO);


                aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
                preencheCampos();
            }else{
                setTitle(TITULO_APPBAR_NOVO_ALUNO);

                aluno = new Aluno();
            }
        }catch(Exception ex){
            Log.e("errrouu", ex.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_aluno_menu_salvar){
            finalizaFormulario();
        }

        return super.onOptionsItemSelected(item);
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
    }

//    private void configuraBotaoSalvar() {
//        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
//        botaoSalvar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finalizaFormulario();
//            }
//        });
//    }

    private void finalizaFormulario() {
        preencheAluno();
        if (aluno.valida_id()){
            dao.editar(aluno);
        }else{
            dao.salva(aluno);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);

    }
}
