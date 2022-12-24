package com.lawencon.elearning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="attendances")
@Data
@EqualsAndHashCode(callSuper = false)
public class Attendance extends BaseEntity{
    
    @Column(name = "approval")
	private Boolean approval;

	@ManyToOne
	@JoinColumn(name = "class_dtl_id", nullable=false)
	private ClassDtl classDtl;

	@ManyToOne
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;
    
}
