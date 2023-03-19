package com.asianaidt.dutyfreeshop.dto;

import org.apache.ibatis.type.Alias;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("RegionDTO")
public class RegionDTO {
	int regionId;
	String regionNameKor;
	String regionNameEng;
	String regionCode;
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public String getRegionNameKor() {
		return regionNameKor;
	}
	public void setRegionNameKor(String regionNameKor) {
		this.regionNameKor = regionNameKor;
	}
	public String getRegionNameEng() {
		return regionNameEng;
	}
	public void setRegionNameEng(String regionNameEng) {
		this.regionNameEng = regionNameEng;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	
	
}