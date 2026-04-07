package tp;

import tp.bdd.Connexion;
import tp.gestion.GestionTransactionsFournisseur;

public class Main {
    public static void main(String[] args) {
        Connexion conn = null;
        try {
            conn = new Connexion("dinf", "ift287_21db", "ift287_21", "Oadeig3pailu");

            GestionTransactionsFournisseur gtf = new GestionTransactionsFournisseur(conn);

            gtf.ajouterFournisseur("FruitsQC", "contact@fruitsqc.ca", "456 rue des Vergers");
            gtf.afficherFournisseur("FruitsQC");
            gtf.supprimerFournisseur("FruitsQC");

        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.fermer();
                } catch (Exception e) {
                    System.err.println("Erreur fermeture : " + e.getMessage());
                }
            }
        }
    }
}