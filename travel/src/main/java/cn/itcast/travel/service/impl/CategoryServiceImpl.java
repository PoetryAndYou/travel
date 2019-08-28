package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author rui
 * @create 2019-08-27 11:11
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {

        //从redies中查询数据
        //获取连接
        Jedis jedis = JedisUtil.getJedis();
        //查询lis_json
        //Set<String> lis_json = jedis.zrange("lis_json", 0, -1);
        //查询lis_json分数
        Set<Tuple> lis_json = jedis.zrangeWithScores("lis_json", 0, -1);
        List<Category> categories=null;
        //判断lisjson是否为null
        if (lis_json == null || lis_json.size() == 0) {
            //从数据库中查询
             categories = categoryDao.findAll();
            //将list数列化为json
            ObjectMapper om = new ObjectMapper();
            System.out.println("数据库查询");
            //将json数据存入redis
            for (int i = 0; i < categories.size(); i++) {
                jedis.zadd("lis_json", categories.get(i).getCid(), categories.get(i).getCname());
            }


            jedis.close();
        }else{
            System.out.println("redis中查询");
            //将set存到List
            categories=new ArrayList<Category>();
            for (Tuple tuple:
                lis_json ) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                categories.add(category);
            }
        }
        return categories;

    }
}
