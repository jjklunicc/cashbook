package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cash.model.CashbookDao;

/**
 * Servlet implementation class RemoveCashbookController
 */
@WebServlet("/removeCashbook")
public class RemoveCashbookController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		System.out.println("cashbookNo : " + cashbookNo);
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		CashbookDao cashbookDao = new CashbookDao();
		int row = cashbookDao.deleteCashbook(cashbookNo);
		System.out.println(row);
		response.sendRedirect(request.getContextPath() + "/calendarOne?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&targetDate=" + targetDate);
	}

}
