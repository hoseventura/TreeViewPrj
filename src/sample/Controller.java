package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.stage.Modality.WINDOW_MODAL;

public class Controller implements Initializable{

    @FXML
    TreeView<File> treeView;
    @FXML
    Button button;
    private TreeItem<File> createNode(final File f, ImageView icon) {
        return new TreeItem<File>(f, icon) {

            private boolean isLeaf;

            private boolean isFirstTimeChildren = true;
            private boolean isFirstTimeLeaf = true;

            @Override
            public ObservableList<TreeItem<File>> getChildren() {
                if (isFirstTimeChildren) {
                    isFirstTimeChildren = false;

                    super.getChildren().setAll(buildChildren(this));
                }
                return super.getChildren();
            }

            @Override
            public boolean isLeaf() {
                if (isFirstTimeLeaf) {
                    isFirstTimeLeaf = false;
                    File f = (File) getValue();
                    isLeaf = f.isFile();
                }
                return isLeaf;
            }

            private ObservableList<TreeItem<File>> buildChildren(TreeItem<File> TreeItem) {
                Image icon = new Image(getClass().getResourceAsStream("/image/folder.png"));
                File f = TreeItem.getValue();
                if (f != null && f.isDirectory()) {
                    File[] files = f.listFiles();
                    if (files != null) {
                        ObservableList<TreeItem<File>> children = FXCollections.observableArrayList();

                        for (File childFile : files) {
                            children.add(createNode(childFile, new ImageView(icon)));
                        }

                        return children;
                    }
                }

                return FXCollections.emptyObservableList();
            }
        };
    }
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {

    Image icon = new Image(getClass().getResourceAsStream("/image/folder.png"));
    TreeItem <File> file = createNode(new File("/"), new ImageView(icon));
//    TreeItem <File> file = new TreeItem<> (new File("/home/jack"), new ImageView(icon));
       treeView.setRoot(file);
    }
    public void showDialog(ActionEvent actionEvent) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("module.fxml"));
        stage.setTitle("Dialog");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.initModality(WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }
}
