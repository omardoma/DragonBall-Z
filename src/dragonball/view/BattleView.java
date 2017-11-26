package dragonball.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import dragonball.model.attack.Attack;
import dragonball.model.battle.Battle;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.Saiyan;

@SuppressWarnings("serial")
public class BattleView extends JPanel
{
	private Battle battle;
	private JButton attack;
	private JButton block;
	private JButton use;
	private JComboBox<String> assignedAttacks;
	private ArrayList<String> attacksNames;
	private ImageIcon fighterImage;
	private ImageIcon fighterBlocking;
	private ImageIcon fighterPhysicalAttack;
	private ImageIcon fighterSuperAttack;
	private ImageIcon fighterUltimateAttack;
	private ImageIcon fighterHit;
	private ImageIcon fighterTransformed;
	private ImageIcon fighterDeTransformed;
	private ImageIcon foeImage;
	private ImageIcon foeBlocking;
	private ImageIcon foePhysicalAttack;
	private ImageIcon foeSuperAttack;
	private ImageIcon foeUltimateAttack;
	private ImageIcon foeHit;
	private ImageIcon foePngImage;
	private ImageIcon fighterPngImage;
	private JLabel fighter;
	private JLabel foe;
	private JLabel battleBackground;
	private JLabel fighterName;
	private JLabel foeName;
	private JLabel fighterLevel;
	private JLabel foeLevel;
	private JLabel turn;
	private JLabel foeAvatar;
	private JLabel fighterAvatar;
	private JProgressBar fighterHealth;
	private FlippedProgress foeHealth;
	private JProgressBar fighterStamina;
	private FlippedProgress foeStamina;
	private JProgressBar fighterKi;
	private FlippedProgress foeKi;
	private JPanel controls;
	private int levelBefore;
	private boolean transformed;
	private ArrayList<JButton> buttons;
	
	public BattleView(Battle battle)
	{
		this.battle = battle;
		preparePanel();
	}
	
	public boolean isTransformed()
	{
		return transformed;
	}

	public void setTransformed(boolean transformed)
	{
		this.transformed = transformed;
	}

	public ImageIcon getFighterDeTransformed()
	{
		return fighterDeTransformed;
	}

	public void setFighterImage(ImageIcon fighterImage)
	{
		this.fighterImage = fighterImage;
	}

	public void setFighterBlocking(ImageIcon fighterBlocking)
	{
		this.fighterBlocking = fighterBlocking;
	}

	public void setFighterPhysicalAttack(ImageIcon fighterPhysicalAttack)
	{
		this.fighterPhysicalAttack = fighterPhysicalAttack;
	}

	public void setFighterSuperAttack(ImageIcon fighterSuperAttack)
	{
		this.fighterSuperAttack = fighterSuperAttack;
	}

	public void setFighterUltimateAttack(ImageIcon fighterUltimateAttack)
	{
		this.fighterUltimateAttack = fighterUltimateAttack;
	}

	public void setFighterHit(ImageIcon fighterHit)
	{
		this.fighterHit = fighterHit;
	}

	public ImageIcon getFighterTransformed()
	{
		return fighterTransformed;
	}
	
	public ImageIcon getFoeHit()
	{
		return foeHit;
	}

	public ImageIcon getFighterHit()
	{
		return fighterHit;
	}

	public ImageIcon getFighterBlocking()
	{
		return fighterBlocking;
	}

	public ImageIcon getFighterPhysicalAttack()
	{
		return fighterPhysicalAttack;
	}

	public ImageIcon getFighterSuperAttack()
	{
		return fighterSuperAttack;
	}

	public ImageIcon getFighterUltimateAttack()
	{
		return fighterUltimateAttack;
	}

	public ImageIcon getFoeBlocking()
	{
		return foeBlocking;
	}

	public ImageIcon getFoePhysicalAttack()
	{
		return foePhysicalAttack;
	}

	public ImageIcon getFoeSuperAttack()
	{
		return foeSuperAttack;
	}

	public ImageIcon getFoeUltimateAttack()
	{
		return foeUltimateAttack;
	}

	public JPanel getControls()
	{
		return controls;
	}

	public ImageIcon getFighterPngImage()
	{
		return fighterPngImage;
	}

	public JLabel getFoeAvatar()
	{
		return foeAvatar;
	}

	public JLabel getFighterAvatar()
	{
		return fighterAvatar;
	}

