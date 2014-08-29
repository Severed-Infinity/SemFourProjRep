package com.davidswift.project.utility;

import com.davidswift.project.model.*;
import com.davidswift.project.model.ClassProperty.*;
import com.davidswift.project.model.ModuleProperty.*;

import java.io.*;
import java.nio.charset.*;
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
  private final TimetableProperty timetable;
  private final Charset charset = Charset.defaultCharset();

  //todo create file
  private PrintTimetable(final int courseID) {
    super();
    this.timetable = getTimetable(courseID);
    if (this.timetable != null) {
      //creating file
      final Path timetableFile = Paths.get("../timetables/" + this.timetable.getCourseName() + "" +
          ".txt");
      try {
        LOGGER.log(Level.INFO, "Creating timetable");
        if (Files.exists(timetableFile)) {
          LOGGER.log(Level.INFO, "Deleting old timetable");
          Files.delete(timetableFile);
        }
        Files.createFile(timetableFile);
        LOGGER.log(Level.INFO, "Timetable created");
      } catch (final IOException e) {
        LOGGER.log(Level.SEVERE, "Unable to create file", e);
      }
      //writing to timetable file
      final List<String> timetableLines = Arrays.asList(this.timetable.toString().split("\\n"));
      try {
        Files.write(timetableFile, timetableLines, this.charset);
      } catch (final IOException e) {
        LOGGER.log(Level.SEVERE, "Unable to write to file", e);
      }
    } else {
      LOGGER.log(Level.SEVERE, "Course does not exist");
    }
    //    LOGGER.log(Level.INFO, this.timetable.toString());
  }

  private TimetableProperty getTimetable(final int courseID) {
    TimetableProperty timetableResult = null;
    String courseNameResult = null;
    ArrayList<ModuleProperty> modulesResult = null;
    ArrayList<ClassProperty> classesResult = null;
    final String queryTimetableString = "SELECT\n" +
        "  *\n" +
        "FROM CLASSTABLE\n" +
        "  INNER JOIN MODULETABLE\n" +
        "    ON CLASSTABLE.MODULE_ID = MODULETABLE.MODULE_ID\n" +
        "  INNER JOIN COURSETABLE\n" +
        "    ON MODULETABLE\n" +
        "       .COURSE_ID =\n" +
        "       COURSETABLE.COURSE_ID\n" +
        "WHERE coursetable.COURSE_ID = " + courseID;
    try (Connection connection = DatabaseConnection.getInstance();
        PreparedStatement preparedStatement = connection.prepareStatement(queryTimetableString);
        ResultSet resultSet = preparedStatement.executeQuery()) {
      modulesResult = new ArrayList<>(50);
      classesResult = new ArrayList<>(50);
      while (resultSet.next()) {
        courseNameResult = resultSet.getString(11);
        modulesResult.add(ModulePropertyBuilder.createModulePropertyBuilder().setModuleID
            (resultSet.getInt(6)).setModuleName(resultSet
            .getString(7)).setModuleLecturer(resultSet.getString(8)).setCourseID(
            resultSet.getInt(9)).createModuleProperty());
        classesResult.add(ClassPropertyBuilder.createClassPropertyBuilder().setClassID
            (resultSet.getInt(1)).setDay(resultSet.getString(2)).setTimeSlot(resultSet.getDouble
            (3)).setRoomNumber(resultSet.getInt(4)).setModuleID(
            resultSet.getInt(5)).createClassProperty());
      }
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "No course timetable exists", e);
    }
    if (courseNameResult != null && modulesResult != null && classesResult != null) {
      timetableResult = TimetableProperty.createTimetableProperty(courseNameResult, modulesResult,
          classesResult);
    }
    return timetableResult;
  }

  public static PrintTimetable createPrintTimetable(final int courseID) {
    return new PrintTimetable(courseID);
  }

}



