import tp.bdd.Connexion;

public class Main {
    public static void main(String[] args) {

        try {
            Connexion conn = new Connexion("local", "tpDB", "", "");

            System.out.println("Nom BD : " + conn.getDatabase().getName());

            conn.fermerConnexion();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
