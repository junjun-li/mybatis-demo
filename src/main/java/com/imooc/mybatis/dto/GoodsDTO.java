package com.imooc.mybatis.dto;

import com.imooc.mybatis.entity.Category;
import com.imooc.mybatis.entity.Goods;

public class GoodsDTO {
    private Goods goods = new Goods();
    private Category category = new Category();
    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "GoodsDTO{" +
            "goods=" + goods +
            ", category=" + category +
            ", test='" + test + '\'' +
            '}';
    }
}
