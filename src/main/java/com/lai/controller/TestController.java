package com.lai.controller;

import com.alibaba.fastjson.JSONObject;
import com.lai.common.util.HttpConnection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "测试模块")
@RequestMapping("pc/test/")
public class TestController {
//
//    @Autowired
//    UserMapper userMapper;
//
//    @Autowired
//    UserService userService;
//
//
//    // -------------------------------------------- 处理接口传参问题 start
//
//    @PostMapping("/params1")
//    @ApiOperation(value = "params1", notes = "params")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userName", value = "用户名"),
//            @ApiImplicitParam(paramType = "query", dataType = "String", name = "passWord", value = "密码")
//    })
//    public Object params1(HttpServletRequest request){
//
//        String userName = request.getParameter("userName");
//        String password = request.getParameter("password");
//
//        return userName + password;
//    }
//
//    @PostMapping("/params2")
//    @ApiOperation(value = "params2", notes = "params")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userName", value = "用户名"),
//            @ApiImplicitParam(paramType = "query", dataType = "String", name = "passWord", value = "密码")
//    })
//    public Object params2(@RequestParam Map<String, Object> params){
//
//        return params;
//    }
//
//    @PostMapping("/params3")
//    @ResponseBody
//    @ApiOperation(value = "params3-使用responseBody封装成JavaBean", notes = "params")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userName", value = "用户名"),
//            @ApiImplicitParam(paramType = "query", dataType = "String", name = "passWord", value = "密码")
//    })
//    public Object params3(@RequestBody UserBean user){ // 使用postman测试时去掉 @RequestBody
//
//        return user;
//    }
//
//    // -------------------------------------------- 处理接口传参问题 end
//
//
//    // ================================================== mybatis-plus语法sql测试 start
//
//    @PostMapping("/userList1")
//    @ApiOperation(value = "用户列表1", notes = "列表")
//    public Object userList1(){
//
//        List<UserBean> userList = userService.getUserList();
//        return userList;
//    }
//
//    @PostMapping("/userList2")
//    @ApiOperation(value = "用户列表2", notes = "列表")
//    public Object userList2(){
//
//        List<User> userList = userMapper.selectList(new QueryWrapper<>());
//        return userList;
//    }
//
//    /**
//     * @RequestParam Map<String, Object> params 他会以Object形式接收参数-可以把数据封装成map
//     * @RequestBody Map<String, Object> params 他会以json形式接收参数-可以把数据封装成map/javaBean
//     * 前两个都是一个总参数,使用swagger就无法展示具体参数,建议前后端是同一个人开发才使用这两者
//     *
//     */
//    @PostMapping("/userList3")
//    @ApiOperation(value = "用户列表3", notes = "列表")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userName", value = "用户名"),
//            @ApiImplicitParam(paramType = "query", dataType = "String", name = "nickName", value = "用户昵称")
//    })
//    public Object userList3(HttpServletRequest request) {
//
//        Map<String, Object> params = RequestUtils.getParamsByRequest(request);
//
//        String userName = String.valueOf(params.get("userName"));
//        String nickName = String.valueOf(params.get("nickName"));
//
//        // 查询体(user)
//        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//
//        // 判空写在外面
////        if (StringUtils.isNotBlank(userName)) {
////            // queryWrapper.eq("user_name", userName);
////            queryWrapper.like("user_name", userName);
////        }
////        if (StringUtils.isNotBlank(nickName)) {
////            // queryWrapper.eq("nick_name", nickName);
////            queryWrapper.like("nick_name", nickName);
////        }
//
//        // 判空写在里面
//        queryWrapper.like(StringUtils.isNotBlank(userName), "user_name", userName);
//        queryWrapper.like(StringUtils.isNotBlank(nickName), "nick_name", nickName);
//
//
//        List<User> userList = userMapper.selectList(queryWrapper);
//
//        // userList.forEach(System.out::println);
//
//        return userList;
//    }
//

    // 大数据接口
    @Value("${gdjwy.base_url}")
    private String base_url;

    @Value("${gdjwy.client_id}")
    private String client_id;

    @Value("${gdjwy.client_secret}")
    private String client_secret;

    @Value("${gdjwy.grant_type}")
    private String grant_type;

    private static String _base_url;
    private static String _client_id;
    private static String _client_secret;
    private static String _grant_type;

    @PostConstruct
    public void init() {
        this._base_url = base_url;
        this._client_id = client_id;
        this._client_secret = client_secret;
        this._grant_type = grant_type;
    }



    @PostMapping("/httpClient")
    @ResponseBody
    @ApiOperation(value = "params3-使用responseBody封装成JavaBean", notes = "params")
    @ApiImplicitParams({
            //@ApiImplicitParam(paramType = "query", dataType = "String", name = "userName", value = "用户名"),
    })
    public Object httpClient(){


        System.out.println("token获取参数_client_id:" + _client_id + "--_client_secret:" + _client_secret+"--base_url:" + _base_url);

//        Map<String, Object> bodyMap = new HashMap<>();
//        bodyMap.put("client_id", _client_id);
//        bodyMap.put("client_secret", _client_secret);
//        bodyMap.put("grant_type", _grant_type);

        String  access_token = "";
        try {

            String[] res = HttpConnection.request("POST", _base_url, new String[]{
                    "Authorization", "Basic " + Base64.getEncoder().encodeToString((_client_id + ":" + _client_secret).getBytes()),
                    "Content-Type", "application/x-www-form-urlencoded"
            }, "grant_type=client_credentials");

            //HttpClientUtils.doPostNew(_base_url, "application/x-www-form-urlencoded", JSON.toJSONString(bodyMap), null, "UTF-8");

            String result = "";

            result = res[1];
            System.out.println("================ 获取广东警务云大数据的授权接口成功,数据: + " + result);
            Map<String, Object> resultMap = JSONObject.parseObject(result, Map.class);
            access_token = String.valueOf(resultMap.get("access_token"));

        } catch (Exception e){

            e.printStackTrace();
            System.out.println("================ 获取广东警务云大数据的授权接口失败!");
        }

//        if (StringUtils.isBlank(access_token)) {
//            throw new RuntimeException("======================= 获取广东警务云大数据接口 的 access_token 为空!!!");
//        }

        return access_token;
    }


    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir
     * @return
     */
    private static boolean deleteDir(File dir) {

        if (dir.isDirectory()) {
            String[] children = dir.list();

            for (String child : children) {
                boolean success = deleteDir(new File(dir, child)); // 递归删除目录中的子目录下
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();  // 目录此时为空，可以删除
    }

    public static void main(String[] args) {

//        String path = "/Users/laixueshi/applicationFile/file/ygnet/APMobileService/files/apk/2021";
//        File file = new File(path);
//        //file.delete();
//        boolean success = deleteDir(file);
//        if (success) {
//            System.out.println("成功: " + path);
//        } else {
//            System.out.println("失败: " + path);
//        }

        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        List<Object> sub = list.subList(0, 2);// 1,2
//        sub.add(5);
//        sub.add(6);
        System.out.println("list:" + list);
        System.out.println("sub:" + sub);

    }



}
