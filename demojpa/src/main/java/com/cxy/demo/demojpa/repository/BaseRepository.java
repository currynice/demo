package com.cxy.demo.demojpa.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 通用Repository
 * @param <T>
 * @param <Long>
 */
@NoRepositoryBean//不需要创建bean
public interface BaseRepository<T,Long> extends PagingAndSortingRepository<T,Long> {

    //按UpdateTime降序,id升序
    List<T> findTop3ByOrderByUpdateTimeDescIdAsc();
}
