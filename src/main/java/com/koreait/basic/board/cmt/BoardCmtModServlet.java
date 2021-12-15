package com.koreait.basic.board.cmt;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.entity.BoardCmtEntity;
import com.koreait.basic.dao.BoardCmtDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/cmt/mod")
public class BoardCmtModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int iboard = Utils.getParameterInt(req, "iboard");
        int writer = Utils.getLoginUserPk(req);
        String ctnt = req.getParameter("ctnt");
        int icmt = Utils.getParameterInt(req, "icmt");

        BoardCmtEntity entity = new BoardCmtEntity();
        entity.setIboard(iboard);
        entity.setCtnt(ctnt);

        entity.setWriter(writer);
        entity.setIcmt(icmt);

        int result = BoardCmtDAO.upBoardCmt(entity);
        switch (result) {
            case 1:
                res.sendRedirect("/board/detail?nohits=1&iboard=" + iboard);
                break;
        }
    }
}
