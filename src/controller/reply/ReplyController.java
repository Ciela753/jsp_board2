package controller.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.ReplyService;
import vo.Reply;

@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private ReplyService service = new ReplyService();
	private Gson gson = new Gson();
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long rno = Long.parseLong(req.getParameter("rno"));
		System.out.println(rno);
		service.delete(rno);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long rno = Long.parseLong(req.getParameter("rno"));
		Reply reply = service.get(rno);
		resp.setContentType("utf-8");
		resp.getWriter().print(gson.toJson(reply));
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jsonData = req.getParameter("jsonData");
		Reply reply = gson.fromJson(jsonData, Reply.class);
		System.out.println("컨트롤러상 "+ reply);
		service.write(reply);
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
	
	

}
