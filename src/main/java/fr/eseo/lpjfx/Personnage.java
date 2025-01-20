package fr.eseo.lpjfx;

public class Personnage {

    //Attributs
    private Position position;
    private Direction direction;
    /**
     * Constructeur de la classe personnage
     */
    public Personnage(Position start) {
        this(start, Direction.SUD); // Appelle le constructeur avec la direction par défaut
    }

    public Personnage(Position start, Direction direction) {
        this.position = start;
        this.direction = direction;
    }

    //Méthode pour obtenir la position actuelle du personnage
    public Position getPosition() {
        return position;
    }



    // Méthode pour obtenir la direction actuelle du personnage
    public Direction getDirection() {
        return direction;
    }



    // Méthode pour faire tourner le personnage à gauche
    public void tournerGauche() {
        this.direction = direction.prochaineDirection(true); // true indique un tournant à gauche
    }

    // Méthode pour faire tourner le personnage à droite
    public void tournerDroite() {
        this.direction = direction.prochaineDirection(false); // false indique un tournant à droite
    }

    public boolean avancer(Labyrinthe labyrinthe) {
        // Position actuelle
        Position actuelle = this.getPosition();
        System.out.println("Position actuelle : " + actuelle);

        // Déplacement en fonction de la direction actuelle
        Position nouvellePosition = actuelle.deplacerVers(this.direction);  // Appel à deplacerVers

        System.out.println("Tentative d'avancer dans la direction : " + this.direction.getNom());
        System.out.println("Nouvelle position calculée : " + nouvellePosition);

        // Vérification de la validité de la nouvelle position
        if (labyrinthe.estPositionValide(nouvellePosition)) {
            System.out.println("La position " + nouvellePosition + " est valide. Déplacement autorisé.");

            // Mise à jour de la position
            this.position = nouvellePosition;
            return true;
        } else {
            System.out.println("La position " + nouvellePosition + " est invalide. Déplacement interdit.");
            return false;
        }
    }






    // Méthode pour vérifier si le personnage est arrivé à la sortie du labyrinthe
    public boolean estArriveSortie(Labyrinthe labyrinthe) {
        return position.equals(labyrinthe.getSortie());
    }



    public void setDirection(Direction direction) {
    }

    public void setPosition(Position entree) {
    }
}
