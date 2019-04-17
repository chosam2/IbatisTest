package kr.or.ddit.basic;

import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MemberIbatisTest {
	public static void main(String[] args) {
		// ibatis를 이용하여 DB자료를 처리하는 작업 순서
		// 1. ibatis의 환경설정파일을 읽어와 실행시킨다.
		try {
			// 1-1. xml 문서 읽어오기
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml"); // Resources읽어올 때 ibatis 패키지에 있는거 가져오기.

			// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);

			rd.close(); // Reader객체는 다 썼으니깐 반환

			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.

			// 2-1. isnert작업 연습
			System.out.println("insert작업 시작...");

			// 1) 저장할 데이터를 VO에 담는다.
			MemberVO mv = new MemberVO();

			mv.setMem_id("a001");
			mv.setMem_name("박찬호");
			mv.setMem_tel("1111-2222");
			mv.setMem_addr("대전시 서구 둔산동");

			// 2) SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다.
			// 형식) smc.insert("namespace값.id값", 파라미터클래스);
			// 		반환값 : 성공하면 null 이 반환된다.	// int가 아니고 null을 반환함.
			Object obj = smc.insert("memberTest.insertMember", mv);

			if (obj == null) {
				System.out.println("insert작업 성공");
			} else {
				System.out.println("insert작업 실패!!!");
			}

			System.out.println("-------------------------------------------------");

			// 2-2. update 연습
			System.out.println("update작업 시작...");

			MemberVO mv2 = new MemberVO();

			mv2.setMem_id("a001");
			mv2.setMem_name("박찬호");
			mv2.setMem_tel("1111-2222");
			mv2.setMem_addr("대전시 서구 둔산동");

			// update() 메서드는 반환값은 성공한 레코드 수이다.
			int cnt = smc.update("memberTest.updateMember", mv2);

			if (cnt > 0) {
				System.out.println("update 성공!");
			} else {
				System.out.println("update 실패!");
			}

			// 2-3 delete 연습
			System.out.println("delete 작업 시작...");

			// delete메서드 반환값 : 성공한 레코드수

			int cnt2 = smc.delete("memberTest.deleteMember", "a001");

			if (cnt2 > 0) {
				System.out.println("delete작업 성공.");
			} else {
				System.out.println("delete작업 실패.");
			}

			System.out.println("-------------------------------------------------");

			// select 연습

			/*
			// 1) 응답의 결과가 여러개일 경우
			System.out.println("select 연습 시작(결과가 여러개일 경우...)");
			List<MemberVO> memList;
			
			// 응답 결과가 여러개일 경우에는 queryForList메서드를 사용한다.
			// 이 메서드는 여러개의 레코드를 VO에 담은 후 이 VO데이터를 List에
			// 추가해 주는 작업을 자동으로 수행한다.
			memList = smc.queryForList("memberTest.getAllMemberList");
			
			for(MemberVO mv3 : memList) {
				System.out.println("ID : " + mv3.getMem_id());
				System.out.println("이름 : " + mv3.getMem_name());
				System.out.println("전화번호 : " + mv3.getMem_tel());
				System.out.println("주소 : " + mv3.getMem_addr());
				System.out.println("===================================");
			}
			
			System.out.println("출력 끝...");
			*/

			// 2) => 집계함수
			System.out.println("select 연습 시작(결과가  1건일 경우...)");

			// 응답결과가 1건인 것이 확실한 경우에는 queryForObject메서드를 사용한다.

			MemberVO mv4 = (MemberVO) smc.queryForObject("memberTest.getMember", "1");
			System.out.println("ID : " + mv4.getMem_id());
			System.out.println("이름 : " + mv4.getMem_name());
			System.out.println("전화번호 : " + mv4.getMem_tel());
			System.out.println("주소 : " + mv4.getMem_addr());
			System.out.println("===================================");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
