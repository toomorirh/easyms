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

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping("/api")
@Tag(name = "HttpResponse State Machine", description = "Set Response Code. getRequest Response Saved Code. ")
public class ResponseCodeController {

	@Autowired
	HtstatusRepository htstatusRepository;

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

	@GetMapping("/code/{id}")
	public ResponseEntity<HtStatus> getHtStatus(@PathVariable("id") long id) {
		Optional<HtStatus> htStatusData = htstatusRepository.findById(id);

		if (htStatusData.isPresent()) {
			return new ResponseEntity<>(htStatusData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

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
