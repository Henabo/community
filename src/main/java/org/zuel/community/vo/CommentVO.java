package org.zuel.community.vo;

import lombok.Data;
import org.zuel.community.model.User;

/**
 * @author henabo
 */
@Data
public class CommentVO {
    private Integer id;
    private Integer userId;
    private Integer questionId;
    private Boolean type;
    private String content;
    private Boolean shielded;
    private Long addTime;
    private Long updateTime;

    private String userName;
    private String avatorUrl;
}
