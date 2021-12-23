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
		if(dao.get(bno) ==null)
			System.out.println("서비스 문제");
		else 
			System.out.println("컨트롤러 정상적 실행");
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
