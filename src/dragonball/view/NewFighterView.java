package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dragonball.model.player.Player;

@SuppressWarnings("serial")
public class NewFighterView extends JPanel
{
	private Player player;
	private JLabel backgroundLabel;
	private JLabel logo;
	private JLabel fighter;
	private ImageIcon fighterImage;
	private JTextField enterName;
	private JLabel nameStatic;
	private JLabel typeStatic;
	private JLabel raceProperties;
	private JButton back;
	private JButton createFighter;
	private JLabel s;
	private JLabel e;
	private JLabel f;
	private JLabel m;
	private JLabel n;
	private JPanel defaultPanel;
	private JPanel fighterProperties;
	private JPanel fighterShapes;
	private JComboBox<String> chooseRace;
	private ArrayList<JButton> buttons;

	public NewFighterView(Player player)
	{
		this.player = player;
		preparePanel();
	}

	public JLabel getRaceProperties()
	{
		return raceProperties;
	}

	public JLabel getS()
	{
		return s;
	}

	public JLabel getE()
	{
		return e;
	}

	public JLabel getF()
	{
		return f;
	}

	public JLabel getM()
	{
		return m;
	}

	public JLabel getN()
	{
		return n;
	}

	public JPanel getDefaultPanel()
	{
		return defaultPanel;
	}

	public JPanel getFighterProperties()
	{
		return fighterProperties;
	}

	public JPanel getFighterShapes()
	{
		return fighterShapes;
	}

	public JLabel getFighter()
	{
		return fighter;
	}

	public ImageIcon getFighterImage()
	{
		return fighterImage;
	}

	public JLabel getLogo()
	{
		return logo;
	}

	public JLabel getBackgroundLabel()
	{
		return backgroundLabel;
	}

	public JLabel getTypeStatic()
	{
		return typeStatic;
	}

	public JLabel getNameStatic()
	{
		return nameStatic;
	}

	public Player getPlayer()
	{
		return player;
	}

	public JTextField getEnterName()
	{
		return enterName;
	}

	public JButton getBack()
	{
		return back;
	}

	public JButton getCreateFighter()
	{
		return createFighter;
	}

	public JComboBox<String> getChooseRace()
	{
		return chooseRace;
	}

	public ArrayList<JButton> getButtons()
	{
		return buttons;
	}

	private void preparePanel()
	{
		setBackground(Color.WHITE);
		backgroundLabel = new JLabel(
				new ImageIcon(new ImageIcon("Images/b.jpg")
						.getImage().getScaledInstance(790, 565, Image.SCALE_SMOOTH)));
		add(backgroundLabel);
		backgroundLabel.setLayout(new BorderLayout());
		defaultPanel=new JPanel();
		defaultPanel.setOpaque(false);
		defaultPanel.setLayout(null);
		logo = new JLabel("Create Fighter");
		logo.setHorizontalAlignment(JLabel.CENTER);
		logo.setBackground(new Color(135,206,250,50));
		logo.setForeground(Color.WHITE);
		logo.setFont(new Font("Arcade", Font.BOLD, 40));
		logo.setBounds(250, 0, 300, 50);
		logo.setOpaque(true);
		fighter=new JLabel(fighterImage);
		defaultPanel.add(logo);
		enterName = new JTextField();
		enterName.setBounds(420, 200, 200, 40);
		enterName.setFont(new Font("Arcade", Font.PLAIN, 30));
		enterName.setBorder(new LineBorder(Color.WHITE));
		enterName.setForeground(Color.RED);
		enterName.setBackground(Color.WHITE);
		defaultPanel.add(enterName);
		nameStatic = new JLabel("Fighter's Name:");
		nameStatic.setFont(new Font("Arcade", Font.BOLD, 40));
		nameStatic.setBounds(enterName.getX()-330, 205, 330, 40);
		nameStatic.setOpaque(false);
		nameStatic.setForeground(Color.WHITE);
		defaultPanel.add(nameStatic);
		back = new JButton("Back");
		back.setBounds(0, 0, 150, 50);
		back.setFont(new Font("Arcade", Font.BOLD, 40));
		back.setFocusPainted(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		//back.setBackground(new Color(135,206,250,50));
		back.setForeground(Color.WHITE);
		defaultPanel.add(back);
		String[] races =
		{  " ", "Saiyan", "Earthling","Majin", "Namekian", "Frieza" };
		chooseRace = new JComboBox<String>(races);
		chooseRace.setBounds(420, 245, 200, 40);
		chooseRace.setFont(new Font("Arcade", Font.PLAIN, 30));
		chooseRace.setForeground(Color.RED);
		chooseRace.setBackground(Color.WHITE);
		defaultPanel.add(chooseRace);
		typeStatic = new JLabel("Fighter's Race:");
		typeStatic.setOpaque(false);
		typeStatic.setFont(new Font("Arcade", Font.BOLD, 40));
		typeStatic.setBounds(chooseRace.getX()-330, 250, 330, 40);
		typeStatic.setForeground(Color.WHITE);
		defaultPanel.add(typeStatic);
		createFighter = new JButton("Create Fighter");
		createFighter.setBounds(logo.getX(), 290, 330, 50);
		createFighter.setForeground(Color.WHITE);
		createFighter.setFocusPainted(false);
		createFighter.setBorderPainted(false);
		createFighter.setContentAreaFilled(false);
		createFighter.setFont(new Font("Arcade", Font.BOLD, 40));
		defaultPanel.add(createFighter);
		fighterProperties=new JPanel();
	    fighterProperties.setLayout(new BorderLayout());
	    fighterProperties.setPreferredSize(new Dimension(getToolkit().getScreenSize().width,200));
		fighterProperties.setBackground(new Color(135,206,250,200));
		fighterShapes=new JPanel();
		fighterShapes.setLayout(new GridLayout(1,5));
		fighterShapes.setOpaque(false);
	    fighterShapes.setPreferredSize(new Dimension(getWidth(),150));
	    s=new JLabel(new ImageIcon("Images/Navigation/S.png"));
	    e=new JLabel(new ImageIcon("Images/Navigation/E.png"));
	    n=new JLabel(new ImageIcon("Images/Navigation/N.png"));
	    m=new JLabel(new ImageIcon("Images/Navigation/M.png"));
	    f=new JLabel(new ImageIcon("Images/Navigation/F.png"));
	    fighterShapes.add(s);
	    fighterShapes.add(e);
	    fighterShapes.add(m);
	    fighterShapes.add(n);
	    fighterShapes.add(f);
		raceProperties=new JLabel("Physical Damage: " +"     Blast Damage: "+"     Max Ki: "+ "     Max Stamina: "+"     Max HP: ");
		raceProperties.setFont(new Font("Arcade", Font.PLAIN, 18));
		raceProperties.setOpaque(false);
		raceProperties.setForeground(Color.RED);
		raceProperties.setHorizontalAlignment(JLabel.CENTER);
		fighterProperties.add(raceProperties, BorderLayout.NORTH);
		fighterProperties.add(fighterShapes, BorderLayout.CENTER);
		backgroundLabel.add(fighterProperties, BorderLayout.SOUTH);
		backgroundLabel.add(defaultPanel, BorderLayout.CENTER);
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		createFighter.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttons = new ArrayList<JButton>();
		buttons.add(back);
		buttons.add(createFighter);
	}
}