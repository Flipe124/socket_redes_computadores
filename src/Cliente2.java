import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente2 {
    public static final int PORTA = 8347;

    public static void main(String[] args) {
        Socket s = null;
        BufferedReader entrada; // Armazena o que vem do servidor
        PrintWriter saida; // Armazena o que será enviado ao servidor
        BufferedReader teclado; // Armazena o que o cliente digitar para o servidor
        String mensagem;
        boolean fim = false;

        try {
//            try {
//                // Cria o socket para se comunicar no IP especificado
//                InetAddress host = InetAddress.getLocalHost();
//                System.out.println("Meu nome: " + host.getHostName());
//                System.out.println("Meu IP: " + host.getHostAddress());
//                s = new Socket(host,PORTA);
//            }catch(Exception e){
//                System.out.println("Falha na busca."+ e.getMessage());
//            }
            s = new Socket("localhost",PORTA);

            // Cria streams de entrada, saída e teclado
            entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
            saida = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
            teclado = new BufferedReader(new InputStreamReader(System.in));

            // Informa status da conexão
            System.out.println("Conectado ao servidor: "+ s.getInetAddress()+":"+s.getPort());

            // Lê a  primeira resposta do servidor
            String resposta = entrada.readLine();
            System.out.println("Servidor> "+resposta);

            // Envia várias mensagens ao servidor
            while (true){
                System.out.print("Cliente> ");
                System.out.flush();
                mensagem = teclado.readLine();
                if (mensagem.equals("Fim")){
                    fim = true;
                }
                saida.println(mensagem);
                saida.flush();

                // Lẽ a resposta do servidor, se for vazia a conexão foi encerrada pelo servidor,
                // senão imprime o que o servidor enviou (caso o cliente não queira interromper a conexão!)
                mensagem = entrada.readLine();
                if (mensagem == null){
                    System.out.println("Conexão encerrada pelo servidor");
                    break;
                }
                if (fim){
                    break;
                }
                System.out.println("Servidor> "+mensagem);
            }

            entrada.close();
            saida.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (s != null) {
                    s.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}