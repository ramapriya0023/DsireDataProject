package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SyncStatus;
import com.example.demo.model.SyncStatus.ProgressEnum;

@Repository
public interface SyncRepository extends JpaRepository<SyncStatus, Long> {

	SyncStatus findFirstByOrderByIdDesc();
	
	@Query("SELECT s FROM SyncStatus s WHERE s.lastSyncedOn = (SELECT MAX(s2.lastSyncedOn) FROM SyncStatus s2 WHERE s2.progress = :progress)")
	SyncStatus findLastSuccessfullSync(@Param("progress") ProgressEnum progress);

}
