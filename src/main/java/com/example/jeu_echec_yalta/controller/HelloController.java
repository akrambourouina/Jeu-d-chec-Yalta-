package com.example.jeu_echec_yalta.controller;

import com.example.jeu_echec_yalta.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.lang.reflect.Field;
import java.util.List;

import javafx.scene.control.Alert;

public class HelloController {

    @FXML private Pane rootPane;
    private Plateau plateau;
    private Label[][] labels = new Label[12][12];
    private Case selectedCase = null;
    private int joueurActuel = 1; // Blanc commence
    private boolean partieTerminee = false;


    @FXML private Label case0_0, case0_1, case0_2, case0_3, case0_4, case0_5, case0_6, case0_7, case0_8, case0_9, case0_10, case0_11;
    @FXML private Label case1_0, case1_1, case1_2, case1_3, case1_4, case1_5, case1_6, case1_7, case1_8, case1_9, case1_10, case1_11;
    @FXML private Label case2_0, case2_1, case2_2, case2_3, case2_4, case2_5, case2_6, case2_7, case2_8, case2_9, case2_10, case2_11;
    @FXML private Label case3_0, case3_1, case3_2, case3_3, case3_4, case3_5, case3_6, case3_7, case3_8, case3_9, case3_10, case3_11;
    @FXML private Label case4_0, case4_1, case4_2, case4_3, case4_4, case4_5, case4_6, case4_7, case4_8, case4_9, case4_10, case4_11;
    @FXML private Label case5_0, case5_1, case5_2, case5_3, case5_4, case5_5, case5_6, case5_7, case5_8, case5_9, case5_10, case5_11;
    @FXML private Label case6_0, case6_1, case6_2, case6_3, case6_4, case6_5, case6_6, case6_7, case6_8, case6_9, case6_10, case6_11;
    @FXML private Label case7_0, case7_1, case7_2, case7_3, case7_4, case7_5, case7_6, case7_7, case7_8, case7_9, case7_10, case7_11;
    @FXML private Label case8_0, case8_1, case8_2, case8_3, case8_4, case8_5, case8_6, case8_7, case8_8, case8_9, case8_10, case8_11;
    @FXML private Label case9_0, case9_1, case9_2, case9_3, case9_4, case9_5, case9_6, case9_7, case9_8, case9_9, case9_10, case9_11;
    @FXML private Label case10_0, case10_1, case10_2, case10_3, case10_4, case10_5, case10_6, case10_7, case10_8, case10_9, case10_10, case10_11;
    @FXML private Label case11_0, case11_1, case11_2, case11_3, case11_4, case11_5, case11_6, case11_7, case11_8, case11_9, case11_10, case11_11;

