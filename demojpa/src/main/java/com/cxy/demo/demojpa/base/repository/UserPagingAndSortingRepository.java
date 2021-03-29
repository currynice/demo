package com.cxy.demo.demojpa.base.repository;

/**
 * Description:   </br>
 * Date: 2021/3/21 20:49
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */

import com.cxy.demo.demojpa.base.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserPagingAndSortingRepository extends PagingAndSortingRepository<User,Long> {

}

