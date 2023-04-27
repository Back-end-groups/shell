package com.improve.shell.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.improve.shell.handler.NoAuth;
import com.improve.shell.handler.UserThreadLocal;
import com.improve.shell.pojo.po.House;
import com.improve.shell.pojo.po.HouseImages;
import com.improve.shell.pojo.po.HouseWithUser;
import com.improve.shell.pojo.po.User;
import com.improve.shell.pojo.vo.HouseVO;
import com.improve.shell.pojo.vo.UserVO;
import com.improve.shell.service.HouseImagesService;
import com.improve.shell.service.HouseService;
import com.improve.shell.service.HouseWithUserService;
import com.improve.shell.service.UserService;
import com.improve.shell.util.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/house")
public class HouseControlller {

    @Autowired
    private HouseService houseService;

    @Autowired
    private HouseWithUserService houseWithUserService;

    @Autowired
    private HouseImagesService houseImagesService;
    /**
     * 查询单个房屋信息，根据前端传入的房屋ID获取房屋信息并返回
     * @param houseId
     * @return
     */

    @GetMapping("/{houseId}")
    public Result getOneHouse(@PathVariable String houseId){
        House room = houseService.getById(houseId);
        List<String> allImages = houseImagesService.getAllImages(houseId);
        if(room != null ){
            HouseVO houseVO = new HouseVO();
            BeanUtils.copyProperties(room,houseVO);
            houseVO.setImages(allImages);
            return Result.success("用户查询成功",houseVO);
        }
        return Result.fail("用户查询失败");
    }

    /**
     * 以分页形式将房屋所有信息返回给前端
     * @param currentPage  当前页码值
     * @param flushTime    用户刷新页面时间
     * @return
     */
    @GetMapping("/page")
    public Result page(int currentPage,String flushTime){
        Page<House> housePage = new Page<>(currentPage,5);
        Page<HouseVO> houseVOPage = new Page<>();

        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime FlushTime = LocalDateTime.parse(flushTime, dateTime);

        LambdaQueryWrapper<House> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(House::getListing).lt(House::getListing,FlushTime);
        houseService.page(housePage,queryWrapper);
        BeanUtils.copyProperties(housePage,houseVOPage,"records");

        List<House> records = housePage.getRecords();
        ArrayList<HouseVO> houseVOS = new ArrayList<>();
        for(House house : records){
            HouseVO houseVO = new HouseVO();
            BeanUtils.copyProperties(house,houseVO);
            List<String> allImages = houseImagesService.getAllImages(house.getId());
            houseVO.setImages(allImages);
            houseVOS.add(houseVO);
        }
        houseVOPage.setRecords(houseVOS);
        return Result.success("房屋信息查询成功",houseVOPage);
    }

    /**
     * 查看用户所有的收藏列表
     */
    @NoAuth
    @GetMapping("/allMyCollect")
    public Result getAllMyCollect(Long uid){
        List<HouseVO> houseVOS = houseWithUserService.AllMyCollect(uid);
        return Result.success("查询用户的收藏房屋信息成功",houseVOS);
    }

    /**
     * 查询用户所有发表的房屋信息
     * @param uid
     * @return
     */
    @NoAuth
    @GetMapping("/myPublic")
    public Result allMyPublic(Integer uid){
        long Uid = uid.longValue();
        List<HouseVO> myPublic = houseWithUserService.getMyPublic(Uid);
        return Result.success("查询用户所有发表房屋信息成功",myPublic);
    }

