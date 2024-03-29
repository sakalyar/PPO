package pet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pet.model.PetModel;
import pet.model.StdPetModel;

public class Pet {
    
    private JFrame frame;
    private JLabel statusBar;
    private JTextArea editor;
    private JScrollPane scroller;
    private PetModel model;
    private Map<Item, JMenuItem> menuItems;
    private JMenuBar menuBar = new JMenuBar();
    
    // CONSTRUCTEUR
    
    public Pet() {
        createModel();
        createView();
        placeMenuItemsAndMenus();
        placeComponents();
        createController();
    }
    
    //COMMANDE
    
    public void display() {
        setItemsEnabledState();
        updateStatusBar();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // OUTILS
    
    private void createModel() {
        model = new StdPetModel();
    }

    private void createView() {
        final Dimension prefSize = new Dimension(640, 480);
        final int fontSize = 14;
        System.out.println("OLDTEST");
        frame = new JFrame("Petit Éditeur de Texte");
        frame.setPreferredSize(prefSize);
        
        editor = new JTextArea();
        editor.setBackground(Color.BLACK);
        editor.setForeground(Color.LIGHT_GRAY);
        editor.setCaretColor(Color.RED);
        editor.setFont(new Font("Courier New", Font.PLAIN, fontSize));
        
        scroller = new JScrollPane();
        
        statusBar = new JLabel();
        
        menuItems = getMenuItemsMap();
    }
    
    /**
     * Création de la correspondance Item -> JMenuItem.
     */
    private Map<Item, JMenuItem> getMenuItemsMap() {
        /*****************/
        /** A COMPLETER **/
    	Map<Item, JMenuItem> menuMap = new HashMap<Item, JMenuItem>();
    	for (Item item : Item.values()) {
    		menuMap.put(item, new JMenuItem(item.label()));
    	}
    	return menuMap;
        /*****************/
    }
    
    /**
     * Place les menus et les éléments de menu sur une barre de menus, et cette
     *  barre de menus sur la fenêtre principale.
     */
    private void placeMenuItemsAndMenus() {
        /*****************/
        /** A COMPLETER **/
    	
    	for (Menu menu : Menu.values()) {
    		JMenu newMenu = new JMenu(menu.label());
    		for (Item it : menu.STRUCT.get(menu)) {
    			JMenuItem item;
    			if (it != null) {
    				item = menuItems.get(it);
    				newMenu.add(item);
    			}
    			else {
    				newMenu.addSeparator();
    			}
    		}
    		menuBar.add(newMenu);
    	}
    	frame.add(menuBar, BorderLayout.NORTH);
    	frame.setVisible (true);
        /*****************/
    }
    
    private void placeComponents() {
        frame.add(scroller, BorderLayout.CENTER);
        
        JPanel p = new JPanel(new GridLayout(1, 0));
        { //--
            p.setBorder(
                BorderFactory.createCompoundBorder(
                    BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
                    BorderFactory.createEmptyBorder(3, 5, 3, 5)
                )
            );
            p.add(statusBar);
        } //--
        
        frame.add(p, BorderLayout.SOUTH);
    }
    private void refresh() {
		// TODO Auto-generated method stub
		
	}
    private void createController() {
        /*
         * L'opération de fermeture par défaut ne doit rien faire car on se
         *  charge de tout dans un écouteur qui demande confirmation puis
         *  libère les ressources de la fenêtre en cas de réponse positive.
         */
        /*****************/
        /** A COMPLETER **/
//    	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	
        /*****************/
        
        /*
         * Observateur du modèle.
         */
        /*****************/
        /** A COMPLETER **/
    	model.addChangeListener(new ChangeListener() {
    		@Override
    		public void stateChanged(ChangeEvent e) {
    			refresh();
    		}
    	});
        /*****************/
        
        /*
         * Écouteurs des items du menu
         */
        menuItems.get(Item.NEW).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*****************/
                /** A COMPLETER **/
            	System.out.println("NEWTEST");  
//            	scroller = new JScrollPane(editor);
            	scroller.setViewportView(editor);
            	updateStatusBar();
//            	placeComponents();
//            	frame.add(scroller,BorderLayout.CENTER);
                /*****************/
            }//
        });
        menuItems.get(Item.NEW_FROM_FILE).addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /*****************/
                    /** A COMPLETER **/
                	System.out.println("NEWTEST2");        
                    /*****************/
                }
            }
        );
        menuItems.get(Item.OPEN).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*****************/
                /** A COMPLETER **/
                /*****************/
            }
        });
        menuItems.get(Item.REOPEN).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*****************/
                /** A COMPLETER **/
                /*****************/
            }
        });
        menuItems.get(Item.SAVE).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*****************/
                /** A COMPLETER **/
                /*****************/
            }
        });
        menuItems.get(Item.SAVE_AS).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*****************/
                /** A COMPLETER **/
                /*****************/
            }
        });
        menuItems.get(Item.CLOSE).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*****************/
                /** A COMPLETER **/
                /*****************/
            }
        });
        menuItems.get(Item.CLEAR).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*****************/
                /** A COMPLETER **/
                /*****************/
            }
        });
        menuItems.get(Item.QUIT).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*****************/
                /** A COMPLETER **/
                /*****************/
            }
        });
    }
    
    /**
     * Gère l'état de la disponibilité des éléments du menu en fonction de
     *  l'état du modèle.
     */
    private void setItemsEnabledState() {
        /*****************/
        /** A COMPLETER **/
    	if (model.getFile() == null) {    		
    		JMenu m = menuBar.getMenu(1);
    		JMenuItem it = m.getItem(0);
    		it.setEnabled(false);
    	}
    	
    	if (model.getDocument() == null) {
    		JMenu m = menuBar.getMenu(0);
    		JMenuItem it = m.getItem(5);
//    		System.out.println(it.toString());
//    		it.setEnabled(false);
//    		it = m.getItem(6);
//    		it.setEnabled(false);
    	}
    	
    	if (model.getFile() == null && model.getDocument() == null && !model.isSynchronized()) {
    		JMenu m = menuBar.getMenu(0);
    		JMenuItem it = m.getItem(3);
    		it.setEnabled(false);
    		it = m.getItem(4);
    		it.setEnabled(false);
    	}
    	
        /*****************/
    }
    
    /**
     * Met à jour le Viewport du JScrollPane en fonction de la présence d'un
     *  document dans le modèle.
     * Remplace le document de la zone de texte par celui du modèle quand c'est
     *  nécessaire.
     */
    private void updateScrollerAndEditorComponents() {
        /*****************/
        /** A COMPLETER **/
    	
        /*****************/
    }
    
    /**
     * Met à jour la barre d'état.
     */
    private void updateStatusBar() {
        /*****************/
        /** A COMPLETER **/
    	if (model.getFile() == null) {
    		statusBar.setText("Fichier : * <aucun>");
    	}
        /*****************/
    }
    
    /**
     * Demande une confirmation de poursuite d'action.
     * @post
     *     result == true <==>
     *         le modèle était synchronisé
     *         || il n'y avait pas de document dans le modèle
     *         || le document était en cours d'édition mais l'utilisateur
     *            a répondu positivement à la demande de confirmation
     */
    private boolean confirmAction() {
        /*****************/
        /** A COMPLETER **/
    	return false;
        /*****************/
    }
    
    /**
     * Demande une confirmation d'écrasement de fichier.
     * @pre
     *     f != null
     * @post
     *     result == true <==>
     *         le fichier n'existe pas
     *         || le fichier existe mais l'utilisateur a répondu positivement
     *            à la demande de confirmation
     */
    private boolean confirmReplaceContent(File f) {
        /*****************/
        /** A COMPLETER **/
        /*****************/return false;
    }
    /**
     * Toute erreur inexpliquée de l'application doit être interceptée et
     *  transformée en message présenté dans une boite de dialogue.
     */
    private void displayError(String m) {
        /*****************/
        /** A COMPLETER **/
        /*****************/
    }
    
    /**
     * Permet au client de sélectionner un fichier de sauvegarde en choisissant
     *  un nom de fichier à l'aide d'un JFileChooser.
     * Si le nom de fichier choisi n'existe pas encore, un nouveau fichier est
     *  créé avec ce nom.
     * Retourne null si et seulement si :
     * - l'utilisateur a annulé l'opération,
     * - le nom choisi correspond à un élément préexistant du système de fichier
     *   mais cet élément n'est pas un fichier
     * - le nom choisi ne correspond pas à un élément préexistant du système de
     *   fichier mais le fichier n'a pas pu être créé.
     * Dans les deux derniers cas, une boite de dialogue vient de plus décrire
     *  le problème.
     */
    private File selectSaveFile() {
        /*****************/
        /** A COMPLETER **/return null;
        /*****************/
    }
    
    /**
     * Permet au client de sélectionner un fichier à charger en choisissant
     *  un nom de fichier à l'aide d'un JFileChooser.
     * Retourne null si et seulement si :
     * - l'utilisateur a annulé l'opération,
     * - le nom choisi ne correspond pas un fichier existant.
     * Dans ce dernier cas, une boite de dialogue vient de plus décrire
     *  le problème.
     */
    private File selectLoadFile() {
        /*****************/
        /** A COMPLETER **/
    	return null;
        /*****************/
    }
}
