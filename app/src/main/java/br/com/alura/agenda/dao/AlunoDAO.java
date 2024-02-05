package br.com.alura.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {

    private static int contador_id = 1;
    private final static List<Aluno> alunos = new ArrayList<>();

    public void salva(Aluno aluno) {
        aluno.setId(contador_id);
        alunos.add(aluno);
        aumentaId();
    }

    private static void aumentaId() {
        contador_id++;
    }

    public void editar(Aluno aluno){
        Aluno aluno_encontrado = buscaAlunoPeloId(aluno);
        if (aluno_encontrado != null){
            int posicao_aluno = alunos.indexOf(aluno_encontrado);
            alunos.set(posicao_aluno, aluno);
        }
    }

    private static Aluno buscaAlunoPeloId(Aluno aluno) {
        for (Aluno a: alunos){
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno aluno) {
        Aluno alunoDevolvido = buscaAlunoPeloId(aluno);
        if (alunoDevolvido != null){
            alunos.remove(alunoDevolvido);
        }
    }
}
