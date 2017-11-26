package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import dragonball.model.cell.FoeCell;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.player.Player;
import dragonball.model.world.World;

@SuppressWarnings("serial")
public class WorldView extends JPanel
{
	private World world;
	private Player player;
	private JPanel mapPanel;
	private JPanel playerInfoPanel;
	private JButton upgradeFighter;
	private JButton switchFighter;
	private JButton save;
	private JButton assignAttacks;
	private JButton createFighter;
	private ImageIcon playerImage;
	private ImageIcon bossImage;
	private JLabel exploredMaps;
	private JLabel mapBackground;
	private JLabel playerName;
	private JLabel playerDragonBalls;
	private JLabel playerSenzuBeans;
	private JLabel activeFighter;
	private JLabel activeFighterAbilityPoints;
	private JLabel activeFighterLevel;
	private ArrayList<JButton> buttons;
	private JButton[][] cells;
	private boolean senzuBeanOpened;
	private boolean dragonBallOpened;
	private boolean weakFoeOpened;
	private Icon[][] cellIcons;

	public WorldView(World world, Player player)
	{
		this.world = world;
		this.player = player;
		preparePanel();
	}
	
	public ImageIcon getBossImage()
	{
		return bossImage;
	}

	public boolean isWeakFoeOpened()
	{
		return weakFoeOpened;
	}

	public void setWeakFoeOpened(boolean weakFoeOpened)
	{
		this.weakFoeOpened = weakFoeOpened;
	}

	public void setCellIcons(Icon[][] cellIcons)
	{
		this.cellIcons = cellIcons;
	}

	public Icon[][] getCellIcons()
	{
		return cellIcons;
	}

	public boolean isSenzuBeanOpened()
	{
		return senzuBeanOpened;
	}

	public boolean isDragonBallOpened()
	{
		return dragonBallOpened;
	}

	public void setSenzuBeanOpened(boolean senzuBeanOpened)
	{
		this.senzuBeanOpened = senzuBeanOpened;
	}

	public void setDragonBallOpened(boolean dragonBallOpened)
	{
		this.dragonBallOpened = dragonBallOpened;
	}

	public ImageIcon getPlayerImage()
	{
		return playerImage;
	}

	public JLabel getMapBackground()
	{
		return mapBackground;
	}

	public JButton getAssignAttacks()
	{
		return assignAttacks;
	}

	public JButton getCreateFighter()
	{
		return createFighter;
	}

	public JLabel getActiveFighter()
	{
		return activeFighter;
	}

	public JLabel getPlayerSenzuBeans()
	{
		return playerSenzuBeans;
	}

	public JButton getSave()
	{
		return save;
	}

	public JLabel getPlayerName()
	{
		return playerName;
	}

	public JLabel getPlayerDragonBalls()
	{
		return playerDragonBalls;
	}

	public JLabel getActiveFighterAbilityPoints()
	{
		return activeFighterAbilityPoints;
	}

	public JLabel getActiveFighterLevel()
	{
		return activeFighterLevel;
	}

	public JButton[][] getCells()
	{
		return cells;
	}

	public ArrayList<JButton> getButtons()
	{
		return buttons;
	}

	public void setWorld(World world)
	{
		this.world = world;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
	}

	public World getWorld()
	{
		return world;
	}

	public Player getPlayer()
	{
		return player;
	}

	public JPanel getPlayerInfoPanel()
	{
		return playerInfoPanel;
	}

	public JPanel getMapPanel()
	{
		return mapPanel;
	}

	public JButton getUpgradeFighter()
	{
		return upgradeFighter;
	}

	public JButton getSwitchFighter()
	{
		return switchFighter;
	}

	public JLabel getExploredMaps()
	{
		return exploredMaps;
	}

