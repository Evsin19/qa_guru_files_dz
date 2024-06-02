package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Education {
    @JsonProperty("Course")
    private String course;
    @JsonProperty("ID")
    private Integer id;
    @JsonProperty("Details")
    private Details details;

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
}
