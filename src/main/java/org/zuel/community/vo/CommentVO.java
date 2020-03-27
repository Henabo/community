package org.zuel.community.vo;

import lombok.Data;

@Data
public class CommentVO {
    private Integer userId;
    private boolean type;
    private String content;
}
