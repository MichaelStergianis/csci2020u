package Registration;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.*;

/**
 * Registrar
 *
 * The purpose of this class is to provide an interface
 * for registering for a particular service
 */
public class Registrar extends Application {
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Registration Utility");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label unameLabel = new Label("Username:");
        grid.add(unameLabel, 0, 0);
        final TextField unameField = new TextField();
        unameField.setPromptText("LinusTorvalds");
        grid.add(unameField, 1, 0);

        Label passLabel = new Label("Password:");
        grid.add(passLabel, 0, 1);
        final PasswordField passField = new PasswordField();
        passField.setPromptText("*************");
        grid.add(passField, 1, 1);

        Label fnameLabel = new Label("Full Name:");
        grid.add(fnameLabel, 0, 2);
        final TextField fnameField = new TextField();
        fnameField.setPromptText("Linus Torvalds");
        grid.add(fnameField, 1, 2);

        Label emailLabel = new Label("E-Mail:");
        grid.add(emailLabel, 0, 3);
        final TextField emailField = new TextField();
        emailField.setPromptText("linus.torvalds@linux.org");
        grid.add(emailField, 1, 3);
        final Label emailError = new Label();
        grid.add(emailError, 2, 3);

        Label phoneLabel = new Label("Phone #:");
        grid.add(phoneLabel, 0, 4);
        final TextField phoneField = new TextField();
        phoneField.setPromptText("555-555-5555");
        grid.add(phoneField, 1, 4);

        Label dateLabel = new Label("Date of Birth:");
        grid.add(dateLabel, 0, 5);
        final DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("dd/mm/yyyy");
        grid.add(datePicker, 1, 5);

        Button regButton = new Button("Register");
        regButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(unameField.getText());
                System.out.println(passField.getText());
                System.out.println(fnameField.getText());
                EmailValidator emailValidator = EmailValidator.getInstance();
                if (emailValidator.isValid(emailField.getText())) {
                    emailError.setText(null);
                    System.out.println(emailField.getText());
                } else {
                    emailError.setText("Invalid E-Mail Address");
                }
                System.out.println(phoneField.getText());
                System.out.println(datePicker.getValue());

            }
        });
        grid.add(regButton, 1, 6);

        BorderPane layout = new BorderPane();
        layout.setCenter(grid);

        Scene scene = new Scene(layout, 500, 275);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    //private boolean parsePhoneNum(){

    //}

    public static void main(String[] args){
        launch(args);
    }
}
