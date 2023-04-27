package com.improve.shell.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.improve.shell.mapper.HouseImagesMapper;
import com.improve.shell.mapper.HouseMapper;
import com.improve.shell.mapper.HouseWithUserMapper;
import com.improve.shell.pojo.po.House;
import com.improve.shell.pojo.po.HouseImages;
import com.improve.shell.pojo.po.HouseWithUser;
import com.improve.shell.pojo.vo.HouseVO;
import com.improve.shell.service.HouseWithUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseWithUserServiceImp extends ServiceImpl<HouseWithUserMapper, HouseWithUser> implements HouseWithUserService {

    @Autowired
    private HouseWithUserMapper houseWithUserMapper;

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private HouseImagesMapper houseImagesMapper;
    /**
     * 通过houseId判断房屋信息是否是自己发表，为删除操作提供判定条件
     * @param houseId
     * @return
     */
    @Override
    public boolean isMyPublic(String houseId) {

        LambdaQueryWrapper<HouseWithUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HouseWithUser::getHouseId,houseId);
        //获取HouseWithUser对象
        HouseWithUser houseWithUser = houseWithUserMapper.selectOne(queryWrapper);
        //查看isOwn属性，若为true则为自己发表，返回true；否则，返回false
        if(houseWithUser.getIsOwn()){
            return true;
        }
        return false;
    }

    /**
     * 获取用户发表的所有发房屋信息
     * @param uid
     * @return
     */
    @Override
    public List<HouseVO> getMyPublic(Long uid) {
       //添加查询条件，通过用户id 和 是否为用户自己发表房屋信息的isOwn属性 ，来获取HouseWithUser类型的集合
        LambdaQueryWrapper<HouseWithUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HouseWithUser::getIsOwn,true).eq(HouseWithUser::getUid,uid);

        //根据查询信息，获取HouseWithUser的集合
        List<HouseWithUser> houseWithUsers = houseWithUserMapper.selectList(queryWrapper);

        ArrayList<HouseVO> houseVOS = new ArrayList<>();

        //循环遍历houseWithUsers集合
        for(HouseWithUser houseWithUser : houseWithUsers){

            //通过houseWithUser对象获取其houseId
            String houseId = houseWithUser.getHouseId();

            HouseVO houseVO = new HouseVO();

            //通过houseId在house表中查找对应的house对象，并将其对象拷贝在houseVO中
            House house = houseMapper.selectById(houseId);
            BeanUtils.copyProperties(house,houseVO);

            //通过houseId在house_images表中查找对应的houseImages对象的集合
            LambdaQueryWrapper<HouseImages> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(HouseImages::getHouseId,houseId);
            List<HouseImages> houseImages = houseImagesMapper.selectList(wrapper);

            //创建图像集合
            ArrayList<String> images = new ArrayList<>();


            //循环houseImages对象的集合，将其对应的所有图片信息放入在图像集合images中
            for(HouseImages houseImages1 : houseImages){
                images.add(houseImages1.getImageUrl());
            }

            //将图片集放入houseVO对象中
            houseVO.setImages(images);

            //将houseVO放入至houseVOS集合中
            houseVOS.add(houseVO);
        }

        //判断houseVOS集合是否不为空，若不为空，则返回HouseVO对象的集合；否则，返回空
         if(houseVOS.size() != 0){
             return houseVOS;
         }
        return null;
    }

    /**
     * 点击收藏
     * @param houseId  房屋id
     * @param uid      用户id
     * @return
     */
    @Override
    public Boolean ClickCollect(String houseId, Long uid) {

        //添加查询条件，通过 houseId 和 uid 在houseWithUser表中查找对应的houseWithUser对象
        LambdaQueryWrapper<HouseWithUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HouseWithUser::getHouseId,houseId).eq(HouseWithUser::getUid,uid);
        HouseWithUser houseWithUser = houseWithUserMapper.selectOne(queryWrapper);

        //判断后续操作是否成功的标志，0表示不成功，1代表成功
        int flag = 0;

        //若查询到的houseWithUser对象不为空
        if(houseWithUser != null){
             //此房屋信息不是用户所发表 且 其并未收藏此房屋信息
            if(houseWithUser.getIsOwn()== false && houseWithUser.getIsCollect() == false){
                //则将isCollect属性设置为true
                houseWithUser.setIsCollect(true);
                //更新houseWithUser属性，若操作成功，会返回1给flag，否则flag还是0
                flag = houseWithUserMapper.updateById(houseWithUser);
            }
        }else{
            //若查询的用户为空，则创建一个HuouseWithUser对象
            HouseWithUser houseWithUser1 = new HouseWithUser();
            houseWithUser1.setHouseId(houseId);
            houseWithUser1.setUid(uid);
            //将isCollect属性置为true
            houseWithUser1.setIsCollect(true);

            //将创建的houseWithUser对象插入至数据库中
           flag = houseWithUserMapper.insert(houseWithUser1);
        }

        //如果flag大于0，说明操作成功，返回true；否则，返回false
        if(flag>0)
           return true;
        return false;
    }

    /**
     * 取消收藏
     * @param houseId
     * @param uid
     * @return
     */
    @Override
    public Boolean CancelCollect(String houseId, Long uid) {
        //添加查询条件，通过 houseId 和 uid 在houseWithUser表中查找对应的houseWithUser对象
        LambdaQueryWrapper<HouseWithUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HouseWithUser::getHouseId,houseId).eq(HouseWithUser::getUid,uid);
        HouseWithUser houseWithUser = houseWithUserMapper.selectOne(queryWrapper);

        //判断后续操作是否成功的标志，0表示不成功，1代表成功
        int flag = 0;

        //此房屋信息不是用户所发表 且 其已收藏此房屋信息
        if (houseWithUser.getIsOwn()== false && houseWithUser.getIsCollect() == true) {
             //将isCollect属性设置为false
            houseWithUser.setIsCollect(false);
            //更新houseWithUser属性，若操作成功，会返回1给flag，否则flag还是0
            flag = houseWithUserMapper.updateById(houseWithUser);
        }

        //如果flag大于0，说明操作成功，返回true；否则，返回false
        if(flag>0)
            return true;
        return false;
    }

    /**
     * 查看用户的所有收藏房屋信息
     * @param uid
     * @return
     */
    @Override
    public List<HouseVO> AllMyCollect(Long uid) {

        //添加查询数据，通过用户uid 和 用户已收藏isCollect属性 查询用户的houseWithUsers对象的集合
        LambdaQueryWrapper<HouseWithUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HouseWithUser::getUid,uid).eq(HouseWithUser::getIsCollect,true);

        //根据查询条件，获取HouseWithUser对象的集合
        List<HouseWithUser> houseWithUsers = houseWithUserMapper.selectList(queryWrapper);

        ArrayList<HouseVO> houseVOS = new ArrayList<>();

        //循环遍历houseWithUsers集合对象
        for(HouseWithUser houseWithUser : houseWithUsers){
            //通过houWithUser获取houseId
            String houseId = houseWithUser.getHouseId();

            HouseVO houseVO = new HouseVO();

            //通过houseId在house表中查找对应的house对象，并将其对象拷贝在houseVO中
            House house = houseMapper.selectById(houseId);
            BeanUtils.copyProperties(house,houseVO);

            //通过houseId在house_images表中查找对应的houseImages对象的集合
            LambdaQueryWrapper<HouseImages> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(HouseImages::getHouseId,houseId);
            List<HouseImages> houseImages = houseImagesMapper.selectList(wrapper);

            //创建图像集合
            ArrayList<String> images = new ArrayList<>();

            //循环houseImages对象的集合，将其对应的所有图片信息放入在图像集合images中
            for(HouseImages houseImages1 : houseImages){
                images.add(houseImages1.getImageUrl());
            }

            //将图片集放入houseVO对象中
            houseVO.setImages(images);
            //将houseVO放入至houseVOS集合中
            houseVOS.add(houseVO);
        }

        //判断houseVOS集合是否不为空，若不为空，则返回HouseVO对象的集合；否则，返回空
        if(houseVOS.size() != 0){
            return houseVOS;
        }
        return null;

    }
}
