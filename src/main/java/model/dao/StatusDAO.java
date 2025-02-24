package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.StatusBean;

public class StatusDAO {
	
	public List<StatusBean> selectAllStatus () throws SQLException {
		List<StatusBean> list = new ArrayList<StatusBean>();
		String sql = "SELECT * FROM m_status";
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery(sql)) {
			while (res.next()) {
				StatusBean status = new StatusBean();
				status.setStatus_name(res.getString("status_code"));
				status.setStatus_name(res.getString("status_name"));
				list.add(status);
			}
		} 
		return list;
	}

}
