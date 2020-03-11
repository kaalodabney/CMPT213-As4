package ca.cmpt213.as4.model;

/**
 * Semester stores info on when a class is held, parsing its semester code into year and season.
 */
public class Semester {
    private final String SEMESTERCODE;
    private int year;
    private String season;

    public Semester(String semesterCode) {
        SEMESTERCODE = semesterCode;
        year = 2000 + Integer.parseInt(SEMESTERCODE.substring(1, 3));
        switch (SEMESTERCODE.charAt(3)) {
            case '1':
                season = "Spring";
                break;
            case '4':
                season = "Summer";
                break;
            case '7':
                season = "Fall";
                break;
            default:
                season = "invalid semester entry";
                break;
        }
    }

    public int getYear() {
        return year;
    }

    public String getSeason() {
        return season;
    }

    public String getSEMESTERCODE() {
        return SEMESTERCODE;
    }
}
