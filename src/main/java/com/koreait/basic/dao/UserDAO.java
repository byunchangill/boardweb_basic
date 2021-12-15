package com.koreait.basic.dao;

import com.koreait.basic.DbUtils;
import com.koreait.basic.user.model.LoginResult;
import com.koreait.basic.user.model.UserEntity;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public static int join (UserEntity entity) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " INSERT INTO t_user(uid, upw, nm, gender) VALUES (?, ?, ?, ?) ";

        try {
           con = DbUtils.getCon();
           ps = con.prepareStatement(sql);
           ps.setString(1, entity.getUid());
           ps.setString(2, entity.getUpw());
           ps.setString(3, entity.getNm());
           ps.setInt(4, entity.getGender());
           return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }

    public static LoginResult login(UserEntity entity) {
        int result = 0;
        UserEntity loginUser = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT iuser, upw, nm, gender, profileImg FROM t_user WHERE uid = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, entity.getUid());
            rs = ps.executeQuery();

            if(rs.next()) {
                String dbupw = rs.getString("upw");
                if(BCrypt.checkpw(entity.getUpw(), dbupw)) { // 로그인 성공.
                    loginUser = new UserEntity();
                    loginUser.setIuser(rs.getInt("iuser"));
                    loginUser.setNm(rs.getString("nm"));
                    loginUser.setUid(entity.getUid());
                    loginUser.setGender(rs.getInt("gender"));
                    loginUser.setProfileImg(rs.getString("profileImg"));
                    result = 1;
                } else {
                   result = 3; // 비밀번호 틀림.
                }
            }else {
               result = 2; // 아이디 틀림.
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return new LoginResult(result, loginUser);
    }

    public static UserEntity selUser(UserEntity entity) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = " SELECT uid, nm, gender, rdt, profileImg FROM t_user WHERE iuser = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1, entity.getIuser());
            rs = ps.executeQuery();
            if(rs.next()) {
                UserEntity vo = new UserEntity();
                vo.setUid(rs.getString("uid"));
                vo.setNm(rs.getString("nm"));
                vo.setRdt(rs.getString("rdt"));
                vo.setGender(rs.getInt("gender"));
                vo.setProfileImg(rs.getString("profileImg"));
                return vo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps, rs);
        }
        return null;
    }

    public static int updUser(UserEntity entity) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " UPDATE t_user SET ";
        String changedVal = null;
        if(entity.getUpw() != null && !"".equals(entity.getUpw())) {
            sql += " upw = ? ";
            changedVal = entity.getUpw();
        } else if (entity.getProfileImg() != null && !"".equals(entity.getUpw())) {
            sql += " profileImg = ? ";
            changedVal = entity.getProfileImg();
        }
        sql += " WHERE iuser = ? ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, changedVal);
            ps.setInt(2, entity.getIuser());
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.close(con, ps);
        }
        return 0;
    }
}
