import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private Socket socket;
    private ArrayList<ServerThread> listaThreads;
    private PrintWriter saida;
    private String mensagem = "";

    public ServerThread(Socket socket, ArrayList<ServerThread> listaThreads){
        this.socket = socket;
        this.listaThreads = listaThreads;
    }

    // DEFININMOS O COMPORTAMENTO DA THREAD

    @Override
    public void run() {
        // PREPARA A LEITURA DO QUE VEM DO CLIENTE
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // PREPARA O ENVIO DO QUE VAI PARA O CLIENTE
            saida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        // LÊ AS MESAGENS DOS CLIENTES
            while (true){
                mensagem = entrada.readLine();

                if ((mensagem == null) || (mensagem.equals("Fim"))){
                    break;
                }

                // EXIBE A MENSAGEM PARA TODOS OS CLIENTES CONECTADOS
                for (ServerThread st : listaThreads){
                    st.saida.println(mensagem);
                }
                System.out.println("Servidor recebeu a mensagem " + mensagem);
            }

            entrada.close();
            saida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
