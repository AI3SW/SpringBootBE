package com.example.comsumingrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PredictionSource {
	private final String src_img;
	private final String ref_img;
	private final String ref_class;
	private final boolean align_face;
	
	public PredictionSource(String src_img, String ref_img, String ref_class, boolean align_face) {
		this.src_img = src_img;
		this.ref_img = ref_img;
		this.ref_class = ref_class;
		this.align_face = align_face;
	}
	
	public PredictionSource(String src_img, String ref_img, String ref_class) {
		this.src_img = src_img;
		this.ref_img = ref_img;
		this.ref_class = ref_class;
		this.align_face = false;
	}
	

	public String getSrc_Img() {
		return src_img;
	}
	
	public String getRef_Img() {
		return ref_img;
	}
	
	public String getRef_Class() {
		return ref_class;
	}
	
	public boolean getAlign_Face() {
		return align_face;
	}
}