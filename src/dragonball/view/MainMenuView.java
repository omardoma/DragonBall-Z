package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainMenuView extends JPanel
{
	private JPanel logoPanel;
	private JPanel restPanel;
	private JLabel backgroundLabel;
	private JLabel logo;
	private JButton newGame;
	private JButton resume;
	private JButton quit;
	private ArrayList<JButton> buttons;

	public MainMenuView()
	{
		preparePanel();
	}

	public JPanel getRestPanel()
	{
		return restPanel;
	}

	public JPanel getLogoPanel()
	{
		return logoPanel;
	}

	public JLabel getLogo()
	{
		return logo;
	}

	public JLabel getBackgroundLabel()
	{
		return backgroundLabel;
	}

	public JButton getNewGame()
	{
		return newGame;
	}

	public JButton getResume()
	{
		return resume;
	}

	public JButton getQuit()
	{
		return quit;
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
		logoPanel = new JPanel();
		logoPanel.setOpaque(false);
		logo = new JLabel(new ImageIcon(new ImageIcon("Images/logo.png")
				.getImage().getScaledInstance(600, 150, Image.SCALE_SMOOTH)));
		logoPanel.add(logo);
		backgroundLabel.add(logoPanel, BorderLayout.NORTH);
		restPanel = new JPanel();
		restPanel.setLayout(new GridBagLayout());
		restPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		newGame = new JButton("New Game");
		resume = new JButton("Resume");
		quit = new JButton("Quit");
		newGame.setFont(new Font("Arcade", Font.BOLD, 50));
		newGame.setForeground(Color.RED);
		newGame.setBorderPainted(false);
		newGame.setContentAreaFilled(false);
		newGame.setFocusPainted(false);
		resume.setFont(new Font("Arcade", Font.BOLD, 50));
		resume.setForeground(Color.RED);
		resume.setBorderPainted(false);
		resume.setFocusPainted(false);
		resume.setContentAreaFilled(false);
		quit.setFont(new Font("Arcade", Font.BOLD, 50));
		quit.setForeground(Color.RED);
		quit.setBorderPainted(false);
		quit.setFocusPainted(false);
		quit.setContentAreaFilled(false);
		gbc.ipadx = 50;
		gbc.ipady = 20;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 0;
		restPanel.add(resume, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		restPanel.add(newGame, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		restPanel.add(quit, gbc);
		backgroundLabel.add(restPanel, BorderLayout.CENTER);
		newGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
		resume.setCursor(new Cursor(Cursor.HAND_CURSOR));
		quit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttons = new ArrayList<JButton>();
		buttons.add(newGame);
		buttons.add(resume);
		buttons.add(quit);
	}
}