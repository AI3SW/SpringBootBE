package com.example.comsumingrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PredictionSource {
	private final long session_id;
	private final String img;
	
	public PredictionSource(long session_id, String img) {
		this.session_id = session_id;
		this.img = img;
	}
	
	public long getSession_id() {
		return session_id;
	}
	
	public String getInput() {
		return img;
	}
}