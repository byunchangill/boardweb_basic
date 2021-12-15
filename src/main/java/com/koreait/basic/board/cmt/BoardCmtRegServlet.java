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

@WebServlet("/board/cmt/reg")
public class BoardCmtRegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int iboard = Utils.getParameterInt(req, "iboard");
        String ctnt = req.getParameter("ctnt");
        int writer = Utils.getLoginUserPk(req);

        BoardCmtEntity entity = new BoardCmtEntity();
        entity.setIboard(iboard);
        entity.setCtnt(ctnt);
        entity.setWriter(writer);

        int result = BoardCmtDAO.insBoardCmt(entity);
        switch (result) {
            case 1:
                res.sendRedirect("/board/detail?nohits=1&iboard=" + iboard);
                break;
        }
    }
}
