package serie009;

import java.util.regex.Matcher;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import serie08.Speedometer;

public class DrinksMachine extends Observable {
	
	private DrinksMachineModel model;
	private JFrame mainFrame;
	
	private JLabel changeInfo;
	private JLabel credit;
	
	private JButton coffee;
	private JButton chocolate;
	private JButton juice;
	private JLabel coffeePrice;
	private JLabel chocolatePrice;
	private JLabel juicePrice;
	
	private JButton insert;
	private JTextField insertedMoney;
	private JLabel cents1;
	
	private JButton cancel;
	private JButton result;
	
	
	private JLabel drinksLabel;
	private JTextField drinks;
	
	private JLabel moneyLabel;
	private JTextField money;
	private JLabel cents2;
	
	//Error window
	JFrame error;
	
	
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
		
		coffee = new JButton("coffee");
		chocolate = new JButton("chocolate");
		juice = new JButton("juice");
		coffeePrice = new JLabel("30 cents");
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
		drinks.setPreferredSize(new Dimension(100, 24));
		drinks.setEditable(false);
		drinks.setDisabledTextColor(Color.GRAY);
		
		moneyLabel = new JLabel("Monnaie: ");
		money = new JTextField();
		money.setEditable(false);
		money.setPreferredSize(new Dimension(100, 24));
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
			p.add(coffee);
			p.add(coffeePrice);
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
		p = new JPanel(new GridLayout(2, 1));
		{ //--
			JPanel q = new JPanel(new GridLayout(1, 2));
			{ //--
				JPanel r = new JPanel();
				{ //--
					r.add(drinksLabel);
					r.add(drinks);
				} //--
				q.add(r);
				r = new JPanel();
				{ //--
					r.add(moneyLabel);
					r.add(money);
					r.add(cents2);
				} //--
				q.add(r);
			} //--
			p.add(q);
			q = new JPanel();
			{ //--
				q.add(result);
			} //--
			p.add(q);
		} //--
		mainFrame.add(p, BorderLayout.SOUTH);
		
		p = new JPanel(new BorderLayout());
		{ //--
			JPanel q = new JPanel(new GridLayout(2, 2));
			{ //--
				q.add(insert);
				JPanel r = new JPanel();
				{ //--
					r.add(insertedMoney);
					r.add(cents2);
				} //--
				q.add(r);
				q.add(cancel);
			} //--
			p.add(q, BorderLayout.NORTH);
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
		
		coffee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(model.getCashNb(CoinTypes.TWENTY));
				if (model.getCreditAmount() < DrinkTypes.COFFEE.getPrice()) {
					error = new JFrame();
					JOptionPane.showMessageDialog(error, "Le crédit n\'est pas suffisant!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				if (model.getDrinkNb(DrinkTypes.COFFEE) < 1) {
					error = new JFrame();
					JOptionPane.showMessageDialog(error, "Il n\'y a pas de boisson vouz avez commandé!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				System.out.println(model.getCreditAmount());
				
				drinks.setText(DrinkTypes.COFFEE.toString());
				System.out.println(model.getCreditAmount());
//				System.out.println(model.getDrinkNb(DrinkTypes.COFFEE));
				model.selectDrink(DrinkTypes.COFFEE);
				System.out.println(model.getDrinkNb(DrinkTypes.COFFEE));

			}
		});
		
		chocolate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(model.getDrinkNb(DrinkTypes.CHOCOLATE));
				if (model.getCreditAmount() < DrinkTypes.CHOCOLATE.getPrice()) {
					error = new JFrame();
					JOptionPane.showMessageDialog(error, "Le crédit n\'est pas suffisant!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				if (model.getDrinkNb(DrinkTypes.COFFEE) < 1) {
					error = new JFrame();
					JOptionPane.showMessageDialog(error, "Il n\'y a pas de boisson vouz avez commandé!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				drinks.setText(DrinkTypes.CHOCOLATE.toString());
			}
		});
		
		juice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.selectDrink(DrinkTypes.ORANGE_JUICE);
			}
		});
		
		
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.cancelCredit();	
				System.out.println(model.getCreditAmount());
				credit.setText("Vous disposez d\'un crédit de 0 cents");
			}
		});
		
		insert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String pattern = "[0-9]+";
				Pattern p = Pattern.compile("[0-9]+");
				
				Matcher m = p.matcher(credit.getText()); 
				if (!m.find()) {
					System.out.println("CRITICAL ERROR");
					return;
				}
				
				Integer insertedValue = Integer.parseInt(insertedMoney.getText());
				CoinTypes c = CoinTypes.getCoinType(insertedValue);
				if (c == null) {
					error = new JFrame();
					JOptionPane.showMessageDialog(error, "L\'automate n\'accepte pas de telles pieces", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				model.insertCoin(c);
				Integer newCredit = Integer.parseInt(m.group(0)) + insertedValue;
				credit.setText("Vous disposez d\'un crédit de " + String.valueOf(newCredit) + " cents");
				insertedMoney.setText(null);
				
				System.out.println(model.getCreditAmount());
//				model.fillCash(c, model.getCashNb(c)+1);
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
