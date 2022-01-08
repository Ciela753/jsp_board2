package vo;

import javax.servlet.annotation.WebServlet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@WebServlet
@NoArgsConstructor
@AllArgsConstructor
public class Attach {
	private String uuid;
	private String origin;
	private Long bno;
	private String path;
	
	public Attach(String uuid, String origin, Long bno) {
		super();
		this.uuid = uuid;
		this.origin = origin;
		this.bno = bno;
	}
	
	
}
