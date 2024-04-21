package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "dsire_data")
public class DsireDataModel {

	@Id
	@JsonProperty("ProgramId")
    @Column(name = "program_id")
    private Integer programId;

	@JsonProperty("Code")
    private String code;
	
	@JsonProperty("State")
    private String state;
    
	@JsonProperty("ImplementingSectorId")
    @Column(name = "implementing_sector_id")
    private Integer implementingSectorId;
    
	@JsonProperty("ImplementingSectorName")
    @Column(name = "implementing_sector_name")
    private String implementingSectorName;
    
	@JsonProperty("CategoryId")
    @Column(name = "category_id")
    private Integer categoryId;
    
	@JsonProperty("CategoryName")
    @Column(name = "category_name")
    private String categoryName;
    
	@JsonProperty("TypeId")
    @Column(name = "type_id")
    private Integer typeId;
    
	@JsonProperty("TypeName")
    @Column(name = "type_name")
    private String typeName;
    
	@JsonProperty("Name")
    private String name;
    
	@JsonProperty("LastUpdate")
    @Column(name = "last_update")
    private String lastUpdate;
    
	@JsonProperty("WebsiteUrl")
    @Column(name = "website_url")
    private String websiteUrl;
    
	@JsonProperty("Administrator")
    private String administrator;
	
	@JsonProperty("FundingSource")
    private String fundingSource;
	
	@JsonProperty("Budget")
    private String budget;
    
	@JsonProperty("StartDate")
    @Column(name = "start_date")
    private String startDate;
    
	@JsonProperty("EndDate")
    @Column(name = "end_date")
    private String endDate;
	
    
    public Integer getProgramId() {
		return programId;
	}

	public void setProgramId(Integer programId) {
		this.programId = programId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getImplementingSectorId() {
		return implementingSectorId;
	}

	public void setImplementingSectorId(Integer implementingSectorId) {
		this.implementingSectorId = implementingSectorId;
	}

	public String getImplementingSectorName() {
		return implementingSectorName;
	}

	public void setImplementingSectorName(String implementingSectorName) {
		this.implementingSectorName = implementingSectorName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getAdministrator() {
		return administrator;
	}

	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}

	public String getFundingSource() {
		return fundingSource;
	}

	public void setFundingSource(String fundingSource) {
		this.fundingSource = fundingSource;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


}



