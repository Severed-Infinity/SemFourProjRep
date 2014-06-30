package com.davidswift.project.controller;

import com.davidswift.project.data.*;
import com.davidswift.project.interfaces.*;

import java.sql.*;
import java.util.*;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 6/30/2014.
 */
public class UserController implements IAddToDB {
  public LinkedList<User> userLinkedList = new LinkedList<>();
  private PreparedStatement preparedStatement;

  private UserController() {}

  public static UserController createUserController() {return new UserController();}



}
