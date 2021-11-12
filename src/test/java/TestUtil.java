import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 测试类
 * @Author: laixues
 * @Date: 2021/1/20
 */
public class TestUtil {



    public static void main(String[] args) {

        /**
         * 系统方法测试
         */
        System.out.println("--------------------当前项目所在的系统目录路径-------------------");
        System.out.println(System.getProperty("user.dir"));


        /**
         * 对比JSONObject与Map的效率问题
         */
        System.out.println("--------------------对比JSONObject与Map的效率问题-------------------");
        long startTime = System.currentTimeMillis();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "联盟");
        jsonObject.put("age", 10);
        long endTime = System.currentTimeMillis();
        System.out.println("JsonObject程序运行时间：" + (endTime - startTime) + "ms");
        System.out.println(jsonObject + "name" + jsonObject.get("name"));

        long startTime2 = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "联盟");
        map.put("age", 10);
        map.put("like", new int []{1, 2});
        map.put("select", new String []{"1", "2", "3"});
        long endTime2 = System.currentTimeMillis();
        System.out.println("Map程序运行时间：" + (endTime2 - startTime2) + "ms");
        System.out.println(map + "name" + map.get("name"));
        System.out.println(map.get("name").getClass().getCanonicalName());
        System.out.println(map.get("like").getClass().getCanonicalName());

        int[] like = new int[]{1, 2, 3, 4};
        int[] like2 = {1, 2, 3, 4};


        /**
         * 字段处理 截取\变动\
         */
        System.out.println("--------------------截取-------------------");
        String token = "userToken_1_pc_0:0:0:0:0:0:0:1m8Ebg76gtHrgYWSSUrKYhsdf6KsuKQ3V";
        token = token.substring(0, token.indexOf("_", token.indexOf("_") + 1));
        System.out.println(token);

        String deptId = "44010000";
        String deptId2 = "44010000";
        String region = "0000";
        deptId = deptId.substring(0, region.length());
        System.out.println(deptId);
        System.out.println(deptId2.substring(0, 4));

        String text = "photo" + 1;
        System.out.println(text);

        // toLowerCase 小写
        String fileNames = "你再闹啊.我在笑啊.jpg";
         String fileType = fileNames.substring(fileNames.lastIndexOf(".")).toLowerCase();
        String fileName = fileNames.substring(0, fileNames.lastIndexOf(".")).toLowerCase();
        System.out.println(fileType);
        System.out.println(fileName);


    }

}
