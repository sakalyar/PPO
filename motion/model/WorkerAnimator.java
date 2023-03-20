package motion.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;

//import motion.model.WorkerAnimator.TickLoop;

public class WorkerAnimator extends AbstractAnimator {

	private int speed;
	private int delay;
//	int value;
	private final int maxSpeed;
	private volatile boolean started;
	private volatile boolean paused;
	private volatile boolean stopped;
	private PropertyChangeSupport pcs;
	private SwingWorker<Object, Object> tickThread;
	Object lock;
	
	public WorkerAnimator(int max) {
		super(max);
		System.out.println("WA créé 1");

		speed = 0;
		delay = (int) 1000;
		maxSpeed = max;
		tickThread = new TickLoop();
//		tickThread.
		System.out.println("WA créé 2");
		tickThread.execute();
	}
	
	@Override
	public void start() {
		started = true;
		stopped = false;
		paused = false;
		fireStateChanged();
		tickThread.execute();
	}
	
	public class TickLoop extends SwingWorker<Object, Object>{
		public Void doInBackground() throws Exception {
			System.out.println("edrzer");
			while(!hasStopped()) {
				System.out.println("e");
				if (isPaused()) {
					sleep();
				} else {
					System.out.println(delay);
					Thread.sleep(delay);
					sendTickNotification();
				}
			}
			return null;
		}
		
		private void sendTickNotification() {
			System.out.println("sendTN");
			fireTickOccured();
		}
		
		private void sleep() throws InterruptedException {
			try { 
				tickThread.wait();
			} catch (InterruptedException e) {}
		}
		
		public void pause() {
			synchronized(lock) {
				while(isPaused()) {
					try {
						lock.wait();
					} catch (InterruptedException e) {}
				}
			}
		}
		
		public void resume() {
			synchronized(lock) {
				paused = false;
				lock.notifyAll();
			}
			fireStateChanged();
		}
		
		
	}
	
	public void resume() {
		synchronized(lock) {
			paused = false;
			lock.notifyAll();
		}
		fireStateChanged();
	}
	@Override
	public int getSpeed() {
		return speed;
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
		// TODO Auto-generated method stub
		return !paused;
	}

	@Override
	public boolean isRunning() {
		return started && !paused;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setSpeed(int d) {
		speed = d;
		fireStateChanged();
	}

	

	@Override
	public void stop() {
		stopped = true;
		fireStateChanged();
		tickThread.cancel(true);
	}


}
