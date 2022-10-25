import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public final static int PORTA = 8347;

    public static void main(String[] args){
        PrintWriter saida;
        BufferedReader entrada;
        Socket cliente = null;

        // Estabelece a comunicação
        try {
            System.out.println("Ouvindo a entrada " + PORTA + "...");

            ServerSocket servidor = new ServerSocket(PORTA);

            cliente = servidor.accept();

            System.out.println("Conexão estabelecida com " + cliente.getInetAddress());

            // Prepara as streams de entrada e saída
            // Cria um bafferReader baseado na informação que chegou
            // do cliente cliente.getInetAddress()
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            saida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()));

            // Lê uma mensagem do cliente
            String mensagem = entrada.readLine();
            System.out.println("Cliente>" + mensagem);

            // Responde ao cliente
            saida.println("Você está conectado ao servidor mais top do mundo ;)");

            // Envia a mensagem
            saida.flush();

            // Encerrar as variáveis (liberar espaço)
            entrada.close();
            saida.close();


        } catch (IOException e){
            e.printStackTrace();
        } finally {
            // Encerra a conucação (fecha o socket)
            System.out.println("Encerrando a conexão...");
            // Só encerra a conexão caso java um cliente conectado
                try {
                    if (cliente != null) {
                        cliente.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Conexão encerrada com sucesso.");
        }
    }
}
