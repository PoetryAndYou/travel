package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rui
 * @create 2019-08-27 16:35
 */
public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 总条数
     *
     * @param cid
     * @param rname
     * @return
     */
    @Override
    public int findTotalCount(int cid, String rname) {
        // String sql="select count(*) from tab_route where cid=? and rname like ?";
        //定义sql模板
        String sql = "select count(*) from tab_route where 1=1 ";
        //判断参数是否有值
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (0 != cid) {
            sb.append(" and cid=? ");
            params.add(cid);
        }
        if (!"null".equals(rname) && rname.length() > 0) {
            sb.append(" and rname like ? ");
            params.add("%" + rname + "%");
        }

        Integer integer = jdbcTemplate.queryForObject(sb.toString(), Integer.class, params.toArray());
        return integer;
    }

    /**
     * 当前页面的数据
     *
     * @param start
     * @param cid
     * @param pageSize
     * @param rname
     * @return
     */
    @Override
    public List<Route> findByPage(int start, int cid, int pageSize, String rname) {
        List<Route> routes = null;
        try {
            //String sql = "select * from tab_route where cid=? limit ? , ?";
            String sql = "select * from tab_route where 1=1 ";
            //判断参数是否有值
            StringBuilder sb = new StringBuilder(sql);
            List params = new ArrayList();
            if (0 != cid) {
                sb.append(" and cid = ? ");
                params.add(cid);
            }
            if (!"null".equals(rname) && rname.length() > 0) {
                sb.append(" and rname like  ?  ");
                params.add("%" + rname + "%");
            }
            sb.append(" limit  ? , ?");
            params.add(start);
            params.add(pageSize);

            routes = jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
        } catch (DataAccessException e) {
            System.out.println("啥也没有"+cid);
            e.printStackTrace();
        }
        return routes;
    }

    @Override
    public Route findOne(int rid) {
        Route route = null;
        try {
            String sql="select * from tab_route where rid=?";
            route = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
        } catch (DataAccessException e) {
            System.out.println("findone");
        }
        return route;
    }
}
