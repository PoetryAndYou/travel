package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

/**
 * @author rui
 * @create 2019-08-28 22:19
 */
public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询用户收藏记录
     *
     * @param uid
     * @param rid
     * @return
     */
    @Override
    public Favorite isFavorite(int uid, int rid) {
        Favorite favorite = null;
        try {
            String sql = "select * from tab_favorite where uid=? and rid=?";
            favorite = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), uid, rid);
        } catch (DataAccessException e) {

        }
        return favorite;
    }

    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(*) from tab_favorite where rid=?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, rid);
        return count;
    }

    @Override
    public void addFavorite(int rid, int uid) {
        String sql = "insert into tab_favorite values(?,?,?)";
        jdbcTemplate.update(sql, rid, new Date(), uid);

    }

    @Override
    public List<Favorite> findAllMyFavorite(int uid) {
        List<Favorite> query = null;
        try {
            String sql = "select rid from tab_favorite where uid=?";
            query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return query;
    }
}
