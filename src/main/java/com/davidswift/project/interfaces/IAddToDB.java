package com.davidswift.project.interfaces;

import java.sql.*;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 6/29/2014.
 */
public interface IAddToDB {
  PreparedStatement preparedStatement = null;

  //  public <T> void addToDB(T... args) throws SQLException;
  public default <T, S, Connection> void addToDB(
      S s,
      Connection connection,
      T... args
  ) throws SQLException {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("INSERT INTO ").append(s).append(" values (");
    for (T t : args) {
      stringBuilder.append("?,");
    }
    stringBuilder.append(")");
    preparedStatement = connection.preparedStatement(stringBuilder.toString());
    for (int i = 0; 0 < args.length; i++) {
      preparedStatement.setObject(i, args[i]);
    }
    preparedStatement.executeUpdate();
  }
}
