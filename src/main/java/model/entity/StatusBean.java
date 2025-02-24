package model.entity;

import java.io.Serializable;

public class StatusBean implements Serializable {

	private String status_code;
	private String status_name;

	public StatusBean() {
	}

	public StatusBean(String status_code, String status_name) {
		this.status_code = status_code;
		this.status_name = status_name;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}

}