	public JComboBox<String> getAssignedAttacks()
	{
		return assignedAttacks;
	}

	public ArrayList<String> getAttacksNames()
	{
		return attacksNames;
	}

	public JLabel getFighterLevel()
	{
		return fighterLevel;
	}

	public JLabel getFoeLevel()
	{
		return foeLevel;
	}

	public int getLevelBefore()
	{
		return levelBefore;
	}

	public ImageIcon getFoePngImage()
	{
		return foePngImage;
	}

	public JButton getAttack()
	{
		return attack;
	}

	public JButton getBlock()
	{
		return block;
	}

	public JButton getUse()
	{
		return use;
	}

	public ImageIcon getFighterImage()
	{
		return fighterImage;
	}

	public ImageIcon getFoeImage()
	{
		return foeImage;
	}

	public JLabel getFighter()
	{
		return fighter;
	}

	public JLabel getFoe()
	{
		return foe;
	}

	public JLabel getBattleBackground()
	{
		return battleBackground;
	}

	public JLabel getFighterName()
	{
		return fighterName;
	}

	public JLabel getFoeName()
	{
		return foeName;
	}

	public JProgressBar getFighterKi()
	{
		return fighterKi;
	}

	public JProgressBar getFoeKi()
	{
		return foeKi;
	}

	public JProgressBar getFighterStamina()
	{
		return fighterStamina;
	}

	public JProgressBar getFoeStamina()
	{
		return foeStamina;
	}

	public JLabel getTurn()
	{
		return turn;
	}

	public JProgressBar getFighterHealth()
	{
		return fighterHealth;
	}

	public JProgressBar getFoeHealth()
	{
		return foeHealth;
	}

	public ArrayList<JButton> getButtons()
	{
		return buttons;
	}

	public Battle getBattle()
	{
		return battle;
	}

