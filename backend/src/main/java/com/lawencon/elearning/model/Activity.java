package com.lawencon.elearning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "activities", uniqueConstraints = {
        @UniqueConstraint(name = "activities_bk", columnNames = { "activity_code" }),
        @UniqueConstraint(name = "activities_ck", columnNames = { "activity_code", "activity_type" })
})
@Data
@EqualsAndHashCode(callSuper=false)
public class Activity extends BaseEntity{

    @Column(name = "activity_code", nullable = false, length = 10, unique = true)
    private String code;

    @Column(name = "activity_type", nullable = false, length = 20)
    private String type;

}
