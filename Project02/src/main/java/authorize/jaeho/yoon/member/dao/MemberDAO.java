package authorize.jaeho.yoon.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import authorize.jaeho.yoon.member.vo.Member;


@Repository
public class MemberDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	public Member selectOne(String employee_id){
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		Member customer = null;
		try {
			customer = mapper.selectOne(employee_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

}
