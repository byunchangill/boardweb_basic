package com.koreait.basic.board.model.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardVO {
    private int iboard;
    private String title;
    private String ctnt;
    private int writer;
    private int hit;
    private String rdt;
    private String mdt;
    private String profileImg;
    private String writerNm;
    private int cnt;
}
