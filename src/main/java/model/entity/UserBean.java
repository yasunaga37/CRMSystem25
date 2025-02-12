package model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserBean implements Serializable {

	private String user_id;
	private String password;
	private String user_name;
	private Timestamp update_datetime;

	public UserBean() {}

	public UserBean(String user_id, String password) {
		this.user_id = user_id;
		this.password = password;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Timestamp getUpdate_datetime() {
		return update_datetime;
	}

	public void setUpdate_datetime(Timestamp update_datetime) {
		this.update_datetime = update_datetime;
	}
}
