package serie07;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observer;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class StdSwellingModel extends Observable implements SwellingModel {

	private int min;
	private int max;
	private int w;
	private int h;
	
	private Dimension dim;
	
	public StdSwellingModel(int w, int h) {
		dim = new Dimension(w, h);
		this.w = w;
		this.h = h;
	}
	
	public StdSwellingModel() {
		setCurrent(new Dimension(0, 0));
	}
	
	@Override
	public Dimension current() {
		return new Dimension(dim);
	}

	@Override
	public boolean isSurroundedBy(Dimension d) {
		if (d != null) {
			
		}
		return false;
	}

	@Override
	public boolean isSurrounding(Dimension d) {
		// TODO Auto-generated method stub
		return false;
	}

	//Retravailler
	@Override
	public boolean isValidScaleFactor(double f) {
		if (f >= MIN_FACTOR) {
			Dimension d = new Dimension((int) (w * (1 + f / 100)), (int) (h * (1 + f / 100)));
		}
		return false;
	}

	@Override
	public Dimension max() {
		return new Dimension(max, max);
	}


	@Override
	public Dimension min() {
		return new Dimension(min, min);
	}

	@Override
	public void scaleCurrent(double f) {
		dim.width = (int) (dim.width * (1 + f / 100));
		dim.height = (int) (dim.height  * (1 + f / 100));
		setChanged();
        notifyObservers();
	}

	@Override
	public void setCurrent(Dimension d) {
		dim = d;
	}

	@Override
	public void setMax(Dimension d) {
		max = d.height;
	}

	@Override
	public void setMin(Dimension d) {
		min = d.height;
	}


}
