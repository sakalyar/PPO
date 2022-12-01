package serie09.copy;

/**
 * 
 * @inv
 *     getCash() != null && getChange() != null
 *     forall c in CoinTypes :
 *         0 <= getChange().getNumber(c) <= getCash().getNumber(c)
 */
public interface ChangeAlgorithm {
    
    // REQUETES
    
    /**
     * Les pièces disponibles initialement.
     */
    MoneyAmount getCash();
    
    /**
     * Une solution si la dernière recherche en a trouvé une.
     * @pre solutionFound()
     */
    MoneyAmount getChange();
    
    /**
     * Indique si une solution a été trouvée.
     */
    boolean solutionFound();
    
    // COMMANDES
    
    /**
     * Cherche une solution pour la somme représentée par amount en fonction
     *  des pièces disponibles dans getCash().
     * @pre
     *     amount >= 0
     * @post
     *     solutionFound() ==>
     *         il existe une solution et getChange() en est une
     */
    void computeChange(int amount);
}
