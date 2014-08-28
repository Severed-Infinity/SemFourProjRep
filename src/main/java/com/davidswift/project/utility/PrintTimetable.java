package com.davidswift.project.utility;

import com.davidswift.project.model.*;
import com.davidswift.project.model.ClassProperty.*;

import java.nio.file.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 8/28/2014.
 */
public final class PrintTimetable {
  private static final Logger LOGGER = Logger.getLogger(PrintTimetable.class.getName());


  private PrintTimetable(final int courseID) {
    super();
    //todo text file creation from course query

    final ArrayList timetables = gatherTimeSlots(courseID);
    timetables.forEach(timetable -> LOGGER.log(Level.INFO, timetable.toString()));
    final String courseName = "test";
    final Path output = Paths.get("/timetables/"+ courseName+".txt");
      //todo create file path

  }

  private ArrayList gatherTimeSlots(final int courseID) {
    //TODO query database such that we get time slots that match modules that belong to the course
    // given
    final ArrayList<ClassProperty> timetableResult = new ArrayList<>(50);
    final String queryClassTable = "SELECT\n" +
        "  *\n" +
        "FROM classtable\n" +
        "  INNER JOIN moduletable\n" +
        "    ON CLASSTABLE.MODULE_ID = MODULETABLE.MODULE_ID\n" +
        "WHERE MODULETABLE.COURSE_ID = " + courseID;
    try (Connection connection = DatabaseConnection.getInstance();
        PreparedStatement preparedStatement = connection.prepareStatement(queryClassTable);
        ResultSet resultSet = preparedStatement.executeQuery()) {
      while (resultSet.next()) {
        timetableResult.add(ClassPropertyBuilder.createClassPropertyBuilder().setClassID
            (resultSet.getInt(1)).setDay(resultSet.getString(2)).setTimeSlot(resultSet.getDouble
            (3)).setRoomNumber(resultSet.getInt(4)).setModuleID(
            resultSet.getInt(5)).createClassProperty());
      }

    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "No classes found for the given course", e);
    }

    return timetableResult;
  }

  public static PrintTimetable createPrintTimetable(final int courseID) {
    return new PrintTimetable(courseID);
  }
}
