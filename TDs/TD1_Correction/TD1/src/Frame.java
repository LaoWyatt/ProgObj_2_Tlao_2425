import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Frame extends JFrame {
    private JTextArea[] Labels;
    private JTextField[] Infos;
    private JSlider slider;
    private JButton prem, der, prec, suiv, mil, num, sup, nouv, save;
    private JButton photoPrec, photoSuiv;
    private JMenuItem menuNouv, menuSupp, menuSave;
    private ImageIcon[] photo;
    private JLabel photoLabel;

    public Frame() {
        // Initialize Labels and Infos
        this.Labels = new JTextArea[ClientController.LABELS.length];
        this.Infos = new JTextField[this.Labels.length];

        // Initialize menu and menu bar
        JMenu menuEdit = new JMenu("Edit");
        menuEdit.add(this.menuSave = new JMenuItem("Save"));
        menuEdit.add(this.menuSupp = new JMenuItem("Supprimer"));
        menuEdit.add(this.menuNouv = new JMenuItem("Nouveau"));

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuEdit);

        // Initialize info panel
        JPanel fiche = new JPanel(new GridLayout(this.Labels.length, 2));
        for (int i = 0; i < this.Labels.length; i++) {
            this.Labels[i] = new JTextArea(ClientController.LABELS[i]);
            this.Labels[i].setEditable(false);
            this.Infos[i] = new JTextField("");
            fiche.add(this.Labels[i]);
            fiche.add(this.Infos[i]);
        }
        this.Infos[0].setEditable(false);

        // Initialize command panel
        JPanel commandes = new JPanel(new GridLayout(2, 0));
        commandes.setFont(new Font("Helvetica", Font.PLAIN, 12));
        commandes.add(prem = new JButton("Debut"));
        commandes.add(suiv = new JButton("Suivant"));
        commandes.add(prec = new JButton("Precedent"));
        commandes.add(der = new JButton("Fin"));
        commandes.add(mil = new JButton("Milieu"));
        commandes.add(num = new JButton("Numero"));
        commandes.add(sup = new JButton("Supprimer"));
        commandes.add(nouv = new JButton("Nouveau"));
        commandes.add(save = new JButton("Save"));

        // Initialize photo panel
        JPanel photoPanel = new JPanel();
        this.photo = new ImageIcon[7];
        for (int i = 1; i <= 7; i++) {
            this.photo[i - 1] = new ImageIcon("Client" + i + ".png");
        }

        photoPanel.add(this.photoPrec = new JButton("<"));
        photoPanel.add(this.photoLabel = new JLabel());
        photoPanel.add(this.photoSuiv = new JButton(">"));

        // Add components to frame
        this.setJMenuBar(menuBar);
        this.add(photoPanel, BorderLayout.NORTH);
        this.add(fiche, BorderLayout.CENTER);
        this.add(commandes, BorderLayout.SOUTH);
    }

    // Constructor with slider
    public Frame(int cliNum) {
        this();
        this.slider = new JSlider(JSlider.VERTICAL, 0, cliNum - 1, 0);
        this.slider.setMajorTickSpacing(1);
        this.slider.setPaintTicks(true);
        this.slider.setInverted(true);
        this.add(this.slider, BorderLayout.EAST);
    }

    public void afficher(Client client) {
        String[] str = client.getInfos();
        for (int i = 0; i < Infos.length; i++) {
            this.Infos[i].setText(str[i]);
        }
        this.setPhoto(client.getIndexPhoto());
    }

    public void setSliderMax(int i) {
        this.slider.setMaximum(i);
    }

    public void setSlider(int i) {
        if (i <= this.slider.getMaximum()) {
            this.slider.setValue(i);
        }
    }

    public void setPhoto(int index) {
        if (index > -1 && index < 7) {
            this.photoLabel.setIcon(this.photo[index]);
        } else {
            this.photoLabel.setIcon(null);
        }
    }

    public JTextArea[] getLabels() {
        return Labels;
    }

    public JTextField[] getInfos() {
        return Infos;
    }

    public JSlider getSlider() {
        return slider;
    }

    public JButton getPrem() {
        return prem;
    }

    public JButton getDer() {
        return der;
    }

    public JButton getPrec() {
        return prec;
    }

    public JButton getSuiv() {
        return suiv;
    }

    public JButton getMil() {
        return mil;
    }

    public JButton getNum() {
        return num;
    }

    public JButton getSup() {
        return sup;
    }

    public JButton getNouv() {
        return nouv;
    }

    public JButton getSave() {
        return save;
    }

    public JMenuItem getMenuNouv() {
        return menuNouv;
    }

    public JMenuItem getMenuSupp() {
        return menuSupp;
    }

    public JMenuItem getMenuSave() {
        return menuSave;
    }

    public JButton getPhotoPrec() {
        return photoPrec;
    }

    public JButton getPhotoSuiv() {
        return photoSuiv;
    }

    public ImageIcon[] getPhoto() {
        return photo;
    }

    public JLabel getPhotoLabel() {
        return photoLabel;
    }
}
