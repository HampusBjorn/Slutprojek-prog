import javax.swing.*;
import java.io.*;
import java.net.Socket;


/**
 *
 */
public class Client {
    PrintWriter out;
    BufferedReader in;
    boolean conected = false;


    /**
     * Connects the client to the server.
     * @param ip the ip address used to connect to the server.
     * @param port the port used to connect to the server.
     */
    public void connect(String ip, int port) {
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Client failed to connect");
        }

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //om du har anslutit till servern redan körs inte det här
        if (conected == false) {
            JOptionPane.showMessageDialog(null, "Du är nu ansluten till servern");
            conected = true;
        }

    }

    /**
     * Send the message and key used in encryption to the server.
     * @param msg the message used in encryption.
     * @param key the key used in encryption.
     * @return the encrypted message.
     */
    public String crypt(String msg, String key) {
        String crypt = null;

            out.println(msg);
            out.println(key);

            try {
                crypt = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return crypt;
        }

    /**
     * Uses JFileChooser to get the path for the file used in encryption.
     * @return the content of the file as a string with line breaks.
     */
    public String openFile(){
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        String fileName = chooser.getSelectedFile().getAbsolutePath();

        System.out.println(fileName);

        FileReader fr = null;
        try {
            fr = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader inFil = new BufferedReader(fr);

        StringBuilder sb = new StringBuilder();

        String i = "";

        try {
            i = inFil.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(i != null) {
            sb.append(i + "\n");
            try {
                i = inFil.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String file = sb.toString();
        return file;
    }
    //slumpmäsig string skapas
    public String generateKey(){
        final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        //den får max vara 20 men är minst 5
        int count = (int) ((Math.random() * 15) + 5);
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}

