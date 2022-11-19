package serie08;

import java.awt.Color;
import java.util.Observable;

public class StdSpeedometerModel extends Observable implements SpeedometerModel {
    
	private double step;
	private double max;
	private double speed;
	private boolean on;
	private SpeedUnit unit;
	
    
    public StdSpeedometerModel(double step, double max) {
    	this.step = step;
    	this.max = max;
    	unit = SpeedUnit.MIH; 
    	on = false;
    }
	
    public StdSpeedometerModel() {
    	unit = SpeedUnit.MIH;
    	on = false;
    }
	
	@Override
	public double getMaxSpeed() {
		if (getUnit() == SpeedUnit.KMH)
			return 100;
		return 60;
	}

	@Override
	public double getSpeed() {
		return speed;
	}

	@Override
	public double getStep() {
		return step;
	}

	@Override
	public SpeedUnit getUnit() {
		return unit;
	}

	@Override
	public boolean isOn() {
		setChanged();
        notifyObservers();
		return on;
	}

	@Override
	public void setUnit(SpeedUnit unit) {
		this.unit = unit;
	}

	@Override
	public void slowDown() {
		if (isOn()) {
			speed = Math.max(0, getSpeed() - getStep());
		}
		
	}

	@Override
	public void speedUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnOff() {
		setChanged();
        notifyObservers();
		on = false;
	}

	@Override
	public void turnOn() {
		setChanged();
        notifyObservers();
		on = true;
	}

}
