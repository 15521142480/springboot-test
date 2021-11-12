package com.lai.service.impl;

import com.lai.entity.sys.UserBean;
import com.lai.mapper.sys.UserExpandMapper;
import com.lai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserExpandMapper userExpandMapper;

    @Override
    public List<UserBean> getUserList() {
        return userExpandMapper.selectUserList();
    }

}
