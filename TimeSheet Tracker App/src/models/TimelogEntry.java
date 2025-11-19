// Packages
package models;

// IMPORTS
import java.sql.Timestamp;
import java.sql.Date;

// Class to create an object for each timelog entry
public class TimelogEntry {

    private String projectName;
    private Timestamp clockIn;
    private Timestamp clockOut;
    private double totalHours;
    private Date workDate;

    // Constructor for the TimelogEntry class object
    public TimelogEntry(String projectName, Timestamp clockIn, Timestamp clockOut, double totalHours, Date workDate) {
        this.projectName = projectName;
        this.clockIn = clockIn;
        this.clockOut = clockOut;
        this.totalHours = totalHours;
        this.workDate = workDate;
    }
    
    public String getProjectName() {
        return projectName;
    }

    public Timestamp getClockIn() {
        return clockIn;
    }

    public Timestamp getClockOut() {
        return clockOut;
    }

    public double getTOtalHours() {
        return totalHours;
    }

    public Date getWorkDate() {
        return workDate;
    }
  
}
