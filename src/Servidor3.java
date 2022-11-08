import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Servidor3 {
    public static final int PORTA = 8747;
    public static Socket cliente;

    public static void main(String[] args) {
        // CRIAMOS UMA LISTA DE THREADS ATIVAS
        ArrayList<ServerThread> listaThreads = new ArrayList<ServerThread>();
        try {
            ServerSocket servidor = new ServerSocket(PORTA);
            // DEIXAMOS O SERVIDOR EM LOOP, PARA SEMPRE ACEITAR NOVAS CONEXÕES
            while (true){
                System.out.println("Ouvindo a porta " + PORTA + "...");
                cliente = servidor.accept();
                // ATRIBUI A CONEXÃO A UMA THREAD
                ServerThread thread = new ServerThread(cliente, listaThreads);
                // ADICIONANDO A NOVA THREAD A LISTA
                listaThreads.add(thread);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (cliente != null) {
                    cliente.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
