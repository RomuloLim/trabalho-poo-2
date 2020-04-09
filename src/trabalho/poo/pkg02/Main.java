package trabalho.poo.pkg02;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {
        int i = -1;
        do{
            System.out.println("O que quer fazer?");
            System.out.println("1. Criar disciplina");
            System.out.println("2. Ver disciplinas criadas");
            System.out.println("3. Adicionar aluno em uma disciplina");
            System.out.println("4. Criar gabarito de uma disciplina");
            System.out.println("5. Gerar resultado de uma disciplina");
            System.out.println("0. Sair");
            i = teclado.nextInt();
            switch(i){
                case 1:
                    criarDisciplina();
                    break;
                case 2:
                    verDisciplinas();
                    break;
                case 3:
                    adcAluno();
                    break;
                case 4:
                    adcGabarito();
                    break;
                case 5:
                    gerarResultado();
                    break;
                case 8:
                    verAlunoTest();
                    break;
            }
        }while(i != 0);
    }
    
     
   static ArrayList <Disciplina> disciplinas = new ArrayList<Disciplina>();
   static ArrayList <Aluno> alunos = new ArrayList<Aluno>();
   
   static void criarDisciplina(){
     System.out.println("Qual o nome da disciplina?");
     teclado.nextLine();//LIMPA O BUFER DO TECLADO
     String str = teclado.nextLine();
     Disciplina d = new Disciplina(str);
     disciplinas.add(d);
     File file = new File("disciplinas/"+str+".txt");
     
     try {
        file.createNewFile();
        System.out.println("Disciplina criada com sucesso!\n");
    } catch (IOException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
   
   static void verDisciplinas(){
       for(int i = 0; i<disciplinas.size(); i++){
           System.out.println(disciplinas.get(i).getNomeDisciplina()+"\n");
       }
   }

   static void adcAluno(){
       System.out.println("Escolha uma disciplina (pelo número)");
              for(int i = 0; i<disciplinas.size(); i++){
           System.out.println("["+i+"] "+disciplinas.get(i).getNomeDisciplina()+"\n");
                }
              
       int escolhaDisciplina = teclado.nextInt();
       
       /*
       FALTA CRIAR
       -> OPC 0 (ALUNO JA CADASTRADO?)
       -> NÃO EXIBIR O ALUNO SE ELE JÁ TIVER SIDO ESCOLHIDO
       */
       
       int i = 0;
      System.out.println("Digite o nome do aluno:");
       teclado.nextLine(); //LIMPAR O BUFFER DO TECLADO
       String nomeAluno = teclado.nextLine();
       alunos.add(new Aluno(nomeAluno));
           
           System.out.println("Digite o gabarito do aluno:");
           String gabarito = teclado.next();
           alunos.get(alunos.size()-1).setDisciplina(disciplinas.get(escolhaDisciplina).getNomeDisciplina());
           alunos.get(alunos.size()-1).setGabarito(gabarito);

           try {
               FileWriter linha = new FileWriter("disciplinas/"+disciplinas.get(escolhaDisciplina).getNomeDisciplina()+".txt", true);
               linha.write(gabarito+"\t"+alunos.get(alunos.size()-1).getNome()+"\n");
               linha.close();
           } catch (IOException ex) {
               Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
           }
   }
   
   static void adcGabarito(){
       int i = 0;
       System.out.println("Selecione a disciplina (pelo número)");
       for (Disciplina d : disciplinas) {
           System.out.println("["+i+"] "+d.getNomeDisciplina());
       }
       int escolhaDisciplina = teclado.nextInt();
       
       System.out.println("Digite o Gabarito oficial");
       String gabaritoOficial = teclado.next();
       
    try {
        FileWriter linhaG = new FileWriter("gabaritos/"+disciplinas.get(escolhaDisciplina).getNomeDisciplina()+".txt");
        linhaG.write(gabaritoOficial);
        linhaG.close();
    } catch (IOException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
   }
   
   static void gerarResultado(){
       int i = 0;
       System.out.println("Selecione a disciplina (pelo número)");
       for (Disciplina d : disciplinas) {
           System.out.println("["+i+"] "+d.getNomeDisciplina());
       }
       int escolhaDisciplina = teclado.nextInt();
       System.out.println("O gabarito oficial da prova está em gabaritos/"+disciplinas.get(escolhaDisciplina).getNomeDisciplina()+".txt");
       
    try {
        FileWriter ordemAlfabetica = new FileWriter("resultados/"+disciplinas.get(escolhaDisciplina).getNomeDisciplina()+"(Ordem alfabetica).txt", true);
        FileReader gOfc = new FileReader("gabaritos/"+disciplinas.get(escolhaDisciplina).getNomeDisciplina()+".txt");
        BufferedReader lerGOfc = new BufferedReader(gOfc);
        
        ArrayList<String> alunosOrdemAlfabetica = new ArrayList<>();
        String linha = "";
        linha = lerGOfc.readLine(); //GABARITO OFICIAL
        gOfc.close();
        
        FileReader gDisc = new FileReader("disciplinas/"+disciplinas.get(escolhaDisciplina).getNomeDisciplina()+".txt");
        BufferedReader lerGDisc = new BufferedReader(gDisc);
        String linhaDisc;
        int contador = 0;
        int contador2 = 0;
        linhaDisc = lerGDisc.readLine();
        while(linhaDisc != null){
            int nota = 0;
            char linhaArray[] = linha.toCharArray();
            char linhaDiscArray[] =  linhaDisc.toCharArray();
            System.out.println(linhaArray.length);
            for(int j = 0; j < linhaArray.length; j++){
                if(linhaDiscArray[j] == linhaArray[j]){
                nota ++;
                    System.out.println(nota);
                    System.out.println(linhaArray[j]);
                    System.out.println(linhaDiscArray[j]);
                }
            }
            if (alunos.get(contador).getDisciplina() == disciplinas.get(escolhaDisciplina).getNomeDisciplina()) {
            alunos.get(contador).setNota(nota);
            alunosOrdemAlfabetica.add(alunos.get(contador).getNome()+": "+alunos.get(contador).getNota());
            }
            Collections.sort(alunosOrdemAlfabetica);
            contador++;
            linhaDisc = lerGDisc.readLine();
        }
        
        for(int j = 0; j < alunosOrdemAlfabetica.size(); j++){
        ordemAlfabetica.write(alunosOrdemAlfabetica.get(j)+"\n");
        }
        ordemAlfabetica.close();
        
        
    } catch (IOException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
   }
   
    static void verAlunoTest(){
        System.out.println(alunos.get(0).getNome());
        System.out.println(alunos.get(0).getDisciplina());
        System.out.println(alunos.get(0).getGabarito());
        System.out.println(alunos.get(0).getNota());
   }
}
