package utill;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;

@WebServlet("/download")
public class FileDownload extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getParameter("filename");
		String saveDirectory = "C:\\Users\\chyn1\\OneDrive\\Desktop\\workspace\\upload";
		
		String filePath = saveDirectory +File.separator + fileName;
		
		System.out.println(filePath);
		
		FileInputStream fis = new FileInputStream(filePath);
		
		String mimeType = getServletContext().getMimeType(filePath);
		
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		
		System.out.println(mimeType);
		
		String userAgent = req.getHeader("User-Agent");
		System.out.println(userAgent);
		
		BoardService service = new BoardService();
		
		if(userAgent.toLowerCase().contains("trigent")) {
			fileName = URLEncoder.encode(fileName, "utf-8").replace("\\+", "%20");
		} else {
			fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
		}
		
		resp.setContentType(mimeType);
		resp.setHeader("Content-DisPosition", "attachment; filename="+fileName);
		
		ServletOutputStream sos = resp.getOutputStream();
		int b;
		
		while((b=fis.read()) != -1) {
			sos.write(b);
		}
		
		fis.close();
		sos.close();
	}
	
}
