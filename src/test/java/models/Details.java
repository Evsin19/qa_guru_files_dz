package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Details {
    @JsonProperty("Teacher")
    private String teacher;

    @JsonProperty("Speciality")
    private String speciality;
    @JsonProperty("Lectures")
    private String[] lectures;

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String[] getLectures() {
        return lectures;
    }

    public void setLectures(String[] lectures) {
        this.lectures = lectures;
    }
}
