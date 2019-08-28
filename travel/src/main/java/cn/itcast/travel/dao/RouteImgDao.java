package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 * @author rui
 * @create 2019-08-28 16:27
 */
public interface RouteImgDao {

    List<RouteImg> findImg(int rid);
}
