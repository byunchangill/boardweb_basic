package com.koreait.basic.board.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardEntity { // 테이블이랑 1 : 1 매칭
    private int iboard;
    private String title;
    private String ctnt;
    private int writer;
    private int hit;
    private String rdt;
    private String mdt;
}
