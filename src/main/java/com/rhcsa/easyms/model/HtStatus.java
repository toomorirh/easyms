package com.rhcsa.easyms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "htstatus")
public class HtStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "code")
	private String code;

	public HtStatus() {

	}

	public HtStatus(String code ) {
		this.code = code;
	}

	public long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	@Override
	public String toString() {
		return "HtStatus [id=" + id + ", code=" + code + "]";
	}
}
