package motion.model;

public class ThreadAnimator extends AbstractAnimator {

	private int speed;
	private int delay;
	private boolean started;
	private boolean paused;
	private boolean stopped;
	private Thread tickThread;

	
	public ThreadAnimator(int max) {
		super(max);
		tickThread = new Thread(
				new TickLoop()
				);
	}
	
	@Override
	public void start() {
		started = true;
		stopped = false;
		paused = false;
		fireStateChanged();
		tickThread.start();
	}
	
	private class TickLoop implements Runnable {
		public void run() {
			while(!hasStopped()) {
				if (isPaused()) {
					doPause();
				} else {
					sendTickNotification();
				}
			}
			
		}
		private void sendTickNotification() {
			tickThread.notifyAll();
		}
		
		public void doPause() {
			try {
				synchronized(ThreadAnimator.this) {
					while(isPaused()) {
						ThreadAnimator.this.wait();
					}
				}
			} catch (InterruptedException e) {
				resume();
			}
			
		}
		
		public synchronized void resume() {
			paused = false;
			this.notify();
			fireStateChanged();
		}
		
	}
	
	
	
	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStarted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasStopped() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPaused() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isResumed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSpeed(int d) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void stop() {
		stopped = true;
		fireStateChanged();
		tickThread.interrupt();
	}

}
