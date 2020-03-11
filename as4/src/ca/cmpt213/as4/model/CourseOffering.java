package ca.cmpt213.as4.model;

import java.util.ArrayList;

/**
 * Course offering is a concrete course that has been held at sfu, it stores its semester, location, instructor and a list of its components
 */
public class CourseOffering {
    private final Semester SEMESTER;
    private final String LOCATION;
    private String instructors;
    private ArrayList<CourseComponent> courseComponentList = new ArrayList<>();

    public CourseOffering(Semester semester, String location, String instructors) {
        SEMESTER = semester;
        LOCATION = location;
        this.instructors = instructors;
    }

    public void addCourseComponent(String componentCode, int enrollmentCapacity, int enrollmentTotal, String instructors) {
        boolean newComponent = false;
        boolean newInstructors = checkInstructors(instructors);
        if (newInstructors) {
            addNewInstructors(instructors);
        }

        if (courseComponentList.isEmpty()) {
            courseComponentList.add(new CourseComponent(componentCode, enrollmentCapacity, enrollmentTotal));
        } else {
            for (CourseComponent courseComponent : courseComponentList) {
                if (componentCode.equals(courseComponent.getComponentCode())) {
                    courseComponent.addSameComponent(enrollmentCapacity, enrollmentTotal);
                    newComponent = false;
                    break;
                } else {
                    newComponent = true;
                }
            }

            if (newComponent) {
                courseComponentList.add(new CourseComponent(componentCode, enrollmentCapacity, enrollmentTotal));
            }
        }
    }

    public String getLocation() {
        return LOCATION;
    }

    public String getInstructors() {
        return instructors;
    }

    public Semester getSemester() {
        return SEMESTER;
    }

    public ArrayList<CourseComponent> getCourseComponentList() {
        return courseComponentList;
    }

    private boolean checkInstructors(String newInstructors) {
        if (instructors.contains(newInstructors)) {
            return false;
        } else {
            return true;
        }
    }

    private void addNewInstructors(String newInstructors) {
        instructors = instructors + ", " + newInstructors;
    }
}
