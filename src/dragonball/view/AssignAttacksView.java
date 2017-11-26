package dragonball.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.player.Player;

@SuppressWarnings("serial")
public class AssignAttacksView extends JPanel
{
	private Player player;
	private JPanel attacks;
	private JLabel backgroundLabel;
	private JLabel logo;
	private JLabel superAttacksAssigned;
	private JLabel ultimateAttacksAssigned;
	private JLabel replaceWith;
	private JLabel superAttacksStatic;
	private JLabel ultimateAttacksStatic;
	private JTextArea superA;
	private JTextArea ultimateA;
	private JButton back;
	private JButton addSuper;
	private JButton addUltimate;
	private JComboBox<String> ultimateAttack;
	private JComboBox<String> ultimateAttackOld;
	private JComboBox<String> superAttackOld;
	private JComboBox<String> superAttack;
	private ArrayList<JComboBox<String>> boxes;
	private ArrayList<JButton> buttons;
	private ArrayList<String> superAttackNames;
	private ArrayList<String> ultimateAttackNames;
	private ArrayList<String> assignedSuperAttacks;
	private ArrayList<String> assignedUltimateAttacks;

	public AssignAttacksView(Player player)
	{
		this.player = player;
		preparePanel();
	}

	public JTextArea getSuperA()
	{
		return superA;
	}

	public JTextArea getUltimateA()
	{
		return ultimateA;
	}

	public JPanel getAttacks()
	{
		return attacks;
	}

	public ArrayList<String> getAssignedSuperAttacks()
	{
		return assignedSuperAttacks;
	}

	public ArrayList<String> getAssignedUltimateAttacks()
	{
		return assignedUltimateAttacks;
	}

	public JLabel getReplaceWith()
	{
		return replaceWith;
	}

	public JButton getAddSuper()
	{
		return addSuper;
	}

	public JButton getAddUltimate()
	{
		return addUltimate;
	}

	public JComboBox<String> getUltimateAttackOld()
	{
		return ultimateAttackOld;
	}

	public JComboBox<String> getSuperAttackOld()
	{
		return superAttackOld;
	}

	public void setPlayer(Player player)
	{
		this.player=player;
	}
	
	public JLabel getSuperAttacksAssigned()
	{
		return superAttacksAssigned;
	}

	public JLabel getUltimateAttacksAssigned()
	{
		return ultimateAttacksAssigned;
	}

	public JComboBox<String> getUltimateAttack()
	{
		return ultimateAttack;
	}

	public JComboBox<String> getSuperAttack()
	{
		return superAttack;
	}

	public JLabel getSuperAttacksStatic()
	{
		return superAttacksStatic;
	}

	public JLabel getUltimateAttacksStatic()
	{
		return ultimateAttacksStatic;
	}

	public ArrayList<JComboBox<String>> getBoxes()
	{
		return boxes;
	}

	public Player getPlayer()
	{
		return player;
	}

	public JLabel getBackgroundLabel()
	{
		return backgroundLabel;
	}

	public JLabel getLogo()
	{
		return logo;
	}

	public JButton getBack()
	{
		return back;
	}

	public ArrayList<JButton> getButtons()
	{
		return buttons;
	}

	public ArrayList<String> getSuperAttackNames()
	{
		return superAttackNames;
	}

	public ArrayList<String> getUltimateAttackNames()
	{
		return ultimateAttackNames;
	}
	
	public String printSuperAttacks()
	{
		String s = "";
		int i=1;
		for(SuperAttack attack : player.getActiveFighter().getSuperAttacks())
		{
			s+=i+") "+attack.getName() +" ("+attack.getDamage()+")\n";
			i++;
		}
		return s;
	}
	
	public String printUltimateAttacks()
	{
		String s = "";
		int i=1;
		for(UltimateAttack attack : player.getActiveFighter().getUltimateAttacks())
		{
			s+=i+") "+attack.getName() +" ("+attack.getDamage()+")\n";
			i++;
		}
		return s;
	}
	
	public void updateSuperAttacks()
	{
		assignedSuperAttacks.clear();
		assignedSuperAttacks.add("None");
		for(SuperAttack attack : player.getActiveFighter().getSuperAttacks())
			assignedSuperAttacks.add(attack.getName());
		String[] aSA=new String[assignedSuperAttacks.size()];
		assignedSuperAttacks.toArray(aSA);
		backgroundLabel.remove(superAttackOld);
		superAttackOld=new JComboBox<String>(aSA);
		superAttackOld.setBounds(logo.getX()-120, 220,250,30);
		superAttackOld.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		superAttackOld.setBackground(Color.WHITE);
		superAttackOld.setForeground(Color.RED);
		backgroundLabel.add(superAttackOld);
	}
	
