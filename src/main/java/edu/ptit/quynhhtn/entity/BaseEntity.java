package edu.ptit.quynhhtn.entity;

import edu.ptit.quynhhtn.config.SiteConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    protected Long createdBy;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, name = "createdBy", referencedColumnName = "personId")
    protected Person createdByPerson;

    protected Long updatedBy;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, name = "updatedBy", referencedColumnName = "personId")
    protected Person updatedByPerson;

    protected Date createdDate;

    protected Date updatedDate;

    @PrePersist
    public void prePersist(){
        Person editor = SiteConfig.getInstance().getLoggedInUser();
        if(editor != null) {
            setCreatedByPerson(editor);
            setUpdatedByPerson(editor);
        }
        setCreatedDate(new Date());
        setUpdatedDate(new Date());
    }
    @PreUpdate
    public void preUpdate(){
        Person editor = SiteConfig.getInstance().getLoggedInUser();
        if(editor != null) {
            setUpdatedByPerson(editor);
        }
        setUpdatedDate(new Date());
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Person getCreatedByPerson() {
        return createdByPerson;
    }

    public void setCreatedByPerson(Person createdByPerson) {
        if(createdByPerson != null){
            setCreatedBy(createdByPerson.getPersonId());
        }
        this.createdByPerson = createdByPerson;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Person getUpdatedByPerson() {
        return updatedByPerson;
    }

    public void setUpdatedByPerson(Person updatedByPerson) {
        if(updatedByPerson != null){
            setUpdatedBy(updatedByPerson.getPersonId());
        }
        this.updatedByPerson = updatedByPerson;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }


}
