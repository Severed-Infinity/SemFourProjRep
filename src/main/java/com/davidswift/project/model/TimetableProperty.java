package com.davidswift.project.model;

import java.util.*;
import java.util.logging.*;
import java.util.stream.*;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 8/28/2014.
 */
public class TimetableProperty {
  public static final Logger LOGGER = Logger.getLogger(TimetableProperty.class.getName());
  private final String courseName;
  private final ArrayList<ModuleProperty> modules;
  private final ArrayList<ClassProperty> classes;

  private TimetableProperty(
      final String courseName,
      final ArrayList<ModuleProperty> modules,
      final ArrayList<ClassProperty> classes
  ) {
    super();
    this.courseName = courseName;
    this.modules = modules;
    this.classes = classes;
  }

  public static TimetableProperty createTimetableProperty(
      final String courseName,
      final ArrayList<ModuleProperty> modules,
      final ArrayList<ClassProperty> classes
  ) {return new TimetableProperty(courseName, modules, classes);}

  public String getCourseName() {
    return this.courseName;
  }

  @Override
  public String toString() {
    final StringBuilder timetableFormat = new StringBuilder(300);
    timetableFormat.append("Course: ").append(
        this.courseName).append("\n---------------------------\n").append(
        "Monday\n================\n");
    //printing monday classes
    try (Stream<ClassProperty> classStreamMonday = this.classes.stream(); Stream<ClassProperty>
        classStreamMondayFilter = classStreamMonday.filter(classProperty -> "monday"
        .equalsIgnoreCase(
            classProperty.getDay())); Stream<ClassProperty> classStreamMondayDistinct =
        classStreamMondayFilter.distinct()) {
      classStreamMondayDistinct.forEach(
          classProperty -> { //todo tidy nested try loop into one try-with-resources
            try (Stream<ModuleProperty> moduleStream = this.modules.stream();
                Stream<ModuleProperty> moduleStreamFilter = moduleStream.filter(
                    module -> module.getModuleID() == classProperty.getModuleID());
                Stream<ModuleProperty> moduleStreamDistinct = moduleStreamFilter.distinct()) {
              moduleStreamDistinct.findFirst().ifPresent(
                  module -> timetableFormat.append("Time: ").append(classProperty.getTimeSlot())
                      .append
                          ("\tRoom: ").append(classProperty.getRoomNumber()).append(
                          "\nModule: \t").append
                          (module
                              .getModuleName()).append("\nLecturer: \t").append(
                          module.getModuleLecturer())
                      .append("\n\n"));
            }
          });
    }
    timetableFormat.append("Tuesday\n" +
        "================\n");
    try (Stream<ClassProperty> classStreamTuesday = this.classes.stream(); Stream<ClassProperty>
        classStreamTuesdayFilter = classStreamTuesday.filter(classProperty -> "tuesday"
        .equalsIgnoreCase(
            classProperty.getDay())); Stream<ClassProperty> classStreamTuesdayDistinct =
        classStreamTuesdayFilter.distinct()) {
      classStreamTuesdayDistinct.forEach(
          classProperty -> { //todo tidy nested try loop into one try-with-resources
            try (Stream<ModuleProperty> moduleStream = this.modules.stream();
                Stream<ModuleProperty> moduleStreamFilter = moduleStream.filter(
                    module -> module.getModuleID() == classProperty.getModuleID());
                Stream<ModuleProperty> moduleStreamDistinct = moduleStreamFilter.distinct()) {
              moduleStreamDistinct.findFirst().ifPresent(
                  module -> timetableFormat.append("Time: ").append(classProperty.getTimeSlot())
                      .append
                          ("\tRoom: ").append(classProperty.getRoomNumber()).append(
                          "\nModule: \t").append
                          (module
                              .getModuleName()).append("\nLecturer: \t").append(
                          module.getModuleLecturer())
                      .append("\n\n"));
            }
          });
    }
    timetableFormat.append("Wednesday\n" +
        "================\n");
    try (Stream<ClassProperty> classStreamWednesday = this.classes.stream();
        Stream<ClassProperty>
            classStreamWednesdayFilter = classStreamWednesday.filter(
            classProperty -> "wednesday"
                .equalsIgnoreCase(
                    classProperty.getDay())); Stream<ClassProperty> classStreamWednesdayDistinct =
        classStreamWednesdayFilter.distinct()) {
      classStreamWednesdayDistinct.forEach(
          classProperty -> { //todo tidy nested try loop into one try-with-resources
            try (Stream<ModuleProperty> moduleStream = this.modules.stream();
                Stream<ModuleProperty> moduleStreamFilter = moduleStream.filter(
                    module -> module.getModuleID() == classProperty.getModuleID());
                Stream<ModuleProperty> moduleStreamDistinct = moduleStreamFilter.distinct()) {
              moduleStreamDistinct.findFirst().ifPresent(
                  module -> timetableFormat.append("Time: ").append(classProperty.getTimeSlot())
                      .append
                          ("\tRoom: ").append(classProperty.getRoomNumber()).append(
                          "\nModule: \t").append
                          (module
                              .getModuleName()).append("\nLecturer: \t").append(
                          module.getModuleLecturer())
                      .append("\n\n"));
            }
          });
    }
    timetableFormat.append("Thursday\n" +
        "================\n");
    try (Stream<ClassProperty> classStreamThursday = this.classes.stream(); Stream<ClassProperty>
        classStreamThursdayFilter = classStreamThursday.filter(classProperty -> "thursday"
        .equalsIgnoreCase(
            classProperty.getDay())); Stream<ClassProperty> classStreamThursdayDistinct =
        classStreamThursdayFilter.distinct()) {
      classStreamThursdayDistinct.forEach(
          classProperty -> { //todo tidy nested try loop into one try-with-resources
            try (Stream<ModuleProperty> moduleStream = this.modules.stream();
                Stream<ModuleProperty> moduleStreamFilter = moduleStream.filter(
                    module -> module.getModuleID() == classProperty.getModuleID());
                Stream<ModuleProperty> moduleStreamDistinct = moduleStreamFilter.distinct()) {
              moduleStreamDistinct.findFirst().ifPresent(
                  module -> timetableFormat.append("Time: ").append(
                      classProperty.getTimeSlot())
                      .append
                          ("\tRoom: ").append(classProperty.getRoomNumber()).append(
                          "\nModule: \t").append
                          (module
                              .getModuleName()).append("\nLecturer: \t").append(
                          module.getModuleLecturer())
                      .append("\n\n"));
            }
          });
    }
    timetableFormat.append("Friday\n" +
        "================\n");
    try (Stream<ClassProperty> classStreamFriday = this.classes.stream(); Stream<ClassProperty>
        classStreamFridayFilter = classStreamFriday.filter(classProperty -> "friday"
        .equalsIgnoreCase(
            classProperty.getDay())); Stream<ClassProperty> classStreamFridayDistinct =
        classStreamFridayFilter.distinct()) {
      classStreamFridayDistinct.forEach(
          classProperty -> { //todo tidy nested try loop into one try-with-resources
            try (Stream<ModuleProperty> moduleStream = this.modules.stream();
                Stream<ModuleProperty> moduleStreamFilter = moduleStream.filter(
                    module -> module.getModuleID() == classProperty.getModuleID());
                Stream<ModuleProperty> moduleStreamDistinct = moduleStreamFilter.distinct()) {
              moduleStreamDistinct.findFirst().ifPresent(
                  module -> timetableFormat.append("Time: ").append(
                      classProperty.getTimeSlot())
                      .append
                          ("\tRoom: ").append(classProperty.getRoomNumber()).append(
                          "\nModule: \t").append
                          (module
                              .getModuleName()).append("\nLecturer: \t").append(
                          module.getModuleLecturer())
                      .append("\n\n"));
            }
          });
    }
    return timetableFormat.toString();
  }
}

