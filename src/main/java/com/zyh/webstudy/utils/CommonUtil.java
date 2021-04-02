package com.zyh.webstudy.utils;

import java.text.DecimalFormat;
import java.util.*;

public class CommonUtil {

    private static final Random random = new Random();

    private static final DecimalFormat fourdf = new DecimalFormat("0000");

    private static final DecimalFormat sixdf = new DecimalFormat("000000");

    /**
      *@Description: 获取唯一uuid
      *@Param: []
      *@return: java.lang.String
      *@Author: zyh
      *@Date: 2021/3/30 11:13
     **/
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
      *@Description: 生成4位随机数
      *@Param: []
      *@return: java.lang.String
      *@Author: zyh
      *@Date: 2021/3/30 11:13
     **/
    public static String getFourBitRandom() {
        return fourdf.format(random.nextInt(10000));
    }

    /**
      *@Description: 生成生成6位随机数
      *@Param: []
      *@return: java.lang.String
      *@Author: zyh
      *@Date: 2021/3/30 11:12
     **/
    public static String getSixBitRandom() {
        return sixdf.format(random.nextInt(1000000));
    }

}
