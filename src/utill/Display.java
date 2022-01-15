package utill;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/display")
public class Display extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String filename = req.getParameter("filename");
		String saveDirectory = "C:\\Users\\chyn1\\OneDrive\\Desktop\\workspace\\upload";
		
		String filePath = saveDirectory + File.separator + filename;
		
		System.out.println(filePath);
		
		FileInputStream fis = new FileInputStream(filePath);
		
		String mimeType = getServletContext().getMimeType(filePath);
		
		if(mimeType==null) {
			mimeType = "application/octet-stream";
		}
		
		resp.setContentType(mimeType);
		
		ServletOutputStream sos = resp.getOutputStream();
		int b;
		
		//더이상 읽을 값이 없으면 -1리턴
		while((b = fis.read()) != -1) {
			sos.write(b);
		}
		fis.close();
		sos.close();
		
	}
	
}
