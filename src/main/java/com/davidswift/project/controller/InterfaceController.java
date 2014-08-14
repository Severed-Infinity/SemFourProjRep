package com.davidswift.project.controller;

import com.davidswift.project.utility.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 7/28/2014.
 */
public class InterfaceController implements Initializable {
  public static final Logger LOGGER = Logger.getLogger(Class.class.getName());
  private static Stage prevStage;
  public Button logout;

  public static Stage getPrevStage() {
    return prevStage;
  }

  public static void setPrevStage(final Stage stage) {
    prevStage = stage;
  }

  @Override
  public void initialize(final URL url, final ResourceBundle resourceBundle) {
  }

  public void handleLogoutButtonAction(final ActionEvent actionEvent) {
    try {
      closeMain();
    } catch (final IOException e) {
      LOGGER.log(Level.SEVERE, "Unable to logout", e);
    } finally {
      try {
        LOGGER.log(Level.INFO, "Closing Database Connection...");
        DatabaseConnection.getInstance().close();
      } catch (final SQLException e) {
        LOGGER.log(Level.SEVERE, "Unable to close database connection", e);
      }
    }
  }

  private void closeMain() throws IOException {
    final Stage stage = new Stage();
    stage.setTitle("Database Management");
    final Pane pane = FXMLLoader.load(getClass().getResource("/assets/project/fxml/LoginInterface" +
        ".fxml"));
    final Scene scene = new Scene(pane);
    stage.setScene(scene);
    prevStage.close();
    stage.show();
  }
}
