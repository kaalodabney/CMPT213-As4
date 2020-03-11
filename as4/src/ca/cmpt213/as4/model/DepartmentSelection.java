package ca.cmpt213.as4.model;

/**
 * DepartmentSelection is used when choosing what department to display with the given filters
 */
public class DepartmentSelection {
    private String department;
    private boolean undergrad;
    private boolean grad;

    public DepartmentSelection(String department, boolean undergrad, boolean grad) {
        this.department = department;
        this.undergrad = undergrad;
        this.grad = grad;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setGrad(boolean grad) {
        this.grad = grad;
    }

    public void setUndergrad(boolean undergrad) {
        this.undergrad = undergrad;
    }

    public String getDepartment() {

        return department;
    }

    public boolean isUndergrad() {
        return undergrad;
    }

    public boolean isGrad() {
        return grad;
    }
}
