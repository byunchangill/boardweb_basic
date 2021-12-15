package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.dto.BoardDTO;
import com.koreait.basic.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int searchType = Utils.getParameterInt(req, "searchType", 0);
        String searchText = req.getParameter("searchText");
        int rowCnt = Utils.getParameterInt(req, "rowCnt", 5);
        int page = Utils.getParameterInt(req, "page", 1);

        BoardDTO param  = new BoardDTO();
        param.setSearchType(searchType);
        param.setSearchText(searchText);
        param.setRowCnt(rowCnt);
        param.setPage(page);
        int startIdx = (param.getPage() - 1) * param.getRowCnt();
        param.setStartIdx(startIdx);

        req.setAttribute("maxPageNum", BoardDAO.gatMaxPageNum(param));
        req.setAttribute("list", BoardDAO.selBoardList(param));

        Utils.displayView(req, res, "게시판", "/board/list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
