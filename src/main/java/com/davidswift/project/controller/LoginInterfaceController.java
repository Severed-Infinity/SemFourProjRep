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

import com.davidswift.project.data.*;
import com.davidswift.project.data.UserProperty.*;
import com.davidswift.project.references.*;
import com.davidswift.project.utility.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class LoginInterfaceController implements Initializable {
  public static final Logger LOGGER = Logger.getLogger(LoginInterfaceController.class.getName());
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
  private Stage prevStage;
  private ToggleGroup toggleGroup;

  @FXML
  protected synchronized void handleSubmitButtonAction(final ActionEvent event) {
    locationCheck();
    if (this.buildDatabase.isSelected()) {
      BuildDatabase.createBuildDatabase();
      try {
        final Course defaultCourse = Course.createCourse(0, "Admin", "Admin", 0, "admin");
        defaultCourse.addToDB();
        final UserProperty defaultAdmin = UserPropertyBuilder.createUserPropertyBuilder()
            .setUserID(0)
            .setUserFirstName("Admin").setUserLastName("Admin").setUserPassword("admin")
            .setCourseID(0).setUserType(UserType.ADMIN.getType()).createUserPropertyTest();
        defaultAdmin.addToDB();
        loadMain();
      } catch (final IOException e) {
        LOGGER.log(Level.SEVERE, "Could not load main screen", e);
      }
    } else {
      try (Connection connection = DatabaseConnection.getInstance(); Statement statement =
          connection.createStatement(); ResultSet resultSet =
          statement.executeQuery("SELECT * FROM USERTABLE")) {
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

  public void loadMain() throws IOException {
    final Stage stage = new Stage();
    stage.setTitle("Database Management");
    final Pane pane = FXMLLoader.load(getClass().getResource("/assets/project/fxml/Interface" +
        ".fxml"));
    final Scene scene = new Scene(pane);
    stage.setScene(scene);
    this.prevStage.close();
    stage.show();
  }

  public void locationCheck() {
    if (this.local.isSelected()) {
      DatabaseConnection.setDblocation(DBLocation.LOCAL);

    } else if (this.college.isSelected()) {
      DatabaseConnection.setDblocation(DBLocation.COLLEGE);

    }
  }

  @Override
  public void initialize(final URL url, final ResourceBundle resourceBundle) {
    this.toggleGroup = new ToggleGroup();
    this.local.setToggleGroup(this.toggleGroup);
    this.college.setToggleGroup(this.toggleGroup);
    this.local.setSelected(true);
  }

  public Stage getPrevStage() {
    return this.prevStage;
  }

  public void setPrevStage(final Stage prevStage) {
    this.prevStage = prevStage;
  }
}

