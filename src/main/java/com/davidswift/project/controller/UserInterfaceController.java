package com.davidswift.project.controller;

import com.davidswift.project.data.*;
import com.davidswift.project.data.UserProperty.*;
import com.davidswift.project.references.*;
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
 * Created by david on 7/25/2014.
 */
public class UserInterfaceController implements Initializable {
  private static final Logger LOGGER = Logger.getLogger(UserInterfaceController.class.getName());
  @FXML
  public TableView<UserProperty> userTableView;
  @FXML
  public TableColumn<UserProperty, Integer> userIDField;
  @FXML
  public TableColumn<UserProperty, String> userFirstNameField;
  @FXML
  public TableColumn<UserProperty, String> userLastNameField;
  @FXML
  public TableColumn<UserProperty, String> userPasswordField;
  @FXML
  public TableColumn<UserProperty, Integer> userCourseIDField;
  @FXML
  public TableColumn<UserProperty, String> userTypeField;
  @FXML
  public TextField newUserID;
  @FXML
  public TextField newUserFirstName;
  @FXML
  public TextField newUserLastName;
  @FXML
  public TextField newUserPassword;
  @FXML
  public TextField newUserCourseID;
  public ChoiceBox newUserType;
  public Button newUser;
  public Button deleteUser;
  private ObservableList<UserProperty> usersList;

  @Override
  public void initialize(final URL url, final ResourceBundle resourceBundle) {
    initUserTable();
    initUserFields();

  }

  private void initUserTable() {
    try (Connection connection = DatabaseConnection.getInstance(); Statement statement = connection
        .createStatement(); ResultSet resultSet = statement
        .executeQuery(
            "SELECT " +
                "* FROM USERTABLE")) {
      this.usersList = FXCollections.observableArrayList(
          //          UserPropertyBuilder.createUserPropertyTestBuilder().setUserID(10)
          // .setUserFirstName(
          //              "test first")
          //              .setUserLastName("test last").setUserPassword("test pass").setCourseID
          // (1).setUserType
          //              (UserType.PART_TIME.getType()).createUserPropertyTest()
      );
      while (resultSet.next()) {
        this.usersList.add(UserPropertyBuilder.createUserPropertyBuilder().setUserID
            (resultSet.getInt(1))
            .setUserFirstName
                (resultSet.getString(2))
            .setUserLastName(resultSet.getString(3)).setUserPassword(
                resultSet.getString(4))
            .setCourseID(resultSet.getInt(5))
            .setUserType(resultSet.getString(6))
            .createUserPropertyTest());
      }
      this.userIDField.setCellValueFactory(
          cellData -> cellData.getValue().userIDProperty().asObject());
      this.userFirstNameField.setCellValueFactory(
          cellData -> cellData.getValue().userFirstNameProperty());
      this.userLastNameField.setCellValueFactory(
          cellData -> cellData.getValue().userLastNameProperty());
      this.userPasswordField.setCellValueFactory(
          cellData -> cellData.getValue().userPasswordProperty());
      this.userCourseIDField.setCellValueFactory(
          cellData -> cellData.getValue().courseIDProperty().asObject());
      this.userTypeField.setCellValueFactory(cellData -> cellData.getValue().userTypeProperty());
      this.userTableView.setItems(this.usersList);
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to execute query", e);
    }
    this.userTableView.setOnMouseClicked(mouseEvent -> {
      final UserProperty selectedUserProperty = UserInterfaceController.this.userTableView
          .getSelectionModel()
          .getSelectedItem();
      if (selectedUserProperty != null) {
        UserInterfaceController.this.newUserID.setText(String.valueOf(selectedUserProperty
            .userIDProperty().get()));
        UserInterfaceController.this.newUserFirstName.setText(selectedUserProperty
            .userFirstNameProperty().get());
        UserInterfaceController.this.newUserLastName.setText(selectedUserProperty
            .userLastNameProperty().get());
        UserInterfaceController.this.newUserPassword.setText(selectedUserProperty
            .userPasswordProperty().get());
        UserInterfaceController.this.newUserCourseID.setText(
            String.valueOf(selectedUserProperty.courseIDProperty().get()));
        UserInterfaceController.this.newUserType.getSelectionModel().select(
            selectedUserProperty.userTypeProperty().get().equals
                (UserType.ADMIN.getType()) ? 0 : selectedUserProperty.userTypeProperty().get()
                .equals
                    (UserType.FULL_TIME.getType()) ? 1 : 2);
      }
    });

  }

