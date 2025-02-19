package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.CustomerBean;

public class CustomerDAO {
	
	/**
	 * 顧客一覧リストを取得する
	 * @return 顧客一覧リスト
	 * @throws SQLException
	 */
	public List<CustomerBean> selectAllCustomers () throws SQLException {
		List<CustomerBean> list = new ArrayList<CustomerBean>();
		String sql = "SELECT"
				+ "  cust.customer_id, "
				+ "  cust.customer_name, "
				+ "  cust.customer_name_kana, "
				+ "  cust.postal_code, "
				+ "  cust.adress, "
				+ "  area.area_name, "
				+ "  cust.contact_person_name, "
				+ "  cust.contact_person_name_kana, "
				+ "  cust.contact_person_tel, "
				+ "  usr.user_name, "
				+ "  cust.delete_flg, "
				+ "  cust.update_datetime "
				+ "FROM"
				+ "  m_area area "
				+ "  INNER JOIN m_customer cust "
				+ "    ON cust.area_code = area.area_code "
				+ "  INNER JOIN m_user usr "
				+ "    ON cust.user_id = usr.user_id "
				+ "ORDER BY"
				+ "  cust.customer_id";
		
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement()) {
			ResultSet res = stmt.executeQuery(sql);
			
			while (res.next()) {
				CustomerBean cstm = new CustomerBean();
				cstm.setCustomer_id(res.getInt("customer_id"));
				cstm.setCustomer_name(res.getString("customer_name"));
				cstm.setCustomer_name_kana(res.getString("customer_name_kana"));
				cstm.setPostal_code(res.getString("postal_code"));
				cstm.setAdress(res.getString("adress"));
				cstm.setArea_name(res.getString("area_name"));
				cstm.setContact_person_name(res.getString("contact_person_name"));
				cstm.setContact_person_name_kana(res.getString("contact_person_name_kana"));
				cstm.setContact_person_tel(res.getString("contact_person_tel"));
				cstm.setUser_name(res.getString("user_name"));
				cstm.setDelete_flg(res.getInt("delete_flg"));
				cstm.setUpdate_datetime(res.getTimestamp("update_datetime"));
				list.add(cstm);
			}
		} 
		return list;		
	}
	
	/**
	 * 顧客IDから顧客情報を取得する
	 * @param id
	 * @return CustomerBean
	 * @throws SQLException
	 */
	public CustomerBean searchCustomerByID (int id) throws SQLException {
		CustomerBean customer = null;
		String sql = "SELECT"
				+ "  cust.customer_id, "
				+ "  cust.customer_name, "
				+ "  cust.customer_name_kana, "
				+ "  cust.postal_code, "
				+ "  cust.adress, "
				+ "  area.area_name, "
				+ "  cust.contact_person_name, "
				+ "  cust.contact_person_name_kana, "
				+ "  cust.contact_person_tel, "
				+ "  usr.user_name, "
				+ "  cust.update_datetime "
				+ "FROM"
				+ "  m_area area "
				+ "  INNER JOIN m_customer cust "
				+ "    ON cust.area_code = area.area_code "
				+ "  INNER JOIN m_user usr "
				+ "    ON cust.user_id = usr.user_id "
				+ " WHERE "
				+ " cust.customer_id = ? "
				+ " ORDER BY "
				+ "  cust.customer_id " ;
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				customer = new CustomerBean();
				customer.setCustomer_id(res.getInt("customer_id"));
				customer.setCustomer_name(res.getString("customer_name"));
				customer.setCustomer_name_kana(res.getString("customer_name_kana"));
				customer.setPostal_code(res.getString("postal_code"));
				customer.setAdress(res.getString("adress"));
				customer.setArea_name(res.getString("area_name"));
				customer.setContact_person_name(res.getString("contact_person_name"));
				customer.setContact_person_name_kana(res.getString("contact_person_name_kana"));
				customer.setContact_person_tel(res.getString("contact_person_tel"));
				customer.setUser_name(res.getString("user_name"));
				customer.setUpdate_datetime(res.getTimestamp("update_datetime"));
			}
		} 
		return customer; 
	}

	/**
	 * 既存の顧客データを更新する
	 * @param customer
	 * @return int 更新したレコード件数
	 * @throws SQLException
	 */
	public int update(CustomerBean customer) throws SQLException {
		int count = 0;
		String sql = "UPDATE m_customer "
						+ " SET "
						+ " customer_name = ?,  "
						+ "customer_name_kana = ?, "
						+ " postal_code = ?, adress = ?, area_code = ?, "
						+ " contact_person_name = ?, contact_person_name_kana = ?, contact_person_tel = ?, "
						+ " user_id = ? "
						+ " WHERE customer_id = ?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, customer.getCustomer_name());
			pstmt.setString(2, customer.getCustomer_name_kana());
			pstmt.setString(3, customer.getPostal_code());
			pstmt.setString(4, customer.getAdress());
			pstmt.setString(5, customer.getArea_code());
			pstmt.setString(6, customer.getContact_person_name());
			pstmt.setString(7, customer.getContact_person_name_kana());
			pstmt.setString(8, customer.getContact_person_tel());
			pstmt.setString(9, customer.getUser_id());
			pstmt.setInt(10, customer.getCustomer_id());
			count = pstmt.executeUpdate();
		} 
		return count;		
	}
	
	/**
	 * 顧客レコードの削除フラグを「1」(削除済み)に設定する
	 * @param id 顧客ID
	 * @return int 更新済みレコード数
	 * @throws SQLException
	 */
	public int deleteCustomerByID(int id) throws SQLException {
		int count = 0;
		String sql = "UPDATE m_customer "
				+ " SET "
				+ " delete_flg = 1 "
				+ " WHERE customer_id = ?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			count = pstmt.executeUpdate();
		}
		return count;
	}
	
}
