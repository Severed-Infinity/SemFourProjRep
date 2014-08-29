package com.davidswift.project.controller;

import com.davidswift.project.model.*;
import com.davidswift.project.model.ClassProperty.*;
import com.davidswift.project.model.ModuleProperty.*;
import com.davidswift.project.model.RoomProperty.*;
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
 * Created by david on 8/22/2014.
 */
public class ClassInterfaceController implements Initializable {
  private static final Logger LOGGER = Logger.getLogger(ClassInterfaceController.class.getName());
  public TableView<ClassProperty> classTableView;
  public TableColumn<ClassProperty, Integer> classIDField;
  public TableColumn<ClassProperty, String> dayField;
  public TableColumn<ClassProperty, Double> timeSlotField;
  public TableColumn<ClassProperty, Integer> roomNumberField;
  public TableColumn<ClassProperty, Integer> moduleIDField;
  public TextField newClassID;
  public ChoiceBox newClassDay;
  public ChoiceBox newClassTimeSlot;
  public TextField newRoomNumber;
  public TextField newModuleID;
  public Button newClass;
  public Button updateClass;
  public Button deleteClass;
  public Button clearFields;
  private ObservableList<ClassProperty> classesList;

  @Override
  public void initialize(final URL url, final ResourceBundle resourceBundle) {
    initClassTable();
    initClassFields();
  }

  private void initClassFields() {
    final Pattern idPattern = Pattern.compile("[0-9]|[0-9]+");
    initClassIDTextfield(idPattern);
    initRoomNumberTextfield(idPattern);
    initModuleIDTextfield(idPattern);
    this.newClassDay.setTooltip(new Tooltip("Select the day of the week"));
    this.newClassDay.getSelectionModel().select(0);
    this.newClassTimeSlot.setTooltip(new Tooltip("Select the time slot"));
    this.newClassTimeSlot.getSelectionModel().select(0);
  }

