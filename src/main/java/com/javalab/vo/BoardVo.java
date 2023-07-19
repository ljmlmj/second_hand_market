package com.javalab.vo;

public class BoardVo {

	// 멤버변수
	private int board_no;		// 게시글번호
	private String category_id;		// 카테고리명
	private String head_id;		// 머리말
	private String writer;		// 작성자
	private String title;		// 제목
	private String content;		// 내용
	private int hit;			// 조회수
	private int wish_count;		// 찜횟수
	private String regdate;		// 등록일
	
	private String pageNum = "1";		// 페이지 번호
	private Integer listCount = 10;		// 1페이지 당 보여줄 게시물 수
	private Integer pagePerBlock = 10;  // 한번에 보여질 페이지번호 갯수
	private String searchText = "";	//조회 키워드
	
	public BoardVo() {
		super();
	}

	public BoardVo(int board_no, String category_id, String head_id, String writer, String title, String content, int hit,
			int wish_count, String regdate) {
		super();
		this.board_no = board_no;
		this.category_id = category_id;
		this.head_id = head_id;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.wish_count = wish_count;
		this.regdate = regdate;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getHead_id() {
		return head_id;
	}

	public void setHead_id(String head_id) {
		this.head_id = head_id;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getWish_count() {
		return wish_count;
	}

	public void setWish_count(int wish_count) {
		this.wish_count = wish_count;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
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

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	@Override
	public String toString() {
		return "BoardVo [board_no=" + board_no + ", category_id=" + category_id + ", head_id=" + head_id + ", writer="
				+ writer + ", title=" + title + ", content=" + content + ", hit=" + hit + ", wish_count=" + wish_count
				+ ", regdate=" + regdate + "]";
	}

	
	
}
