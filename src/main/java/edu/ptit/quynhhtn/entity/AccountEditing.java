package edu.ptit.quynhhtn.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class AccountEditing extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountEditingId;

    private Long personId;

    private boolean prevSuspended;

    private String oldUsername;

    private String oldPassword;

    private String oldFullName;

    private String oldShortName;

    private String oldPhone;

    private String oldEmail;

    private Date oldDob;

    @Column(length = 2000)
    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personId", referencedColumnName = "personId", insertable = false, updatable = false)
    private Person person;

    public AccountEditing() {
    }

    public Long getAccountEditingId() {
        return accountEditingId;
    }

    public void setAccountEditingId(Long accountEditingId) {
        this.accountEditingId = accountEditingId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public boolean isPrevSuspended() {
        return prevSuspended;
    }

    public void setPrevSuspended(boolean prevSuspended) {
        this.prevSuspended = prevSuspended;
    }

    public String getOldUsername() {
        return oldUsername;
    }

    public void setOldUsername(String oldUsername) {
        this.oldUsername = oldUsername;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getOldFullName() {
        return oldFullName;
    }

    public void setOldFullName(String oldFullName) {
        this.oldFullName = oldFullName;
    }

    public String getOldShortName() {
        return oldShortName;
    }

    public void setOldShortName(String oldShortName) {
        this.oldShortName = oldShortName;
    }

    public String getOldPhone() {
        return oldPhone;
    }

    public void setOldPhone(String oldPhone) {
        this.oldPhone = oldPhone;
    }

    public String getOldEmail() {
        return oldEmail;
    }

    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }

    public Date getOldDob() {
        return oldDob;
    }

    public void setOldDob(Date oldDob) {
        this.oldDob = oldDob;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        if(person != null){
            setPersonId(person.getPersonId());
        }
        this.person = person;
    }
}
