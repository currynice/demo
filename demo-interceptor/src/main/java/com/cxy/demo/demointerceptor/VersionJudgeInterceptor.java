//package com.cxy.demo.demointerceptor;
//
//
//import cn.hutool.core.util.StrUtil;
//import com.bms.base.dao.SystemSetupDao;
//import com.bms.base.entity.result.UserLoginInfo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 判断用户信息是否已被后台更改，并根据更改的情况(version变化) 清除token
// * cxy
// */
//@Slf4j
//public class VersionJudgeInterceptor implements HandlerInterceptor {
//
//        @Autowired
//        private SystemSetupDao systemSetupDao;
//
//        @Autowired
//        private RedisTemplate<String,Object> redisTemplate;
//
//
//         @Value("${databaseName}")
//         private String databaseName;
//
//        //请求之后
//        @Override
//        public void afterCompletion(HttpServletRequest request,
//                                    HttpServletResponse response, Object obj, Exception e)
//                throws Exception {
//
//
//        }
//
//        //处理之后
//        @Override
//        public void postHandle(HttpServletRequest request,
//                               HttpServletResponse response, Object obj, ModelAndView mv)
//                throws Exception {
//
//        }
//
//        @Override
//        public boolean preHandle(HttpServletRequest request,
//                                 HttpServletResponse response, Object obj) throws Exception {
//
//            // 获取用户信息
//            UserLoginInfo currentUser =  (UserLoginInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            //token里保密编号和version要有
//            if (currentUser != null && StrUtil.isNotEmpty(currentUser.getSecretCode())
//                    && null != currentUser.getVersion()&& null != currentUser.getAuthToken()) {
//                // 获取数据库中的用户version
//                Long userversion = this.systemSetupDao.findUserVersion(databaseName,currentUser.getSecretCode());
//                // 对比
//                if (userversion != null
//                        && null != currentUser.getVersion()
//                        && String.valueOf(userversion).equals(
//                        String.valueOf(currentUser.getVersion()))) {
//                    //ok
//                    return true;
//                }else{
//                    // 删token,后续配置了返回json
//                    redisTemplate.delete(currentUser.getAuthToken());
//                }
//            }
//            return false;
//        }
//
//
//    }
