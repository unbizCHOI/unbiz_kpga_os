package com.unbiz.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * packageName    : com.unbiz.api.model
 * fileName       : Score
 * author         : UNBIZ
 * date           : 2022-12-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-12-14        UNBIZ              최초 생성
 * 2022-12-21        UNBIZ              ㅎ 오타수정
 */
@ApiModel(description = "스코어정보 VO")
@Data
public class ScoreVO {

    @ApiModelProperty(name = "gameId", value = "대회코드", example = "202211000004M")
    private String gameId;

    @ApiModelProperty(name = "roundNo", value = "라운드번호", example = "1")
    private Integer roundNo;

    @ApiModelProperty(name = "memberId", value = "선수코드", example = "00056344")
    private String memberId;

    @ApiModelProperty(name = "regionGroupCourseId", value = "선수코드", example = "00056344")
    private String regionGroupCourseId;

    @ApiModelProperty(name = "gameSubgroupCode", value = "선수코드", example = "00056344")
    private String gameSubgroupCode;

    @ApiModelProperty(name = "startTimeId", value = "선수코드", example = "00056344")
    private String startTimeId;

    @ApiModelProperty(name = "courseId", value = "선수코드", example = "00056344")
    private String courseId;

    @ApiModelProperty(name = "playerName", value = "선수명", example = "최정욱")
    private String playerName;

    @ApiModelProperty(name = "groupNum", value = "조번호", example = "3")
    private Integer groupNum;

    @ApiModelProperty(name = "startTeeOffOrder", value = "출발 티오프 순서 ", example = "1")
    private Integer startTeeOffOrder;

    @ApiModelProperty(name = "score1", value = "1홀 스코어", example = "5")
    private Integer score1;

    @ApiModelProperty(name = "score2", value = "2홀 스코어", example = "4")
    private Integer score2;

    @ApiModelProperty(name = "score3", value = "3홀 스코어", example = "4")
    private Integer score3;

    @ApiModelProperty(name = "score4", value = "4홀 스코어", example = "3")
    private Integer score4;

    @ApiModelProperty(name = "score5", value = "5홀 스코어", example = "5")
    private Integer score5;

    @ApiModelProperty(name = "score6", value = "6홀 스코어", example = "4")
    private Integer score6;

    @ApiModelProperty(name = "score7", value = "7홀 스코어", example = "3")
    private Integer score7;

    @ApiModelProperty(name = "score8", value = "8홀 스코어", example = "4")
    private Integer score8;

    @ApiModelProperty(name = "score9", value = "9홀 스코어", example = "4")
    private Integer score9;

    @ApiModelProperty(name = "score10", value = "10홀 스코어", example = "4")
    private Integer score10;

    @ApiModelProperty(name = "score11", value = "11홀 스코어", example = "4")
    private Integer score11;

    @ApiModelProperty(name = "score12", value = "12홀 스코어", example = "3")
    private Integer score12;

    @ApiModelProperty(name = "score13", value = "13홀 스코어", example = "4")
    private Integer score13;

    @ApiModelProperty(name = "score14", value = "14홀 스코어", example = "4")
    private Integer score14;

    @ApiModelProperty(name = "score15", value = "15홀 스코어", example = "5")
    private Integer score15;

    @ApiModelProperty(name = "score16", value = "16홀 스코어", example = "4")
    private Integer score16;

    @ApiModelProperty(name = "score17", value = "17홀 스코어", example = "3")
    private Integer score17;

    @ApiModelProperty(name = "score18", value = "18홀 스코어", example = "4")
    private Integer score18;

    @ApiModelProperty(name = "scoreOut", value = "아웃코스 스코어합", example = "36")
    private Integer scoreOut;

    @ApiModelProperty(name = "scoreIn", value = "인코스 스코어합", example = "35")
    private Integer scoreIn;

    @ApiModelProperty(name = "scoreSum", value = "총 스코어합", example = "71")
    private Integer scoreSum;

    @ApiModelProperty(name = "udpar1", value = "1홀 언더파", example = "5")
    private Integer udpar1;

    @ApiModelProperty(name = "udpar2", value = "2홀 언더파", example = "4")
    private Integer udpar2;

    @ApiModelProperty(name = "udpar3", value = "3홀 언더파", example = "4")
    private Integer udpar3;

    @ApiModelProperty(name = "udpar4", value = "4홀 언더파", example = "3")
    private Integer udpar4;

    @ApiModelProperty(name = "udpar5", value = "5홀 언더파", example = "5")
    private Integer udpar5;

    @ApiModelProperty(name = "udpar6", value = "6홀 언더파", example = "4")
    private Integer udpar6;

    @ApiModelProperty(name = "udpar7", value = "7홀 언더파", example = "3")
    private Integer udpar7;

    @ApiModelProperty(name = "udpar8", value = "8홀 언더파", example = "4")
    private Integer udpar8;

    @ApiModelProperty(name = "udpar9", value = "9홀 언더파", example = "4")
    private Integer udpar9;

    @ApiModelProperty(name = "udpar10", value = "10홀 언더파", example = "4")
    private Integer udpar10;

    @ApiModelProperty(name = "udpar11", value = "11홀 언더파", example = "4")
    private Integer udpar11;

    @ApiModelProperty(name = "udpar12", value = "12홀 언더파", example = "3")
    private Integer udpar12;

    @ApiModelProperty(name = "udpar13", value = "13홀 언더파", example = "4")
    private Integer udpar13;

    @ApiModelProperty(name = "udpar14", value = "14홀 언더파", example = "4")
    private Integer udpar14;

    @ApiModelProperty(name = "udpar15", value = "15홀 언더파", example = "5")
    private Integer udpar15;

    @ApiModelProperty(name = "udpar16", value = "16홀 언더파", example = "4")
    private Integer udpar16;

    @ApiModelProperty(name = "udpar17", value = "17홀 언더파", example = "3")
    private Integer udpar17;

    @ApiModelProperty(name = "udpar18", value = "18홀 언더파", example = "4")
    private Integer udpar18;

    @ApiModelProperty(name = "udparOut", value = "아웃코스 언더파합", example = "36")
    private Integer udparOut;

    @ApiModelProperty(name = "udparIn", value = "인코스 언더파합", example = "35")
    private Integer udparIn;

    @ApiModelProperty(name = "udparSum", value = "총 언더파합", example = "71")
    private Integer udparSum;

    @ApiModelProperty(name = "accuUdpar", value = "누적언더파", example = "-1")
    private Integer accuUdpar;

    @ApiModelProperty(name = "accuScore", value = "누적스커어", example = "98")
    private Integer accuScore;

    @ApiModelProperty(name = "roundRank", value = "라운드 랭킹", example = "5")
    private Integer roundRank;

    @ApiModelProperty(name = "accuRank", value = "누럭랭킹", example = "15")
    private Integer accuRank;

    @ApiModelProperty(name = "exceptionYn", value = "기권/탈락여부", example = "N")
    private String exceptionYn;

    @ApiModelProperty(name = "exceptionType", value = "기권/탈락 타입", example = "05")
    private String exceptionType;

}
