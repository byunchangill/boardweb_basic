package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.entity.BoardHeartEntity;
import com.koreait.basic.dao.BoardHeartDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/heart")
public class BoardHeartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String proc = req.getParameter("proc");
        int iuser = Utils.getLoginUserPk(req);
        int iboard = Utils.getParameterInt(req, "iboard");

        BoardHeartEntity entity = new BoardHeartEntity();
        entity.setIuser(iuser);
        entity.setIboard(iboard);

        switch (proc) {
            case "1":
                BoardHeartDAO.insBoardHeart(entity);
                break;
            case "2":
                BoardHeartDAO.delBoardHeart(entity);
                break;
        }
        res.sendRedirect("/board/detail?iboard=" + iboard);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
