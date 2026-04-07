package tp;

import tp.bdd.Connexion;
import tp.gestion.GestionTransactionsFournisseur;

public class Main {
    public static void main(String[] args) {

        try {
            Connexion conn = new Connexion("dinf", "ift287_21db", "ift287_21", "Oadeig3pailu");

            System.out.println("Nom BD : " + conn.getDatabase().getName());

            GestionTransactionsFournisseur gtf = new GestionTransactionsFournisseur(conn);

            // Test ajouterFournisseur
            gtf.ajouterFournisseur("Fruits Cantons", "100 rue des Vergers, Bromont", "contact@fruitscantons.ca");

            // Test afficherFournisseur
            gtf.afficherFournisseur("Fruits Cantons");

            // Test listerTous
            System.out.println("Tous les fournisseurs : " + gtf.listerTous());

            // Test supprimerFournisseur
            gtf.supprimerFournisseur("Fruits Cantons");

            conn.fermer();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
