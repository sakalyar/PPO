package motion.model;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

import motion.view.Animable;

public class TimerAnimator extends AbstractAnimator {

	private static final int SEC = 1000;
	private int max;
	private int value;
	private final Timer t;
	private boolean started = false,
					stopped = true,
					paused = false;
	
	public TimerAnimator(int max) {
		super(max);
		this.max = max;
		value = 0;
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getSpeed() == 0 && !isRunning())
					stop();
				else if (getSpeed() == 0)
					pause();
				else
					start();
				fireTickOccured();
			}
		};
		t = new Timer(SEC, al);
		t.setInitialDelay(0);
	}
	
	int max() {
		return max;
	}
	
	@Override
	public int getSpeed() {
		return value;
	}

	@Override
	public boolean hasStarted() {
		return started;
	}

	@Override
	public boolean hasStopped() {
		return stopped;
	}

	@Override
	public boolean isPaused() {
		return paused;
	}

	@Override
	public boolean isResumed() {
		return !paused;
	}

	@Override
	public boolean isRunning() {
		return t.isRunning();
	}

	@Override
	public void pause() {
		stop();
		paused = true;
		stopped = false;
		fireStateChanged();
	}

	@Override
	public void resume() {
		paused = false;
		stopped = false;
		start();
		fireStateChanged();
	}

	@Override
	public void setSpeed(int d) {
		value = d;
		fireStateChanged();
	}

	@Override
	public void start() {
		stop();
		int del = (int) Double.POSITIVE_INFINITY;
		if (value != 0)
			del = SEC / value;
		t.setDelay(del);
		t.start();
		started = true;
		stopped = true;
		fireStateChanged();
	}

	@Override
	public void stop() {
		t.stop();
		stopped = true;
		started = false;
		paused = false;
		fireStateChanged();
	}

}
