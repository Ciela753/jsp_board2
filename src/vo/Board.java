package vo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	
	private List<Attach> attachs;
	
	
	
	public Board(Long bno, String title, Date regDate, String writer) {
		super();
		this.bno=bno;
		this.title= title;
		this.regDate=regDate;
		this.writer= writer;
	}
	
	public Board(Long bno, String title, String content) {
		super();
		this.bno=bno;
		this.title=title;
		this.content=content;
		
	}
	
	public Board(String title, String content, String writer) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	
	@Override
	public String toString() {
		return "Board [bno=" + bno + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regDate="
				+ regDate + "]";
	}

	public Board(Long bno, String title, String content, String writer, Date regDate) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regDate = regDate;
	}
}
