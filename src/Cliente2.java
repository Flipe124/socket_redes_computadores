import java.io.*;
import java.net.Socket;

public  class Cliente2 {
    public  static final  int PORTA = 8347;

    public static void main(String[] args){
        Socket s = null;
        BufferedReader entrada;
        PrintWriter saida;
        BufferedReader teclado;
        String mensagem;
        boolean fim = false;

        try {
            s = new Socket("localhost", PORTA);
            entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));

            saida = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));

            teclado = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Conectando ao servidor " + s.getInetAddress() + ":" + s.getPort());

            String resposta = entrada.readLine();
            System.out.println("servidor>" + resposta);

            while (true){
                System.out.println("Cliente> ");
                System.out.flush();

                mensagem = teclado.readLine();

                if (mensagem.equals("Fim")){
                    fim = true;
                }

                saida.println(mensagem);
                saida.flush();

                // Lê resposta do servidor
                saida.println(mensagem);
                saida.flush();

                mensagem = entrada.readLine();

                if (mensagem == null){
                    System.out.println("Conexão encerrado pelo servidor!");
                    break;
                }

                if (fim){
                    break;
                }

                try {
                    entrada.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                saida.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (s != null){
                    s.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}