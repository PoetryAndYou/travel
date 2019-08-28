package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @author rui
 * @create 2019-08-27 16:35
 */
public interface RouteDao {
    //根据cid查询总条数
    public int findTotalCount(int cid, String rname);
    //查询当夜的数据cid start pageSize
    public List<Route> findByPage(int start, int cid, int pageSize, String rname);
    //
    public Route findOne(int rid);
}
