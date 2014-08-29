package com.davidswift.project.controller;

import com.davidswift.project.model.*;
import com.davidswift.project.model.CourseProperty.*;
import com.davidswift.project.model.ModuleProperty.*;
import com.davidswift.project.utility.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;

import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import java.util.regex.*;
import java.util.stream.*;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 8/14/2014.
 */
public class ModuleInterfaceController implements Initializable {
  private static final Logger LOGGER = Logger.getLogger(ModuleInterfaceController.class.getName());
  public TableView<ModuleProperty> moduleTableView;
  public TableColumn<ModuleProperty, Integer> moduleIDField;
  public TableColumn<ModuleProperty, String> moduleNameField;
  public TableColumn<ModuleProperty, String> moduleLecturerField;
  public TableColumn<ModuleProperty, Integer> courseIDField;
  public TextField newModuleID;
  public TextField newModuleName;
  public TextField newModuleLecturer;
  public TextField newCourseID;
  public Button newModule;
  public Button updateModule;
  public Button deleteModule;
  public Button clearFields;
  private ObservableList<ModuleProperty> modulesList;

  @Override
  public void initialize(final URL url, final ResourceBundle resourceBundle) {
    initModuleTable();
    initModuleFields();
  }

  private void initModuleFields() {
    this.newModule.setDisable(true);
    final Pattern idPattern = Pattern.compile("[0-9]|[0-9]+");
    final Pattern fullNamePattern = Pattern.compile("[A-Z][a-z]+\\s+[A-Z][a-z]+");
    initModuleIDTextfield(idPattern);
    initModuleNameTextfield(fullNamePattern);
    initModuleLecturerTextfield(fullNamePattern);
    initCourseIDTextfield(idPattern);
  }

