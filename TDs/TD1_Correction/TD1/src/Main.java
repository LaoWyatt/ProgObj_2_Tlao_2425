import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        ClientController cards = new ClientController();
        cards.getFrame().addWindowListener(new Fermeur());
        cards.getFrame().setSize(400, 500);
        cards.getFrame().setVisible(true);
    }
}

class Fermeur extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
