package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.InquiryLogic;
import logic.StatusLogic;
import model.entity.InquiryBean;
import util.LoginUserChecker;

/**
 * Servlet implementation class InquiryEditServlet
 */
@WebServlet("/inquiry_edit")
public class InquiryEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryEditServlet() {
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
		// パラメータの取得
		String action= request.getParameter("action");
		// 遷移先URLの取得
		String url = null;
		if ("goto_customer_detail".equals(action)) {
			// お問合せ編集画面へ遷移		
			url = gotoInquiryEditPage(request);
		} else if ("inquiry_edit_execute".equals(action)) {
			// 編集済み問合せ情報登録後にお問合せ情報詳細画面へ遷移
			url = updateInquiry(request);
		}
		// ディスパッチ
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	private String gotoInquiryEditPage(HttpServletRequest request) {
		// 問合せIDの取得
		int inquiry_id = Integer.parseInt(request.getParameter("inquiry_id"));
		// 問合せオブジェクトを取得し、リクエストスコープに格納する
		InquiryLogic logic = new InquiryLogic();
		InquiryBean inquiry = logic.getInquiryByID(inquiry_id);
//		int i_id = inquiry.getId();
//		String customer_name = inquiry.getCustomer_name();
//		Timestamp inquiry_datetime = inquiry.getInquiry_datetime();
//		String status_code = inquiry.getStatus_code();
//		System.out.println(i_id +"  " + customer_name +"  " + inquiry_datetime +"  " + status_code);
		request.setAttribute("inquiry", inquiry);		
		// ステータスリストを取得し、リクエストスコープに格納する
		StatusLogic slogic = new StatusLogic();
		slogic.getStatusList(request);
		return "WEB-INF/view/inquiry_edit.jsp";
	}
	
	/**
	 * 該当問合せ情報の更新処理を行い、該当問合せ情報詳細画面へ遷移する
	 * @param request
	 * @return
	 */
	private String updateInquiry(HttpServletRequest request) {
		// 問合せIDの取得
		int inquiry_id = Integer.parseInt(request.getParameter("inquiry_id"));
		// 該当問合せオブジェクトの更新処理
		InquiryLogic ilogic = new InquiryLogic();
		int count = ilogic.editInquiry(request, inquiry_id);
		// 該当問合せオブジェクトを取得して、遷移先を設定する
		InquiryBean inquiry = ilogic.getInquiryByID(inquiry_id);
		request.setAttribute("inquiry", inquiry);
		return "WEB-INF/view/inquiry_show.jsp";	
	}

}
