package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

/**
 * @author rui
 * @create 2019-08-28 22:18
 */
public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(int uid, int rid) {
        Favorite favorite = favoriteDao.isFavorite(uid, rid);

        return favorite != null;
    }

    @Override
    public void addFavorite(String rid, int uid) {
        //添加收藏
        favoriteDao.addFavorite(Integer.parseInt(rid),uid);
    }
}
