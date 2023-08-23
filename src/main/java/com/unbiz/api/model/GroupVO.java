package com.unbiz.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.unbiz.api.model
 * fileName       : GroupVO
 * author         : UNBIZ
 * date           : 2022-12-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-12-14        UNBIZ              최초 생성
 */
@ApiModel(description = "조편성 정보 VO")
@Data
@NoArgsConstructor
public class GroupVO {

    @ApiModelProperty(name = "gameId", value = "대회코드", example = "202211000004M")
    private String gameId;

    @ApiModelProperty(name = "roundNo", value = "라운드번호", example = "1")
    private Integer roundNo;

    @ApiModelProperty(name = "groupNum", value = "조번호", example = "3")
    private Integer groupNum;

    @ApiModelProperty(name = "startInOut", value = "출발IN/OUT", example = "OUT")
    private String startInOut;

    @ApiModelProperty(name = "startHoleNo", value = "출발 홀 번호", example = "1")
    private Integer startHoleNo;

    @ApiModelProperty(name = "startTime", value = "출발 시간", example = "1150")
    private String startTime;

}
