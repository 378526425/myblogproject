package com.wl.blog.service;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wl.blog.server.entity.User;
import com.wl.blog.server.entity.querydsl.QUser;
import com.wl.blog.viewmodel.UserViewModel;
import com.wl.common.dao.BaseDao;
import com.wl.common.utils.JrsfReturn;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
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

    @Transactional
    public JrsfReturn register(UserViewModel userViewModel) {
        if (!userViewModel.getPassWord().equals(userViewModel.getTruePassword())) {
            return JrsfReturn.error("密码不一致！");
        }
        User user = new User();
        BeanUtils.copyProperties(userViewModel, user);
        this.dao.save(user);
        return JrsfReturn.okData(user);
    }

    public JrsfReturn login(UserViewModel userViewModel, HttpServletRequest request) {
        QUser qUser = QUser.user;
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(this.dao.getEntityManager());
        User user = jpaQueryFactory.selectFrom(qUser).select(qUser).where(qUser.loginNumber.eq(userViewModel.getLoginNumber())).fetchFirst();
        if (user==null)
        {
            return JrsfReturn.error("账号不存在！");
        }
        if (!user.getPassWord().equals(userViewModel.getPassWord()))
        {
            return JrsfReturn.error("密码错误！");
        }
        request.getSession().setAttribute("user",user);
        return JrsfReturn.okMsg("登录成功！");
    }
}
