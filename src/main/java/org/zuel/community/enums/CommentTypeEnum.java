package org.zuel.community.enums;

/**
 * @author henabo
 */

public enum CommentTypeEnum {
    QUESTION_TYPE(false),
    COMMENT_TYPE(true)
    ;
    private Boolean type;

    public Boolean getType() {
        return type;
    }

    CommentTypeEnum(Boolean type) {
        this.type = type;
    }
}
