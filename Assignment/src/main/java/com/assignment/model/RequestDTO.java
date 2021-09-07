package com.assignment.model;

import java.util.Arrays;

public class RequestDTO {

	private Integer id;
	
	private String findDuplicates;
	
	private String whiteSpacesGalore;
	
	private boolean shouldProcess;
	
	private Integer[] numbers;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFindDuplicates() {
		return findDuplicates;
	}

	public void setFindDuplicates(String findDuplicates) {
		this.findDuplicates = findDuplicates;
	}

	public String getWhiteSpacesGalore() {
		return whiteSpacesGalore;
	}

	public void setWhiteSpacesGalore(String whiteSpacesGalore) {
		this.whiteSpacesGalore = whiteSpacesGalore;
	}

	public boolean isShouldProcess() {
		return shouldProcess;
	}

	public void setShouldProcess(boolean shouldProcess) {
		this.shouldProcess = shouldProcess;
	}

	public Integer[] getNumbers() {
		return numbers;
	}

	public void setNumbers(Integer[] numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return "RequestDTO [id=" + id + ", findDuplicates=" + findDuplicates + ", whiteSpacesGalore="
				+ whiteSpacesGalore + ", shouldProcess=" + shouldProcess + ", numbers=" + Arrays.toString(numbers)
				+ "]";
	}

	
}
