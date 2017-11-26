package dragonball.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;

@SuppressWarnings("serial")
public class DragonView extends JPanel
{
	private Dragon dragon;
	private JLabel backgroundLabel;
	private JLabel logo;
	private JButton choose;
	private JComboBox<String> chooseWish;
	private JLabel wishStatic;
	private ArrayList<JButton> buttons;
	private ArrayList<String> wishesNames;

	public DragonView(Dragon dragon)
	{
		this.dragon = dragon;
		preparePanel();
	}

	public Dragon getDragon()
	{
		return dragon;
	}

	public JLabel getBackgroundLabel()
	{
		return backgroundLabel;
	}

	public JLabel getLogo()
	{
		return logo;
	}

	public JButton getChoose()
	{
		return choose;
	}

	public JComboBox<String> getChooseWish()
	{
		return chooseWish;
	}

	public JLabel getWishStatic()
	{
		return wishStatic;
	}

	public ArrayList<JButton> getButtons()
	{
		return buttons;
	}

	public ArrayList<String> getWishesNames()
	{
		return wishesNames;
	}

	private void preparePanel()
	{
		setBackground(Color.WHITE);
		backgroundLabel = new JLabel(new ImageIcon("Images/Dragon/dragon.gif"));
		add(backgroundLabel);
		backgroundLabel.setLayout(null);
		logo = new JLabel("Choose Your Wish");
		logo.setOpaque(true);
		logo.setHorizontalAlignment(JLabel.CENTER);
		logo.setBackground(new Color(135,206,250,30));
		logo.setForeground(Color.WHITE);
		logo.setFont(new Font("Arcade", Font.BOLD, 40));
		logo.setBounds(200, 0, 400, 50);
		wishesNames=new ArrayList<String>();
		for (DragonWish wish : dragon.getWishes())
			wishesNames.add(wish.getType().toString().toLowerCase().replace('_', ' '));
		wishStatic = new JLabel("Wish:");
		//wishStatic.setOpaque(false);
		wishStatic.setFont(new Font("Arcade", Font.BOLD, 40));
		wishStatic.setHorizontalAlignment(JTextField.CENTER);
		wishStatic.setBounds(200, 295, 130, 40);
		wishStatic.setForeground(Color.WHITE);
		wishStatic.setBackground(new Color(135,206,250,30));
		String[] wishes = new String[wishesNames.size()];
		wishesNames.toArray(wishes);
		choose=new JButton("Choose");
		choose.setBounds(320, 340, 180, 50);
		choose.setFocusPainted(false);
		choose.setForeground(Color.WHITE);
		choose.setContentAreaFilled(false);
		choose.setBorderPainted(false);
		choose.setFont(new Font("Arcade", Font.BOLD, 40));
		chooseWish = new JComboBox<String>(wishes);
		chooseWish.setBounds(wishStatic.getX()+130, 290, 200, 40);
		chooseWish.setFont(new Font("Arcade", Font.TRUETYPE_FONT, 25));
		chooseWish.setBackground(Color.WHITE);
		chooseWish.setForeground(Color.RED);
		backgroundLabel.add(chooseWish);
		backgroundLabel.add(logo);
		backgroundLabel.add(choose);
		backgroundLabel.add(wishStatic);
		choose.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttons = new ArrayList<JButton>();
		buttons.add(choose);
	}
}