    /**
     * 添加房屋信息
     * 先将房屋house保存在数据库中,根据Id获取HouseImages对象，
     * 判断对象是否为空，若为空则创建HouseImages对象，将houseId与图片之间关系插入到house_images表中
     * 若对象不为空，则通过houseId拿到其所对应的所有imageUrl,将已存在数据库中的图片与前端传入的图片集进行比对，若没有，则将其加入到数据库中
     * @param houseVO
     * @return
     */
    @NoAuth
    @PostMapping("/save")
    public Result saveHouseWithImages(@RequestBody HouseVO houseVO) {
        UserVO userVO = UserThreadLocal.get();
//        String id = String.valueOf(userVO.getId());
        String id ="1111";

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String localTime = df.format(now);

        String hId = localTime + id;

        if (StringUtils.isNotBlank(id)) {
            houseVO.setId(hId);
            houseService.save(houseVO); //先保存房屋信息

            HouseWithUser houseWithUser = new HouseWithUser();
            houseWithUser.setHouseId(houseVO.getId());
//            houseWithUser.setUid(userVO.getId());
            houseWithUser.setIsOwn(true);
        houseWithUser.setUid(111111L);
            houseWithUserService.save(houseWithUser);  //保存房屋 与 用户 之间关系

            List<String> images = houseVO.getImages();  //房子图片
            String voId = houseVO.getId();  //房子Id

            ArrayList<HouseImages> houseImages = new ArrayList<>();
            for (String image : images) {
                HouseImages houseImages1 = new HouseImages();
                houseImages1.setHouseId(voId);
                houseImages1.setImageUrl(image);
                houseImages.add(houseImages1);
            }

            houseImagesService.saveBatch(houseImages); //保存房屋图片 与 房屋 之间关系

            return Result.success("房屋添加成功");
        }
        return Result.fail("用户id为空，请重新获取");
    }

    /**
     * 修改房屋信息
     * @param houseVO
     * @return
     */
    @PutMapping("/update")
    @NoAuth
    public Result updateHouse(@RequestBody HouseVO houseVO){
        houseService.updateById(houseVO);
        LambdaQueryWrapper<HouseImages> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HouseImages::getHouseId,houseVO.getId());
        List<String> images = houseVO.getImages();
        houseImagesService.remove(queryWrapper);
        ArrayList<HouseImages> list = new ArrayList<>();
            for(String image : images){
               HouseImages houseImages = new HouseImages();
               houseImages.setHouseId(houseVO.getId());
               houseImages.setImageUrl(image);
               list.add(houseImages);
           }
        houseImagesService.saveBatch(list);

        return Result.success("房屋信息修改成功");
    }

    /**
     * 点击收藏
     * @param houseId  房屋id
     * @param uid      用户id
     * @return
     */
    @NoAuth
    @PostMapping("/clickCollect")
    public Result clickCollect(String houseId,Integer uid){
        long Uid = uid.longValue();
        Boolean clickCollect = houseWithUserService.ClickCollect(houseId, Uid);
        if(clickCollect)
            return Result.success("收藏成功");
        return Result.fail("收藏失败");
    }

    /**
     * 取消收藏
     * @param houseId    房屋id
     * @param uid        用户id
     * @return
     */
    @PutMapping("/cancelCollect")
    public Result cancelCollect(String houseId,Integer uid){
        long Uid = uid.longValue();
        Boolean cancelCollect = houseWithUserService.CancelCollect(houseId, Uid);
        if(cancelCollect)
            return Result.success("取消收藏成功");
        return Result.fail("取消收藏失败");
    }


    /**
     * 删除房屋信息
     * @param houseId   房屋id
     * @return
     */
    @DeleteMapping("/delete/{houseId}")
    @NoAuth
    public Result deleteHouseWithImages(@PathVariable("houseId") String houseId) {

        //先判断房屋信息是否是本人发表，若是才有删除权限
        if (houseWithUserService.isMyPublic(houseId)) {

            //删除房屋信息
             houseService.removeById(houseId);
            LambdaQueryWrapper<HouseImages> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(HouseImages::getHouseId, houseId);

            //删除房屋以及对应的房屋图片信息
            houseImagesService.remove(lambdaQueryWrapper);

            //删除房屋以及用户之间的关系
            LambdaQueryWrapper<HouseWithUser> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(HouseWithUser::getHouseId, houseId);
            houseWithUserService.remove(queryWrapper);
            return Result.success("房屋删除成功");
        }
        return Result.fail("你并没有权限删除此房屋信息");
    }




}
