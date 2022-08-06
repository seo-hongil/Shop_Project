/* 고객 테이블*/
CREATE TABLE SHOP_MEMBER(
  memberId VARCHAR2(50) PRIMARY KEY,
  memberPw VARCHAR2(100) NOT NULL,
  memberName VARCHAR2(30) NOT NULL,
  memberMail VARCHAR2(100) NOT NULL,
  memberAddr1 VARCHAR2(100) NOT NULL,
  memberAddr2 VARCHAR2(100) NOT NULL,
  memberAddr3 VARCHAR2(100) NOT NULL,
  adminCk NUMBER NOT NULL,
  regDate DATE NOT NULL,
  money number NOT NULL,
  point number NOT NULL
);


/* 상품 테이블 */
create table SHOP_GOODS(
    goodId number generated as identity (start with 1) primary key,
    goodName varchar2(50)   not null,
    cateCode varchar2(30),
    postedDate date not null,
    goodPrice number not null,
    goodStock number not null,
    goodDiscount number(2,2),
    goodIntro clob,
    goodContents clob,
    regDate date default sysdate,
    updateDate date default sysdate
);


/* 카테고리 테이블 */
create table SHOP_bcate(
    tier number(1) not null,
    cateName varchar2(30) not null,
    cateCode varchar2(30) not null,
    cateParent varchar2(30) ,
    primary key(cateCode),
    foreign key(cateParent) references shop_bcate(cateCode) 
);


   /* 카테고리 데이터 */
insert into shop_bcate(tier, cateName, cateCode) values (1, '남성', '100000');
    insert into shop_bcate(tier, cateName, cateCode, cateParent) values (2, '상의', '101000','100000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '셔츠', '101001','101000');
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '(반팔)티셔츠', '101002','101000');
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '맨투맨/후드티', '101003','101000');
    insert into shop_bcate(tier, cateName, cateCode, cateParent) values (2, '하의', '102000','100000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '청바지', '102001','102000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '슬랙스', '102002','102000');   
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '면바지', '102003','102000');
   insert into shop_bcate(tier, cateName, cateCode, cateParent) values (2, '일체형', '103000','100000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '점프슈트', '103001','103000'); 
    insert into shop_bcate(tier, cateName, cateCode, cateParent) values (2, '아우터', '104000','100000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '재킷', '104001','104000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '패딩', '104002','104000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '바람막이/가디건', '104003','104000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '무스탕', '104004','104000');    
    insert into shop_bcate(tier, cateName, cateCode, cateParent) values (2, 'ACC', '105000','100000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '모자', '105001','105000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '시계', '105002','105000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '목걸이', '105003','105000');    
    insert into shop_bcate(tier, cateName, cateCode, cateParent) values (2, '신발', '106000','100000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '운동화', '106001','106000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '구두', '106002','106000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '슬리퍼', '106003','106000');    
insert into shop_bcate(tier, cateName, cateCode) values (1, '여성', '200000');
    insert into shop_bcate(tier, cateName, cateCode, cateParent) values (2, '상의', '201000','200000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '블라우스', '201001','201000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '셔츠', '201002','201000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '(반팔)티셔츠', '201003','201000');
                insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '맨투맨/후드티', '201004','201000'); 
    insert into shop_bcate(tier, cateName, cateCode, cateParent) values (2, '하의', '202000','200000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '청바지', '202001','202000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '슬랙스', '202002','202000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '면바지', '202003','202000');
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '치마', '202004','202000'); 
    insert into shop_bcate(tier, cateName, cateCode, cateParent) values (2, '일체형', '203000','200000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '원피스', '203001','203000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '점프슈트', '203002','203000');     
   insert into shop_bcate(tier, cateName, cateCode, cateParent) values (2, '아우터', '204000','200000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '재킷', '204001','204000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '패딩', '204002','204000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '바람막이/가디건', '204003','204000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '무스탕', '204004','204000');
    insert into shop_bcate(tier, cateName, cateCode, cateParent) values (2, 'ACC', '205000','200000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '모자', '205001','205000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '시계', '205002','205000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '목걸이', '205003','205000');  
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '패션양말', '205004','205000');  
    insert into shop_bcate(tier, cateName, cateCode, cateParent) values (2, '신발', '206000','200000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '운동화', '206001','206000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '구두', '206002','206000');    
        insert into shop_bcate(tier, cateName, cateCode, cateParent) values (3, '슬리퍼', '206003','206000');    
             
        
/* 상품 테이블에 카테고리 외래키 추가 */
	alter table shop_goods add foreign key (cateCode) references shop_bcate(cateCode);        

/* 상품 이미지 테이블 */
	 create table shop_image(
        uuid varchar2(100)not null primary key,
        goodId number,
        fileName varchar2(100) not null,
        uploadPath varchar2(200) not null,
        foreign key (goodId) references shop_goods(goodId)
    );
    
    /* 공지사항 테이블 */
    create table shop_notice(
    noticeId number generated as identity (start with 1) primary key,	
    noticeTitle varchar2(100) not null,
    noticeContents clob,
    noticeDate date default sysdate
    );
    
    