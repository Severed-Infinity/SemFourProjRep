package com.davidswift.project.controller;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;

import java.net.*;
import java.util.*;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 8/14/2014.
 */
public class ModuleInterfaceController implements Initializable{
  public TableView moduleTableView;
  public TableColumn moduleIDField;
  public TableColumn moduleNameField;
  public TableColumn moduleLecturerField;
  public TableColumn courseIDField;
  public TextField newModuleID;
  public TextField newModuleName;
  public TextField newModuleLecturer;
  public TextField newCourseID;
  public Button newModule;
  public Button updateModule;
  public Button deleteModule;
  public Button clearFields;

  @Override
  public void initialize(final URL url, final ResourceBundle resourceBundle) {
  }

  public void addNewCourse(final ActionEvent actionEvent) {

  }

  public void updateCourse(final ActionEvent actionEvent) {

  }

  public void removeCourse(final ActionEvent actionEvent) {

  }

  public void clearFields(final ActionEvent actionEvent) {

  }
}
