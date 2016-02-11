package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextArea editor;
    @FXML private TreeView projectTreeView;

    public void initialize() {
        TreeItem<String> rootItem = new TreeItem<>("Project");
        rootItem.setExpanded(true);

        TreeItem<String> src = new TreeItem<>("src");
        src.setExpanded(true);
        rootItem.getChildren().add(src);

        TreeItem<String> main = new TreeItem<>("main");
        main.setExpanded(true);
        src.getChildren().add(main);

        TreeItem<String> java = new TreeItem<>("java");
        java.setExpanded(true);
        main.getChildren().add(java);

        TreeItem<String> helloWorld = new TreeItem<>("HelloWorld.java");
        src.setExpanded(false);
        java.getChildren().add(helloWorld);

        TreeItem<String> gradle = new TreeItem<>("build.gradle");
        src.setExpanded(false);
        rootItem.getChildren().add(gradle);

        projectTreeView.setRoot(rootItem);
        projectTreeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue value, Object oldValue, Object newValue) {
                TreeItem<String> selected = (TreeItem<String>)newValue;
                if (selected.getValue().equals("build.gradle")) {
                    editor.setText("apply plugin: java");
                }
            }
        });
    }

    public void login(ActionEvent e) {

    }

}