  private void initModuleIDTextfield(final Pattern idPattern) {
    this.newModuleID.setPromptText("Enter Module identification number");
    final ObservableList<ModuleProperty> modules = FXCollections.observableArrayList();
    try (Connection connection = DatabaseConnection.getInstance(); Statement statement = connection
        .createStatement(); ResultSet resultSet = statement
        .executeQuery("SELECT * FROM MODULETABLE")) {
      while (resultSet.next()) {
        LOGGER.log(Level.INFO, "checking modules");
        modules.addAll(
            ModulePropertyBuilder.createModulePropertyBuilder().setModuleID(resultSet.getInt(1))
                .setModuleName
                (resultSet.getString(2)).setModuleLecturer(resultSet.getString(3)).setCourseID
                (resultSet.getInt(4)).createModuleProperty());

      }
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "No Module where found", e);
    }
    this.newModuleID.textProperty().addListener((observableValue, s, s2) -> {
      final Matcher matcher = idPattern.matcher(s2);
      this.newModuleID.setTooltip(new Tooltip("Must be a valid Module ID"));
      if (matcher.matches()) {
        this.newModuleID.setText(s2);
        this.newModuleID.setStyle("-fx-border-color: transparent;");
        this.newClass.setDisable(false);
        try (Stream<ModuleProperty> modulePropertyStream = modules.stream();
            Stream<ModuleProperty> filter = modulePropertyStream.filter
                (module ->
                    Integer.parseInt(s2) != module.moduleIDProperty().get())) {
          filter.forEach(
              module -> {
                this.newModuleID.setStyle(
                    "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
                this.newModuleID.setTooltip(new Tooltip("Must be a valid Module ID"));
                this.newClass.setDisable(true);
              });
        }
      } else if (this.newModuleID.textProperty().getValue().isEmpty()) {
        this.newModuleID.setStyle("-fx-border-color: transparent");
        this.newClass.setDisable(true);
      } else {
        this.newModuleID.setStyle(
            "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
        this.newClass.setDisable(true);
      }
    });
  }

  private void initRoomNumberTextfield(final Pattern idPattern) {
    this.newRoomNumber.setPromptText("Enter room number");
    final ObservableList<RoomProperty> rooms = FXCollections.observableArrayList();
    try (Connection connection = DatabaseConnection.getInstance(); Statement statement = connection
        .createStatement(); ResultSet resultSet = statement
        .executeQuery("SELECT * FROM ROOMTABLE")) {
      while (resultSet.next()) {
        LOGGER.log(Level.INFO, "checking rooms");
        rooms.addAll(
            RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(resultSet.getInt(1))
                .setRoomSeating(resultSet.getInt(2)).setDepartment(resultSet.getString(3)).setLab
                (Boolean.parseBoolean(resultSet.getString(4))).setSharedRoomNumber(resultSet
                .getInt(5)).createRoomProperty());

      }
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "No Rooms where found", e);
    }
    this.newRoomNumber.textProperty().addListener((observableValue, s, s2) -> {
      final Matcher matcher = idPattern.matcher(s2);
      this.newRoomNumber.setTooltip(new Tooltip("Must be a valid Room Number"));
      if (matcher.matches()) {
        this.newRoomNumber.setText(s2);
        this.newRoomNumber.setStyle("-fx-border-color: transparent;");
        this.newClass.setDisable(false);
        try (Stream<RoomProperty> roomPropertyStream = rooms.stream();
            Stream<RoomProperty> filter = roomPropertyStream.filter
                (room ->
                    Integer.parseInt(s2) != room.roomIDProperty().get())) {
          filter.forEach(
              room -> {
                this.newRoomNumber.setStyle(
                    "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
                this.newRoomNumber.setTooltip(new Tooltip("Must be a valid room number"));
                this.newClass.setDisable(true);
              });
        }
      } else if (this.newRoomNumber.textProperty().getValue().isEmpty()) {
        this.newRoomNumber.setStyle("-fx-border-color: transparent");
        this.newClass.setDisable(true);
      } else {
        this.newRoomNumber.setStyle(
            "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
        this.newClass.setDisable(true);
      }
    });
  }

  private void initClassIDTextfield(final Pattern idPattern) {
    this.newClass.setDisable(true);
    this.newClassID.setPromptText("Enter identification number");
    this.newClassID.textProperty().addListener((observableValue, s, s2) -> {
      Matcher matcher = idPattern.matcher(s2);
      try (Stream<ClassProperty> classPropertyStream = this.classesList.stream()) {

        boolean matches = classPropertyStream.anyMatch(
            classProperty -> Integer.parseInt(s2) == classProperty.classIDProperty().get());
        if (matcher.matches()) {
          this.newClassID.setText(s2);
          this.newClassID.setStyle("-fx-border-color: transparent;");
          this.newClassID.setTooltip(new Tooltip("Must be Numeric Characters only"));
          this.newClass.setDisable(false);
          if (matches) {
            this.newClassID.setStyle(
                "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
            this.newClassID.setTooltip(new Tooltip("This ID is already assigned"));
            this.newClass.setDisable(true);
          }
        } else if (this.newClassID.getText().isEmpty()) {
          this.newClassID.setStyle("-fx-border-color: transparent");
          this.newClassID.setTooltip(new Tooltip("Must be Numeric Characters only"));
        } else {
          this.newClassID.setStyle(
              "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
          this.newClassID.setTooltip(new Tooltip("Must be Numeric Characters only"));
          this.newClass.setDisable(true);
        }
      }
    });
  }

  private void initClassTable() {
    try (Connection connection = DatabaseConnection.getInstance(); Statement statement = connection
        .createStatement(); ResultSet resultSet = statement
        .executeQuery(
            "SELECT " +
                "* FROM CLASSTABLE")) {
      this.classesList = FXCollections.observableArrayList(
          //          UserPropertyBuilder.createUserPropertyTestBuilder().setUserID(10)
          // .setUserFirstName(
          //              "test first")
          //              .setUserLastName("test last").setUserPassword("test pass").setCourseID
          // (1).setUserType
          //              (UserType.PART_TIME.getType()).createUserPropertyTest()
      );
      while (resultSet.next()) {
        this.classesList.add(ClassPropertyBuilder.createClassPropertyBuilder().setClassID
            (resultSet.getInt(1)).setDay(resultSet.getString(2)).setTimeSlot(resultSet.getDouble
            (3)).setRoomNumber(resultSet.getInt(4)).setModuleID(
            resultSet.getInt(5)).createClassProperty());
      }
      this.classIDField.setCellValueFactory(
          cellData -> cellData.getValue().classIDProperty().asObject());
      this.dayField.setCellValueFactory(
          cellData -> cellData.getValue().dayProperty());
      this.timeSlotField.setCellValueFactory(
          cellData -> cellData.getValue().timeSlotProperty().asObject());
      this.roomNumberField.setCellValueFactory(
          cellData -> cellData.getValue().roomNumberProperty().asObject());
      this.moduleIDField.setCellValueFactory(
          cellData -> cellData.getValue().roomNumberProperty().asObject());
      this.classTableView.setItems(this.classesList);
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to execute query", e);
    }
  }

  //todo add day and time checking and double booking prevention
  public void addNewClass(final ActionEvent actionEvent) {
    final int setID;
    if (this.newClassID.getText().isEmpty()) {
      int nextID = 0;
      for (final ClassProperty classProperty : this.classesList) {
        if (nextID <= classProperty.classIDProperty().get()) {
          nextID = classProperty.classIDProperty().get() + 1;
        }
        LOGGER.log(Level.INFO, String.valueOf(nextID));
      }
      setID = nextID;
    } else {
      setID = Integer.parseInt(
          this.newClassID.getText());
    }
    if (this.newRoomNumber.textProperty().getValue().isEmpty()) {
      this.newRoomNumber.setStyle(
          "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
      LOGGER.log(Level.SEVERE, "Fields cannot not be empty");
    } else if (this.newRoomNumber
        .textProperty
            ().getValue().isEmpty()) {
      this.newRoomNumber.setStyle(
          "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
      LOGGER.log(Level.SEVERE, "Fields cannot not be empty");
    } else {
      final ClassProperty newClass = ClassPropertyBuilder.createClassPropertyBuilder().setClassID
          (setID)
          .setDay(
              String.valueOf(this.newClassDay.getValue()))
          .setTimeSlot(Double.parseDouble
              (String.valueOf(this.newClassTimeSlot
                  .getSelectionModel().getSelectedItem())))
          .setRoomNumber(Integer.parseInt(this
              .newRoomNumber.getText())).setModuleID(Integer.parseInt(this.newModuleID.getText()))
          .createClassProperty();
      newClass.addToDB();
      this.classesList.addAll(newClass);
      this.clearFields();
    }
  }

  public void clearFields() {
    this.newClassDay.getSelectionModel().clearAndSelect(0);
    this.newClassTimeSlot.getSelectionModel().clearAndSelect(0);
    this.newModuleID.clear();
    this.newRoomNumber.clear();
    this.newClassID.clear();
  }

  public void updateClass(final ActionEvent actionEvent) {
  }

  public void removeClass(final ActionEvent actionEvent) {
    final ClassProperty selectedClassProperty = selectClassProperty();
    if (selectedClassProperty.classIDProperty().get() != 0) {
      selectedClassProperty.removeFromDB();
      this.classesList.remove(selectedClassProperty);
    }
  }

  private ClassProperty selectClassProperty() {
    return this.classTableView.getSelectionModel().getSelectedItem();
  }
}
