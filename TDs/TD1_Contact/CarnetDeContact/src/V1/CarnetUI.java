package V1;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CarnetUI extends JFrame implements ActionListener {
	static ContactList list = new ContactList();
	static JLabel numLabel;
	static JTextField nomTF, prenomTF, telephoneTF, adresseTF, codePostalTF, emailTF, metierTF, situationTF, answerNum;
	static JButton prevAButton, suivAButton, prevC, debutC, millieuC, finC, suivC, numC, suppC, nouvC, saveC, saveModC;
	static JLabel avatar;
	static ArrayList<String> avatarList = new ArrayList<>();
	static int currentAvatar;
	
	CarnetUI(){
		JPanel avatarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		prevAButton = new JButton("<");
		prevAButton.addActionListener(this);
		avatar = new JLabel();
        avatar.setIcon(new ImageIcon(new ImageIcon(getAbPath() + "Client1.png").getImage().getScaledInstance(60, 75, Image.SCALE_SMOOTH)));
		suivAButton = new JButton(">");
		suivAButton.addActionListener(this);
		
		avatarPanel.add(prevAButton);
		avatarPanel.add(avatar);
		avatarPanel.add(suivAButton);
		
		currentAvatar = 0;
		
		for (int i = 1; i < 8; i++) {
			avatarList.add(getAbPath() + "Client" + i + ".png");
		}
		
		

		JPanel infoPanel = new JPanel(new GridLayout(9,2,1,1));
		
		infoPanel.add(new JLabel("Numéro:"));
		numLabel = new JLabel("");
		infoPanel.add(numLabel);
		
		infoPanel.add(new JLabel("Nom:"));
		nomTF = new JTextField();
		infoPanel.add(nomTF);
		
		infoPanel.add(new JLabel("Prénom:"));
		prenomTF = new JTextField();
		infoPanel.add(prenomTF);
		
		infoPanel.add(new JLabel("Téléphone:"));
		telephoneTF = new JTextField();
		infoPanel.add(telephoneTF);
		
		infoPanel.add(new JLabel("Adresse:"));
		adresseTF = new JTextField();
		infoPanel.add(adresseTF);
		
		infoPanel.add(new JLabel("Code Postal:"));
		codePostalTF = new JTextField();
		infoPanel.add(codePostalTF);
		
		infoPanel.add(new JLabel("Email:"));
		emailTF = new JTextField();
		infoPanel.add(emailTF);
		
		infoPanel.add(new JLabel("Métier:"));
		metierTF = new JTextField();
		infoPanel.add(metierTF);
		
		infoPanel.add(new JLabel("Situation:"));
		situationTF = new JTextField();
		infoPanel.add(situationTF);
		
		JScrollPane scrollInfo = new JScrollPane(infoPanel);
		
		
		JPanel buttonPanel = new JPanel(new GridLayout(3,4,1,1));
		
		prevC = new JButton("Prec");
		buttonPanel.add(prevC);
		
		debutC = new JButton("Début");
		buttonPanel.add(debutC);
		
		millieuC = new JButton("Milieu");
		buttonPanel.add(millieuC);
		
		finC = new JButton("Fin");
		buttonPanel.add(finC);
		
		suivC = new JButton("Suiv...");
		buttonPanel.add(suivC);
		
		suppC = new JButton("Suppr...");
		buttonPanel.add(suppC);
		
		nouvC = new JButton("Nouveau");
		buttonPanel.add(nouvC);
		
		saveC = new JButton("Sauv...");
		buttonPanel.add(saveC);
		
		saveModC = new JButton("Sauv Modif");
		buttonPanel.add(saveModC);
		
		numC = new JButton("Num");
		buttonPanel.add(numC);
		
		answerNum = new JTextField("1");
		buttonPanel.add(answerNum);
		
		prevC.addActionListener(this);
		debutC.addActionListener(this);
		millieuC.addActionListener(this);
		finC.addActionListener(this);
		suivC.addActionListener(this);
		numC.addActionListener(this);
		suppC.addActionListener(this);
		nouvC.addActionListener(this);
		saveC.addActionListener(this);
		saveModC.addActionListener(this);
		
		
		JMenu editMenu = new JMenu("Edit");
		JMenuItem saveM = new JMenuItem("Save");
		editMenu.add(saveM);
		
		JMenuBar bMenu = new JMenuBar();
		bMenu.add(editMenu);

		
		
		getRootPane().setJMenuBar(bMenu);
		JPanel panneau = (JPanel)getContentPane();
		panneau.setLayout(
		new BoxLayout(panneau, BoxLayout.Y_AXIS));
		this.setTitle("Carnet de contacts");
		panneau.add(Box.createVerticalStrut(5));
		panneau.add(avatarPanel);
		panneau.add(Box.createVerticalStrut(5));
		panneau.add(scrollInfo);
		panneau.add(Box.createVerticalStrut(5));
		panneau.add(buttonPanel);
		
		
	}
	
	public static void main(String[] args) {
		CarnetUI t = new CarnetUI();
		t.pack();
		
		list.reloadSave();
		if (list.getSize() < 1) {
			list.addContact("Ender", "Night", "88776655", "HTTYD", 12345, "Ender@Celest.Oc", "retired", "Solo", 0);
			list.addContact("Light", "Lightning", "31452369", "HTTYD", 69695, "Light@Celest.Oc", "Scientist", "Solo", 1);
			list.addContact("Over", "Shift", "99999999", "HTTYD", 11111, "Over@Celest.Oc", "Library", "Solo", 2);
		}
		
		displayContact(1);
		t.setVisible(true);
		t.addWindowListener(new WindowCloser());
		//System.out.print((new File().getName());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand()) {
		
			case "<":
				if (currentAvatar == 0){
					currentAvatar = avatarList.size() - 1;
		        } else {
		        	currentAvatar--;
		        }
				avatar.setIcon(new ImageIcon(new ImageIcon(avatarList.get(currentAvatar)).getImage().getScaledInstance(60, 75, Image.SCALE_SMOOTH)));
				break;
		
			case ">":
				if (currentAvatar + 1 == avatarList.size()){
					currentAvatar = 0;
		        } else {
		        	currentAvatar++;
		        }
				avatar.setIcon(new ImageIcon(new ImageIcon(avatarList.get(currentAvatar)).getImage().getScaledInstance(60, 75, Image.SCALE_SMOOTH)));
				break;
				
			case "Prec":
				if (list.getSize() > 0){
		            int idd = Integer.valueOf(numLabel.getText());

		            if (idd == 1){
		                displayContact(list.getSize());
		            } else displayContact(idd - 1);
		        }
				break;
				
			case "Début":
				if (list.getSize() > 0) displayContact(1);
				break;
				
			case "Milieu":
				int size = list.getSize();
				if (size > 1) {
					int res = list.getSize() / 2;
		            if (size % 2 == 1) {
		            	res++;
		            }
		            displayContact(res);
		        }
				break;
				
			case "Fin":
				if (list.getSize() > 0) displayContact(list.getSize());
				break;
				
			case "Suiv...":
				if(list.getSize() > 0){
		            int idd = Integer.valueOf(numLabel.getText());

		            if (idd == list.getSize()){
		                displayContact(1);
		            } else {
		                displayContact(idd + 1);
		            }
		        }
				break;
				
			case "Suppr...":
				if (list.getSize() > 0){
		            int idd = Integer.valueOf(numLabel.getText());
		            list.deleteContact(idd);
		            if (list.getSize() > 0 && idd <= list.getSize()) displayContact(idd);
		            else if (list.getSize() > 0) displayContact(list.getSize());

		            if (list.getSize() == 0) clearContact();
		        }
				break;
				
			case "Nouveau":
				clearContact();

		        int id = list.getSize() + 1;
		        numLabel.setText(String.valueOf(id));
				break;
				
			case "Sauv...":
				if (list.Save()) JOptionPane.showMessageDialog(this, "Carnet sauvegardé !", "INFO", JOptionPane.PLAIN_MESSAGE);
				break;
				
			case "Sauv Modif":
				
				if (!numLabel.getText().isEmpty()) {
					
		            int size1 = list.getSize();
		            int iddd = Integer.valueOf(numLabel.getText());
	
		            if (iddd == size1 + 1){
		                if(!list.addContact(nomTF.getText(),prenomTF.getText(),telephoneTF.getText(),
		                        adresseTF.getText(),Integer.valueOf(codePostalTF.getText()),emailTF.getText(),
		                        metierTF.getText(),situationTF.getText(),currentAvatar))JOptionPane.showMessageDialog(this, "Code postal non valide !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		                
		            } else if (iddd < size1 + 1 && iddd > 0){
		                if(!list.modifyContact(iddd,nomTF.getText(),prenomTF.getText(),telephoneTF.getText(),
		                        adresseTF.getText(),Integer.valueOf(codePostalTF.getText()),emailTF.getText(),
		                        metierTF.getText(),situationTF.getText(),currentAvatar))JOptionPane.showMessageDialog(this, "Code postal non valide !", "ERREUR", JOptionPane.ERROR_MESSAGE);
		            }
		            JOptionPane.showMessageDialog(this, "Contact sauvegardé !", "INFO", JOptionPane.PLAIN_MESSAGE);
				}
				break;
				
			case "Num":
				try {
					int n = Integer.valueOf(answerNum.getText());
					if (n > 0 && n < list.getSize() + 1){
						displayContact(n);
					} else {
						JOptionPane.showMessageDialog(this, "Iddentifiant non valide !", "ERREUR", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "Veillez entrez un iddentifiant !", "ERREUR", JOptionPane.ERROR_MESSAGE);
				}
				
				break;
		}
		
	}
	
	public static String getAbPath() {
		return CarnetUI.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("/bin/","").replace("/C:", "C:").replace("/", "\\") + "\\src\\V1\\";
	}

	private static void displayContact(int id){
        Contact c = list.getContact(id-1);
        
        numLabel.setText(Integer.toString(c.getID()));
        nomTF.setText(c.getNom());
        prenomTF.setText(c.getPrenom());
        telephoneTF.setText(c.getNumTel());
        codePostalTF.setText(Integer.toString(c.getCodePostal()));
        adresseTF.setText(c.getAdresse());
        emailTF.setText(c.getEmail());
        metierTF.setText(c.getMetier());
        situationTF.setText(c.getSituation());
        currentAvatar = c.getPFP();
        avatar.setIcon(new ImageIcon(new ImageIcon(avatarList.get(currentAvatar)).getImage().getScaledInstance(60, 75, Image.SCALE_SMOOTH)));;
    }
	
	private static void clearContact(){
        numLabel.setText("");
        nomTF.setText("");
        prenomTF.setText("");
        telephoneTF.setText("");
        codePostalTF.setText("");
        adresseTF.setText("");
        emailTF.setText("");
        metierTF.setText("");
        situationTF.setText("");
        currentAvatar = 0;
        avatar.setIcon(new ImageIcon(new ImageIcon(getAbPath() + "Client1.png").getImage().getScaledInstance(60, 75, Image.SCALE_SMOOTH)));;
    }
	
}
