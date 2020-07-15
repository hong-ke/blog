package com.hk.blog.entity;


import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//博客
@Data
public class Blog {

    private Long id;
    private String title;
    private String content;  //内容
    private String firstPicture;  //首图
    private String flag;   //标记
    private Integer views;  //浏览次数
    private boolean appreciation;   // 赞赏是否开启
    private boolean shareStatement;  // 转载声明是否开启
    private boolean commentabled;  // 评论
    private boolean published;  // 发布or保存
    private boolean recommend;  // 是否推荐
    private Date createTime;  // 创建时间
    private Date updateTime;  // 更新时间
    private Type type;  // 类型
    private List<Tag> tags;
    private User user;
    private Long userId;
    private List<Comment> comments;
    private String tagIds;
    private String description;
    private Long typeId;

    public void init(){
       this.tagIds = tagsToIds(this.getTags());
    }

    //1,2,3
    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }
}
