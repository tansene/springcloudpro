package com.tansene.shiro;

import com.tansene.annotation.CurrentUser;
import com.tansene.exception.UnauthorizedException;
import com.tansene.entity.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 *  增加方法注入，将含有 @CurrentUser 注解的方法参数注入当前登录用户
 *  （策略接口，用于在给定请求的上下文中将方法参数解析为实参值）
 * @author tansene
 * @since 2020/11/22
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //用于判定是否需要处理该参数分解，返回true为需要，并会去调用下面的方法resolveArgument
        return parameter.getParameterType().isAssignableFrom(User.class)
                && parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        User user = (User) webRequest.getAttribute("currentUser", RequestAttributes.SCOPE_REQUEST);
        if (user == null) {
            throw new UnauthorizedException("获取用户信息失败");
        }
        return user;
    }
}
