package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import cash.vo.*;
import cash.model.*;
/**
 * Servlet implementation class SelectCashbookController
 */
@WebServlet("/calendarOne")
public class SelectCashbookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = "user";
		
		// 넘어온 값 저장
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		// 모델 호출(DAO 타켓 월의 수입/지출 데이터)
		List<Cashbook> list = new CashbookDao().selectCashbookListByDate(memberId, targetYear, targetMonth+1, targetDate);
		
		//뷰에 값 넘기기(request속성)
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("targetDate", targetDate);
		request.setAttribute("list", list);
		
		// 날짜에 해당하는 내역을 출력하는 뷰
		request.getRequestDispatcher("/WEB-INF/view/calendarOne.jsp").forward(request, response);
	}
}
