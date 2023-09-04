package com.unbiz.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.unbiz.api.model
 * fileName       : TimeparGroup
 * author         : UNBIZ
 * date           : 2023-09-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-02        UNBIZ              최초 생성
 */
@Data
public class TimeparGroup {
    @ApiModelProperty(name = "gameId", value = "대회코드", example = "202211000004M")
    private String gameId;

    @ApiModelProperty(name = "roundNo", value = "라운드번호", example = "1")
    private Integer roundNo;

    @ApiModelProperty(name = "holeNo", value = "홀번호", example = "1")
    private Integer holeNo;

    @ApiModelProperty(name = "memberId", value = "선수번호", example = "00004751")
    private String memberId;

    @ApiModelProperty(name = "starTtimeId", value = "조 연결 키", example = "1")
    private Integer startTimeId;
    
    @ApiModelProperty(name = "groupNum", value = "조번호", example = "3")
    private Integer groupNum;

    @ApiModelProperty(name = "playerName", value = "선수명", example = "최정욱")
    private String playerName;

    @ApiModelProperty(name = "score", value = "스코어", example = "3")
    private Integer score;
    
    @ApiModelProperty(name = "shotList", value = "샷정보")
    private List<Map> shotList;

}
