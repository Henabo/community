package org.zuel.community.vo;

import lombok.Data;
import org.zuel.community.model.User;

@Data 
public class QuestionVO {
    private Integer id;
    private Integer userId;
    private String title;
    private String content;
    private Long answerTime;
    private boolean ended;
    private boolean shielded;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private Long addTime;
    private Long updateTime;

    private User user;
}
