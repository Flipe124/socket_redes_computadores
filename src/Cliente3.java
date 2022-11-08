import java.io.*;
import java.net.Socket;
import java.sql.SQLOutput;
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
            // CLIENTE SE DESCONECTA AO DIGTAR "FIM"!
            while (!mensagem.equals("Fim")){
                // LEMOS O NOME DO CLIENTE (CASO AINDA NÃO ESTEJA SETADO)
                // E DEPOIS LEMOS AS MENSAGENS QUE ELE QUER ENVIAR
                if (nomeCliente == null){
                    // LER O NOME DO CLIENTE
                    System.out.println("Qual seu nome?");
                    mensagem = scanner.nextLine();
                    nomeCliente = mensagem;
                    saida.println(mensagem);
                } else {
                    // LER A MENSAGEM QUE O CLIENTE QUER ENVIAR
                    mensagem = scanner.nextLine();
                    saida.println(nomeCliente + "> " + mensagem);

                    if (mensagem.equals("Fim")){
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null){
                    socket.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
