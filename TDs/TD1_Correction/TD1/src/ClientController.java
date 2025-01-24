import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class ClientController {
    static final String LABELS[] = { "Numéro", "Nom", "Prénom", "Téléphone", "Adresse", "Code Postal", "Email", "Métier", "Situation" };

    private Frame frame;
    private ArrayList<Client> Clients;
    private int numCli;
    private int index;

    public ClientController() {
        this.Clients = new ArrayList<>();
        load();
        this.frame = new Frame(this.numCli);
        this.index = 0;
        this.frame.afficher(this.Clients.get(this.index));

        ButtonListn buttonListener = new ButtonListn();
        SaveAction saveAction = new SaveAction("Save");
        SuppAction suppAction = new SuppAction("Supprimer");
        NouvAction nouvAction = new NouvAction("Nouveau");

        this.frame.getPrec().addActionListener(buttonListener);
        this.frame.getPrem().addActionListener(buttonListener);
        this.frame.getDer().addActionListener(buttonListener);
        this.frame.getSuiv().addActionListener(buttonListener);
        this.frame.getMil().addActionListener(buttonListener);
        this.frame.getNum().addActionListener(buttonListener);
        this.frame.getNouv().addActionListener(buttonListener);
        this.frame.getSup().addActionListener(buttonListener);
        this.frame.getPhotoPrec().addActionListener(buttonListener);
        this.frame.getPhotoSuiv().addActionListener(buttonListener);

        this.frame.getMenuSave().setAction(saveAction);
        this.frame.getMenuSupp().setAction(suppAction);
        this.frame.getMenuNouv().setAction(nouvAction);
        this.frame.getSave().setAction(saveAction);
        this.frame.getSup().setAction(suppAction);
        this.frame.getNouv().setAction(nouvAction);

        this.frame.getSlider().addChangeListener(new SliderListn());

        for (int i = 0; i < LABELS.length; i++) {
            this.frame.getInfos()[i].getDocument().addDocumentListener(new TextListn(i));
        }
    }

    public void load() {
        try (BufferedReader buff = new BufferedReader(new FileReader("clients.txt"))) {
            String ligne = buff.readLine();
            this.setNumCli(Integer.parseInt(ligne));

            int i = 0;
            while (i < this.numCli) {
                int j = 0;
                this.Clients.add(new Client(i + 1));
                ligne = buff.readLine();
                for (String val : ligne.split("::")) {
                    this.Clients.get(i).setInfo(val, j);
                    j++;
                }
                ligne = buff.readLine();
                this.Clients.get(i).setIndexPhoto(Integer.parseInt(ligne));
                i++;
            }
        } catch (IOException | NumberFormatException | NullPointerException e) {
            this.setNumCli(10);
            for (int i = 0; i < this.numCli; i++) {
                this.Clients.add(new Client(i + 1));
            }
            e.printStackTrace();
        }
    }

    public void save() {
        try (PrintWriter buff = new PrintWriter(new FileWriter("clients.txt"))) {
            buff.println(this.numCli);

            for (Client client : this.Clients) {
                buff.println(client.toStringInfos());
                buff.println(client.toStringIndexPhoto());
            }
        } catch (IOException e) {
            System.out.println("Erreur écriture");
            e.printStackTrace();
        }
    }

    public Frame getFrame() {
        return frame;
    }

    public void setNumCli(int numCli) {
        if (numCli > 0) {
            this.numCli = numCli;
            try {
                this.frame.setSliderMax(numCli - 1);
            } catch (NullPointerException e) {
                // Pas de frame encore initialisée
            }
        }
        System.out.println("numCli = " + this.numCli);
    }

    public void addNumCli(int var) {
        this.setNumCli(numCli + var);
    }

    public void setIndex(int index) {
        if (index > -1 && index < numCli) {
            this.index = index;
            frame.setSlider(this.index);
            frame.afficher(Clients.get(this.index));
        }
        System.out.println("index = " + this.index);
    }

    public void addIndex(int var) {
        setIndex(this.index + var);
    }

    class SaveAction extends AbstractAction {
        public SaveAction(String text) {
            super(text);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            save();
        }
    }

    class SuppAction extends AbstractAction {
        public SuppAction(String text) {
            super(text);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (numCli > 1) {
                Clients.remove(index);
                addNumCli(-1);
                for (int i = 0; i < numCli; i++) {
                    Clients.get(i).setNum(i + 1);
                }

                if (index == numCli) {
                    addIndex(-1);
                } else {
                    addIndex(0);
                }
            }
        }
    }

    class NouvAction extends AbstractAction {
        public NouvAction(String text) {
            super(text);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            addNumCli(1);
            Clients.add(new Client(numCli));
            setIndex(numCli - 1);
        }
    }

    class ButtonListn implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = ((JButton) e.getSource()).getText();
            System.out.println(str);

            switch (str) {
                case "Debut":
                    setIndex(0);
                    break;

                case "Suivant":
                    addIndex(1);
                    break;

                case "Fin":
                    setIndex(numCli - 1);
                    break;

                case "Precedent":
                    addIndex(-1);
                    break;

                case "Milieu":
                    setIndex((numCli - 1) / 2);
                    break;

                case "Numero":
                    String cliNum = JOptionPane.showInputDialog(frame, "Numero du client?", null);
                    setIndex(Integer.parseInt(cliNum) - 1);
                    break;

                case "<":
                    Clients.get(index).addIndexPhoto(-1);
                    frame.afficher(Clients.get(index));
                    break;

                case ">":
                    Clients.get(index).addIndexPhoto(1);
                    frame.afficher(Clients.get(index));
                    break;

                default:
                    break;
            }
        }
    }

    class SliderListn implements javax.swing.event.ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            setIndex((frame.getSlider().getValue()));
        }
    }

    class TextListn implements DocumentListener {
        private final int i;

        public TextListn(int i) {
            this.i = i;
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            // Ne s'applique pas aux champs de texte simples
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            sync(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            sync(e);
        }

        private void sync(DocumentEvent e) {
            int len = e.getDocument().getLength();
            try {
                String str = e.getDocument().getText(0, len);
                System.out.println(str);
                Clients.get(index).setInfo(str, this.i);
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }
    }
}
