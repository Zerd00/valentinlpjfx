package fr.eseo.lpjfx;


public class Position {
    /**
     * Attributs
     */
    private int x;
    private int y;
    @Override
    public String toString() {
        return "Position{" + "x=" + x + ", y=" + y + '}';
    }

    /**
     * Constructeur
     * @param x
     * @param y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getters
     * @return
     */
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }


    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Position pos) {
        return x == pos.getX() && y == pos.getY();
    }

    public Position deplacerVers(Direction direction) {
        // Afficher l'état initial de la position
        System.out.println("Position actuelle : " + this);

        // Calculer la nouvelle position selon la direction
        Position nouvellePosition = new Position(this.x, this.y);

        switch (direction) {
            case NORD:
                nouvellePosition.y -= 1;
                break;
            case SUD:
                nouvellePosition.y += 1;
                break;
            case EST:
                nouvellePosition.x += 1;
                break;
            case OUEST:
                nouvellePosition.x -= 1;
                break;
        }

        // Afficher la nouvelle position calculée
        System.out.println("Nouvelle position calculée : " + nouvellePosition);

        return nouvellePosition;
    }


}
