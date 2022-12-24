package com.lawencon.elearning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "forums")
@Data
@EqualsAndHashCode(callSuper = false)
public class Forum extends BaseEntity {
    
    @Column(name = "title", nullable=false, length = 200)
	private String title;

	@Column(name = "content", nullable=false)
	private String content;
    
	@ManyToOne
	@JoinColumn(name = "class_dtl_id", nullable=false)
	private ClassDtl classDtl;

}
