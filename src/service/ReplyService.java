package service;

import java.util.List;

import dao.ReplyDao;
import vo.Reply;

public class ReplyService {
	private ReplyDao dao = new ReplyDao();
	
	public void write(Reply reply) {
		dao.insert(reply);
	}
	
	public List<Reply> list(Long bno) {
		return dao.list(bno);
	}
	
	public Reply get(Long rno) {
		return dao.select(rno);
	}
	
	public void delete(Long rno) {
		dao.delete(rno);
	}
}
