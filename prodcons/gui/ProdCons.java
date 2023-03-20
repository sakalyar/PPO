package prodcons.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import prodcons.model.ProdConsModel;
import prodcons.model.StdProdConsModel;
import prodcons.util.Logger;

public class ProdCons {

    // CONSTANTES

    private static final int APPLI_WIDTH = 800;
    private static final int APPLI_HEIGHT = 600;
    private static final int FONT_SIZE = 12;
    private static final String SEPARATION_LINE =
        "--------------------------------------\n";
    private static final String FROZEN_LINE = "***** frozen detection *****\n";
    private static final String TERMINATION_LINE =
            "***** extermination *****\n";

    // ATTRIBUTS
    
    private final Logger logger;

    private JButton starter;
    private JButton terminator;
    private JTextArea whiteBoard;
    private JFrame frame;
    private ProdConsModel model;
    private boolean neverFrozen;

    // CONSTRUCTEURS
    
    public ProdCons(int prod, int cons, int iter) {
        //LOG
        logger = new Logger();
        
        createModel(prod, cons, iter);
        createView();
        placeComponents();
        createController();
    }

    // COMMANDES
    
    public void display() {
        updateEnabledButtons(model.isRunning());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // OUTILS
    
    private void createModel(int prod, int cons, int iter) {
        model = new StdProdConsModel(prod, cons, iter);
    }

    private void createView() {
        frame = new JFrame("Producteurs & Consommateurs");
        starter = new JButton("Démarrer");
        terminator = new JButton("Exterminer");
        whiteBoard = new JTextArea();
        whiteBoard.setFont(new Font("Monospaced", Font.PLAIN, FONT_SIZE));
        whiteBoard.setEditable(false);
    }

    private void placeComponents() {
        JPanel p = new JPanel(); {
            p.add(starter);
            p.add(terminator);
        }
        frame.add(p, BorderLayout.NORTH);

        JScrollPane j = new JScrollPane(whiteBoard); {
            j.setPreferredSize(new Dimension(APPLI_WIDTH, APPLI_HEIGHT));
        }
        frame.add(j, BorderLayout.CENTER);
    }

    private void createController() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        starter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //LOG
                logger.reset();
                Logger.logMessage(SEPARATION_LINE);
                
                neverFrozen = true;
                model.start();
            }
        });
        
        terminator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                neverFrozen = false;
                whiteBoard.append(TERMINATION_LINE);
                
                //LOG
                Logger.logMessage("** model is exterminated");
                
                model.stop();
            }
        });
        
        model.addPropertyChangeListener(ProdConsModel.PROP_FROZEN,
            new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if (neverFrozen) {
                        Boolean frozen = (Boolean) evt.getNewValue();
                        if (frozen) {
                            neverFrozen = false;
                            whiteBoard.append(FROZEN_LINE);
                        }
                    }
                }
            }
        );
        model.addPropertyChangeListener(ProdConsModel.PROP_RUNNING,
            new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    Boolean running = (Boolean) evt.getNewValue();
                    if (running) {
                        whiteBoard.append(SEPARATION_LINE);
                    }
                    updateEnabledButtons(running);
                    
                    //LOG
                    Logger.logMessage("model is "
                            + (model.isRunning() ? "" : "not ") + "running");
                }
            }
        );
        model.addPropertyChangeListener(ProdConsModel.PROP_SENTENCE,
            new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    //LOG
                    logger.logIncoherentTime(evt.getNewValue().toString());
                    
                    whiteBoard.append(evt.getNewValue().toString());
                }
            }
        );
    }
    
    private void updateEnabledButtons(boolean running) {
        starter.setEnabled(!running);
        terminator.setEnabled(running);
    }
}
