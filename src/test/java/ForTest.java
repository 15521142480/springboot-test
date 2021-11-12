
import com.lai.common.enums.SysCarBlackListEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: jdk1.8新属性测试
 * @Author: laixues
 * @Date: 2021/1/30
 */
public class ForTest {


    public static void main(String[] args) {

        // =================== 之前的版本与jdk1.8的Lambda函数箭头写法对比

        // === forEach
        System.out.println("====== forEach");
        List<String> list1 = new ArrayList<>();
        list1.add("name");
        list1.add("age");
        list1.add("sex");
        System.out.println("list1: " + list1);

        list1.forEach(System.out::println); // 等同于 list1.forEach(item -> System.out.print(item));
        list1.forEach((item) -> {
            item = item + ":都是我";
            System.out.println(item);
        });

        // === map
        System.out.println("====== map");
        Map<String, Integer> mapData1 = new HashMap<>();
        mapData1.put("A", 10);
        mapData1.put("B", 20);
        mapData1.put("C", 30);
        for (Map.Entry<String, Integer> entry : mapData1.entrySet()) {
            System.out.println("ket : " + entry.getKey() + " value : " + entry.getValue());
        }
        mapData1.forEach((k,v)-> System.out.println("key : " + k + " value : " + v));
        mapData1.forEach((k,v)->{
            if("B".equals(k)){
                System.out.println("Hello B");
            }
        });

        // === enum 貌似用不了....
        System.out.println("====== enum");
        for (SysCarBlackListEnum enumEntity : SysCarBlackListEnum.values()) {
            System.out.println(enumEntity.name() + ":" + enumEntity.getRequires());
        }

    }
}
