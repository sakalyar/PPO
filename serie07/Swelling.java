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

public class Swelling {
	
	private JFrame mainFrame;
	private JLabel enterFactor;
	private JLabel percent;
	private JButton modify;
	private JButton reinitialize;
	private JTextField input;
	
	private SwellingModel model;
	
	public Swelling() {
		createModel();
        createView();
        placeComponents();
        createController();
	}
	
	private void initializeBounds() {
		JFrame wa = new JFrame();
		wa.pack();
		wa.dispose();
		System.out.println(mainFrame.getSize());
		model.setMin(mainFrame.getSize());
		model.setMax(Toolkit.getDefaultToolkit().getScreenSize());
	}
	
	public void display() {
		refresh();
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		initializeBounds();
	}
	
	private void createModel() {
		model = new StdSwellingModel();
	}
	
	private void createView() {
		final int frameWidth = 300;
		final int frameHeight = 300;
		
		mainFrame = new JFrame("Swelling");
		mainFrame.setPreferredSize(new Dimension(frameWidth, frameHeight));
		System.out.print(mainFrame.getHeight());
		enterFactor = new JLabel("Facteur: ");
		percent = new JLabel(" % ");
		input = new JTextField();
		modify = new JButton("Modifier");
		reinitialize = new JButton("Reinitialiser");
		model.setCurrent(new Dimension(frameWidth, frameHeight));
	}
	
	private void placeComponents() {
		JPanel p = new JPanel();
		{ //--
			p.add(enterFactor);
			p.add(input);
			p.add(percent);
			p.add(modify);
		} //--
		JPanel resetPanel = new JPanel();
		{
			resetPanel.add(reinitialize);
		}
		mainFrame.add(p, BorderLayout.NORTH);
		mainFrame.add(resetPanel, BorderLayout.CENTER);
	}
	
	private void createController() {
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		((Observable) model).addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                refresh();
            }
        });

        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	double scale = Double.parseDouble(input.getText());
                model.scaleCurrent(scale);
                System.out.println("Hello");
                
            }
        });        
	}
	
	private void refresh() {
		Container contentPane = mainFrame.getContentPane();
		System.out.println(contentPane.HEIGHT);
		System.out.println(model.current().height);
		mainFrame.setSize(model.current());
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Swelling().display();
			}
		});
	}
}
