//TO-DO: Music, GIFs.
package dragonball.controller;

import  java.io.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import dragonball.model.attack.MaximumCharge;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;
import dragonball.view.AssignAttacksView;
import dragonball.view.BattleView;
import dragonball.view.SwitchFighterView;
import dragonball.view.DragonView;
import dragonball.view.GameView;
import dragonball.view.NewFighterView;
import dragonball.view.UpgradeFighterView;
import dragonball.view.WorldView;

@SuppressWarnings("serial")
public class GameGUI implements ActionListener, KeyListener, GameListener, ItemListener
{
	private Game game;
	private GameView view;
	private String saveLocation;

	public GameGUI() throws MissingFieldException, UnknownAttackTypeException
	{
		game = new Game();
		game.setListener(this);
		game.getPlayer().setDragonBalls(6);
		saveLocation = "lastSavedGame";
		view=new GameView();
		view.setContentPane(new JLabel(new ImageIcon("Images/intro.gif")));
		view.setVisible(true);
		addListenerMainPanel();
		addListenerNewPlayerPanel();
		new Timer().schedule(new TimerTask()
		{
			public void run()
			{
				view.setContentPane(view.getMainPanel());
				refreshView();
			}
		}, 4000);
		view.getContentPane().requestFocusInWindow();
	}

