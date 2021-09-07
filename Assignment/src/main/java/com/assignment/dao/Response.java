package com.assignment.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "RESPONSE_STORE")
public class Response implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "DUPLICATE_CHARS")
	private String duplicateChars;
	
	@Column(name = "REPLACE_SPACES")
	private String replaceSpaces;
	
	@Column(name = "LARGEST_NUMBER")
	private Integer largestNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDuplicateChars() {
		return duplicateChars;
	}

	public void setDuplicateChars(String duplicateChars) {
		this.duplicateChars = duplicateChars;
	}

	public String getReplaceSpaces() {
		return replaceSpaces;
	}

	public void setReplaceSpaces(String replaceSpaces) {
		this.replaceSpaces = replaceSpaces;
	}

	public Integer getLargestNumber() {
		return largestNumber;
	}

	public void setLargestNumber(Integer largestNumber) {
		this.largestNumber = largestNumber;
	}
	
	
	
}