    @FXML
    public void initialize() {
        plateau = new Plateau();
        plateau.initialiserVoisins();

        Joueur joueurBlanc = new Joueur("Blanc", 1);
        Joueur joueurRouge = new Joueur("Rouge", 2);
        Joueur joueurNoir = new Joueur("Noir", 0);

        plateau.initialiserJoueurGaucheYalta(joueurBlanc);
        plateau.initialiserJoueurHautYalta(joueurRouge);
        plateau.initialiserJoueurBasYalta(joueurNoir);

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                String fxId = "case" + i + "_" + j;
                try {
                    Field champ = getClass().getDeclaredField(fxId);
                    champ.setAccessible(true);
                    Label label = (Label) champ.get(this);
                    if (label == null) continue;
                    labels[i][j] = label;

                    final int x = i;
                    final int y = j;
                    label.setOnMouseClicked((MouseEvent e) -> gererClicCase(x, y));

                } catch (NoSuchFieldException | IllegalAccessException e) {
                    System.err.println("Aucun Label trouvé pour " + fxId);
                }
            }
        }
        plateau.ajouterObservateur(() -> afficherPiecesDansInterface());
        afficherPiecesDansInterface();
    }

    private void afficherPiecesDansInterface() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                Case c = plateau.getCase(i, j);
                if (c == null || !c.estValide()) continue;

                Label label = labels[i][j];
                if (c.getPiece() != null) {
                    label.setText(c.getPiece().getSymboleUnicode());
                    switch (c.getPiece().getJoueur().getCouleur()) {
                        case 1 -> label.setStyle("-fx-text-fill: white;");
                        case 2 -> label.setStyle("-fx-text-fill: red;");
                        case 0 -> label.setStyle("-fx-text-fill: black;");
                    }
                } else {
                    label.setText(" ");
                    label.setStyle("-fx-text-fill: grey;");
                }
            }
        }
    }

    private boolean estCaseDePromotion(Piece piece, Case destination) {
        if (!(piece instanceof Pion)) return false;
        int ligne = destination.getLigne();
        int colonne = destination.getColonne();
        int couleur = piece.getJoueur().getCouleur();

        return switch (couleur) {
            case 0 -> colonne == 7; // Noir arrive à gauche
            case 2 -> colonne == 0; // Blanc arrive à droite
            case 1 -> colonne == 11; // Rouge arrive en haut
            default -> false;
        };
    }

    private TypePiece demanderPromotion(Joueur joueur) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Promotion du pion");
        alert.setHeaderText("🎉 Promotion du pion de " + joueur.getNom());
        alert.setContentText("Choisissez la pièce :");

        ButtonType reine = new ButtonType("♕ Reine");
        ButtonType fou = new ButtonType("♗ Fou");
        ButtonType tour = new ButtonType("♖ Tour");
        ButtonType cavalier = new ButtonType("♘ Cavalier");

        alert.getButtonTypes().setAll(reine, fou, tour, cavalier);
        ButtonType resultat = alert.showAndWait().orElse(reine);

        if (resultat == fou) return TypePiece.FOU;
        if (resultat == tour) return TypePiece.TOUR;
        if (resultat == cavalier) return TypePiece.CAVALIER;
        return TypePiece.REINE;
    }


    private void afficherAlerte(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    private Joueur joueurEnEchec = null;
    private int compteurToursSousEchec = 0;


    private void gererClicCase(int ligne, int colonne) {
        if (partieTerminee) {
            System.out.println("🚫 La partie est terminée. Redémarrez pour rejouer.");
            return;
        }

        afficherPiecesDansInterface();
        Case clic = plateau.getCase(ligne, colonne);
        if (clic == null || !clic.estValide()) return;

        if (selectedCase != null) {
            Piece piece = selectedCase.getPiece();

            if (piece.getJoueur().getCouleur() != joueurActuel) {
                System.out.println("❌ Ce n'est pas ton tour !");
                selectedCase = null;
                //afficherPiecesDansInterface();
                return;
            }

            List<Case> deplacements = piece.getDeplacementsPossibles(plateau);
            for (Case casePossible : deplacements) {
                if (casePossible.getLigne() == ligne && casePossible.getColonne() == colonne) {

                    if (clic.getPiece() != null && clic.getPiece().getJoueur() != piece.getJoueur()) {
                        if (clic.getPiece().getType() == TypePiece.ROI) {
                            System.out.println("👑 Le roi ne peut pas être capturé directement !");
                            selectedCase = null;
                           // afficherPiecesDansInterface();
                            return;
                        } else {
                            System.out.println("⚔️ Capture de " + clic.getPiece().getSymboleAvecJoueur());
                        }
                    }

                    selectedCase.setPiece(null);
                    // 🎯 Promotion
                    if (piece instanceof Pion && estCaseDePromotion(piece, clic)) {
                        TypePiece choix = demanderPromotion(piece.getJoueur());
                        Piece nouvellePiece = PieceFactory.creer(choix, clic, piece.getJoueur());
                        clic.setPiece(nouvellePiece);
                        System.out.println("🔄 Promotion du pion en " + choix);
                    } else {
                        clic.setPiece(piece);
                        piece.setPosition(clic); // ✅ Seulement si ce n’est pas une promotion
                    }

                    selectedCase = null;
                   // afficherPiecesDansInterface();

                    verifierRoiEnDangerOuMat();

                    if (!partieTerminee) {
                        joueurActuel = (joueurActuel + 1) % 3;
                        System.out.println("👉 Au tour du joueur " + joueurActuel);
                    }
                    return;
                }
            }

            selectedCase = null;
           // afficherPiecesDansInterface();
            return;
        }

        if (clic.getPiece() != null && clic.getPiece().getJoueur().getCouleur() == joueurActuel) {
            selectedCase = clic;
         //   afficherPiecesDansInterface();

            List<Case> deplacements = clic.getPiece().getDeplacementsPossibles(plateau);
            for (Case cible : deplacements) {
                Label l = labels[cible.getLigne()][cible.getColonne()];
                if (l != null) {
                    l.setText(".");
                    l.setStyle("-fx-text-fill: black;");
                }
            }
        } else {
            selectedCase = null;
          //  afficherPiecesDansInterface();
        }
    }


    private void verifierRoiEnDangerOuMat() {
        if (partieTerminee) return;

        List<Joueur> joueursEnVie = plateau.getTousLesJoueursAvecRoi();

        for (Joueur joueur : joueursEnVie) {
            Piece roi = joueur.getRoi();
            if (roi == null) continue;

            boolean estEnEchec = plateau.estCaseMenacee(roi.getPosition(), joueur);
            if (!estEnEchec) {
                // ✅ Le roi n’est plus en échec → on réinitialise
                if (joueurEnEchec == joueur) {
                    joueurEnEchec = null;
                    compteurToursSousEchec = 0;
                }
                continue;
            }

            if (joueurEnEchec != joueur) {
                // Première fois que ce roi est en échec
                System.out.println("🚨 Le roi de " + joueur.getNom() + " est en échec.");
                afficherAlerte("Échec", "🚨 Le roi de " + joueur.getNom() + " est en échec !");
                joueurEnEchec = joueur;
                compteurToursSousEchec = 1;
            } else {
                compteurToursSousEchec++;
            }


            // ❌ Le roi est en échec depuis deux tours ⇒ Mat
            if (compteurToursSousEchec > 2) {

                System.out.println("♟️ Échec et mat pour le roi de " + joueur.getNom() + " !");
                afficherAlerte("Échec et mat", "♟️ Échec et mat pour le roi de " + joueur.getNom() + " !");

                partieTerminee = true;
                afficherAlerte("Victoire", "🏆 Le joueur " + (joueurActuel+1) + " a gagné !");
                System.out.println("🛑 La partie est terminée.");
            }

            return;
        }

        // Aucun roi en échec
        joueurEnEchec = null;
        compteurToursSousEchec = 0;
    }










}
