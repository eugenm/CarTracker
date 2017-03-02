package com.example.model.option;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Entity
public class Feature {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "featureId", nullable = false)
	private int featureId;

	public void setFeatureId(int featureId) {
		this.featureId = featureId;
	}

	public int getFeatureId() {
		return featureId;
	}

	public Feature() {
	}
}
