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

public interface UserRepository extends JpaRepository<User,Long> {

}

