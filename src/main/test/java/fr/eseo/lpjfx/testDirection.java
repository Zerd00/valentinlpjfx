package fr.eseo.lpjfx;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class testDirection {
    @Test
    void testProchaineDirectionTournerGauche() {
        // Tourner à gauche
        assertEquals(Direction.OUEST, Direction.NORD.prochaineDirection(true));
        assertEquals(Direction.NORD, Direction.EST.prochaineDirection(true));
        assertEquals(Direction.EST, Direction.SUD.prochaineDirection(true));
        assertEquals(Direction.SUD, Direction.OUEST.prochaineDirection(true));
    }
    @Test
    void testProchaineDirectionTournerDroite() {
        // Vérifie si la direction change correctement en tournant à droite
        assertEquals(Direction.EST, Direction.NORD.prochaineDirection(false), "De NORD à EST en tournant à droite");
        assertEquals(Direction.SUD, Direction.EST.prochaineDirection(false), "De EST à SUD en tournant à droite");
        assertEquals(Direction.OUEST, Direction.SUD.prochaineDirection(false), "De SUD à OUEST en tournant à droite");
        assertEquals(Direction.NORD, Direction.OUEST.prochaineDirection(false), "De OUEST à NORD en tournant à droite");
    }
}
