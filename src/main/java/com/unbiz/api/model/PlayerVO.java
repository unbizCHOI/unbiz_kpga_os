package com.unbiz.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * packageName    : com.unbiz.api.vo
 * fileName       : PlayerVO
 * author         : UNBIZ
 * date           : 2022-12-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-12-14        UNBIZ              최초 생성
 */
@ApiModel(description = "선수정보 VO")
@Data
public class PlayerVO {

    @ApiModelProperty(name = "gameId", value = "대회코드", example = "202211000004M")
    private String gameId;
    
    @ApiModelProperty(name = "memberId", value = "선수코드", example = "00056344")
    private String memberId;
    
    @ApiModelProperty(name = "playerNm", value = "선수명", example = "최정욱")
    private String playerNm;

    @ApiModelProperty(name = "playerImgPath", value = "선수 이미지 경로", example = "http://211.216.48.143:9990/player.png")
    private String playerImgPath;

}
