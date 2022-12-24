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
@Table(name="materials", uniqueConstraints = {
    @UniqueConstraint(name="materials_bk", columnNames={"material_code"})
})
@Data
@EqualsAndHashCode(callSuper=false)
public class Material extends BaseEntity{
    
    @Column(name = "material_code", nullable=false, length = 10, unique=true)
	private String materialCode;

	@Column(name = "material_subject", nullable=false, length = 200)
	private String materialSubject;

	@Column(name = "material_description", nullable=false)
	private String materialDescription;
    
	@ManyToOne
	@JoinColumn(name = "class_hdr_id", nullable=false)
	private ClassHdr classHdr;

    @ManyToOne
	@JoinColumn(name = "activities_id", nullable=false)
	private Activity activity;

}
