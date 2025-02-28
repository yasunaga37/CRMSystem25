package logic;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.StatusDAO;
import model.entity.StatusBean;

public class StatusLogic {
	
	public void getStatusList(HttpServletRequest request) {
		StatusDAO dao = new StatusDAO();
		try {
			List<StatusBean> list = dao.selectAllStatus();
			request.setAttribute("status_list", list);
//			System.out.println(list.size());
		} catch (SQLException e) {
			System.out.println("StatusLogic#getStatusList() ステータスリストの取得に失敗しました。");
			e.printStackTrace();
		}
		
	}

}
