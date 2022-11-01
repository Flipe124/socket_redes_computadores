import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor2 {
    public  final static  int PORTA = 8347;

    public static void main(String[] args){
        PrintWriter saida;
        BufferedReader entrada;
        Socket cliente = null;
        String mensagem;

        try {
            System.out.println("Escutando a porta " + PORTA);
            ServerSocket servidor = null;
            servidor = new ServerSocket(PORTA);
            cliente = servidor.accept();

            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            saida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()));
            saida.println("Conexão estabelecida com " +cliente.getInetAddress());
            saida.flush();

            // Lê várias mensagens  envia ao cliente
            do {
                // Lê memsagem do cliente
                mensagem = entrada.readLine();
                // Retorna um ACK
                System.out.println("Msg ACK");

                saida.flush();
                //Imprime a mensagem enviada pelo usuário
                System.out.println("Cliente>" + mensagem);
            } while (!mensagem.equals("Fim"));

            entrada.close();
            saida.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Encerrando conexão...");
            try {
                if (s != null){
                    cliente.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}