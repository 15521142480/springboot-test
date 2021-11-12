package com.lai.controller.mobile;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lai.common.util.HttpUtils;
import com.lai.common.util.RequestUtils;
import com.lai.common.util.StringUtils;
import com.lai.common.util.WyyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description: 歌单模块
 * @Author: laixues
 * @Date: 2021/2/4
 */
@RestController("mobilePlayListController")
@RequestMapping("mobile/play")
@Api(tags = "歌单模块")
public class MobilePlayListController {

//    @Value("${wangyiyun-music.base-path}")
//    public String wyyMusicBasePath;

    /**
     * 首页轮播图
     * @return
     */
    @PostMapping("/getHomeBannerList")
    @ApiOperation(value = "首页轮播列表", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "type", value = "类型(0: pc,1: android, 2: iphone,3: ipad)")
    })
    public Object getHomeBannerList(HttpServletRequest request){

        Map<String, Object> params = RequestUtils.getParamsByRequest(request);
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();

        int type = 0;
        type = StringUtils.getNumByObject(params.get("type"));
        String api = WyyUtils.getBannerListApi();
        String resultDataStr = HttpUtils.doGet(api+ "?type=" + type);
        // String resultData = HttpUtils.doPost(api, queryMap, null);
        // JSONObject data = JSON.parseObject(resultDataStr);
        Map<String, Object> resultData = JSONObject.parseObject(resultDataStr, Map.class);
        List<Map<String, Object>> list = (List<Map<String, Object>>) resultData.get("banners");

        // 组装成与本项目开发的表数据结构
        List<Map<String, Object>> newList = new ArrayList<>();
        if (list.size() > 0) {
            for (Map<String, Object> data : list) {
                Map<String, Object> newData = new HashMap<>();
                newData.put("id", data.get("id"));
                newData.put("name", data.get("name"));
                newData.put("pic", data.get("pic"));
                newList.add(newData);
            }
        }

        resultMap.put("data", newList);
        resultMap.put("resultCode", "200");

        return resultMap;
    }

    /**
     * 推荐歌单列表
     * @return
     */
    @PostMapping("/getPersonalizedList")
    @ApiOperation(value = "推荐歌单列表", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "limit", value = "查询条数")
    })
    public Object getPersonalizedList(HttpServletRequest request){

        Map<String, Object> params = RequestUtils.getParamsByRequest(request);
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();

        String api = WyyUtils.getPersonalizedListApi(); // 推荐歌单
        int limit = StringUtils.getNumByObject(params.get("limit"));
        queryMap.put("limit", limit);
        String resultDataStr = HttpUtils.doGet(api+ "?limit=" + limit);

        Map<String, Object> resultData = JSONObject.parseObject(resultDataStr, Map.class);
        List<Map<String, Object>> list = (List<Map<String, Object>>) resultData.get("result");

        // 组装成与本项目开发的表数据结构
        List<Map<String, Object>> newList = new ArrayList<>();
        if (list.size() > 0) {
            for (Map<String, Object> data : list) {
                Map<String, Object> newData = new HashMap<>();
                newData.put("id", data.get("id"));
                newData.put("name", data.get("name"));
                newData.put("pic", data.get("picUrl"));
                newData.put("playCount", data.get("playCount"));
                newList.add(newData);
            }
        }

        resultMap.put("data", newList);
        resultMap.put("resultCode", "200");

        return resultMap;
    }


}