  public void initUserFields() {
    this.newUser.setDisable(true);
    final Pattern namePattern = Pattern.compile("[A-Z][a-z]+");
    final Pattern passwordPattern = Pattern.compile("[a-z 0-9]+");
    final Pattern idPattern = Pattern.compile("[0-9]|[0-9]+");
    initFirstNameTextfield(namePattern);
    initLastNameTextfield(namePattern);
    initPasswordTextfield(passwordPattern);
    initUserIDTextfield(idPattern);
    initCourseIDTextfield(idPattern);
    this.newUserType.setTooltip(new Tooltip("Select user type"));
    this.newUserType.getSelectionModel().select(1);

  }

  private void initUserIDTextfield(final Pattern namePattern) {
    this.newUserID.setPromptText("Enter identification number");
    this.newUserID.textProperty().addListener((observableValue, s, s2) -> {
      Matcher matcher = namePattern.matcher(s2);
      boolean matches = this.usersList.stream().anyMatch(user -> Integer.parseInt(s2) == user
          .userIDProperty().get());
      if (matcher.matches()) {
        this.newUserID.setText(s2);
        this.newUserID.setStyle("-fx-border-color: transparent;");
        this.newUserID.setTooltip(new Tooltip("Must be Numeric Characters only"));
        this.newUser.setDisable(false);
        if (matches) {
          this.newUserID.setStyle(
              "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
          this.newUserID.setTooltip(new Tooltip("This ID is already assigned"));
          this.newUser.setDisable(true);
        }
      } else if (this.newUserID.getText().isEmpty()) {
        this.newUserID.setStyle("-fx-border-color: transparent");
        this.newUserID.setTooltip(new Tooltip("Must be Numeric Characters only"));
      } else {
        this.newUserID.setStyle(
            "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
        this.newUserID.setTooltip(new Tooltip("Must be Numeric Characters only"));
        this.newUser.setDisable(true);
      }
    });
  }

  private void initCourseIDTextfield(final Pattern namePattern) {
    this.newUserCourseID.setPromptText("Enter Course identification number");
    final ObservableList<Course> courses = FXCollections.observableArrayList();
    try (Connection connection = DatabaseConnection.getInstance(); Statement statement = connection
        .createStatement(); ResultSet resultSet = statement
        .executeQuery("SELECT * FROM COURSETABLE")) {
      while (resultSet.next()) {
        courses.addAll(Course.createCourse(resultSet.getInt(1), resultSet.getString(2),
            resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5)));
      }
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "No courses where found", e);
    }
    this.newUserCourseID.textProperty().addListener((observableValue, s, s2) -> {
      final Matcher matcher = namePattern.matcher(s2);
      this.newUserCourseID.setTooltip(new Tooltip("Must be a valid Course ID"));
      if (matcher.matches()) {
        this.newUserCourseID.setText(s2);
        this.newUserCourseID.setStyle("-fx-border-color: transparent;");
        this.newUser.setDisable(false);
        courses.stream().filter(course -> Integer.parseInt(s2) != course.getCourseID()).forEach(
            course -> {
              this.newUserCourseID.setStyle(
                  "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
              this.newUserCourseID.setTooltip(new Tooltip("Must be a valid Course ID"));
              this.newUser.setDisable(true);
            });
      } else if (this.newUserCourseID.textProperty().getValue().isEmpty()) {
        this.newUserCourseID.setStyle("-fx-border-color: transparent");
        this.newUser.setDisable(true);
      } else {
        this.newUserCourseID.setStyle(
            "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
        this.newUser.setDisable(true);
      }
    });
  }

  private void initFirstNameTextfield(final Pattern namePattern) {
    this.newUserFirstName.setPromptText("Enter First Name");
    this.newUserFirstName.textProperty().addListener((observableValue, s, s2) -> {
      Matcher matcher = namePattern.matcher(s2);
      if (matcher.matches()) {
        this.newUserFirstName.setText(s2);
        this.newUserFirstName.setStyle("-fx-border-color: transparent;");
        this.newUser.setDisable(false);

      } else if (this.newUserFirstName.textProperty().getValue().isEmpty()) {
        this.newUserFirstName.setStyle("-fx-border-color: transparent");
        this.newUser.setDisable(true);
      } else {
        this.newUserFirstName.setStyle(
            "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
        this.newUserFirstName.setTooltip(new Tooltip("Must begin with a Capital \nCan only have " +
            "one Capital" +
            " " +
            "\nMust be longer " +
            "than one character"));
        this.newUser.setDisable(true);
      }
    });
  }

  private void initLastNameTextfield(final Pattern namePattern) {
    this.newUserLastName.setPromptText("Enter Last Name");
    this.newUserLastName.textProperty().addListener((observableValue, s, s2) -> {
      Matcher matcher = namePattern.matcher(s2);
      if (matcher.matches()) {
        this.newUserLastName.setText(s2);
        this.newUserLastName.setStyle("-fx-border-color: transparent;");
        this.newUser.setDisable(false);

      } else if (this.newUserLastName.textProperty().getValue().isEmpty()) {
        this.newUserLastName.setStyle("-fx-border-color: transparent");
        this.newUser.setDisable(true);
      } else {
        this.newUserLastName.setStyle(
            "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
        this.newUserLastName.setTooltip(new Tooltip("Must begin with a Capital \nCan only have " +
            "one Capital" +
            " " +
            "\nMust be longer " +
            "than one character"));
        this.newUser.setDisable(true);
      }
    });
  }

  private void initPasswordTextfield(final Pattern passwordPattern) {
    this.newUserPassword.setPromptText("Enter Password");
    this.newUserPassword.textProperty().addListener((observableValue, s, s2) -> {
      Matcher matcherNew = passwordPattern.matcher(s2);
      if (matcherNew.matches()) {
        this.newUserPassword.setText(s2);
        this.newUserPassword.setStyle("-fx-border-color: transparent;");
        this.newUser.setDisable(false);

      } else if (this.newUserPassword.textProperty().getValue().isEmpty()) {
        this.newUserPassword.setStyle("-fx-border-color: transparent");
        this.newUser.setDisable(true);
      } else {
        this.newUserPassword.setStyle(
            "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
        this.newUserPassword.setTooltip(new Tooltip("Must be lowercase" +
            "\nMust be longer " +
            "than one character"));
        this.newUser.setDisable(true);
      }
    });
  }

  public void addNewUser(final ActionEvent actionEvent) {
    final int setID;
    if (this.newUserID.getText().isEmpty()) {
      int nextID = 0;
      for (final UserProperty userProperty : this.usersList) {
        if (nextID <= userProperty.userIDProperty().get()) {
          nextID = userProperty.userIDProperty().get() + 1;
        }
        LOGGER.log(Level.INFO, String.valueOf(nextID));
      }
      setID = nextID;
    } else {
      setID = Integer.parseInt(
          this.newUserID.getText());
    }
    if (this.newUserFirstName.textProperty().getValue().isEmpty()) {
      this.newUserFirstName.setStyle(
          "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
      LOGGER.log(Level.SEVERE, "Fields cannot not be empty");
    } else if (this.newUserLastName
        .textProperty
            ().getValue().isEmpty()) {
      this.newUserLastName.setStyle(
          "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
      LOGGER.log(Level.SEVERE, "Fields cannot not be empty");
    } else if (this.newUserPassword.textProperty().getValue().isEmpty
        ()) {
      this.newUserPassword.setStyle(
          "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
      LOGGER.log(Level.SEVERE, "Fields cannot not be empty");
    } else if (this.newUserCourseID.textProperty().getValue().isEmpty()) {
      this.newUserCourseID.setStyle(
          "-fx-border-color: #f00; -fx-border-radius: 3; -fx-border-width: 2;");
      LOGGER.log(Level.SEVERE, "Fields cannot not be empty");
    } else {
      final UserProperty newUser = UserPropertyBuilder.createUserPropertyBuilder().setUserID(
          setID
      ).setUserFirstName(this.newUserFirstName.getText())
          .setUserLastName(this.newUserLastName.getText()).setUserPassword(
              this.newUserPassword.getText())
          .setCourseID(Integer.parseInt(this.newUserCourseID.getText()))
          .setUserType(String.valueOf(this.newUserType.getValue()))
          .createUserPropertyTest();
      newUser.addToDB();
      this.usersList.addAll(newUser);
      this.newUserID.clear();
      this.newUserFirstName.clear();
      this.newUserLastName.clear();
      this.newUserPassword.clear();
      this.newUserCourseID.clear();
      this.newUserType.getSelectionModel().select(1);
    }

  }

  public void removeUser(final ActionEvent actionEvent) {
    //    final int selectedIndex = this.userTableView.getSelectionModel().getSelectedIndex();
    final UserProperty selectedUserProperty = selectUserProperty();
    if (selectedUserProperty.userIDProperty().get() != 0) {
      selectedUserProperty.removeFromDB();
      this.usersList.remove(selectedUserProperty);
      LOGGER.log(Level.INFO, this.usersList.toString());
      LOGGER.log(Level.INFO, this.userTableView.getItems().toString());
      //    this.userTableView.getItems().remove(selectedUserProperty);
      //    this.userTableView.getItems().remove(selectedIndex);
    }

  }

  private UserProperty selectUserProperty() {
    return this.userTableView.getSelectionModel()
        .getSelectedItem();
  }
}

