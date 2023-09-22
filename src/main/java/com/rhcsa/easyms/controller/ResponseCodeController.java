package com.rhcsa.easyms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhcsa.easyms.model.HtStatus;
import com.rhcsa.easyms.repository.HtstatusRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping("/api")
@Tag(name = "HttpResponse State Machine", description = "任意のHttpStatusCodeを設定後、GetApiを通して任意のステータスを返却します ")
public class ResponseCodeController {

	@Autowired
	HtstatusRepository htstatusRepository;

	@Operation(summary = "ステータスコード返却",description = "登録した最新のHttpStatusCodeを返却します")
	@GetMapping("/code/status")
	public ResponseEntity<String> getHtstatus() {
		try {
			List<HtStatus> htstatus = new ArrayList<HtStatus>();

			htstatusRepository.findAll().forEach(htstatus::add);
	
			if (htstatus.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>("get code success :" + htstatus.get(htstatus.size() -1).getCode(),
				HttpStatusCode.valueOf(Integer.parseInt(htstatus.get(htstatus.size() -1).getCode())));

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "ステータスコード返却（ID指定)",description = "登録した特定のHttpStatusCodeをIDを指定して取得(過去の状態の取得)")
	@GetMapping("/code/{id}")
	public ResponseEntity<HtStatus> getHtStatus(@PathVariable("id") long id) {
		Optional<HtStatus> htStatusData = htstatusRepository.findById(id);

		if (htStatusData.isPresent()) {
			return new ResponseEntity<>(htStatusData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "ステータスコード設定",description = "HttpStatusCodeを登録します")
	@PostMapping("/code")
	public ResponseEntity<HtStatus> createHtStatus(@RequestBody HtStatus htstatus) {
		try {
			HtStatus _htstatus = htstatusRepository
					.save(new HtStatus(htstatus.getCode()));
			return new ResponseEntity<>(_htstatus, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "システムクリア",description = "登録したすべてのHttpStatusCodeをクリアします")
	@DeleteMapping("/code")
	public ResponseEntity<HttpStatus> deleteHtstatus() {
		try {
			htstatusRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
