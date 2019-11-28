//package com.bms.framework.filter;
//
//
//import cn.hutool.core.util.StrUtil;
//import com.bms.base.dao.SystemSetupDao;
//import com.bms.base.entity.result.UserLoginInfo;
//import com.bms.common.service.RedisService;
//import com.cxy.demo.demointerceptor.WebUtil;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 判断用户信息是否已被后台更改，并根据更改的情况(version变化) 清除token
// * cxy
// */
//@Slf4j
//public class VersionJudgeInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private SystemSetupDao systemSetupDao;
//
//    @Autowired
//    private RedisService redisService;
//
//
//    @Value("${databaseName}")
//    private String databaseName;
//
//    //请求之后
//    @Override
//    public void afterCompletion(HttpServletRequest request,
//                                HttpServletResponse response, Object obj, Exception e)
//            throws Exception {
//
//
//    }
//
//    //处理之后
//    @Override
//    public void postHandle(HttpServletRequest request,
//                           HttpServletResponse response, Object obj, ModelAndView mv)
//            throws Exception {
//
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response, Object obj) throws Exception {
//
//        Object s = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserLoginInfo currentUser = (UserLoginInfo)s;
//        String authToken = request.getHeader("$authToken");
//        //token里保密编号和version要有
//        if (currentUser != null && StrUtil.isNotEmpty(currentUser.getSecretCode())
//                && null != currentUser.getVersion() && null != authToken) {
//            // 获取数据库中的用户version
//            Long userversion = this.systemSetupDao.findUserVersion(databaseName, currentUser.getSecretCode());
//            // 对比
//            if (userversion != null
//                    && String.valueOf(userversion).equals(
//                    String.valueOf(currentUser.getVersion()))) {
//                return true;
//            } else {
//                // 删token,后续配置了返回json
//                redisService.del(authToken);
//                isAjaxResponse(request, response);
//            }
//        }
//        return false;
//    }
//
//
//
//
//    private boolean isAjaxResponse(HttpServletRequest request,
//                                   HttpServletResponse response) throws IOException {
//        // ajax请求
//        /**
//         * 判断是否已经踢出
//         * 1.如果是Ajax 访问，那么给予json返回值提示。
//         * 2.如果是普通请求（本项目不存在）
//         */
//        //判断是不是Ajax请求
//        if (WebUtil.isAjax(request) ) {
//            Map<String,Object> map = new HashMap<>();
//            ObjectMapper objectMapper = new ObjectMapper();
//            map.put("code", 203);
//            map.put("msg", "权限信息变更或失效,请重新登陆");
//            // 跳转地址
//            map.put("data","/index.html");
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/json");
//            response.setStatus(203);
//            PrintWriter out = response.getWriter();
//            out.write(objectMapper.writeValueAsString(map));
//            out.flush();
//            out.close();
//        }else{
//            // url连接，也返回json,不做重定向
//            Map<String,Object> map = new HashMap<>();
//            ObjectMapper objectMapper = new ObjectMapper();
//            map.put("code", 203);
//            map.put("msg", "权限信息变更或失效,请重新登陆");
//            // 跳转地址
//            map.put("data","/index.html");
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/json");
//            response.setStatus(203);
//            PrintWriter out = response.getWriter();
//            out.write(objectMapper.writeValueAsString(map));
//            out.flush();
//            out.close();
//
//        }
//        return false;
//    }
//
//
//
//}
