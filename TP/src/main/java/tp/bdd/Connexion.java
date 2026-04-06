package tp.bdd;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;

public class Connexion implements IConnexion {

    private MongoClient client;
    private MongoDatabase database;

    /**
     * Ouverture d'une connexion
     *
     * @serveur serveur à utiliser (local ou dinf)
     * @bd nom de la base de données
     * @user userid sur le serveur MongoDB pour la BD specifiée
     * @pass mot de passe sur le serveur MongoDB pour la BD specifiée
     */
    public Connexion(String serveur, String bd, String user, String pass) throws Exception
    {
        if (serveur.equals("local"))
        {
            client = new MongoClient();
        }
        else if (serveur.equals("dinf"))
        {
            MongoClientURI uri = new MongoClientURI("mongodb://"+user+":"+pass+"@bd-info2.dinf.usherbrooke.ca:27017/"+bd+"?ssl=true");
            client = new MongoClient(uri);
        }
        else
        {
            throw new Exception("Serveur inconnu");
        }

        database = client.getDatabase(bd);

        System.out.println("Ouverture de la connexion :\n"
                + "Connecté sur la BD MongoDB "
                + bd + " avec l'utilisateur " + user);
    }

    /**
     * fermeture d'une connexion
     */
    @Override
    public void fermerConnexion() throws Exception {
        client.close();
        System.out.println("Connexion fermée");
    }

    @Override
    public void demarreTransaction() throws Exception {

    }

    @Override
    public void executeTransaction() throws Exception {

    }

    @Override
    public void annuleTransaction() throws Exception {

    }




    /**
     * retourne la Connection MongoDB
     */
    public MongoClient getConnection()
    {
        return client;
    }

    /**
     * retourne la DataBase MongoDB
     */
    public MongoDatabase getDatabase()
    {
        return database;
    }
}// Classe Connexion
