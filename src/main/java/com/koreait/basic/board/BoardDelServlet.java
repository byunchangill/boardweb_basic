package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.entity.BoardEntity;
import com.koreait.basic.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/del")
public class BoardDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int iboard = Utils.getParameterInt(req, "iboard");
        int writer = Utils.getLoginUserPk(req); // 로그아웃 상태에서 주소창에서 호작질 해도 오류 안터지는게 하는 메소드.

        BoardEntity entity = new BoardEntity();
        entity.setIboard(iboard);
        entity.setWriter(writer);

        int result = BoardDAO.delBoard(entity);
        switch (result) {
            case 1:
                res.sendRedirect("/board/list");
                return;
            default:
                req.setAttribute("err", "글 삭제에 실패하였습니다.");
                req.getRequestDispatcher("/board/detail?iboard=" + iboard).forward(req, res);
                return;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
