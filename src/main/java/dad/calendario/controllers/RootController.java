package dad.calendario.controllers;

import dad.calendario.components.Calendario;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    // model

    private IntegerProperty anyo = new SimpleIntegerProperty(2025);

    // view

    @FXML
    private Calendario abrilComponent;

    @FXML
    private Calendario agostoComponent;

    @FXML
    private Label añoLabel;

    @FXML
    private Calendario diciembreComponent;

    @FXML
    private Calendario eneroComponent;

    @FXML
    private Calendario febreroComponent;

    @FXML
    private Calendario julioComponent;

    @FXML
    private Calendario junioComponent;

    @FXML
    private Calendario marzoComponent;

    @FXML
    private Calendario mayoComponent;

    @FXML
    private Calendario noviembreComponent;

    @FXML
    private Calendario octubreComponent;

    @FXML
    private GridPane root;

    @FXML
    private Calendario septiembreComponent;


    public RootController(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // bindings

        añoLabel.textProperty().bindBidirectional(anyo, new NumberStringConverter("#"));

        eneroComponent.anyoProperty().bind(anyo);
        febreroComponent.anyoProperty().bind(anyo);
        marzoComponent.anyoProperty().bind(anyo);
        abrilComponent.anyoProperty().bind(anyo);
        mayoComponent.anyoProperty().bind(anyo);
        junioComponent.anyoProperty().bind(anyo);
        julioComponent.anyoProperty().bind(anyo);
        agostoComponent.anyoProperty().bind(anyo);
        septiembreComponent.anyoProperty().bind(anyo);
        octubreComponent.anyoProperty().bind(anyo);
        noviembreComponent.anyoProperty().bind(anyo);
        diciembreComponent.anyoProperty().bind(anyo);
    }

    public GridPane getRoot() {
        return root;
    }


    @FXML
    void onNextYearAction(ActionEvent event) {
        anyo.set(anyo.get() + 1);
    }

    @FXML
    void onPreviousYearAction(ActionEvent event) {
        anyo.set(anyo.get() - 1);
    }
}
