package fr.eseo.lpjfx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Labyrinthe {
    /**
     * Attributs
     */
    private Cellule[][] grille;
    private Position entree;
    private Position sortie;
    List<Position> liste = new ArrayList<>();
    private Random random = new Random(); //Pour faire des choix aléatoires

    /**
     * Constructeur classe labyrinthe
     */
    public Labyrinthe(int largeur, int hauteur) {
        this.grille = new Cellule[largeur][hauteur];
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                Position position = new Position(i, j);
                // Les bords ne sont pas initialisés en tant que chemin
                boolean estChemin = (i > 0 && j > 0 && i < largeur - 1 && j < hauteur - 1);
                grille[i][j] = new Cellule(position, estChemin);
            }
        }
        this.entree = new Position(1, 1); // Entrée en haut à gauche
        if (entree.getX() + 1 < grille.length) {
            grille[2][1].setChemin(false);
        }
        // Placement aléatoire de la sortie sur la bordure inférieure
        int randomX = random.nextInt(grille.length); // Choix aléatoire sur la bordure
        int randomY = random.nextInt(grille[0].length - 2) + 1; // Ne pas inclure les coins 0, grille[0].length - 1
        this.sortie = new Position(grille.length - 1, randomY);
        grille[entree.getX()][entree.getY()].setChemin(true); // Entrée est un chemin
        grille[sortie.getX()][sortie.getY()].setChemin(true); // Sortie est un chemin


    }

    //Méthodes d'accès pour la grille
    public Cellule[][] getGrille() {
        return grille;
    }

    public void setGrille(Cellule[][] grille) {
        this.grille = grille;
    }

    public Position getEntre() {
        return entree;
    }

    public void setEntre(Position entre) {
        this.entree = entre;
    }

    public Position getSortie() {
        return sortie;
    }

    public void setSortie(Position sortie) {
        this.sortie = sortie;
    }

    public void genererLabyrinthe() {
        Position current = entree;
        liste.add(current);

        while (!liste.isEmpty()) {
            current = liste.get(liste.size() - 1);
            List<Position> voisins = trouverVoisinsNonVisites(current);

            if (!voisins.isEmpty()) {
                Position voisinChoisi = voisins.get(random.nextInt(voisins.size()));
                int nx = (current.getX() + voisinChoisi.getX()) / 2;
                int ny = (current.getY() + voisinChoisi.getY()) / 2;
                grille[nx][ny].setChemin(true);
                grille[voisinChoisi.getX()][voisinChoisi.getY()].setChemin(true);

                liste.add(voisinChoisi);
            } else {
                liste.remove(liste.size() - 1);
            }
        }


        // Vérifier que la sortie n'est pas bloquée par un mur
        garantirSortieAccessible();

    }

    public List<Position> trouverVoisinsNonVisites(Position pos) {
        List<Position> voisins = new ArrayList<>();

        // Vérifier les 4 positions (haut, bas, gauche, droite)
        if (pos.getX() > 1 && !grille[pos.getX() - 2][pos.getY()].getChemin()) {
            voisins.add(new Position(pos.getX() - 2, pos.getY())); // Haut
        }
        if (pos.getX() < grille.length - 2 && !grille[pos.getX() + 2][pos.getY()].getChemin()) {
            voisins.add(new Position(pos.getX() + 2, pos.getY())); // Bas
        }
        if (pos.getY() > 1 && !grille[pos.getX()][pos.getY() - 2].getChemin()) {
            voisins.add(new Position(pos.getX(), pos.getY() - 2));  // Gauche
        }
        if (pos.getY() < grille[0].length - 2 && !grille[pos.getX()][pos.getY() + 2].getChemin() && !(pos.getX() == 1 && pos.getY() == 1)) {
            voisins.add(new Position(pos.getX(), pos.getY() + 2)); // Droite
        }
        return voisins;
    }


    public void affficherLabyrinthe(){
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                if (grille[i][j].getChemin()) {
                    System.out.print("."); // Cellule du chemin
                } else {
                    System.out.print("#"); // Mur
                }
            }
            System.out.println(); // Nouvelle ligne après chaque rangée
        }
    }

    public Position getEntree() {
        return entree; // Retourne l'objet entrée correctement initialisé
    }

    // Nouvelle méthode pour garantir qu'il y a toujours un chemin adjacent à la sortie
    public void garantirSortieAccessible() {
        Position sortie = getSortie();
        List<Position> cheminsAdjacents = trouverVoisinsAccessibles(sortie);

        if (cheminsAdjacents.isEmpty()) {
            // Débloque une cellule voisine
            if (sortie.getX() > 0) {
                grille[sortie.getX() - 1][sortie.getY()].setChemin(true);
            } else if (sortie.getX() < grille.length - 1) {
                grille[sortie.getX() + 1][sortie.getY()].setChemin(true);
            } else if (sortie.getY() > 0) {
                grille[sortie.getX()][sortie.getY() - 1].setChemin(true);
            } else if (sortie.getY() < grille[0].length - 1) {
                grille[sortie.getX()][sortie.getY() + 1].setChemin(true);
            }
        }
    }

    private List<Position> trouverVoisinsAccessibles(Position pos) {
        List<Position> accessibles = new ArrayList<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int[] d : directions) {
            int nx = pos.getX() + d[0];
            int ny = pos.getY() + d[1];
            if (nx >= 0 && ny >= 0 && nx < grille.length && ny < grille[0].length) {
                if (grille[nx][ny].getChemin()) {
                    accessibles.add(new Position(nx, ny));
                }
            }
        }
        return accessibles;
    }

    // Ajout pour valider un chemin complet entre entrée et sortie
    public boolean verifierChemin(Position start, Position end) {
        boolean[][] visite = new boolean[grille.length][grille[0].length];
        return dfs(start, end, visite);
    }

    private boolean dfs(Position current, Position end, boolean[][] visite) {
        if (current.equals(end)) return true;

        visite[current.getX()][current.getY()] = true;

        for (Position voisin : trouverVoisinsAccessibles(current)) {
            if (!visite[voisin.getX()][voisin.getY()]) {
                if (dfs(voisin, end, visite)) return true;

            }
        }

        return false;
    }


    public boolean estPositionValide(Position pos) {
        if (pos.getX() < 0 || pos.getX() >= grille.length || pos.getY() < 0 || pos.getY() >= grille[0].length) {
            return false; // Hors des limites
        }
        return grille[pos.getX()][pos.getY()].getChemin(); // Vérifie si c'est un chemin
    }


    public boolean estMur(Position position) {
        // Vérifie si la position est hors des limites
        if (position.getX() < 0 || position.getX() >= grille.length ||
                position.getY() < 0 || position.getY() >= grille[0].length) {
            return true; // Considéré comme un mur
        }
        // Si la position est dans les limites, retourne si ce n'est pas un chemin
        return !grille[position.getX()][position.getY()].getChemin();
    }




}
