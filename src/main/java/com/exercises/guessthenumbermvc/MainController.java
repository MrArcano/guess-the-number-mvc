/**
 * Sample Skeleton for 'main-view.fxml' Controller Class
 */

package com.exercises.guessthenumbermvc;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import com.exercises.guessthenumbermvc.model.Game;
import com.exercises.guessthenumbermvc.model.Result;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCheck"
    private Button btnCheck; // Value injected by FXMLLoader

    @FXML // fx:id="btnStartGame"
    private Button btnStartGame; // Value injected by FXMLLoader

    @FXML // fx:id="errorNMax"
    private Label errorNMax; // Value injected by FXMLLoader

    @FXML // fx:id="errorNTest"
    private Label errorNTest; // Value injected by FXMLLoader

    @FXML // fx:id="errorTMax"
    private Label errorTMax; // Value injected by FXMLLoader

    @FXML // fx:id="lblT"
    private Label lblT; // Value injected by FXMLLoader

    @FXML // fx:id="nMax"
    private TextField nMax; // Value injected by FXMLLoader

    @FXML // fx:id="tMax"
    private TextField tMax; // Value injected by FXMLLoader

    @FXML // fx:id="nTest"
    private TextField nTest; // Value injected by FXMLLoader

    @FXML // fx:id="messageArea"
    private TextArea messageArea; // Value injected by FXMLLoader

    @FXML // fx:id="pBarT"
    private ProgressBar pBarT; // Value injected by FXMLLoader

    @FXML // fx:id="flagAssistMode"
    private CheckBox flagAssistMode; // Value injected by FXMLLoader

    @FXML // fx:id="lblAssistMode"
    private Label lblAssistMode; // Value injected by FXMLLoader

    private Game model;

    @FXML
    void startGame(ActionEvent event) {
        // resetto i campi
        messageArea.setText("");
        nTest.setText("");
        errorNMax.setText("");
        errorTMax.setText("");
        errorNTest.setText("");
        pBarT.setProgress(0);
        lblAssistMode.setText("? < NUMERO < ?");

        // Prendo il numero massimo
        try {
            model.setMaxNumber(Integer.parseInt(nMax.getText()));
        } catch (NumberFormatException e) {
            errorNMax.setText("Errore inserimento!");
            return;
        }

        // Prendo il numero massimo di tentativi
        try {
            model.setMaxAttempts(Integer.parseInt(tMax.getText()));
        } catch (NumberFormatException e) {
            errorTMax.setText("Errore inserimento!");
            return;
        }

        // avvio il gioco
        model.startGame();

        // mostro il range possibile
        lblAssistMode.setText(model.getMinNumber() + " < NUMERO < " + model.getMaxNumber());

        // setto i tentativi possibili
        lblT.setText("Tentativi rimanenti: " + model.getRemaingAttempts());
    }

    @FXML
    void checkNumber(ActionEvent event) {
        // resetto la scritta di errore
        errorNTest.setText("");

        Result result;
        try {
            result = model.checkRandomNumberMatch(Integer.parseInt(nTest.getText()));
        } catch (NumberFormatException e) {
            errorNTest.setText("Errore inserimento!");
            return;
        }

        // aggiorno la progressBar
        pBarT.setProgress((double) model.getUsedAttempts() / model.getMaxAttempts());

        // resetto il numero inserito
        nTest.setText("");

        // aggiorno il range possibile
        lblAssistMode.setText(model.getMinNumber() + " < NUMERO < " + model.getMaxNumber());

        // aggiorno i tentativi rimanenti
        lblT.setText("Tentativi rimanenti: " + model.getRemaingAttempts());

        switch (result){
            case Result.tentativiFiniti: // ho finito i tentativi vincendo o perdendo
                messageArea.appendText("Gioco finito, inizia una nuova partita!\n");
                pBarT.setProgress(1);
                lblT.setText("Tentativi finiti");
                break;
            case Result.numeroRipetuto: // ripetizione ultimo numero
                errorNTest.setText("Errore ripetizione!");
                break;
            case Result.troppoAlto: // troppo alto
                messageArea.appendText("T"+ model.getUsedAttempts()  +": Il numero " + model.getCheckNumber() + " è troppo ALTO!\n");
                break;
            case Result.troppoBasso: // troppo basso
                messageArea.appendText("T"+ model.getUsedAttempts()  +": Il numero " + model.getCheckNumber() + " è troppo BASSO!\n");
                break;
            case Result.vinto: // vinto
                messageArea.appendText("Hai vinto. Il numero era: " + model.getnRand() + "\n");
                pBarT.setProgress(1);
                lblT.setText("Tentativi finiti");
                break;
            case Result.perso: // perso
                messageArea.appendText("Hai perso. Il numero era: " + model.getnRand() + "\n");
                lblT.setText("Tentativi finiti");
                break;
        }

    }

    @FXML
    void toggleAssistMode(ActionEvent event) {
        lblAssistMode.setVisible(flagAssistMode.isSelected());
    }

    public void setModel(Game model){
        this.model = model;
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'main-view.fxml'.";
        assert btnStartGame != null : "fx:id=\"btnStartGame\" was not injected: check your FXML file 'main-view.fxml'.";
        assert lblT != null : "fx:id=\"lblT\" was not injected: check your FXML file 'main-view.fxml'.";
        assert nMax != null : "fx:id=\"nMax\" was not injected: check your FXML file 'main-view.fxml'.";
        assert nTest != null : "fx:id=\"nTest\" was not injected: check your FXML file 'main-view.fxml'.";
        assert tMax != null : "fx:id=\"tMax\" was not injected: check your FXML file 'main-view.fxml'.";

    }

}
