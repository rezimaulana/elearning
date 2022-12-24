package com.lawencon.elearning.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "attachments")
@Data
@EqualsAndHashCode(callSuper = false)
public class Attachment extends BaseEntity{
    
    @ManyToOne
	@JoinColumn(name = "material_id", nullable=false)
	private Material material;
    
	@ManyToOne
	@JoinColumn(name = "file_id", nullable=false)
	private File file;

}
