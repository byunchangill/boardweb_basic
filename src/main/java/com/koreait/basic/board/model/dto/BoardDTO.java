package com.koreait.basic.board.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private int iboard;
    private int page;
    private int startIdx;
    private int rowCnt;
    private int searchType;
    private String searchText;
}
