package com.lawencon.elearning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "submissions")
@Data
@EqualsAndHashCode(callSuper = false)
public class Submission extends BaseEntity{
    
    @Column(name = "score")
	private Float score;

	@ManyToOne
	@JoinColumn(name = "class_dtl_id", nullable=false)
	private ClassDtl classDtl;

	@ManyToOne
	@JoinColumn(name = "schedule_id", nullable=false)
	private Schedule schedule;
    
	@ManyToOne
	@JoinColumn(name = "file_id", nullable=false)
	private File file;

}
