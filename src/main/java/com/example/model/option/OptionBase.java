package com.example.model.option;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.Length;

import com.example.model.Base;

@MappedSuperclass
public class OptionBase extends Base {

	@Length(min = 3, max = 10)
	private String shortName;

	@Length(min = 3, max = 50)
	private String longName;

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

}
