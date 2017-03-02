package com.example.model.option;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Color extends OptionBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "colorId", nullable = false)
	private int colorId;

	public int getId() {
		return colorId;
	}

	public void setId(int id) {
		this.colorId = id;
	}

	public Color() {

	}

}
