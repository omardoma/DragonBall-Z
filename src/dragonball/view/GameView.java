package dragonball.view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameView extends JFrame
{
	private MainMenuView mainPanel;
	private NewPlayerView newPlayerPanel;
	private NewFighterView newFighterPanel;
	private WorldView worldPanel;
	private BattleView battlePanel;
	private DragonView dragonPanel;
	private UpgradeFighterView upgradeFighterPanel;
	private SwitchFighterView switchFighterPanel;
	private AssignAttacksView assignAttacksPanel;
	private JPanel currentPanel;

	public GameView()
	{
		super("Dragonball-Z");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setIconImage(new ImageIcon("Images/World/Map/dragonball.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		addWindowListener(new CloseDialog(this));
		mainPanel = new MainMenuView();
		newPlayerPanel = new NewPlayerView();
		currentPanel = mainPanel;
	}

	public AssignAttacksView getAssignAttacksPanel()
	{
		return assignAttacksPanel;
	}

	public void setAssignAttacksPanel(AssignAttacksView assignAttacksPanel)
	{
		this.assignAttacksPanel = assignAttacksPanel;
	}

	public void setNewFighterPanel(NewFighterView newFighterPanel)
	{
		this.newFighterPanel = newFighterPanel;
	}

	public void setBattlePanel(BattleView battlePanel)
	{
		this.battlePanel = battlePanel;
	}

	public void setDragonPanel(DragonView dragonPanel)
	{
		this.dragonPanel = dragonPanel;
	}

	public void setUpgradeFighterPanel(UpgradeFighterView upgradeFighterPanel)
	{
		this.upgradeFighterPanel = upgradeFighterPanel;
	}

	public void setSwitchFighterPanel(SwitchFighterView chooseFighterPanel)
	{
		this.switchFighterPanel = chooseFighterPanel;
	}

	public SwitchFighterView getSwitchFighterPanel()
	{
		return switchFighterPanel;
	}

	public void setWorldPanel(WorldView worldPanel)
	{
		this.worldPanel = worldPanel;
	}

	public WorldView getWorldPanel()
	{
		return worldPanel;
	}

	public BattleView getBattlePanel()
	{
		return battlePanel;
	}

	public DragonView getDragonPanel()
	{
		return dragonPanel;
	}

	public UpgradeFighterView getUpgradeFighterPanel()
	{
		return upgradeFighterPanel;
	}

	public MainMenuView getMainPanel()
	{
		return mainPanel;
	}

	public NewPlayerView getNewPlayerPanel()
	{
		return newPlayerPanel;
	}

	public JPanel getCurrentPanel()
	{
		return currentPanel;
	}

	public NewFighterView getNewFighterPanel()
	{
		return newFighterPanel;
	}

	public void setCurrentPanel(JPanel currentPanel)
	{
		this.currentPanel = currentPanel;
	}
}