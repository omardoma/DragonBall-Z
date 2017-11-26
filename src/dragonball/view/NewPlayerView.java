package dragonball.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class NewPlayerView extends JPanel
{
	private JLabel backgroundLabel;
	private JLabel logo;
	private JTextField enterName;
	private JLabel nameStatic;
	private JButton back;
	private JButton next;
	private ArrayList<JButton> buttons;

	public NewPlayerView()
	{
		preparePanel();
	}

	public JLabel getBackgroundLabel()
	{
		return backgroundLabel;
	}

	public JLabel getLogo()
	{
		return logo;
	}

	public JTextField getEnterName()
	{
		return enterName;
	}

	public JButton getBack()
	{
		return back;
	}

	public JButton getNext()
	{
		return next;
	}

	public ArrayList<JButton> getButtons()
	{
		return buttons;
	}

	public JLabel getNameStatic()
	{
		return nameStatic;
	}

	private void preparePanel()
	{
		setBackground(Color.WHITE);
		backgroundLabel = new JLabel(
				new ImageIcon(new ImageIcon("Images/b.jpg")
						.getImage().getScaledInstance(790, 565, Image.SCALE_SMOOTH)));
		add(backgroundLabel);
		backgroundLabel.setLayout(null);
		logo = new JLabel("Create Player");
		logo.setFont(new Font("Arcade", Font.BOLD, 40));
		logo.setForeground(Color.WHITE);
		logo.setOpaque(true);
		logo.setHorizontalAlignment(JLabel.CENTER);
		logo.setBackground(new Color(135,206,250,50));
		logo.setBounds(250, 0, 300, 50);
		backgroundLabel.add(logo);
		enterName = new JTextField();
		enterName.setBounds(420, 250, 180, 40);
		enterName.setFont(new Font("Arcade", Font.PLAIN, 30));
		enterName.setBorder(new LineBorder(Color.WHITE));
		enterName.setForeground(Color.RED);
		enterName.setBackground(Color.WHITE);
		backgroundLabel.add(enterName);
		nameStatic = new JLabel("Player Name:");
		nameStatic.setFont(new Font("Arcade", Font.BOLD, 40));
		nameStatic.setBounds(enterName.getX()-250, 255, 250, 40);
		nameStatic.setOpaque(false);
		nameStatic.setVerticalAlignment(JLabel.CENTER);
		nameStatic.setForeground(Color.WHITE);
		backgroundLabel.add(nameStatic);
		back = new JButton("Back");
		back.setBounds(0, 0, 150, 50);
		back.setContentAreaFilled(false);
		back.setFont(new Font("Arcade", Font.BOLD, 40));
		back.setFocusPainted(false);
		back.setBorderPainted(false);
		back.setForeground(Color.WHITE);
		backgroundLabel.add(back);
		next = new JButton("Next");
		next.setBounds(320, 305, 150, 50);
		next.setContentAreaFilled(false);
		next.setBorderPainted(false);
		next.setFont(new Font("Arcade", Font.BOLD, 40));
		next.setFocusPainted(false);
		next.setForeground(Color.WHITE);
		backgroundLabel.add(next);
		back.setCursor(new Cursor(Cursor.HAND_CURSOR));
		next.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttons = new ArrayList<JButton>();
		buttons.add(back);
		buttons.add(next);
	}
}
