package com.lijiaxuan.project.repository;

import com.lijiaxuan.project.domain.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;
import java.util.Set;

public interface ShopRespository extends JpaRepository<Shop,Long>, PagingAndSortingRepository<Shop,Long> {
    //传参
    @Query(value = "select max(id) from Shop ")
    Long findMaxId();

    Page<Shop> findAll(Pageable pageable);
    List<Shop> findAllByIdIn(Set<Long> ids);

}
