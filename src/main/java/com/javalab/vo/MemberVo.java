package com.javalab.vo;

public class MemberVo {

	// 멤버변수
	private String member_id;	// 회원아이디
	private String pwd;			// 비밀번호
	private String name;		// 이름
	private String gender;		// 성별
	private int phone;			// 전화번호
	private String email;		// 이메일
	private String address;		// 주소
	private String joindate;	// 가입일
	private int rank;			// 권한
	
	public MemberVo() {
		super();
	}

	public MemberVo(String member_id, String pwd, String name, String gender, int phone, String email, String address,
			String joindate, int rank) {
		super();
		this.member_id = member_id;
		this.pwd = pwd;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.joindate = joindate;
		this.rank = rank;
	}

	public MemberVo(String member_id, String pwd) {
		super();
		this.member_id = member_id;
		this.pwd = pwd;
	}

	public MemberVo(String member_id, String pwd, String name) {
		super();
		this.member_id = member_id;
		this.pwd = pwd;
		this.name = name;
	}

	public MemberVo(String member_id, String pwd, String name, String gender, int phone, String email,
			String address, String joindate) {
		super();
		this.member_id = member_id;
		this.pwd = pwd;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.joindate = joindate;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}



}
