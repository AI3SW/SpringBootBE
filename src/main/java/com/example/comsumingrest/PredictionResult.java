package com.example.comsumingrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PredictionResult {
	//private final long id;
	private final String output_img;
	
	
	public PredictionResult(String output_img) {
		this.output_img = output_img;
	}
	
	
	public String getOutput() {
		return output_img;
	}
	
	@Override
	public String toString() {
		return "Result{" +
				", output=" + output_img + 
				'}';
	}
}