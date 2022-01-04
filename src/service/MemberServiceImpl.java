package service;

import java.util.List;

import dao.BoardDao;
import dao.MemberDao;
import vo.Member;

public class MemberServiceImpl implements MemberService {
	MemberDao dao = new MemberDao();
	@Override
	public void join(Member member) {
		// TODO Auto-generated method stub
		dao.join(member);
	}

	@Override
	public boolean login(String id, String pwd) {
		// TODO Auto-generated method stub
		return dao.login(id, pwd);
	}

	@Override
	public Member findBy(String id) {
		// TODO Auto-generated method stub
		return dao.findBy(id);
	}

	@Override
	public List<Member> getMembers() {
		// TODO Auto-generated method stub
		return dao.getMembers();
	}
	
}
