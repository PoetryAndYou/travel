package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @author rui
 * @create 2019-08-27 11:11
 */
public interface CategoryService {
    public List<Category> findAll();
}
