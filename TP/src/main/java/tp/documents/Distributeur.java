package tp.documents;

import org.bson.*;

public class Distributeur {
    private String courriel;
    private String nom;
    private String adresse;

    public Distributeur(Document d) {
        courriel = d.getString("courriel");
        nom = d.getString("nom");
        adresse = d.getString("adresse");
    }

    public Distributeur(String nom, String courriel, String adresse) {
        this.nom = nom;
        this.courriel = courriel;
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public void getCourriel(String courriel) {
        this.courriel = courriel;
    }

    public void getAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Document toDocument() {
        return new Document().append("nom", nom)
                             .append("courriel", courriel)
                             .append("adresse", adresse);
    }
}
