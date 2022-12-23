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
@Table(name="users", uniqueConstraints = {
    @UniqueConstraint(name="users_bk", columnNames = {"email"})
})
@Data
@EqualsAndHashCode(callSuper=false)
public class User extends BaseEntity{
    
    @Column(name = "email", nullable=false, unique=true)
	private String email;

	@Column(name = "password", nullable=false)
	private String password;
	
    @Column(name = "fullname", nullable=false)
	private String fullname;
	
    @ManyToOne
	@JoinColumn(name = "role_id", nullable=false)
	private Role role;
	
    @ManyToOne
	@JoinColumn(name = "photo_id")
	private File photo;

}
