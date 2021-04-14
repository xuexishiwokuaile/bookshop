package com.example.bookshop.service.Impl;

import com.example.bookshop.dao.SalesDao;
import com.example.bookshop.domain.Book;
import com.example.bookshop.domain.Sales;
import com.example.bookshop.exception.AddException;
import com.example.bookshop.exception.DeleteException;
import com.example.bookshop.exception.UpdateException;
import com.example.bookshop.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alu
 * @date 2020/4/8 22:06
 */
@Service
public class SalesServiceImpl implements SalesService {
    @Autowired
    private SalesDao salesDao;

    @Override
    public void add(Sales sales) throws AddException {
        if (sales == null) {
            throw new AddException("添加书籍销售信息错误：对象为空");
        } else if (salesDao.findOneById(sales.getBook()) != null) {
            throw new AddException("添加书籍销售信息错误：书籍销售信息已存在");
        } else if (sales.getCount() < 0) {
            throw new AddException("添加书籍销售信息错误：书籍数量应为非负数");
        } else if (sales.getHot() > 2 || sales.getHot() < 0) {
            throw new AddException("添加书籍销售信息错误：热门范围不正确，应为0-2，0：未知；1：不热门；2：热门");
        } else if (sales.getState() > 2 || sales.getState() < 0) {
            throw new AddException("添加书籍销售信息错误：书籍状态不正确，应为0-2，状态：0：在售；1：下架；2：预售");
        }

        int result;
        try {
            result = salesDao.add(sales);
        } catch (Exception e) {
            System.out.println("添加书籍销售信息失败");
            e.printStackTrace();
            throw new AddException("添加书籍销售信息失败：" + e.getMessage());
        }
        if (result > 0) System.out.println("添加书籍销售信息成功");
}

    @Override
    public void delete(Serializable book) throws DeleteException {
        if (book == null) {
            throw new DeleteException("删除书籍销售信息错误：对象为空");
        }

        int result;
        try {
            result = salesDao.delete(book);
        } catch (Exception e) {
            System.out.println("删除书籍销售信息失败");
            e.printStackTrace();
            throw new DeleteException("删除书籍销售信息失败：" + e.getMessage());
        }
        if (result > 0) System.out.println("删除书籍销售信息成功");
    }

    @Override
    public void update(Sales sales) throws UpdateException {
        if (sales == null) {
            throw new UpdateException("更新书籍销售信息错误：对象为空");
        } else if (sales.getCount() < 0) {
            throw new UpdateException("更新书籍销售信息错误：书籍数量应为非负数");
        } else if (sales.getHot() > 2 || sales.getHot() < 0) {
            throw new UpdateException("更新书籍销售信息错误：热门范围不正确，应为0-2，0：未知；1：不热门；2：热门");
        } else if (sales.getState() > 2 || sales.getState() < 0) {
            throw new UpdateException("更新书籍销售信息错误：书籍状态不正确，应为0-2，状态：0：在售；1：下架；2：预售");
        }

        int result;
        try {
            result = salesDao.update(sales);
        } catch (Exception e) {
            System.out.println("更新书籍销售信息失败");
            e.printStackTrace();
            throw new UpdateException("更新书籍销售信息失败：" + e.getMessage());
        }
        if (result > 0) System.out.println("更新书籍销售信息成功");
    }

    @Override
    public Sales findOneById(Serializable book) {
        return salesDao.findOneById(book);
    }

    @Override
    public List<Sales> findAll() {
        return salesDao.findAll();
    }

    @Override
    public int findStockById(Serializable book) {
        return salesDao.findStockById(book);
    }


    @Override
    public List<Book> findHot() {
        return salesDao.findHot();
    }
}
