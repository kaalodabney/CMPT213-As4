package ca.cmpt213.as4.model;

import java.util.ArrayList;

/**
 * CourseManager class contains a list of all courses and facilitates adding courses to the list
 */
public class CourseManager {
    ArrayList<Course> courseList = new ArrayList<>();

    public void addCourse(String semester, String subject, String catalogNumber, String location, String enrollCap, String enrollTot, String inst, String componentCode) {
        String instructors = nullInstructors(inst);
        int enrollmentCapacity = Integer.parseInt(enrollCap);
        int enrollmentTotal = Integer.parseInt(enrollTot);

        Course courseToEdit = null;
        boolean courseInList = false;
        if (courseList.isEmpty()) {
            addNewCourse(semester, subject, catalogNumber, location, enrollmentCapacity, enrollmentTotal, instructors, componentCode);
        } else {
            for (Course course : courseList) {
                if (subject.equals(course.getSubject()) && catalogNumber.equals(course.getCatalognumber())) {
                    courseToEdit = course;
                    courseInList = true;
                    break;
                } else {
                    courseInList = false;
                }
            }
            if (courseInList) {
                addExistingCourse(courseToEdit, semester, location, enrollmentCapacity, enrollmentTotal, instructors, componentCode);
            } else {
                addNewCourse(semester, subject, catalogNumber, location, enrollmentCapacity, enrollmentTotal, instructors, componentCode);
            }
        }
    }

    private void addExistingCourse(Course course, String semester, String location, int enrollmentCapacity, int enrollmentTotal, String inst, String componentCode) {
        String instructors = nullInstructors(inst);
        if (course.courseOfferingExists(semester, location, instructors)) {
            course.addExistingCourseOffering(semester, location, instructors, enrollmentCapacity, enrollmentTotal, componentCode);
        } else {
            course.addNewCourseOffering(semester, location, enrollmentCapacity, enrollmentTotal, instructors, componentCode);
        }
    }

    private void addNewCourse(String semester, String subject, String catalogNumber, String location, int enrollmentCapacity, int enrollmentTotal, String inst, String componentCode) {
        String instructors = nullInstructors(inst);
        Course newCourse = new Course(subject, catalogNumber);
        newCourse.addNewCourseOffering(semester, location, enrollmentCapacity, enrollmentTotal, instructors, componentCode);
        courseList.add(newCourse);
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    private String nullInstructors(String instructors) {
        if (instructors.equals("(null)")) {
            return "";
        }
        return instructors;
    }


}
