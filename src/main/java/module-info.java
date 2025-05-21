module com.example.jeu_echec_yalta {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.jeu_echec_yalta to javafx.fxml;
    exports com.example.jeu_echec_yalta;
	exports com.example.jeu_echec_yalta.controller;
	opens com.example.jeu_echec_yalta.controller to javafx.fxml;
}