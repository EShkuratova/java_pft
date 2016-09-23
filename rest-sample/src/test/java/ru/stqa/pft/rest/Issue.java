package ru.stqa.pft.rest;

import java.util.Objects;

/**
 * Created by eshkuratova on 23.09.2016.
 */
public class Issue {


    int Id;
    String description;
    String subject;
    String state;

    public String getDescription() {
        return description;
    }
    public String getState() {
        return state;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public int getId() {
        return Id;
    }

    public Issue withId(int id) {

        Id = id;
        return this;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Id == issue.Id &&
                Objects.equals(description, issue.description) &&
                Objects.equals(subject, issue.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, description, subject);
    }

    @Override
    public String toString() {
        return "Issue{" +
                "Id=" + Id +
                ", description='" + description + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
