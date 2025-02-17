package logic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.AreaDAO;
import model.entity.AreaBean;

public class AreaLogic {

	public void executeSelectAllArea(HttpServletRequest request) {
		AreaDAO dao = new AreaDAO();
		List<AreaBean> area_list = dao.selectAllArea();
//		System.out.println(area_list.size());
		request.setAttribute("area_list", area_list);
	}

}
