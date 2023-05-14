package com.improve.shell.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.improve.shell.pojo.po.House;
import com.improve.shell.service.HouseService;
import com.improve.shell.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: fengxin
 * @CreateTime: 2023-05-14  17:06
 * @Description: TODO
 */
@Slf4j
@RestController
@RequestMapping("/index")
public class IndexController {

    @Value("${project.pageSize}")
    private String pageSize;

    @Autowired
    private HouseService houseService;

    @GetMapping("/search")
    public Result search(@RequestParam(defaultValue = "1", name = "current") Integer current,
                         @RequestParam("title") String title, @RequestParam("introduction") String introduction,
                         @RequestParam("address") String address, @RequestParam("between") String between) {
        log.info("current==>{}",current);

        IPage<House> page = houseService.search(new Page<House>(current, Long.parseLong(pageSize)),title,introduction,address,between);
        return Result.success("通过城市查询的游记列表信息",page);


    }

}
