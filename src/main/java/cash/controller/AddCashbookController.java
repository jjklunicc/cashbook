package cash.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cash.model.CashbookDao;
import cash.model.HashtagDao;
import cash.vo.Cashbook;
import cash.vo.Hashtag;
import cash.vo.Member;

import java.util.*;

/**
 * Servlet implementation class AddCashbookController
 */
@WebServlet("/addCashbook")
public class AddCashbookController extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 로그인 안되어 있으면 로그인으로
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		// 요청값 유효성 검사
		if(request.getParameter("category") == null 
		|| request.getParameter("targetYear") == null
		|| request.getParameter("targetMonth") == null
		|| request.getParameter("targetDate") == null
		|| request.getParameter("price") == null
		|| request.getParameter("memo") == null) {
			request.getRequestDispatcher("/WEB-INF/view/calendarOne.jsp").forward(request, response);
			return;
		}
		
		// 요청값 저장
		Member member = (Member)session.getAttribute("loginMember");
		String memberId = member.getMemberId();
		String category = request.getParameter("category");
		String targetYear = request.getParameter("targetYear");
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		String targetDate = request.getParameter("targetDate");
		String cashbookDate = targetYear + "-" + (targetMonth+1) + "-" + targetDate;
		int price = Integer.parseInt(request.getParameter("price"));
		String memo = request.getParameter("memo");
		
		// 해시태그만 따로 저장해줄 배열 선언
		Set<String> hashWord = new HashSet<>();
		
		// 해시태그 부분 수정된 메모 저장할 변수 선언(db에 저장될 메모)
		String toSaveMemo = "";
		
		// 메모 값 해시태그 제대로 안되어있는것들 수정 & 해시태그만 따로 저장
		String[] memoWord = memo.split(" ");
		for(int i = 0; i < memoWord.length; i++) {
			String word = memoWord[i];
			if(word.startsWith("#")) {
				// 단어만 남게 #을 없애줌
				word = word.replace("#", "");
				// 빈태그가 아니면 해시태그 목록에 저장 & #다시 붙여줌
				if(!word.equals("")) {
					hashWord.add(word);
					word = "#" + word;
				}
			}
			toSaveMemo += word;
			if(!word.equals("") && i != memoWord.length -1) {
				toSaveMemo += " ";
			}
		}
		
		// db에 저장할 cashbook데이터 입력
		Cashbook cashbook = new Cashbook();
		cashbook.setMemberId(memberId);
		cashbook.setCategory(category);
		cashbook.setCashbookDate(cashbookDate);
		cashbook.setPrice(price);
		cashbook.setMemo(toSaveMemo);
		
		// cashbook 디비에 저장 후 cashbookNo값 가져옴(해시태그 저장을 위해!)
		CashbookDao cashbookDao = new CashbookDao();
		int cashbookNo = cashbookDao.insertCashbook(cashbook);
		
		// hashtag 디비에 hashtag를 저장해줌
		HashtagDao hashtagDao = new HashtagDao();
		
		for(String word : hashWord) {
			Hashtag hashtag = new Hashtag();
			hashtag.setCashbookNo(cashbookNo);
			hashtag.setWord(word);
			hashtagDao.insertHashtag(hashtag);
		}
		
		response.sendRedirect(request.getContextPath() + "/calendarOne?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&targetDate=" + targetDate);
	}

}
