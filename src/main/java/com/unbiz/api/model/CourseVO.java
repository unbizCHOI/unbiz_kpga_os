package com.unbiz.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.unbiz.api.model
 * fileName       : CourseVO
 * author         : UNBIZ
 * date           : 2022-12-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-12-14        UNBIZ              최초 생성
 */
@ApiModel(description = "코스정보 VO")
@Data
@NoArgsConstructor
public class CourseVO {

    @ApiModelProperty(name = "gameId", value = "대회코드", example = "202211000004M")
    private String gameId;

    @ApiModelProperty(name = "holePar1", value = "1홀 파", example = "5")
    private Integer holePar1;

    @ApiModelProperty(name = "holePar2", value = "2홀 파", example = "4")
    private Integer holePar2;

    @ApiModelProperty(name = "holePar3", value = "3홀 파", example = "4")
    private Integer holePar3;

    @ApiModelProperty(name = "holePar4", value = "4홀 파", example = "3")
    private Integer holePar4;

    @ApiModelProperty(name = "holePar5", value = "5홀 파", example = "5")
    private Integer holePar5;

    @ApiModelProperty(name = "holePar6", value = "6홀 파", example = "4")
    private Integer holePar6;

    @ApiModelProperty(name = "holePar7", value = "7홀 파", example = "3")
    private Integer holePar7;

    @ApiModelProperty(name = "holePar8", value = "8홀 파", example = "4")
    private Integer holePar8;

    @ApiModelProperty(name = "holePar9", value = "9홀 파", example = "4")
    private Integer holePar9;

    @ApiModelProperty(name = "holePar10", value = "10홀 파", example = "4")
    private Integer holePar10;

    @ApiModelProperty(name = "holePar11", value = "11홀 파", example = "4")
    private Integer holePar11;

    @ApiModelProperty(name = "holePar12", value = "12홀 파", example = "3")
    private Integer holePar12;

    @ApiModelProperty(name = "holePar13", value = "13홀 파", example = "4")
    private Integer holePar13;

    @ApiModelProperty(name = "holePar14", value = "14홀 파", example = "4")
    private Integer holePar14;

    @ApiModelProperty(name = "holePar15", value = "15홀 파", example = "5")
    private Integer holePar15;

    @ApiModelProperty(name = "holePar16", value = "16홀 파", example = "4")
    private Integer holePar16;

    @ApiModelProperty(name = "holePar17", value = "17홀 파", example = "3")
    private Integer holePar17;

    @ApiModelProperty(name = "holePar18", value = "18홀 파", example = "4")
    private Integer holePar18;

    @ApiModelProperty(name = "holeParOut", value = "아웃코스 파합", example = "36")
    private Integer holeParOut;

    @ApiModelProperty(name = "holeParIn", value = "인코스 파합", example = "35")
    private Integer holeParIn;

    @ApiModelProperty(name = "holeParSum", value = "총 파합", example = "71")
    private Integer holeParSum;

    @ApiModelProperty(name = "holeDistance1", value = "1 홀 거리", example = "583")
    private Integer holeDistance1;

    @ApiModelProperty(name = "holeDistance2", value = "2 홀 거리", example = "439")
    private Integer holeDistance2;

    @ApiModelProperty(name = "holeDistance3", value = "3 홀 거리", example = "425")
    private Integer holeDistance3;

    @ApiModelProperty(name = "holeDistance4", value = "4 홀 거리", example = "185")
    private Integer holeDistance4;

    @ApiModelProperty(name = "holeDistance5", value = "5 홀 거리", example = "537")
    private Integer holeDistance5;

    @ApiModelProperty(name = "holeDistance6", value = "6 홀 거리", example = "419")
    private Integer holeDistance6;

    @ApiModelProperty(name = "holeDistance7", value = "7 홀 거리", example = "230")
    private Integer holeDistance7;

    @ApiModelProperty(name = "holeDistance8", value = "8 홀 거리", example = "378")
    private Integer holeDistance8;

    @ApiModelProperty(name = "holeDistance9", value = "9 홀 거리", example = "454")
    private Integer holeDistance9;

    @ApiModelProperty(name = "holeDistance10", value = "10홀 거리", example = "371")
    private Integer holeDistance10;

    @ApiModelProperty(name = "holeDistance11", value = "11홀 거리", example = "498")
    private Integer holeDistance11;

    @ApiModelProperty(name = "holeDistance12", value = "12홀 거리", example = "166")
    private Integer holeDistance12;

    @ApiModelProperty(name = "holeDistance13", value = "13홀 거리", example = "451")
    private Integer holeDistance13;

    @ApiModelProperty(name = "holeDistance14", value = "14홀 거리", example = "422")
    private Integer holeDistance14;

    @ApiModelProperty(name = "holeDistance15", value = "15홀 거리", example = "582")
    private Integer holeDistance15;

    @ApiModelProperty(name = "holeDistance16", value = "16홀 거리", example = "392")
    private Integer holeDistance16;

    @ApiModelProperty(name = "holeDistance17", value = "17홀 거리", example = "156")
    private Integer holeDistance17;

    @ApiModelProperty(name = "holeDistance18", value = "18홀 거리", example = "441")
    private Integer holeDistance18;

    @ApiModelProperty(name = "holeDistanceOut", value = "아웃코스 거리합", example = "3650")
    private Integer holeDistanceOut;

    @ApiModelProperty(name = "holeDistanceIn", value = "인코스 거리합", example = "3479")
    private Integer holeDistanceIn;

    @ApiModelProperty(name = "holeDistanceSum", value = "총 거리합", example = "7129")
    private Integer holeDistanceSum;
}
