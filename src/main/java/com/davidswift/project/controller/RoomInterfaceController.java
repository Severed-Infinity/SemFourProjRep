package com.davidswift.project.controller;

import com.davidswift.project.model.*;
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
 * Created by david on 8/14/2014.
 */
public class RoomInterfaceController implements Initializable {
  private static final Logger LOGGER = Logger.getLogger(RoomInterfaceController.class.getName());
  public TableView<RoomProperty> roomTableView;
  public TableColumn<RoomProperty, Integer> roomNumberField;
  public TableColumn<RoomProperty, Integer> roomSeatingField;
  public TableColumn<RoomProperty, String> roomDepartmentField;
  public TableColumn<RoomProperty, Boolean> isLabField;
  public TextField newRoomNumber;
  public TextField newRoomSeating;
  public TextField newDepartment;
  public ChoiceBox newRoomIsLab;
  public Button newRoom;
  public Button clearFields;
  public TableColumn<RoomProperty, Integer> sharedRoomNumberField;
  public TextField newSharedRoomNumber;
  private ObservableList<RoomProperty> roomsList;

  @Override
  public void initialize(final URL url, final ResourceBundle resourceBundle) {
    initRoomTable();
    initRoomFields();
  }

  private void initRoomFields() {
    this.newRoom.setDisable(true);
    final Pattern idPattern = Pattern.compile("[0-9]|[0-9]+");
    final Pattern seatingPattern = Pattern.compile("[1-9]|[0-9]+");
    final Pattern fullNamePattern = Pattern.compile("[A-Z][a-z]+\\s+[A-Z][a-z]+");
    initRoomNumberTextfield(idPattern);
    initRoomSeatingTextfield(seatingPattern);
    initDepartmentTextfield(fullNamePattern);
    initSharedRoomNumberTextfield(idPattern);
    this.newRoomIsLab.setTooltip(new Tooltip("Is this room a lab"));
    this.newRoomIsLab.getSelectionModel().select(1);
  }

