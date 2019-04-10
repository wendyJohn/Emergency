package com.sanleng.electricalfire.data;

import com.sanleng.electricalfire.R;
import com.sanleng.electricalfire.util.ACache;
import com.sanleng.electricalfire.util.UtilFileDB;

import java.util.ArrayList;
import java.util.List;

public class HomeData {

    public static String[] TITLE = {"电气安全", "消防安全", "安防监控", "危化品柜", "安全巡查", "预警事件", "地图监控", "更多"};

    public static int[] IMG = {R.drawable.se_a, R.drawable.se_b, R.drawable.se_c,
            R.drawable.se_d, R.drawable.se_e, R.drawable.se_f,
            R.drawable.se_g, R.drawable.whole};

    public static int[] IMGSEARCH = {R.drawable.se_a, R.drawable.se_b, R.drawable.se_c,
            R.drawable.se_d, R.drawable.se_e, R.drawable.se_f,
            R.drawable.se_g, R.drawable.whole};

    /****
     *
     * 获取缓存数据
     *
     * @param aCache
     * @return
     */
    public static final List<Integer> POSITION(ACache aCache) {
        String string = UtilFileDB.SELETEFile(aCache, "home");
        List<Integer> list = new ArrayList<Integer>();
        if (string == null)// 默认
        {
            for (int i = 0; i < 7; i++) {
                list.add(i);
            }
            list.add(7);
            UtilFileDB.ADDFile(aCache, "home", "0,1,2,3,4,5,6");
            return list;
        } else {
            String[] stringArr = string.split(",");
            for (int i = 0; i < stringArr.length; i++) {
                list.add(Integer.valueOf(stringArr[i]));
            }
            list.add(7);
            return list;
        }

    }

}
