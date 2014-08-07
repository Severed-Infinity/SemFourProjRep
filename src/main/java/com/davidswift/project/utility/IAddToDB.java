package com.davidswift.project.utility;

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
@FunctionalInterface
public interface IAddToDB {
  public default void addToDB(final String table, final Object... args) throws
      SQLException {
    int i = 1;
    final StringBuilder stringBuilder = new StringBuilder(16);
    //initial start of insert statement
    stringBuilder.append("Insert into ").append(table).append("TABLE values (");
    //dynamic adding of arguments
    for (int j = 1; j < args.length; j++) {
      stringBuilder.append("?,");
    }
    stringBuilder.append("?)"); // end of insert statement
    final String addToDBString = stringBuilder.toString();
//    System.out.println(addToDBString);//testing the output of the insert statement

    try (Connection connection = DatabaseConnection.getInstance();PreparedStatement ps = connection.prepareStatement(
        addToDBString)) {
      //running through the passed in arguments and finding there types
      for (final Object arg : args) {
        if (arg instanceof Date) {
          ps.setTimestamp(i++, new Timestamp(((Date)arg).getTime()));
        } else if (arg instanceof Integer) {
          ps.setInt(i++, (Integer)arg);
        } else if (arg instanceof Long) {
          ps.setLong(i++, (Long)arg);
        } else if (arg instanceof Double) {
          ps.setDouble(i++, (Double)arg);
        } else if (arg instanceof Float) {
          ps.setFloat(i++, (Float)arg);
        } else {
          ps.setString(i++, (String)arg);
        }
      }
      ps.executeUpdate();//executing the insert
      ps.close();
    }
  }
  public abstract void addToDB();

}
