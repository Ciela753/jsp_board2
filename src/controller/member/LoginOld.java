package controller.member;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.Member;

@WebServlet("/loginOld")
public class LoginOld extends HttpServlet{
	List<Member> members = new ArrayList<Member>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		String msg="";
		String redirectUrl = "login";
		
		if(findBy(id, pwd)==null) {
			msg = "아이디 없음";
		} else if(findBy(id, pwd) == null) {
			msg = "비밀번호 불일치";
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("id", id);
			msg="성공";
			redirectUrl = "list.jsp";
		}
		
		resp.sendRedirect(redirectUrl+"?msg="+URLEncoder.encode(msg, "utf-8"));
	}
	
	private Member findBy(String id) {
		Member member = null;
		for(Member m : members) {
			if(m.getId().equals(id)) {
				member = m;
				break;
			}
		}
		return member;
	}
	
	private Member findBy(String id, String pwd) {
		Member member = null;
		for(Member m : members) {
			if(m.getId().equals(id) && m.getPwd().equals(pwd)) {
				member = m;
				break;
			}
		}
		return member;
	}
	
}
