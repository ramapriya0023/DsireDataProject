package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DsireDataModel;
import com.example.demo.response.MasterResponse;
import com.example.demo.service.DsireService;

@RestController
public class DsireController {

	@Autowired
	private DsireService dsireService;

	@PostMapping("/sync-data")
	public ResponseEntity<MasterResponse<String>> getDataAndStore() {
		try {
			
			dsireService.getDataAndStore();
			MasterResponse<String> response = new MasterResponse<>(true, HttpStatus.OK.value(),"Data fetching and storing process initiated.",null);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			MasterResponse<String>  response = new MasterResponse<>(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@GetMapping("/data")
	public ResponseEntity<MasterResponse<List<DsireDataModel>>> getAllData(@RequestParam(value = "skip", defaultValue = "0") int skip,
			@RequestParam(value = "limit", defaultValue = "100") int limit) {
		try {
			
			List<DsireDataModel> dsireData = dsireService.getAllData(skip, limit);
			
			MasterResponse<List<DsireDataModel>> response = new MasterResponse<>(true, HttpStatus.OK.value(),"Retrieved successfully", dsireData);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			MasterResponse<List<DsireDataModel>>  response = new MasterResponse<>(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@GetMapping("/data/{id}")
	public ResponseEntity<MasterResponse<DsireDataModel>> getDataById(@PathVariable Long id) {
		try {
		DsireDataModel data = dsireService.getDataById(id);
			MasterResponse<DsireDataModel> response= new MasterResponse<>(true, HttpStatus.OK.value(),"Retrieved successfully",data);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			MasterResponse<DsireDataModel>  response = new MasterResponse<>(false,HttpStatus.BAD_REQUEST.value(),e.getMessage(),null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
	}

	@GetMapping("/data/filter-by-date")
	public ResponseEntity<MasterResponse<List<DsireDataModel>>> getDataByLastUpdate(@RequestParam String lastUpdate) {
		try {
			List<DsireDataModel> data = dsireService.getDataByLastUpdate(lastUpdate);
			MasterResponse<List<DsireDataModel>> response= new MasterResponse<>(true, HttpStatus.OK.value(),"Retrieved successfully",data);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			MasterResponse<List<DsireDataModel>>  response = new MasterResponse<>(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage(),null);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

}
