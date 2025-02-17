package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.AreaBean;

public class AreaDAO {
	
	public List<AreaBean> selectAllArea () {
		List<AreaBean> areaList = new ArrayList<AreaBean>();
		String sql = "SELECT * FROM m_area";
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement()) {
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				AreaBean area = new AreaBean();
				area.setArea_code(res.getString("area_code"));
				area.setArea_name(res.getString("area_name"));
				area.setUpdate_datetime(res.getTimestamp("update_datetime"));
				areaList.add(area);
			}
		} catch (SQLException e) {
			System.out.println("地区リストが取得できませんでした。");
			e.printStackTrace();
		}
		return areaList;
	}

}
