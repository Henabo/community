package org.zuel.community.enums;

public enum CommentTypeEnum {
    QUESTION(false),
    COMMENT(true)
    ;
    private Boolean type;

    public Boolean getType() {
        return type;
    }

    CommentTypeEnum(Boolean type) {
        this.type = type;
    }
}
