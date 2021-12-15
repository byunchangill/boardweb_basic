package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.board.model.entity.BoardHeartEntity;
import com.koreait.basic.board.model.vo.BoardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardHeartDAO {
    public static int insBoardHeart(BoardHeartEntity param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " INSERT INTO t_board_heart(iuser, iboard) VALUES (?, ?) ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIuser());
            ps.setInt(2, param.getIboard());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
    public static int selIsHeart(BoardHeartEntity param) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT iuser FROM t_board_heart WHERE iuser = ? AND iboard = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIuser());
            ps.setInt(2, param.getIboard());
            rs = ps.executeQuery();

            if(rs.next()) {
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    public static int delBoardHeart(BoardHeartEntity param) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " DELETE FROM t_board_heart WHERE iuser = ? AND iboard = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, param.getIuser());
            ps.setInt(2, param.getIboard());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
}
