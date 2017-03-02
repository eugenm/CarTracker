package com.example.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class Base {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, updatable = false)
	private Date Created;

	@NotNull
	private boolean Active;

	@PrePersist
	public void onCreate() {
		Created = new Date();
	}

	public Date getCreated() {
		return Created;
	}

	public void setCreated(Date created) {
		this.Created = created;
	}

	public boolean isActive() {
		return Active;
	}

	public void setActive(boolean active) {
		this.Active = active;
	}
}
