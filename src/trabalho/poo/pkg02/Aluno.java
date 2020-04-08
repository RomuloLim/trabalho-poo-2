package trabalho.poo.pkg02;
import java.util.ArrayList;

public class Aluno {
    private String nome;
    private ArrayList <String> disciplinas;
    private ArrayList <String> gabaritos;

    public Aluno(String nome) {
        this.nome = nome;
    }

    
    
    
    public String getNome() {
        return nome;
    }

    public ArrayList<String> getDisciplinas() {
        return disciplinas;
    }

    public ArrayList<String> getGabaritos() {
        return gabaritos;
    }
    
    }
