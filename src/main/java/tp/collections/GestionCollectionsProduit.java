package tp.collections;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import tp.bdd.Connexion;

public class GestionCollectionsProduit extends GestionCollections {

    private final MongoCollection<Document> collection;

    public GestionCollectionsProduit(Connexion cx) {
        super(cx);
        this.collection = cx.getDatabase().getCollection("produits");
    }

    /** Vérifie si un produit existe déjà */
    public boolean existe(String nom) {
        return collection.find(Filters.eq("nom", nom)).first() != null;
    }

    /** Insère un nouveau produit */
    public void inserer(String nom, double prix, double cout,
                        String categorie, String nomProducteur) {
        Document doc = new Document("nom", nom)
                .append("prix", prix)
                .append("cout", cout)
                .append("categorie", categorie)
                .append("nomProducteur", nomProducteur);
        collection.insertOne(doc);
    }

    /** Retourne le document d'un produit */
    public Document trouver(String nom) {
        return collection.find(Filters.eq("nom", nom)).first();
    }

    /** Supprime un produit */
    public void supprimer(String nom) {
        collection.deleteOne(Filters.eq("nom", nom));
    }

    /** Supprime tous les produits d'un producteur (utile pour supprimerProducteur) */
    public void supprimerParProducteur(String nomProducteur) {
        collection.deleteMany(Filters.eq("nomProducteur", nomProducteur));
    }
}