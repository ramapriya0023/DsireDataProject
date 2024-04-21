package com.example.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DsireDataModel;

@Repository
public interface DsireDataRepository extends JpaRepository<DsireDataModel, Long> {

	@Query("SELECT d FROM DsireDataModel d WHERE d.lastUpdate = :lastUpdate")
	List<DsireDataModel> findByLastUpdate(@Param("lastUpdate") String lastUpdate);

}




