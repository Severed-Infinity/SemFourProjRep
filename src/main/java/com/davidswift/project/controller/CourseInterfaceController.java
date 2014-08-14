package com.davidswift.project.controller;

import com.davidswift.project.model.*;
import com.davidswift.project.model.CourseProperty.*;
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

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 7/27/2014.
 */
public class CourseInterfaceController implements Initializable {
  public static final Logger LOGGER = Logger.getLogger(CourseInterfaceController.class.getName());
  @FXML
  public TableView<CourseProperty> courseTableView;
  @FXML
  public TableColumn<CourseProperty, Integer> courseIDField;
  @FXML
  public TableColumn<CourseProperty, String> courseNameField;
  @FXML
  public TableColumn<CourseProperty, Integer> courseLengthField;
  @FXML
  public TableColumn<CourseProperty, String> courseDepartmentField;
  @FXML
  public TableColumn<CourseProperty, String> courseHeadField;
  @FXML
  public TextField newCourseID;
  @FXML
  public TextField newCourseName;
  @FXML
  public TextField newCourseLength;
  @FXML
  public TextField newCourseDepartment;
  @FXML
  public TextField newCourseHead;
  @FXML
  public Button newCourse;
  @FXML
  public Button updateCourse;
  @FXML
  public Button deleteCourse;
  @FXML
  public Button clearFields;
  private ObservableList<CourseProperty> coursesList;

  @Override
  public void initialize(final URL url, final ResourceBundle resourceBundle) {
    initCourseTable();
    initCourseFields();
  }

  private void initCourseTable() {
    try (Connection connection = DatabaseConnection.getInstance(); Statement statement = connection
        .createStatement(); ResultSet resultSet = statement
        .executeQuery(
            "SELECT " +
                "* FROM COURSETABLE")) {
      this.coursesList = FXCollections.observableArrayList(
          CoursePropertyBuilder.createCoursePropertyBuilder().setCourseID
              (10).setCourseName("testcourse").setCourseLength(4).setDepartment("testdepartment")
              .setCourseHead("testcoursehead").createCourseProperty());
      while (resultSet.next()) {
        this.coursesList.add(CoursePropertyBuilder.createCoursePropertyBuilder().setCourseID
            (resultSet.getInt(1)).setCourseName(resultSet.getString(2)).setCourseHead(
            resultSet.getString
                (3)).setCourseLength(resultSet
            .getInt(4)).setDepartment(resultSet.getString(5)).createCourseProperty());
      }
      this.courseIDField.setCellValueFactory(
          cellData -> cellData.getValue().courseIDProperty().asObject());
      this.courseNameField.setCellValueFactory(
          cellData -> cellData.getValue().courseNameProperty());
      this.courseLengthField.setCellValueFactory(
          cellData -> cellData.getValue().courseLengthProperty().asObject());
      this.courseDepartmentField.setCellValueFactory(
          cellData -> cellData.getValue().departmentProperty());
      this.courseHeadField.setCellValueFactory(
          cellData -> cellData.getValue().courseHeadProperty());
      this.courseTableView.setItems(this.coursesList);
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to execute query", e);
    }
  }

  protected void initCourseFields() {
    this.newCourse.setDisable(true);
    final Pattern namePattern = Pattern.compile("[A-Z][a-z]+");
    final Pattern idPattern = Pattern.compile("[0-9]|[0-9]+");
    final Pattern fullNamePattern = Pattern.compile("[A-Z][a-z]+\\s+[A-Z][a-z]+");
    final Pattern yearsPattern = Pattern.compile("0-4");
    initCourseNameTextfield(namePattern);
    initCourseHeadTextfield(fullNamePattern);
    initCourseIDTextfield(idPattern);
    initCourseLengthTextfield(yearsPattern);
    initDepartmentTextfield(fullNamePattern);
  }

  private void initDepartmentTextfield(final Pattern fullNamePattern) {

  }

  private void initCourseLengthTextfield(final Pattern yearsPattern) {

  }

  private void initCourseIDTextfield(final Pattern idPattern) {
    this.newCourseID.setPromptText("Enter identification number");
    this.newCourseID.textProperty().addListener((observableValue, s, s2) -> {
      Matcher matcher = idPattern.matcher(s2);
      boolean matches = this.coursesList.stream().anyMatch(course -> Integer.parseInt(s2) == course
          .courseIDProperty().get());
      if (matcher.matches()) {
        this.newCourseID.setText(s2);
        this.newCourseID.setStyle("-fx-border-color: transparent;");
        this.newCourseID.setTooltip(new Tooltip("Must be Numeric Characters only"));
        this.newCourse.setDisable(false);
        if (matches) {
          this.newCourseID.setStyle(
              "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
          this.newCourseID.setTooltip(new Tooltip("This ID is already assigned"));
          this.newCourse.setDisable(true);
        }
      } else if (this.newCourseID.getText().isEmpty()) {
        this.newCourseID.setStyle("-fx-border-color: transparent");
        this.newCourseID.setTooltip(new Tooltip("Must be Numeric Characters only"));
      } else {
        this.newCourseID.setStyle(
            "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
        this.newCourseID.setTooltip(new Tooltip("Must be Numeric Characters only"));
        this.newCourse.setDisable(true);
      }
    });
  }

  private void initCourseHeadTextfield(final Pattern personNamePattern) {
    this.newCourseHead.setPromptText("Enter Course Head");
    this.newCourseHead.textProperty().addListener((observableValue, s, s2) -> {
      Matcher matcher = personNamePattern.matcher(s2);
      if (matcher.matches()) {
        this.newCourseHead.setText(s2);
        this.newCourseHead.setStyle("-fx-border-color: transparent;");
        this.newCourse.setDisable(false);

      } else if (this.newCourseHead.textProperty().getValue().isEmpty()) {
        this.newCourseHead.setStyle("-fx-border-color: transparent");
        this.newCourse.setDisable(true);
      } else {
        this.newCourseHead.setStyle(
            "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
        this.newCourseHead.setTooltip(new Tooltip("Must be a full name" + "\nMust contain a space" +
            " " +
            "after first name"+
            "\nMust be longer " +
            "than one character"));
        this.newCourse.setDisable(true);
      }
    });
  }

  private void initCourseNameTextfield(final Pattern namePattern) {
    this.newCourseName.setPromptText("Enter Course Name");
    this.newCourseName.textProperty().addListener((observableValue, s, s2) -> {
      Matcher matcher = namePattern.matcher(s2);
      if (matcher.matches()) {
        this.newCourseName.setText(s2);
        this.newCourseName.setStyle("-fx-border-color: transparent;");
        this.newCourse.setDisable(false);

      } else if (this.newCourseName.textProperty().getValue().isEmpty()) {
        this.newCourseName.setStyle("-fx-border-color: transparent");
        this.newCourse.setDisable(true);
      } else {
        this.newCourseName.setStyle(
            "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
        this.newCourseName.setTooltip(new Tooltip("Must begin with a Capital \nCan only have " +
            "one Capital" +
            " " +
            "\nMust be longer " +
            "than one character"));
        this.newCourse.setDisable(true);
      }
    });
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
