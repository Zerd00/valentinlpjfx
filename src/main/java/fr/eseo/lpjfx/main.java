package fr.eseo.lpjfx;

public class main {
    public static void main(String[] args) {
        // Créer une instance de labyrinthe avec une largeur et une hauteur spécifiques
        Labyrinthe labyrinthe = new Labyrinthe(20, 20);

        // Générer le labyrinthe
        labyrinthe.genererLabyrinthe();

        // Afficher le labyrinthe dans la console
        labyrinthe.affficherLabyrinthe();
    }
}
