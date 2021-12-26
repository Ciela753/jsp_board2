package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.Board;

@WebServlet("/board/register")
public class BoardRegister extends HttpServlet{
	BoardService boardService = new BoardService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("board/regist.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Board board = new Board();
		board.setTitle(req.getParameter("title"));
		board.setContent(req.getParameter("content"));
		board.setWriter(req.getParameter("writer"));
		boardService.register(board);
		
		resp.sendRedirect("list");
	}
	
}
