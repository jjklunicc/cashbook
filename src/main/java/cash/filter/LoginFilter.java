
package cash.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter extends HttpFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		if (session.getAttribute("loginMember") == null && !req.getRequestURI().endsWith("/login") 
			&& !req.getRequestURI().endsWith("/addMember") && !req.getRequestURI().endsWith("/style.css")
			&& !req.getRequestURI().contains("img")) {
			HttpServletResponse rep = (HttpServletResponse) response;
			rep.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		request.setCharacterEncoding("utf-8");

		chain.doFilter(request, response);
	}

}
