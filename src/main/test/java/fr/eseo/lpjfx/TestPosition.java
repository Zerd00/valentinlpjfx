package fr.eseo.lpjfx;

import org.junit.jupiter.api.Test;

import javax.management.loading.ClassLoaderRepository;

import static org.junit.jupiter.api.Assertions.*;

public class TestPosition {
    @Test
    void TestConstructor() {
        //tester les positions et verifier si les x et y sont correctes
        Position pos = new Position(3,5);
        assert pos.getX() == 3 && pos.getY() == 5;
    }
    void TestSetters() {
        //tests des setters
        Position pos = new Position(0,0);
        pos.setX(4);
        pos.setY(6);
        assertEquals(4, pos.getX(), "La valeur de x doit être 4 après setX");
        assertEquals(6, pos.getY(), "La valeur de y doit être 6 après setY");
    }
    @Test
    void testEquals() {
        // Teste si deux positions identiques sont considérées comme égales
        Position pos1 = new Position(2, 2);
        Position pos2 = new Position(2, 2);
        Position pos3 = new Position(3, 3);

        assertTrue(pos1.equals(pos2), "Les positions pos1 et pos2 devraient être égales");
        assertFalse(pos1.equals(pos3), "Les positions pos1 et pos3 ne devraient pas être égales");
    }

    @Test
    void testDeplacerVersNord() {
        // Teste le déplacement vers le nord
        Position pos = new Position(2, 2);
        Position nouvellePos = pos.deplacerVers(Direction.NORD);
        assertEquals(2, nouvellePos.getX(), "La position x ne doit pas changer en allant vers le nord");
        assertEquals(2, nouvellePos.getY(), "La position y doit rester la même car NORD n'implique pas de changement de position");
    }

    @Test
    void testDeplacerVersEst() {
        // Teste le déplacement vers l'est
        Position pos = new Position(2, 2);
        Position nouvellePos = pos.deplacerVers(Direction.EST);
        assertEquals(2, nouvellePos.getX(), "La position x ne doit pas changer en allant vers l'est");
        assertEquals(2, nouvellePos.getY(), "La position y doit rester la même car EST n'implique pas de changement de position");
    }

    @Test
    void testDeplacerVersOuest() {
        // Teste le déplacement vers l'ouest
        Position pos = new Position(2, 2);
        Position nouvellePos = pos.deplacerVers(Direction.OUEST);
        assertEquals(2, nouvellePos.getX(), "La position x ne doit pas changer en allant vers l'ouest");
        assertEquals(2, nouvellePos.getY(), "La position y doit rester la même car OUEST n'implique pas de changement de position");
    }

    @Test
    void testDeplacerVersSud() {
        // Teste le déplacement vers le sud
        Position pos = new Position(2, 2);
        Position nouvellePos = pos.deplacerVers(Direction.SUD);
        assertEquals(2, nouvellePos.getX(), "La position x ne doit pas changer en allant vers le sud");
        assertEquals(2, nouvellePos.getY(), "La position y doit rester la même car SUD n'implique pas de changement de position");
    }
}

