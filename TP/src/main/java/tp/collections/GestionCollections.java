package tp.collections;

import tp.bdd.IConnexion;

public abstract class GestionCollections {
    protected final IConnexion cx;

    protected GestionCollections(IConnexion cx) {
        this.cx = cx;
    }

    public IConnexion getConnexion() {
        return cx;
    }
}
