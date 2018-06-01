package com.demo;

/**
 * FileName: GIFFactory
 * Author: yeyang
 * Date: 2018/6/1 11:07
 */
public class GIFReadFactory extends PhotoReadFactory {
    @Override
    Photo createPhoto() {
        Photo photo = new GIFPhoto();
        return photo;
    }
}
