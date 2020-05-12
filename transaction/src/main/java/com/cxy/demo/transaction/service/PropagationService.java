package com.cxy.demo.transaction.service;

import com.cxy.demo.transaction.CopyUser;
import com.cxy.demo.transaction.User;
import com.cxy.demo.transaction.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:   <br>
 * Date: 2020/5/12 16:46  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
@Service
@Slf4j
public class PropagationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CopyUserService copyUserService;

    @Transactional
    public void createUserWrong(User entity) {
        userRepository.save(entity);
        log.info("createMainUser finish");
        CopyUser copyUser = copy(entity);
        copyUserService.createSubUserWithExceptionWrong(copyUser);
    }

    private CopyUser copy(User user) {
        String userName = user.getName();
        return new CopyUser(userName);
    }

}
