/*
 * Copyright (c) 2011, 2014 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.davidswift.project.controller;

import com.davidswift.project.model.*;
import com.davidswift.project.model.ClassProperty.*;
import com.davidswift.project.model.CourseProperty.*;
import com.davidswift.project.model.ModuleProperty.*;
import com.davidswift.project.model.UserProperty.*;
import com.davidswift.project.references.*;
import com.davidswift.project.utility.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.time.*;
import java.util.*;
import java.util.logging.*;

public class LoginInterfaceController implements Initializable {
  private static final Logger LOGGER = Logger.getLogger(LoginInterfaceController.class.getName());
  private static Stage prevStage;
  @FXML
  public Button login;
  @FXML
  private PasswordField passwordFieldInput;
  @FXML
  private RadioButton local;
  @FXML
  private RadioButton college;
  @FXML
  private TextField idFieldInput;
  @FXML
  private CheckBox buildDatabase;
  @FXML
  private Text actiontarget;

  @FXML
  protected synchronized void handleSubmitButtonAction(final ActionEvent event) {
    loginProcess();
  }

  private void loginProcess() {
    locationCheck();
    if (this.buildDatabase.isSelected()) {
      BuildDatabase.createBuildDatabase();
      try {

        final CourseProperty defaultCourse = CoursePropertyBuilder.createCoursePropertyBuilder()
            .setCourseID(0).setCourseName("Admin").setCourseHead("Admin").setCourseLength(0)
            .setDepartment("admin").createCourseProperty();
        defaultCourse.addToDB();
        final UserProperty defaultAdmin = UserPropertyBuilder.createUserPropertyBuilder()
            .setUserID(0)
            .setUserFirstName("Admin").setUserLastName("Admin").setUserPassword("admin")
            .setCourseID(0).setUserType(UserType.ADMIN.getType()).createUserPropertyTest();
        defaultAdmin.addToDB();
        final ModuleProperty defaultModule = ModulePropertyBuilder.createModulePropertyBuilder()
            .setModuleID(0).setModuleName("Admin").setModuleLecturer("Admin").setCourseID(0)
            .createModuleProperty();
        defaultModule.addToDB();
        final ClassProperty defaultClass = ClassPropertyBuilder.createClassPropertyBuilder()
            .setClassID(0).setDay(String.valueOf(DayOfWeek.SUNDAY)).setTimeSlot(12.00)
            .setRoomNumber(1).setModuleID(0).createClassProperty();
        defaultClass.addToDB();
        loadMain();
      } catch (final IOException e) {
        LOGGER.log(Level.SEVERE, "Could not load main screen", e);
      }
    } else {
      try (Connection connection = DatabaseConnection.getInstance(); PreparedStatement
          preparedStatement = connection
          .prepareStatement("SELECT * FROM USERTABLE")
      ) {
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
          if (resultSet.getInt(1) ==
              Integer.parseInt(this.idFieldInput.getText()) && resultSet.getString(4).equals(this
              .passwordFieldInput
              .getText()) && resultSet.getString(6).equals(UserType.ADMIN.getType())) {
            LOGGER.log(Level.INFO, "logged in: " + resultSet.getInt(1) + ": " + resultSet.getString
                (2));
            this.actiontarget.setText("logging in");
            loadMain();
            break;
          }

        }
        if (resultSet.isAfterLast()) {
          this.actiontarget.setText("Invalid login");
        }
      } catch (final SQLException e) {
        LOGGER.log(Level.SEVERE, "Login is invalid", e);
        this.actiontarget.setText("Invalid login");
      } catch (final IOException e) {
        LOGGER.log(Level.SEVERE, "Could not load main screen", e);
      }
    }
  }

  protected void loadMain() throws IOException {
    final Stage stage = new Stage();
    stage.setTitle("Database Management");
    final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource
        ("/assets/project/fxml/MainInterface.fxml"));
    final Parent root = fxmlLoader.load();
    //    final Pane pane = FXMLLoader.load(getClass().getResource
    // ("/assets/project/fxml/MainInterface" +
    //        ".fxml"));
    final Scene scene = new Scene(root);
    stage.setScene(scene);
    prevStage.close();
    MainInterfaceController.setPrevStage(stage);
    stage.show();
  }

  protected void locationCheck() {
    if (this.local.isSelected()) {
      DatabaseConnection.setDblocation(DBLocation.LOCAL);

    } else if (this.college.isSelected()) {
      DatabaseConnection.setDblocation(DBLocation.COLLEGE);

    }
  }

  @Override
  public void initialize(final URL url, final ResourceBundle resourceBundle) {
    final ToggleGroup toggleGroup = new ToggleGroup();
    this.local.setToggleGroup(toggleGroup);
    this.college.setToggleGroup(toggleGroup);
    this.local.setSelected(true);
    this.login.setOnKeyPressed(keyEvent -> {
      if (keyEvent.getCode() == KeyCode.ENTER) {
        loginProcess();
      }
    });
  }

  public Stage getPrevStage() {
    return prevStage;
  }

  public static void setPrevStage(final Stage stage) {
    prevStage = stage;
  }

}

