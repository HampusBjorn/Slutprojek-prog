import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 2345;
        ServerSocket serverSocket;
        Socket socket;
        System.out.println("Server started");
        boolean conected = false;

        try {
            serverSocket = new ServerSocket(port);
            while(true){
                //kollar om du har anslutit till servern tidigara och om du har det så kör den inte vissa saker
                if (conected == false) {
                    System.out.println("Waiting for connections!");
                }
                socket = serverSocket.accept();

                if (conected == false) {
                    System.out.println("Connecting...");
                    System.out.println("Connected");
                }
                conected = true;

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


                String msg = in.readLine();
                String key = in.readLine();

                //Kryptering
                int[] crypt = new int[msg.length()];
                for (int i = 0; i < msg.length(); i++) {
                    if (msg.length() > key.length()) key += key;
                    crypt[i] = msg.charAt(i) ^ key.charAt(i);
                }

                //Bygger till en string
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < crypt.length; i++){
                    sb.append((char) crypt[i]);
                }

                String cryptMsg = sb.toString();
                System.out.println(cryptMsg);
                out.println(cryptMsg);


                in.close();
                out.close();
                socket.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Server fail");
        }

    }
}
