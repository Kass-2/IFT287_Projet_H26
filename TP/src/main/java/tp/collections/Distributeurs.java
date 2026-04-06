package tp.collections;

import com.mongodb.client.*;

import static com.mongodb.client.model.Filters.eq;

import org.bson.*;
import tp.bdd.Connexion;
import tp.bdd.IConnexion;
import tp.documents.Distributeur;

public class Distributeurs extends GestionCollections{
    private MongoCollection<Document> distributeursCollection;

    public Distributeurs(Connexion cx) {
        super(cx);
        distributeursCollection = cx.getDatabase().getCollection("Distributeurs");
    }

    public boolean existe(String nom) {
        return distributeursCollection.find(eq("nom", nom)).first() != null;
    }

    public Distributeur getDistributeur(String nom) {
        Document d = distributeursCollection.find(eq("nom", nom)).first();
        if (d != null) {
            return new Distributeur(d);
        }
        return null;
    }

    public void ajouterPointDeVente(String nom, String courriel, String adresse) {
        Distributeur distributeur = new Distributeur(nom, courriel, adresse);
        distributeursCollection.insertOne(distributeur.toDocument());
    }

    public boolean supprimerPointDeVente(String nom) {
        return distributeursCollection.deleteOne(eq("nom", nom)).getDeletedCount() > 0;
    }

    // TODO : À compléter
    public void afficherPointDeVente(String nom) {

    }

    // TODO : À compléter
    public void vendreProduit(String nomProduit, String nomPointDeVente) {

    }

    // TODO : À compléter
    public void retirerProduitPointDeVente(String nomProduit, String nomPointDeVente) {

    }
}
