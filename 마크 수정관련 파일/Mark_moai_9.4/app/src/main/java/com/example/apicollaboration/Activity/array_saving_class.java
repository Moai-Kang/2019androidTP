package com.example.apicollaboration.Activity;

import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class array_saving_class {

    public static ArrayList<TMapPoint> alTMapPoint = new ArrayList<>();
    public static ArrayList<String> nameOfIt = new ArrayList<>();
    public static ArrayList<String> addressOfIt = new ArrayList<>();
    public static ArrayList<String> final_location = new ArrayList<>();
    public static ArrayList<TMapPoint> final_Point = new ArrayList<>();


    //가운데 좌표를 구하기 위한 똥꼬쇼
    public static TMapPoint center_point = new TMapPoint(0,0);
    public static String center_location;
    public static double tempX;
    public static double tempY;
    public static boolean centerOfIt = false;
    public static String center_address;

    public static  ArrayList<String> infraList = new ArrayList<>();
    public static boolean infraFlag = false;

    public static boolean fuck = false;

    public static ArrayList<TMapPoint> centerPointArr = new ArrayList<>();
    public static ArrayList<TMapPOIItem> centerPOI = new ArrayList<>();
}
