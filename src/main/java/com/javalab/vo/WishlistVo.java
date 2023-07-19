package com.javalab.vo;

import java.sql.Date;

public class WishlistVo extends BoardVo {
	// 멤버변수
	private int board_no;		// 게시글번호
	private String member_id;	// 회원아이디
	private Date wishdate;		// 찜한날짜
	
	public WishlistVo() {
		super();
	}
	
	public WishlistVo(int board_no, String member_id, Date wishdate) {
		super();
		this.board_no = board_no;
		this.member_id = member_id;
		this.wishdate = wishdate;
	}

	public WishlistVo(int board_no, String category_id, String head_id, String writer, String title, String content,
			int hit, int wish_count, String regdate) {
		super(board_no, category_id, head_id, writer, title, content, hit, wish_count, regdate);
	}

	public WishlistVo(String member_id, int board_no) {
		super();
		this.board_no = board_no;
		this.member_id = member_id;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public Date getWishdate() {
		return wishdate;
	}

	public void setWishdate(Date wishdate) {
		this.wishdate = wishdate;
	}


	@Override
	public String toString() {
		return "WishlistVo [board_no=" + board_no + ", member_id=" + member_id + ", wishdate=" + wishdate
				+ wishdate + "]";
	}


} //class e
