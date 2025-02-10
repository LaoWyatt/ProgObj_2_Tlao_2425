package V1;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
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
	private JPanel degPanel, coefPanel, tabPanel, bornPanel, spacePanel, trajectoryPanel, radioPanel, buttonPanel;
	private JButton validButton, resetButton;
	private JSlider degSlider, redSlider, greenSlider, blueSlider;
	private JTextField coefTF, borneMinX, borneMaxX, borneMinY, borneMaxY, spaceMinX, spaceMajX, spaceMinY, spaceMajY;
	private JTable table;
	
	private final static int NB_INPUT = 14;
	
	CheckboxGroup typeBG;
	Checkbox[] radButtons = new Checkbox[3];
	
	private MainAll _parent;
	
	String[] columnNames = {"X", "Y"};
	
	Object[][] cellules = {
			{-2,-8.0},
			{-1,-1.0},
			{0,0.0},
			{1,1.0},
			{2,8.0}
	};
	
	ConfigPanel(MainAll root){
		
		_parent = root;
		
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
		
		typeBG = new CheckboxGroup();
		
		radButtons[0] = new Checkbox("Lie", typeBG, true);
		radioPanel.add(radButtons[0]);
		
		radButtons[1] = new Checkbox("Point", typeBG, false);
		radioPanel.add(radButtons[1]);
		
		radButtons[2] = new Checkbox("Croix", typeBG, false);
		radioPanel.add(radButtons[2]);
		
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
	
	public int getDegree() {
		return degSlider.getValue();
	}
	
	public int getCoef() {
		return Integer.valueOf(coefTF.getText());
	}
	
	public int[] getBorne() {
		int tab[] = {Integer.valueOf(this.borneMinX.getText()),Integer.valueOf(this.borneMaxX.getText()),Integer.valueOf(this.borneMinY.getText()),Integer.valueOf(this.borneMaxY.getText())};
		return tab;
	}
	

	public int[] getSpace() {
		int tab[] = {Integer.valueOf(this.spaceMinX.getText()),Integer.valueOf(this.spaceMajX.getText()),Integer.valueOf(this.spaceMinY.getText()),Integer.valueOf(this.spaceMajY.getText())};
		return tab;
	}
	
	public Color getColor() {
		return new Color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue());
	}
	
	public int getType() {
		
		if (radButtons[0].getState()) {
			return 0;
		} else if (radButtons[1].getState()) {
			return 1;
		}
		return 2;
	}
	
	protected boolean setValues(int[] tab) {
		try {
			
			if (tab.length != NB_INPUT) {
				throw(new Exception("Out of Bound"));
			}
			
			degSlider.setValue(tab[0]);
			
			redSlider.setValue(tab[1]);
			greenSlider.setValue(tab[2]);
			greenSlider.setValue(tab[3]);
			
			coefTF.setText(String.valueOf(tab[4]));
			
			borneMinX.setText(String.valueOf(tab[5]));
			borneMaxX.setText(String.valueOf(tab[6]));
			borneMinY.setText(String.valueOf(tab[7]));
			borneMaxY.setText(String.valueOf(tab[8]));
			
			spaceMinX.setText(String.valueOf(tab[9]));
			spaceMajX.setText(String.valueOf(tab[10]));
			spaceMinY.setText(String.valueOf(tab[11]));
			spaceMajY.setText(String.valueOf(tab[12]));
			
			switch(tab[13]) {
			case 0:
				radButtons[0].setState(true);
				radButtons[1].setState(false);
				radButtons[2].setState(false);
				break;
				
			case 1:
				radButtons[0].setState(false);
				radButtons[1].setState(true);
				radButtons[2].setState(false);
				break;
				
			case 2:
				radButtons[0].setState(false);
				radButtons[1].setState(false);
				radButtons[2].setState(true);
				break;
			}
			
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == validButton) {
			_parent.updateGraph();
		} else if (e.getSource() == resetButton) {
			int tab[] = {0,255,0,0,0,-10,10,-10,10,1,5,1,5,0};
			setValues(tab);
		}
	}

}
