package cash.vo;

public class Member {
	private String memberId;
	private String memberPw;
	private String createdate;
	private String updatedate;
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Member(String memberId, String memberPw, String createdate, String updatedate) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.createdate = createdate;
		this.updatedate = updatedate;
	}

	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPw=" + memberPw + ", createdate=" + createdate
				+ ", updatedate=" + updatedate + "]";
	}
	
}
