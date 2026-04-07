package tp.collections;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import tp.bdd.Connexion;
import tp.bdd.IConnexion;
import tp.documents.Fournisseur;

import java.util.ArrayList;
import java.util.List;

/**
 * Accès à la collection MongoDB "fournisseurs".
 * Étend GestionCollections — utilise le champ cx hérité.
 */
public class GestionCollectionsFournisseur extends GestionCollections {

    private final MongoCollection<Document> collection;

    public GestionCollectionsFournisseur(IConnexion cx) {
        super(cx);
        this.collection = ((Connexion) cx).getDatabase().getCollection("fournisseurs");
    }

    // ─── Insertion ────────────────────────────────────────────────────────────

    public void inserer(Fournisseur f) {
        collection.insertOne(f.toDocument());
    }

    // ─── Recherche ────────────────────────────────────────────────────────────

    public Fournisseur trouver(String nom) {
        Document doc = collection.find(Filters.eq("nom", nom)).first();
        return Fournisseur.fromDocument(doc);
    }

    public Fournisseur trouverParCourriel(String courriel) {
        Document doc = collection.find(Filters.eq("courriel", courriel)).first();
        return Fournisseur.fromDocument(doc);
    }

    public List<String> listerTous() {
        List<String> noms = new ArrayList<>();
        for (Document doc : collection.find()) {
            noms.add(doc.getString("nom"));
        }
        return noms;
    }

    // ─── Suppression ─────────────────────────────────────────────────────────

    public void supprimer(String nom) {
        collection.deleteOne(Filters.eq("nom", nom));
    }

    // ─── Mise à jour des listes embarquées ───────────────────────────────────

    public void ajouterProduit(String nomFournisseur, String nomProduit) {
        collection.updateOne(Filters.eq("nom", nomFournisseur),
                Updates.addToSet("produits", nomProduit));
    }

    public void retirerProduit(String nomFournisseur, String nomProduit) {
        collection.updateOne(Filters.eq("nom", nomFournisseur),
                Updates.pull("produits", nomProduit));
    }

    public void ajouterProducteur(String nomFournisseur, String nomProducteur) {
        collection.updateOne(Filters.eq("nom", nomFournisseur),
                Updates.addToSet("producteurs", nomProducteur));
    }

    public void retirerProducteur(String nomFournisseur, String nomProducteur) {
        collection.updateOne(Filters.eq("nom", nomFournisseur),
                Updates.pull("producteurs", nomProducteur));
    }
}
