package com.example.bookshop.controller;

import com.example.bookshop.domain.*;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;
import com.example.bookshop.service.FavouriteService;
import com.example.bookshop.util.ReturnMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.aliyun.oss.*;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chen Anran
 * @date 2020/4/13
 */

@RestController
@RequestMapping("/favourite")
public class FavouriteController implements BaseController<Favourite>{
    @Autowired
    private FavouriteService favouriteService;

    /**
     * 添加收藏 URL: /favourite/add
     * @param favourite 待添加的收藏
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ReturnMsgUtil add(@RequestBody Favourite favourite)
    {
        try
        {
            favouriteService.add(favourite);
            //将book表中的isFavourite设置为1
            favouriteService.updateFavouriteTrue(favourite.getBook());
            return new ReturnMsgUtil(successCode,favourite.getId());
        }catch (AddException | UpdateException e){
            return new ReturnMsgUtil(failCode,e.getMessage());
        }
    }

    /**
     * 删除收藏 URL: /favourite/delete
     * @param id 待删除的收藏id
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public ReturnMsgUtil delete(@RequestParam(value = "id")Serializable id)
    {
        try
        {
            favouriteService.delete(id);
            //找到书籍id
            String bookId = favouriteService.findOneById(id).getBook();
            //将book表中的isFavourite设置为0
            favouriteService.updateFavouriteFalse(bookId);
            return new ReturnMsgUtil(successCode,"success");
        }catch (DeleteException | UpdateException e){
            return new ReturnMsgUtil(failCode,e.getMessage());
        }
    }

    /**
     * 更新收藏 URL: /favourite/update
     * @param favourite 待更新的收藏
     * @return 返回ReturnMsgUtil对象，(state, message)
     */
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public ReturnMsgUtil update(@RequestBody Favourite favourite)
    {
        try
        {
            favouriteService.update(favourite);
            return new ReturnMsgUtil(successCode,"success");
        }catch (UpdateException e){
            return new ReturnMsgUtil(failCode,e.getMessage());
        }
    }

    /**
     * 根据id查找收藏项目 URL: /favourite/findOneById
     * @param id 待查找的收藏项目id
     * @return 待查找的收藏项目
     */
    @RequestMapping(value = "/findOneById",method = RequestMethod.GET)
    public Favourite findOneById(@RequestParam(value = "id")Serializable id)
    {
        return favouriteService.findOneById(id);
    }

    /**
     * 查找所有收藏项目 URL: /favourite/findAll
     * @return 待查找的收藏项目集合
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public List<Favourite> findAll()
    {
        return favouriteService.findAll();
    }

    /**
     * 查找某一用户的收藏项目 URL: /favourite/findUserFavourite
     * @param user 待查找的用户id
     * @return 待查找的收藏项目集合
     */
    @RequestMapping(value = "/findUserFavourite",method = RequestMethod.GET)
    public List<Favourite> findUserFavourite(@RequestParam(value = "user")Serializable user)
    {
        return favouriteService.findUserFavourite(user);
    }

    /**
     * 查找收藏项目详情 URL: /favourite/findFavouriteBookDetails
     * @param id 待查找的收藏项目id
     * @return 待查找的收藏项目集合
     */
    @RequestMapping(value = "/findFavouriteBookDetails",method = RequestMethod.GET)
    public Book findFavouriteBookDetails(@RequestParam(value = "id")Serializable id)
    {
        return favouriteService.findFavouriteBookDetails(id);
    }

    /**
     * 查找某一用户的收藏详情 URL: /favourite/findFavouriteDetailsByUser
     * @param user 待查找的用户id
     * @return 待查找的收藏详情集合
     */
    @RequestMapping(value = "/findFavouriteDetailsByUser",method = RequestMethod.GET)
    public List<FavouriteDetail> findFavouriteDetailsByUser(@RequestParam(value = "user")Serializable user)
    {
        return favouriteService.findFavouriteDetailsByUser(user);
    }

    public static void uploadFile(File file,Map<String,String> infoMap)
    {
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "-----------";
        String accessKeySecret = "----------";
        String bucketName = "---------";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //设置文件名称
        String objectKey = "bookshop"+"/"+file.getName();
        // 创建PutObjectRequest对象
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectKey, file);

        // 上传文件
        ossClient.putObject(putObjectRequest);

        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        //图片的存储url
        String url = ossClient.generatePresignedUrl(bucketName,objectKey,expiration).toString();

        infoMap.put(url,file.getName());

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public void find()
    {
        File filepath = new File("/Users/chenanran/Downloads/static/images");
        File[] files = filepath.listFiles();
        Map<String,String> infoMap = new HashMap<>();

        for (File file : files)
        {
            if(file != null)
                uploadFile(file, infoMap);
        }

        for(Map.Entry<String ,String> iter:infoMap.entrySet())
        {
            String imageUrl = iter.getKey();
            String image = iter.getValue();
            favouriteService.find(imageUrl,image);
        }

    }
}