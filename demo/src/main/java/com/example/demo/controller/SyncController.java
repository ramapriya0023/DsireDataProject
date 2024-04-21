package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.SyncStatus;
import com.example.demo.response.MasterResponse;
import com.example.demo.service.SyncService;

import jakarta.persistence.EntityNotFoundException;

@RestController
public class SyncController {

	@Autowired
	private SyncService syncService;
	
	@GetMapping("/sync")
	public ResponseEntity<MasterResponse<List<SyncStatus>>> getAllSyncData() {
		try {
			
			List<SyncStatus> syncDetailsList = syncService.getAllSyncData();
			MasterResponse<List<SyncStatus>> response = new MasterResponse<>(true, HttpStatus.OK.value(),
					"Retrieved successfully", syncDetailsList);
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			MasterResponse<List<SyncStatus>> response = new MasterResponse<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					e.getMessage(), null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@GetMapping("/sync/{id}")
	public ResponseEntity<MasterResponse<Optional<SyncStatus>>> getSyncById(@PathVariable long id){
		try {
			Optional<SyncStatus> syncData = syncService.getSyncById(id);
			MasterResponse<Optional<SyncStatus>> response = new MasterResponse<>(true, HttpStatus.OK.value(),
					"Retrieved successfully", syncData);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			MasterResponse<Optional<SyncStatus>> response = new MasterResponse<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),
					e.getMessage(), null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@GetMapping("/sync/lastsyncedon")
	public ResponseEntity<MasterResponse<SyncStatus>> getLastSyncedOn(){
		try {
			SyncStatus lastSyncData = syncService.lastSyncedOn();
			MasterResponse<SyncStatus> response = new MasterResponse<>(true, HttpStatus.OK.value(),"Retrieved successfully", lastSyncData);
			return ResponseEntity.ok(response);
		} catch (EntityNotFoundException e) {
			MasterResponse<SyncStatus> response = new MasterResponse<>(false, HttpStatus.NOT_FOUND.value(),
					e.getMessage(), null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		catch (Exception e) {
			MasterResponse<SyncStatus> response = new MasterResponse<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@GetMapping("/sync/syncstatus")
	public ResponseEntity<MasterResponse<SyncStatus>> getLastSyncStatus(){
		try {
			SyncStatus syncData = syncService.lastSyncStatus();
			MasterResponse<SyncStatus> response = new MasterResponse<>(true, HttpStatus.OK.value(),"Retrieved successfully", syncData);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			MasterResponse<SyncStatus> response = new MasterResponse<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
