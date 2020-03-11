package ca.cmpt213.as4.model;

import java.util.ArrayList;

/**
 * Course class is a course at SFU with a subject and catalog number, it stores a list of offerings of the course.
 */
public class Course {
    private final String SUBJECT;
    private final String CATALOGNUMBER;
    private ArrayList<CourseOffering> courseOfferingList = new ArrayList<>();


    public Course(String subject, String catalogNumber) {
        SUBJECT = subject;
        CATALOGNUMBER = catalogNumber;
    }

    public void addNewCourseOffering(String semesterCode, String location, int enrollmentCapacity, int enrollmentTotal, String instructors, String componentCode) {
        Semester semester = new Semester(semesterCode);
        CourseOffering newCourseOffering = new CourseOffering(semester, location, instructors);
        newCourseOffering.addCourseComponent(componentCode, enrollmentCapacity, enrollmentTotal, instructors);
        courseOfferingList.add(newCourseOffering);
    }

    //finds the existing CourseOffering and adds a component to it
    public void addExistingCourseOffering(String semester, String location, String instructors, int enrollmentCapacity, int enrollmentTotal, String componentCode) {
        for (CourseOffering courseOffering : courseOfferingList) {
            if (courseOffering.getLocation().equals(location) && courseOffering.getSemester().getSEMESTERCODE().equals(semester)) {
                courseOffering.addCourseComponent(componentCode, enrollmentCapacity, enrollmentTotal, instructors);
            }
        }
    }

    public boolean courseOfferingExists(String semester, String location, String instructors) {
        for (CourseOffering course : courseOfferingList) {
            if (course.getLocation().equals(location) && course.getSemester().getSEMESTERCODE().equals(semester)) {
                return true;
            }
        }
        return false;
    }

    public String getSubject() {
        return SUBJECT;
    }

    public String getCatalognumber() {
        return CATALOGNUMBER;
    }

    public ArrayList<CourseOffering> getCourseOfferingList() {
        return courseOfferingList;
    }


}
