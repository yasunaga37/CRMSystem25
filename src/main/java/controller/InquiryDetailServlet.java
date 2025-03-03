package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.InquiryLogic;
import model.entity.InquiryBean;

/**
 * Servlet implementation class InquiryDetailServlet
 */
@WebServlet("/inquiry_detail")
public class InquiryDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InquiryDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 顧客情報詳細画面の「お問合せ」リンク押下時に呼び出されて、お問合せ詳細画面に遷移する
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("inquiry_id"));
//		System.out.println("InquiryDetailServlet#doGet()   " + id);
		InquiryLogic logic = new InquiryLogic();
		InquiryBean inquiry = logic.getInquiryByID(id);
		request.setAttribute("inquiry", inquiry);
		request.setAttribute("action", "お問合せ詳細");
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/inquiry_show.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
