package com.lawencon.elearning.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "created_by", nullable = false)
	private Long createdBy;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_by")
	private Long updatedBy;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@Version
	private Integer ver;
	
	@Column(name = "is_active", nullable = false)
	private Boolean isActive;
	
	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
		this.isActive = true;
	}
	@PreUpdate
	public void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
	
}