	public void updateUltimateAttacks()
	{
		assignedUltimateAttacks.clear();		
		assignedUltimateAttacks.add("None");
		for(UltimateAttack attack : player.getActiveFighter().getUltimateAttacks())
			assignedUltimateAttacks.add(attack.getName());
		String[] aUA=new String[assignedUltimateAttacks.size()];
		assignedUltimateAttacks.toArray(aUA);
		backgroundLabel.remove(ultimateAttackOld);
		ultimateAttackOld=new JComboBox<String>(aUA);
		ultimateAttackOld.setBounds(logo.getX()+logo.getWidth()/2, 220,250,30);
		ultimateAttackOld.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		ultimateAttackOld.setBackground(Color.WHITE);
		ultimateAttackOld.setForeground(Color.RED);
		backgroundLabel.add(ultimateAttackOld);
	}
	
	private void preparePanel()
	{
		setBackground(Color.WHITE);
		backgroundLabel = new JLabel(
				new ImageIcon(new ImageIcon("Images/b.jpg")
						.getImage().getScaledInstance(790, 565, Image.SCALE_SMOOTH)));
		add(backgroundLabel);
		backgroundLabel.setLayout(null);
		logo = new JLabel("Assign Attacks");
		logo.setFont(new Font("Arcade", Font.TRUETYPE_FONT, 40));
		logo.setOpaque(true);
		logo.setForeground(Color.WHITE);
		logo.setHorizontalAlignment(JLabel.CENTER);
		logo.setBackground(new Color(135,206,250,50));
		logo.setBounds(250, 0, 320, 50);
		replaceWith=new JLabel("Replace With");
		replaceWith.setFont(new Font("Arcade", Font.TRUETYPE_FONT, 40));
		replaceWith.setBounds(logo.getX()+15, 165, 300, 40);
		replaceWith.setOpaque(true);
		replaceWith.setBackground(new Color(135,206,250,50));
		replaceWith.setForeground(Color.WHITE);
		replaceWith.setHorizontalAlignment(JLabel.CENTER);
		back = new JButton("Back");
		back.setBounds(0, 0, 150, 50);
		back.setBackground(Color.WHITE);
		back.setFont(new Font("Arcade", Font.TRUETYPE_FONT, 40));
		back.setFocusPainted(false);
		back.setBorderPainted(false);
		back.setForeground(Color.WHITE);
		back.setContentAreaFilled(false);
		addSuper = new JButton("Add");
		addSuper.setBounds(170, 260, 200, 30);
		addSuper.setForeground(Color.WHITE);
		addSuper.setFont(new Font("Arcade", Font.TRUETYPE_FONT, 40));
		addSuper.setFocusPainted(false);
		addSuper.setContentAreaFilled(false);
		addSuper.setBorderPainted(false);
		addUltimate = new JButton("Add");
		addUltimate.setBounds(450, 260, 200, 30);
		addUltimate.setForeground(Color.WHITE);
		addUltimate.setContentAreaFilled(false);
		addUltimate.setBorderPainted(false);
		addUltimate.setFocusPainted(false);
		addUltimate.setFont(new Font("Arcade", Font.TRUETYPE_FONT, 40));
		addUltimate.setFocusPainted(false);
		superAttacksStatic = new JLabel("Super Attack | Damage");
		superAttacksStatic.setOpaque(false);
		superAttacksStatic.setForeground(Color.WHITE);
		superAttacksStatic.setFont(new Font("Arcade", Font.TRUETYPE_FONT, 20));
		superAttacksStatic.setBounds(logo.getX()-125, 90, 270, 25);
		superAttacksStatic.setHorizontalAlignment(JLabel.CENTER);
		ultimateAttacksStatic = new JLabel("Ultimate Attack | Damage");
		ultimateAttacksStatic.setOpaque(false);
		ultimateAttacksStatic.setForeground(Color.WHITE);
		ultimateAttacksStatic.setFont(new Font("Arcade", Font.TRUETYPE_FONT, 20));
		ultimateAttacksStatic.setBounds(logo.getX()+logo.getWidth()/2, 90, 270, 30);
		ultimateAttacksStatic.setHorizontalAlignment(JLabel.CENTER);
		superAttackNames=new ArrayList<String>();
		ultimateAttackNames=new ArrayList<String>();
		for(SuperAttack attack : player.getSuperAttacks())
			superAttackNames.add(attack.getName()+"("+attack.getDamage()+")");
		for(UltimateAttack attack : player.getUltimateAttacks())
			ultimateAttackNames.add(attack.getName()+"("+attack.getDamage()+")");
		
		String[] superEntries =new String[superAttackNames.size()];
		String[] ultimateEntries =new String[ultimateAttackNames.size()];
		superAttackNames.toArray(superEntries);
		ultimateAttackNames.toArray(ultimateEntries);
		superAttack= new JComboBox<String>(superEntries);
		superAttack.setBounds(logo.getX()-120, 120,250,30);
		superAttack.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		superAttack.setBackground(Color.WHITE);
		superAttack.setForeground(Color.RED);
		assignedSuperAttacks=new ArrayList<String>();
		assignedUltimateAttacks=new ArrayList<String>();
		assignedSuperAttacks.add("None");
		assignedUltimateAttacks.add("None");
		for(SuperAttack attack : player.getActiveFighter().getSuperAttacks())
			assignedSuperAttacks.add(attack.getName());
		for(UltimateAttack attack : player.getActiveFighter().getUltimateAttacks())
			assignedUltimateAttacks.add(attack.getName());
		
		String[] aSA=new String[assignedSuperAttacks.size()];
		String[] aUA=new String[assignedUltimateAttacks.size()];
		assignedSuperAttacks.toArray(aSA);
		assignedUltimateAttacks.toArray(aUA);
		superAttackOld= new JComboBox<String>(aSA);
		superAttackOld.setBounds(logo.getX()-120, 220,250,30);
		superAttackOld.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		superAttackOld.setBackground(Color.WHITE);
		superAttackOld.setForeground(Color.RED);
		ultimateAttack= new JComboBox<String>(ultimateEntries);
		ultimateAttack.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		ultimateAttack.setBackground(Color.WHITE);	
		ultimateAttack.setBounds(logo.getX()+logo.getWidth()/2, 120,250,30);
		ultimateAttack.setForeground(Color.RED);
		ultimateAttackOld= new JComboBox<String>(aUA);
		ultimateAttackOld.setBounds(logo.getX()+logo.getWidth()/2, 220,250,30);
		ultimateAttackOld.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		ultimateAttackOld.setBackground(Color.WHITE);
		ultimateAttackOld.setForeground(Color.RED);
		superAttacksAssigned = new JLabel("Super Attacks Assigned");
		superAttacksAssigned.setFont(new Font("Arcade", Font.TRUETYPE_FONT, 25));
		superAttacksAssigned.setBackground(new Color(135,206,250,50));
		superAttacksAssigned.setOpaque(true);
		superAttacksAssigned.setHorizontalAlignment(JLabel.CENTER);
		superAttacksAssigned.setForeground(Color.WHITE);
		ultimateAttacksAssigned = new JLabel("Ultimate Attacks Assigned");
		ultimateAttacksAssigned.setFont(new Font("Arcade", Font.TRUETYPE_FONT, 25));
		ultimateAttacksAssigned.setBackground(new Color(135,206,250,50));
		ultimateAttacksAssigned.setOpaque(true);
		ultimateAttacksAssigned.setForeground(Color.WHITE);
		ultimateAttacksAssigned.setHorizontalAlignment(JLabel.CENTER);
		superA=new JTextArea();
		superA.setFont(new Font("Arcade", Font.TRUETYPE_FONT, 20));
		superA.setEditable(false);
		superA.setForeground(Color.RED);
		superA.setOpaque(false);
		superA.setRows(4);
		superA.setColumns(1);
		superA.setFocusable(false);
		superA.setText(printSuperAttacks());
		ultimateA=new JTextArea();
		ultimateA.setFont(new Font("Arcade", Font.TRUETYPE_FONT, 20));
		ultimateA.setEditable(false);
		ultimateA.setForeground(Color.RED);
		ultimateA.setOpaque(false);
		ultimateA.setFocusable(false);
		ultimateA.setRows(2);
		ultimateA.setColumns(1);
		ultimateA.setText(printUltimateAttacks());
		attacks=new JPanel();
		attacks.setLayout(new GridLayout(2,2));
		attacks.setBounds(50, 300, 700,300);
		attacks.setBackground(new Color(135,206,250,200));
		attacks.setOpaque(true);
		attacks.add(superAttacksAssigned);
		attacks.add(ultimateAttacksAssigned);
		attacks.add(superA);
		attacks.add(ultimateA);
		backgroundLabel.add(back);
		backgroundLabel.add(addSuper);
		backgroundLabel.add(addUltimate);
		backgroundLabel.add(logo);
		backgroundLabel.add(superAttack);
		backgroundLabel.add(ultimateAttack);
		backgroundLabel.add(superAttacksStatic);
		backgroundLabel.add(ultimateAttacksStatic);
		backgroundLabel.add(superAttackOld);
		backgroundLabel.add(ultimateAttackOld);
		backgroundLabel.add(replaceWith);
		backgroundLabel.add(attacks);
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addSuper.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addUltimate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttons = new ArrayList<JButton>();
		buttons.add(back);
		buttons.add(addSuper);
		buttons.add(addUltimate);
		boxes=new ArrayList<JComboBox<String>>();
		boxes.add(ultimateAttack);
	}
}