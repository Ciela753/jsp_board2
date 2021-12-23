package controller.board;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.Board;

@WebServlet("/board/modify")
public class BoardModify extends HttpServlet {
	BoardService boardService = new BoardService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("board/modify.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long bno = (long) Integer.parseInt(req.getParameter("bno"));
		Board board = new Board();
		board.setBno(bno);
		board.setTitle(req.getParameter("title"));
		board.setContent(req.getParameter("content"));
		board.setWriter(req.getParameter("write"));
		boardService.modify(board);
 	}
	
}
