package com.melissadata.globalemail;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.melissadata.globalemail.view.GlobalEmailController;
import com.melissadata.globalemail.view.RootLayoutController;

public class Main extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    public void start(Stage primaryStage) {
        try {
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("Global Email Java Sample Application");

            initRootLayout();

            showBusinessSearch();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);
            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showBusinessSearch() {
        try {
            // Load Global Email overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("GlobalEmail.fxml"));
            BorderPane businessSearch = (BorderPane) loader.load();

            // Set Global Email
            rootLayout.setCenter(businessSearch);

            // Give the controller access to the main app
            GlobalEmailController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
