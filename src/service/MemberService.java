package service;

import java.util.List;

import dao.MemberDao;
import vo.Member;

public interface MemberService {
	//회원가입
	void join(Member member);
	//로그인
	boolean login(String id, String pwd);
	//탈퇴
	//단일 회원 목록조회
	Member findBy(String id);
	//회원 목록 조회	
	List<Member> getMembers();
}
