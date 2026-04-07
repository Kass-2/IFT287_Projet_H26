package tp.gestion;

import org.bson.Document;
import tp.CollantException;
import tp.bdd.Connexion;
import tp.collections.GestionCollectionsFournisseur;

public class GestionTransactionsFournisseur extends GestionTransactions {

    private final GestionCollectionsFournisseur gestionFournisseurs;

    public GestionTransactionsFournisseur(Connexion cx) {
        super(cx);
        this.gestionFournisseurs = new GestionCollectionsFournisseur(cx);
    }

    /**
     * Ajoute un nouveau fournisseur.
     * ajouterFournisseur <nom> <courriel> <adresse>
     */
    public void ajouterFournisseur(String nom, String courriel, String adresse)
            throws CollantException, Exception {

        if (gestionFournisseurs.existe(nom)) {
            throw new CollantException("Le fournisseur '" + nom + "' existe déjà.");
        }

        cx.demarreTransaction();
        try {
            gestionFournisseurs.inserer(nom, courriel, adresse);
            cx.executeTransaction();
            System.out.println("Fournisseur '" + nom + "' ajouté avec succès.");
        } catch (Exception e) {
            cx.annuleTransaction();
            throw e;
        }
    }

    /**
     * Affiche toutes les informations d'un fournisseur.
     * afficherFournisseur <nom>
     */
    public void afficherFournisseur(String nom) throws CollantException {

        Document doc = gestionFournisseurs.trouver(nom);

        if (doc == null) {
            throw new CollantException("Le fournisseur '" + nom + "' n'existe pas.");
        }

        System.out.println("=== Fournisseur ===");
        System.out.println("Nom      : " + doc.getString("nom"));
        System.out.println("Courriel : " + doc.getString("courriel"));
        System.out.println("Adresse  : " + doc.getString("adresse"));
    }

    /**
     * Supprime un fournisseur.
     * supprimerFournisseur <nom>
     */
    public void supprimerFournisseur(String nom) throws CollantException, Exception {

        if (!gestionFournisseurs.existe(nom)) {
            throw new CollantException("Le fournisseur '" + nom + "' n'existe pas.");
        }

        cx.demarreTransaction();
        try {
            gestionFournisseurs.supprimer(nom);
            cx.executeTransaction();
            System.out.println("Fournisseur '" + nom + "' supprimé avec succès.");
        } catch (Exception e) {
            cx.annuleTransaction();
            throw e;
        }
    }
}