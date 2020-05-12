package com.cxy.demo.transaction.service;

import com.cxy.demo.transaction.CopyUser;
import com.cxy.demo.transaction.repository.CopyUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:   <br>
 * Date: 2020/5/12 17:14  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
@Service
@Slf4j
public class CopyUserService {
    @Autowired
    private CopyUserRepository copyUserRepository;

    @Transactional
    public void createSubUserWithExceptionWrong(CopyUser entity) {
        log.info("createCopyUserWithExceptionWrong start");
        copyUserRepository.save(entity);
        throw new RuntimeException("invalid status");
    }
}
