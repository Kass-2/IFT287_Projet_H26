package tp.bdd;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

public class Connexion implements IConnexion {

    private final MongoClient client;
    private final MongoDatabase database;

    public Connexion(String serveur, String bd, String user, String pass) throws Exception {

        if (serveur.equals("local")) {
            client = new MongoClient();
        } else if (serveur.equals("dinf")) {

            // Désactiver la vérification SSL (certificat expiré côté serveur dinf)
            TrustManager[] trustAll = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() { return null; }
                        public void checkClientTrusted(X509Certificate[] c, String a) { }
                        public void checkServerTrusted(X509Certificate[] c, String a) { }
                    }
            };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAll, new java.security.SecureRandom());

            MongoClientOptions options = MongoClientOptions.builder()
                    .sslEnabled(true)
                    .sslContext(sslContext)
                    .sslInvalidHostNameAllowed(true)
                    .build();

            MongoCredential credential = MongoCredential.createCredential(user, bd, pass.toCharArray());

            client = new MongoClient(
                    new ServerAddress("bd-info2.dinf.usherbrooke.ca", 27017),
                    credential,
                    options
            );
        } else {
            throw new Exception("Serveur inconnu : " + serveur);
        }

        database = client.getDatabase(bd);
        System.out.println("Connexion ouverte : MongoDB " + bd
                + " avec l'utilisateur " + user);
    }

    @Override
    public void fermerConnexion() throws Exception {
        if (client != null) {
            client.close();
            System.out.println("Connexion fermée.");
        }
    }

    @Override
    public void demarreTransaction() throws Exception { }

    @Override
    public void executeTransaction() throws Exception { }

    @Override
    public void annuleTransaction() throws Exception { }

    public void fermer() throws Exception {
        fermerConnexion();
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}