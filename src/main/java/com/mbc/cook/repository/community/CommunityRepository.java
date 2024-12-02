package com.mbc.cook.repository.community;

import com.mbc.cook.entity.community.CommunityEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<CommunityEntity,Long> {

    @Override
    List<CommunityEntity> findAll();

    @Transactional
    @Modifying
    @Query(value = "update cookcommunity set community_readcnt = community_readcnt + 1 where cookcommunity.community_num=:num",nativeQuery = true)
    int readcntUp(@Param("num") long num);

    @Transactional
    @Modifying
    @Query(value = "update cookcommunity set community_title=:title, community_content=:content, community_update_date=:update_date, community_readcnt = 0 where cookcommunity.community_num=:num",nativeQuery = true)
    void updateSave(@Param("num") long num, @Param("title") String title, @Param("content") String content, @Param("update_date") LocalDateTime update_date);
}