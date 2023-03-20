package prodcons.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import javax.swing.event.SwingPropertyChangeSupport;

import prodcons.model.actor.StdConsumer;
import prodcons.model.actor.StdProducer;
import prodcons.util.event.SentenceEvent;
import util.Contract;

public class StdProdConsModel implements ProdConsModel {

    // ATTRIBUTS STATIQUES

    private static final int MAX_VALUE = 100;

    // ATTRIBUTS

    private final Actor[] actors;
    private final Box box;
    private final int prodNumber;
    private final int consNumber;
    private PropertyChangeSupport support;
    private final FrozenDetector frozenDetector;
    private boolean running;
    protected volatile boolean frozen;
    // CONSTRUCTEURS

    public StdProdConsModel(int prod, int cons, int iter) {
        Contract.checkCondition(prod > 0 && cons > 0 && iter > 0);
        
        box = new UnsafeBox();
        prodNumber = prod;
        consNumber = cons;
        frozenDetector = new FrozenDetector();
        actors = new Actor[prodNumber + consNumber];
        for (int i = 0; i < prodNumber; i++) {
            actors[i] = new StdProducer(iter, MAX_VALUE, box);
            actors[i].addPropertyChangeListener(PROP_FROZEN, frozenDetector);
        }
        for (int i = prodNumber; i < prodNumber + consNumber; i++) {
            actors[i] = new StdConsumer(iter, box);
            actors[i].addPropertyChangeListener(PROP_FROZEN, frozenDetector);
        }
        
        support = new SwingPropertyChangeSupport(this, true);
    }
    
    private class FrozenDetector implements PropertyChangeListener {
    	
    	private volatile int consomateurActive = 0;
    	private volatile int consomatteurNB = 0;
    	private volatile int producteurNB = 0;
    	private volatile int producteurActive = 0;
    	private boolean active;
    	
    	public FrozenDetector() {
    		active = false;
			setFrozen(true);
    	}
    	
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getSource().getClass() == StdProducer.class) {
				consomatteurNB++;
				if ((boolean) evt.getNewValue() == true)
					producteurActive++;
				else 
					producteurActive--;
			} else if (evt.getSource().getClass() == StdConsumer.class) {
				producteurNB++;
				if ((boolean) evt.getNewValue() == true)
					consomateurActive++;
				else
					consomateurActive--;
			}
			active = (consomateurActive == 0 || producteurActive == 0) ? false : true;
		}
    }
    
    private class EraserTask implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < prodNumber; ++i)
				actors[i] = null;
		}
    }
    
    // REQUETES

    @Override
    public Box box() {
        return box;
    }

    @Override
    public Actor consumer(int i) {
        Contract.checkCondition(0 <= i && i < consNumber);

        return actors[prodNumber + i];
    }

    @Override
    public int consumersNb() {
        return consNumber;
    }

    @Override
    public boolean isFrozen() {
        /*****************/
        /** A COMPLETER **/
    	return frozen;
        /*****************/
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public Actor producer(int i) {
        Contract.checkCondition(0 <= i && i < prodNumber);

        return actors[i];
    }

    @Override
    public int producersNb() {
        return prodNumber;
    }
    
    // COMMANDES

    @Override
    public void addPropertyChangeListener(String pName,
                PropertyChangeListener lnr) {
        Contract.checkCondition(pName != null && lnr != null);

        support.addPropertyChangeListener(pName, lnr);
    }
    
    @Override
    public void start() {
        /*****************/
        /** A COMPLETER **/
    	box.clear();
    	setFrozen(false);
    	setRunning(false);
    	for (Actor act: actors) {
    		act.start();
    	}
        /*****************/
    }

    @Override
    public void stop() {
        /*****************/
        /** A COMPLETER **/
    	for (Actor act: actors) {
    		act.interruptAndWaitForTermination();
    	}
        /*****************/
    }

    // OUTILS

    private void setRunning(boolean b) {
        boolean oldRunning = isRunning();
        running = b;
        support.firePropertyChange(PROP_RUNNING, oldRunning, b);
    }
    private void setFrozen(boolean b) {
        boolean oldFrozen = isFrozen();
        frozen = b;
        support.firePropertyChange(PROP_RUNNING, oldFrozen, b);
    }
}
