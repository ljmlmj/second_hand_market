package com.javalab.vo;

public class CommentVo {

	// 멤버변수
	private int comment_id;		// 댓글번호
	private int board_no;		// 게시글번호
	private String member_id;	// 회원아이디
	private String comment1;		// 댓글내용
	private String create_date;	// 댓글등록일
	private int comment_group;	// 댓글그룹번호
	private int comment_order;	// 댓글그룹내순서
	private int comment_indent;	// 댓글들여쓰기
	
	private String pageNum = "1";		// 페이지 번호
	private Integer listCount = 6;		// 1페이지 당 보여줄 댓글 수
	private Integer pagePerBlock = 10;  // 한번에 보여질 페이지번호 갯수
	
	public CommentVo() {
		super();
	}

	public CommentVo(int comment_id, int board_no, String member_id, String comment, String create_date, int comment_group,
			int comment_order, int comment_indent) {
		super();
		this.comment_id = comment_id;
		this.board_no = board_no;
		this.member_id = member_id;
		this.comment1 = comment;
		this.create_date = create_date;
		this.comment_group = comment_group;
		this.comment_order = comment_order;
		this.comment_indent = comment_indent;
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
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

	public String getComment1() {
		return comment1;
	}

	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public int getComment_group() {
		return comment_group;
	}

	public void setComment_group(int comment_group) {
		this.comment_group = comment_group;
	}

	public int getComment_order() {
		return comment_order;
	}

	public void setComment_order(int comment_order) {
		this.comment_order = comment_order;
	}

	public int getComment_indent() {
		return comment_indent;
	}

	public void setComment_indent(int comment_indent) {
		this.comment_indent = comment_indent;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getListCount() {
		return listCount;
	}

	public void setListCount(Integer listCount) {
		this.listCount = listCount;
	}

	public Integer getPagePerBlock() {
		return pagePerBlock;
	}

	public void setPagePerBlock(Integer pagePerBlock) {
		this.pagePerBlock = pagePerBlock;
	}
	
	
} // class e
