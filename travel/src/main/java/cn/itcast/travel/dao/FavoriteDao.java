package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

import java.util.List;

/**
 * @author rui
 * @create 2019-08-28 22:19
 */
public interface FavoriteDao {
    Favorite isFavorite(int uid, int rid);

    int findCountByRid(int rid);

    void addFavorite(int parseInt, int uid);

    List<Favorite> findAllMyFavorite(int uid);
}
