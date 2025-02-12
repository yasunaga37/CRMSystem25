package model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class CustomerBean implements Serializable {

	private int customer_id;
	private String customer_name;
	private String customer_name_kana;
	private String postal_code;
	private String adress;
	private String area_code;
	private String area_name;
	private String contact_person_name;
	private String contact_person_name_kana;
	private String contact_person_tel;
	private String user_id;
	private String user_name;
	private int delete_flg;
	private Timestamp update_datetime;

	public CustomerBean() {	}	
	
	public CustomerBean(String name, String nameKana, String postalCode, String adress, String areaCode,
			String contactPersonName, String contactPersonNameKana, String contactPersonTel, String userID) {
		this.customer_name = name;
		this.customer_name_kana = nameKana;
		this.postal_code = postalCode;
		this.adress = adress;
		this.area_code = areaCode;
		this.contact_person_name = contactPersonName;
		this.contact_person_name_kana = contactPersonNameKana;
		this.contact_person_tel = contactPersonTel;
		this.user_id = userID;
	}

	public CustomerBean(int id, String name, String nameKana, String postalCode, String adress, String areaCode,
			String contactPersonName, String contactPersonNameKana, String contactPersonTel, String userID) {
		this.customer_id = id;
		this.customer_name = name;
		this.customer_name_kana = nameKana;
		this.postal_code = postalCode;
		this.adress = adress;
		this.area_code = areaCode;
		this.contact_person_name = contactPersonName;
		this.contact_person_name_kana = contactPersonNameKana;
		this.contact_person_tel = contactPersonTel;
		this.user_id = userID;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int id) {
		this.customer_id = id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String name) {
		this.customer_name = name;
	}

	public String getCustomer_name_kana() {
		return customer_name_kana;
	}

	public void setCustomer_name_kana(String nameKana) {
		this.customer_name_kana = nameKana;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postalCode) {
		this.postal_code = postalCode;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

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

	public String getContact_person_name() {
		return contact_person_name;
	}

	public void setContact_person_name(String contactPersonName) {
		this.contact_person_name = contactPersonName;
	}

	public String getContact_person_name_kana() {
		return contact_person_name_kana;
	}

	public void setContact_person_name_kana(String contactPersonNameKana) {
		this.contact_person_name_kana = contactPersonNameKana;
	}

	public String getContact_person_tel() {
		return contact_person_tel;
	}

	public void setContact_person_tel(String contactPersonTel) {
		this.contact_person_tel = contactPersonTel;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String userID) {
		this.user_id = userID;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String userName) {
		this.user_name = userName;
	}

	public int getDelete_flg() {
		return delete_flg;
	}

	public void setDelete_flg(int deleteFlg) {
		this.delete_flg = deleteFlg;
	}

	public Timestamp getUpdate_datetime() {
		return update_datetime;
	}

	public void setUpdate_datetime(Timestamp updateDatetime) {
		this.update_datetime = updateDatetime;
	}

}
