package model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class AreaBean implements Serializable {

	private String area_code;
	private String area_name;
	private Timestamp update_datetime;

	public AreaBean() {	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String areaCode) {
		this.area_code = areaCode;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String areaName) {
		this.area_name = areaName;
	}

	public Timestamp getUpdate_datetime() {
		return update_datetime;
	}

	public void setUpdate_datetime(Timestamp updateDatetime) {
		this.update_datetime = updateDatetime;
	}

}
