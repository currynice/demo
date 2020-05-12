package com.cxy.demo.transaction.repository;

import com.cxy.demo.transaction.CopyUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:   <br>
 * Date: 2020/5/12 17:04  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public interface CopyUserRepository extends JpaRepository<CopyUser, Integer> {
}
