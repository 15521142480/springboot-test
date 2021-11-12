package com.lai.entity.sys;

/**
 * <p>
 * 系统管理-用户基础信息表
 * </p>
 *
 */
public class UserBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID groups:标识在更新的时候才能验证非空
     */
    private Integer id;
    /**
     * 账号
     */
    private String userName;
    /**
     * 登录密码
     */
    private String passWord;
    /**
     * 明文密码 - QQ第三方授权登录时用
     */
    private String pwd;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 性别 0:男 1:女
     */
    private String sex;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 状态
     */
    private String flag;
    /**
     * 盐值
     */
    private String salt;
    /**
     * token
     */
    private String token;

    private String qqOppenId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getQqOppenId() {
        return qqOppenId;
    }

    public void setQqOppenId(String qqOppenId) {
        this.qqOppenId = qqOppenId;
    }
}
