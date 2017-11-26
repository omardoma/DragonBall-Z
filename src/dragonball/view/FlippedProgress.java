package dragonball.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JProgressBar;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class FlippedProgress extends JProgressBar implements ActionListener
{
	private int nextHealth;
	private int rate;
	private ActionListener actionListener;

	public FlippedProgress(int maxHealth)
	{
		super(0, maxHealth);
		setValue(maxHealth);
		rate = maxHealth / 1000;
	}
	
	public void setActionListener(ActionListener actionListener)
	{
		this.actionListener = actionListener;
	}

	public int getTrueValue()
	{
		return this.getMaximum() - this.getValue();
	}

	public void setNextHealth(int nextHealth)
	{
		this.nextHealth = nextHealth;
	}

	public void setHealthPoints(int nextHealth)
	{
		this.nextHealth = nextHealth;
		Timer t = new Timer(1, this);
		t.start();
	}

	public void actionPerformed(ActionEvent arg0)
	{
		if (nextHealth < getValue())
		{
			if (nextHealth + 3 * rate < getValue())
			{
				setValue(getValue() - 3 * rate);
			}
			else
			{
				setValue(nextHealth);
			}
		}
		else
			if (nextHealth > this.getValue())
			{
				if (nextHealth > this.getValue() + 3 * rate)
				{
					setValue(this.getValue() + 3 * rate);
				}
				else
				{
					setValue(nextHealth);
				}
			}
			else
			{
				((Timer) (arg0.getSource())).stop();
				if (actionListener != null)
				{
					actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_FIRST, "continue"));
				}
			}
		repaint();
	}

	public void setValue(int value)
	{
		super.setValue(getMaximum() - value);
	}

	public int getValue()
	{
		return getMaximum() - getValue();
	}
}
