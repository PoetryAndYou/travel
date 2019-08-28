package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author rui
 * @create 2019-08-27 16:00
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String rname = request.getParameter("rname");
        System.out.println(rname);

        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");

        System.out.println(rname);
        int cid = 0;
        int currentPage = 0;
        int pageSize = 0;
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);

        }
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);

        } else {
            //默认
            pageSize = 5;
        }
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);

        } else {
            //默认
            currentPage = 1;
        }
        //调用service;
        PageBean<Route> routePageBean = routeService.pageQuery(cid, currentPage, pageSize, rname);
        //序列化为json，返回
        writeValue(routePageBean, response);

    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        System.out.println(rid);
        Route route = routeService.findOne(Integer.parseInt(rid));
        writeValue(route, response);
    }

    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("login_s");
        int uid = 0;
        if (null == user || "".equals(user)) {
            //用户未登录
            uid = 0;
        } else {
            uid = user.getUid();
        }
        //调用FravoiteService
        boolean favorite = favoriteService.isFavorite(uid, Integer.parseInt(rid));
        writeValue(favorite, response);

    }
    /*
    添加收藏
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("login_s");
        int uid = 0;
        if (null == user || "".equals(user)) {
            //用户未登录
           return;
        } else {
            uid = user.getUid();
        }
        favoriteService.addFavorite(rid,uid);
    }

}
