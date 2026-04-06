package tp.gestion;

import tp.bdd.IConnexion;

public abstract class GestionTransactions {
    protected final IConnexion cx;

    protected GestionTransactions(IConnexion cx) {
        this.cx = cx;
    }
}
