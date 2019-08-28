package cn.itcast.travel;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;

public class test {
    static int x=0;
    public static void main(String[] args) {
        FavoriteDao favoriteDao=new FavoriteDaoImpl();
        Favorite favorite = favoriteDao.isFavorite(19, 1);
        System.out.println(favorite);
        System.out.println(x);

    }
}
