package com.example.bookshop.service.Impl;

import com.example.bookshop.dao.TypeDao;
import com.example.bookshop.domain.Type;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;
import com.example.bookshop.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alu
 * @date 2020/4/17 0:31
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;

    @Override
    public void add(Type type) throws AddException {
        if (type == null || findOneById(type.getId()) != null) {
            throw new AddException("添加类别错误：id重复");
        } else if (findOneByName(type.getCname()) != null || findOneByName(type.getEname()) != null) {
            throw new AddException("添加类别错误：已含有此类别");
        }

        int result;
        try {
            result = typeDao.add(type);
        } catch (Exception e) {
            System.out.println("添加类别失败");
            e.printStackTrace();
            throw new AddException("添加类别失败：" + e.getMessage());
        }
        if (result > 0) System.out.println("添加类别成功");
    }

    @Override
    public void delete(Serializable id) throws DeleteException {
        if (id == null) throw new DeleteException("删除类别错误：id为空");

        int result;
        try {
            result = typeDao.delete(id);
        } catch (Exception e) {
            System.out.println("删除类别错误");
            e.printStackTrace();
            throw new DeleteException("删除类别失败：" + e.getMessage());
        }
        if (result > 0) System.out.println("删除类别成功");
    }

    @Override
    public void update(Type type) throws UpdateException {
        if (type == null) {
            throw new UpdateException("更新类别错误：id为空");
        } else if (findOneByName(type.getCname()) != null || findOneByName(type.getEname()) != null) {
            throw new UpdateException("更新类别错误：已含有此类别");
        }

        int result;
        try {
            result = typeDao.update(type);
        } catch (Exception e) {
            System.out.println("更新类别失败");
            e.printStackTrace();
            throw new UpdateException("更新类别失败：" + e.getMessage());
        }
        if (result > 0) System.out.println("更新类别成功");
    }

    @Override
    public Type findOneById(Serializable id) {
        if (id == null) return null;

        return typeDao.findOneById(id);
    }

    @Override
    public List<Type> findAll() {
        return typeDao.findAll();
    }

    @Override
    public int getTypeCount() {
        return typeDao.getTypeCount();
    }

    @Override
    public Type findOneByName(String name) {
        if (name == null || name.length() == 0) return null;

        return typeDao.findOneByName(name);
    }

    @Override
    public int[] getTypeIds() {
        return typeDao.getTypeIds();
    }

    @Override
    public String getENameById(Serializable id) {
        return typeDao.getENameById(id);
    }

    @Override
    public String getCNameById(Serializable id) {
        return typeDao.getCNameById(id);
    }
}
