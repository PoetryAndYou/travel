package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

/**
 * @author rui
 * @create 2019-08-27 16:25
 */
public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        //查询总条数
        /**
         *     private Integer totalCount;
         *     private Integer totalPage;
         *     private Integer currentPage;
         *     private Integer pageSize;
         *     List<T> list;
         */
        PageBean<Route> pageBean = new PageBean<Route>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        //总条数
        int totalCount = routeDao.findTotalCount(cid, rname);
        pageBean.setTotalCount(totalCount);
        //总页数
        int totalPage = +totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        pageBean.setTotalPage(totalPage);
        //设置数据
        int start = (currentPage - 1) * pageSize;
        List<Route> byPage = routeDao.findByPage(start, cid, pageSize, rname);
        pageBean.setList(byPage);

        return pageBean;
    }

    @Override
    public Route findOne(int rid) {
        //查询route信息
        Route route = routeDao.findOne(rid);
        //查询根据rid查询img设置到route对象中

        List<RouteImg> routeImg = routeImgDao.findImg(rid);
        // 设置图片
        route.setRouteImgList(routeImg);
        //查询sid查询卖家信息

        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);
        //查询被收藏数量
        int count = favoriteDao.findCountByRid(rid);
        route.setCount(count);

        return route;
    }


}
