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
public class RoomInterfaceController implements Initializable{
  public TableView roomTableView;
  public TableColumn roomNumberField;
  public TableColumn roomSeatingField;
  public TableColumn roomDepartmentField;
  public TableColumn isLabField;
  public TextField newRoomNumber;
  public TextField newRoomSeating;
  public TextField newDepartment;
  public TextField newRoomIsLab;
  public Button newRoom;
  public Button updateRoom;
  public Button deleteRoom;
  public Button clearFields;

  @Override
  public void initialize(final URL url, final ResourceBundle resourceBundle) {
  }

  public void addNewRoom(final ActionEvent actionEvent) {

  }

  public void updateRoom(final ActionEvent actionEvent) {

  }

  public void removeRoom(final ActionEvent actionEvent) {

  }

  public void clearFields(final ActionEvent actionEvent) {

  }
}
