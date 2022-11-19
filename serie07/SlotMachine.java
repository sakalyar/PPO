package serie07;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SlotMachine {

	private JFrame mainFrame;
	private JButton play;
	private JLabel won;
	private JLabel lost;
	private JTextField result;
	private JLabel payout;
	private int lettersIndeces[] = new int[3];
	
	private SlotModel model;
	
	SlotMachine() {
		createModel();
		createView();
		placeComponents();
		createController();
	}
	
	public void display() {
		refresh();
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		initializeBounds();
	}
	
	private void initializeBounds() {
		JFrame wa = new JFrame();
		wa.pack();
		wa.dispose();
	}
	
	private void createModel() {
		model = new StdSlotModel(lettersIndeces);
	}
	
	private void createView() {
		final int frameWidth = 300;
		final int frameHeight = 300;
		
		mainFrame = new JFrame("Slot Machine");
		mainFrame.setPreferredSize(new Dimension(frameWidth, frameHeight));
		System.out.println(frameWidth);
		play = new JButton("Tentez votre chance!");
		won = new JLabel("Gains: 0");
		lost = new JLabel("Pertes: 0");
		result = new JTextField();
		payout = new JLabel("Vous venez de gagner: 0");
		//model.setCurrent(new Dimension(frameWidth, frameHeight));
		
	}
	
	private void placeComponents() {
		JPanel p = new JPanel();
		{
			//--
				p.add(play);
				p.add(result);
			//--
		}
		JPanel wonLostPanel = new JPanel();
		{
			//--
				wonLostPanel.add(won);
				wonLostPanel.add(lost);
			//--
		}
		mainFrame.add(p, BorderLayout.NORTH);
		System.out.println("Added all");
		System.out.println("Executed");
		mainFrame.add(wonLostPanel, BorderLayout.CENTER);
	}
	
	private void createController() {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		((Observable) model).addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                refresh();
            }
        });
		
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.gamble();
				
				result.setText(model.result());
				System.out.println("Changed to: " + model.result());
				System.out.println("Executed successfully!");
			}
		});
		
	}
	
	void refresh() {
		Container contentPane = mainFrame.getContentPane();
		System.out.println("Refreshed");
		mainFrame.setSize(300, 300);
		System.out.println("Length: " + mainFrame.HEIGHT);
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SlotMachine().display();
			}
		});
	}
	
}