	private void preparePanel()
	{
		setBackground(Color.WHITE);
		battleBackground = new JLabel(new ImageIcon(new ImageIcon("Images/Battle/b"+new Random().nextInt(10)+".jpg")
				.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
		add(battleBackground);
		battleBackground.setLayout(null);
		levelBefore=((Fighter) battle.getMe()).getLevel();
		if (battle.getMe() instanceof Saiyan)
		{
			fighterImage = new ImageIcon("Images/Battle/Goku/still.gif");
			fighterTransformed=new ImageIcon("Images/Battle/Goku/transformed.gif");
			fighterDeTransformed=new ImageIcon("Images/Battle/Goku/detransformed.gif");
			fighterBlocking=new ImageIcon("Images/Battle/Goku/block.gif");
			fighterPhysicalAttack=new ImageIcon("Images/Battle/Goku/pA.gif");
			fighterSuperAttack=new ImageIcon("Images/Battle/Goku/sA.gif");
			fighterHit=new ImageIcon("Images/Battle/Goku/hit.gif");
			fighterPngImage = new ImageIcon("Images/Battle/Avatars/goku.png");
		}
		else
			if (battle.getMe() instanceof Frieza)
			{
				fighterImage = new ImageIcon("Images/Battle/frieza.gif");
				fighterPngImage = new ImageIcon("Images/Battle/Avatars/frieza.png");
			}
			else
				if (battle.getMe() instanceof Namekian)
				{
					fighterImage = new ImageIcon("Images/Battle/namekian.gif");
					fighterPngImage = new ImageIcon("Images/Battle/Avatars/piccolo.png");	
				}
				else
					if (battle.getMe() instanceof Majin)
					{
						fighterImage = new ImageIcon("Images/Battle/majinbuu.gif");
						fighterPngImage = new ImageIcon("Images/Battle/Avatars/majin.png");	
					}
					else
					{
						fighterImage = new ImageIcon("Images/Battle/Krillin/still.gif");
						fighterBlocking=new ImageIcon("Images/Battle/Krillin/block.gif");
						fighterPhysicalAttack=new ImageIcon("Images/Battle/Krillin/pA.gif");
						fighterSuperAttack=new ImageIcon("Images/Battle/Krillin/sA.gif");
						fighterHit=new ImageIcon("Images/Battle/Krillin/hit.gif");
						fighterPngImage = new ImageIcon("Images/Battle/Avatars/krillin.png");
					}
		
		if (((NonPlayableFighter) battle.getFoe()).getName().equalsIgnoreCase("goku"))
		{
			foeImage = new ImageIcon("Images/Battle/Goku/stillF.gif");
			foeBlocking=new ImageIcon("Images/Battle/Goku/blockF.gif");
			foePhysicalAttack=new ImageIcon("Images/Battle/Goku/pAF.gif");
			foeSuperAttack=new ImageIcon("Images/Battle/Goku/sAF.gif");
			foeHit=new ImageIcon("Images/Battle/Goku/hitF.gif");
			foePngImage = new ImageIcon("Images/Battle/Avatars/goku.png");
		}

		else
			if (((NonPlayableFighter) battle.getFoe()).getName().equalsIgnoreCase("frieza"))
			{
				foeImage = new ImageIcon("Images/Battle/friezaF.gif");
				foePngImage = new ImageIcon("Images/Battle/Avatars/frieza.png");
			}
			else
				if (((NonPlayableFighter) battle.getFoe()).getName().equalsIgnoreCase("piccolo"))
				{
					foeImage = new ImageIcon("Images/Battle/namekianF.gif");
					foePngImage = new ImageIcon(new ImageIcon("Images/Battle/Avatars/piccolo.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
				}
				else
					if (((NonPlayableFighter) battle.getFoe()).getName().equalsIgnoreCase("ramon"))
					{
						foeImage = new ImageIcon("Images/Battle/ramon.gif");
						foePngImage = new ImageIcon(new ImageIcon("Images/Battle/Avatars/ramon.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
					}
					else
						if (((NonPlayableFighter) battle.getFoe()).getName().equalsIgnoreCase("gohan (kid)"))
						{
							foeImage = new ImageIcon("Images/Battle/gohan(kid).gif");
							foePngImage = new ImageIcon(new ImageIcon("Images/Battle/Avatars/gohan (kid).png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
						}
						else
							if (((NonPlayableFighter) battle.getFoe()).getName().equalsIgnoreCase("tien"))
							{
								foeImage = new ImageIcon("Images/Battle/tien.gif");
								foePngImage = new ImageIcon(new ImageIcon("Images/Battle/Avatars/tien.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
							}

							else
								if (((NonPlayableFighter) battle.getFoe()).getName().equalsIgnoreCase("krillin"))
								{
									foeImage = new ImageIcon("Images/Battle/Krillin/stillF.gif");
									foeBlocking=new ImageIcon("Images/Battle/Krillin/blockF.gif");
									foePhysicalAttack=new ImageIcon("Images/Battle/Krillin/pAF.gif");
									foeSuperAttack=new ImageIcon("Images/Battle/Krillin/sAF.gif");
									foeHit=new ImageIcon("Images/Battle/Krillin/hitF.gif");
									foePngImage = new ImageIcon(new ImageIcon("Images/Battle/Avatars/krillin.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
								}
								else
									if (((NonPlayableFighter) battle.getFoe()).getName().equalsIgnoreCase("majinbuu"))
									{
										foeImage = new ImageIcon("Images/Battle/majinbuuF.gif");
										foePngImage = new ImageIcon(new ImageIcon("Images/Battle/Avatars/majinbuu.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
									}
									else
										if (((NonPlayableFighter) battle.getFoe()).getName().equalsIgnoreCase("raditz"))
										{
											foeImage = new ImageIcon("Images/Battle/raditz.gif");
											foePngImage =new ImageIcon( new ImageIcon("Images/Battle/Avatars/raditz.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
										}
										else
										{
											foeImage = new ImageIcon("Images/Battle/ramon.gif");
											foePngImage = new ImageIcon(new ImageIcon("Images/Battle/Avatars/yamcha.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
										}
		foeAvatar=new JLabel(foePngImage);
		foeAvatar.setBounds(800-85,0,80,80);
		foeAvatar.setOpaque(true);
		foeAvatar.setBackground(Color.WHITE);
		fighterAvatar=new JLabel(fighterPngImage);
		fighterAvatar.setBounds(5,0,80,80);
		fighterAvatar.setOpaque(true);
		fighterAvatar.setBackground(Color.WHITE);
		fighter=new JLabel(fighterImage);
		fighter.setBounds(150,350,200,200);
		foe=new JLabel(foeImage);
		foe.setBounds(400, 350, 200, 200);
		battleBackground.add(fighter);
		battleBackground.add(foe);
		turn= new JLabel(((Fighter)battle.getMe()).getName()+"'s Turn");
		turn.setBounds((800-300)/2,0,300, 30);
		turn.setHorizontalAlignment(JLabel.CENTER);
		turn.setBackground(Color.WHITE);
		turn.setOpaque(true);
		turn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		fighterName= new JLabel(((Fighter)battle.getMe()).getName()+" ");
		fighterName.setBounds(turn.getX()-165, 0, 165, 40);
		fighterName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		fighterName.setOpaque(true);
		fighterName.setBackground(Color.WHITE);
		fighterName.setHorizontalAlignment(JLabel.RIGHT);
		foeName= new JLabel(" "+((Fighter)battle.getFoe()).getName());
		foeName.setBounds(turn.getX()+turn.getWidth()-2,0, 167, 40);
		foeName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		foeName.setOpaque(true);
		foeName.setBackground(Color.WHITE);
		fighterLevel= new JLabel("Level: "+((Fighter)battle.getMe()).getLevel()+" ");
		fighterLevel.setBounds(fighterName.getX(),fighterName.getY()+fighterName.getHeight() , 165,40);
		fighterLevel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		fighterLevel.setHorizontalAlignment(JLabel.RIGHT);
		fighterLevel.setOpaque(true);
		fighterLevel.setBackground(Color.WHITE);
		foeLevel= new JLabel(" Level: "+((Fighter)battle.getFoe()).getLevel());
		foeLevel.setBounds(foeName.getX(),fighterLevel.getY(), 167, 40);
		foeLevel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		foeLevel.setOpaque(true);
		foeLevel.setBackground(Color.WHITE);
		UIManager.put("ProgressBar.selectionForeground",Color.DARK_GRAY);
		UIManager.put("ProgressBar.selectionBackground",Color.DARK_GRAY);
		fighterHealth= new JProgressBar(0, ((Fighter)battle.getMe()).getMaxHealthPoints());
		fighterHealth.setForeground(Color.RED);
		fighterHealth.setStringPainted(true);
		fighterHealth.setOrientation(JProgressBar.HORIZONTAL);
		fighterHealth.setValue(((Fighter)battle.getMe()).getHealthPoints());
		fighterHealth.setString("HP: "+((Fighter)battle.getMe()).getHealthPoints()+"/"+((Fighter)battle.getMe()).getMaxHealthPoints());
		fighterHealth.setBackground(Color.WHITE);
		fighterHealth.setBorder(new LineBorder(Color.WHITE));
		fighterHealth.setBounds(5,fighterAvatar.getY()+fighterAvatar.getHeight(),230,40);
		fighterHealth.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		foeHealth= new FlippedProgress(((Fighter)battle.getFoe()).getMaxHealthPoints());
		foeHealth.setStringPainted(true);
		foeHealth.setForeground(Color.WHITE);
		foeHealth.setOrientation(JProgressBar.HORIZONTAL);
		foeHealth.setValue(((Fighter)battle.getFoe()).getHealthPoints());
		foeHealth.setString("HP: "+((Fighter)battle.getFoe()).getHealthPoints()+"/"+((Fighter)battle.getFoe()).getMaxHealthPoints());
		foeHealth.setBorder(null);
		foeHealth.setBackground(Color.RED);
		foeHealth.setBorder(new LineBorder(Color.WHITE));
		foeHealth.setBounds(800-235,foeAvatar.getY()+foeAvatar.getHeight(),230,40);
		foeHealth.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		fighterStamina= new JProgressBar(0, ((Fighter)battle.getMe()).getMaxStamina());
		fighterStamina.setForeground(Color.YELLOW);
		fighterStamina.setStringPainted(true);
		fighterStamina.setOrientation(JProgressBar.HORIZONTAL);
		fighterStamina.setValue(((Fighter)battle.getMe()).getStamina());
		fighterStamina.setString("Stamina: "+((Fighter)battle.getMe()).getStamina()+"/"+((Fighter)battle.getMe()).getMaxStamina());
		fighterStamina.setBackground(Color.WHITE);
		fighterStamina.setBorder(new LineBorder(Color.WHITE));
		fighterStamina.setBounds(5,fighterHealth.getY()+fighterHealth.getHeight(),180,35);
		fighterStamina.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		foeStamina= new FlippedProgress(((Fighter)battle.getFoe()).getMaxStamina());
		foeStamina.setForeground(Color.WHITE);
		foeStamina.setStringPainted(true);
		foeStamina.setOrientation(JProgressBar.HORIZONTAL);
		foeStamina.setValue(((Fighter)battle.getFoe()).getStamina());
		foeStamina.setString("Stamina: "+((Fighter)battle.getFoe()).getStamina()+"/"+((Fighter)battle.getFoe()).getMaxStamina());
		foeStamina.setBackground(Color.YELLOW);
		foeStamina.setBorder(new LineBorder(Color.WHITE));
		foeStamina.setBounds(800-185,foeHealth.getY()+foeHealth.getHeight(),180,35);
		foeStamina.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		fighterKi= new JProgressBar(0, ((Fighter)battle.getMe()).getMaxKi());
		fighterKi.setForeground(Color.GREEN);
		fighterKi.setStringPainted(true);
		fighterKi.setOrientation(JProgressBar.HORIZONTAL);
		fighterKi.setValue(((Fighter)battle.getMe()).getKi());
		fighterKi.setString("HP: "+((Fighter)battle.getMe()).getKi()+"/"+((Fighter)battle.getMe()).getMaxKi());
		fighterKi.setBackground(Color.WHITE);
		fighterKi.setBorder(new LineBorder(Color.WHITE));
		fighterKi.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		fighterKi.setBounds(5,fighterStamina.getY()+fighterStamina.getHeight(),150,30);
		foeKi= new FlippedProgress(((Fighter)battle.getFoe()).getMaxKi());
		foeKi.setBounds(800-155,foeStamina.getY()+foeStamina.getHeight(),150,30);
		foeKi.setStringPainted(true);
		foeKi.setForeground(Color.WHITE);
		foeKi.setOrientation(JProgressBar.HORIZONTAL);
		foeKi.setValue(((Fighter)battle.getFoe()).getKi());
		foeKi.setString("HP: "+((Fighter)battle.getFoe()).getKi()+"/"+((Fighter)battle.getFoe()).getMaxKi());
		foeKi.setBackground(Color.GREEN);
		foeKi.setBorder(new LineBorder(Color.WHITE));
		foeKi.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		buttons = new ArrayList<JButton>();
		attack= new JButton("Attack");
		attack.setBackground(Color.WHITE);
		attack.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		attack.setFocusPainted(false);
		attack.setBorder(new LineBorder(Color.WHITE));
		attack.setOpaque(false);
		buttons.add(attack);
		block= new JButton("Block");
		block.setBackground(Color.WHITE);
		block.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		block.setFocusPainted(false);
		block.setBorder(new LineBorder(Color.WHITE));
		block.setOpaque(false);
		buttons.add(block);
		use= new JButton("Use");
		use.setOpaque(false);
		use.setBackground(Color.WHITE);
		use.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		use.setFocusPainted(false);
		use.setBorder(new LineBorder(Color.WHITE));
		buttons.add(use);
		attacksNames=new ArrayList<String>();
		for (Attack attack : battle.getAssignedAttacks())
			attacksNames.add(attack.getName() +" ("+attack.getAppliedDamage(battle.getMe())+")");
		
		String[] attacks= new String[attacksNames.size()];
		attacksNames.toArray(attacks);
		assignedAttacks= new JComboBox<String>(attacks);
		assignedAttacks.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		assignedAttacks.setBackground(Color.WHITE);	
		controls=new JPanel();
		controls.setBounds((800-280)/2, turn.getY()+turn.getY()+turn.getHeight()+5,280,150);
		controls.setOpaque(false);
		controls.setLayout(new GridLayout(4,1));
		controls.add(assignedAttacks);
		block.setCursor(new Cursor(Cursor.HAND_CURSOR));
		attack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		use.setCursor(new Cursor(Cursor.HAND_CURSOR));
		controls.add(attack);
		controls.add(block);
		controls.add(use);
		battleBackground.add(fighterName);
		battleBackground.add(foeName);
		battleBackground.add(fighterLevel);
		battleBackground.add(foeLevel);
		battleBackground.add(fighterHealth);
		battleBackground.add(foeHealth);
		battleBackground.add(fighterKi);
		battleBackground.add(foeKi);
		battleBackground.add(fighterStamina);
		battleBackground.add(foeStamina);
		battleBackground.add(fighterAvatar);
		battleBackground.add(foeAvatar);
		battleBackground.add(turn);
		battleBackground.add(controls);
		battleBackground.add(fighter);
		battleBackground.add(foe);
	}
}