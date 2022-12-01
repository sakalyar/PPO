package serie07;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Rainbow {

    // ATTRIBUTS
    
    private JFrame mainFrame;
    private JButton changeColorButton;
    private RainbowModel model;

    // CONSTRUCTEURS
    
    public Rainbow() {
        createModel();
        createView();
        placeComponents();
        createController();
    }
    
    // COMMANDES
    
    /**
     * Rend l'application visible au centre de l'écran.
     */
    public void display() {
        refresh();
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    // OUTILS
    
    private void createModel() {
        model = new StdRainbowModel();
    }
    
    private void createView() {
        final int frameWidth = 300;
        final int frameHeight = 300;
        
        mainFrame = new JFrame("Arc-en-ciel");
        mainFrame.setPreferredSize(new Dimension(frameWidth, frameHeight));
        
        changeColorButton = new JButton("Modifier");
    }
    
    private void placeComponents() {
        JPanel p = new JPanel();
        { //--
            p.add(changeColorButton);
        } //--
        mainFrame.add(p, BorderLayout.NORTH);
    }
    
    private void createController() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ((Observable) model).addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                refresh();
            }
        });

        changeColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.changeColor();
            }
        });
    }
    
    private void refresh() {
        Container contentPane = mainFrame.getContentPane();
        contentPane.setBackground(model.getColor());
    }

    // POINT D'ENTREE
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Rainbow().display();
            }
        });
    }
}
