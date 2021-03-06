package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import vo.Criteria;
import vo.PageDTO;

@WebServlet({"/board/list", "/index.html"})
public class BoardList extends HttpServlet {
	private BoardService boardService = new BoardService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardService service = new BoardService();
		
		String pn = req.getParameter("pageNum");
		String am = req.getParameter("amount");
		if(pn==null) pn="1";
		if(am == null) am = "10";
		
		Criteria cri = new Criteria(Integer.parseInt(pn), Integer.parseInt(am));
		req.setAttribute("boardList", boardService.list(cri));
		req.setAttribute("page", new PageDTO(service.getCount(), cri));
		req.getRequestDispatcher("/WEB-INF/jsp/board/list.jsp").forward(req, resp);
		
//		System.out.println(boardService.list());
//		req.setAttribute("boardList", boardService.list());
//		req.getRequestDispatcher("/WEB-INF/jsp/board/list.jsp").forward(req, resp);
	}
	
}
