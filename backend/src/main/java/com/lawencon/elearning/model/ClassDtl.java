package com.lawencon.elearning.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="class_dtl")
@Data
@EqualsAndHashCode(callSuper=false)
public class ClassDtl extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "student_id", nullable=false)
	private User student;

	@ManyToOne
	@JoinColumn(name = "class_hdr_id", nullable=false)
	private ClassHdr classHdr;

}
