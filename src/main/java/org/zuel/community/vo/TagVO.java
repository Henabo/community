package org.zuel.community.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: Nabo He
 * @Date: 2020/4/18 12:29 下午
 */
@Data
public class TagVO {
    private String categoryName;
    private List<String> tags;
}
