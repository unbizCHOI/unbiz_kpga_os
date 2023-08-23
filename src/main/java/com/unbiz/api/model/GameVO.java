package com.unbiz.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * packageName    : com.unbiz.api.model
 * fileName       : Game
 * author         : UNBIZ
 * date           : 2022-12-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-12-21        UNBIZ              최초 생성
 */
@ApiModel(description = "대회정보 VO")
@Data
public class GameVO {

    @ApiModelProperty(name = "apcYear", value = "연도", example = "2022")
    private Integer apcYear;

    @ApiModelProperty(name = "gameId", value = "대회코드", example = "202211000004M")
    private String gameId;

    @ApiModelProperty(name = "gameName", value = "대회명", example = "우리금융 챔피언십")
    private String gameName;

    @ApiModelProperty(name = "gameRollType", value = "대회룰종류(S:기본, M:매치, F:변형스테이브폴드)", example = "S")
    private String gameRollType;

    @ApiModelProperty(name = "gameOpenDate", value = "대회시작일", example = "20220602")
    private String gameOpenDate;

    @ApiModelProperty(name = "gameCloseDate", value = "대회종료일", example = "20220605")
    private String gameCloseDate;

    @ApiModelProperty(name = "gamePrize", value = "상금", example = "1300000000")
    private Integer gamePrize;

    @ApiModelProperty(name = "amtUnit", value = "상금단위", example = "KSW")
    private String amtUnit;

    @ApiModelProperty(name = "gameRoundCnt", value = "라운드수", example = "3")
    private Integer gameRoundCnt;

    @ApiModelProperty(name = "roundNo", value = "라운드", example = "1")
    private String roundNo;

}
