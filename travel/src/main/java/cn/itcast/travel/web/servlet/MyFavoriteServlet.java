package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author rui
 * @create 2019-08-29 1:32
 */
@WebServlet("/myfavorite/*")
public class MyFavoriteServlet extends BaseServlet {
    public void myFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");
        User user = (User) request.getSession().getAttribute("login_s");
        int uid = 0;
        if (null == user || "".equals(user)) {
            //用户未登录
           return;
        } else {
            uid = user.getUid();
        }


    }
}
