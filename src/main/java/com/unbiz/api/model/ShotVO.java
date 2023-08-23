package com.unbiz.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * packageName    : com.unbiz.api.model
 * fileName       : Shot
 * author         : UNBIZ
 * date           : 2022-12-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-12-14        UNBIZ              최초 생성
 * 2022-12-21        UNBIZ              위도경도 추가
 */
@ApiModel(description = "샷정보 VO")
@Data
public class ShotVO {

    @ApiModelProperty(name = "gameId", value = "대회코드", example = "202211000004M")
    private String gameId;

    @ApiModelProperty(name = "roundNo", value = "라운드번호", example = "1")
    private Integer roundNo;

    @ApiModelProperty(name = "memberId", value = "선수코드", example = "00056344")
    private String memberId;

    @ApiModelProperty(name = "holeNo", value = "홀번호", example = "1")
    private Integer holeNo;

    @ApiModelProperty(name = "shotNo", value = "샷순서", example = "1")
    private Integer shotNo;

    @ApiModelProperty(name = "field", value = "필드값(1 : 페어웨이 ,2 : 러브 ,3 : 그린 ,4 : OB ,5 : 해저드 ,6 : 벙커 ,7 : 분실구 ,8 : 벌타 ,9 : 그린주변 ,10 : 홀인)", example = "10")
    private Integer field;

    @ApiModelProperty(name = "penalty", value = "페널티여부", example = "1")
    private Integer penalty;

    @ApiModelProperty(name = "distance", value = "비거리", example = "271.07605")
    private Double distance;

    @ApiModelProperty(name = "remain", value = "남은거리", example = "174.53725")
    private Double remain;

    @ApiModelProperty(name = "latitude", value = "위도", example = "37.73821091160456")
    private Double latitude;

    @ApiModelProperty(name = "longitude", value = "경도", example = "127.75780417609538")
    private Double longitude;
}
