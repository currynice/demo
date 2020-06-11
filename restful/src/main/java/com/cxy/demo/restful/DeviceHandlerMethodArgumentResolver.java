package com.cxy.demo.restful;


import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * Description:   <br>
 * Date: 2020/6/11 11:24  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class DeviceHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(MyDevice.class);
    }


    /**
     * 将header中的自定义参数解析成 MyDevice 对象
     * @param methodParameter
     * @param modelAndViewContainer
     * @param nativeWebRequest
     * @param webDataBinderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        MyDevice myDevice = new MyDevice();
        myDevice.setType(nativeWebRequest.getHeader("device.type"));
        myDevice.setVersion(nativeWebRequest.getHeader("device.version"));
        myDevice.setScreen(nativeWebRequest.getHeader("device.screen"));
        return myDevice;
    }
}
