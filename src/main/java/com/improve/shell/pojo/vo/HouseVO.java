package com.improve.shell.pojo.vo;

import com.improve.shell.pojo.po.House;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HouseVO extends House {
    List<String> images = new ArrayList<>(); //图片集

}