  private void initSharedRoomNumberTextfield(final Pattern idPattern) {
    this.newSharedRoomNumber.setPromptText("Enter shared room number");
    this.newSharedRoomNumber.textProperty().addListener((observableValue, s, s2) -> {
      Matcher matcher = idPattern.matcher(s2);
      try (Stream<RoomProperty> roomPropertyStream = this.roomsList.stream()) {
        boolean matchesNone = !roomPropertyStream.anyMatch(room -> Integer.parseInt(s2) == room
            .roomIDProperty().get());
        if (matcher.matches()) {
          this.newSharedRoomNumber.setText(s2);
          this.newSharedRoomNumber.setStyle("-fx-border-color: transparent;");
          this.newSharedRoomNumber.setTooltip(new Tooltip("Must be Numeric Characters only"));
          this.newRoom.setDisable(false);
          if (matchesNone) {
            this.newSharedRoomNumber.setStyle(
                "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
            this.newSharedRoomNumber.setTooltip(new Tooltip("This room does not exist"));
            this.newRoom.setDisable(true);
          }
        } else if (this.newSharedRoomNumber.getText().isEmpty()) {
          this.newSharedRoomNumber.setStyle("-fx-border-color: transparent");
          this.newRoom.setDisable(false);
        } else {
          this.newSharedRoomNumber.setStyle(
              "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
          this.newSharedRoomNumber.setTooltip(new Tooltip("Must be Numeric Characters only"));
          this.newRoom.setDisable(true);
        }
      }
    });
  }

  private void initRoomSeatingTextfield(final Pattern seatingPattern) {
    this.newRoomSeating.setPromptText("Enter room seating");
    this.newRoomSeating.textProperty().addListener((observableValue, s, s2) -> {
      Matcher matcher = seatingPattern.matcher(s2);
      if (matcher.matches()) {
        this.newRoomSeating.setText(s2);
        this.newRoomSeating.setStyle("-fx-border-color: transparent;");
        this.newRoomSeating.setTooltip(new Tooltip("Must be Numeric Characters only"));
        this.newRoom.setDisable(false);

      } else if (this.newRoomSeating.getText().isEmpty()) {
        this.newRoomSeating.setStyle("-fx-border-color: transparent");
        this.newRoomSeating.setTooltip(new Tooltip("Must be Numeric Characters only"));
      } else {
        this.newRoomSeating.setStyle(
            "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
        this.newRoomSeating.setTooltip(new Tooltip("Must be Numeric Characters only\nMust be " +
            "greater than 0"));
        this.newRoom.setDisable(true);

      }
    });
  }

  private void initRoomNumberTextfield(final Pattern idPattern) {
    this.newRoomNumber.setPromptText("Enter room number");
    this.newRoomNumber.textProperty().addListener((observableValue, s, s2) -> {
      Matcher matcher = idPattern.matcher(s2);
      try (Stream<RoomProperty> roomPropertyStream = this.roomsList.stream()) {
        boolean matches = roomPropertyStream.anyMatch(room -> Integer.parseInt(s2) == room
            .roomIDProperty().get());
        if (matcher.matches()) {
          this.newRoomNumber.setText(s2);
          this.newRoomNumber.setStyle("-fx-border-color: transparent;");
          this.newRoomNumber.setTooltip(new Tooltip("Must be Numeric Characters only"));
          this.newRoom.setDisable(false);
          if (matches) {
            this.newRoomNumber.setStyle(
                "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
            this.newRoomNumber.setTooltip(new Tooltip("This room already exists"));
            this.newRoom.setDisable(true);
          }
        } else if (this.newRoomNumber.getText().isEmpty()) {
          this.newRoomNumber.setStyle("-fx-border-color: transparent");
          this.newRoomNumber.setTooltip(new Tooltip("Must be Numeric Characters only"));
        } else {
          this.newRoomNumber.setStyle(
              "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
          this.newRoomNumber.setTooltip(new Tooltip("Must be Numeric Characters only"));
          this.newRoom.setDisable(true);
        }
      }
    });
  }

  private void initDepartmentTextfield(final Pattern fullNamePattern) {
    this.newDepartment.setPromptText("Enter Department Name");
    this.newDepartment.textProperty().addListener((observableValue, s, s2) -> {
      Matcher matcher = fullNamePattern.matcher(s2);
      if (matcher.matches()) {
        this.newDepartment.setText(s2);
        this.newDepartment.setStyle("-fx-border-color: transparent;");
        this.newRoom.setDisable(false);

      } else if (this.newDepartment.textProperty().getValue().isEmpty()) {
        this.newDepartment.setStyle("-fx-border-color: transparent;");
        this.newRoom.setDisable(false);
      } else {
        this.newDepartment.setStyle(
            "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
        this.newDepartment.setTooltip(new Tooltip("Must begin with a Capital \nMust be a " +
            "full named department" +
            "\nMust be longer " +
            "than one character"));
        this.newRoom.setDisable(true);
      }
    });
  }

  private void initRoomTable() {
    try (Connection connection = DatabaseConnection.getInstance(); Statement statement = connection
        .createStatement(); ResultSet resultSet = statement
        .executeQuery(
            "SELECT " +
                "* FROM ROOMTABLE")) {
      this.roomsList = FXCollections.observableArrayList(
          //          CoursePropertyBuilder.createCoursePropertyBuilder().setCourseID
          //              (10).setCourseName("testcourse").setCourseLength(4).setDepartment
          // ("testdepartment")
          //              .setCourseHead("testcoursehead").createCourseProperty()
      );
      while (resultSet.next()) {
        this.roomsList.addAll(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(resultSet
            .getInt(1)).setRoomSeating(resultSet.getInt(2)).setDepartment(resultSet.getString(3))
            .setLab(Boolean.parseBoolean(resultSet.getString(4))).setSharedRoomNumber(
                resultSet.getInt(5)).createRoomProperty());
      }
      this.roomNumberField.setCellValueFactory(
          cellData -> cellData.getValue().roomIDProperty().asObject());
      this.roomSeatingField.setCellValueFactory(
          cellData -> cellData.getValue().roomSeatingProperty().asObject());
      this.roomDepartmentField.setCellValueFactory(
          cellData -> cellData.getValue().departmentProperty());
      this.isLabField.setCellValueFactory(
          cellData -> cellData.getValue().labProperty());
      this.sharedRoomNumberField.setCellValueFactory(
          cellData -> cellData.getValue().sharedRoomNumberProperty().asObject());
      this.roomTableView.setItems(this.roomsList);
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to execute query", e);
    }
  }

  public void addNewRoom(final ActionEvent actionEvent) {
    if (this.newRoomNumber.getText().isEmpty()) {
      this.newRoomNumber.setStyle(
          "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
      LOGGER.log(Level.SEVERE, "Fields cannot be empty");
    }
    if (this.newRoomSeating.textProperty().getValue().isEmpty()) {
      this.newRoomSeating.setStyle(
          "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
      LOGGER.log(Level.SEVERE, "Fields cannot not be empty");
    } else {
      final RoomProperty newRoom = RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(
          Integer.parseInt(this
              .newRoomNumber.getText())).setRoomSeating(
          Integer.parseInt(this.newRoomSeating.getText())).setDepartment
          (this.newDepartment.getText()).setLab(this.newRoomIsLab.getSelectionModel()
          .getSelectedIndex() == 1 ? true : false).setSharedRoomNumber(this.newSharedRoomNumber
          .getText().isEmpty() ? Integer.parseInt(this.newRoomNumber.getText()) :
          Integer.parseInt(this.newSharedRoomNumber.getText())).createRoomProperty();
      newRoom.addToDB();
      this.roomsList.addAll(newRoom);
      this.clearFields();

    }
  }

  public void clearFields() {
    this.newRoomNumber.clear();
    this.newRoomSeating.clear();
    this.newRoomIsLab.getSelectionModel().select(2);
    this.newDepartment.clear();
    this.newSharedRoomNumber.clear();
  }
}
