package cruddesafio;

import dao.UsuarioDAO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class CrudDesafio {

    public static void main(String[] args) {
        
        // ARMAZENA O CAMINHO DO ARQUIVO EM UMA VARIAVEL
        String arquivo = "d:\\teste.txt.txt";
        
        // LE O ARQUIVO ESPECIFICADO
        // FileReader lerArquivo = null;
        // GERENCIA A LEITURA DO ARQUIVO DE UMA FORMA MAIS OTIMIZADA
        // BufferedReader gerenciarLeitura = null;
        
        try(BufferedReader gerenciarLeitura = new BufferedReader(new FileReader(arquivo))) {
            // CRIA UMA VARIAVEL PARA ARMAZENAR A ACESSAR DE LINHA A LINHA OS DADOS DO ARQUIVO readLine()
            String linha = gerenciarLeitura.readLine(); // <- ESSA PRIMEIRA LINHA E REFERENTE AO CABECALHO
            linha = gerenciarLeitura.readLine(); // COMECA A ARMAZENAR REALMENTE OS DADOS
            
            // ENQUANTO NAO HOUVER LINHAS VAZIA, CONTINUE LENTO
            while(linha != null) {
                
                // NAVEGA NA STRING ARMAZENANDO OS DADOS NO VETOR LEVANDO COMO REFERENCIA AS VIRGULAS
                String[] vetor = linha.split(";");
                String nome = vetor[0];
                int idade = Integer.parseInt(vetor[1]);
                String sexo = vetor[2];
                
                /* POST */
                Usuario usuario = new Usuario();
                usuario.setNome(nome);
                usuario.setIdade(idade);
                usuario.setSexo(sexo);
                
                /* RECEBE COMO RETORNO UM VALOR BOOLEANO DO METODO POST DE ACORDO COM O RESULTADO DO METODO */
                boolean status = UsuarioDAO.inserirUsuario(usuario);

                if(status) {
                    System.out.println("Usuario cadastrado com sucesso!");

                }else {
                    System.out.println("Ocorreu um erro ao tentar cadastrar o usuario, tente novamente!");

                }
                
                System.out.println(linha);
                // ARMAZENA A PROXIMA LINHA NA VARIAVEL PARA IMPRIMIR NA TELA
                linha = gerenciarLeitura.readLine();
            
            }
            
            /* ACESSA O METODO GET QUE VAI ATE O BANCO DE DADOS PERCORRE ELE POR COMPLETO E TRAZ TODOS OS DADOS ARMAZENANDO-OS DENTRO DO Arraylist<> POR MEIO DO OBJETO Usuario  DESSA FORMA TRAZENDO OS DADOS MODELADOS DE ACORDO COM A CLASSE */
            System.out.println("USUARIOS CADASTRADOS |=======================");
            ArrayList<Usuario> usuarios = UsuarioDAO.buscarUsuarios();
            
            /* SE CRIA UMA FOR PARA PERCORRER O ArrayList<> E INFORMAR NA TELA TODOS OS DADOS CONTIDOS DENTRO DO Array */
            for(Usuario usuario : usuarios) {
                System.out.println("ID: "+ usuario.getId());
                System.out.println("Nome "+ usuario.getNome());
                System.out.println("Idade: "+ usuario.getIdade());
                System.out.println("Sexo: "+ usuario.getSexo());
                System.out.println("");

            }
        
        /* TRAZ UM ERRO DE LEITURA DE ARQUIVO */
        }catch(IOException erro) {
            /* PERCORRE O ERRO E TRAZ NA TELA SEU PORCURSO COMPLETO, INFORMANDO ONDE EXATAMENTE ESTA O ERRO */
            erro.printStackTrace();
            
            /* IMPRIME NA TELA UMA MENSAGEM DE ERRO */
            System.out.println(erro.getMessage());
        
        }
        
    }
    
}
