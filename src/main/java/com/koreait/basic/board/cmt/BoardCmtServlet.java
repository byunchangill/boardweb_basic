package com.koreait.basic.board.cmt;

import com.google.gson.Gson;
import com.koreait.basic.Utils;
import com.koreait.basic.board.model.dto.BoardCmtDTO;
import com.koreait.basic.board.model.vo.BoardCmtVO;
import com.koreait.basic.dao.BoardCmtDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/board/cmt")
public class BoardCmtServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 리스트 (R)
        int iboard = Utils.getParameterInt(req,"iboard");
        BoardCmtDTO cmtParam = new BoardCmtDTO();
        cmtParam.setIbaord(iboard);

        List<BoardCmtVO> list = BoardCmtDAO.selBoardCmtList(cmtParam);

        Gson gson = new Gson();

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        PrintWriter out = res.getWriter();
        out.print(gson.toJson(list));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 등록(C), 수정(U), 삭제(D)
    }
}
