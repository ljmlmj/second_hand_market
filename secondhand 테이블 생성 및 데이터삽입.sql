-- 1. ��� ���̺� ����
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

-- 2. ī�װ� ���̺� ����
CREATE TABLE category (
    category_id     VARCHAR2(20) NOT NULL,
    CONSTRAINT pk_category_id PRIMARY KEY (category_id)
);

-- 3. �Ӹ��� ���̺� ����
CREATE TABLE head (
    head_id         VARCHAR2(20) NOT NULL,
    CONSTRAINT pk_head_id PRIMARY KEY (head_id)
);

-- 4. �Խñ� ���̺� ����
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

-- 5. �̹��� ���̺� ����
CREATE TABLE image (
    image_id        VARCHAR2(50) NOT NULL,
    board_no        NUMBER NOT NULL,
    CONSTRAINT fk_image_board_no FOREIGN KEY (board_no) REFERENCES board (board_no)
);

-- 6. ���ø���Ʈ ���̺� ����
CREATE TABLE wishlist (
    board_no        NUMBER NOT NULL,
    member_id       VARCHAR2(20) NOT NULL,
    wishdate        DATE DEFAULT SYSDATE NOT NULL,
    CONSTRAINT fk_wishlist_board_no FOREIGN KEY (board_no) REFERENCES board (board_no),
    CONSTRAINT fk_wishlist_member_id FOREIGN KEY (member_id) REFERENCES member (member_id)
);

-- 7. ��� ���̺� ����
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
-- ������ ����
CREATE SEQUENCE seq_board_no INCREMENT BY 1;
CREATE SEQUENCE seq_comment_id INCREMENT BY 1;

-------------------------------------------------------------------------------------
-- 1. ��������� 
insert into member (member_id, pwd, name, gender, phone, email, address, rank)
values ('admin', '1234', '������', '��', 01000000000, 'admin@admin.com', '�����', 0);
insert into member (member_id, pwd, name, gender, phone, email, address, rank)
values ('user01', '1234', 'ȸ��01', '��', 01000000000, 'user01@user01.com', '��⵵ �Ȼ��', 1);
insert into member (member_id, pwd, name, gender, phone, email, address, rank)
values ('user02', '1234', 'ȸ��02', '��', 01000000000, 'user03@user02.com', '��⵵ �����', 1);

-- 2. ī�װ� ������
insert into category values ('�ǸŰԽ���');
insert into category values ('���ŰԽ���');

-- 3. �Ӹ��� ������
insert into category values('�Ǹ���', '�ǸſϷ�', '������', '���ſϷ�');

-- 4. �Խñ� ������
insert into board (board_no, cateogry_id, head_id, writer, title, content)
values (seq_board_no.nextval, '�ǸŰԽ���', '�Ǹ���', 'user01', '�ǸŰԽ���1', '�ǸŰԽ���1');
insert into board (board_no, cateogry_id, head_id, writer, title, content)
values (seq_board_no.nextval, '�ǸŰԽ���', '�ǸſϷ�', 'user02', '�ǸŰԽ���2', '�ǸŰԽ���2');
insert into board (board_no, cateogry_id, head_id, writer, title, content)
values (seq_board_no.nextval, '���ŰԽ���', '������', 'user01', '���ŰԽ���1', '���ŰԽ���1');
insert into board (board_no, cateogry_id, head_id, writer, title, content)
values (seq_board_no.nextval, '���ŰԽ���', '���ſϷ�', 'user02', '���ŰԽ���2', '���ŰԽ���2');

-- 5. �̹��� ������
insert into image (image_id, board_no)
values ('10.png', 1);
insert into image (image_id, board_no)
values ('11.png', 2);
insert into image (image_id, board_no)
values ('12.png', 3);
insert into image (image_id, board_no)
values ('13.png', 4);