	private void preparePanel()
	{
		setLayout(new BorderLayout());
		setSize(800,600);
		playerInfoPanel = new JPanel();
		playerInfoPanel.setLayout(null);
		playerInfoPanel.setBackground(Color.WHITE);
		playerInfoPanel.setPreferredSize(new Dimension(getWidth(), 80));
		mapPanel = new JPanel();
		mapPanel.setBackground(Color.WHITE);
		mapBackground = new JLabel();
		mapBackground.setIcon(new ImageIcon("Images/World/Map/worldMap"+new Random().nextInt(3)+".png"));
		mapPanel.add(mapBackground);
		mapBackground.setLayout(new GridLayout(10, 10));
		cells = new JButton[world.getMap().length][world.getMap().length];
		cellIcons = new Icon[world.getMap().length][world.getMap().length];
		for (int i = 0; i < world.getMap().length; i++)
			for (int j = 0; j < world.getMap()[i].length; j++)
			{
				JButton button = new JButton();
				button.setContentAreaFilled(false);
				button.setFocusPainted(false);
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Border border = new LineBorder(Color.WHITE);
				button.setBorder(border);
				cells[i][j] = button;
				cellIcons[i][j] = button.getIcon();
				mapBackground.add(button);
			}

		if (player.getActiveFighter() instanceof Saiyan)
			playerImage = new ImageIcon("Images/World/Map/S.png");
		else
			if (player.getActiveFighter() instanceof Frieza)
				playerImage = new ImageIcon("Images/World/Map/F.png");
			else
				if (player.getActiveFighter() instanceof Namekian)
					playerImage = new ImageIcon("Images/World/Map/N.png");
				else
					if (player.getActiveFighter() instanceof Majin)
						playerImage = new ImageIcon("Images/World/Map/M.png");
					else
						playerImage = new ImageIcon("Images/World/Map/E.png");
		
		if (((FoeCell) world.getMap()[0][0]).getFoe().getName().equalsIgnoreCase("goku"))
			bossImage = new ImageIcon("Images/World/Boss/goku.png");
		else
			if (((FoeCell) world.getMap()[0][0]).getFoe().getName().equalsIgnoreCase("majin"))
				bossImage = new ImageIcon("Images/World/Boss/majin.png");
			else
				if (((FoeCell) world.getMap()[0][0]).getFoe().getName().equalsIgnoreCase("vegeta"))
					bossImage = new ImageIcon("Images/World/Boss/vegeta.png");
				else
					if (((FoeCell) world.getMap()[0][0]).getFoe().getName().equalsIgnoreCase("gohan (kid)"))
						bossImage = new ImageIcon("Images/World/Boss/gohan(kid).png");
					else
						if (((FoeCell) world.getMap()[0][0]).getFoe().getName().equalsIgnoreCase("kidbuu"))
							bossImage = new ImageIcon("Images/World/Boss/kidbuu.png");
						else
							if (((FoeCell) world.getMap()[0][0]).getFoe().getName().equalsIgnoreCase("krillin"))
								bossImage = new ImageIcon("Images/World/Boss/krillin.png");
							else
								if (((FoeCell) world.getMap()[0][0]).getFoe().getName().equalsIgnoreCase("nappa"))
									bossImage = new ImageIcon("Images/World/Boss/nappa.png");
								else
									if (((FoeCell) world.getMap()[0][0]).getFoe().getName().equalsIgnoreCase("piccolo"))
										bossImage = new ImageIcon("Images/World/Boss/piccolo.png");
									else
										if (((FoeCell) world.getMap()[0][0]).getFoe().getName()
												.equalsIgnoreCase("raditz"))
											bossImage = new ImageIcon("Images/World/Boss/raditz.png");
										else
											if (((FoeCell) world.getMap()[0][0]).getFoe().getName()
													.equalsIgnoreCase("ramon"))
												bossImage = new ImageIcon("Images/World/Boss/ramon.png");
											else
												if (((FoeCell) world.getMap()[0][0]).getFoe().getName()
														.equalsIgnoreCase("saibaman"))
													bossImage = new ImageIcon("Images/World/Boss/saibaman.png");
												else
													if (((FoeCell) world.getMap()[0][0]).getFoe().getName()
															.equalsIgnoreCase("superbuu"))
														bossImage = new ImageIcon("Images/World/Boss/superbuu.png");
													else
														if (((FoeCell) world.getMap()[0][0]).getFoe().getName()
																.equalsIgnoreCase("tien"))
															bossImage = new ImageIcon("Images/World/Boss/tien.png");
														else
															if (((FoeCell) world.getMap()[0][0]).getFoe().getName()
																	.equalsIgnoreCase("yamcha"))
																bossImage = new ImageIcon("Images/World/Boss/yamcha.png");
															else
																if (((FoeCell) world.getMap()[0][0]).getFoe().getName()
																		.equalsIgnoreCase("gohan(kid) supersaiyan"))
																	bossImage = new ImageIcon("Images/World/Boss/gohan(kid)SS.png");
																else
																	bossImage = new ImageIcon("Images/World/Boss/frieza.png");

		cells[world.getPlayerRow()][world.getPlayerColumn()].setIcon(playerImage);
		cells[world.getPlayerRow()][world.getPlayerColumn()].setBorder(new LineBorder(null,5));
		cells[0][0].setIcon(bossImage);
		cellIcons[0][0] = cells[0][0].getIcon();
		playerName = new JLabel(" Player: " + player.getName());
		playerName.setBounds(0, 0, 300, 20);
		playerName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		playerDragonBalls = new JLabel(new ImageIcon("Images/World/Info/dragonball.png"));
		playerDragonBalls.setText(": " + player.getDragonBalls() + "/7");
		playerDragonBalls.setBounds(300, 0, 100, 20);
		playerDragonBalls.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		playerSenzuBeans = new JLabel(new ImageIcon("Images/World/Info/senzubean.png"));
		playerSenzuBeans.setText(": " + player.getSenzuBeans());
		playerSenzuBeans.setBounds(200, 0, 100, 20);
		playerSenzuBeans.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		exploredMaps = new JLabel(" Explored Maps: " + player.getExploredMaps());
		exploredMaps.setBounds(0, 20, 300, 20);
		exploredMaps.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		activeFighter = new JLabel(" Active Fighter: " + player.getActiveFighter().getName() + " ("
				+ player.getActiveFighter().getClass().getSimpleName() + ")");
		activeFighter.setBounds(0, 40, 380, 20);
		activeFighter.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		activeFighterLevel = new JLabel(" Fighter Level: " + player.getActiveFighter().getLevel());
		activeFighterLevel.setBounds(0, 60, 300, 20);
		activeFighterLevel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		createFighter = new JButton("Create Fighter");
		createFighter.setBounds(485, 5, 150, 30);
		createFighter.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		createFighter.setFocusable(false);
		createFighter.setBackground(Color.WHITE);
		upgradeFighter = new JButton("Upgrade Fighter");
		upgradeFighter.setBounds(330, 40, 150, 30);
		upgradeFighter.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		upgradeFighter.setFocusable(false);
		upgradeFighter.setBackground(Color.WHITE);
		switchFighter = new JButton("Switch Fighter");
		switchFighter.setBounds(485, 40, 150, 30);
		switchFighter.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		switchFighter.setFocusable(false);
		switchFighter.setBackground(Color.WHITE);
		assignAttacks = new JButton("Assign Attacks");
		assignAttacks.setBounds(640, 5, 150, 30);
		assignAttacks.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		assignAttacks.setFocusPainted(false);
		assignAttacks.setBackground(Color.WHITE);
		save = new JButton("Save Game");
		save.setBounds(640, 40, 150, 30);
		save.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		save.setFocusPainted(false);
		save.setBackground(Color.WHITE);
		playerInfoPanel.add(upgradeFighter);
		playerInfoPanel.add(switchFighter);
		playerInfoPanel.add(exploredMaps);
		playerInfoPanel.add(playerName);
		playerInfoPanel.add(playerDragonBalls);
		playerInfoPanel.add(playerSenzuBeans);
		playerInfoPanel.add(activeFighter);
		playerInfoPanel.add(activeFighterLevel);
		playerInfoPanel.add(createFighter);
		playerInfoPanel.add(assignAttacks);
		playerInfoPanel.add(save);
		add(playerInfoPanel, BorderLayout.NORTH);
		add(mapPanel, BorderLayout.CENTER);
		createFighter.setCursor(new Cursor(Cursor.HAND_CURSOR));
		switchFighter.setCursor(new Cursor(Cursor.HAND_CURSOR));
		upgradeFighter.setCursor(new Cursor(Cursor.HAND_CURSOR));
		assignAttacks.setCursor(new Cursor(Cursor.HAND_CURSOR));
		save.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttons = new ArrayList<JButton>();
		buttons.add(createFighter);
		buttons.add(switchFighter);
		buttons.add(upgradeFighter);
		buttons.add(assignAttacks);
		buttons.add(save);
	}
}