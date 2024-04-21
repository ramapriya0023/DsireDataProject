package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SyncStatus;
import com.example.demo.model.SyncStatus.ProgressEnum;
import com.example.demo.respository.SyncRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SyncService {

	@Autowired
	private SyncRepository syncRepository;

	public List<SyncStatus> getAllSyncData() {
		return syncRepository.findAll();
	}

	public Optional<SyncStatus> getSyncById(long id) {
		if(syncRepository.findById(id) == null) {
			throw new IllegalArgumentException(id + " Id not found!"); 
		}
		return syncRepository.findById(id);
	}
	
	public SyncStatus lastSyncedOn() {
		return syncRepository.findLastSuccessfullSync(ProgressEnum.COMPLETED);
	}
	
	public SyncStatus lastSyncStatus() {
		return syncRepository.findFirstByOrderByIdDesc();
	}
	
	public SyncStatus createSyncData(SyncStatus syncData) {
        return syncRepository.save(syncData);
    }
	
	public SyncStatus updateProgress(long id, ProgressEnum progress) {
		SyncStatus syncData = syncRepository.findById(id).orElse(null);
		if(syncData ==null) {
			throw new IllegalArgumentException("ID not found");
		}
		syncData.setProgress(progress);
		syncData.setLastUpdated(new Date());
		if(progress == ProgressEnum.COMPLETED) {
			syncData.setLastSyncedOn(new Date());
		}
		syncRepository.save(syncData);
		return syncData;
	}
}
