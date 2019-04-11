package com.sanleng.electricalfire.data;


import com.sanleng.electricalfire.ui.bean.Video;

import java.util.ArrayList;
import java.util.List;

public class VideoData {
    public static List<Video> getVideoListData() {
        List<Video> videoList = new ArrayList<>();
        videoList.add(new Video("应急宣传",
                98000,
                "https://slyj.slicity.com/RootFile/Platform/20181122/1542854342919.jpg" ,
                "https://slyj.slicity.com/RootFile/Platform/20181114/1542178640266.mp4"));

        videoList.add(new Video("应急宣传",
                413000,
                "https://slyj.slicity.com/RootFile/Platform/20181122/1542854162848.jpg",
                "https://slyj.slicity.com/RootFile/Platform/20181114/1542178640266.mp4"));

        videoList.add(new Video("应急宣传",
                439000,
                "https://slyj.slicity.com/RootFile/Platform/20181122/1542853670308.jpg",
                "https://slyj.slicity.com/RootFile/Platform/20181114/1542178640266.mp4"));
        return videoList;
    }
}