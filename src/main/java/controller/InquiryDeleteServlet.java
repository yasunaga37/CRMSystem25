package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.InquiryLogic;
import logic.UserLogic;
import model.entity.InquiryBean;
import util.LoginUserChecker;

/**
 * Servlet implementation class InquiryDeleteServlet
 */
@WebServlet("/inquiry_delete")
public class InquiryDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインチェック
		LoginUserChecker.checkLoginUser(request, response);
		// リクエストパラメータの取得
		String action = request.getParameter("action");
		// 遷移先URLの取得
		String url = null;
		if ("goto_delete".equals(action)) {
			url = gotoDelete(request);
		} else if ("execute_delete".equals(action)) {
			url = executeDelete(request);
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	/**
	 * 問合せ情報削除確認画面へ遷移する
	 * @param request
	 * @return 遷移先URL
	 */
	private String gotoDelete(HttpServletRequest request) {
		int inquiry_id = Integer.parseInt(request.getParameter("inquiry_id"));
//		System.out.println("inquiry_delete. " + inquiry_id);
		InquiryLogic logic = new InquiryLogic();
		InquiryBean inquiry = logic.getInquiryByID(inquiry_id);
		request.setAttribute("inquiry", inquiry);
		request.setAttribute("action", "以下のお問合せを削除します");
		return "WEB-INF/view/inquiry_show.jsp";	
	}
	
	private String executeDelete(HttpServletRequest request) {
		int inquiry_id = Integer.parseInt(request.getParameter("inquiry_id"));
		System.out.println("inquiry_delete. " + inquiry_id);
		UserLogic ulogic = new UserLogic();
		boolean userFLG = ulogic.userChrck(request);
		if (userFLG) {
			InquiryLogic logic = new InquiryLogic();
			int count = logic.deleteInquiry(inquiry_id);
			if (count <= 0) {
				System.out.println("InquiryDeleteServlet#executeDelete 削除に失敗しました。");
				request.setAttribute("delete_execute", "以下のお問合せ情報を削除出来ませんでした。");
			} else {
				request.setAttribute("delete_execute", "以下のお問合せ情報を削除しました。");
			}
			InquiryBean inquiry = logic.getInquiryByID(inquiry_id);
			request.setAttribute("inquiry", inquiry);
		} else {
			InquiryLogic logic = new InquiryLogic();
			InquiryBean inquiry = logic.getInquiryByID(inquiry_id);
			request.setAttribute("inquiry", inquiry);
			request.setAttribute("delete_error", "ユーザーIDとパスワードが一致しません。");
		}		
		return "WEB-INF/view/inquiry_show.jsp";	
	}

}
