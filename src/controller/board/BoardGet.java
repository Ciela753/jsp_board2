package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;

@WebServlet("/board/get")
public class BoardGet extends HttpServlet {
	private BoardService boardService = new BoardService();  
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long bno = (long) Integer.parseInt(req.getParameter("bno"));
		req.setAttribute("boardGet", boardService.get(bno));
		req.getRequestDispatcher("board/get.jsp").forward(req, resp);
	}
	
}
