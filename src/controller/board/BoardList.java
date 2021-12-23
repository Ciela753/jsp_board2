package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;

@WebServlet({"/board/list", "/index.html"})
public class BoardList extends HttpServlet {
	private BoardService boardService = new BoardService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 
		System.out.println(boardService.list());
		req.setAttribute("boardList", boardService.list());
		req.getRequestDispatcher("board/list.jsp").forward(req, resp);
	}
	
}
