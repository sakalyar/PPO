package phrase.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import phrase.model.Filter;
import phrase.model.filters.Factor;
import phrase.model.filters.Prefix;
import phrase.model.filters.RegExp;
import phrase.model.filters.Suffix;
import phrase.view.FilteringPane;

public class PhraseAppli {

    // ATTRIBUTS STATIQUES

    private static final String DEFAULT_DIR = System.getProperty("user.home");

    // ATTRIBUTS

    private JFrame frame;
    private FilteringPane filteringPane;
    private JButton loader;
    private JFileChooser chooser;

    // CONSTRUCTEURS
    
    public PhraseAppli() {
        createView();
        placeComponents();
        createController();
    }
    
    // COMMANDES
    
    public void display() {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // OUTILS
    
    private void createView() {
        frame = new JFrame("Filtrage de phrases");
        filteringPane = new FilteringPane(createFilters());
        loader = new JButton("Charger une liste de phrases");
        chooser = new JFileChooser(DEFAULT_DIR);
    }

    private void placeComponents() {
        JPanel p = new JPanel();
        { //--
            p.add(loader);
        } //--
        frame.add(p, BorderLayout.NORTH);
        
        frame.add(filteringPane, BorderLayout.CENTER);
    }

    private void createController() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        loader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = chooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File f = chooser.getSelectedFile();
                    Worker w = new Worker(f);
                    try {
                    	w.populateFilteringPane(f);
                    } catch (Exception ex) {
                    	System.out.println("Erreur de téléchargement du fichier!");
                    }
                }
            }
        });
    }
    
    private static Filter[] createFilters() {
        return new Filter[] {
                new Prefix(), new Factor(), new Suffix(), new RegExp()
        };
    }
    
    private void delayAction() {
        final int delay = 20;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            // rien, on quitte
        }
    }
    
    private class Worker extends SwingWorker<Object, Object> {
    	public File file;
    	public Worker(File file) {
    		this.file = file;
    	}
		@Override
		protected Void doInBackground() throws Exception {
			populateFilteringPane(file);
			return null;
		}
		public void populateFilteringPane(File in) throws FileNotFoundException {
			File file = new File(in.getAbsolutePath());
	        Scanner sc = new Scanner(file);
	        while(sc.hasNextLine()){
	            System.out.println(sc.nextLine());
	        }
	        filteringPane.addElement(sc.toString());
	        sc.close();
	    }
    }
}
