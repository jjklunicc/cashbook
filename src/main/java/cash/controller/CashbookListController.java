package cash.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cash.model.CashbookDao;
import cash.vo.Cashbook;

/**
 * Servlet implementation class CashbookListController
 */
@WebServlet("/cashbookListByTag")
public class CashbookListController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session처리
		String memberId = "user";
		
		String word = request.getParameter("word");
	
		// 페이징 알고리즘
		int currentPage = 1;
		int rowPerPage = 10;
		int beginRow = 0;
		
		CashbookDao cashbookDao = new CashbookDao();
		List<Cashbook> list = cashbookDao.selectCashbookListByTag(memberId, word, beginRow, rowPerPage);
		
		request.setAttribute("word", word);
		request.setAttribute("list", list);
		
		// 뷰로 포워딩
		request.getRequestDispatcher("WEB-INF/view/cashbookListByTag.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
