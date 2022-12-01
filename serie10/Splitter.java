package serie10;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Splitter {
	
	private int CONFIG_CHANGE;
	private String FILE_CHANGE;
	
	private JFileChooser fileChooser;
	private JFrame returnFrame;
	
	private JTextField splitFileName;
	private JButton browse;
	private JComboBox splitFragmentsNb;
	private JTextField splitFragmentsSize;
	private JButton splitFile;
	private JTextArea description;
	private JFrame frame;
	private final String initialDescription = 
			"Taille total du fichier: non défini\n\n"
			+ "Description des fragments: non défini\n\n"
			+ "Taille moyenne d\'un fragement: non défini"; 
	private String FILE_NAME;
	private SplitManager model;
	
	public Splitter() {
		createModel();
		createView();
		placeComponents();
		createController();
	}
	
	private void display() {
		refresh();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void createModel() {
		model = new StdSplitManager();
		
	}
	
	private void createView() {
		frame = new JFrame();
		
		splitFileName = new JTextField(16);
		browse = new JButton ("Parcourir");
		
		splitFragmentsNb = new JComboBox<Integer>();
		splitFragmentsNb.setEnabled(false);
		splitFragmentsSize = new JTextField();
		
		splitFragmentsSize.setPreferredSize(new Dimension(80, 20));
		splitFile = new JButton ("Fragmenter!");
		splitFile.setEnabled(false);
		description = new JTextArea(initialDescription);
		description.setEditable(false);
		description.setDisabledTextColor(Color.GRAY);
		
		// Pour ouvrir la fenetre du chargement du fichier
		returnFrame = new JFrame();
		
	}
	
	
	private void placeComponents() {
		
		//Création d'un main frame 
		JPanel p = new JPanel();
		{ //--
			JLabel fileLabel = new JLabel("Fichier à fragmenter");
			p.add(fileLabel);
			p.add(splitFileName);
			p.add(browse);
		} //--
		frame.add(p, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		{ //--
			scrollPane.setViewportView(description);
		} //--
		frame.add(scrollPane, BorderLayout.CENTER);
		
		p = new JPanel(new GridLayout(2, 1));
		{ //--
			JPanel q = new JPanel(new BorderLayout());
			{ //--
				JPanel r = new JPanel(new GridLayout(2, 2));
				{
					JPanel s = new JPanel(new FlowLayout(FlowLayout.RIGHT));
					{ //--
						JLabel fragmentsLabel = new JLabel("Nb. fragments");
						s.add(fragmentsLabel);
					} //--
					r.add(s);
					s = new JPanel(new FlowLayout(FlowLayout.LEFT));
					{ //--
						s.add(splitFragmentsNb);
					} //--
					r.add(s);
					s = new JPanel(new FlowLayout(FlowLayout.RIGHT));
					{ //--
						JLabel size = new JLabel("Taille des fragments"); 
						s.add(size);
					} //--
					r.add(s);
					s = new JPanel(new FlowLayout(FlowLayout.LEFT));
					{ //--
						s.add(splitFragmentsSize);
						JLabel octetsLabel = new JLabel("octets");
						s.add(octetsLabel, BorderLayout.WEST);
					} //--
					r.add(s);
				}
				q.add(r, BorderLayout.SOUTH);
			} //--
			p.add(q);
			q = new JPanel(new FlowLayout(FlowLayout.CENTER));
			{ //--
				q.add(splitFile, BorderLayout.NORTH);
			} //--
			p.add(q);
		} //--
		frame.add(p, BorderLayout.WEST);
		
		JLabel AddtitionalInformation = new JLabel("(*) Il s\'agit de la taille de chaque fragment à un octet près," + 
													"sauf peut-etre le dernier fragment");
		frame.add(AddtitionalInformation, BorderLayout.SOUTH);
		
		// Création d'une fenetre d'ouverture:
		p = new JPanel();
		{ //--
			LayoutManager layout = new FlowLayout();
			p.setLayout(layout);
		} //--
		returnFrame.add(p);
		
	}
	private void createController() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fileChooser = new JFileChooser();
		
        ((Observable) model).addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
            	//???
            	if (arg instanceof Integer) {
            		if ((Integer)arg == CONFIG_CHANGE) {
            			refresh();
            		};
            	} else if (arg instanceof String) {
            		if ((String) arg == FILE_CHANGE) {
            			
            			if (model.canSplit()) {
            				System.out.println("can split");
            				refresh();
            			} else {
            				
            			}
            		};
            	}
            }
        });
        
        browse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(returnFrame);
				if (result == JFileChooser.APPROVE_OPTION) {
					File f = fileChooser.getSelectedFile();
					System.out.println("Opening: " + f.getName() + ".\n");
					System.out.println("OK!");
					try (BufferedReader reader = new BufferedReader (new FileReader (fileChooser.getSelectedFile()))) {
						
						StringBuilder resultStringBuilder = new StringBuilder();
						String line;
						while ((line = reader.readLine()) != null) {
				            resultStringBuilder.append(line).append("\n");
				        }
						model.changeFor(null);
						System.out.println();
//						System.out.println(resultStringBuilder);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				} else {
					System.out.println("Out of luck!");
				}
			}
        });
        
	}
	
	public void refresh() {
		Container contentPane = frame.getContentPane();
		splitFragmentsSize.setText(model.getSplitsSizes() == null ? "" : String.valueOf(model.getSplitsSizes()[0]));
		if (model.canSplit()) {
			splitFile.setText(model.getDescription());			
		}
		splitFragmentsNb.setSelectedIndex(model.getMaxFragmentNb()-1);
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Splitter().display();
            }
        });
    }
	
}
