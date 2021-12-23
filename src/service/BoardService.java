package service;

import java.util.List;

import dao.BoardDao;
import vo.Board;

public class BoardService {
	private BoardDao dao = new BoardDao();
	
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

}
