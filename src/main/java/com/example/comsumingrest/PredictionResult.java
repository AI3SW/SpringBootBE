package com.example.comsumingrest;

public class PredictionResult {
	private final long id;
	private final String output;
	
	public PredictionResult(long id, String output) {
		this.id = id;
		this.output = output;
	}
	
	public long getId() {
		return id;
	}
	
	public String getOutput() {
		return output;
	}
}