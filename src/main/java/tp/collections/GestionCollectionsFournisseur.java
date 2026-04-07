package tp.collections;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import tp.bdd.Connexion;

public class GestionCollectionsFournisseur extends GestionCollections {

    private final MongoCollection<Document> collection;

    public GestionCollectionsFournisseur(Connexion cx) {
        super(cx);
        this.collection = cx.getDatabase().getCollection("fournisseurs");
    }

    public boolean existe(String nom) {
        return collection.find(Filters.eq("nom", nom)).first() != null;
    }

    public void inserer(String nom, String courriel, String adresse) {
        Document doc = new Document("nom", nom)
                .append("courriel", courriel)
                .append("adresse", adresse);
        collection.insertOne(doc);
    }

    public Document trouver(String nom) {
        return collection.find(Filters.eq("nom", nom)).first();
    }

    public void supprimer(String nom) {
        collection.deleteOne(Filters.eq("nom", nom));
    }
}