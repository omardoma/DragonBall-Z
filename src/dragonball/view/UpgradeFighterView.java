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
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.player.Player;

@SuppressWarnings("serial")
public class UpgradeFighterView extends JPanel
{
	private Player player;
	private JPanel att;
	private JLabel backgroundLabel;
	private JLabel logo;
	private JLabel containerImage;
	private JLabel fighterImage;
	private JLabel abilityPoints;
	private JLabel hP;
	private JLabel pD;
	private JLabel bD;
	private JLabel ki;
	private JLabel stamina;
	private JLabel attributeStatic;
	private JButton back;
	private JButton upgrade;
	private ImageIcon image;
	private JComboBox<String> fighterAttributes;
	private ArrayList<JButton> buttons;
	private ArrayList<String> fighterNames;

	public UpgradeFighterView(Player player)
	{
		this.player = player;
		preparePanel();
	}

	public JLabel getFighterImage()
	{
		return fighterImage;
	}

	public ImageIcon getImage()
	{
		return image;
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

	public JLabel getAttributeStatic()
	{
		return attributeStatic;
	}

	public JLabel getAbilityPoints()
	{
		return abilityPoints;
	}

	public JPanel getAtt()
	{
		return att;
	}

	public ArrayList<String> getFighterNames()
	{
		return fighterNames;
	}

	public JLabel getBackgroundLabel()
	{
		return backgroundLabel;
	}

	public JLabel getLogo()
	{
		return logo;
	}

	public ArrayList<JButton> getButtons()
	{
		return buttons;
	}

	public JButton getBack()
	{
		return back;
	}

	public JButton getUpgrade()
	{
		return upgrade;
	}

	public JComboBox<String> getFighterAttributes()
	{
		return fighterAttributes;
	}

	public Player getPlayer()
	{
		return player;
	}

	private void preparePanel()
	{
		setBackground(Color.WHITE);
		backgroundLabel = new JLabel(
				new ImageIcon(new ImageIcon("Images/b.jpg")
						.getImage().getScaledInstance(790, 565, Image.SCALE_SMOOTH)));
		add(backgroundLabel);
		backgroundLabel.setLayout(null);
		logo = new JLabel("Upgrade Fighter");
		logo.setHorizontalAlignment(JLabel.CENTER);
		logo.setBackground(new Color(135,206,250,50));
		logo.setForeground(Color.WHITE);
		logo.setFont(new Font("Arcade", Font.BOLD, 40));
		logo.setBounds(230, 0, 330, 50);
		logo.setOpaque(true);
		attributeStatic = new JLabel("Fighter's Attribute:");
		attributeStatic.setOpaque(false);
		attributeStatic.setHorizontalAlignment(JTextField.CENTER);
		attributeStatic.setFont(new Font("Arcade", Font.BOLD, 40));
		attributeStatic.setBounds(50, 155, 400, 40);
		attributeStatic.setForeground(Color.WHITE);
		back = new JButton("Back");
		back = new JButton("Back");
		back.setBounds(0, 0, 150, 50);
		back.setFont(new Font("Arcade", Font.BOLD, 40));
		back.setFocusPainted(false);
		back.setContentAreaFilled(false);
		back.setBorderPainted(false);
		back.setForeground(Color.WHITE);
		upgrade = new JButton("Upgrade");
		upgrade.setBounds(285, 200, 250, 50);
		upgrade.setBackground(Color.WHITE);
		upgrade.setFont(new Font("Arcade", Font.BOLD, 40));
		upgrade.setFocusPainted(false);
		upgrade.setBorderPainted(false);
		upgrade.setContentAreaFilled(false);
		upgrade.setForeground(Color.WHITE);
		String[] attributes ={ "Physical Damage", "Blast Damage", "Ki", "Stamina", "Health Points" };
		fighterAttributes = new JComboBox<String>(attributes);
		fighterAttributes.setBounds(attributeStatic.getX()+400, 150, 250, 40);
		fighterAttributes.setFont(new Font("Arcade", Font.PLAIN, 25));
		fighterAttributes.setBackground(Color.WHITE);
		fighterAttributes.setForeground(Color.RED);
		abilityPoints = new JLabel("Fighter's Ability Points: " + player.getActiveFighter().getAbilityPoints());
		abilityPoints.setBounds((800-500)/2, 275, 500, 50);
		abilityPoints.setFont(new Font("Arcade", Font.BOLD, 25));
		abilityPoints.setBackground(new Color(135,206,250,200));
		abilityPoints.setHorizontalAlignment(JLabel.CENTER);
		abilityPoints.setOpaque(true);
		abilityPoints.setForeground(Color.WHITE);
		if(player.getActiveFighter() instanceof Saiyan)
			image=new ImageIcon("Images/Navigation/S.png");
		else
			if(player.getActiveFighter() instanceof Earthling)
				image=new ImageIcon("Images/Navigation/E.png");
			else
				if(player.getActiveFighter() instanceof Namekian)
					image=new ImageIcon("Images/Navigation/N.png");
				else
					if(player.getActiveFighter() instanceof Majin)
						image=new ImageIcon("Images/Navigation/M.png");
						else
							image=new ImageIcon("Images/Navigation/F.png");
		
		fighterImage=new JLabel(image);
		att=new JPanel();
		att.setBounds(abilityPoints.getX(), abilityPoints.getY()+abilityPoints.getHeight(), 500, 200);
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
		containerImage=new JLabel();
		containerImage.setLayout(new GridLayout(5,1));
		containerImage.add(pD);
		containerImage.add(bD);
		containerImage.add(ki);
		containerImage.add(stamina);
		containerImage.add(hP);
		att.add(fighterImage);
		att.add(containerImage);
		backgroundLabel.add(fighterAttributes);
		backgroundLabel.add(back);
		backgroundLabel.add(upgrade);
		backgroundLabel.add(att);
		backgroundLabel.add(logo);
		backgroundLabel.add(abilityPoints);
		backgroundLabel.add(attributeStatic);
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		upgrade.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttons = new ArrayList<JButton>();
		buttons.add(back);
		buttons.add(upgrade);
	}
}