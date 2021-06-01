package com.cxy.demo.imagestore;

import com.cxy.demo.imagestore.service.AliyunImageStore;
import com.cxy.demo.imagestore.service.ImageStore;
import lombok.var;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.naming.spi.InitialContextFactory;
import javax.sql.rowset.RowSetFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Description:    </br>
 * Date: 2021/6/1 9:59
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
@Component
public class ImageStoreFactory implements InitializingBean {




    private Map<String,ImageStore> imageStoreMap = new HashMap<>();


    public ImageStore getImageStore(String type){
      return  imageStoreMap.get(type);
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        var imageStoreServiceLoader = ServiceLoader.load(ImageStore.class);
        var imageStoreServiceIterator = imageStoreServiceLoader.iterator();

        /**
         * 获取自定义的  imageStore
         */
        while (imageStoreServiceIterator.hasNext()) {
            var each = imageStoreServiceIterator.next();
            imageStoreMap.put(each.getType(), each);
        }

        /**
         * 查找系统默认的 todo
         */
        System.out.println();


    }
}
