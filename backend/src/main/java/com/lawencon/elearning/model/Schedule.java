package com.lawencon.elearning.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "schedules")
@Data
@EqualsAndHashCode(callSuper = false)
public class Schedule extends BaseEntity {
    
    @Column(name = "start_time", nullable=false)
	private LocalDateTime startTime;

	@Column(name = "end_time", nullable=false)
	private LocalDateTime endTime;
    
	@OneToOne
	@JoinColumn(name = "material_id", nullable=false)
	private Material material;

}
