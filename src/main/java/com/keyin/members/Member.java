package com.keyin.members;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @SequenceGenerator(name = "member_sequence", sequenceName = "member_sequence", allocationSize = 1)
    @GeneratedValue(generator = "member_sequence")
    private long id;

    private String firstName;
    private String lastName;
    private String email;

    public Member() {
    }

    public Member(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
