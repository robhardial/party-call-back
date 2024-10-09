package com.partycall.partycallback.dto;

import java.util.Date;

import lombok.Data;

@Data
public class SavedFileDTO {
    
    private String originalFileName;
	private String generatedFileName;
	private String generatedUrl;
	private Date uploadedAt;

}
