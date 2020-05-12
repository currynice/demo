package com.cxy.demo.transaction.service;

import com.cxy.demo.transaction.CopyUser;
import com.cxy.demo.transaction.User;
import com.cxy.demo.transaction.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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

    //自己捕获异常进行处理的话，手动设置让当前事务处于回滚状态：
    @Transactional
    public void createUserRight1(String name) {
        try {
            userRepository.save(new User(name));
            throw new RuntimeException("error");    }
        catch (Exception ex) {
            log.error("create user failed", ex);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Transactional
    public void createUserWrong1(User entity) {
        createMainUser(entity);
        CopyUser copyUser = getCopy(entity);
        copyUserService.createCopyUserWithExceptionWrong(copyUser);

    }

    @Transactional
    public void createUserWrong2(User entity) {
        createMainUser(entity);
        CopyUser copyUser = getCopy(entity);
        try{
            copyUserService.createCopyUserWithExceptionWrong(copyUser);
        }catch (Exception ex){
            // 虽然捕获了异常，但是因为还是一个事务，没有开启新事务，当前事务因为异常已经被标记为rollback了，所以最终还是会回滚。
            log.error("create sub user error:{}", ex.getMessage());
        }

    }

    @Transactional
    public void createUserRight(User entity) {
        createMainUser(entity);
        CopyUser copyUser = getCopy(entity);
        try{
            copyUserService.createCopyUserWithExceptionRight(copyUser);
        }catch (Exception ex){
            // 子方法开启一个新事务,主方法不会回滚。
            log.error("create sub user error:{}", ex.getMessage());
        }

    }

    private void createMainUser(User entity) {
        userRepository.save(entity);
        log.info("createMainUser finish");
    }

    private CopyUser getCopy(User user) {
        String userName = user.getName();
        return new CopyUser(userName);
    }


    public Long getUserCount() {
       return userRepository.count();
    }
}
