package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.MyFavoriteService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rui
 * @create 2019-08-29 1:35
 */
public class MyFavoriteServiceImpl implements MyFavoriteService {
    private FavoriteDao favorite = new FavoriteDaoImpl();
    private RouteDao routeDao=new RouteDaoImpl();
    /**
     * private Integer totalCount;
     * private Integer totalPage;
     * private Integer currentPage;
     * private Integer pageSize;
     * List<T> list;
     */
    @Override
    public PageBean<Route> findById(Integer pageSize, Integer currentPage, int uid) {
        List<Favorite> allMyFavorite = favorite.findAllMyFavorite(uid);
        List<Route> routes=new ArrayList<Route>();
        //当前起始页
        int start=(currentPage-1)*pageSize;
        for (int i = start; i <= start+pageSize; i++) {
             allMyFavorite.get(i).getRoute();
        }

        return null;
    }
}
