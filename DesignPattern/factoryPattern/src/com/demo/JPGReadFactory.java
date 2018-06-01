package com.demo;

/**
 * FileName: JPGReadFactory
 * Author: yeyang
 * Date: 2018/6/1 11:06
 */
public class JPGReadFactory extends PhotoReadFactory {
    @Override
    Photo createPhoto() {
        Photo photo = new JPGPhoto();
        return photo;
    }
}
