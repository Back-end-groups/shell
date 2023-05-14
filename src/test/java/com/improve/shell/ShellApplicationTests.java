package com.improve.shell;

import com.improve.shell.mapper.HouseMapper;
import com.improve.shell.mapper.repository.HouseRepository;
import com.improve.shell.pojo.po.House;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShellApplicationTests {

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private HouseRepository houseRepository;

    @Test
    void save() {
        House house = new House();
        house.setId("10001");
        house.setTitle("中国最好的房屋");
        house.setIntroduction("选我你不会后悔！");
        house.setAddress("江西省南昌市");

        House save = houseRepository.save(house);
        System.out.println("save = " + save);

    }



    @Test
    void findAll() {
        Iterable<House> houses = houseRepository.findAll();
        for (House house : houses) {
            System.out.println(house);
        }
    }

    /*手动同步MySQL数据库和es索引库*/
    @Test
    void synchronousData() {
        List<House> houses = houseMapper.selectList(null);
        for (House house : houses) {
            System.out.println(house);
            houseRepository.save(house);
        }
    }

}
