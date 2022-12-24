package com.lawencon.elearning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(name = "roles_bk", columnNames = { "role_code" }),
        @UniqueConstraint(name = "roles_ck", columnNames = { "role_code", "role_name" })
})
@Data
@EqualsAndHashCode(callSuper=false)
public class Role extends BaseEntity {

    @Column(name = "role_code", nullable = false, length = 10, unique = true)
    private String roleCode;

    @Column(name = "role_name", nullable = false, length = 20)
    private String roleName;

}
