package fr.eseo.lpjfx.controller;

import fr.eseo.lpjfx.*;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class LabyrintheController {

    @FXML
    private GridPane gridPane; // Grille pour afficher le labyrinthe
    private Polygon fleche; // Flèche pour afficher la direction du personnage

    private Labyrinthe labyrinthe; // Instance du labyrinthe
    private Personnage personnage; // Instance du personnage contrôlé par le joueur
    private ListeDeCommandes listeDeCommandes; // Liste des commandes à exécuter
    private double angleActuel = 0.0; // Angle actuel de la flèche

    @FXML
    private Label commandesLabel; // Label pour afficher l'état des commandes ajoutées
    @FXML
    private VBox commandesVBox; // Conteneur des commandes ajoutées

    public void initialize() {
        labyrinthe = new Labyrinthe(11, 11);
        labyrinthe.genererLabyrinthe();
        personnage = new Personnage(labyrinthe.getEntree());
        listeDeCommandes = new ListeDeCommandes();
        afficherLabyrinthe();
    }

    public void ajouterCommandeAvancer() {
        Commande avancer = new Commande(TypeCommande.AVANCER, 0);
        listeDeCommandes.ajouterCommande(avancer);
        afficherCommande("AVANCER", Color.BLUE);
    }

    public void ajouterCommandeTournerGauche() {
        Commande tournerGauche = new Commande(TypeCommande.TOURNER_GAUCHE, 0);
        listeDeCommandes.ajouterCommande(tournerGauche);
        afficherCommande("TOURNER GAUCHE", Color.GREEN);
    }

    public void ajouterCommandeTournerDroite() {
        Commande tournerDroite = new Commande(TypeCommande.TOURNER_DROITE, 0);
        listeDeCommandes.ajouterCommande(tournerDroite);
        afficherCommande("TOURNER DROITE", Color.RED);
    }

    public void ajouterCommandeBoucle() {
        Commande boucle = new Commande(TypeCommande.BOUCLE, 3); // Exemple : boucle qui répète 3 fois
        listeDeCommandes.ajouterCommande(boucle);
        afficherCommande("BOUCLE", Color.YELLOW);
    }

    public void ajouterCommandeSi() {
        Commande si = new Commande(TypeCommande.SI, 0);
        listeDeCommandes.ajouterCommande(si);
        afficherCommande("SI", Color.PURPLE);
    }

    private void afficherCommande(String commande, Color couleur) {
        Label commandeLabel = new Label(commande);
        commandeLabel.setTextFill(couleur);
        commandesVBox.getChildren().add(commandeLabel);
    }

    public void executerCommandes() {
        boolean success = listeDeCommandes.executerToutes(personnage, labyrinthe);
        if (success) {
            commandesLabel.setText("Toutes les commandes exécutées avec succès.");
        } else {
            commandesLabel.setText("Une erreur est survenue lors de l'exécution des commandes.");
        }
        // Met à jour l'affichage du labyrinthe et du personnage
        afficherLabyrinthe();
    }



    @FXML
    private void reinitialiserCommandes() {
        // Supprimer tous les labels de commandes affichés dans le VBox
        commandesVBox.getChildren().clear();

        // Réinitialiser la liste des commandes
        listeDeCommandes.reinitialiser();

        // Mettre à jour le label d'état
        commandesLabel.setText("Commandes réinitialisées.");
    }


    private void afficherLabyrinthe() {
        Cellule[][] grille = labyrinthe.getGrille();
        gridPane.getChildren().clear(); // Réinitialise le contenu de la grille

        // Parcourt chaque cellule de la grille pour l'afficher
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                Rectangle rect = new Rectangle(20, 20);

                // Affichage des différents éléments du labyrinthe
                if (i == labyrinthe.getSortie().getX() && j == labyrinthe.getSortie().getY()) {
                    rect.setFill(Color.RED); // Sortie
                } else if (i == labyrinthe.getEntree().getX() && j == labyrinthe.getEntree().getY()) {
                    rect.setFill(Color.GREEN); // Entrée
                } else if (grille[i][j].getChemin()) {
                    rect.setFill(Color.WHITE); // Chemin libre
                } else {
                    rect.setFill(Color.BLACK); // Mur
                }

                gridPane.add(rect, j, i); // Ajoute la cellule à la grille
            }
        }

        // Affiche la position actuelle du personnage
        afficherPersonnage();
    }

    public void afficherPersonnage() {
        if (fleche == null) {
            fleche = new Polygon();
            fleche.setFill(Color.ORANGE);
        }

        // Récupère la position du personnage
        Position pos = personnage.getPosition();

        // Met à jour la position de la flèche dans la grille
        GridPane.setRowIndex(fleche, pos.getX());
        GridPane.setColumnIndex(fleche, pos.getY());

        // Met à jour la direction de la flèche
        definirDirectionFleche(personnage.getDirection());

        // Ajoute la flèche si elle n'est pas déjà dans la grille
        if (!gridPane.getChildren().contains(fleche)) {
            gridPane.getChildren().add(fleche);
        }
    }


    private void definirDirectionFleche(Direction direction) {
        fleche.getPoints().clear(); // Réinitialise les points de la flèche

        switch (direction) {
            case EST:
                fleche.getPoints().addAll(7.5, 0.0, 15.0, 15.0, 0.0, 15.0);
                break;
            case OUEST:
                fleche.getPoints().addAll(0.0, 0.0, 15.0, 0.0, 7.5, 15.0);
                break;
            case NORD:
                fleche.getPoints().addAll(0.0, 0.0, 15.0, 7.5, 0.0, 15.0);
                break;
            case SUD:
                fleche.getPoints().addAll(15.0, 0.0, 0.0, 7.5, 15.0, 15.0);
                break;
        }
    }

    public void avancerPers() {
        System.out.println("Avant avancerPers - Rotation actuelle : " + fleche.getRotate());

        // Vérifie si le personnage peut avancer dans la direction actuelle
        boolean avance = personnage.avancer(labyrinthe);
        if (avance) {
            Position nouvellePosition = personnage.getPosition();

            // Réinitialiser les transformations (éviter toute rotation résiduelle)
            fleche.setRotate(0); // Réinitialise la rotation de la flèche à 0° (si nécessaire)

            System.out.println("Après setRotate(0) - Rotation actuelle : " + fleche.getRotate());

            // Met à jour la position du personnage dans la grille
            afficherLabyrinthe();

            // Transition pour déplacer le personnage sans rotation
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), fleche);
            transition.setByX((nouvellePosition.getY() - GridPane.getColumnIndex(fleche)) * 20);
            transition.setByY((nouvellePosition.getX() - GridPane.getRowIndex(fleche)) * 20);
            transition.setInterpolator(Interpolator.EASE_BOTH);
            transition.play();
        } else {
            System.out.println("Le personnage ne peut pas avancer.");
        }

        System.out.println("Après avancerPers - Rotation actuelle : " + fleche.getRotate());
    }




    public void tournerGauche() {
        personnage.tournerGauche();
        System.out.println("Le personnage tourne à gauche.");

        // Transition pour tourner la flèche
        RotateTransition transition = new RotateTransition(Duration.seconds(0.5), fleche);
        transition.setByAngle(-90); // Tourne de 90° vers la gauche
        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.play();
    }

    public void tournerDroite() {
        personnage.tournerDroite();
        System.out.println("Le personnage tourne à droite.");

        // Transition pour tourner la flèche
        RotateTransition transition = new RotateTransition(Duration.seconds(0.5), fleche);
        transition.setByAngle(90); // Tourne de 90° vers la droite
        transition.setInterpolator(Interpolator.EASE_BOTH);
        transition.play();
    }



    public void avancerPersAvecAnimation() {
        // Vérifie si le personnage peut avancer dans le labyrinthe
        boolean peutAvancer = personnage.avancer(labyrinthe);

        if (peutAvancer) {
            // Récupère les positions avant et après le déplacement
            Position positionInitiale = personnage.getPosition(); // Position avant mise à jour
            afficherLabyrinthe(); // Met à jour la grille (le modèle a déjà changé la position)

            // Transition pour le déplacement visuel
            TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), fleche);
            transition.setByX((GridPane.getColumnIndex(fleche) - positionInitiale.getY()) * 20);
            transition.setByY((GridPane.getRowIndex(fleche) - positionInitiale.getX()) * 20);
            transition.setInterpolator(Interpolator.EASE_BOTH);

            // Jouer l'animation
            transition.setOnFinished(event -> afficherLabyrinthe());
            transition.play();
        } else {
            System.out.println("Impossible d'avancer : mur détecté.");
        }
    }




    private double calculerAngle(Direction direction) {
        switch (direction) {
            case NORD: return 0;
            case EST: return 90;
            case SUD: return 180;
            case OUEST: return 270;
            default: return 0;
        }
    }


    public void avancerPersAvecRotationEtAnimation(Direction nouvelleDirection) {
        // Transition pour tourner la flèche
        RotateTransition rotation = new RotateTransition(Duration.seconds(0.3), fleche);
        double angleCible = calculerAngle(nouvelleDirection); // Calcule l'angle cible
        rotation.setToAngle(angleCible);
        rotation.setInterpolator(Interpolator.EASE_BOTH);

        // Transition pour avancer après la rotation
        TranslateTransition translation = new TranslateTransition(Duration.seconds(0.5), fleche);
        Position positionInitiale = personnage.getPosition();
        boolean peutAvancer = personnage.avancer(labyrinthe);

        if (peutAvancer) {
            Position positionFinale = personnage.getPosition();
            translation.setByX((positionFinale.getY() - positionInitiale.getY()) * 20);
            translation.setByY((positionFinale.getX() - positionInitiale.getX()) * 20);
            translation.setInterpolator(Interpolator.EASE_BOTH);
        } else {
            System.out.println("Impossible d'avancer !");
        }

        // Enchaîner rotation puis translation
        SequentialTransition animation = new SequentialTransition(rotation, translation);
        animation.setOnFinished(event -> afficherLabyrinthe());
        animation.play();
    }


}


