package controller.gallery;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import net.coobird.thumbnailator.Thumbnailator;
import service.BoardService;
import utill.MyFileRenamePolicy;
import vo.Attach;
import vo.Board;
import vo.Member;

@WebServlet("/gallery/write")
public class GalleryWrite extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/WEB-INF/jsp/board/write.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		// TODO Auto-generated method stub
		
		//파일을 저장할 경로 지정
		String saveDirectory = "C:\\Users\\chyn1\\OneDrive\\Desktop\\workspace\\upload";
		String path = getPath();
		
		//파일을 저장할 경로 생성
		File uploadPath = new File(saveDirectory+File.separator+path);
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		
		//파일의 크기
		int maxPostSize = 10*1024*1024;
		//파일의 인코딩 타입
		String encoding = "utf-8";
		//저장될 파일의 이름(pos붙여서)
		FileRenamePolicy policy = new MyFileRenamePolicy();
		
		//파일 업로드 선언과 완료
		MultipartRequest multipartRequest = new MultipartRequest(req, uploadPath.getAbsolutePath(), maxPostSize, encoding, policy);
		
		//업로드된 파일의 이름 반환
		Enumeration<String> files = multipartRequest.getFileNames();
		
		List<Attach> attachs = new ArrayList<Attach>();
		while(files.hasMoreElements()) {
			//파일 이름 얻어오기
			String file = files.nextElement();
			String uuid = multipartRequest.getFilesystemName(file);
			if(uuid==null) continue;
			String origin = multipartRequest.getOriginalFileName(file);
			
			Attach attach = new Attach(uuid, origin, null, path);
			attachs.add(attach);
			
			//썸네일
			FileInputStream fis = new FileInputStream(uploadPath.getAbsolutePath()+"\\"+uuid);
			FileOutputStream fos = new FileOutputStream(uploadPath.getAbsolutePath()+"\\s_"+uuid);
			Thumbnailator.createThumbnail(fis, fos, 250, 250);
		}
		
		attachs.forEach(System.out::println);
		
		String title = multipartRequest.getParameter("title");
		String id=((Member)req.getSession().getAttribute("member")).getId();
		
		Board board = new Board(title, "", id);
		board.setAttachs(attachs);
		
		new BoardService().write(board);
		
		resp.sendRedirect("list");
		
	}
	
	private String getPath() {
		return new SimpleDateFormat("yyMMdd").format(new Date());
	}
}
