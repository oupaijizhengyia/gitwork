package com.demo;

/**
 * FileName: PhotoReadFactory
 * Author: yeyang
 * Date: 2018/6/1 10:52
 */
public abstract class PhotoReadFactory {
    public void readPhoto(){
        Photo photo = this.createPhoto();
        photo.say();
    }
    abstract Photo createPhoto();
}
