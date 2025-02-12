package dad.calendario.components;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class Calendario extends GridPane implements Initializable {

    // model

    private IntegerProperty anyo = new SimpleIntegerProperty(0);
    private IntegerProperty mes = new SimpleIntegerProperty(1);

    // view

    @FXML
    private Label monthName;

    public Calendario(){
        super();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CalendarView.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // bindings

        monthName.textProperty().bind(Bindings.createStringBinding(() -> {
            return LocalDate.of(anyo.get() , mes.get() , 1)
                    .getMonth().getDisplayName(TextStyle.FULL ,new Locale( "es" , "ES" ))
                    .toLowerCase();
        } , mes));

        for (int i = 8; i < getChildren().size(); i++){
            Label label = (Label) getChildren().get(i);

            label.textProperty().bind(Bindings.createStringBinding(() -> {
                LocalDate localDate = LocalDate.of(anyo.get(), mes.get(), 1);
                int firstDayOfMonth = localDate.getDayOfWeek().getValue();
                if (firstDayOfMonth > getChildren().indexOf(label) - 7 | (getChildren().indexOf(label) - firstDayOfMonth - 6) > localDate.lengthOfMonth()){
                    return "";
                }
                return "" + (getChildren().indexOf(label) - firstDayOfMonth - 6);
            } , anyo, mes));
        }
    }

    public int getAnyo() {
        return anyo.get();
    }

    public IntegerProperty anyoProperty() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo.set(anyo);
    }

    public int getMes() {
        return mes.get();
    }

    public IntegerProperty mesProperty() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes.set(mes);
    }
}
