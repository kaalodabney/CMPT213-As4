package ca.cmpt213.as4.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Model Facade to pass data to the ui classes from the model classes
 */
public class Model {
    private final File FILE = new File("data\\course_data_2016.csv");
    private CourseManager courseManager;
    private FileReader reader;

    private Course selectedCourse = null;
    private CourseOffering selectedOffering = null;
    private DepartmentSelection selectedDepartment = new DepartmentSelection("", false, false);

    private ArrayList<ModelObserver> departmentObservers = new ArrayList<>();
    private ArrayList<ModelObserver> courseObservers = new ArrayList<>();
    private ArrayList<ModelObserver> offeringObservers = new ArrayList<>();

    public Model() {
        courseManager = new CourseManager();
        reader = new FileReader(courseManager);
    }


    public void loadFile() throws FileNotFoundException {
        reader.readFile(FILE);
        dumpModelToFile(courseManager);
    }

    public void dumpModelToFile(CourseManager courseManager) throws FileNotFoundException {
        File file = new File("C:\\Users\\kaalo\\Documents\\school\\CMPT 213\\as4\\data\\output_dump.txt");
        PrintWriter writer = new PrintWriter(file);

        ArrayList<Course> courseList = courseManager.getCourseList();
        for (Course course : courseList) {
            writer.printf("%s %s%n", course.getSubject(), course.getCatalognumber());
            ArrayList<CourseOffering> courseOfferingList = course.getCourseOfferingList();
            for (CourseOffering courseOffer : courseOfferingList) {
                writer.printf("\t%s in %s by %s%n", courseOffer.getSemester().getSEMESTERCODE(), courseOffer.getLocation(), courseOffer.getInstructors());
                ArrayList<CourseComponent> courseComponentList = courseOffer.getCourseComponentList();
                for (CourseComponent courseComponent : courseComponentList) {
                    writer.printf("\t\tType=%s, Enrollment=%d/%d%n", courseComponent.getComponentCode(), courseComponent.getEnrollmentTotal(), courseComponent.getEnrollmentCapacity());
                }

            }
        }
        writer.close();
    }

    public Vector<String> getSubjectVectorList() {
        Vector<String> subjects = new Vector<>();
        for (Course course : courseManager.getCourseList()) {
            if (!subjects.contains(course.getSubject())) {
                subjects.add(course.getSubject());
            }
        }

        java.util.Collections.sort(subjects);
        return subjects;
    }

    public Vector<String> getCourseVectorList() {
        Vector<String> courses = new Vector<>();
        for (Course course : courseManager.getCourseList()) {
            if (course.getSubject().equals(selectedDepartment.getDepartment())) {
                if ((selectedDepartment.isUndergrad()) && (getStringValue(course.getCatalognumber()) < 500)) {
                    courses.add(course.getSubject() + course.getCatalognumber());
                }
                if ((selectedDepartment.isGrad()) && (getStringValue(course.getCatalognumber()) >= 500)) {
                    courses.add(course.getSubject() + course.getCatalognumber());
                }
            }
        }

        java.util.Collections.sort(courses);
        return courses;
    }

    public File getInputFile() {
        return FILE;
    }

    public Course getSelectedCourse() {
        return selectedCourse;
    }

    public CourseOffering getSelectedOffering() {
        return selectedOffering;
    }

    public void setSelectedCourse(String course) {
        if (course == null) {
            selectedCourse = null;
        } else {
            for (Course c : courseManager.getCourseList()) {
                if (course.equals(c.getSubject() + c.getCatalognumber())) {
                    selectedCourse = c;
                }
            }
        }
        notifyCourseObservers();
    }

    public void setSelectedCourseOffering(CourseOffering offering) {
        selectedOffering = offering;
        notifyOfferingObservers();
    }

    public void setSelectedDepartment(String department, boolean underGrad, boolean grad) {
        selectedDepartment.setDepartment(department);
        selectedDepartment.setUndergrad(underGrad);
        selectedDepartment.setGrad(grad);
        notifyDepartmentObservers();
    }

    public void addObserver(ModelObserver observer, String list) {
        if (list.equals("department")) {
            departmentObservers.add(observer);
        } else if (list.equals("course")) {
            courseObservers.add(observer);
        } else if (list.equals(("offering"))) {
            offeringObservers.add(observer);
        }
    }

    private void notifyCourseObservers() {
        for (ModelObserver observer : courseObservers) {
            observer.stateChanged();
        }
    }

    private void notifyOfferingObservers() {
        for (ModelObserver observer : offeringObservers) {
            observer.stateChanged();
        }
    }

    private void notifyDepartmentObservers() {
        for (ModelObserver observer : departmentObservers) {
            observer.stateChanged();
        }
    }

    private static int getStringValue(String str) {
        return Integer.valueOf("0" + str.replaceAll("(\\d*).*", "$1"));
    }


}
