package com.koreait.basic;

import com.koreait.basic.user.model.UserEntity;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

public class Utils {
    public static void displayView(HttpServletRequest req, HttpServletResponse res, String title, String page) throws ServletException, IOException {
        req.setAttribute("title", title);
        req.setAttribute("page", page);
        disForward(req, res, "layout");
    }

    public static void disForward (HttpServletRequest req, HttpServletResponse res, String jsp) throws ServletException, IOException {
        String path = "/WEB-INF/view/" + jsp + ".jsp";
        req.getRequestDispatcher(path).forward(req, res);
    }

    public static int getParameterInt (HttpServletRequest req, String key) {
        return getParameterInt(req, key, 0);
    }

    public static int getParameterInt (HttpServletRequest req, String key, int defVal) {
        String val = req.getParameter(key);
        return parseStringToInt(val, defVal);
    }

    public static int parseStringToInt(String val, int defVal) {
        try {
            return Integer.parseInt(val);
        } catch (Exception e) {
        }
        return defVal;
    }

    public static UserEntity getLoginUser(HttpServletRequest req) {
        HttpSession hs = req.getSession();
        return (UserEntity) hs.getAttribute("loginUser");
    }

    public static int getLoginUserPk(HttpServletRequest req) {
        UserEntity loginUser = getLoginUser(req);
        if(loginUser == null) { return 0; }
        return loginUser.getIuser();
    }

    public static String getJson(HttpServletRequest request) throws IOException { // getJson 사용 메소드.
        String reqStr = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        reqStr = stringBuilder.toString();
        return reqStr;
    }
}
