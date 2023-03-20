package notes.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import notes.util.event.ResourceListener;
import notes.util.event.ResourceSupport;
import util.Contract;

class NoteFileManager {

    // CONSTANTES STATIQUES

    private static final String NL = System.getProperty("line.separator");

    // ATTRIBUTS
    
    private final ResourceSupport support;

    // CONSTRUCTEURS

    NoteFileManager() {
        support = new ResourceSupport(this);
    }

    // REQUETES
    
    ResourceListener[] getResourceListeners() {
        return support.getListeners();
    }

    // COMMANDES

    /**
     * @pre
     *     lnr != null
     * @post
     *     lnr a été ajouté à la séquence des écouteurs
     */
    void addResourceListener(ResourceListener lnr) {
        Contract.checkCondition(lnr != null);
        
        support.add(lnr);
    }

    void scanFile(File f) throws IOException {
    	BufferedReader r = new BufferedReader(new FileReader(f));
		try {
			String s = r.readLine();
			while(s != null) {
				s = r.readLine();
				delayAction();
				support.fireLineLoaded(s);
			}
		} catch (IOException e) {
			support.fireFailureOccured(e);
			e.printStackTrace();
		} finally {
			r.close();
		}
    }

    /**
     * @pre
     *     lnr != null
     * @post
     *     lnr a été retiré de la séquence des écouteurs
     */
    void removeResourceListener(ResourceListener lnr) {
        Contract.checkCondition(lnr != null);
        
        support.remove(lnr);
    }

    void saveListToFile(List<String> lines, File f) throws IOException {
    	BufferedWriter r = new BufferedWriter(new FileWriter(f));
		try {
			for (String s : lines) {
				r.write(s, 0, s.length());
				support.fireDataSaved(s);
				delayAction();
			}
		} catch (IOException e) {
			support.fireFailureOccured(e);
			e.printStackTrace();
		} finally {
			r.close();
		}
    }

    // OUTILS

    /**
     * Pour ralentir les actions de lecture ou d'écriture.
     */
    private void delayAction() {
        final int delay = 100;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            // rien, on quitte
        }
    }
}
