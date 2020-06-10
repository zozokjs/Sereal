/**
  * File Name : ImageBrowserModel.java
  * 
  * Copyright(c) 2017 InnC Co., Ltd. 
  * All rights reserved
  */
package com.innc.system.model;

/**
  * @since : 2017-03-16
  * @author : Administator
  * @version 1.0
  * @see
  * <pre>
  * 개정이력
  * -----------------------------------------------------
  * 2017-03-16   Administator    TODO
  * </pre>
  */
public class ImageBrowserModel {
    private String name;
    private String type;
    private long size;

    public ImageBrowserModel() {
        type = "f";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the size
     */
    public long getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(long size) {
        this.size = size;
    }
}
