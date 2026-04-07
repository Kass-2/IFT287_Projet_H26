package tp.bdd;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class Connexion implements IConnexion {
    private MongoClient client;
    private MongoDatabase database;

    public Connexion(String serveur, String bd, String user, String pass) throws Exception {

        if (serveur.equals("local")) {
            client = new MongoClient();
        }
        else if (serveur.equals("dinf")) {
            MongoClientURI uri = new MongoClientURI(
                    "mongodb://" + user + ":" + pass + "@bd-info2.dinf.usherbrooke.ca:27017/" + bd + "?ssl=true");
            client = new MongoClient(uri);
        }
        else {
            throw new Exception("Serveur inconnu");
        }

        database = client.getDatabase(bd);

        System.out.println("Connexion ouverte :\n"
                + "Connecté sur la MongoDB " + bd + " avec l'utilisateur " + user);
    }

    @Override
    public void fermerConnexion() throws Exception { fermer(); }

    @Override
    public void demarreTransaction() throws Exception { /* no-op MongoDB */ }

    @Override
    public void executeTransaction() throws Exception { /* no-op MongoDB */ }

    @Override
    public void annuleTransaction() throws Exception { /* no-op MongoDB */ }

    public void fermer() {
        if (client != null) {
            client.close();
            System.out.println("Connexion fermée");
        }
    }

    public MongoClient getConnection() { return client; }

    public MongoDatabase getDatabase() { return database; }
}
