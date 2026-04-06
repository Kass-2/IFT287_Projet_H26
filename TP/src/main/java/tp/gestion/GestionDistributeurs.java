package tp.gestion;

import tp.collections.Distributeurs;
import tp.documents.Distributeur;

public class GestionDistributeurs {
    private Distributeurs distributeurs;

    public GestionDistributeurs(Distributeurs distributeurs) throws Exception {
        this.distributeurs = distributeurs;
    }

    public void ajouterPointDeVente(String nom, String courriel,String adresse) throws Exception {
        try
        {
            // Vérifie si le distributeur existe déjà
            if (distributeurs.existe(nom))
                throw new Exception("Distributeur existe déjà : " + nom);

            // Ajout du distributeur
            distributeurs.ajouterPointDeVente(nom, courriel, adresse);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public void supprimerPointDeVente(String nom) throws Exception {
        try
        {
            // Vérifie si le distributeur existe
            Distributeur distributeur = distributeurs.getDistributeur(nom);
            if (distributeur == null)
                throw new Exception("Distributeur inexistant: " + nom);

            // Suppression du distributeur
            if(!distributeurs.supprimerPointDeVente(nom))
                throw new Exception("Distributeur " + nom + " inexistant");
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public void afficherPontDeVente(String nom) throws Exception {
        try
        {
            // TODO : À compléter
            distributeurs.afficherPointDeVente(nom);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    // TODO : À compléter
    public void vendreProduit(String nomProduit, String nomPointDeVente) {

    }

    // TODO : À compléter
    public void retirerProduitPointDeVente(String nomProduit, String nomPointDeVente) {

    }
}
