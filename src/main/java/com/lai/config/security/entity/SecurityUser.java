package com.lai.config.security.entity;

import com.lai.entity.generator.User;
import com.lai.entity.sys.SysRoleBean;
import com.lai.entity.sys.UserBean;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

/**
 * @Description: 安全认证用户详情
 * @Author: laixues
 * @Date: 2021/1/27
 */
@Data
@Slf4j
public class SecurityUser implements UserDetails {

    // 当前登录用户
    private transient User curUser; // transient 避免被序列化

    // 角色
    private transient List<SysRoleBean> roleList;

    // 角色代码
    private transient String roleCodes;


    public SecurityUser() {
    }

    public SecurityUser(User user) {
        if (user != null) {
            this.curUser = user;
        }
    }

    public SecurityUser(User user, List<SysRoleBean> roleList) {
        if (user != null) {
            this.curUser = user;
            this.roleList = roleList;
            if (!CollectionUtils.isEmpty(roleList)) {
                StringJoiner roleCodes = new StringJoiner(",", "[", "]");
                // roleList.forEach(e -> roleCodes.add(e.getCode()));
                this.roleCodes = roleCodes.toString();
            }
        }
    }

    /**
     * 获取当前用户所具有的角色
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (this.roleList.size() > 0) {
            for (SysRoleBean role : this.roleList) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRolename());
                authorities.add(authority);
            }
        }
        return authorities;
    }


    @Override
    public String getPassword() {
        return this.curUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.curUser.getNickName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
