package com.lawencon.elearning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "comments")
@Data
@EqualsAndHashCode(callSuper = false)
public class Comment extends BaseEntity{
    
    @Column(name = "comment", nullable=false)
	private String content;
    
	@ManyToOne
	@JoinColumn(name = "forum_id", nullable=false)
	private Forum forum;

    @ManyToOne
	@JoinColumn(name = "user_id", nullable=false)
	private User user;

}
