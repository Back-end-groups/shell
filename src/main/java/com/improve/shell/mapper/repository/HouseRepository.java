package com.improve.shell.mapper.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.improve.shell.pojo.po.House;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: fengxin
 * @CreateTime: 2023-05-14  14:53
 * @Description: TODO
 */

@Repository
public interface HouseRepository extends ElasticsearchRepository<House, String> {

    // 模糊查询
    IPage<House> findByTitleContainingOrIntroductionContainingOrAddressContainingOrTotalPriceBetween(
            Page<House> page, String title, String introduction, String address, int start, int end
    );


}
