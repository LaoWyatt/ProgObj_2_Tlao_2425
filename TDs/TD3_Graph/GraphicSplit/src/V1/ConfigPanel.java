package V1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


public class ConfigPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	JPanel degPanel, coefPanel, tabPanel, bornPanel, spacePanel, trajectoryPanel, radioPanel, buttonPanel;
	JButton validButton, resetButton;
	JRadioButton ligneButton, croixButton, pointButton;
	JSlider degSlider, redSlider, greenSlider, blueSlider;
	JTextField coefTF, borneMinX, borneMaxX, borneMinY, borneMaxY, spaceMinX, spaceMajX, spaceMinY, spaceMajY;
	JTable table;
	ButtonGroup typeBG;
	
	String[] columnNames = {"X", "Y"};
	
	Object[][] cellules = {
			{-2,-8.0},
			{-1,-1.0},
			{0,0.0},
			{1,1.0},
			{2,8.0}
	};
	
	ConfigPanel(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		degPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		degPanel.setBorder(new TitledBorder("Degre du polynome"));
		
		degSlider = new JSlider(0, 100, 0);
		degSlider.setMajorTickSpacing(25);
		degSlider.setPaintTicks(true);
		degSlider.setPaintLabels(true);
		degSlider.setPaintTrack(true);
		degSlider.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		degPanel.add(degSlider);
		
		
		coefPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		coefPanel.setBorder(new TitledBorder("Coefficients"));
		
		coefTF = new JTextField("0");
		coefTF.setPreferredSize(new Dimension(40, 22));
		
		coefPanel.add(coefTF);
		
		
		tabPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		tabPanel.setBorder(new TitledBorder("Tableau"));
		tabPanel.setPreferredSize(new Dimension(100,140));
		
		table = new JTable(cellules,columnNames);
		table.setAutoResizeMode(0);
		tabPanel.add(new JScrollPane(table));
		
		bornPanel = new JPanel(new GridLayout(4,2));
		bornPanel.setBorder(new TitledBorder("Bornes"));
		
		bornPanel.add(new JLabel("X min"));
		
		borneMinX = new JTextField("-10");
		borneMinX.setPreferredSize(new Dimension(40,22));
		
		bornPanel.add(borneMinX);
		
		bornPanel.add(new JLabel("X max"));
		
		borneMaxX = new JTextField("10");
		borneMaxX.setPreferredSize(new Dimension(40,22));
		
		bornPanel.add(borneMaxX);
		
		bornPanel.add(new JLabel("Y min"));
		
		borneMinY = new JTextField("-10");
		borneMinY.setPreferredSize(new Dimension(40,22));
		
		bornPanel.add(borneMinY);
		
		bornPanel.add(new JLabel("Y max"));
		
		borneMaxY = new JTextField("10");
		borneMaxY.setPreferredSize(new Dimension(40,22));
		
		bornPanel.add(borneMaxY);
		
		
		spacePanel = new JPanel(new GridLayout(4,2));
		spacePanel.setBorder(new TitledBorder("Espacements"));
		
		spacePanel.add(new JLabel("X mineur"));
		
		spaceMinX = new JTextField("1");
		spaceMinX.setPreferredSize(new Dimension(40,22));
		
		spacePanel.add(spaceMinX);
		
		spacePanel.add(new JLabel("X majeur"));
		
		spaceMajX = new JTextField("5");
		spaceMajX.setPreferredSize(new Dimension(40,22));
		
		spacePanel.add(spaceMajX);
		
		spacePanel.add(new JLabel("Y mineur"));
		
		spaceMinY = new JTextField("1");
		spaceMinY.setPreferredSize(new Dimension(40,22));
		
		spacePanel.add(spaceMinY);
		
		spacePanel.add(new JLabel("Y majeur"));
		
		spaceMajY = new JTextField("5");
		spaceMajY.setPreferredSize(new Dimension(40,22));
		
		spacePanel.add(spaceMajY);
		
		
		trajectoryPanel = new JPanel();
		trajectoryPanel.setLayout(new BoxLayout(trajectoryPanel,BoxLayout.Y_AXIS));
		trajectoryPanel.setBorder(new TitledBorder("Courbe"));
		
		radioPanel = new JPanel();
		radioPanel.setLayout(new BoxLayout(radioPanel,BoxLayout.X_AXIS));
		
		typeBG = new ButtonGroup();
		
		ligneButton = new JRadioButton("Lie",true);
		typeBG.add(ligneButton);
		radioPanel.add(ligneButton);
		
		pointButton = new JRadioButton("Point",false);
		typeBG.add(pointButton);
		radioPanel.add(pointButton);
		
		croixButton = new JRadioButton("Croix",false);
		typeBG.add(croixButton);
		radioPanel.add(croixButton);
		
		trajectoryPanel.add(radioPanel);
		
		trajectoryPanel.add(new JLabel("Rouge"));
		
		redSlider = new JSlider(0,255,255);
		redSlider.setMajorTickSpacing(50);
		redSlider.setPaintTicks(true);
		redSlider.setPaintLabels(false);
		redSlider.setPaintTrack(true);
		redSlider.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		trajectoryPanel.add(redSlider);
		
		trajectoryPanel.add(new JLabel("Vert"));
		
		greenSlider = new JSlider(0,255,0);
		greenSlider.setMajorTickSpacing(50);
		greenSlider.setPaintTicks(true);
		greenSlider.setPaintLabels(false);
		greenSlider.setPaintTrack(true);
		greenSlider.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		trajectoryPanel.add(greenSlider);
		
		trajectoryPanel.add(new JLabel("Bleu"));
		
		blueSlider = new JSlider(0,255,0);
		blueSlider.setMajorTickSpacing(50);
		blueSlider.setPaintTicks(true);
		blueSlider.setPaintLabels(false);
		blueSlider.setPaintTrack(true);
		blueSlider.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		trajectoryPanel.add(blueSlider);
		
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
		
		validButton = new JButton("Valider");
		validButton.addActionListener(this);
		buttonPanel.add(validButton);
		
		
		resetButton = new JButton("Reinitialiser");
		resetButton.addActionListener(this);
		buttonPanel.add(resetButton);
		
		this.add(new JScrollPane(degPanel));
		this.add(coefPanel);
		this.add(new JScrollPane(tabPanel));
		this.add(new JScrollPane(bornPanel));
		this.add(new JScrollPane(spacePanel));
		this.add(new JScrollPane(trajectoryPanel));
		this.add(buttonPanel);
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
