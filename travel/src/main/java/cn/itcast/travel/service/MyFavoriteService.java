package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

/**
 * @author rui
 * @create 2019-08-29 1:34
 */
public interface MyFavoriteService {
    /**
     * private Integer totalCount;
     * private Integer totalPage;
     * private Integer currentPage;
     * private Integer pageSize;
     * List<T> list;
     */

    PageBean<Route> findById(Integer pageSize, Integer currentPage, int uid);

}
