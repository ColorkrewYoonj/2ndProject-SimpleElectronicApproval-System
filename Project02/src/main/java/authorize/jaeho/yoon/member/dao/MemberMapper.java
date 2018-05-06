package authorize.jaeho.yoon.member.dao;

import authorize.jaeho.yoon.member.vo.Member;

public interface MemberMapper {
	public Member selectOne(String employee_id);
}
