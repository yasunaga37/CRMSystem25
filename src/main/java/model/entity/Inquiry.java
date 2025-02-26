package model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Inquiry implements Serializable {

	private int id;
	private int customer_id;
	private String customer_name;
	private String contact_person_name;
	private Timestamp inquiry_datetime;
	private String inquiry_contents;
	private String reply_contents;
	private String status_code;
	private String status_name;
	private String user_name;
	private int delete_flg;
	private Timestamp update_datetime;

	public Inquiry() {}
	
	public Inquiry(int id, int customer_id, String inquiry_contents,
			String reply_contents, String status_code) {
		this.id = id;
		this.customer_id = customer_id;
		this.inquiry_contents = inquiry_contents;
		this.reply_contents = reply_contents;
		this.status_code = status_code;
	}
	
	public Inquiry(int id, int customer_id, String customer_name, Timestamp inquiry_datetime, String inquiry_contents,
			String reply_contents, String status_code, String status_name, Timestamp update_datetime) {
		this.id = id;
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.inquiry_datetime = inquiry_datetime;
		this.inquiry_contents = inquiry_contents;
		this.reply_contents = reply_contents;
		this.status_code = status_code;
		this.status_name = status_name;
		this.update_datetime = update_datetime;
	}
	
	/**
	 * 既存の問合せオブジェクトの内容を「概略」に置き換える
	 * @param inquiry
	 * @param summary
	 */
	public Inquiry(Inquiry inquiry, String summary) {
		this.id = inquiry.getId();
		this.customer_id = inquiry.getCustomer_id();
		this.customer_name = inquiry.getCustomer_name();
		this.inquiry_datetime = inquiry.getInquiry_datetime();
		this.inquiry_contents = summary;
		this.reply_contents = inquiry.getReply_contents();
		this.status_code = inquiry.getStatus_code();
		this.status_name = inquiry.getStatus_name();
		this.update_datetime = inquiry.getUpdate_datetime();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public Timestamp getInquiry_datetime() {
		return inquiry_datetime;
	}

	public void setInquiryDatetime(Timestamp inquiry_datetime) {
		this.inquiry_datetime = inquiry_datetime;
	}

	public String getInquiry_contents() {
		return inquiry_contents;
	}

	public void setInquiry_contents(String inquiry_contents) {
		this.inquiry_contents = inquiry_contents;
	}

	public String getReply_contents() {
		return reply_contents;
	}

	public void setReply_contents(String reply_contents) {
		this.reply_contents = reply_contents;
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

	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public int getDelete_flg() {
		return delete_flg;
	}
	
	public void setDelete_flg(int delete_flg) {
		this.delete_flg = delete_flg;
	}
	
	public Timestamp getUpdate_datetime() {
		return update_datetime;
	}

	public void setUpdate_datetime(Timestamp update_datetime) {
		this.update_datetime = update_datetime;
	}

	public String getContact_person_name() {
		return contact_person_name;
	}

	public void setContact_person_name(String contact_person_name) {
		this.contact_person_name = contact_person_name;
	}

}