	public void itemStateChanged(ItemEvent e)
	{
		if (view.getCurrentPanel() == view.getNewFighterPanel())
		{
			if (e.getSource() == view.getNewFighterPanel().getChooseRace())
			{
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					view.getNewFighterPanel().requestFocusInWindow();
					view.getNewFighterPanel().getS().setBorder(null);
					view.getNewFighterPanel().getE().setBorder(null);
					view.getNewFighterPanel().getM().setBorder(null);
					view.getNewFighterPanel().getF().setBorder(null);
					view.getNewFighterPanel().getN().setBorder(null);
					if (((String) e.getItem()).equalsIgnoreCase("Saiyan"))
					{
						view.getNewFighterPanel().getS().setBorder(new LineBorder(Color.RED, 8));
						view.getNewFighterPanel().getRaceProperties().setText("Physical Damage: " +100+"      Blast Damage: "+150+"      Max Ki: "+5+ "      Max Stamina: "+3+"     Max HP: "+1000);
					}
					else
						if (((String) e.getItem()).equalsIgnoreCase("Earthling"))
						{
							view.getNewFighterPanel().getE().setBorder(new LineBorder(Color.RED, 8));
							view.getNewFighterPanel().getRaceProperties().setText("Physical Damage: " +50+"      Blast Damage: "+50+"      Max Ki: "+4+ "      Max Stamina: "+4+"      Max HP: "+1250);
						}
						else
							if (((String) e.getItem()).equalsIgnoreCase("Namekian"))
							{
								view.getNewFighterPanel().getN().setBorder(new LineBorder(Color.RED, 8));
								view.getNewFighterPanel().getRaceProperties().setText("Physical Damage: " +50+"      Blast Damage: "+0+"      Max Ki: "+3+ "      Max Stamina: "+5+"      Max HP: "+1350);
							}
							else
								if (((String) e.getItem()).equalsIgnoreCase("Majin"))
								{
									view.getNewFighterPanel().getM().setBorder(new LineBorder(Color.RED, 8));
									view.getNewFighterPanel().getRaceProperties().setText("Physical Damage: " +50+"      Blast Damage: "+50+"      Max Ki: "+3+ "      Max Stamina: "+6+"      Max HP: "+1500);
								}
								else
									if (((String) e.getItem()).equalsIgnoreCase("Frieza"))
									{
										view.getNewFighterPanel().getF().setBorder(new LineBorder(Color.RED, 8));
										view.getNewFighterPanel().getRaceProperties().setText("Physical Damage: " +75+"      Blast Damage: "+75+"     Max Ki: "+4+ "    Max Stamina: "+4+"      Max HP: "+1100);
									}
									else
									{
										view.getNewFighterPanel().getS().setBorder(null);
										view.getNewFighterPanel().getE().setBorder(null);
										view.getNewFighterPanel().getM().setBorder(null);
										view.getNewFighterPanel().getF().setBorder(null);
										view.getNewFighterPanel().getN().setBorder(null);
										view.getNewFighterPanel().getRaceProperties().setText("Physical Damage: " +"      Blast Damage: "+"      Max Ki: "+ "      Max Stamina: "+"      Max HP: ");
									}
										
				}
			}
		}
		else
			if (view.getCurrentPanel() == view.getSwitchFighterPanel())
			{
				if (e.getSource() == view.getSwitchFighterPanel().getChooseFighter())
				{
					if (e.getStateChange() == ItemEvent.SELECTED)
					{
						Fighter fighter=null;
						for(Fighter f : game.getPlayer().getFighters())
						{
							if((f.getName().equalsIgnoreCase(((String) e.getItem()))))
							{
								view.getSwitchFighterPanel().updateFighterImage(f);
								fighter=f;
								break;
							}	
						}
						view.getSwitchFighterPanel().getFighterImage().setIcon(view.getSwitchFighterPanel().getImage());
						view.getSwitchFighterPanel().getKi().setText(": "+fighter.getMaxKi());
						view.getSwitchFighterPanel().getStamina().setText(": "+fighter.getMaxStamina());
						view.getSwitchFighterPanel().gethP().setText(": "+fighter.getMaxHealthPoints());
						view.getSwitchFighterPanel().getpD().setText(": "+fighter.getPhysicalDamage());
						view.getSwitchFighterPanel().getbD().setText(": "+fighter.getBlastDamage());	
					}
				}

			}
	}

	public void keyTyped(KeyEvent e)
	{

	}

	public void keyPressed(KeyEvent e)
	{
		if (view.getCurrentPanel() == view.getWorldPanel())
		{
			if (view.getWorldPanel().isSenzuBeanOpened())
			{
				view.getWorldPanel().getCells()[game.getWorld()
												.getPlayerRow()][game.getWorld().getPlayerColumn()]
														.setIcon(new ImageIcon(
																"Images/World/Map/senzubean.png"));
				view.getWorldPanel().getCellIcons()[game.getWorld().getPlayerRow()][game.getWorld()
						.getPlayerColumn()] = view.getWorldPanel().getCells()[game.getWorld().getPlayerRow()][game
								.getWorld().getPlayerColumn()].getIcon();
				view.getWorldPanel().setSenzuBeanOpened(false);
			}
			else
				if (view.getWorldPanel().isDragonBallOpened())
				{
					view.getWorldPanel().getCells()[game.getWorld()
													.getPlayerRow()][game.getWorld().getPlayerColumn()]
															.setIcon(new ImageIcon(
																	"Images/World/Map/dragonball.png"));
					view.getWorldPanel().getCellIcons()[game.getWorld().getPlayerRow()][game.getWorld()
							.getPlayerColumn()] = view.getWorldPanel().getCells()[game.getWorld().getPlayerRow()][game
									.getWorld().getPlayerColumn()].getIcon();
					view.getWorldPanel().setDragonBallOpened(false);
				}
				else
					if (view.getWorldPanel().isWeakFoeOpened())
					{

						view.getWorldPanel().getCells()[game.getWorld().getPlayerRow()][game.getWorld()
								.getPlayerColumn()]
										.setIcon(new ImageIcon(view.getBattlePanel().getFoePngImage().getImage()
												.getScaledInstance(60, 45, Image.SCALE_SMOOTH)));
						view.getWorldPanel().getCellIcons()[game.getWorld().getPlayerRow()][game.getWorld()
								.getPlayerColumn()] = view.getWorldPanel().getCells()[game.getWorld()
										.getPlayerRow()][game.getWorld().getPlayerColumn()].getIcon();
						view.getWorldPanel().setWeakFoeOpened(false);
					}
					else
						view.getWorldPanel().getCells()[game.getWorld().getPlayerRow()][game.getWorld()
								.getPlayerColumn()].setIcon(
										view.getWorldPanel().getCellIcons()[game.getWorld().getPlayerRow()][game
												.getWorld().getPlayerColumn()]);

			view.getWorldPanel().getCells()[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()]
					.setBorder(new LineBorder(Color.WHITE));
			if (e.getKeyCode() == KeyEvent.VK_UP)
			{
				try
				{
					game.getWorld().moveUp();
				}
				catch (MapIndexOutOfBoundsException e1)
				{
					view.getWorldPanel().getCells()[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()]
							.setIcon(view.getWorldPanel().getCellIcons()[game.getWorld().getPlayerRow()][game.getWorld()
									.getPlayerColumn()]);
				}
			}
			else
				if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					try
					{
						game.getWorld().moveRight();
					}
					catch (MapIndexOutOfBoundsException e1)
					{
						view.getWorldPanel().getCells()[game.getWorld().getPlayerRow()][game.getWorld()
								.getPlayerColumn()].setIcon(
										view.getWorldPanel().getCellIcons()[game.getWorld().getPlayerRow()][game
												.getWorld().getPlayerColumn()]);
					}
				}
				else
					if (e.getKeyCode() == KeyEvent.VK_LEFT)
					{
						try
						{
							game.getWorld().moveLeft();
						}
						catch (MapIndexOutOfBoundsException e1)
						{
							view.getWorldPanel().getCells()[game.getWorld().getPlayerRow()][game.getWorld()
									.getPlayerColumn()].setIcon(
											view.getWorldPanel().getCellIcons()[game.getWorld().getPlayerRow()][game
													.getWorld().getPlayerColumn()]);
						}
					}
					else
						if (e.getKeyCode() == KeyEvent.VK_DOWN)
						{
							try
							{
								game.getWorld().moveDown();
							}
							catch (MapIndexOutOfBoundsException e1)
							{
								view.getWorldPanel().getCells()[game.getWorld().getPlayerRow()][game.getWorld()
										.getPlayerColumn()].setIcon(
												view.getWorldPanel().getCellIcons()[game.getWorld().getPlayerRow()][game
														.getWorld().getPlayerColumn()]);
							}
						}
			view.getWorldPanel().setWorld(game.getWorld());
			view.getWorldPanel().getCells()[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()]
					.setIcon(view.getWorldPanel().getPlayerImage());
			view.getWorldPanel().getCells()[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()]
					.setBorder(new LineBorder(null, 5));
		}
		else
			if (view.getCurrentPanel() == view.getNewPlayerPanel())
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					view.getNewPlayerPanel().getNext().doClick();
				else
					if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
						view.getNewPlayerPanel().getBack().doClick();
			}
			else
				if (view.getCurrentPanel() == view.getNewFighterPanel())
				{
					if (e.getKeyCode() == KeyEvent.VK_ENTER)
						view.getNewFighterPanel().getCreateFighter().doClick();
					else
						if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
							view.getNewFighterPanel().getBack().doClick();
				}
				else
					if (view.getCurrentPanel() == view.getNewFighterPanel())
					{
						if (e.getKeyCode() == KeyEvent.VK_ENTER)
							view.getNewFighterPanel().getCreateFighter().doClick();
						else
							if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
								view.getNewFighterPanel().getBack().doClick();
					}
					else
						if (view.getCurrentPanel() == view.getSwitchFighterPanel())
						{
							if (e.getKeyCode() == KeyEvent.VK_ENTER)
								view.getSwitchFighterPanel().getChoose().doClick();
							else
								if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
									view.getSwitchFighterPanel().getBack().doClick();
						}
						else
							if (view.getCurrentPanel() == view.getUpgradeFighterPanel())
							{
								if (e.getKeyCode() == KeyEvent.VK_ENTER)
									view.getUpgradeFighterPanel().getUpgrade().doClick();

								else
									if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
										view.getUpgradeFighterPanel().getBack().doClick();
							}
							else
								if (view.getCurrentPanel() == view.getAssignAttacksPanel())
								{
									if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
										view.getAssignAttacksPanel().getBack().doClick();
								}
								else
									if (view.getCurrentPanel() == view.getBattlePanel())
									{
										if (e.getKeyCode() == KeyEvent.VK_A)
											view.getBattlePanel().getAttack().doClick();
										else
											if (e.getKeyCode() == KeyEvent.VK_B)
												view.getBattlePanel().getBlock().doClick();
											else
												if (e.getKeyCode() == KeyEvent.VK_U)
													view.getBattlePanel().getUse().doClick();
									}
	}

	public void keyReleased(KeyEvent e)
	{

	}

	public void actionPerformed(ActionEvent e)
	{
		if (view.getCurrentPanel() == view.getMainPanel())
		{
			if (e.getSource() == view.getMainPanel().getNewGame())
			{
				view.setContentPane(view.getNewPlayerPanel());
				view.setCurrentPanel(view.getNewPlayerPanel());
			}
			else
				if (e.getSource() == view.getMainPanel().getResume())
				{
					try
					{
						game.load(saveLocation);
						game.setListener(this);
						view.setWorldPanel(new WorldView(game.getWorld(), game.getPlayer()));
						addListenerWorldPanel();
						reloadWorldIcons(game.getIcons());
						view.getWorldPanel().getMapBackground().setIcon(game.getBackground());
						view.setSwitchFighterPanel(new SwitchFighterView(game.getPlayer()));
						view.getWorldPanel().getCells()[game.getWorld().getPlayerRow()][game.getWorld()
								.getPlayerColumn()].setBorder(new LineBorder(null, 5));
						addListenerSwitchFighterPanel();
						view.setCurrentPanel(view.getWorldPanel());
						view.setContentPane(view.getWorldPanel());
					}
					catch (IOException e1)
					{
						int reply = JOptionPane.showOptionDialog(view,
								"No saved game found, Do you want to start a new game?", "Error",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						if (reply == JOptionPane.YES_OPTION)
						{
							view.setContentPane(view.getNewPlayerPanel());
							view.setCurrentPanel(view.getNewPlayerPanel());
						}
					}
					catch (ClassNotFoundException e1)
					{
						// Do Nothing
					}
				}
				else
				{
					int reply = JOptionPane.showOptionDialog(view, "Are you sure you want to quit?", "Exit Dialog",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (reply == JOptionPane.YES_OPTION)
						System.exit(0);
				}
		}
		else
			if (view.getCurrentPanel() == view.getNewPlayerPanel())
			{
				String name = view.getNewPlayerPanel().getEnterName().getText();
				if (e.getSource() == view.getNewPlayerPanel().getNext())
				{
					if (name.trim().isEmpty())
						JOptionPane.showMessageDialog(view, "Please enter a player name!", "Error",
								JOptionPane.ERROR_MESSAGE);
					else
					{
						game.getPlayer().setName(name);
						view.setNewFighterPanel(new NewFighterView(game.getPlayer()));
						addListenerNewFighterPanel();
						view.setContentPane(view.getNewFighterPanel());
						view.setCurrentPanel(view.getNewFighterPanel());
					}
				}
				else
					if (e.getSource() == view.getNewPlayerPanel().getBack())
					{
						game.getPlayer().setName("");
						view.setNewFighterPanel(null);
						view.getNewPlayerPanel().getEnterName().setText("");
						view.setContentPane(view.getMainPanel());
						view.setCurrentPanel(view.getMainPanel());
					}
			}
			else
				if (view.getCurrentPanel() == view.getNewFighterPanel())
				{
					if (e.getSource() == view.getNewFighterPanel().getBack())
					{
						if (view.getWorldPanel() == null)
						{
							game.getPlayer().setName("");
							view.setContentPane(view.getNewPlayerPanel());
							view.setCurrentPanel(view.getNewPlayerPanel());
						}
						else
						{
							view.setContentPane(view.getWorldPanel());
							view.setCurrentPanel(view.getWorldPanel());
						}
					}
					else
						if (e.getSource() == view.getNewFighterPanel().getCreateFighter())
						{
							String name = view.getNewFighterPanel().getEnterName().getText();
							if (name.trim().isEmpty())
								JOptionPane.showMessageDialog(view, "Please enter a fighter name!", "Error",
										JOptionPane.ERROR_MESSAGE);
							else
								if (view.getWorldPanel() == null)
								{
									if (!((String) view.getNewFighterPanel().getChooseRace().getSelectedItem())
											.equalsIgnoreCase(" "))
									{
										game.getPlayer().createFighter(
												((String) view.getNewFighterPanel().getChooseRace().getSelectedItem())
														.toUpperCase().charAt(0),
												name);
										view.setWorldPanel(new WorldView(game.getWorld(), game.getPlayer()));
										addListenerWorldPanel();
										view.setSwitchFighterPanel(new SwitchFighterView(game.getPlayer()));
										addListenerSwitchFighterPanel();
										view.setContentPane(view.getWorldPanel());
										view.setCurrentPanel(view.getWorldPanel());
									}
									else
										JOptionPane.showMessageDialog(view, "Please choose a fighter Race!", "Error",
												JOptionPane.ERROR_MESSAGE);
								}
								else
									if (view.getSwitchFighterPanel().getFighterNames().contains(name) || view.getSwitchFighterPanel().getFighterNames().contains(name +"  <- Active") )
										JOptionPane.showMessageDialog(view,
												"You already have a fighter with the Name: " + name
														+ ", Please choose another name!",
												"Error", JOptionPane.ERROR_MESSAGE);
									else
									{
										if (!((String) view.getNewFighterPanel().getChooseRace().getSelectedItem())
												.equalsIgnoreCase(" "))
										{
											game.getPlayer().createFighter(((String) view.getNewFighterPanel()
													.getChooseRace().getSelectedItem()).toUpperCase().charAt(0), name);
											Icon[][] tmp = view.getWorldPanel().getCellIcons();
											Icon mapBackground = view.getWorldPanel().getMapBackground().getIcon();
											view.setWorldPanel(new WorldView(game.getWorld(), game.getPlayer()));
											addListenerWorldPanel();
											view.setSwitchFighterPanel(new SwitchFighterView(game.getPlayer()));
											addListenerSwitchFighterPanel();
											int reply = JOptionPane.showOptionDialog(view,
													"Do you want to switch the active fighter to the new created fighter?",
													"Switch Dialog", JOptionPane.YES_NO_OPTION,
													JOptionPane.QUESTION_MESSAGE, null, null, null);
											if (reply == JOptionPane.YES_OPTION)
											{
												game.getPlayer().setActiveFighter(game.getPlayer().getFighters()
														.get(game.getPlayer().getFighters().size() - 1));
												view.setWorldPanel(new WorldView(game.getWorld(), game.getPlayer()));
												addListenerWorldPanel();
											}
											reloadWorldIcons(tmp);
											view.getWorldPanel().getMapBackground().setIcon(mapBackground);
											view.setContentPane(view.getWorldPanel());
											view.setCurrentPanel(view.getWorldPanel());
										}
										else
											JOptionPane.showMessageDialog(view, "Please choose a fighter Race!",
													"Error", JOptionPane.ERROR_MESSAGE);
									}
						}
				}
				else
					if (view.getCurrentPanel() == view.getWorldPanel())
					{
						if (e.getSource() == view.getWorldPanel().getUpgradeFighter())
						{
							view.setUpgradeFighterPanel(new UpgradeFighterView(game.getPlayer()));
							addListenerUpgradeFighterPanel();
							view.setContentPane(view.getUpgradeFighterPanel());
							view.setCurrentPanel(view.getUpgradeFighterPanel());
						}
						else
							if (e.getSource() == view.getWorldPanel().getCreateFighter())
							{
								view.setNewFighterPanel(new NewFighterView(game.getPlayer()));
								addListenerNewFighterPanel();
								view.setContentPane(view.getNewFighterPanel());
								view.setCurrentPanel(view.getNewFighterPanel());
							}
							else
								if (e.getSource() == view.getWorldPanel().getSwitchFighter())
								{
									view.setSwitchFighterPanel(new SwitchFighterView(game.getPlayer()));
									addListenerSwitchFighterPanel();
									view.setContentPane(view.getSwitchFighterPanel());
									view.setCurrentPanel(view.getSwitchFighterPanel());
								}
								else
									if (e.getSource() == view.getWorldPanel().getSave())
									{
										try
										{
											game.setIcons(view.getWorldPanel().getCellIcons());
											game.setBackground(view.getWorldPanel().getMapBackground().getIcon());
											game.save(saveLocation);
											JOptionPane.showMessageDialog(view, "Game is saved successfully !",
													"Save Dialog", JOptionPane.INFORMATION_MESSAGE);
										}
										catch (IOException e1)
										{
											JOptionPane.showMessageDialog(view,
													"An unknown error has occured, cannot save current game!",
													"Save Dialog", JOptionPane.ERROR_MESSAGE);
										}
									}
									else
										if (e.getSource() == view.getWorldPanel().getAssignAttacks())
										{
											view.setAssignAttacksPanel(new AssignAttacksView(game.getPlayer()));
											addListenerAssignAttacksPanel();
											view.setContentPane(view.getAssignAttacksPanel());
											view.setCurrentPanel(view.getAssignAttacksPanel());
										}
										else
											for (int i = 0; i < game.getWorld().getMap().length; i++)
												for (int j = 0; j < game.getWorld().getMap()[i].length; j++)
													if (e.getSource() == view.getWorldPanel().getCells()[i][j])
													{
														if (view.getWorldPanel().isSenzuBeanOpened())
														{
															view.getWorldPanel().getCells()[game.getWorld()
																	.getPlayerRow()][game.getWorld().getPlayerColumn()]
																			.setIcon(new ImageIcon(
																					"Images/World/Map/senzubean.png"));
															view.getWorldPanel().getCellIcons()[game.getWorld()
																	.getPlayerRow()][game
																			.getWorld()
																			.getPlayerColumn()] = view.getWorldPanel()
																					.getCells()[game.getWorld()
																							.getPlayerRow()][game
																									.getWorld()
																									.getPlayerColumn()]
																											.getIcon();
															view.getWorldPanel().setSenzuBeanOpened(false);
														}
														else
															if (view.getWorldPanel().isDragonBallOpened())
															{
																view.getWorldPanel().getCells()[game.getWorld()
																								.getPlayerRow()][game.getWorld().getPlayerColumn()]
																										.setIcon(new ImageIcon(
																												"Images/World/Map/dragonball.png"));
																view.getWorldPanel().getCellIcons()[game.getWorld()
																		.getPlayerRow()][game
																				.getWorld()
																				.getPlayerColumn()] = view
																						.getWorldPanel().getCells()[game
																								.getWorld()
																								.getPlayerRow()][game
																										.getWorld()
																										.getPlayerColumn()]
																												.getIcon();
																view.getWorldPanel().setDragonBallOpened(false);
															}
															else
																if (view.getWorldPanel().isWeakFoeOpened())
																{

																	view.getWorldPanel().getCells()[game.getWorld()
																			.getPlayerRow()][game.getWorld()
																					.getPlayerColumn()]
																							.setIcon(new ImageIcon(view
																									.getBattlePanel()
																									.getFoePngImage()
																									.getImage()
																									.getScaledInstance(
																											60, 45, Image.SCALE_SMOOTH)));
																	view.getWorldPanel().getCellIcons()[game.getWorld()
																			.getPlayerRow()][game
																					.getWorld()
																					.getPlayerColumn()] = view
																							.getWorldPanel()
																							.getCells()[game.getWorld()
																									.getPlayerRow()][game
																											.getWorld()
																											.getPlayerColumn()]
																													.getIcon();
																	view.getWorldPanel().setWeakFoeOpened(false);
																}
																else
																	view.getWorldPanel().getCells()[game.getWorld()
																			.getPlayerRow()][game
																					.getWorld()
																					.getPlayerColumn()].setIcon(view
																							.getWorldPanel().getCellIcons()[game
																									.getWorld()
																									.getPlayerRow()][game
																											.getWorld()
																											.getPlayerColumn()]);

														view.getWorldPanel().getCells()[game.getWorld()
																.getPlayerRow()][game.getWorld().getPlayerColumn()]
																		.setBorder(new LineBorder(Color.WHITE));
														if (game.getWorld().getPlayerRow() > i)
															game.getWorld().moveUp();
														else
															if (game.getWorld().getPlayerColumn() < j)
																game.getWorld().moveRight();
															else
																if (game.getWorld().getPlayerColumn() > j)
																	game.getWorld().moveLeft();
																else
																	if (game.getWorld().getPlayerRow() < i)
																		game.getWorld().moveDown();
														view.getWorldPanel().setWorld(game.getWorld());
														view.getWorldPanel().getCells()[game.getWorld()
																.getPlayerRow()][game.getWorld().getPlayerColumn()]
																		.setIcon(view.getWorldPanel().getPlayerImage());
														view.getWorldPanel().getCells()[game.getWorld()
																.getPlayerRow()][game.getWorld().getPlayerColumn()]
																		.setBorder(new LineBorder(null, 5));
													}
					}
					else
						if (view.getCurrentPanel() == view.getUpgradeFighterPanel())
						{
							if (e.getSource() == view.getUpgradeFighterPanel().getUpgrade())
							{
								char attribute = ((String) view.getUpgradeFighterPanel().getFighterAttributes()
										.getSelectedItem()).toUpperCase().charAt(0);
								try
								{
									game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(), attribute);
									view.getWorldPanel().setPlayer(game.getPlayer());
									view.getUpgradeFighterPanel().getAbilityPoints()
											.setText("Fighter's Ability Points: "
													+ game.getPlayer().getActiveFighter().getAbilityPoints());
									view.getUpgradeFighterPanel().getKi().setText(": "+game.getPlayer().getActiveFighter().getMaxKi());
									view.getUpgradeFighterPanel().getStamina().setText(": "+game.getPlayer().getActiveFighter().getMaxStamina());
									view.getUpgradeFighterPanel().gethP().setText(": "+game.getPlayer().getActiveFighter().getMaxHealthPoints());
									view.getUpgradeFighterPanel().getpD().setText(": "+game.getPlayer().getActiveFighter().getPhysicalDamage());
									view.getUpgradeFighterPanel().getbD().setText(": "+game.getPlayer().getActiveFighter().getBlastDamage());
								}
								catch (NotEnoughAbilityPointsException e1)
								{
									JOptionPane.showMessageDialog(view, e1.getMessage(), "Error",
											JOptionPane.ERROR_MESSAGE);
								}
							}
							else
								if (e.getSource() == view.getUpgradeFighterPanel().getBack())
								{
									view.setContentPane(view.getWorldPanel());
									view.setCurrentPanel(view.getWorldPanel());
								}
						}
						else
							if (view.getCurrentPanel() == view.getSwitchFighterPanel())
							{
								if (e.getSource() == view.getSwitchFighterPanel().getBack())
								{
									view.setContentPane(view.getWorldPanel());
									view.setCurrentPanel(view.getWorldPanel());
								}
								else
									if (e.getSource() == view.getSwitchFighterPanel().getChoose())
									{
										int chosenIndex = view.getSwitchFighterPanel().getFighterNames().indexOf(
												view.getSwitchFighterPanel().getChooseFighter().getSelectedItem());
										game.getPlayer().setActiveFighter(view.getSwitchFighterPanel().getPlayer()
												.getFighters().get(chosenIndex));
										Icon mapBackground = view.getWorldPanel().getMapBackground().getIcon();
										Icon[][] tmp = view.getWorldPanel().getCellIcons();
										view.setWorldPanel(new WorldView(game.getWorld(), game.getPlayer()));
										addListenerWorldPanel();
										view.getWorldPanel().getMapBackground().setIcon(mapBackground);
										reloadWorldIcons(tmp);
										view.setContentPane(view.getWorldPanel());
										view.setCurrentPanel(view.getWorldPanel());
									}
							}
							else
								if (view.getCurrentPanel() == view.getAssignAttacksPanel())
								{
									if (e.getSource() == view.getAssignAttacksPanel().getBack())
									{
										view.setContentPane(view.getWorldPanel());
										view.setCurrentPanel(view.getWorldPanel());
									}

									if (e.getSource() == view.getAssignAttacksPanel().getAddSuper())
									{
										try
										{
											if (view.getAssignAttacksPanel().getSuperAttack().getSelectedItem() != null)
											{
												if (((String) view.getAssignAttacksPanel().getSuperAttackOld()
														.getSelectedItem()).equalsIgnoreCase("None"))
													game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(),
															game.getPlayer()
																	.getSuperAttacks().get(view.getAssignAttacksPanel()
																			.getSuperAttack().getSelectedIndex()),
															null);
												else
													game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(),
															game.getPlayer().getSuperAttacks()
																	.get(view.getAssignAttacksPanel().getSuperAttack()
																			.getSelectedIndex()),
															game.getPlayer().getActiveFighter().getSuperAttacks()
																	.get(view.getAssignAttacksPanel()
																			.getSuperAttackOld().getSelectedIndex()
																			- 1));
												view.getAssignAttacksPanel().updateSuperAttacks();

											}
											view.getAssignAttacksPanel().setPlayer(game.getPlayer());
											view.getAssignAttacksPanel().getSuperA()
													.setText(view.getAssignAttacksPanel().printSuperAttacks());
										}
										catch (MaximumAttacksLearnedException e1)
										{
											JOptionPane.showMessageDialog(view, e1.getMessage(), "Error",
													JOptionPane.ERROR_MESSAGE);
										}
										catch (DuplicateAttackException e1)
										{
											JOptionPane.showMessageDialog(view, e1.getMessage(), "Error",
													JOptionPane.ERROR_MESSAGE);
										}
										catch (NotASaiyanException e1)
										{
											JOptionPane.showMessageDialog(view, e1.getMessage(), "Error",
													JOptionPane.ERROR_MESSAGE);
										}
									}
									else
										if (e.getSource() == view.getAssignAttacksPanel().getAddUltimate())
										{
											try
											{
												if (view.getAssignAttacksPanel().getUltimateAttack()
														.getSelectedItem() != null)
												{
													if (((String) view.getAssignAttacksPanel().getUltimateAttackOld()
															.getSelectedItem()).equalsIgnoreCase("None"))
														game.getPlayer().assignAttack(
																game.getPlayer().getActiveFighter(),
																game.getPlayer().getUltimateAttacks()
																		.get(view.getAssignAttacksPanel()
																				.getUltimateAttack()
																				.getSelectedIndex()),
																null);
													else
														game.getPlayer().assignAttack(
																game.getPlayer().getActiveFighter(),
																game.getPlayer().getUltimateAttacks()
																		.get(view.getAssignAttacksPanel()
																				.getUltimateAttack()
																				.getSelectedIndex()),
																game.getPlayer().getActiveFighter().getUltimateAttacks()
																		.get(view.getAssignAttacksPanel()
																				.getUltimateAttackOld()
																				.getSelectedIndex() - 1));
													view.getAssignAttacksPanel().updateUltimateAttacks();
												}
												view.getAssignAttacksPanel().setPlayer(game.getPlayer());
												view.getAssignAttacksPanel().getUltimateA()
														.setText(view.getAssignAttacksPanel().printUltimateAttacks());
											}
											catch (MaximumAttacksLearnedException e1)
											{
												JOptionPane.showMessageDialog(view, e1.getMessage(), "Error",
														JOptionPane.ERROR_MESSAGE);
											}
											catch (DuplicateAttackException e1)
											{
												JOptionPane.showMessageDialog(view, e1.getMessage(), "Error",
														JOptionPane.ERROR_MESSAGE);
											}
											catch (NotASaiyanException e1)
											{
												JOptionPane.showMessageDialog(view, e1.getMessage(), "Error",
														JOptionPane.ERROR_MESSAGE);
											}
										}
								}
								else
									if (view.getCurrentPanel() == view.getDragonPanel())
									{
										if (e.getSource() == view.getDragonPanel().getChoose())
										{
											DragonWish wish = view.getDragonPanel().getDragon().getWishes()[view
													.getDragonPanel().getChooseWish().getSelectedIndex()];
											game.getPlayer().chooseWish(wish);
											Icon mapBackground = view.getWorldPanel().getMapBackground().getIcon();
											Icon[][] tmp = view.getWorldPanel().getCellIcons();
											// tmp[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()]=view.getWorldPanel()
											view.setWorldPanel(new WorldView(game.getWorld(), game.getPlayer()));
											addListenerWorldPanel();
											view.getWorldPanel().getMapBackground().setIcon(mapBackground);
											reloadWorldIcons(tmp);
											String var = "";
											switch (wish.getType())
											{
											case SENZU_BEANS:
												var = view.getDragonPanel().getDragon().getSenzuBeans()
														+ " Senzu Beans!";
												break;
											case ABILITY_POINTS:
												var = view.getDragonPanel().getDragon().getAbilityPoints()
														+ " Ability Points!";
												break;
											case ULTIMATE_ATTACK:
												var = "a new ultimate attack!";
												break;
											case SUPER_ATTACK:
												var = "a new super attack!";
												break;
											}
											JOptionPane.showMessageDialog(view,
													"Your wish has been satisified, you got " + var, "Wish Dialog",
													JOptionPane.INFORMATION_MESSAGE);
											view.setContentPane(view.getWorldPanel());
											view.setCurrentPanel(view.getWorldPanel());
										}
									}
									else
										if (view.getCurrentPanel() == view.getBattlePanel())
										{
											if (e.getSource() == view.getBattlePanel().getAttack())
											{
												try
												{
													view.getBattlePanel().getBattle()
															.attack(view.getBattlePanel().getBattle()
																	.getAssignedAttacks().get(view.getBattlePanel()
																			.getAssignedAttacks().getSelectedIndex()));

												}
												catch (NotEnoughKiException e1)
												{
													JOptionPane.showMessageDialog(view, e1.getMessage(),
															"Battle Dialog", JOptionPane.ERROR_MESSAGE);
												}

											}
											else
												if (e.getSource() == view.getBattlePanel().getBlock())
													view.getBattlePanel().getBattle().block();
												else
													if (e.getSource() == view.getBattlePanel().getUse())
														try
														{
															view.getBattlePanel().getBattle().use(game.getPlayer(),
																	Collectible.SENZU_BEAN);
														}
														catch (NotEnoughSenzuBeansException e1)
														{
															JOptionPane.showMessageDialog(view, e1.getMessage(),
																	"Battle Dialog", JOptionPane.ERROR_MESSAGE);
														}
										}
		refreshView();
		view.getContentPane().requestFocusInWindow();
	}

	public void onDragonCalled(Dragon dragon)
	{
		view.setDragonPanel(new DragonView(dragon));
		addListenerDragonPanel();
		JOptionPane.showMessageDialog(view,
				"Congratulations, You have collected the legendary 7 DragonBalls, You will be rewarded by meeting the mighty Dragon, All your wishes may come true!",
				"Dragon Dialog", JOptionPane.INFORMATION_MESSAGE);
		view.setContentPane(view.getDragonPanel());
		view.setCurrentPanel(view.getDragonPanel());
		refreshView();
	}

	public void onCollectibleFound(Collectible collectible)
	{
		if (collectible.equals(Collectible.DRAGON_BALL))
		{
			view.getWorldPanel().setPlayer(game.getPlayer());
			view.getWorldPanel().getPlayerDragonBalls().setText(": " + game.getPlayer().getDragonBalls() + "/7");
			JOptionPane.showMessageDialog(view, "You have collected a Dragonball !", "Great News",
					JOptionPane.INFORMATION_MESSAGE);
			view.getWorldPanel().setDragonBallOpened(true);

		}
		else
		{
			view.getWorldPanel().setPlayer(game.getPlayer());
			view.getWorldPanel().getPlayerSenzuBeans().setText(": " + game.getPlayer().getSenzuBeans());
			JOptionPane.showMessageDialog(view, "You have collected a Senzu Bean !", "Great News",
					JOptionPane.INFORMATION_MESSAGE);
			view.getWorldPanel().setSenzuBeanOpened(true);
		}
		refreshView();
	}

	public void onBattleEvent(BattleEvent e)
	{
		Battle battle = (Battle) e.getSource();
		if (e.getType().equals(BattleEventType.STARTED))
		{
			view.setBattlePanel(new BattleView(battle));
			addListenerBattlePanel();
			JOptionPane.showMessageDialog(view, "You have met a Foe, You will go to battle now, Stay strong fighter!",
					"Battle Dialog", JOptionPane.INFORMATION_MESSAGE);
			view.setContentPane(view.getBattlePanel());
			view.setCurrentPanel(view.getBattlePanel());
		}
		else
			if (e.getType().equals(BattleEventType.ENDED))
			{
				view.getBattlePanel().getFoeKi().setString("Ki: " + ((Fighter) battle.getFoe()).getKi() + "/"
						+ ((Fighter) battle.getFoe()).getMaxKi());
				view.getBattlePanel().getFighterKi().setString(
						"Ki: " + ((Fighter) battle.getMe()).getKi() + "/" + ((Fighter) battle.getMe()).getMaxKi());
				view.getBattlePanel().getFighterKi().setValue(((Fighter) battle.getMe()).getKi());
				view.getBattlePanel().getFoeKi().setValue(((Fighter) battle.getFoe()).getKi());
				view.getBattlePanel().getFighterStamina()
						.setString("Stamina: " + ((Fighter) battle.getMe()).getStamina() + "/"
								+ ((Fighter) battle.getMe()).getMaxStamina());
				view.getBattlePanel().getFoeStamina()
						.setString("Stamina: " + ((Fighter) battle.getFoe()).getStamina() + "/"
								+ ((Fighter) battle.getFoe()).getMaxStamina());
				view.getBattlePanel().getFoeStamina().setValue(((Fighter) battle.getFoe()).getStamina());
				view.getBattlePanel().getFighterStamina().setValue(((Fighter) battle.getMe()).getStamina());
				view.getBattlePanel().getFighterHealth().setValue(((Fighter) battle.getMe()).getHealthPoints());
				view.getBattlePanel().getFoeHealth().setValue(((Fighter) battle.getFoe()).getHealthPoints());
				view.getBattlePanel().getFighterHealth()
						.setString("HP: " + ((Fighter) battle.getMe()).getHealthPoints() + "/"
								+ ((Fighter) battle.getMe()).getMaxHealthPoints());
				view.getBattlePanel().getFoeHealth()
						.setString("HP: " + ((Fighter) battle.getFoe()).getHealthPoints() + "/"
								+ ((Fighter) battle.getFoe()).getMaxHealthPoints());
				Icon mapBackground = view.getWorldPanel().getMapBackground().getIcon();
				
				if (e.getWinner() == battle.getMe())
				{
					JOptionPane.showMessageDialog(view,
							"Fantastic Job, You have won the fight, the foe's attacks are now unlocked!",
							"Battle Dialog", JOptionPane.INFORMATION_MESSAGE);
					Icon[][] tmp = view.getWorldPanel().getCellIcons();
					view.setWorldPanel(new WorldView(game.getWorld(), game.getPlayer()));
					addListenerWorldPanel();
					if (!((NonPlayableFighter) battle.getFoe()).isStrong())
					{
						reloadWorldIcons(tmp);
						view.getWorldPanel().getMapBackground().setIcon(mapBackground);
						view.getWorldPanel().setWeakFoeOpened(true);
					}
					else
						JOptionPane.showMessageDialog(view, "You defeated the boss, a new map is unlocked!",
								"Battle Dialog", JOptionPane.INFORMATION_MESSAGE);
					if (view.getBattlePanel().getLevelBefore() < game.getPlayer().getActiveFighter().getLevel())
					{
						JOptionPane.showMessageDialog(view, "Your Fighter has leveled up, You gained 2 Ability Points!",
								"Battle Dialog", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(view,
							"You have lost! your last saved game will be loaded or if no savegame found, a new world will be generated",
							"Battle Dialog", JOptionPane.INFORMATION_MESSAGE);
					if (!game.getLastSavedFile().equals(""))
					{
						try
						{
							game.load(saveLocation);
							game.setListener(this);
							view.setWorldPanel(new WorldView(game.getWorld(), game.getPlayer()));
							addListenerWorldPanel();
							reloadWorldIcons(game.getIcons());
							view.getWorldPanel().getMapBackground().setIcon(game.getBackground());
						}
						catch (Exception exp)
						{
							exp.printStackTrace();
						}

					}
					else
					{
						view.setWorldPanel(new WorldView(game.getWorld(), game.getPlayer()));
						addListenerWorldPanel();
						reloadWorldIcons(view.getWorldPanel().getCellIcons());
					}
				}
				view.setContentPane(view.getWorldPanel());
				view.setCurrentPanel(view.getWorldPanel());
			}
			else
				if (e.getType().equals(BattleEventType.NEW_TURN))
				{
					view.getBattlePanel().getFoeKi().setString("Ki: " + ((Fighter) battle.getFoe()).getKi() + "/"
							+ ((Fighter) battle.getFoe()).getMaxKi());
					view.getBattlePanel().getFighterKi().setString(
							"Ki: " + ((Fighter) battle.getMe()).getKi() + "/" + ((Fighter) battle.getMe()).getMaxKi());
					view.getBattlePanel().getFighterKi().setValue(((Fighter) battle.getMe()).getKi());
					view.getBattlePanel().getFoeKi().setValue(((Fighter) battle.getFoe()).getKi());
					view.getBattlePanel().getFighterStamina()
							.setString("Stamina: " + ((Fighter) battle.getMe()).getStamina() + "/"
									+ ((Fighter) battle.getMe()).getMaxStamina());
					view.getBattlePanel().getFoeStamina()
							.setString("Stamina: " + ((Fighter) battle.getFoe()).getStamina() + "/"
									+ ((Fighter) battle.getFoe()).getMaxStamina());
					view.getBattlePanel().getFoeStamina().setValue(((Fighter) battle.getFoe()).getStamina());
					view.getBattlePanel().getFighterStamina().setValue(((Fighter) battle.getMe()).getStamina());
					view.getBattlePanel().getFighterHealth().setValue(((Fighter) battle.getMe()).getHealthPoints());
					view.getBattlePanel().getFoeHealth().setValue(((Fighter) battle.getFoe()).getHealthPoints());
					view.getBattlePanel().getFighterHealth()
							.setString("HP: " + ((Fighter) battle.getMe()).getHealthPoints() + "/"
									+ ((Fighter) battle.getMe()).getMaxHealthPoints());
					view.getBattlePanel().getFoeHealth()
							.setString("HP: " + ((Fighter) battle.getFoe()).getHealthPoints() + "/"
									+ ((Fighter) battle.getFoe()).getMaxHealthPoints());
					view.getBattlePanel().getTurn().setText(((Fighter) battle.getAttacker()).getName() + "'s Turn");
					
					if (battle.getAttacker() == battle.getFoe())
					{
						view.getBattlePanel().getAttack().setEnabled(false);
						view.getBattlePanel().getBlock().setEnabled(false);
						view.getBattlePanel().getUse().setEnabled(false);
						new Timer().schedule(new TimerTask()
						{
							public void run()
							{
								try
								{
									view.getBattlePanel().getBattle().play();
								}
								catch (NotEnoughKiException e1)
								{
									// Do Nothing
								}
								view.getBattlePanel().getAttack().setEnabled(true);
								view.getBattlePanel().getBlock().setEnabled(true);
								view.getBattlePanel().getUse().setEnabled(true);
							}
						}, 1500);
					}
					
					

					/*if (battle.getMe() instanceof Saiyan && !(((Saiyan) battle.getMe()).isTransformed()))
					{
						if (view.getBattlePanel().isTransformed() != ((Saiyan) battle.getMe()).isTransformed())
						{
							view.getBattlePanel().getFighterDeTransformed().getImage().flush();
							view.getBattlePanel().getFighter().setIcon(view.getBattlePanel().getFighterDeTransformed());
							view.getBattlePanel().setTransformed(false);
						}

						new Timer().schedule(new TimerTask()
						{

							public void run()
							{
								view.getBattlePanel().setFighterBlocking(
										new ImageIcon(getClass().getResource("Images/Battle/Goku/block.gif")));
								view.getBattlePanel().setFighterPhysicalAttack(
										new ImageIcon(getClass().getResource("Images/Battle/Goku/pA.gif")));
								view.getBattlePanel().setFighterSuperAttack(
										new ImageIcon(getClass().getResource("Images/Battle/Goku/sA.gif")));
								view.getBattlePanel().setFighterUltimateAttack(
										new ImageIcon(getClass().getResource("Images/Battle/Goku/uA.gif")));
								view.getBattlePanel().setFighterImage(
										new ImageIcon(getClass().getResource("Images/Battle/Goku/still.gif")));
							}
						}, 3000);
					}

					new Timer().schedule(new TimerTask()
					{
						public void run()
						{
							if (!battle.isFoeBlocking())
								view.getBattlePanel().getFoe().setIcon(view.getBattlePanel().getFoeImage());
							if (!battle.isMeBlocking())
								view.getBattlePanel().getFighter().setIcon(view.getBattlePanel().getFighterImage());
						}
					}, 3000);*/
				}

				else
					if (e.getType().equals(BattleEventType.USE))
					{
						view.getBattlePanel().getFighterHealth().setValue(((Fighter) battle.getMe()).getHealthPoints());
						view.getBattlePanel().getFighterHealth()
								.setString("HP: " + ((Fighter) battle.getMe()).getHealthPoints() + "/"
										+ ((Fighter) battle.getMe()).getMaxHealthPoints());
					}
					else
						if (e.getType().equals(BattleEventType.BLOCK))
						{
							
							if(battle.getAttacker() == battle.getFoe())
								JOptionPane.showMessageDialog(view,
									"Opponent has blocked!",
									"Battle Dialog", JOptionPane.INFORMATION_MESSAGE);
					
							/*if (battle.getAttacker() == battle.getFoe())
								view.getBattlePanel().getFoe().setIcon(view.getBattlePanel().getFoeBlocking());
							else
								view.getBattlePanel().getFighter().setIcon(view.getBattlePanel().getFighterBlocking());

							new Timer().schedule(new TimerTask()
							{
								public void run()
								{
									// Do Nothing
								}
							}, 5000);*/
						}
						else
						{
							if(battle.getAttacker() == battle.getFoe())
								JOptionPane.showMessageDialog(view,
									"Opponent has attacked!",
									"Battle Dialog", JOptionPane.INFORMATION_MESSAGE);
							
							/*if (battle.getAttacker() == battle.getFoe())
							{
								view.getBattlePanel().getFoe().setIcon(view.getBattlePanel().getFoePhysicalAttack());
								if (!battle.isMeBlocking())
								{
									view.getBattlePanel().getFighterHit().getImage().flush();
									view.getBattlePanel().getFighter().setIcon(view.getBattlePanel().getFighterHit());
								}
							}

							else
							{
								if (view.getBattlePanel().getBattle().getAssignedAttacks().get(view.getBattlePanel()
										.getAssignedAttacks().getSelectedIndex()) instanceof PhysicalAttack)
									view.getBattlePanel().getFighter()
											.setIcon(view.getBattlePanel().getFighterPhysicalAttack());
								else
									if (view.getBattlePanel().getBattle().getAssignedAttacks().get(view.getBattlePanel()
											.getAssignedAttacks().getSelectedIndex()) instanceof SuperAttack)
										view.getBattlePanel().getFighter()
												.setIcon(view.getBattlePanel().getFighterSuperAttack());
									else
										if (view.getBattlePanel().getBattle().getAssignedAttacks()
												.get(view.getBattlePanel().getAssignedAttacks()
														.getSelectedIndex()) instanceof SuperSaiyan)
										{
											view.getBattlePanel().getFighter()
													.setIcon(view.getBattlePanel().getFighterTransformed());

											view.getBattlePanel().setTransformed(true);
											view.getBattlePanel().setFighterBlocking(
													new ImageIcon("Images/Battle/Goku/blockSS.gif"));
											view.getBattlePanel().setFighterPhysicalAttack(
													new ImageIcon("Images/Battle/Goku/pASS.gif"));
											view.getBattlePanel().setFighterSuperAttack(
													new ImageIcon("Images/Battle/Goku/sASS.gif"));
											view.getBattlePanel().setFighterUltimateAttack(
													new ImageIcon("Images/Battle/Goku/uASS.gif"));
											view.getBattlePanel()
													.setFighterImage(new ImageIcon("Images/Battle/Goku/stillSS.gif"));
										}
										else
											view.getBattlePanel().getFighter()
													.setIcon(view.getBattlePanel().getFighterUltimateAttack());

								if (!((view.getBattlePanel().getBattle().getAssignedAttacks()
										.get(view.getBattlePanel().getAssignedAttacks()
												.getSelectedIndex()) instanceof SuperSaiyan)
										|| view.getBattlePanel().getBattle().getAssignedAttacks()
												.get(view.getBattlePanel().getAssignedAttacks()
														.getSelectedIndex()) instanceof MaximumCharge))
								{
									// view.getBattlePanel().getFoeHit().getImage().flush();
									view.getBattlePanel().getFoe().setIcon(view.getBattlePanel().getFoeHit());
								}
							}
							new Timer().schedule(new TimerTask()
							{
								public void run()
								{

								}

							}, 3000);*/
						}

		refreshView();
		view.getContentPane().requestFocusInWindow();
	}

	public void reloadWorldIcons(Icon[][] tmp)
	{
		view.getWorldPanel().setCellIcons(tmp);
		for (int i = 0; i < game.getWorld().getMap().length; i++)
			for (int j = 0; j < game.getWorld().getMap()[i].length; j++)
				view.getWorldPanel().getCells()[i][j].setIcon(view.getWorldPanel().getCellIcons()[i][j]);
		view.getWorldPanel().getCells()[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()]
				.setIcon(view.getWorldPanel().getPlayerImage());
	}

	public void refreshView()
	{
		view.repaint();
		view.revalidate();
	}

	public void addListenerMainPanel()
	{
		for (JButton b : view.getMainPanel().getButtons())
			b.addActionListener(this);
		view.getMainPanel().addKeyListener(this);
	}

	public void addListenerNewPlayerPanel()
	{
		view.getNewPlayerPanel().getEnterName().addActionListener(this);
		for (JButton b : view.getNewPlayerPanel().getButtons())
			b.addActionListener(this);
		view.getNewPlayerPanel().addKeyListener(this);
	}

	public void addListenerNewFighterPanel()
	{
		view.getNewFighterPanel().getChooseRace().addActionListener(this);
		view.getNewFighterPanel().getChooseRace().addItemListener(this);
		for (JButton b : view.getNewFighterPanel().getButtons())
			b.addActionListener(this);
		view.getNewFighterPanel().addKeyListener(this);
	}

	public void addListenerWorldPanel()
	{
		for (JButton b : view.getWorldPanel().getButtons())
			b.addActionListener(this);
		for (int i = 0; i < game.getWorld().getMap().length; i++)
			for (int j = 0; j < game.getWorld().getMap()[i].length; j++)
				view.getWorldPanel().getCells()[i][j].addActionListener(this);

		view.getWorldPanel().addKeyListener(this);
	}

	public void addListenerUpgradeFighterPanel()
	{
		view.getUpgradeFighterPanel().getFighterAttributes().addActionListener(this);
		for (JButton b : view.getUpgradeFighterPanel().getButtons())
			b.addActionListener(this);
		view.getUpgradeFighterPanel().addKeyListener(this);
	}

	public void addListenerSwitchFighterPanel()
	{
		view.getSwitchFighterPanel().getChooseFighter().addActionListener(this);
		view.getSwitchFighterPanel().getChooseFighter().addItemListener(this);
		for (JButton b : view.getSwitchFighterPanel().getButtons())
			b.addActionListener(this);
		view.getSwitchFighterPanel().addKeyListener(this);
	}

	public void addListenerAssignAttacksPanel()
	{
		for (JComboBox<String> b : view.getAssignAttacksPanel().getBoxes())
			b.addActionListener(this);
		for (JButton b : view.getAssignAttacksPanel().getButtons())
			b.addActionListener(this);
		view.getAssignAttacksPanel().addKeyListener(this);
	}

	public void addListenerDragonPanel()
	{
		view.getDragonPanel().getChooseWish().addActionListener(this);
		for (JButton b : view.getDragonPanel().getButtons())
			b.addActionListener(this);
		view.getDragonPanel().addKeyListener(this);
	}

	public void addListenerBattlePanel()
	{
		for (JButton b : view.getBattlePanel().getButtons())
			b.addActionListener(this);
		view.getBattlePanel().getControls().addKeyListener(this);
		view.getBattlePanel().addKeyListener(this);
	}

	public static void main(String[] args) throws MissingFieldException, UnknownAttackTypeException
	{
		GameGUI g=new GameGUI();
		System.out.println(g.game.getWorld());
	}
}