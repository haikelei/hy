package com.empathy.domain.collection.bo;

import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/24.
 */
@Getter@Setter
public class CollectionAddBo {

    @ApiModelProperty("收藏类型100直播间 200音频")
    @Required
    private Integer type;

    @ApiModelProperty("收藏id")
    @Required
    private Long typeId;

    @ApiModelProperty("用户id")
    @Required
    private Long userId;

}
