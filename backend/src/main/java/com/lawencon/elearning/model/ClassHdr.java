package com.lawencon.elearning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="class_hdr", uniqueConstraints = {
    @UniqueConstraint(name="class_hdr_bk", columnNames = {"class_hdr_code"})
})
@Data
@EqualsAndHashCode(callSuper=false)
public class ClassHdr extends BaseEntity {

	@Column(name = "class_hdr_code", nullable=false, length=10, unique = true)
	private String classHdrCode;

	@Column(name = "class_hdr_subject", nullable=false, length=200)
	private String classHdrSubject;

	@Column(name = "class_hdr_description", nullable=false)
	private String classHdrDescription;

	@ManyToOne
	@JoinColumn(name = "instructor_id", nullable=false)
	private User instructor;

	@ManyToOne
	@JoinColumn(name = "photo_id", nullable = false)
	private File photo;

}