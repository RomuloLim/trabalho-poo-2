package trabalho.poo.pkg02;
import java.util.ArrayList;

public class Aluno {
    private String nome;
    private String disciplina;
    private String gabarito;  

    public Aluno(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Aluno{" + "nome=" + nome + ", disciplina=" + disciplina + ", gabarito=" + gabarito + '}';
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplinas) {
        this.disciplina = disciplinas;
    }

    public String getGabarito() {
        return gabarito;
    }

    public void setGabarito(String gabarito) {
        this.gabarito = gabarito;
    }

    
}
