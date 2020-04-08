package trabalho.poo.pkg02;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
            System.out.println("3. Criar aluno");
            System.out.println("4. Ver alunos cadastrados");
            System.out.println("5. Adicionar aluno em uma disciplina");
            System.out.println("6. Criar gabarito de uma disciplina");
            System.out.println("7. Gerar resultado de uma disciplina");
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
                    criarAluno();
                    break;
                case 4:
                    verAlunos();
                    break;
                case 5:
                    adcAluno();
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
     
     disciplinas.add(new Disciplina(str));
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
   
   static void criarAluno(){
       System.out.println("Digite o nome do aluno:");
       teclado.nextLine(); //LIMPAR O BUFFER DO TECLADO
       String str = teclado.nextLine();
       alunos.add(new Aluno(str));
   }
   
   static void verAlunos(){
       for(int i = 0; i<disciplinas.size(); i++){
           System.out.println(alunos.get(i).getNome()+"\n");
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
       
       System.out.println("Adicionar um aluno já cadastrado? (1 para sim | 0 para não)");
       int n = teclado.nextInt();
       if(n == 1){
           System.out.println("Escolha o aluno (pelo seu respectivo número)");
           for(int i = 0; i<alunos.size(); i++){
               System.out.println(i+"."+alunos.get(i).getNome());
           }
           int escolhaAluno = teclado.nextInt();
           
           System.out.println("Digite o gabarito do aluno:");
           String gabarito = teclado.next();
           
           try {
               FileWriter linha = new FileWriter("disciplinas/"+disciplinas.get(escolhaDisciplina).getNomeDisciplina()+".txt", true);
               linha.write(gabarito+"\t"+alunos.get(escolhaAluno).getNome()+"\n");
               linha.close();
           } catch (IOException ex) {
               Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }
   }
}
