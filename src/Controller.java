import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller {
    private GUI view;
    private Client client;

    /**
     * Class constructor
     * @param view the GUI
     * @param client the Model
     */
    public Controller(GUI view, Client client){
        this.view = view;
        this.client = client;
        //JFramen skappas
        JFrame frame = new JFrame("KryptGUI");
        frame.setContentPane(view.getGUI());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        this.view.addCryptListener(new CryptListener());
        this.view.addGenerateListener(new GenerateListener());
        this.view.addConnectListener(new ConnectListener());
        this.view.addOpenListener(new OpenListener());
    }

    /**
     * A actionListener for the cryptButton.
     */
    class CryptListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!view.getKey().isEmpty() && !view.getMsg().isEmpty()) {
                view.setCryptArea(client.crypt(view.getMsg(), view.getKey()));
                //ansluter till servern igen på grund av att man blir från kopplad
                client.connect(view.getIp(), view.getPort()); }
            else JOptionPane.showMessageDialog(null, "Du måste ange en nyckel och meddelande");
        }
    }

    class GenerateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setKeyArea(client.generateKey());
        }
    }

    /**
     * A actionListener for the connectButton.
     */
    class ConnectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           client.connect(view.getIp(), view.getPort());
        }
    }

    /**
     * A actionListener for the openButton.
     */
    class OpenListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setMsgArea(client.openFile());
        }
    }
}
