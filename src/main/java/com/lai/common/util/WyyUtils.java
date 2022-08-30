package com.lai.common.util;

import org.springframework.beans.factory.annotation.Value;

/**
 * @Description: 网易云工具类
 * @Author: laixues
 * @Date: 2021/2/4
 */
public class WyyUtils {

//    @Value("${wangyiyun-music.base-path}")
//    public String wyyMusicBasePath;


    // ====== 网易云接口
    private static final String basePath = "http://106.15.42.115:3000";

   // ====== 根据网易云的音乐id获取该歌曲地址
    private static String getSongById (int musicId) {
        return "https://music.163.com/song/media/outer/url?id= " + musicId + ".mp3";
    }


    /**
     * 首页相关
     * @return
     */
    // 首页轮播图
    public static String getBannerListApi() {
        String bannerListApi = "/banner";
        return basePath + bannerListApi;
    }

    /**
     * 歌单相关
     * @return
     */
    // 推荐歌单
    public static String getPersonalizedListApi() {
        String playListApi = "/personalized";
        return basePath + playListApi;
    }

    // 歌单(热门)
    public static String getPlayListApi() {
        String playListApi = "/top/playlist";
        return basePath + playListApi;
    }
    public static String getPlayListApi(String params) {
        String playListApi = "/top/playlist" + params;
        return basePath + playListApi;
    }

    // 歌单分类
    public static String getPlayCatListApi() {
        String playCatListApi = "/playlist/catlist";
        return basePath + playCatListApi;
    }

    /**
     * 歌曲相关
     * @return
     */
    // 热门新歌
    public static String getHotSongApi() {
        String hotSongApi = "/top/song"; // ?type=0 (全部:0, 华语:7, 欧美:96, 日本:8, 韩国:16)
        return basePath + hotSongApi;
    }
    // 推荐新音乐
    public static String getPersonalizedNewSongListApi() {
        String playNewSongListApi = "/personalized/newsong";
        return basePath + playNewSongListApi;
    }

    // 调用此接口 , 传入音乐 id 可获得对应音乐的歌词
    public static String getLyricById(int id) {
        String url = "/lyric?id=" + id;
        return basePath + url;
    }

    // 调用此接口 , 传入音乐 id 和 limit 参数 , 可获得该音乐的所有评论
    public static String getCommentById(int id) {
        String url = "/comment/music?id=" + id;
        return basePath + url;
    }


    /**
     * 歌手相关
     * @return
     */
    // 热门歌手列表
    public static String getHotSingerApi() {
        String hotSingerApi = "/top/artists";
        return basePath + hotSingerApi;
    }
    // 歌手列表
    public static String getSinger() {
        String singer = "/artist/list";
        return basePath + singer;
    }


}
