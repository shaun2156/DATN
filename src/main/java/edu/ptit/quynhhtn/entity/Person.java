package edu.ptit.quynhhtn.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Person extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long personId;

    @Column(unique = true, length = 120)
    private String username;

    @Column(length = 128, nullable = false)
    private String password;

    @NotNull
    private String fullName;

    @NotNull
    private String shortName;

    private String phone;

    @NotNull
    private String email;

    private Date dob;

    @Column(columnDefinition="bit default 0")
    private boolean suspended = false;

    public Person(@NotNull String username, @NotNull String password, @NotNull String fullName, @NotNull String shortName, @NotNull String phone, @NotNull String email, @NotNull Date dob) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.shortName = shortName;
        this.phone = phone;
        this.email = email;
        this.dob = dob;
    }

    public Person() {
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }
}
