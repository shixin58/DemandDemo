package com.max.demanddemo;

import java.util.List;

/**
 * <p>Created by shixin on 2018/11/6.
 */
public class TopicVideoModel {
    public String topicName;
    public List<VideoModel> list;
    public static class VideoModel{
        public int resId;
        public String name;

        @Override
        public String toString() {
            return TopicVideoModel.class.getSimpleName()+"["+resId+", "+name+"]";
        }
    }
}