  private void initCourseIDTextfield(final Pattern idPattern) {
    this.newCourseID.setPromptText("Enter Course identification number");
    final ObservableList<CourseProperty> courses = FXCollections.observableArrayList();
    try (Connection connection = DatabaseConnection.getInstance(); Statement statement = connection
        .createStatement(); ResultSet resultSet = statement
        .executeQuery("SELECT * FROM COURSETABLE")) {
      while (resultSet.next()) {
        LOGGER.log(Level.INFO, "checking courses");
        courses.addAll(
            CoursePropertyBuilder.createCoursePropertyBuilder().setCourseID(resultSet.getInt(1))
                .setCourseName(resultSet.getString(2)).setCourseHead(resultSet.getString(3))
                .setCourseLength(resultSet.getInt(4)).setDepartment(
                resultSet.getString(5)).createCourseProperty());
      }
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "No courses where found", e);
    }
    this.newCourseID.textProperty().addListener((observableValue, s, s2) -> {
      final Matcher matcher = idPattern.matcher(s2);
      this.newCourseID.setTooltip(new Tooltip("Must be a valid Course ID"));
      if (matcher.matches()) {
        this.newCourseID.setText(s2);
        this.newCourseID.setStyle("-fx-border-color: transparent;");
        this.newModule.setDisable(false);
        try (Stream<CourseProperty> coursePropertyStream = courses.stream();
            Stream<CourseProperty> filter = coursePropertyStream.filter
                (course ->
                    Integer.parseInt(s2) != course.getCourseID())) {
          filter.forEach(
              course -> {
                this.newCourseID.setStyle(
                    "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
                this.newCourseID.setTooltip(new Tooltip("Must be a valid Course ID"));
                this.newModule.setDisable(true);
              });
        }
      } else if (this.newCourseID.textProperty().getValue().isEmpty()) {
        this.newCourseID.setStyle("-fx-border-color: transparent");
        this.newModule.setDisable(true);
      } else {
        this.newCourseID.setStyle(
            "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
        this.newModule.setDisable(true);
      }
    });
  }

  private void initModuleLecturerTextfield(final Pattern fullNamePattern) {
    this.newModuleLecturer.setPromptText("Enter Module Lecturer");
    this.newModuleLecturer.textProperty().addListener((observableValue, s, s2) -> {
      Matcher matcher = fullNamePattern.matcher(s2);
      if (matcher.matches()) {
        this.newModuleLecturer.setText(s2);
        this.newModuleLecturer.setStyle("-fx-border-color: transparent;");
        this.newModule.setDisable(false);

      } else if (this.newModuleLecturer.textProperty().getValue().isEmpty()) {
        this.newModuleLecturer.setStyle("-fx-border-color: transparent");
        this.newModule.setDisable(true);
      } else {
        this.newModuleLecturer.setStyle(
            "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
        this.newModuleLecturer.setTooltip(
            new Tooltip("Must be a full name" + "\nMust contain a space" +
                " " +
                "after first name" +
                "\nMust be longer " +
                "than one character"));
        this.newModule.setDisable(true);
      }
    });
  }

  private void initModuleNameTextfield(final Pattern fullNamePattern) {
    this.newModuleName.setPromptText("Enter Module Name");
    this.newModuleName.textProperty().addListener((observableValue, s, s2) -> {
      Matcher matcher = fullNamePattern.matcher(s2);
      if (matcher.matches()) {
        this.newModuleName.setText(s2);
        this.newModuleName.setStyle("-fx-border-color: transparent;");
        this.newModule.setDisable(false);

      } else if (this.newModuleName.textProperty().getValue().isEmpty()) {
        this.newModuleName.setStyle("-fx-border-color: transparent");
        this.newModule.setDisable(true);
      } else {
        this.newModuleName.setStyle(
            "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
        this.newModuleName.setTooltip(new Tooltip("Must begin with a Capital \nMust be a " +
            "full named department" +
            "\nMust be longer " +
            "than one character"));
        this.newModule.setDisable(true);
      }
    });
  }

  private void initModuleIDTextfield(final Pattern idPattern) {
    this.newModuleID.setPromptText("Enter identification number");
    this.newModuleID.textProperty().addListener((observableValue, s, s2) -> {
      Matcher matcher = idPattern.matcher(s2);
      try (Stream<ModuleProperty> modulePropertyStream = this.modulesList.stream()) {
        boolean matches = modulePropertyStream.anyMatch(module -> Integer.parseInt(s2) == module
            .moduleIDProperty().get());
        if (matcher.matches()) {
          this.newModuleID.setText(s2);
          this.newModuleID.setStyle("-fx-border-color: transparent;");
          this.newModuleID.setTooltip(new Tooltip("Must be Numeric Characters only"));
          this.newModule.setDisable(false);
          if (matches) {
            this.newModuleID.setStyle(
                "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
            this.newModuleID.setTooltip(new Tooltip("This ID is already assigned"));
            this.newModule.setDisable(true);
          }
        } else if (this.newModuleID.getText().isEmpty()) {
          this.newModuleID.setStyle("-fx-border-color: transparent");
          this.newModuleID.setTooltip(new Tooltip("Must be Numeric Characters only"));
        } else {
          this.newModuleID.setStyle(
              "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
          this.newModuleID.setTooltip(new Tooltip("Must be Numeric Characters only"));
          this.newModule.setDisable(true);
        }
      }
    });

  }

  private void initModuleTable() {
    try (Connection connection = DatabaseConnection.getInstance(); Statement statement = connection
        .createStatement(); ResultSet resultSet = statement
        .executeQuery(
            "SELECT " +
                "* FROM MODULETABLE")) {
      this.modulesList = FXCollections.observableArrayList(
          //          CoursePropertyBuilder.createCoursePropertyBuilder().setCourseID
          //              (10).setCourseName("testcourse").setCourseLength(4).setDepartment
          // ("testdepartment")
          //              .setCourseHead("testcoursehead").createCourseProperty()
      );
      while (resultSet.next()) {
        this.modulesList.add(ModulePropertyBuilder.createModulePropertyBuilder().setModuleID
            (resultSet.getInt(1)).setModuleName(resultSet
            .getString(2)).setModuleLecturer(resultSet.getString(3)).setCourseID(
            resultSet.getInt(4)).createModuleProperty());
      }
      this.moduleIDField.setCellValueFactory(
          cellData -> cellData.getValue().moduleIDProperty().asObject());
      this.courseIDField.setCellValueFactory(
          cellData -> cellData.getValue().courseIDProperty().asObject());
      this.moduleNameField.setCellValueFactory(
          cellData -> cellData.getValue().moduleNameProperty());
      this.moduleLecturerField.setCellValueFactory(
          cellData -> cellData.getValue().moduleLecturerProperty());
      this.moduleTableView.setItems(this.modulesList);
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to execute query", e);
    }
  }

  public void addNewModule(final ActionEvent actionEvent) {
    final int setID;
    if (this.newModuleID.getText().isEmpty()) {
      int nextID = 0;
      for (final ModuleProperty moduleProperty : this.modulesList) {
        if (nextID <= moduleProperty.courseIDProperty().get()) {
          nextID = moduleProperty.courseIDProperty().get() + 1;
        }
        LOGGER.log(Level.INFO, String.valueOf(nextID));
      }
      setID = nextID;
    } else {
      setID = Integer.parseInt(
          this.newModuleID.getText());
    }
    if (this.newModuleName.textProperty().getValue().isEmpty()) {
      this.newModuleName.setStyle(
          "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
      LOGGER.log(Level.SEVERE, "Fields cannot not be empty");
    } else if (this.newModuleLecturer
        .textProperty
            ().getValue().isEmpty()) {
      this.newModuleLecturer.setStyle(
          "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
      LOGGER.log(Level.SEVERE, "Fields cannot not be empty");
    } else if (this.newCourseID.textProperty().getValue().isEmpty
        ()) {
      this.newCourseID.setStyle(
          "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
      LOGGER.log(Level.SEVERE, "Fields cannot not be empty");

    } else {
      final ModuleProperty newModule = ModulePropertyBuilder.createModulePropertyBuilder()
          .setModuleID(setID).setModuleName(this.newModuleName.getText()).setModuleLecturer
              (this.newModuleLecturer.getText()).setCourseID(
              Integer.parseInt(this.newCourseID.getText())).createModuleProperty();
      newModule.addToDB();
      this.modulesList.addAll(newModule);
      this.clearFields();
    }
  }

  public void clearFields() {
    this.newModuleName.clear();
    this.newModuleLecturer.clear();
    this.newCourseID.clear();
    this.newModuleID.clear();
  }

  public void updateModule(final ActionEvent actionEvent) {
  }

  public void removeModule(final ActionEvent actionEvent) {
    final ModuleProperty selectedModuleProperty = selectModuleProperty();
    if (selectedModuleProperty.moduleIDProperty().get() != 0) {
      selectedModuleProperty.removeFromDB();
      this.modulesList.remove(selectedModuleProperty);
    }
  }

  private ModuleProperty selectModuleProperty() {
    return this.moduleTableView.getSelectionModel().getSelectedItem();
  }
}
