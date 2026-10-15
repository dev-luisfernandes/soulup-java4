package br.com.fiap.excecoes;

public class ExcecoesConexao extends Exception{

    public ExcecoesConexao() {
    }

    public ExcecoesConexao(Exception e) {
        super();
        if(e.getClass().toString().equals("class java.lang.ClassNotFoundException")){
            System.out.println("Sem comunicação com o Banco de Dados");

        } else if(e.getClass().toString().equals("class java.sql.SQLException")){
            System.out.println("Informações de usuário incorretas, acesso negado");

        }else{
            System.out.println("Falha desconhecida");
            e.printStackTrace();
        }
    }
}