package serie08;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import serie07.Swelling;
import serie08.SpeedometerModel.SpeedUnit;

public class Speedometer extends Observable {

	private JFrame mainFrame;
	private SpeedometerModel model;
	private JRadioButton radioSpeed1;
	private JRadioButton radioSpeed2;
	private JButton slower;
	private JButton faster;
	private JButton turnOnOff;
	private ButtonGroup radioButtonGroup;
	private ButtonGroup buttonGroup;
	private GraphicSpeedometer speedometer;
	
	public Speedometer() {
		createModel();
		createView();
		placeComponents();
		createController();
	}
	
	
	void display() {
		refresh();
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		
	}
	
	void createModel() {
		model = new StdSpeedometerModel();
	}
	
	void createView() {
		int frameWidth = 450;
		int frameHeight = 300;
		
		mainFrame = new JFrame("Speedometer");
		mainFrame.setPreferredSize(new Dimension(frameWidth, frameHeight));
		
		radioButtonGroup = new ButtonGroup();
		buttonGroup = new ButtonGroup();
		
		radioSpeed1 = new JRadioButton("km / h");
		radioSpeed2 = new JRadioButton("mi / h");
		radioButtonGroup.add(radioSpeed1);
		radioButtonGroup.add(radioSpeed2);
		
		radioSpeed2.setSelected(true);
		slower = new JButton("-");
		faster = new JButton("+");
		turnOnOff = new JButton("TURN ON");
		buttonGroup.add(slower);
		buttonGroup.add(faster);
		buttonGroup.add(turnOnOff);
		
		speedometer = new GraphicSpeedometer();
		speedometer.repaint();
	}
	
	void placeComponents() {
		JPanel p = new JPanel(new GridLayout(0,1));
		{ //--
			JPanel q = new JPanel();
			{ //--
				JPanel h = new JPanel(new GridLayout(0, 1));
				{
					//--
					h.add(radioSpeed1);
					h.add(radioSpeed2);
				} //--
				q.add(h);
			} //--
			p.add(q);
			JPanel q2  = new JPanel();
			{ //--
				JPanel h2 = new JPanel(new GridLayout(1, 0));
				{ //--
					h2.add(slower);
					h2.add(faster);
				} //--
				q2.add(h2);
			} //--
			p.add(q2);
			JPanel q3  = new JPanel();
			{ //--
				q3.add(turnOnOff);
			} //--
			p.add(q3);
		} //--
		mainFrame.add(p, BorderLayout.WEST);
		mainFrame.add(speedometer, BorderLayout.CENTER);
		
	}
	
	void createController() {
mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ((Observable) model).addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                refresh();
            }
        });

        slower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speedometer.test();
            }
        });
        
        radioSpeed1.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("Old one: " + model.getUnit());
        		model.setUnit(SpeedUnit.KMH);
        		System.out.println("New one: " + model.getUnit());
        		System.out.println();
        	}
        });
        
        radioSpeed2.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("Old one: " + model.getUnit());
        		model.setUnit(SpeedUnit.MIH);
        		System.out.println("New one: " + model.getUnit());
        		System.out.println();
        	}
        });
        
        turnOnOff.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if (model.isOn()) {
        			System.out.println("CLICKED");
        			turnOnOff.setText("TURN ON");
        			model.turnOff();
        		} else {
        			turnOnOff.setText("TURN OFF");
        			model.turnOn();
        		}
        	}
        });
        
	}
	
	private void refresh() {
		System.out.println("CHANGED");
		Container contentPane = mainFrame.getContentPane();
		contentPane.setBackground(Color.GREEN);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Speedometer().display();
			}
		});
	}

	
	
}
