package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.board.model.dto.BoardCmtDTO;
import com.koreait.basic.board.model.entity.BoardCmtEntity;
import com.koreait.basic.board.model.entity.BoardEntity;
import com.koreait.basic.board.model.vo.BoardCmtVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardCmtDAO {
    public static int insBoardCmt(BoardCmtEntity param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " INSERT INTO t_board_cmt(iboard, ctnt, writer) VALUES (?, ?, ?) ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIboard());
            ps.setString(2, param.getCtnt());
            ps.setInt(3, param.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    public static List<BoardCmtVO> selBoardCmtList(BoardCmtDTO param) {
        List<BoardCmtVO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT A.icmt, A.ctnt, A.writer, A.rdt, B.nm as writerNm " +
                " FROM t_board_cmt A " +
                " INNER JOIN t_user B " +
                " ON A.writer = B.iuser " +
                " WHERE A.iboard = ? " +
                " ORDER BY A.icmt ASC ";
        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIbaord());
            rs = ps.executeQuery();
            while (rs.next()){
                BoardCmtVO vo = BoardCmtVO.builder()
                        .icmt( rs.getInt("icmt"))
                        .ctnt(rs.getString("ctnt"))
                        .writer(rs.getInt("writer"))
                        .rdt(rs.getString("rdt"))
                        .writerNm(rs.getString("writerNm"))
                        .build();
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return list;
    }

    public static int delBoardCmt(BoardCmtEntity param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " DELETE FROM t_board_cmt WHERE icmt = ? AND writer = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIcmt());
            ps.setInt(2, param.getWriter());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    public static int upBoardCmt(BoardCmtEntity param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " UPDATE t_board_cmt SET ctnt = ? WHERE icmt = ? AND writer = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getCtnt());
            ps.setInt(2, param.getIcmt());
            ps.setInt(3, param.getWriter());
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
}
