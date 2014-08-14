package com.davidswift.project.view;/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 7/25/2014.
 */

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

import java.io.*;
import java.util.logging.*;

public class MainInterface extends Application {
  public static final Logger LOGGER = Logger.getLogger(Class.class.getName());
  //  private MainInterface() {super();}

  public static void main(final String... args) {
    launch(args);
  }
  //  public static MainInterface createInterface() {return new MainInterface();}

  @Override
  public void start(final Stage primaryStage) throws IOException {
    final Parent root = FXMLLoader.load(getClass().getResource(
        "/assets/project/fxml/MainInterface.fxml"));
    primaryStage.setTitle("Timetable System");
    primaryStage.setScene(new Scene(root, 800, 700));
    //    primaryStage.setResizable(false);
    primaryStage.show();
  }
}
