package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {

	// 사용할 DAO의 객체변수를 선언한다.
	private IMemberDao memDao;
	
	private static IMemberService service;
	
	
	private MemberServiceImpl() {
//		memDao = new MemberDaoImpl(); // DAO객체 생성
		memDao = MemberDaoImpl.getInstance();
	}
	
	public static IMemberService getInstance() {
		if(service == null) {
			service = new MemberServiceImpl();
		}
		return service;
	}
	
	// 각 메서드에서는 생성된 Dao객체를 이용하여 작업에 맞는 DAO객체의 메서드를 호출한다.
	@Override
	public int insertMember(MemberVO mv) {
		return memDao.insertMember(mv);
	}

	@Override
	public int deleteMember(String memId) {
		return memDao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO mv) {
		return memDao.updateMember(mv);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		return memDao.getAllMemberList();
	}

	@Override
	public boolean chkMemberInfo(String memId) {
		return memDao.chkMemberInfo(memId);
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		return memDao.getSearchMember(mv);
	}

}
