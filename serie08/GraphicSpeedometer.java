package serie08;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;

import javax.swing.JComponent;

public class GraphicSpeedometer extends JComponent {
	// marge horizontale interne de part et d'autre du composant
	private static final int MARGIN = 40;
	// épaisseur de la ligne horizontale graduée
	private static final int THICK = 3;
	// demi-hauteur d'une graduation
	private static final int MARK = 5;
	// largeur de la base du triangle pour la tête de la flèche
	private static final int ARROW_BASE = 20;
	// épaisseur du corps de la flèche
	private static final int ARROW_THICK = 4;
	// hauteur du corps de la flèche
	private static final int ARROW_HEIGHT = 20;
	// hauteur préférée d'un GraphicSpeedometer
	private static final int PREFERRED_HEIGHT = 3 * (3 * MARK + ARROW_BASE / 2 + ARROW_HEIGHT);
	// facteur d'échelle pour l'espacement entre deux graduations
	private static final double ASPECT_RATIO = 1.25;
	// couleur bleu franc lorsque le moteur est allumé
	private static final Color BLUE = Color.BLUE;
	// couleur rouge franc lorsque le moteur est allumé
	private static final Color RED = Color.RED;
	// couleur bleu grisé lorsque le moteur est éteint
	private static final Color GRAYED_BLUE = new Color(0, 0, 255, 50);
	// couleur rouge grisé lorsque le moteur est éteint
	private static final Color GRAYED_RED = new Color(255, 0, 0, 50);
	// les vitesses affichées sont celles, entre 0 et model.getMaxSpeed(), qui sont les multiples de SPLIT_SIZE
	private static final int SPLIT_SIZE = 10;
	
    SpeedometerModel m;
    
	GraphicSpeedometer() {
		m = new StdSpeedometerModel();
		setPreferredSize(new Dimension(110, 150));
	}
	
	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(BLUE);
		g.fillRect(MARGIN, getHeight()/3, getWidth() - 2*MARGIN, THICK);
		int w = (getWidth() - 2*MARGIN) / 6;
		int h = getHeight()/3;
//		int xA = (int)(MARGIN + (w * m.getSpeed()) / m.getMaxSpeed() - ARROW_BASE / 2);
		int xA = (int)(MARGIN + (300 * m.getSpeed()) / m.getMaxSpeed() - ARROW_BASE / 2);
		int yA = (int)(h + THICK + 2 * MARK + ARROW_BASE / 2);
		System.out.println(m.getMaxSpeed());
		System.out.println(yA);
		System.out.println(xA);
		g.fillRect(100, yA, 10, 10);
//		int yA = (int)(h + THICK + 2 * MARK + ARROW_BASE / 2);
		
	}
	
	public void repaint() {
		setBackground(Color.WHITE);
	}
	public void test() {
		System.out.println("TEST");
	}
	
}
