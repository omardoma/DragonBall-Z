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
import javax.swing.JTextField;

import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.player.Player;

@SuppressWarnings("serial")
public class SwitchFighterView extends JPanel
{
	private Player player;
	private JPanel att;
	private JLabel backgroundLabel;
	private JLabel logo;
	private JLabel fighter;
	private JLabel fighterImage;
	private JLabel containerImage;
	private JLabel hP;
	private JLabel pD;
	private JLabel bD;
	private JLabel ki;
	private JLabel stamina;
	private ImageIcon image;
	private JComboBox<String> chooseFighter;
	private JButton choose;
	private JButton back;
	private ArrayList<JButton> buttons;
	private ArrayList<String> fighterNames;

	public SwitchFighterView(Player player)
	{
		this.player = player;
		preparePanel();
	}

	public ImageIcon getImage()
	{
		return image;
	}

	public JLabel getContainerImage()
	{
		return containerImage;
	}

	public JPanel getAtt()
	{
		return att;
	}

	public JLabel getFighterImage()
	{
		return fighterImage;
	}

	public JLabel gethP()
	{
		return hP;
	}

	public JLabel getpD()
	{
		return pD;
	}

	public JLabel getbD()
	{
		return bD;
	}

	public JLabel getKi()
	{
		return ki;
	}

	public JLabel getStamina()
	{
		return stamina;
	}

	public ArrayList<String> getFighterNames()
	{
		return fighterNames;
	}

	public JLabel getLogo()
	{
		return logo;
	}

	public JLabel getFighter()
	{
		return fighter;
	}

	public JLabel getBackgroundLabel()
	{
		return backgroundLabel;
	}

	public ArrayList<JButton> getButtons()
	{
		return buttons;
	}

	public Player getPlayer()
	{
		return player;
	}

	public JComboBox<String> getChooseFighter()
	{
		return chooseFighter;
	}

	public JButton getChoose()
	{
		return choose;
	}

	public JButton getBack()
	{
		return back;
	}
	
	public void updateFighterImage(Fighter f)
	{
		if(f instanceof Saiyan)
			image=new ImageIcon("Images/Navigation/S.png");
		else
			if(f instanceof Earthling)
				image=new ImageIcon("Images/Navigation/E.png");
			else
				if(f instanceof Namekian)
					image=new ImageIcon("Images/Navigation/N.png");
				else
					if(f instanceof Majin)
						image=new ImageIcon("Images/Navigation/M.png");
						else
							image=new ImageIcon("Images/Navigation/F.png");
	}

	private void preparePanel()
	{
		setBackground(Color.WHITE);
		backgroundLabel = new JLabel(
				new ImageIcon(new ImageIcon("Images/b.jpg")
						.getImage().getScaledInstance(790, 565, Image.SCALE_SMOOTH)));
		add(backgroundLabel);
		backgroundLabel.setLayout(null);
		fighterNames = new ArrayList<String>();
		for (Fighter f : player.getFighters())
			fighterNames.add(f.getName());
		
		String[] fighters = new String[fighterNames.size()];
		fighterNames.toArray(fighters);
		logo = new JLabel("Switch Fighter");
		logo.setOpaque(true);
		logo.setHorizontalAlignment(JLabel.CENTER);
		logo.setBackground(new Color(135,206,250,50));
		logo.setFont(new Font("Arcade", Font.BOLD, 40));
		logo.setForeground(Color.WHITE);
		logo.setBounds(250, 0, 300, 50);
		backgroundLabel.add(logo);
		fighter = new JLabel("Fighter:");
		fighter.setOpaque(false);
		fighter.setFont(new Font("Arcade", Font.BOLD, 40));
		fighter.setHorizontalAlignment(JTextField.CENTER);
		fighter.setForeground(Color.WHITE);
		fighter.setBounds(150, 145, 200, 40);
		backgroundLabel.add(fighter);
		chooseFighter = new JComboBox<String>(fighters);
		chooseFighter.setBounds(fighter.getX()+fighter.getWidth(), 140, 300, 40);
		chooseFighter.setFont(new Font("Arcade", Font.PLAIN, 25));
		chooseFighter.setForeground(Color.RED);
		chooseFighter.setBackground(Color.WHITE);
		backgroundLabel.add(chooseFighter);
		back = new JButton("Back");
		back.setBounds(0, 0, 150, 50);
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Arcade", Font.BOLD, 40));
		back.setFocusPainted(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		backgroundLabel.add(back);
		choose = new JButton("Choose");
		choose.setBounds(logo.getX(), 185, 250, 50);
		choose.setForeground(Color.WHITE);
		choose.setFocusPainted(false);
		choose.setBorderPainted(false);
		choose.setContentAreaFilled(false);
		choose.setFont(new Font("Arcade", Font.BOLD, 40));
		backgroundLabel.add(choose);
		att=new JPanel();
		att.setBounds(150, 250, 500,200);
		att.setBackground(new Color(135,206,250,200));
		att.setOpaque(true);
		att.setLayout(new GridLayout(1,2));
		hP=new JLabel(new ImageIcon("Images/Navigation/hp.png"));
		hP.setText(": "+player.getActiveFighter().getMaxHealthPoints());
		hP.setOpaque(false);
		hP.setForeground(Color.RED);
		hP.setFont(new Font("Arcade", Font.PLAIN, 30));
		pD=new JLabel(new ImageIcon("Images/Navigation/physical.png"));
		pD.setText(": "+player.getActiveFighter().getPhysicalDamage());
		pD.setOpaque(false);
		pD.setForeground(Color.RED);
		pD.setFont(new Font("Arcade", Font.PLAIN, 30));
		bD=new JLabel(new ImageIcon("Images/Navigation/blast.png"));
		bD.setText(": "+player.getActiveFighter().getBlastDamage());
		bD.setOpaque(false);
		bD.setForeground(Color.RED);
		bD.setFont(new Font("Arcade", Font.PLAIN, 30));
		stamina=new JLabel(new ImageIcon("Images/Navigation/stamina.png"));
		stamina.setText(": "+player.getActiveFighter().getMaxStamina());
		stamina.setOpaque(false);
		stamina.setForeground(Color.RED);
		stamina.setFont(new Font("Arcade", Font.PLAIN, 30));
		ki=new JLabel(new ImageIcon("Images/Navigation/ki.png"));
		ki.setText(": "+player.getActiveFighter().getMaxKi());
		ki.setOpaque(false);
		ki.setForeground(Color.RED);
		ki.setFont(new Font("Arcade", Font.PLAIN, 30));
		updateFighterImage(player.getActiveFighter());
		fighterImage=new JLabel(image);
		containerImage=new JLabel();
		containerImage.setLayout(new GridLayout(5,1));
		containerImage.add(pD);
		containerImage.add(bD);
		containerImage.add(ki);
		containerImage.add(stamina);
		containerImage.add(hP);
		att.add(fighterImage);
		att.add(containerImage);
		backgroundLabel.add(att);
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		choose.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttons = new ArrayList<JButton>();
		buttons.add(back);
		buttons.add(choose);
	}
}
