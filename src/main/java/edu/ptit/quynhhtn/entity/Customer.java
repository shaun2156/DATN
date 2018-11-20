package edu.ptit.quynhhtn.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Person personInfo;

    private String contactNo;

    @Column(length = 2000)
    protected String description;

    @NotNull
    private Date joinDate;

    public Customer() {
    }

    public Customer(Person personInfo, String contactNo, String description, @NotNull Date joinDate) {
        this();
        this.personInfo = personInfo;
         this.contactNo = contactNo;
        this.description = description;
        this.joinDate = joinDate;
    }


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Person getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(Person personInfo) {
        this.personInfo = personInfo;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}
