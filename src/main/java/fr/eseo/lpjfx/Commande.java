package fr.eseo.lpjfx;

public class Commande {

    private final TypeCommande typeCommande;
    private final int parametre;

    public Commande(TypeCommande typeCommande, int parametre) {
        this.typeCommande = typeCommande;
        this.parametre = parametre;
    }

    public boolean executer(Personnage personnage, Labyrinthe labyrinthe) {
        switch (typeCommande) {
            case AVANCER:
                return personnage.avancer(labyrinthe);
            case TOURNER_GAUCHE:
                personnage.tournerGauche();
                return true;
            case TOURNER_DROITE:
                personnage.tournerDroite();
                return true;
            case BOUCLE:
                for (int i = 0; i < parametre; i++) {
                    if (!personnage.avancer(labyrinthe)) {
                        return false;
                    }
                }
                return true;
            case SI:
                return estConditionRemplie(personnage, labyrinthe);
            default:
                return false;
        }
    }

    public boolean estConditionRemplie(Personnage personnage, Labyrinthe labyrinthe) {
        // Exemple : vérifie si un mur est devant
        Position nouvellePosition = personnage.getPosition().deplacerVers(personnage.getDirection());
        return !labyrinthe.estMur(nouvellePosition);
    }

    public TypeCommande getTypeCommande() {
        return typeCommande;
    }

    public int getParametre() {
        return parametre;
    }
}
