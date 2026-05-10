package com.tvdfp.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
	private String name;
	private String username;
	private String password;
	private String email;
	@Temporal(TemporalType.DATE)
	private Date created;
	@Temporal(TemporalType.DATE)
	private Date modified;
	private Integer status;
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(Long user_id, String name, String username, String password, String email, Date created, Date modified,
			Integer status) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.created = created;
		this.modified = modified;
		this.status = status;
		
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", created=" + created + ", modified=" + modified + ", status=" + status
				+ "]";
	}
}
	
