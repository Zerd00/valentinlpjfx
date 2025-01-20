package fr.eseo.lpjfx;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonnageTest {

    private Personnage personnage;
    private Position startPosition;

    @BeforeEach
    public void setUp() {
        // Configuration avant chaque test
        startPosition = new Position(1, 1); // Position initiale
        personnage = new Personnage(startPosition); // Création du personnage
    }

    @Test
    public void testInitialPosition() {
        // Vérification que la position initiale est correcte
        assertEquals(1, personnage.getPosition().getX());
        assertEquals(1, personnage.getPosition().getY());
    }

    @Test
    public void testTournerGauche() {
        // Vérification après un premier tournant à gauche
        personnage.tournerGauche();
        assertEquals(Direction.OUEST, personnage.getDirection());

        // Après un autre tournant à gauche
        personnage.tournerGauche();
        assertEquals(Direction.SUD, personnage.getDirection());
    }

    @Test
    public void testTournerDroite() {
        // Vérification après un premier tournant à droite
        personnage.tournerDroite();
        assertEquals(Direction.EST, personnage.getDirection());

        // Après un autre tournant à droite
        personnage.tournerDroite();
        assertEquals(Direction.SUD, personnage.getDirection());
    }

    @Test
    public void testAvancer() {
        Labyrinthe labyrinthe = new Labyrinthe(11, 11);
        labyrinthe.genererLabyrinthe();

        // Assurer qu'il n'y a pas d'obstacle à la position suivante
        // Déplacer le personnage à la position initiale (1,1) et vérifier s'il peut avancer.
        personnage.avancer(labyrinthe); // Avance dans la direction actuelle
        assertEquals(2, personnage.getPosition().getX()); // Position après un mouvement vers le bas (exemple)

        // Tester un obstacle en changeant la direction du personnage
        personnage.tournerGauche(); // Par exemple, direction Ouest
        boolean avance = personnage.avancer(labyrinthe); // Si un obstacle existe, le mouvement doit échouer
        assertFalse(avance); // Vérifie que le personnage ne peut pas avancer en cas d'obstacle
    }


    @Test
    public void testEstArriveSortie() {
        // Initialisation d'un labyrinthe avec une sortie à (5, 5)
        Labyrinthe labyrinthe = new Labyrinthe(11, 11);
        labyrinthe.genererLabyrinthe();
        labyrinthe.setSortie(new Position(5, 5)); // Position de la sortie

        // Déplacer le personnage à la sortie
        personnage = new Personnage(new Position(5, 5)); // Placer directement le personnage sur la sortie
        assertTrue(personnage.estArriveSortie()); // Le test doit passer car le personnage est déjà à la sortie
    }

}
