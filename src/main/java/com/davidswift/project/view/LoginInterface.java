package com.davidswift.project.view;

import com.davidswift.project.controller.*;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

import java.util.logging.*;

public class LoginInterface extends Application {
  public static final Logger LOGGER = Logger.getLogger(LoginInterface.class.getName());
  //  private FXMLExample() {}
  //
  //  public static FXMLExample createFXMLExample() {return new FXMLExample();}
  public static Stage primaryStage;

  @Override
  public void start(final Stage stage) throws Exception {
    primaryStage = stage;
    final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource
        ("/assets/project/fxml/LoginInterface.fxml"));
    final Parent root = fxmlLoader.load();
    final LoginInterfaceController controller = fxmlLoader
        .getController();
    LOGGER.log(Level.INFO, controller.toString());
    controller.setPrevStage(primaryStage);
    stage.setTitle("Welcome");
    final Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

}
