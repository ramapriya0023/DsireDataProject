package com.example.demo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "sync_data")
public class SyncStatus {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "progress")
	private ProgressEnum progress;
	
	@Column(name = "lastSyncedOn")
	private Date lastSyncedOn;
	

	@Column(name = "triggeredOn")
	private Date triggeredOn;
	
	@Column(name = "lastUpdated")
	private Date lastUpdated;
	
	public enum ProgressEnum {
		STARTED, INPROGRESS, COMPLETED, FAILED
	}
	
	public Date getLastUpdated() {
		return lastUpdated;
	}
	
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public ProgressEnum getProgress() {
		return progress;
	}

	public void setProgress(ProgressEnum progress) {
		this.progress = progress;
	}

	public Date getLastSyncedOn() {
		return lastSyncedOn;
	}

	public void setLastSyncedOn(Date lastSyncedOn) {
		this.lastSyncedOn = lastSyncedOn;
	}

	public Date getTriggeredOn() {
		return triggeredOn;
	}

	public void setTriggeredOn(Date triggeredOn) {
		this.triggeredOn = triggeredOn;
	}

}
