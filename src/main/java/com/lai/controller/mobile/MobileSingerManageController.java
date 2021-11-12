package com.lai.controller.mobile;

import com.alibaba.fastjson.JSONObject;
import com.lai.common.util.HttpUtils;
import com.lai.common.util.RequestUtils;
import com.lai.common.util.StringUtils;
import com.lai.common.util.WyyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 歌手管理
 * @Author: laixues
 * @Date: 2021/2/25
 */
@RestController("mobileSingerManageController")
@RequestMapping("mobile/singer")
@Api(tags = "歌手模块")
public class MobileSingerManageController {

    /**
     * 热门歌手列表
     * @return
     */
    @PostMapping("/getHotSingerList")
    @ApiOperation(value = "热门歌手列表", notes = "")
    @ApiImplicitParams({
            // @ApiImplicitParam(paramType = "query", dataType = "String", name = "type", value = "新歌类型(全部:0, 华语:1, 欧美:2, 韩国:3, 日本:4)"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "limit", value = "查询条数"),
    })
    public Object getHotSingerList(HttpServletRequest request){

        Map<String, Object> params = RequestUtils.getParamsByRequest(request);
        Map<String, Object> resultMap = new HashMap<>();

        String api = WyyUtils.getHotSingerApi();
        int limit = StringUtils.getNumByObject(params.get("limit"));
        String resultDataStr = HttpUtils.doGet(api+ "?limit=" + limit);
        Map<String, Object> resultData = JSONObject.parseObject(resultDataStr, Map.class);
        List<Map<String, Object>> list = (List<Map<String, Object>>) resultData.get("artists");

        // 组装成与本项目开发的表数据结构
        List<Map<String, Object>> newList = new ArrayList<>();
        if (list.size() > 0) {
            for (Map<String, Object> data : list) {
                Map<String, Object> newData = new HashMap<>();
                newData.put("id", data.get("id"));
                newData.put("name", data.get("name"));
                newData.put("pic", data.get("picUrl"));
                List<Map<String, Object>> alias = (List<Map<String, Object>>) data.get("alias");
                newData.put("alias", alias.isEmpty() ? "" : alias.get(0));
                newList.add(newData);
            }
        }

        resultMap.put("data", newList);
        resultMap.put("resultCode", "200");

        return resultMap;
    }



}
