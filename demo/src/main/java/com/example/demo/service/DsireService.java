package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.DsireDataModel;
import com.example.demo.model.SyncStatus;
import com.example.demo.model.SyncStatus.ProgressEnum;
import com.example.demo.respository.DsireDataRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.log.Log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DsireService {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	private DsireDataRepository dsireDataRepository;

	@Autowired
	private SyncService syncService;

	@Value("${sync.batchSize}")
	int batchSize;

	@Async
	public void getDataAndStore() {

		SyncStatus lastSync = syncService.lastSyncStatus();
		if (lastSync.getProgress() == ProgressEnum.INPROGRESS || lastSync.getProgress().equals(ProgressEnum.STARTED)) {
			throw new IllegalCallerException("Sync already in progress!");
		}

		SyncStatus lastSuccessSync = syncService.lastSyncedOn();
		String apiUrl;
		if (lastSuccessSync == null) {
			apiUrl = "https://programs.dsireusa.org/api/v1/getprograms/json";
			// apiUrl =
			// "https://programs.dsireusa.org/api/v1/getprogramsbydate/20230101/20240101/json";
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String formattedDate = formatter.format(lastSuccessSync.getTriggeredOn());
			String currDate = formatter.format(new Date());

			apiUrl = "https://programs.dsireusa.org/api/v1/getprogramsbydate/" + formattedDate + "/" + currDate
					+ "/json";
		}
		SyncStatus syncData = new SyncStatus();
		
		syncData.setTriggeredOn(new Date());
		syncData.setProgress(ProgressEnum.STARTED);
		SyncStatus createdSync = syncService.createSyncData(syncData);


		System.out.println(apiUrl);
		System.out.println(batchSize);
		try {
			ResponseEntity<String> response = restTemplate().exchange(apiUrl, HttpMethod.GET, null, String.class);
			String responseBody = response.getBody();
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(responseBody);
			JsonNode dataArray = rootNode.get("data");

			int totalData = dataArray.size();
			System.out.println(totalData);
			int startIndex = 0;

			syncService.updateProgress(createdSync.getId(), ProgressEnum.INPROGRESS);

			while (startIndex < totalData) {
				int endIndex = Math.min(startIndex + batchSize, totalData);
				System.out.println(startIndex);
				System.out.println(endIndex);
				List<DsireDataModel> programDataList = new ArrayList<>();
				for (int i = startIndex; i < endIndex; i++) {
					DsireDataModel programData = objectMapper.treeToValue(dataArray.get(i), DsireDataModel.class);
					programDataList.add(programData);
				}
				dsireDataRepository.saveAll(programDataList);
				startIndex += batchSize;
			}
			syncService.updateProgress(createdSync.getId(), ProgressEnum.COMPLETED);
		} catch (Exception e) {
			syncService.updateProgress(createdSync.getId(), ProgressEnum.FAILED);
			e.printStackTrace();
		}
	}

	public List<DsireDataModel> getAllData(int skip, int limit) {
		Pageable pageable = PageRequest.of(skip, limit);
		return dsireDataRepository.findAll(pageable).getContent();
	}

	public DsireDataModel getDataById(Long id) {
		return dsireDataRepository.findById(id).orElseThrow();
	}

	public List<DsireDataModel> getDataByLastUpdate(String lastUpdate) {
		return dsireDataRepository.findByLastUpdate(lastUpdate);
	}

}
