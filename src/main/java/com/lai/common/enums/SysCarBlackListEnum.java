package com.lai.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 车辆黑名单信息
 * @Author: laixues
 * @Date: 2021/1/21
 */
public enum SysCarBlackListEnum {

    hphm("号牌号码", "1"),
    hpzl("号牌种类", "1"),
    bklx("布控类型", "1"),
    city("行政区划", "0");

    SysCarBlackListEnum(String name, String requires) {
        this.name = name;
        this.requires = requires;
    }

    private String name;
    private String requires;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequires() {
        return requires;
    }

    public void setRequires(String requires) {
        this.requires = requires;
    }


    /**
     * 获取车辆黑名单名称字段信息 号牌号码=hphm
     * @return
     */
    public static Map<String, Object> getSysCarBlackListNameByMap() {
        Map<String, Object> enumMap = new HashMap<>();
        for (SysCarBlackListEnum workProjectEnum : SysCarBlackListEnum.values()) {
            enumMap.put(workProjectEnum.getName(), workProjectEnum.name());
        }
        return enumMap;
    }

    /**
     * 获取车辆黑名单字段必填信息 hphm=1
     * @return
     */
    public static Map<String, Object> getSysCarBlackListRequiresByMap() {
        Map<String, Object> enumMap = new HashMap<>();
        for (SysCarBlackListEnum workProjectEnum : SysCarBlackListEnum.values()) {
            enumMap.put(workProjectEnum.name(), workProjectEnum.getRequires());
        }
        return enumMap;
    }

    public static void main(String[] args) {
        System.out.println(getSysCarBlackListNameByMap());
        System.out.println(getSysCarBlackListRequiresByMap());
    }

}
