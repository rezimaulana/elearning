package com.lawencon.elearning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="files")
@Data
@EqualsAndHashCode(callSuper=false)
public class File extends BaseEntity {

	@Column(name = "file_code", nullable = false)
	private String filecode;
	
	@Column(name = "file_ext", nullable = false)
	private String ext;

}
