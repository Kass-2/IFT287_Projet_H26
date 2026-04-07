package tp.gestion;

import org.bson.Document;
import tp.CollantException;
import tp.bdd.Connexion;
import tp.collections.GestionCollectionsProduit;

public class GestionTransactionsProduit extends GestionTransactions {

    private final GestionCollectionsProduit gestionProduits;

    public GestionTransactionsProduit(Connexion cx) {
        super(cx);
        this.gestionProduits = new GestionCollectionsProduit(cx);
    }

    /**
     * Ajoute un nouveau produit.
     * ajouterProduit <nom> <prix> <coût> <catégorie> <nomProducteur>
     */
    public void ajouterProduit(String nom, double prix, double cout,
                               String categorie, String nomProducteur)
            throws CollantException, Exception {

        // Validation : le produit ne doit pas déjà exister
        if (gestionProduits.existe(nom)) {
            throw new CollantException("Le produit '" + nom + "' existe déjà.");
        }

        // Validation : les valeurs numériques doivent être positives
        if (prix < 0) {
            throw new CollantException("Le prix ne peut pas être négatif.");
        }
        if (cout < 0) {
            throw new CollantException("Le coût ne peut pas être négatif.");
        }

        cx.demarreTransaction();
        try {
            gestionProduits.inserer(nom, prix, cout, categorie, nomProducteur);
            cx.executeTransaction();
            System.out.println("Produit '" + nom + "' ajouté avec succès.");
        } catch (Exception e) {
            cx.annuleTransaction();
            throw e;
        }
    }

    /**
     * Affiche toutes les informations d'un produit.
     * afficherProduit <nom>
     */
    public void afficherProduit(String nom) throws CollantException {

        Document doc = gestionProduits.trouver(nom);

        if (doc == null) {
            throw new CollantException("Le produit '" + nom + "' n'existe pas.");
        }

        System.out.println("=== Produit ===");
        System.out.println("Nom        : " + doc.getString("nom"));
        System.out.println("Prix       : " + doc.getDouble("prix") + " $");
        System.out.println("Coût       : " + doc.getDouble("cout") + " $");
        System.out.println("Catégorie  : " + doc.getString("categorie"));
        System.out.println("Producteur : " + doc.getString("nomProducteur"));
    }

    /**
     * Supprime un produit.
     * supprimerProduit <nom>
     */
    public void supprimerProduit(String nom) throws CollantException, Exception {

        if (!gestionProduits.existe(nom)) {
            throw new CollantException("Le produit '" + nom + "' n'existe pas.");
        }

        cx.demarreTransaction();
        try {
            gestionProduits.supprimer(nom);
            cx.executeTransaction();
            System.out.println("Produit '" + nom + "' supprimé avec succès.");
        } catch (Exception e) {
            cx.annuleTransaction();
            throw e;
        }
    }
}