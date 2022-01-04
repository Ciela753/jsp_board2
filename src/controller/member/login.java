package controller.member;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import service.MemberServiceImpl;

@WebServlet("/login")
public class login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/jsp/member/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		String msg = "";
		String redirectUrl = "login";
		
		MemberService service = new MemberServiceImpl();
		
		boolean success = service.login(id, pwd);
		
		if(success) {
			HttpSession session = req.getSession();
			session.setAttribute("member", service.findBy(id));
			msg="성공";
			
			//아이디 저장
			Cookie cookie = new Cookie("saveId", id);
			cookie.setMaxAge(req.getParameter("saveId") == null ? 0 : 60 * 60 * 24 * 365);
			resp.addCookie(cookie);
			
			redirectUrl = "index.html";
		}
		else {
			msg = "로그인 실패";
		}
		
		resp.sendRedirect(redirectUrl+ "?msg="+URLEncoder.encode(msg, "utf-8"));
	}
	
}
