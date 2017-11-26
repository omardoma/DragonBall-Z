package dragonball.model.battle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import dragonball.model.attack.Attack;
import dragonball.model.attack.MaximumCharge;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.player.Player;

@SuppressWarnings("serial")
public class Battle implements Serializable
{
	private BattleOpponent me;
	private BattleOpponent foe;
	private BattleOpponent attacker;
	private boolean meBlocking;
	private boolean foeBlocking;
	private BattleListener listener;

	public Battle(BattleOpponent me, BattleOpponent foe)
	{
		this.me = me;
		this.foe = foe;
		this.attacker = me;

		// set current values appropriately
		Fighter meFighter = (Fighter) me;
		meFighter.setHealthPoints(meFighter.getMaxHealthPoints());
		meFighter.setKi(0);
		meFighter.setStamina(meFighter.getMaxStamina());
		// reset a saiyan's transformation state in case it was transformed in a
		// previous battle
		if (me instanceof Saiyan)
		{
			Saiyan meSaiyan = (Saiyan) me;
			meSaiyan.setTransformed(false);
		}

		Fighter foeFighter = (Fighter) foe;
		foeFighter.setHealthPoints(foeFighter.getMaxHealthPoints());
		foeFighter.setKi(0);
		foeFighter.setStamina(foeFighter.getMaxStamina());
	}

	public BattleOpponent getMe()
	{
		return me;
	}

	public BattleOpponent getFoe()
	{
		return foe;
	}

	public BattleOpponent getAttacker()
	{
		return attacker;
	}

	public BattleOpponent getDefender()
	{
		return attacker == me ? foe : me;
	}

	public boolean isMeBlocking()
	{
		return meBlocking;
	}

	public boolean isFoeBlocking()
	{
		return foeBlocking;
	}

	public ArrayList<Attack> getAssignedAttacks()
	{
		Fighter attackerFighter = (Fighter) attacker;

		ArrayList<Attack> attacks = new ArrayList<>();
		// make sure to include the physical attack as well
		attacks.add(new PhysicalAttack());
		attacks.addAll(attackerFighter.getSuperAttacks());
		attacks.addAll(attackerFighter.getUltimateAttacks());
		return attacks;
	}

	public void switchTurn()
	{
		attacker = getDefender();
	}

	public void endTurn()
	{
		// reset block mode
		if (attacker == me && foeBlocking)
		{
			foeBlocking = false;
		}
		else
			if (attacker == foe && meBlocking)
			{
				meBlocking = false;
			}

		// if i'm dead
		if (((Fighter) me).getHealthPoints() == 0)
		{
			// tell everyone my opponent won
			notifyOnBattleEvent(new BattleEvent(this, BattleEventType.ENDED, foe));
			// if my opponent is dead
		}
		else
			if (((Fighter) foe).getHealthPoints() == 0)
			{
				// tell everyone i won
				notifyOnBattleEvent(new BattleEvent(this, BattleEventType.ENDED, me));
			}
			else
			{
				switchTurn();

				getAttacker().onDefenderTurn();
				getDefender().onAttackerTurn();

				notifyOnBattleEvent(new BattleEvent(this, BattleEventType.NEW_TURN));
			}
	}

	public void start()
	{
		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.STARTED));
		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.NEW_TURN));
	}

	// used to automate turn for opponent a.k.a. ai
	public void play() throws NotEnoughKiException
	{
		int maxDamage = 0;
		Fighter foeFighter = (Fighter) foe;
		SuperAttack maxSuperAttack = null;
		UltimateAttack maxUltimateAttack = null;
		ArrayList<Attack> attacks = getAssignedAttacks();
		for (Attack attack : attacks)
			if (attack.getDamage() >= maxDamage)
			{
				if (attack instanceof SuperAttack)
					maxSuperAttack = (SuperAttack) attack;
				else
					if (attack instanceof UltimateAttack)
						maxUltimateAttack = (UltimateAttack) attack;
				maxDamage = attack.getDamage();
			}

		if (!isMeBlocking())
		{
			if (foeFighter.getHealthPoints() <= foeFighter.getMaxHealthPoints() / 4 && foeFighter.getStamina() >= 1)
			{
				if (new Random().nextInt(100) > 10)
					block();
				else
					attack(new PhysicalAttack());
			}
			else
				if (foeFighter.getKi() >= 3 && maxUltimateAttack != null)
				{
					attack(maxUltimateAttack);
				}
				else
					if (foeFighter.getKi() >= 1 && maxSuperAttack != null)
					{
						if (maxUltimateAttack != null)
							if ((((Fighter) me).getHealthPoints() / 2 <= maxUltimateAttack.getAppliedDamage(foe))
									|| (maxUltimateAttack.getAppliedDamage(foeFighter)
											- maxSuperAttack.getAppliedDamage(foeFighter) >= 200))
							{
								if (attacks.contains(new MaximumCharge()))
									attack(new MaximumCharge());
								else
									attack(new PhysicalAttack());
							}
							else
								attack(maxSuperAttack);
						else
							attack(maxSuperAttack);
					}
					else
					{
						attack(new PhysicalAttack());
					}
		}
		else
		{
			if (foeFighter.getKi() >= 3 && maxUltimateAttack != null)
			{
				attack(maxUltimateAttack);
			}
			else
				if (foeFighter.getKi() >= 1 && maxSuperAttack != null)
				{
					if (maxUltimateAttack != null)
						if ((((Fighter) me).getHealthPoints() / 2 <= maxUltimateAttack.getAppliedDamage(foe))
								|| (maxUltimateAttack.getAppliedDamage(foeFighter)
										- maxSuperAttack.getAppliedDamage(foeFighter) >= 200))
						{
							if (attacks.contains(new MaximumCharge()))
								attack(new MaximumCharge());
							else
								attack(new PhysicalAttack());
						}
						else
							attack(maxSuperAttack);
					else
						attack(maxSuperAttack);
				}
				else
				{
					attack(new PhysicalAttack());
				}
		}
	}

	// perform an attack and end turn
	public void attack(Attack attack) throws NotEnoughKiException
	{
		attack.onUse(attacker, getDefender(), (attacker == me && foeBlocking) || (attacker == foe && meBlocking));

		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.ATTACK, attack));

		endTurn();
	}

	// perform a block and end turn
	public void block()
	{
		if (attacker == me)
		{
			meBlocking = true;
		}
		else
			if (attacker == foe)
			{
				foeBlocking = true;
			}

		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.BLOCK));

		endTurn();
	}

	// use a collectible and end turn
	public void use(Player player, Collectible collectible) throws NotEnoughSenzuBeansException
	{
		switch (collectible)
		{
		case SENZU_BEAN:
			if (player.getSenzuBeans() > 0)
			{
				PlayableFighter activeFighter = player.getActiveFighter();
				activeFighter.setHealthPoints(activeFighter.getMaxHealthPoints());
				activeFighter.setStamina(activeFighter.getMaxStamina());

				player.setSenzuBeans(player.getSenzuBeans() - 1);

				notifyOnBattleEvent(new BattleEvent(this, BattleEventType.USE, collectible));
			}
			else
			{
				throw new NotEnoughSenzuBeansException();
			}
			break;
		default:
			break;
		}

		endTurn();
	}

	public void setListener(BattleListener listener)
	{
		this.listener = listener;
	}

	public void notifyOnBattleEvent(BattleEvent e)
	{
		if (listener != null)
		{
			listener.onBattleEvent(e);
		}
	}
}
