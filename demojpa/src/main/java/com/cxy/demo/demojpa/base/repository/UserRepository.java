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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    //JPQL
    @Query("From User where name=:name")
    User findByQuery(@Param("name")String nameParam);
}

