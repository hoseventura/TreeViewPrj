package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.stage.Modality.WINDOW_MODAL;

public class ModuleWindow {

    prrivate void  showDialog(AcctionEvent actionEvent){
        Stage stage = new Stage();
        Parennt root = FXMLLoader.load(getClass().getResource("module.fxml"));
        stage.setTitle("Dialog");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }
}
