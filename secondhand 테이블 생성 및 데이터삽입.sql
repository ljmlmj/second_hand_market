-- 1. 멤버 테이블 생성
CREATE TABLE member (
    member_id       VARCHAR2(20) NOT NULL,
    pwd             VARCHAR2(20) NOT NULL,
    name            VARCHAR2(20) NOT NULL,
    gender          CHAR(4) NOT NULL,
    phone           NUMBER(11),
    email           VARCHAR2(20),
    address         VARCHAR2(20),
    joindate        DATE DEFAULT SYSDATE,
    rank            NUMBER DEFAULT 1,
    CONSTRAINT pk_member_id PRIMARY KEY (member_id)
);

-- 2. 카테고리 테이블 생성
CREATE TABLE category (
    category_id     VARCHAR2(20) NOT NULL,
    CONSTRAINT pk_category_id PRIMARY KEY (category_id)
);

-- 3. 머리말 테이블 생성
CREATE TABLE head (
    head_id         VARCHAR2(20) NOT NULL,
    CONSTRAINT pk_head_id PRIMARY KEY (head_id)
);

-- 4. 게시글 테이블 생성
CREATE TABLE board (
    board_no        NUMBER NOT NULL,
    category_id     VARCHAR2(20) NOT NULL,
    head_id         VARCHAR2(20) NOT NULL,
    writer          VARCHAR2(20) NOT NULL,
    title           VARCHAR2(100) NOT NULL,
    content         VARCHAR2(4000),
    hit             NUMBER DEFAULT 0,
    wish_count      NUMBER DEFAULT 0,
    regdate         DATE DEFAULT SYSDATE,
    CONSTRAINT pk_board_no PRIMARY KEY (board_no),
    CONSTRAINT fk_board_category_id FOREIGN KEY (category_id) REFERENCES category (category_id),
    CONSTRAINT fk_board_head_id FOREIGN KEY (head_id) REFERENCES head (head_id),
    CONSTRAINT fk_board_writer FOREIGN KEY (writer) REFERENCES member (member_id)
);

-- 5. 이미지 테이블 생성
CREATE TABLE image (
    image_id        VARCHAR2(50) NOT NULL,
    board_no        NUMBER NOT NULL,
    CONSTRAINT fk_image_board_no FOREIGN KEY (board_no) REFERENCES board (board_no)
);

-- 6. 위시리스트 테이블 생성
CREATE TABLE wishlist (
    board_no        NUMBER NOT NULL,
    member_id       VARCHAR2(20) NOT NULL,
    wishdate        DATE DEFAULT SYSDATE NOT NULL,
    CONSTRAINT fk_wishlist_board_no FOREIGN KEY (board_no) REFERENCES board (board_no),
    CONSTRAINT fk_wishlist_member_id FOREIGN KEY (member_id) REFERENCES member (member_id)
);

-- 7. 댓글 테이블 생성
CREATE TABLE comment1 (
    comment_id      NUMBER NOT NULL,
    board_no        NUMBER NOT NULL,
    member_id       VARCHAR2(20) NOT NULL,
    comment1        VARCHAR2(500) NOT NULL,
    create_date     DATE DEFAULT SYSDATE,
    CONSTRAINT pk_comment_id PRIMARY KEY (comment_id),
    CONSTRAINT fk_comment_board_no FOREIGN KEY (board_no) REFERENCES board (board_no),
    CONSTRAINT fk_comment_member_id FOREIGN KEY (member_id) REFERENCES member (member_id)
);

-------------------------------------------------------------------------------------
-- 시퀀스 생성
CREATE SEQUENCE seq_board_no INCREMENT BY 1;
CREATE SEQUENCE seq_comment_id INCREMENT BY 1;

-------------------------------------------------------------------------------------
-- 1. 멤버데이터 
insert into member (member_id, pwd, name, gender, phone, email, address, rank)
values ('admin', '1234', '관리자', '남', 01000000000, 'admin@admin.com', '서울시', 0);
insert into member (member_id, pwd, name, gender, phone, email, address, rank)
values ('user01', '1234', '회원01', '여', 01000000000, 'user01@user01.com', '경기도 안산시', 1);
insert into member (member_id, pwd, name, gender, phone, email, address, rank)
values ('user02', '1234', '회원02', '남', 01000000000, 'user03@user02.com', '경기도 시흥시', 1);

-- 2. 카테고리 데이터
insert into category values ('판매게시판');
insert into category values ('구매게시판');

-- 3. 머리말 데이터
insert into category values('판매중', '판매완료', '구매중', '구매완료');

-- 4. 게시글 데이터
insert into board (board_no, cateogry_id, head_id, writer, title, content)
values (seq_board_no.nextval, '판매게시판', '판매중', 'user01', '판매게시판1', '판매게시판1');
insert into board (board_no, cateogry_id, head_id, writer, title, content)
values (seq_board_no.nextval, '판매게시판', '판매완료', 'user02', '판매게시판2', '판매게시판2');
insert into board (board_no, cateogry_id, head_id, writer, title, content)
values (seq_board_no.nextval, '구매게시판', '구매중', 'user01', '구매게시판1', '구매게시판1');
insert into board (board_no, cateogry_id, head_id, writer, title, content)
values (seq_board_no.nextval, '구매게시판', '구매완료', 'user02', '구매게시판2', '구매게시판2');

-- 5. 이미지 데이터
insert into image (image_id, board_no)
values ('10.png', 1);
insert into image (image_id, board_no)
values ('11.png', 2);
insert into image (image_id, board_no)
values ('12.png', 3);
insert into image (image_id, board_no)
values ('13.png', 4);

