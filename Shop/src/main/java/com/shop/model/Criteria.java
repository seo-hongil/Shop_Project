package com.shop.model;

import lombok.Data;

@Data
public class Criteria {

    private int pageNum;		//현재 페이지 번호
    private int amount;			//페이지 표시 개수
    private String type;			//검색 타입
    private String keyword;	//검색 키워드
    
    /* Criteria 생성자 */
    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }
    
    /* Criteria 기본 생성자 */
    public Criteria(){
        this(1,10);
    }
    
    /* 검색 타입 데이터 배열 변환 */
    public String[] getTypeArr() {
        return type == null? new String[] {}:type.split("");
    }


}
