package service;

import java.util.List;

import dao.BoardDao;
import vo.Attach;
import vo.Board;
import vo.Criteria;

public class BoardService {
	private BoardDao dao = new BoardDao();
	
	public Long write(Board board) {
		Long bno = dao.insert(board);
		for(Attach attach:board.getAttachs()) {
			attach.setBno(bno);
			dao.writeAttach(attach);
		}
		return bno;
	}
	public List<Board> list(Criteria cri) {
		List<Board> list = dao.list(cri);
		list.forEach(b->b.setAttachs(dao.readAttach(b.getBno())));
		return list;
	}
	public List<Board> list() {
		return dao.list();
	}
	
	public Board get(Long bno) {
		return dao.get(bno);
	}
	public void register(Board board) {
		dao.register(board);
	}
	public void modify(Board board) {
		dao.modify(board);
	}
	public void remove(Long bno) {
		dao.remove(bno);
	}
	public int getCount() {
		return dao.getCount();
	}
	public int getCount(Criteria cri) {
		return dao.getCount();
	}

}
