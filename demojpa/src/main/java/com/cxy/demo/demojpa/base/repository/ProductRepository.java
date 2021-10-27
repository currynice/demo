package com.cxy.demo.demojpa.base.repository;

import com.cxy.demo.demojpa.base.streamables.Product;
import com.cxy.demo.demojpa.base.streamables.Products;
import org.springframework.data.repository.Repository;

/**
 * Description:   </br>
 * Date: 2021/3/31 15:56
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public interface ProductRepository extends Repository<Product, Long> {

    //直接返回自定义的Streamable<Product>
    Products findAllByDescriptionContaining(String text);
}
