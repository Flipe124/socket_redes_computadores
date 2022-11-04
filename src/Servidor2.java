import java.net.*;
import java.io.*;

public class Servidor2 {
    public final static int PORTA = 8347;

    public static void main(String[] args) {
        PrintWriter saida;
        BufferedReader entrada;
        Socket cliente = null;
        String mensagem;

        try {
            // Estabelece a comunicação
            System.out.println("Ouvindo a porta "+PORTA+"...");
            ServerSocket servidor = new ServerSocket(PORTA);
            cliente = servidor.accept();

            // Prepara streams de entrada e saída
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            saida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()));
            saida.println("Conexão estabelecida com "+cliente.getInetAddress());
            saida.flush();

            // Lê várias mensagens e retorna ao cliente
            do{
                // Lê uma mensagem do cliente
                mensagem = entrada.readLine();
                // Retorna um ACK
                saida.println("Recebida sua mensagem "+mensagem);
                saida.flush();
                System.out.println("Cliente> "+mensagem);
            }while(!mensagem.equals("Fim"));

            entrada.close();
            saida.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Encerrando conexão...");
            try {
                if (cliente != null) {
                    cliente.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Conexão encerrada!");
        }

    }
}