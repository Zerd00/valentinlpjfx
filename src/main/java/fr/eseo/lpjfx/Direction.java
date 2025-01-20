package fr.eseo.lpjfx;

public enum Direction {
    NORD("nord"),
    SUD("sud"),
    EST("est"),
    OUEST("ouest");

    private final String nom;

    /**
     * Constructeur
     * @param nom
     */
    Direction(String nom) {
        this.nom = nom;
    }

    /**
     * Méthode pour obtenir la prochaine direction lors d'un tournant
     * @param tournerGauche vrai si le personnage tourne à gauche, faux pour tourner à droite
     * @return la nouvelle direction après le tournant
     */
    public Direction prochaineDirection(boolean tournerGauche) {
        switch (this) {
            case NORD:
                return tournerGauche ? OUEST : EST;
            case EST:
                return tournerGauche ? NORD : SUD;
            case SUD:
                return tournerGauche ? EST : OUEST;
            case OUEST:
                return tournerGauche ? SUD : NORD;
            default:
                throw new IllegalArgumentException("Direction inconnue");
        }
    }

    /**
     * Accesseur pour le nom
     */
    public String getNom() {
        return nom;
    }

}
