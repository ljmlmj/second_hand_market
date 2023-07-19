package com.javalab.vo;

public class ImageVo {
	private String image_id;
	private int board_no;
	
	public ImageVo(String image_id, int board_no) {
		super();
		this.image_id = image_id;
		this.board_no = board_no;
	}

	public ImageVo(String image_id) {
		super();
		this.image_id = image_id;
	}
	
	public String getImage_id() {
		return image_id;
	}

	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public ImageVo() {
		super();
		
	}
	
	@Override
	public String toString() {
		return "ImageVo [image_id=" + image_id + ", board_no=" + board_no + "]";
	}

}
