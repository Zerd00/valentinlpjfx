package fr.eseo.lpjfx;

import java.util.ArrayList;
import java.util.List;

public class ListeDeCommandes {

    private final List<Commande> commandes;

    public ListeDeCommandes() {
        this.commandes = new ArrayList<>();
    }

    public void ajouterCommande(Commande commande) {
        commandes.add(commande);
    }

    public boolean executerToutes(Personnage personnage, Labyrinthe labyrinthe) {
        for (Commande commande : commandes) {
            if (!commande.executer(personnage, labyrinthe)) {
                return false;
            }
        }
        return true;
    }

    public void reinitialiserCommandes() {
        commandes.clear();
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void reinitialiser() {
        commandes.clear();
    }
}
