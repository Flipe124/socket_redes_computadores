import java.io.*;
import java.net.Socket;

public class Cliente {
    // Porta de comunicação
    public  static  final int PORTA = 8347;

    public static void main(String[] args){
        Socket s = null;
        BufferedReader entrada;// Armazena o que vem do servidor
        PrintWriter saida;// Armazena o que será enviado ao servidor

        try {
            // Cria o socket no IP localhost (mesma máquina) e porta pré-definida
            // Estabelecemos a tupla IP:porta como localhost:8347
            s = new Socket("localhost", PORTA);

            // Criar streams de entrada e saída
            // Configurando o BufferReader para os dados vindo pelo socket
            // Estabelecido
            entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // Configurando o PrintReader para armazenar os dados que enviaremos
            // ao servidor via socket
            saida = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));

            // Informa o status da conexão
            System.out.println("Conectado ao servidor " + s.getInetAddress() + ":" + s.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
