package tp.gestion;

import tp.CollantException;
import tp.bdd.IConnexion;
import tp.collections.GestionCollectionsFournisseur;
import tp.documents.Fournisseur;

import java.util.List;

/**
 * Logique métier pour les transactions liées aux fournisseurs.
 * Étend GestionTransactions — utilise le champ cx hérité.
 *
 * Transactions :
 *   ajouterFournisseur   <nom> <adresse> <courriel>
 *   afficherFournisseur  <nom>
 *   supprimerFournisseur <nom>
 */
public class GestionTransactionsFournisseur extends GestionTransactions {

    private final GestionCollectionsFournisseur gcf;

    public GestionTransactionsFournisseur(IConnexion cx) {
        super(cx);
        this.gcf = new GestionCollectionsFournisseur(cx);
    }

    // ─── ajouterFournisseur ───────────────────────────────────────────────────

    /**
     * Ajoute un nouveau fournisseur au système.
     *
     * @param nom      nom de l'entreprise (clé unique)
     * @param adresse  adresse postale
     * @param courriel courriel de contact (unique)
     */
    public void ajouterFournisseur(String nom, String adresse, String courriel)
            throws CollantException {

        if (nom == null || nom.trim().isEmpty())
            throw new CollantException("Le nom du fournisseur est obligatoire.");
        if (adresse == null || adresse.trim().isEmpty())
            throw new CollantException("L'adresse est obligatoire.");
        if (courriel == null || courriel.trim().isEmpty())
            throw new CollantException("Le courriel est obligatoire.");

        if (gcf.trouver(nom) != null)
            throw new CollantException("Le fournisseur '" + nom + "' existe déjà.");
        if (gcf.trouverParCourriel(courriel) != null)
            throw new CollantException("Un fournisseur avec le courriel '" + courriel + "' existe déjà.");

        gcf.inserer(new Fournisseur(nom, adresse, courriel));
        System.out.println("Fournisseur '" + nom + "' ajouté avec succès.");
    }

    // ─── afficherFournisseur ──────────────────────────────────────────────────

    /**
     * Affiche toutes les informations d'un fournisseur.
     * Seuls les noms des produits et producteurs sont affichés.
     *
     * @param nom nom du fournisseur à afficher
     * @return l'objet Fournisseur
     */
    public Fournisseur afficherFournisseur(String nom) throws CollantException {

        if (nom == null || nom.trim().isEmpty())
            throw new CollantException("Le nom du fournisseur est obligatoire.");

        Fournisseur f = gcf.trouver(nom);
        if (f == null)
            throw new CollantException("Le fournisseur '" + nom + "' n'existe pas.");

        System.out.println(f);
        return f;
    }

    // ─── supprimerFournisseur ─────────────────────────────────────────────────

    /**
     * Supprime un fournisseur et toutes ses associations.
     *
     * @param nom nom du fournisseur à supprimer
     */
    public void supprimerFournisseur(String nom) throws CollantException {

        if (nom == null || nom.trim().isEmpty())
            throw new CollantException("Le nom du fournisseur est obligatoire.");

        Fournisseur f = gcf.trouver(nom);
        if (f == null)
            throw new CollantException("Le fournisseur '" + nom + "' n'existe pas.");

        if (!f.getProduits().isEmpty())
            System.out.println("Associations supprimées avec les produits : " + f.getProduits());
        if (!f.getProducteurs().isEmpty())
            System.out.println("Associations supprimées avec les producteurs : " + f.getProducteurs());

        gcf.supprimer(nom);
        System.out.println("Fournisseur '" + nom + "' supprimé.");
    }

    // ─── Lister ───────────────────────────────────────────────────────────────

    public List<String> listerTous() {
        return gcf.listerTous();
    }

    // ─── Accès collection (pour les autres gestionnaires du groupe) ──────────

    public GestionCollectionsFournisseur getGestionCollections() {
        return gcf;
    }
}
