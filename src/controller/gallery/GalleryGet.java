package controller.gallery;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;

@WebServlet("/gallery/detail")
public class GalleryGet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long bno = Long.parseLong(req.getParameter("bno"));
		
		req.setAttribute("galleryGet", new BoardService().get(bno));
		req.getRequestDispatcher("/WEB-INF/jsp/gallery/get.jsp").forward(req, resp);
	}
	
}
