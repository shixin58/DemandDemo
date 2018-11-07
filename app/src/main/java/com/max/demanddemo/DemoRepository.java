package com.max.demanddemo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Created by shixin on 2018/11/6.
 */
public class DemoRepository {
    public static List<TopicVideoModel> getAllTopicVideos() {
        List<TopicVideoModel> list = new ArrayList<>();
        for(int i=0;i<10;i++) {
            TopicVideoModel topicVideoModel = new TopicVideoModel();
            topicVideoModel.topicName = "民间达人";
            topicVideoModel.list = new ArrayList<>();
            TopicVideoModel.VideoModel videoModel = new TopicVideoModel.VideoModel();
            videoModel.resId = R.mipmap.talent;
            videoModel.name = "A";
            topicVideoModel.list.add(videoModel);
            TopicVideoModel.VideoModel videoModel1 = new TopicVideoModel.VideoModel();
            videoModel1.resId = R.mipmap.talent;
            videoModel1.name = "B";
            topicVideoModel.list.add(videoModel1);
            TopicVideoModel.VideoModel videoModel2 = new TopicVideoModel.VideoModel();
            videoModel2.resId = R.mipmap.talent;
            videoModel2.name = "C";
            topicVideoModel.list.add(videoModel2);
            TopicVideoModel.VideoModel videoModel3 = new TopicVideoModel.VideoModel();
            videoModel3.resId = R.mipmap.talent;
            videoModel3.name = "D";
            topicVideoModel.list.add(videoModel3);
            list.add(topicVideoModel);

            TopicVideoModel topicVideoModel1 = new TopicVideoModel();
            topicVideoModel1.topicName = "无人驾驶";
            topicVideoModel1.list = new ArrayList<>();
            TopicVideoModel.VideoModel videoModel4 = new TopicVideoModel.VideoModel();
            videoModel4.resId = R.mipmap.drive;
            videoModel4.name = "生命在于运动，生活在于经营，事业在于奋斗";
            topicVideoModel1.list.add(videoModel4);
            TopicVideoModel.VideoModel videoModel5 = new TopicVideoModel.VideoModel();
            videoModel5.resId = R.mipmap.drive;
            videoModel5.name = "我是买了个假车吗？？";
            topicVideoModel1.list.add(videoModel5);
            TopicVideoModel.VideoModel videoModel6 = new TopicVideoModel.VideoModel();
            videoModel6.resId = R.mipmap.drive;
            videoModel6.name = "如果不小心算我输";
            topicVideoModel1.list.add(videoModel6);
            TopicVideoModel.VideoModel videoModel7 = new TopicVideoModel.VideoModel();
            videoModel7.resId = R.mipmap.drive;
            videoModel7.name = "逗一下还有谁能这样，舍我其谁也";
            topicVideoModel1.list.add(videoModel7);
            TopicVideoModel.VideoModel videoModel8 = new TopicVideoModel.VideoModel();
            videoModel8.resId = R.mipmap.drive;
            videoModel8.name = "逗一下还有谁能这样，舍我其谁也";
            topicVideoModel1.list.add(videoModel8);
            TopicVideoModel.VideoModel videoModel9 = new TopicVideoModel.VideoModel();
            videoModel9.resId = R.mipmap.drive;
            videoModel9.name = "逗一下还有谁能这样，舍我其谁也";
            topicVideoModel1.list.add(videoModel9);
            list.add(topicVideoModel1);
        }
        return list;
    }
}
