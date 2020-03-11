package ca.cmpt213.as4.model;

/**
 * CourseComponent manages the type of component it is (lec, tut, lab, etc.) and its enrollment capacity and total
 */
public class CourseComponent {
    private final String COMPONENTCODE;
    private int enrollmentCapacity;
    private int enrollmentTotal;

    public CourseComponent(String componentCode, int enrollmentCapacity, int enrollmentTotal) {
        COMPONENTCODE = componentCode;
        this.enrollmentCapacity = enrollmentCapacity;
        this.enrollmentTotal = enrollmentTotal;
    }

    public void addSameComponent(int enrollmentCapacity, int enrollmentTotal) {
        this.enrollmentCapacity += enrollmentCapacity;
        this.enrollmentTotal += enrollmentTotal;
    }

    public String getComponentCode() {
        return COMPONENTCODE;
    }

    public int getEnrollmentCapacity() {
        return enrollmentCapacity;
    }

    public int getEnrollmentTotal() {
        return enrollmentTotal;
    }

}
