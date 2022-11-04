import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente3 {
    public static final int PORTA = 8747;

    // Deixamos fora poque várias threades vão utilizar o socket
    public static Socket socket = null;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", PORTA);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter saida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            Scanner scanner = new Scanner(System.in);
            String mensagem = "mensagem";
            String nomeCliente = null;

            // CRIA uma thread para o cliente
            ClientThread clientThread = new ClientThread(socket);

            // INICIAR A THREAD
            clientThread.start();

            // LER ENTRADAS DOS USUÁRIOS ENQUANTO ESTÁ CONECTADO
            while (){

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
