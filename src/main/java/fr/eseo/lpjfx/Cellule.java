package fr.eseo.lpjfx;

public class Cellule {
    private Position position;
    private boolean chemin;
    private boolean estchemin;

    public Cellule(Position position, boolean chemin) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public boolean getChemin() {
        return chemin;
    }

    public void setChemin(boolean chemin) {
        this.chemin = chemin;

    }
    public boolean getEstchemin() {
        return estchemin;
    }

}
