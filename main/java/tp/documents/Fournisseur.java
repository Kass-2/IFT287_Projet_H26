package tp.documents;

import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente un fournisseur selon le diagramme ER.
 *
 * Attributs : nom (clé), adresse, courriel
 * Relations  : produits (fournit), producteurs (travaille)
 *
 * Collection MongoDB : fournisseurs
 */
public class Fournisseur {

    private String nom;
    private String adresse;
    private String courriel;
    private List<String> produits;
    private List<String> producteurs;

    public Fournisseur() {
        produits    = new ArrayList<>();
        producteurs = new ArrayList<>();
    }

    public Fournisseur(String nom, String adresse, String courriel) {
        this.nom      = nom;
        this.adresse  = adresse;
        this.courriel = courriel;
        this.produits    = new ArrayList<>();
        this.producteurs = new ArrayList<>();
    }

    // ─── Conversion BSON ─────────────────────────────────────────────────────

    public Document toDocument() {
        return new Document("nom", nom)
                .append("adresse",     adresse)
                .append("courriel",    courriel)
                .append("produits",    produits)
                .append("producteurs", producteurs);
    }

    @SuppressWarnings("unchecked")
    public static Fournisseur fromDocument(Document doc) {
        if (doc == null) return null;
        Fournisseur f   = new Fournisseur();
        f.nom           = doc.getString("nom");
        f.adresse       = doc.getString("adresse");
        f.courriel      = doc.getString("courriel");
        f.produits      = doc.containsKey("produits")
                ? (List<String>) doc.get("produits")    : new ArrayList<>();
        f.producteurs   = doc.containsKey("producteurs")
                ? (List<String>) doc.get("producteurs") : new ArrayList<>();
        return f;
    }

    // ─── Getters / Setters ────────────────────────────────────────────────────

    public String getNom()                     { return nom; }
    public void   setNom(String nom)           { this.nom = nom; }

    public String getAdresse()                 { return adresse; }
    public void   setAdresse(String adresse)   { this.adresse = adresse; }

    public String getCourriel()                { return courriel; }
    public void   setCourriel(String courriel) { this.courriel = courriel; }

    public List<String> getProduits()          { return produits; }
    public void setProduits(List<String> p)    { this.produits = p; }

    public List<String> getProducteurs()       { return producteurs; }
    public void setProducteurs(List<String> p) { this.producteurs = p; }

    @Override
    public String toString() {
        return "=== Fournisseur ===\n"
             + "Nom      : " + nom      + "\n"
             + "Adresse  : " + adresse  + "\n"
             + "Courriel : " + courriel + "\n"
             + "Produits fournis  : " + produits    + "\n"
             + "Producteurs assoc : " + producteurs + "\n";
    }
}
