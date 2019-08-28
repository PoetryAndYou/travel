package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author rui
 * @create 2019-08-27 10:56
 */
public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql="select * from tab_category;";
        List<Category> categories = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
        return categories;
    }
}
