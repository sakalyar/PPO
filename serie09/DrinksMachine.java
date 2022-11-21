package serie09;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import serie08.Speedometer;

public class DrinksMachine extends Observable {
	
	private DrinksMachineModel model;
	private JFrame mainFrame;
	
	private JButton cofee;
	private JButton chocolate;
	private JButton juice;
	private JLabel cofeePrice;
	private JLabel chocolatePrice;
	private JLabel juicePrice;
	
	private JButton insert;
	private JTextField insertedMoney;
	private JLabel cents1;
	
	private JButton cancel;
	private JButton result;
	private JLabel changeInfo;
	private JLabel credit;
	
	private JLabel drinksLabel;
	private JTextField drinks;
	private JLabel moneyLabel;
	private JTextField money;
	private JLabel cents2;
	
	
	
	public DrinksMachine() {
		createModel();
		createView();
		placeComponents();
		createController();
	}
	
	private void display() {
		refresh();
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}

	private void createModel() {
		model = new StdDrinksMachineModel();
		
	}
	
	
	private void createView() {
		mainFrame = new JFrame();
		mainFrame.setPreferredSize(new Dimension(600, 300));
		
		cofee = new JButton("cofee");
		chocolate = new JButton("chocolate");
		juice = new JButton("juice");
		cofeePrice = new JLabel("30 cents");
		chocolatePrice = new JLabel("45 cents");
		juicePrice = new JLabel("110 cents");
		
		insert = new JButton("Inserer");
		cancel = new JButton("Annuler");
		result = new JButton("Prenez votre boisson et/ou votre monnaie");
		changeInfo = new JLabel("Cet apareil rend la monnaie");
		credit = new JLabel("Vous disposez d\'un crédit de 0 cents");
		insertedMoney = new JTextField();
		insertedMoney.setPreferredSize(new Dimension(100, 24));
		cents1 = new JLabel("cents");
		
		drinksLabel = new JLabel("Boisson: ");
		drinks = new JTextField();
		drinks.setDisabledTextColor(Color.GRAY);
		
		moneyLabel = new JLabel("Monnaie: ");
		money = new JTextField();
		money.setDisabledTextColor(Color.GRAY);
		cents2 = new JLabel("cents");
	}
	
	

	private void placeComponents() {
		JPanel p = new JPanel(new GridLayout(2, 1));
		{ //--
			JPanel q = new JPanel(new FlowLayout());
			{ //--
				q.add(changeInfo);
			} //--
			p.add(q);
			q = new JPanel(new FlowLayout());
			{ //--
				q.add(credit);
			} //--
			p.add(q);
		} //--
		mainFrame.add(p, BorderLayout.NORTH);
		p = new JPanel(new GridLayout(3, 2));
		{ //--
			p.add(cofee);
			p.add(cofeePrice);
			p.add(chocolate);
			p.add(chocolatePrice);
			p.add(juice);
			p.add(juicePrice);
		} //--
		mainFrame.add(p, BorderLayout.WEST);
		p = new JPanel (new FlowLayout(FlowLayout.CENTER));
		{ //--
			JPanel q = new JPanel(new FlowLayout(FlowLayout.CENTER));
			{ //--
				q.add(insert);
				JPanel r = new JPanel(new FlowLayout(FlowLayout.CENTER));
				{ //--
					r.add(insertedMoney);
					r.add(cents1);
				} //--
				q.add(r);
				q.add(cancel);
			} //--
			p.add(q);
		} //--
		mainFrame.add(p, BorderLayout.EAST);
		p = new JPanel(new GridLayout(2, 1));
		{ //--
			JPanel q = new JPanel();
			{ //--
				q.add(drinksLabel);
				q.add(drinks);
			} //--
			p.add(q);
			q = new JPanel();
			{ //--
				q.add(moneyLabel);
				q.add(money);
				q.add(cents2);
			} //--
			p.add(q);
		} //--
		mainFrame.add(p, BorderLayout.EAST);
		
	}

	
	private void createController() {
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		((Observable) model).addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                refresh();
            }
        });
		
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int x = model.getCreditAmount();
				System.out.println(x);
				
			}
		});
		
	}
	
	private void refresh() {
		Container contentPane = mainFrame.getContentPane();
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new DrinksMachine().display();
			}
		});
	}
	
}
