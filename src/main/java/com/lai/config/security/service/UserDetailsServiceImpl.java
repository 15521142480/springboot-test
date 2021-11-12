//package com.lai.config.security.service;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.lai.config.security.entity.SecurityUser;
//import com.lai.entity.generator.User;
//import com.lai.entity.sys.SysRoleBean;
//import com.lai.entity.sys.SysUserRoleBean;
//import com.lai.entity.sys.UserBean;
//import com.lai.mapper.generator.UserMapper;
//import com.lai.mapper.sys.SysRoleExpandMapper;
//import com.lai.mapper.sys.SysUserRoleExpandMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * @Description: 自定义userDetailsService - 认证用户详情
// * @Author: laixues
// * @Date: 2021/1/27
// */
//@Service("userDetailsService")
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Autowired
//    private SysUserRoleExpandMapper sysUserRoleMapper;
//
//    @Autowired
//    private SysRoleExpandMapper sysRoleMapper;
//
//    /**
//     * 根据用户账号获取用户信息 (相当于用用户名去查数据库,查到的用户信息返给spring,框架给你判断用户密码权限之类的东西,不用你自己判断)
//     * @param userName 用户登陆名
//     * @return
//     * @throws UsernameNotFoundException
//     */
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//
//        // 从数据库中取出用户信息
//        List<User> userList = userMapper.selectList(new QueryWrapper<User>().eq("user_name", userName));
//        User user;
//
////         if (!CollectionUtils.isEmpty(userList)) {
//        if (userList.size() > 0) { // 判断用户是否存在
//            user = userList.get(0);
//        } else {
//            throw new UsernameNotFoundException("用户名不存在！");
//        }
//
////        Collection<GrantedAuthority> authorities = new ArrayList<>();
////        // 添加权限
////        List<SysUserRole> userRoles = userRoleService.listByUserId(user.getId());
////        for (SysUserRole userRole : userRoles) {
////            SysRole role = roleService.selectById(userRole.getRoleId());
////            authorities.add(new SimpleGrantedAuthority(role.getName()));
////        }
////        authorities.add(new SimpleGrantedAuthority(user.getPositionId()));
//
//        // 返回UserDetails实现类
//        return new SecurityUser(user, getUserRoles(user.getId()));
//    }
//
//
//    /**
//     * 根据用户id获取角色权限信息
//     *
//     * @param userId
//     * @return
//     */
//    private List<SysRoleBean> getUserRoles(Integer userId) {
//
//        List<SysUserRoleBean> userRoles = sysUserRoleMapper.selectList(new QueryWrapper<SysUserRoleBean>().eq("user_id", userId));
//        List<SysRoleBean> roleList = new LinkedList<>();
//
//        for (SysUserRoleBean userRole : userRoles) {
//            SysRoleBean role = sysRoleMapper.selectById(userRole.getRoleId());
//            roleList.add(role);
//        }
//
//        return roleList;
//    }
//
//
//    /***
//     * 根据token获取用户权限与基本信息
//     *
//     * @param token:
//     * @return: SecurityUser
//     */
//    public SecurityUser getUserByToken(String token) {
//
//        User user = null;
//        List<User> loginUserList = userMapper.selectList(new QueryWrapper<User>().eq("token", token));
//        if (loginUserList.size() > 0) {
//            user = loginUserList.get(0);
//        }
//
//        return user != null ? new SecurityUser(user, getUserRoles(user.getId())) : null;
//    }
//
//}
