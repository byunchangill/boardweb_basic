package com.koreait.basic.board.cmt;

import com.google.gson.Gson;
import com.koreait.basic.Utils;
import com.koreait.basic.board.model.dto.BoardCmtDTO;
import com.koreait.basic.board.model.entity.BoardCmtEntity;
import com.koreait.basic.board.model.vo.BoardCmtVO;
import com.koreait.basic.dao.BoardCmtDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/board/cmt") // ajax 용.
public class BoardCmtServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 리스트 (R)
        int iboard = Utils.getParameterInt(req,"iboard");
        BoardCmtDTO cmtParam = new BoardCmtDTO();
        cmtParam.setIbaord(iboard);

        List<BoardCmtVO> list = BoardCmtDAO.selBoardCmtList(cmtParam);

        Gson gson = new Gson(); // 객체(object)를 json 으로 봐꿈.
        String json = gson.toJson(list);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8"); // 한글 깨짐 방지.
        PrintWriter out = res.getWriter();
        out.print(json); //String json = gson.toJson(list); 를 쓰지 않을때
                         // out.print(gson.toJson(list)); 로 하면 된다.
        out.flush(); // 굳이 안 써도 된다.
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 등록(C), 수정(U), 삭제(D)
        String  proc = req.getParameter("proc");

        String json = Utils.getJson(req);
        Gson gson = new Gson();
        BoardCmtEntity entity = gson.fromJson(json, BoardCmtEntity.class);
        entity.setWriter(Utils.getLoginUserPk(req));

        int result = 0;
        switch (proc) {
            case "upd":
                result = BoardCmtDAO.upBoardCmt(entity); // writer, icmt, ctnt 값이 들어가 있다.
                break;
            case "del":
                result = BoardCmtDAO.delBoardCmt(entity); // writer, icmt 값이 들어가 있다.
                break;
            case "ins":
                result = BoardCmtDAO.insBoardCmt(entity); // result 값이 icmt 값을 가진다.
                break;
        }
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8"); // 한글 깨질때. 정수값만 들고올때는 굳이 필요없다.
        PrintWriter out = res.getWriter();
//        out.print(String.format("{\"result\": %d}", result));

        Map<String, Integer> map = new HashMap();
        map.put("result", result);

        String resultJson = gson.toJson(map);
        out.println(resultJson);
    }
}
