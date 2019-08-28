package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

/**
 * @author rui
 * @create 2019-08-28 16:27
 */
public interface SellerDao {
    Seller findById(int sid);
}
