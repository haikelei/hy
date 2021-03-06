package com.empathy.domain.album.bo;

import com.empathy.common.PageBo;
import com.empathy.common.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by MI on 2018/7/30.
 */
@Getter@Setter
public class FindBuyRecordingBo extends PageBo {

    @ApiModelProperty("专辑id")
    @Required
    private Long albumId;
}
