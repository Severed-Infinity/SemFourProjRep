package com.davidswift.project.view;/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 7/25/2014.
 */

import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.logging.*;

public class Interface extends Application {
  public static final Logger LOGGER = Logger.getLogger(Class.class.getName());

//  private Interface() {super();}

  public static void main(final String... args) {
    launch(args);
  }

//  public static Interface createInterface() {return new Interface();}

  @Override
  public void start(final Stage primaryStage) throws IOException {
    final Parent root = FXMLLoader.load(getClass().getResource("Interface.fxml"));

    primaryStage.setTitle("FXML Welcome");
    primaryStage.setScene(new Scene(root, 650, 400));
    primaryStage.setResizable(false);
    primaryStage.show();
  }
}
