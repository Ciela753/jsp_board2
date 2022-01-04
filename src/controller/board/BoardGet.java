package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.Board;

@WebServlet("/board/get")
public class BoardGet extends HttpServlet {
	private BoardService boardService = new BoardService();  
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long bno = Long.parseLong(req.getParameter("bno"));
		Board boardGet = boardService.get(bno);
		req.setAttribute("boardGet", boardGet);
		req.getRequestDispatcher("/WEB-INF/jsp/board/get.jsp").forward(req, resp);
	}
}
