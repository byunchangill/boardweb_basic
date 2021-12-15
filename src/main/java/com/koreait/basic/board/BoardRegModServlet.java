package com.koreait.basic.board;

import com.koreait.basic.Utils;
import com.koreait.basic.board.model.dto.BoardDTO;
import com.koreait.basic.board.model.entity.BoardEntity;
import com.koreait.basic.board.model.vo.BoardVO;
import com.koreait.basic.dao.BoardDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/regmod")
public class BoardRegModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int iboard = Utils.getParameterInt(req, "iboard");
        String title = "글등록";

        if(iboard > 0) { //수정
            title = "글수정";
            BoardDTO param = new BoardDTO();
            param.setIboard(iboard);
            BoardVO data = BoardDAO.selBoardDetail(param);
            req.setAttribute("data", data);
        }
        Utils.displayView(req, res, title, "/board/regmod");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int loginUserPk = Utils.getLoginUserPk(req);
        if(loginUserPk == 0) {
            res.sendRedirect("/user/login");
            return;
        }

        int iboard = Utils.getParameterInt(req, "iboard");
        String title = req.getParameter("title");
        title = title.replace("<", "&lt;").replace(">", "&gt;");
        String ctnt = req.getParameter("ctnt");
        ctnt = ctnt.replace("<", "&lt;").replace(">", "&gt;");

        int result = 0;
        BoardEntity entity = new BoardEntity();
        entity.setTitle(title);
        entity.setCtnt(ctnt);
        entity.setWriter(loginUserPk);

        if(iboard == 0) { // 등록
            result = BoardDAO.insBoardWithPk(entity);
           // req.setAttribute("err", "등록에 실패하였습니다.");
        } else { // 수정
            entity.setIboard(iboard);
            result = BoardDAO.updateBoard(entity);
           // req.setAttribute("err", "수정에 실패하였습니다.");
        }

        switch (result) {
            case 1:
                if(entity.getIboard() != 0) {
                    res.sendRedirect("/board/detail?iboard=" + entity.getIboard());
                    return;
                }
                break;
            default:
                req.setAttribute("err", "등록/ 수정에 실패하였습니다.");
                req.setAttribute("data", entity);
                doGet(req, res);
                break;
        }
        res.sendRedirect("/board/list");
    }
}
