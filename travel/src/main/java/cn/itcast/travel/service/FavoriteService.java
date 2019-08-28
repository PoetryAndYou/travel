package cn.itcast.travel.service;

/**
 * @author rui
 * @create 2019-08-28 22:17
 */
public interface FavoriteService {
    public boolean isFavorite(int uid,int cid);

    void addFavorite(String rid, int uid);
}
