package edu.ptit.quynhhtn.entity;

import javax.persistence.*;

@Entity
public class EmployeeRoleAccess extends BaseEntity {
    @Id
    @GeneratedValue
    private Long roleAccessId;

    private Long employeeId;

    private Long roleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId",insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", referencedColumnName = "roleId",insertable = false, updatable = false)
    private Role role;

    public EmployeeRoleAccess() {
    }

    public Long getRoleAccessId() {
        return roleAccessId;
    }

    public void setRoleAccessId(Long roleAccessId) {
        this.roleAccessId = roleAccessId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        if(employee != null){
            setEmployeeId(employee.getEmployeeId());
        }
        this.employee = employee;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        if(role != null){
            setRoleId(role.getRoleId());
        }
        this.role = role;
    }
}
