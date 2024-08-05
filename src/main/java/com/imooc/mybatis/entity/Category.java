package com.imooc.mybatis.entity;

public class Category {

    private long categoryId;
    private String categoryName;
    private long parentId;
    private long categoryLevel;
    private long categoryOrder;


    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }


    public long getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(long categoryLevel) {
        this.categoryLevel = categoryLevel;
    }


    public long getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(long categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

}
