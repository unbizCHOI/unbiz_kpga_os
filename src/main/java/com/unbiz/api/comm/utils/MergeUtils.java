package com.unbiz.api.comm.utils;

import java.util.*;

/**
 * packageName    : com.unbiz.coda.comm.utils
 * fileName       : MergeUtils
 * author         : UNBIZ
 * date           : 2022-09-16
 * description    : 데이터 합치기
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-16        UNBIZ              최초 생성
 */
public class MergeUtils {
    /**
     * ================================================== description ==================================================
     * <p> 리스트 리스트 합치기 
     * =================================================================================================================
     *
     * @param mainList    main list
     * @param subList     sub list
     * @param mappingKeys mapping keys
     * @return 합친 main list
     */
    public static List mergeListAndList(List<Map> mainList, List<Map> subList, String... mappingKeys){
        Loop1 :
        for(Map main : mainList){
            Loop2 :
            for(Map sub : subList){
                int isSame = 0;

                for(int keyI = 0; keyI < mappingKeys.length; keyI++){
                    String key = mappingKeys[keyI];
                    if(main.get(key).equals(sub.get(key))) isSame++;
                }
                if(isSame == mappingKeys.length){
                    main.putAll(sub);
                    break Loop2;
                }
            }
        }
        return mainList;
    }

    /**
     * ================================================== description ==================================================
     * <p> 맵 리스트 합치기
     * =================================================================================================================
     *
     * @param mainMap     the main map
     * @param subList     the sub list
     * @param mappingKeys the mapping keys
     * @return the map
     */
    public static Map mergeMapAndList(Map mainMap, List<Map> subList, String... mappingKeys){
        int isSame = 0;
        for(Map sub : subList){
            for(int keyI = 0; keyI < mappingKeys.length; keyI++){
                String key = mappingKeys[keyI];
                if(mainMap.get(key).equals(sub.get(key))) isSame++;
            }
            if(isSame == mappingKeys.length){
                mainMap.putAll(sub);
                break;
            }
        }
        return mainMap;
    }

    /**
     * ================================================== description ==================================================
     * <p> 리스트에 서브 리스트 삽입
     * =================================================================================================================
     *
     * @param mainList the main list
     * @param subList  the sub list
     * @param key      the key
     * @return the list
     */
    public static List mergePutGroupList(List<Map> mainList, List<Map> subList, String key){
        for(Map main : mainList){
            List innerList = new ArrayList();
            for(Map sub : subList){
                if(main.get(key).equals(sub.get(key))){
                    innerList.add(sub);
                }
            }
            main.put(key+"List", innerList);
        }
        return mainList;
    }

    /**
     * ================================================== description ==================================================
     * <p> 리스트에 서브 리스트 삽입
     * =================================================================================================================
     *
     * @param mainList   the main list
     * @param subList    the sub list
     * @param key        the key
     * @param variableNm the variable nm
     * @return the list
     */
    public static List mergePutGroupList(List<Map> mainList, List<Map> subList, String key, String variableNm){
        for(Map main : mainList){
            List innerList = new ArrayList();
            for(Map sub : subList){
                if(main.get(key).equals(sub.get(key))){
                    innerList.add(sub);
                }
            }
            main.put(variableNm, innerList);
        }
        return mainList;
    }

}
