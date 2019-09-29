package com.wl.blog.service;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wl.blog.server.entity.*;
import com.wl.blog.viewmodel.PermissionViewModel;
import com.wl.blog.viewmodel.RoleViewModel;
import com.wl.blog.viewmodel.UserViewModel;
import com.wl.common.dao.BaseDao;
import com.wl.common.utils.JrsfReturn;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @program: blog
 * @description:
 * @author: WangLei
 * @create: 2019-07-22 18:00
 **/
@Service
public class UserService {
    @Autowired
    BaseDao dao;
    @Autowired
    StringRedisTemplate StringredisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Value("${defaultrole}")
    private  String defaultrole;
    @Transactional
    public JrsfReturn register(UserViewModel userViewModel) {
        if (!userViewModel.getPassWord().equals(userViewModel.getTruePassword())) {
            return JrsfReturn.error("密码不一致！");
        }
        QUser qUser=QUser.user;
        JPAQuery<User> userJPAQuery=new JPAQuery<>(this.dao.getEntityManager());
        User oldUser= userJPAQuery.select(qUser).from(qUser).where(qUser.loginNumber.eq(userViewModel.getLoginNumber())).fetchFirst();
        if (oldUser!=null)
        {
            return JrsfReturn.error("该登录账号已存在！");
        }
        User user = new User();
        BeanUtils.copyProperties(userViewModel, user);
        this.dao.save(user);

        UserToRole userToRole=new UserToRole();
        userToRole.setRoleId(this.defaultrole);
        userToRole.setUserId(user.getId());
        this.dao.save(userToRole);
        return JrsfReturn.okData(user);
    }
    @Transactional
    public JrsfReturn login(UserViewModel userViewModel) {
        QUser qUser = QUser.user;
        QRole qRole = QRole.role;
        QPermission qPermission = QPermission.permission;
        QRoleToPermission qRoleToPermission = QRoleToPermission.roleToPermission;
        QUserToRole qUserToRole = QUserToRole.userToRole;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(this.dao.getEntityManager());
        User user = jpaQueryFactory.selectFrom(qUser).select(qUser).where(qUser.loginNumber.eq(userViewModel.getLoginNumber())).fetchFirst();
        if (user == null) {
            return JrsfReturn.error("账号不存在！");
        }
        if (!user.getPassWord().equals(userViewModel.getPassWord())) {
            return JrsfReturn.error("密码错误！");
        }
        JPAQuery<UserViewModel> jpaQuery = new JPAQuery<>(this.dao.getEntityManager());
        Map<String, UserViewModel> map = jpaQuery.from(qUser)
                .innerJoin(qUserToRole).on(qUser.id.eq(qUserToRole.userId))
                .innerJoin(qRole).on(qRole.id.eq(qUserToRole.roleId))
                .innerJoin(qRoleToPermission).on(qRoleToPermission.roleId.eq(qRole.id))
                .innerJoin(qPermission).on(qPermission.id.eq(qRoleToPermission.permissionId))
                .where(qUser.id.eq(user.getId()))
                .orderBy(qRole.roleGrade.asc())
                .transform(GroupBy.groupBy(qUser.id).as(
                        Projections.bean(UserViewModel.class,
                                qUser.id,
                                qUser.userName,
                                qUser.portrait,
                                qUser.loginNumber,
                                GroupBy.list(Projections.bean(RoleViewModel.class
                                        , qRole.id
                                        , qRole.roleGrade
                                        , qRole.roleName
                                )).as("roleList"),
                                GroupBy.list(Projections.bean(PermissionViewModel.class,
                                        qPermission.id,
                                        qPermission.api,
                                        qPermission.icon,
                                        qPermission.permissionName,
                                        qPermission.permissionType
                                )).as("permissionList")

                        )
                ));

        UserViewModel userInfo = map.get(user.getId());
        List<RoleViewModel> roles = userInfo.getRoleList().stream().distinct().collect(Collectors.toList());
        List<PermissionViewModel> permissions = userInfo.getPermissionList().stream().distinct().collect(Collectors.toList());
        userViewModel.setRoleList(roles);
        userViewModel.setPermissionList(permissions);
        userViewModel.setMenuPermissionList(permissions.stream().filter(o -> o.getPermissionType().equals("menu")).collect(Collectors.toList()));
        userViewModel.setBtnPermissionList(permissions.stream().filter(o -> o.getPermissionType().equals("btn")).collect(Collectors.toList()));
        String uuid= UUID.randomUUID().toString().replace("-","");
        redisTemplate.opsForValue().set(uuid,userInfo,5, TimeUnit.MINUTES);//存入用户到数据库，有效期为一分钟
        setLoginStatus(userInfo);
        return JrsfReturn.okData(uuid);
    }
    /**
     * @Description: 设置登录状态
     * @Param:
     * @return:
     * @Author: Mr.Wang
     * @Date: 2019/8/27
     */

    private void setLoginStatus(UserViewModel userViewModel) {

        //添加sessionid 用户id,操作时先查询有没有该session存在，否则就把他挤下去
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String sessionId = request.getSession().getId();
        LoginStatus loginStatus = new LoginStatus();
        loginStatus.setSessionId(sessionId);
        loginStatus.setUserName(userViewModel.getLoginNumber());
        loginStatus.setId(UUID.randomUUID().toString());
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(this.dao.getEntityManager());
        QLoginStatus qLoginStatus = QLoginStatus.loginStatus;
        jpaQueryFactory.delete(qLoginStatus).where(qLoginStatus.userName.eq(userViewModel.getLoginNumber())).execute();
        this.dao.save(loginStatus);
    }
}
