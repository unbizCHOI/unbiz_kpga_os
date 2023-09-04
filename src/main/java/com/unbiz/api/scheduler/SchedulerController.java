package com.unbiz.api.scheduler;


import com.opencsv.CSVWriter;
import com.unbiz.api.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * packageName    : com.unbiz.api.scheduler
 * fileName       : SchedulerController
 * author         : UNBIZ
 * date           : 2023-06-12
 * description    : 배치컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-12        UNBIZ              최초 생성
 */


@Controller
public class SchedulerController {

	@Autowired
	private SchedulerService schedulerService;
	private Map parameterMap;
	private static String active;
	@Value("${spring.profiles.active}")
	public void setActive(String active) { this.active = active; }

	private void initParameter(){
		parameterMap = new HashMap();
		parameterMap.put("apcYear", new Date().getYear());
	}

	@Scheduled(cron="*/5 * * * * *")
	public void backup22() throws Exception {
		if("prod".equals(active)) {
			schedulerService.updateTimepar();
		}
	}

	@Scheduled(cron="0 0 23 * * *")
	public void backup() throws Exception {
		System.out.println("시작: " + new Date().getYear());

		if(ObjectUtils.isEmpty(parameterMap)) initParameter();

		long beforeTime = System.currentTimeMillis();

		List<String> tableNameList = getTableNameList();
		for(String tableName : tableNameList){
			parameterMap.put("tableName", tableName);
			for(int i = 0 ; i < 50; i++){
				int pageNo = i * 10000;
				parameterMap.put("pageNo", pageNo);
				List<Map> list = schedulerService.selectSchedulerAll(parameterMap);
				if(list == null ||list.size() < 1) break;

				saveSql(list, tableName, i);
			}
		}
		long afterTime = System.currentTimeMillis();
		long secDiffTime = (afterTime - beforeTime)/1000;
		System.out.println("시간차이(m) : "+secDiffTime);
	}
	private List<String> getTableNameList(){
		List<String> list = new ArrayList<>();
		list.add("attachment_file");
		list.add("benefit_guide");
		list.add("benefit_mutualaid_amt");
		list.add("benefit_mutualaid_mst");
		list.add("benefit_pension_amt");
		list.add("benefit_pension_mst");
		list.add("benefit_sclship_amt");
		list.add("benefit_sclship_mst");
		list.add("board_awards_cate");
		list.add("board_awards_info");
		list.add("board_awards_member");
		list.add("board_base");
		list.add("board_base_order");
		list.add("board_category");
		list.add("board_event");
		list.add("board_faq");
		list.add("board_faq_cate");
		list.add("board_faq_qna");
		list.add("board_file");
		list.add("board_golfclub_notice");
		list.add("board_job_find");
		list.add("board_job_offer");
		list.add("board_member");
		list.add("board_photo");
		list.add("board_proam");
		list.add("board_replay");
		list.add("board_research");
		list.add("board_score_form");
		list.add("board_score_req");
		list.add("board_sc_sub");
		list.add("board_showcode");
		list.add("board_show_mapping");
		list.add("board_totocamp");
		list.add("broadcasting");
		list.add("broadcast_mst");
		list.add("broadcast_schedule");
		list.add("cmmn_attachment");
		list.add("cmmn_nation_code");
		list.add("cnps_rcod_raw");
		list.add("cost_carfare_dtl");
		list.add("cost_carfare_mst");
		list.add("cost_carfare_tax");
		list.add("dg_vote_candidate");
		list.add("dg_vote_delegate");
		list.add("dg_vote_inwon");
		list.add("dg_vote_mst");
		list.add("dg_vote_result_detail");
		list.add("dg_vote_voter");
		list.add("edu_curriculum_dtl");
		list.add("edu_curriculum_member");
		list.add("edu_curriculum_mst");
		list.add("edu_golfrule");
		list.add("edu_golfrule_xls");
		list.add("edu_mip_member");
		list.add("edu_pre_member");
		list.add("employee_user");
		list.add("game_broad_schedule");
		list.add("game_category");
		list.add("game_category_seed");
		list.add("game_course");
		list.add("game_cptmgr");
		list.add("game_exam_round_booking");
		list.add("game_exam_round_booking_hist");
		list.add("game_exam_round_mst");
		list.add("game_exam_round_time");
		list.add("game_gstar_part_point_rk");
		list.add("game_gstar_point_rk");
		list.add("game_hole_df");
		list.add("game_livescore");
		list.add("game_mst");
		list.add("game_mst_del");
		list.add("game_mst_setup");
		list.add("game_pinposition");
		list.add("game_player");
		list.add("game_player_exception");
		list.add("game_player_point");
		list.add("game_player_prize");
		list.add("game_player_record");
		list.add("game_player_req");
		list.add("game_player_req_del");
		list.add("game_player_req_hist");
		//list.add("game_player_req_pend");
		list.add("game_player_starttime_fix");
		list.add("game_poff_hole");
		list.add("game_poff_hole_hist");
		list.add("game_poff_score");
		list.add("game_poff_score_hist");
		list.add("game_point_dsbtbl");
		list.add("game_prize_dsbtbl");
		list.add("game_prize_event");
		list.add("game_prize_spc");
		list.add("game_prize_spc_lst");
		list.add("game_program_1_dtl");
		list.add("game_program_2_dtl");
		list.add("game_program_3_dtl");
		list.add("game_program_4_dtl");
		list.add("game_qlf_mst");
		list.add("game_qlf_player");
		list.add("game_qualifying_round_plan_dtl");
		list.add("game_qualifying_round_plan_mst");
		//list.add("game_ranking");
		//list.add("game_ranking_foreign");
		list.add("game_rookie_point_rk");
		list.add("game_round");
		list.add("game_round_starttime");
		list.add("game_score");
		//list.add("game_score_copy");
		list.add("game_score_display");
		list.add("game_score_excel");
		list.add("game_score_league");
		list.add("game_score_league_close");
		list.add("game_score_mc");
		list.add("game_score_mc_off");
		//list.add("game_score_mc_off_hist");
		list.add("game_score_org");
		list.add("game_score_record");
		list.add("game_score_stableford");
		//list.add("game_seq_last_member");
		list.add("game_starttime_category_dtl");
		list.add("game_starttime_category_member");
		list.add("game_starttime_category_mst");
		list.add("game_starttime_random_member");
		list.add("game_starttime_random_ranking");
		list.add("game_starttime_random_setup");
		list.add("game_starttime_random_setup_default");
		//list.add("game_timepar");
		//list.add("game_trackman");
		list.add("game_weather");
		list.add("game_wt_member");
		list.add("game_year_gmcnt");
		list.add("game_year_gmcnt_before");
		list.add("game_year_point");
		list.add("game_year_prize");
		list.add("golfclub_image");
		list.add("handicap_certificate");
		list.add("honors_contents");
		list.add("honors_contents_detail");
		list.add("honors_contents_type");
		list.add("honors_file");
		list.add("honors_image");
		list.add("honors_member");
		list.add("honors_schedule");
		list.add("honors_view_type");
		//list.add("incs_tx_crosschk");
		//list.add("incs_tx_error");
		list.add("incs_tx_item");
		//list.add("incs_tx_log");
		//list.add("incs_tx_mobile_result");
		list.add("incs_tx_mst");
		//list.add("incs_tx_result");
		list.add("logonuserinfolist");
		list.add("main_layout_setting");
		list.add("main_layout_style");
		list.add("main_setting_pick");
		list.add("main_setting_value");
		list.add("media_guide_book");
		list.add("mgmt_admission");
		list.add("mgmt_bank");
		list.add("mgmt_channel");
		list.add("mgmt_company");
		list.add("mgmt_company_auth_member");
		list.add("mgmt_cptsteering");
		list.add("mgmt_dues_cfg");
		list.add("mgmt_dues_yymm");
		list.add("mgmt_fund");
		list.add("mgmt_golfclub");
		list.add("mgmt_golfclub_course");
		list.add("mgmt_golfclub_member");
		list.add("mgmt_insurance_fee");
		list.add("mgmt_ktour_mbrship_fee");
		list.add("mgmt_location");
		list.add("mgmt_mbrship_fee");
		list.add("mgmt_ofc_point_dsbtbl_dtl");
		list.add("mgmt_ofc_point_dsbtbl_mst");
		list.add("mgmt_ofc_prize_dsbtbl_dtl");
		list.add("mgmt_ofc_prize_dsbtbl_mst");
		list.add("mgmt_prize_tax");
		list.add("mgmt_season");
		list.add("mgmt_serise");
		list.add("mgmt_sponsor");
		list.add("mgmt_staff");
		list.add("mgmt_starttime_category");
		list.add("mgmt_starttime_subcategory");
		list.add("mgmt_tour");
		list.add("mgmt_tour_category");
		list.add("mgmt_tour_seed");
		//list.add("msg_result");
		list.add("opi_bill_billkey");
		list.add("opi_bill_cardbilling");
		list.add("opi_dues_excel_member");
		list.add("opi_dues_exception");
		list.add("opi_dues_month");
		//list.add("opi_dues_mst");
		//list.add("opi_dues_mst_0518");
		//list.add("opi_dues_mst_dtl");
		//list.add("opi_dues_mst_dtl_excel");
		//list.add("opi_dues_mst_excel");
		//list.add("opi_dues_req");
		//list.add("opi_dues_req_excel");
		//list.add("opi_insurance_fee");
		//list.add("opi_join_fee");
		//list.add("opi_mbrship_amt");
		//list.add("opi_mbrship_krtour");
		list.add("penalty_req");
		list.add("pension_bereaved");
		list.add("pension_dtl");
		list.add("pension_mst");
		list.add("player_game_cnt");
		list.add("player_member");
		list.add("player_member_certi");
		list.add("player_member_goods");
		list.add("player_member_history");
		list.add("player_member_relation");
		list.add("player_nonofficial_record_year");
		list.add("player_nonofficial_record_year_before");
		list.add("player_punishment");
		list.add("player_record_year");
		//list.add("player_record_year_2008back");
		list.add("player_record_year_before");
		list.add("player_user_login_info");
		//list.add("player_user_mst");
		list.add("player_user_mst_hist");
		list.add("player_user_webservice_info");
		list.add("player_user_withdrawal");
		list.add("player_year_round");
		list.add("player_year_round_before");
		list.add("player_year_round_top10");
		list.add("player_year_round_top10_before");
		list.add("punishment_member_year");
		//list.add("search_keyword");
		//list.add("sms_info");
		list.add("stats_main_rank");
		list.add("stats_score_rank");
		list.add("sys_cmm_cd");
		list.add("sys_code_nation");
		list.add("sys_code_old");
		list.add("sys_config");
		list.add("sys_menu");
		list.add("sys_migration_tbl");
		list.add("sys_naver_log");
		list.add("sys_nextval");
		list.add("sys_proc_log");
		list.add("sys_record_cd");
		list.add("sys_schedule_log");
		list.add("sys_service_request");
		list.add("tb_error_collect");
		list.add("tb_year_title");
		//list.add("temp_opi_dues_mst");
		//list.add("temp_opi_dues_pay");
		//list.add("temp_opi_dues_req");
		//list.add("temp_player_picture");
		//list.add("t_h_media_player");
		//list.add("t_h_srixon");
		list.add("union_game");
		list.add("union_game_dtl");
		list.add("union_game_mst");
		list.add("union_mst");
		list.add("union_qlf_mst");
		list.add("view_game_player_round");
		list.add("view_game_year_round");

		return list;
	}

	private String[] getHeaderStringArr(String tableName){
		if("attachment_file".equals(tableName)){ return new String[]{"attachment_serial_no","updateIp","updateDt","updateId","createIp","createDt","createId","deleted","remark","download_cnt","attachment_id","file_path","file_name","file_org_name","file_ext","file_size","file_div"};
		} else if("benefit_guide".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","benefit_guide","benefit_type"};
		} else if("benefit_mutualaid_amt".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","amt","mutualAid_type","close_date","open_date"};
		} else if("benefit_mutualaid_mst".equals(tableName)){ return new String[]{"remark","attachment_id2","attachment_id1","attachment_id0","attachment_id","disapr_reason","Is_SMS_tm","depositor","req_comment","kind_type","updateIp","updateDt","updateId","createIp","createDt","createId","message","route","accnt","bank_code","contact","req_date","Is_exp","prc_state","mutualAid_type","mutualAid_date","member_id","benefit_seq","target_name","mutualAid_time","pay_amt","pay_date","replace_close_month","replace_open_month","dues_replace_amt","place","caCoffin_date","mutualAid_am_pm"};
		} else if("benefit_pension_amt".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","amt","close_date","open_date"};
		} else if("benefit_pension_mst".equals(tableName)){ return new String[]{"division_1_close_month","division_1_open_month","pension_amt","std_yearCnt","Is_depart","division_2_punishment_id","division_1_punishment_id","Is_punishment_delay","Is_dues_delay","division_1_sum","division_2_open_month","updateIp","updateDt","updateId","createIp","createDt","createId","remark","division_2_sum","division_2_close_month","delay_contents","delay_year_cnt","bank_code","pay_amt","pay_date","pay_yearCnt","contact","req_date","prc_state","pay_year","member_id","accnt","depositor","target_contact","target_depositor","target_accnt","target_bank_code","target_name","target_type","Is_target","attachment_id","disapr_reason"};
		} else if("benefit_sclship_amt".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","req_close_date","req_open_date","amt","apc_year"};
		} else if("benefit_sclship_mst".equals(tableName)){ return new String[]{"remark","attachment_id","disapr_reason","depositor","createId","createDt","createIp","updateId","updateDt","updateIp","accnt","bank_code","pay_amt","member_id","quarter_type","child_name","prc_state","req_date","contact","child_dob","child_school_name","child_grade","pay_date"};
		} else if("board_awards_cate".equals(tableName)){ return new String[]{"create_date","create_ip","update_id","update_date","update_ip","create_id","del_yn","awd_cate_sqn","awd_cate_name","awd_year","awd_sort","awd_cnts"};
		} else if("board_awards_info".equals(tableName)){ return new String[]{"update_user","update_date","create_user","create_date","awd_spon","awd_host","awd_place","std_date","awd_date","awd_title","awd_year"};
		} else if("board_awards_member".equals(tableName)){ return new String[]{"winner_en_name","create_id","create_dt","update_dt","update_id","create_ip","update_ip","winner_name","grant_etc","awd_id","awd_cate_sqn","awd_year","member_id","grant_name","grant_code","grant_organ"};
		} else if("board_base".equals(tableName)){ return new String[]{"show_champions_tour","show_test_tourpro","show_test_pro","show_korean_tour_qt","DeleteUserName","DeleteDate","show_challenge_tour","show_korean_tour","showM","showT","showA","Rnd","TomtTime","TomtClss","rtnURL","pUusername","is_league","popupSize","thumId","gameNotice","codeDiv","consentdata","mediaType","mediaUrl","EndDate","StartDate","CategoryDiv","supporters","cleanKpga","IsMain","TourDiv","OpenYear","postId","lastUpdatedDate","createdDate","viewCount","contentText","titleNm","emailAddr","password","realName","userName","depthLvl","threadNo","postCategoryId","boardCategoryId","IsDeleted","IsNotice","isOneLine","game_id","DeleteIP","UpdateIP","CreateIP","UpdateUserName","IsEng","NoticeOrder","IsBold","IsNoticeBold","hasFile","parentThread","recommendCount","isText","commentCount"};
		} else if("board_base_order".equals(tableName)){ return new String[]{"updateDt","updateIp","updateId","createDt","createIp","createId","orderUpdateDate","postId","boardCategoryId"};
		} else if("board_category".equals(tableName)){ return new String[]{"filePath","codeDivGroup","categoryCode","boardType","CreatedDate","IsDeleted","IsBlogType","HasPostCategory","BoardCategoryName","BoardCategoryId"};
		} else if("board_event".equals(tableName)){ return new String[]{"del_yn","dip_yn","view_count","create_date","create_id","create_ip","upgate_date","update_id","update_ip","event_viewPath","thumnail_img","event_idx","event_name","content","start_date","start_time","end_date","end_time","event_correct","publication_date"};
		} else if("board_faq".equals(tableName)){ return new String[]{"top_ten","updt_user","updt_date","crat_user","crat_date","Is_deleted","cate_code","menu_code","faq_id","year_dat","faq_seq","tit_txt","cont_txt","read_num"};
		} else if("board_faq_cate".equals(tableName)){ return new String[]{"menu_position","kgt_yn","cate_name","menu_code","cate_code","faq_cate_id"};
		} else if("board_faq_qna".equals(tableName)){ return new String[]{"delete_yn","qna_year","qna_seq","ans_flag","isDelete","cate_code","memu_code","memu_info","tel_no","ans_yn_date","ans_yn_id","ans_yn","ans_ip","ans_date_x","qna_id","qna_cate_id","qna_title","qna_content","ans_content","ans_date","create_id","create_name","create_date","create_ip","ans_id"};
		} else if("board_file".equals(tableName)){ return new String[]{"ContentType","IsDeleted","CreatedDate","filePath","FileSize","VirtualName","RealName","BoardCategoryId","PostId","BoardFileId"};
		} else if("board_golfclub_notice".equals(tableName)){ return new String[]{"createIp","createDt","createId","boardCategoryId","postId","golfclub_id"};
		} else if("board_job_find".equals(tableName)){ return new String[]{"JF_CONTENT","JF_TITLE","JF_CAREER_ETC","JF_CAREER","JF_SCHOOL","JF_WORKTIME","JF_READNUM","JF_CRATUSER","JF_CRATDATE","JF_UPDTUSER","JF_UPDTDATE","JF_ISDELETED","JF_CRATIP","consentdata","JF_TYPEETC","JF_ISLESSON","JF_AREA2","JF_SEQ","JF_SSN","JF_TEL","JF_TEL_HDN","JF_CELL","JF_CELL_HDN","JF_MAIL","JF_MAIL_HDN","JF_AREA1","JF_IMG","JF_ADDR2","JF_ADDR1","JF_POSTNUM","JF_ADDR_HDN"};
		} else if("board_job_offer".equals(tableName)){ return new String[]{"CLASS_A","J_EMAIL","J_FAX_NUM","J_TEL_NUM","J_DAM_NAME","J_LIC_ETC","J_READNUM","J_CRATUSER","J_CRATDATE","J_UPDTUSER","J_UPDTDATE","J_ISDELETED","J_CRATIP","consentdata","J_PRE_LIC","J_METHOD","J_SEQ","J_COM_NAM","J_AREA_CODE","J_TITLE","J_JOB_KIND","J_PER_NUM","J_WORK_TIME","J_WORK_PAY","J_CAREER","J_SCHOOL","J_AGE_INT","J_CONT_TXT","J_END_DAT","J_UNTILEMP"};
		} else if("board_member".equals(tableName)){ return new String[]{"player_seq","member_id","PostId","brd_mbr_Idx"};
		} else if("board_photo".equals(tableName)){ return new String[]{"crat_user","crat_date","updt_user","updt_date","crat_ip","updt_ip","del_ip","isdel","kgt","clean_kpga","supporters","file_path","mediaurl","mediatype","is_league","gm_tour","gm_round","gm_code","phto_seor","phto_year","phto_seq","phto_title","phto_content","small_img","big_img","small_width","small_height","readnum","iskpga","big_size","small_size","big_height","big_width"};
		} else if("board_proam".equals(tableName)){ return new String[]{"del_yn","regdate","create_id","att_file_name","dip_yn","crt_ip","udt_id","udt_dt","udt_ip","event_img","class_code","max_num","proam_idx","proam_name","start_date","end_date","rec_start_date","rec_start_time","rec_end_date","rec_end_time","place_name"};
		} else if("board_replay".equals(tableName)){ return new String[]{"group_no","create_ip","delete_ip","delete_flag","delete_date","parent_reply_idx","contents","member_id","create_date","postId","boardCategoryId","reply_idx"};
		} else if("board_research".equals(tableName)){ return new String[]{"research_type","rm_text","rm_qcode","rm_code"};
		} else if("board_score_form".equals(tableName)){ return new String[]{"update_ip","update_date","update_id","create_ip","create_date","create_id","req_no","ranking","score_sum_tot","game_id","region_group_course_id","round_no","member_id","score_out_tot","score_in_tot"};
		} else if("board_score_req".equals(tableName)){ return new String[]{"update_ip","update_date","update_id","create_ip","create_date","create_id","apc_year","completed","qna_id","member_id","region_group_course_id","game_id","req_no"};
		} else if("board_sc_sub".equals(tableName)){ return new String[]{"sm_sname4","sm_sname3","sm_sname2","sm_sname1","sm_pic","sm_text","sm_mper1","sm_mper2","sm_mper3","sm_ecode1","sm_ecode2","sm_nid","sm_ndate","sm_mid","sm_mdate","sm_care","sm_odate","sm_cname","sm_code","sm_name","sm_ename","sm_zip1","sm_zip2","sm_add1","sm_add2","sm_eadd1","sm_eadd2","sm_tel1","sm_tel2","sm_tel3","sm_fax1","sm_fax2","sm_fax3"};
		} else if("board_showcode".equals(tableName)){ return new String[]{"order_no","code_name","BoardCategoryId","code_id"};
		} else if("board_show_mapping".equals(tableName)){ return new String[]{"postId","code_id"};
		} else if("board_totocamp".equals(tableName)){ return new String[]{"updateDt","total","albatross","eagle","birdie","last_round","game_name","idx"};
		} else if("broadcasting".equals(tableName)){ return new String[]{"round_no","bc_refer","bc_part","bc_title","bc_time","bc_date","bc_name","game_id","tour_id","reg_date","bc_id"};
		} else if("broadcast_mst".equals(tableName)){ return new String[]{"crt_dt","crt_id","bc_save_file_nm","bc_org_file_nm","bc_name","bc_code"};
		} else if("broadcast_schedule".equals(tableName)){ return new String[]{"create_dt","bc_time","bc_date","bc_order","bc_part","bc_code","round_no","game_id"};
		} else if("cmmn_attachment".equals(tableName)){ return new String[]{"createId","createDt","createIp","updateId","updateDt","updateIp","remark","download_cnt","file_ext","attachment_serial_no","attachment_id","attachment_type","file_path_name","file_save_name","file_orgin_name"};
		} else if("cmmn_nation_code".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","nation_id","nation_name","en_name","inquiry_order","nation_image"};
		} else if("cnps_rcod_raw".equals(tableName)){ return new String[]{"gm_11h","gm_12h","gm_13h","gm_14h","gm_15h","gm_16h","gm_17h","gm_18h","gm_regdate","gm_hole1","gm_hole2","gm_10h","gm_9h","gm_8h","gm_code","gm_pcode","gm_round","gm_div","gm_1h","gm_2h","gm_3h","gm_4h","gm_5h","gm_6h","gm_7h"};
		} else if("cost_carfare_dtl".equals(tableName)){ return new String[]{"total_pay_amt","carfare_sum","increase_fee_label","increase_fee","total_intermediate","etc5_fee_label","etc5_fee","etc4_fee_label","etc_income_tax","residence_tax","updateIp","updateDt","updateId","createIp","createDt","createId","remark","total_amount_deducted","etc4_fee","etc3_fee_label","etc3_fee","reward_carfare_pm","day_pay_carfare_pm_amt","day_cnt_carfare_pm","reward_carfare","day_pay_carfare_amt","day_cnt_carfare","member_id","carfare_id","fare","course_exploring_fee","etc2_fee_label","etc2_fee","etc1_fee_label","etc1_fee","course_setting_fee","course_checking_fare","course_checking_fee","course_exploring_fare"};
		} else if("cost_carfare_mst".equals(tableName)){ return new String[]{"meeting_id","remark","createId","createDt","createIp","updateId","updateDt","updateIp","golfclub_id","game_type","carfare_id","carfare_type","carfare_name","apc_year","carfare_open_date","carfare_close_date","tour_id","game_id"};
		} else if("cost_carfare_tax".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","residenceTax_rate","incomeTax_rate","tax_close_date","tax_open_date"};
		} else if("dg_vote_candidate".equals(tableName)){ return new String[]{"vote_score","reg_dttm","reg_id","reg_ip","mod_dttm","mod_id","mod_ip","canidate_yn","exclude_reason_memo","vote_reason_cd","vote_year","vote_seq","member_id","tour_pro_num","pro_num_gbn","member_name","voteInwonStr"};
		} else if("dg_vote_delegate".equals(tableName)){ return new String[]{"mod_ip","mod_id","mod_dttm","reg_ip","reg_id","reg_dttm","vote_remark","user_name","vote_year","member_id","vote_seq","pro_num_gbn","tour_pro_no"};
		} else if("dg_vote_inwon".equals(tableName)){ return new String[]{"mod_ip","mod_id","mod_dttm","reg_ip","reg_id","reg_dttm","dang_cnt","delegate_cnt","pro_num_gbn","vote_year"};
		} else if("dg_vote_mst".equals(tableName)){ return new String[]{"reg_dttm","reg_id","reg_ip","mod_dttm","mod_id","mod_ip","del_yn","end_time","vote_year","vote_seq","fee_date","start_date","start_time","end_date"};
		} else if("dg_vote_result_detail".equals(tableName)){ return new String[]{"reg_ip","reg_dttm","pmm_code","member_name","tour_pro_num","member_id","vote_seq","vote_year"};
		} else if("dg_vote_voter".equals(tableName)){ return new String[]{"vote_exe_yn","remark","reg_dttm","reg_id","reg_ip","mod_dttm","mod_id","mod_ip","vote_exe_dttm","pi_vote_yn","vote_yn","vote_year","vote_seq","member_id","tour_pro_num","pro_num_gbn","member_name","vote_reason_cd","vote_reason_memo"};
		} else if("edu_curriculum_dtl".equals(tableName)){ return new String[]{"createDt","createIp","updateId","updateDt","updateIp","createId","remark","edu_place","curriculum_id","edu_no","edu_date","edu_subject","edu_instructor"};
		} else if("edu_curriculum_member".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","caution","member_id","curriculum_id"};
		} else if("edu_curriculum_mst".equals(tableName)){ return new String[]{"hidden_edu_date","hidden_edu_reg_date","remark","createId","createDt","createIp","updateId","updateDt","updateIp","attachment","notice","curriculum_state","curriculum_id","curriculum_type","curriculum_name","edu_target_type","edu_open_date","edu_close_date","edu_req_open_date","edu_req_close_date","edu_place"};
		} else if("edu_golfrule".equals(tableName)){ return new String[]{"update_dt","update_id","create_dt","create_id","edu_memo","edu_date","member_birthday","member_name","member_id"};
		} else if("edu_golfrule_xls".equals(tableName)){ return new String[]{"update_dt","update_id","create_dt","create_id","edu_memo","edu_date","member_birthday","member_name","member_id"};
		} else if("edu_mip_member".equals(tableName)){ return new String[]{"tourPro_mip_degree","tourPro_mip_feature","remark","createId","createDt","createIp","updateId","updateDt","updateIp","tourPro_mip_year","tourPro_pass_feature","member_id","pro_mip_mtd_type","pro_pass_degree","pro_pass_feature","pro_mip_year","pro_mip_degree","pro_mip_feature","tourPro_mip_mtd_type","tourPro_pass_degree"};
		} else if("edu_pre_member".equals(tableName)){ return new String[]{"createId","createDt","createIp","updateId","updateDt","updateIp","remark","notice","member_id","edu_year","edu_degree","edu_valid_year","edu_valid_degree","region_code"};
		} else if("employee_user".equals(tableName)){ return new String[]{"del_user","remark","createId","createDt","createIp","updateId","updateDt","updateIp","del_game","use_erp","employee_seq","member_id","user_name","dept_type","duty_type","start_date","end_date","is_resignation"};
		} else if("game_broad_schedule".equals(tableName)){ return new String[]{"createId","createDt","createIp","updateId","updateDt","updateIp","remark","program_type","close_time","game_id","game_round_id","broad_seq","channel_code","broad_date","open_time"};
		} else if("game_category".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","game_id","category_id","category_name","category_rank","category_sub_rank"};
		} else if("game_category_seed".equals(tableName)){ return new String[]{"createId","createDt","createIp","updateId","updateDt","updateIp","excel_category_name","excel_category_rank","remark","member_no","game_id","member_id","category_id","member_rank","class_type","player_name","profile_photo_1","nation_code"};
		} else if("game_cptmgr".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","duty_type","cptMgr_type","game_id","cptMgr_id"};
		} else if("game_exam_round_booking".equals(tableName)){ return new String[]{"create_dttm","create_ip","update_id","update_dttm","update_ip","apply_stat","create_id","adm_block_yn","apply_dttm","game_id","round_no","start_team_no","teeoff_no","member_id","apply_member_id"};
		} else if("game_exam_round_booking_hist".equals(tableName)){ return new String[]{"create_id","create_dttm","create_ip","update_id","update_dttm","update_ip","apply_stat","adm_block_yn","apply_dttm","hist_idx","game_id","round_no","start_team_no","teeoff_no","member_id","apply_member_id"};
		} else if("game_exam_round_mst".equals(tableName)){ return new String[]{"start_interval_mm","am_out_team_cnt","am_in_team_cnt","pm_out_team_cnt","pm_in_team_cnt","one_tem_cnt","create_id","create_dttm","update_id","update_dttm","start_pm_mm","start_pm_hh","game_id","round_no","round_date","apply_open_date","apply_open_time","apply_close_date","apply_close_time","disp_yn","start_am_hh","start_am_mm"};
		} else if("game_exam_round_time".equals(tableName)){ return new String[]{"start_hhmm","start_hole_no","start_inout_type","start_ampm_type","start_team_no","round_no","game_id"};
		} else if("game_gstar_part_point_rk".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","game_scr","part_point","tie_rank","part_type","game_id","game_player_id"};
		} else if("game_gstar_point_rk".equals(tableName)){ return new String[]{"remark","createId","createDt","createIp","updateId","updateDt","updateIp","average_stroke","average_putting","GIR","game_player_id","game_id","rk_year","tie_rank","part_point_sum","driverDist","FIR"};
		} else if("game_hole_df".equals(tableName)){ return new String[]{"hole_df_out","hole_df_18","hole_df_17","hole_df_16","hole_df_15","hole_df_14","hole_df_13","hole_df_in","hole_df_sum","player_cnt","remark","create_Id","create_dt","create_ip","update_id","update_dt","update_ip","hole_df_12","hole_df_11","game_id","region_code","game_group_code","round_no","hole_df_type","golfclub_id","course_id","hole_df_01","hole_df_02","hole_df_03","hole_df_10","hole_df_09","hole_df_08","hole_df_07","hole_df_06","hole_df_05","hole_df_04"};
		} else if("game_course".equals(tableName)){ return new String[]{"hole_meter_14","hole_meter_13","hole_meter_12","hole_meter_11","hole_meter_10","hole_meter_15","hole_meter_16","hole_meter_17","hole_meter_18","hole_meter_out","hole_meter_in","hole_meter_sum","hole_meter_9","hole_meter_8","hole_meter_7","hole_yard_16","hole_yard_17","hole_yard_18","hole_yard_out","hole_yard_in","hole_yard_sum","hole_meter_1","hole_meter_2","hole_meter_3","hole_meter_4","hole_meter_5","hole_meter_6","green_speed_fast","green_speed_little_fast","hole_img_12","hole_img_13","hole_img_14","hole_img_15","hole_img_16","hole_img_17","hole_img_18","create_id","create_dt","create_ip","update_id","update_dt","hole_img_11","hole_img_10","green_speed_normal","green_speed_little_slow","green_speed_slow","hole_img_1","hole_img_2","hole_img_3","hole_img_4","hole_img_5","hole_img_6","hole_img_7","hole_img_8","hole_img_9","update_ip","game_id","apply_rcv_yn","qualifying_free_yn","course_play_yn","game_player_cnt","game_subgroup_cnt","hole_par_1","hole_par_2","hole_par_3","hole_par_4","hole_par_5","hole_par_6","hole_par_7","game_end_date","game_start_date","region_group_course_id","game_subgroup_code","game_subgroup_name","game_subgroup_yn","game_course_no","region_code","game_group_code","golfclub_id","course_id","golfclub_name","course_name","course_expl","hole_par_8","hole_yard_15","hole_yard_3","hole_yard_4","hole_yard_5","hole_yard_6","hole_yard_7","hole_yard_8","hole_yard_9","hole_yard_10","hole_yard_11","hole_yard_12","hole_yard_13","hole_yard_14","hole_yard_2","hole_yard_1","hole_par_9","hole_par_10","hole_par_11","hole_par_12","hole_par_13","hole_par_14","hole_par_15","hole_par_16","hole_par_sum","hole_par_out","hole_par_in","hole_par_18","hole_par_17"};
		} else if("game_livescore".equals(tableName)){ return new String[]{"live_dttm","reg_dttm","start_flag","start_time","start_date","round_no","game_id"};
		} else if("game_mst".equals(tableName)){ return new String[]{"unite_sub_game_id","mbrship_fee_seq","remark","game_short_name","game_cutoff_yn","game_cutoff_round","game_mgmt_type","game_match_league","game_distancd_type","unite_main_game_id","Is_unite_game","localRule","add_localRule","game_notice","Is_ofc_game","prize_pay_rate","prize_rank_ofcRecord_rate","Is_prize_equal_dsb","Is_game_ending","caution","same_score_order","pass_player_cnt","prize_cnt_limit_yn","hidden_game_date","hidden_reg_date","test_game_yn","createId","createDt","createIp","updateId","updateDt","change_course_yn","full_refund_yn","game_cancel_yn","prize_ext_yn","close_rank_yn","close_prize_yn","close_prize2_Yn","close_point_yn","close_record_yn","close_cnps_yn","close_main_yn","updateIp","game_id","sponsor_cnt","game_ofcRecord_type","game_ref_type","game_ref_game_id","game_open_date","game_close_date","reg_open_date","reg_close_date","mgmt_type","serise_yearCnt","serise_degree","serise_id","tour_id","season_id","game_type","game_name","game_name_ment","en_name","apc_year","reg_open_time","reg_close_time","cptMtd_feature","gameFee","tourPro_gameFee","pro_gameFee","ama_gameFee","otherTourPro1_gameFee","otherTourPro2_gameFee","galleryGuide","game_summary","Is_gameFee_bundle","game_round_cnt","cptDtlMtd_type","game_prize","game_prize_title","amt_unit","std_exchgRate","pay_exchgRate","game_player_cnt","game_player_cnt_pro","game_player_cnt_ama","cptMtd_type"};
		} else if("game_mst_del".equals(tableName)){ return new String[]{"unite_sub_game_id","mbrship_fee_seq","remark","game_short_name","game_cutoff_yn","game_cutoff_round","game_mgmt_type","game_match_league","unite_main_game_id","Is_unite_game","caution","cptMtd_feature","localRule","add_localRule","Is_ofc_game","prize_pay_rate","prize_rank_ofcRecord_rate","Is_prize_equal_dsb","Is_game_ending","game_distancd_type","same_score_order","reason","del_user","del_date","createId","createDt","createIp","updateId","updateDt","close_main_yn","close_cnps_yn","pass_player_cnt","prize_cnt_limit_yn","prize_ext_yn","close_rank_yn","close_prize_yn","close_prize2_Yn","close_point_yn","close_record_yn","updateIp","game_summary","game_id","sponsor_cnt","game_ofcRecord_type","game_ref_type","game_ref_game_id","game_open_date","game_close_date","reg_open_date","reg_close_date","mgmt_type","serise_yearCnt","serise_id","tour_id","season_id","game_type","game_name","en_name","apc_year","serise_degree","reg_open_time","reg_close_time","Is_gameFee_bundle","gameFee","tourPro_gameFee","pro_gameFee","ama_gameFee","otherTourPro1_gameFee","otherTourPro2_gameFee","galleryGuide","game_round_cnt","cptDtlMtd_type","cptMtd_type","game_prize","game_prize_title","amt_unit","std_exchgRate","pay_exchgRate","game_player_cnt","game_player_cnt_pro","game_player_cnt_ama"};
		} else if("game_mst_setup".equals(tableName)){ return new String[]{"is_start_timeTbl_3R_exp","is_start_timeTbl_4R_exp","is_hole_scoreTbl_1R_exp","is_hole_scoreTbl_2R_exp","is_hole_scoreTbl_3R_exp","is_start_timeTbl_2R_exp","is_start_timeTbl_1R_exp","is_schedule_exp_setup","is_game_player_exp_setup","is_exp_setup","image_color","image_tourMain_size","image_tourMain","is_hole_scoreTbl_4R_exp","is_livescore_exp_setup","is_leaderboard_exp","game_ext_prize","game_champ_name_pre","pre_game_id","game_champ_name","game_champ_id","curr_round_no","is_game_champ_exp","is_game_notice_exp","is_hole_score_exp","is_media_exp","is_records_exp","is_thisweek_exp","game_first_second","game_image_6_size","game_id_setup","is_icon_game_news","is_icon_hole_score","is_icon_start_timeTbl","is_icon_game_player","is_icon_golfclub","is_icon_notice","is_icon_game_guide","Is_icon_livescore","url_geoPos","url_mobile_online","url_web_online","url_livescore","is_icon_geoPos","image_game_logo","image_game_logo_size","game_image_6","game_image_5_size","game_image_5","game_image_4_size","game_image_4","game_image_3_size","game_image_3","game_image_2_size","game_image_2","game_image_1_size","game_image_1","image_game_banner_size","image_game_banner"};
		} else if("game_pinposition".equals(tableName)){ return new String[]{"hole_17_pinpos","hole_16_pinpos","hole_15_pinpos","hole_14_pinpos","hole_13_pinpos","hole_18_pinpos","remark","createId","createDt","createIp","updateId","updateDt","updateIp","hole_12_pinpos","hole_11_pinpos","game_round_id","game_id","pinpos_type","hole_01_pinpos","hole_02_pinpos","hole_03_pinpos","hole_04_pinpos","hole_05_pinpos","hole_10_pinpos","hole_09_pinpos","hole_08_pinpos","hole_07_pinpos","hole_06_pinpos"};
		} else if("game_player_exception".equals(tableName)){ return new String[]{"createDt","createIp","updateId","updateDt","updateIp","createId","remark","game_player_id","game_round_id","game_id","exception_type","exception_reason"};
		} else if("game_player_point".equals(tableName)){ return new String[]{"update_dt","update_id","create_dt","create_id","get_point","point_rank","point_type","member_id","game_id"};
		} else if("game_player_prize".equals(tableName)){ return new String[]{"kpga_spc_charges","kpga_sangjo","kpga_sclship","kpga_fund","tax_residence","kpga_etc1","kpga_etc2","prize_real_rate","create_id","create_dt","update_id","upgate_dt","tax_income","exchange_rate","game_id","member_id","prize_rank","prize_type","residence_type","prize_real_won","prize_score","prize_ext_pct","prize_amt_ext","prize_amt_one","prize_amt","prize_dollar"};
		} else if("game_player_record".equals(tableName)){ return new String[]{"update_dt","update_id","create_dt","create_id","rg_round_cnt","rg_point","rg_rank","record_type","member_id","game_id"};
		} else if("game_player_req".equals(tableName)){ return new String[]{"back_send_bank","back_send_stat","back_acct_nm","back_send_remk","back_send_accno","back_send_amt","back_send_date","back_reason_text","back_reason_type","back_rate_type","back_req_date","back_reg_dttm","back_reg_id","player_req_stat","back_bank_cd","updateIp","updateDt","updateId","createIp","createDt","createId","remark","full_back_rate","game_player_deleted","back_req_stat","game_req_type","course_id","golfclub_id","game_group_code","region_code","player_class_type","member_id","region_group_course_id","game_id","player_req_idx","game_req_stat","game_req_pay_stat","game_req_dttm","pay_ok_stat","pay_ok_date","dbank_expire_date","vbank_del_dttm","vbank_tx_tid","incs_moid","pay_caution","pay_mthd_type","game_fee","game_req_mbrid"};
		} else if("game_player".equals(tableName)){ return new String[]{"game_score","game_under_par","round1_score","round2_score","round3_score","round4_score","round5_score","round6_score","game_rank_name","game_tie_ranking","is_point_get","is_prize_get","game_play_ok_date","game_giveup_yn","game_giveup_type","game_giveup_round","designation_type","game_ranking","last_round_no","play_round_cnt","updateDt","updateId","createIp","createDt","createId","remark","sponsor_name","cloth_color","exam_round_yn","fixed","game_prize_rank_yn","player_req_idx","game_match_rank","game_ama_rank","game_pro_rank","game_prize_amt","game_prize_rank","updateIp","game_id","player_military_open_date","player_profile_photo","player_nation_code","player_residence_type","player_hp_no","player_match_rank","player_seed_rank","player_seed_type","player_class_type","player_name","game_subgroup_code","course_id","golfclub_id","game_group_code","region_code","region_group_course_id","member_id","player_military_close_date","player_tour_mbship_id","Is_sclship_ded","Is_spc_charges_ded","Is_game_playing","is_cutoff_pass","Is_1R_cutoff_pass","Is_2R_cutoff_pass","Is_3R_cutoff_pass","Is_game_pass","Is_sangjo_ded","Is_tax_ded","Is_mbship_free","Is_famous_player","Is_ofc_record","Is_ofc_point","Is_ofc_prize","Is_required_game","Is_reranking","Is_rookie_point"};
		} else if("game_player_req_del".equals(tableName)){ return new String[]{"dbank_expire_date","vbank_tid","incs_moid","pay_caution","createId","createDt","createIp","updateId","updateDt","updateIp","pay_mthd_type","game_fee","delete_idx","delete_dttm","game_id","game_rgc_id","game_group_code","member_id","game_req_type","game_req_stat","game_req_dttm","game_req_mbrid"};
		} else if("game_player_req_hist".equals(tableName)){ return new String[]{"back_send_bank","back_send_accno","back_send_amt","back_send_date","back_reason_text","back_reason_type","back_rate_type","back_req_date","back_acct_nm","back_send_remk","back_reg_dttm","updateIp","updateDt","updateId","createIp","createDt","createId","remark","back_reg_id","pay_ok_stat","pay_ok_date","dbank_expire_date","game_group_code","region_code","player_class_type","member_id","region_group_course_id","game_id","player_req_idx","player_req_hist_idx","golfclub_id","course_id","game_req_type","vbank_tx_tid","incs_moid","pay_caution","pay_mthd_type","game_fee","game_req_mbrid","game_req_dttm","game_req_stat"};
		} else if("game_player_req_pend".equals(tableName)){ return new String[]{"tx_try_dttm","member_id","tx_try_idx"};
		} else if("game_player_starttime_fix".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","fixed","game_id"};
		} else if("game_poff_hole".equals(tableName)){ return new String[]{"createDt","createIp","updateId","updateDt","updateIp","createId","remark","poff_par","Is_poff_ends","poff_hole","poff_type","game_id","serial_no"};
		} else if("game_poff_hole_hist".equals(tableName)){ return new String[]{"createDt","createIp","updateId","updateDt","updateIp","move_id","createId","remark","poff_par","hist_dttm","serial_no","game_id","poff_type","poff_hole","Is_poff_ends"};
		} else if("game_poff_score".equals(tableName)){ return new String[]{"tieOrder","hole_18_score","hole_17_score","hole_16_score","hole_15_score","hole_14_score","remark","createId","createDt","createIp","updateId","updateDt","updateIp","temp_game_id","hole_13_score","hole_12_score","hole_11_score","game_id","member_id","poff_target","Is_poff_winning","hole_01_score","hole_02_score","hole_03_score","hole_04_score","hole_10_score","hole_09_score","hole_08_score","hole_07_score","hole_06_score","hole_05_score"};
		} else if("game_poff_score_hist".equals(tableName)){ return new String[]{"tieOrder","hole_18_score","hole_17_score","hole_16_score","hole_15_score","hole_14_score","remark","createId","createDt","createIp","updateId","updateDt","updateIp","temp_game_id","move_id","hole_13_score","hole_12_score","hole_11_score","hist_dttm","game_id","member_id","poff_target","Is_poff_winning","hole_01_score","hole_02_score","hole_03_score","hole_04_score","hole_10_score","hole_09_score","hole_08_score","hole_07_score","hole_06_score","hole_05_score"};
		} else if("game_point_dsbtbl".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","mod_dsb_point","ofc_dsb_point","dsb_rank","point_type","game_id"};
		} else if("game_prize_dsbtbl".equals(tableName)){ return new String[]{"createId","createDt","createIp","updateId","updateDt","updateIp","remark","apc_dsb_amt","game_id","dsb_rank","dsb_rank_type","dsb_rank_label","ofc_dsb_amt","mod_dsb_amt"};
		} else if("game_prize_event".equals(tableName)){ return new String[]{"kpga_fund","kpga_sclship","kpga_sangjo","kpga_spc_charges","kpga_etc1","kpga_etc2","prize_real_rate","create_id","create_dt","update_id","upgate_dt","tax_residence","tax_income","prize_event_id","game_id","member_id","prize_rank","prize_type","player_residence_type","prize_real_won","prize_score","prize_dollar","prize_amt","exchange_rate"};
		} else if("game_prize_spc".equals(tableName)){ return new String[]{"spc_prize_memo","create_id","create_dt","update_id","update_dt","spc_prize_name","spc_prize_spon","spc_prize_uni_code","spc_prize_score","spc_prize_amt","spc_prize_idx","game_id","round_no","spc_prize_cd","member_id","spc_prize_rank","spc_prize_date","player_residence_type"};
		} else if("game_prize_spc_lst".equals(tableName)){ return new String[]{"spc_prize_hole3","spc_prize_hole4","create_id","create_dt","update_id","update_dt","spc_prize_hole2","spc_prize_hole1","spc_prize_offer","game_id","spc_prize_cd","spc_prize_rank","spc_prize__won","spc_prize__tax_yn","spc_prize_pro"};
		} else if("game_program_1_dtl".equals(tableName)){ return new String[]{"thisYear_tour","thisYear_rk","thisYear_prize","winning_cnt","remark","createId","createDt","createIp","updateId","updateDt","updateIp","lastYear_prize","lastYear_rk","game_id","game_round_id","game_player_name","game_course_id","timeGroup_type","game_player_group","game_player_start_time","game_player_dob","game_player_age","game_player_admission_date","lastYear_tour"};
		} else if("game_program_2_dtl".equals(tableName)){ return new String[]{"game_player_fairway","game_player_birdie","remark","createId","createDt","createIp","updateId","updateDt","updateIp","game_player_driverDist","game_player_average_putting","game_player_GIR","game_id","game_round_id","game_player_name","game_course_id","timeGroup_type","game_player_group","game_player_start_time","game_player_prize","game_player_average_stroke"};
		} else if("game_program_3_dtl".equals(tableName)){ return new String[]{"remark","createId","createDt","createIp","updateId","updateDt","updateIp","game_score","game_year","game_id","game_round_id","game_player_name","game_course_id","timeGroup_type","game_player_group","game_player_start_time"};
		} else if("game_program_4_dtl".equals(tableName)){ return new String[]{"remark","createId","createDt","createIp","updateId","updateDt","updateIp","game_average_stroke","game_year","game_name","game_id","game_round_id","game_player_name","game_course_id","timeGroup_type","game_player_group","game_player_start_time"};
		} else if("game_qlf_mst".equals(tableName)){ return new String[]{"Is_kr_nationality","Is_designation","remark","createId","createDt","createIp","updateId","updateDt","updateIp","check_ageAllow","ageLimit","game_id","Is_tourPro","Is_otherTourPro1","Is_otherTourPro2","Is_ama","ama_kor_yn","ama_frgn_yn","ageAllow","Is_pro"};
		} else if("game_qlf_player".equals(tableName)){ return new String[]{"remark","member_no","admission_date","requester_hp_no","military_close_date","member_rank","createId","createDt","createIp","updateId","updateDt","updateIp","military_open_date","residence_type","game_id","member_id","category_id","designation_type","reg_mtd_type","gameFee_type","gameFee","caution","class_type","player_name","profile_photo_1","nation_code"};
		} else if("game_qualifying_round_plan_dtl".equals(tableName)){ return new String[]{"create_ip","create_dt","create_id","update_ip","update_dt","update_id","passed_ama_member_count","passed_ama_member_gap","game_id","region_group_course_id","passed_member_rate","passed_member_gap","passed_member_count","passed_ama_member_rate"};
		} else if("game_qualifying_round_plan_mst".equals(tableName)){ return new String[]{"update_id","update_dt","update_ip","create_id","create_dt","create_ip","total_passed_ama_member_count","total_passed_ama_member_rate","total_passed_member_count","total_passed_member_rate","game_id"};
		} else if("game_ranking".equals(tableName)){ return new String[]{"prize","point","remark","createId","createDt","createIp","updateId","updateDt","updateIp","score","stroke","game_player_id","game_id","rk_year","game_playing_cnt","order_rank","tie_rank","Is_tie","pro_rank","ama_rank"};
		} else if("game_ranking_foreign".equals(tableName)){ return new String[]{"Is_poff","remark","createId","createDt","createIp","updateId","updateDt","updateIp","prize","tie_rank","game_player_id","foreign_game_no","tour_name","tour_year","Is_game_ofcRecord","game_name","en_name","cptMtd_type"};
		} else if("game_rookie_point_rk".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","point","tie_rank","rk_year","game_player_id"};
		} else if("game_round".equals(tableName)){ return new String[]{"pm_out_team_cnt","pm_in_team_cnt","one_team_cnt","remark","am_in_team_cnt","am_out_team_cnt","round_cutoff_yn","start_shotgun_yn","start_player_cnt","start_interval_min","disp_yn","strattime_state","chng_course_yn","updateIp","updateDt","updateId","createIp","createDt","createId","current_round_yn","chng_remark","chng_game_subgroup_code","chng_region_group_course_id","start_pm_time","start_am_time","disp_livescore_yn","tie_rule_type","green_speed","round_rule_type","round_date","round_state","round_nm","round_no","game_subgroup_code","region_group_course_id","game_id","cutoff_yn","cutoff_type","cutoff_score","disp_score_yn","disp_time_yn","ext_conn_yn_2","ext_conn_yn_1","cnps_end_yn","cutoff_exec_yn","cutoff_pro_cnt","cutoff_ama_cnt","cutoff_player_cnt","cutoff_ment"};
		} else if("game_round_starttime".equals(tableName)){ return new String[]{"notice","remark","createId","createDt","createIp","updateId","updateDt","updateIp","start_time","start_hole_no","game_id","region_group_course_id","game_subgroup_code","round_no","start_time_id","start_am_pm","start_in_out","exp_group_no"};
		} else if("game_score".equals(tableName)){ return new String[]{"putt_in_tot","putt_sum_tot","pttt_sum_tot","udpar_sum_tot","round_rank","accu_rank","accu_sum_score","accu_sum_underpar","putt_out_tot","putt_18","putt_17","putt_9","putt_10","putt_11","putt_12","putt_13","putt_14","putt_15","putt_16","accu_backcount","round_end_yn","exception_memo","round_cutoff_yn","poff_rank","create_id","create_date","create_ip","update_id","update_date","exception_type","exception_yn","match_win_flag","match_win_type","match_skip_hole_cnt","match_poff_yn","match_poff_hole_cnt","match_winner_type","match_winner_id","match_tomt_level","update_ip","game_id","score_2","score_3","score_4","score_5","score_6","score_7","score_8","score_9","score_1","start_time","region_group_course_id","game_subgroup_code","round_no","member_id","start_time_id","start_team_no","start_tee_off_order","start_sms_id","score_10","score_11","putt_1","putt_2","putt_3","putt_4","putt_5","putt_6","putt_7","putt_8","score_backcount","score_sum_tot","score_in_tot","score_12","score_13","score_14","score_15","score_16","score_17","score_18","score_out_tot"};
		} else if("game_score_display".equals(tableName)){ return new String[]{"createId","createDt","createIp","updateId","updateDt","updateIp","display_yn","start_tee_off_order","start_team_no","game_id","region_group_course_id","game_subgroup_code","member_id","round_no","start_time_id"};
		} else if("game_score_excel".equals(tableName)){ return new String[]{"putt_10","putt_9","putt_8","putt_7","putt_6","putt_5","putt_4","putt_3","putt_2","putt_1","score_backcount","putt_11","putt_12","putt_13","update_ip","update_date","update_id","create_ip","create_date","create_id","putt_18","putt_17","putt_16","putt_15","putt_14","score_sum_tot","score_in_tot","game_score_idx","score_5","score_4","score_3","score_2","score_1","member_nm","member_id","round_no","game_subgroup_code","region_group_course_id","game_id","score_6","score_7","score_out_tot","score_18","score_17","score_16","score_15","score_14","score_13","score_8","score_9","score_10","score_11","score_12"};
		} else if("game_score_league".equals(tableName)){ return new String[]{"league_group","league_up_dn","league_win","league_round","game_subgroup_code","region_group_course_id","member_id","game_id"};
		} else if("game_score_league_close".equals(tableName)){ return new String[]{"league_up_dn_17","league_up_dn_32","league_up_dn_64","player_match_rank","league_close_rank","create_id","create_dt","create_ip","league_up_dn_18","league_up_dn_19","game_id","member_id","league_group","league_group_rank","league_win_cnt","league_same_cnt","league_lose_cnt","league_up_dn_sum"};
		} else if("game_score_mc".equals(tableName)){ return new String[]{"match_17","match_18","score_out_tot","score_in_tot","score_sum_tot","udpar_sum_tot","match_16","match_15","match_14","match_13","match_12","match_11","match_10","match_9","match_8","updn_out","updn_in","updn_sum","update_date","update_id","create_ip","create_date","create_id","match_rank","ext_hole_no","ext_match_yn","win_flag","win_text","tomt_level","round_end_yn","now_hole_no","updn_hole_cnt","update_ip","game_id","score_7","score_6","score_5","score_4","score_3","score_2","score_1","start_tee_off_order","start_team_no","start_time_id","round_no","member_id","game_subgroup_code","region_group_course_id","score_8","score_9","score_10","match_7","match_6","match_5","match_4","match_3","match_2","match_1","score_18","score_17","score_11","score_12","score_13","score_14","score_15","score_16"};
		} else if("game_score_mc_off".equals(tableName)){ return new String[]{"hole_30_no","hole_29_no","hole_28_no","hole_27_no","hole_26_no","hole_25_no","hole_24_no","hole_23_no","hole_22_no","hole_21_no","hole_31_no","hole_32_no","hole_33_no","updateIp","updateDt","updateId","createIp","createDt","createId","remark","hole_36_no","hole_35_no","hole_34_no","hole_20_no","hole_19_no","hole_36_score","hole_22_score","hole_21_score","hole_20_score","hole_19_score","Is_poff_winning","poff_target","member_id","start_time_id","round_no","game_id","hole_23_score","hole_24_score","hole_25_score","hole_35_score","hole_34_score","hole_33_score","hole_32_score","hole_31_score","hole_30_score","hole_29_score","hole_28_score","hole_27_score","hole_26_score"};
		} else if("game_score_mc_off_hist".equals(tableName)){ return new String[]{"hole_28_no","hole_29_no","hole_30_no","hole_31_no","hole_27_no","hole_26_no","hole_25_no","hole_24_no","hole_23_no","hole_22_no","hole_21_no","hole_32_no","hole_33_no","hole_34_no","hist_date","updateIp","updateDt","updateId","createIp","createDt","createId","remark","hole_36_no","hole_35_no","hist_updateId","hole_20_no","game_id","hole_23_score","hole_22_score","hole_21_score","hole_20_score","hole_19_score","Is_poff_winning","poff_target","member_id","start_time_id","round_no","hole_24_score","hole_25_score","hole_26_score","hole_19_no","hole_36_score","hole_35_score","hole_34_score","hole_33_score","hole_32_score","hole_31_score","hole_30_score","hole_29_score","hole_28_score","hole_27_score"};
		} else if("game_score_record".equals(tableName)){ return new String[]{"std_birdie_par4","std_birdie_par3","std_avg_par6","std_avg_par5","std_avg_par4","std_avg_par3","par_savior","par_breaker","std_birdie_par5","std_birdie_avg","fairway_rate","updateIp","updateDt","updateId","createIp","createDt","createId","remark","bunker_rate","avg_birdie_rate","avg_putt_rate","green_in_rate","udp_eagle_cnt","udp_albatross_cnt","udp_hio_cnt","game_subgroup_code","region_group_course_id","member_id","round_no","game_id","udp_birdie_cnt","udp_even_cnt","udp_bogey_cnt","driver2_dist","driver1_dist","driver_hole2_dist","driver_hole1_dist","udp_sum_cnt","udp_etc_bogey_cnt","udp_triple_bogey_cnt","udp_double_bogey_cnt"};
		} else if("game_score_stableford".equals(tableName)){ return new String[]{"point_out_tot","point_18","point_17","point_16","point_15","point_14","point_in_tot","point_sum_tot","accu_sum_point","create_id","create_date","create_ip","update_id","update_date","update_ip","point_13","point_12","point_11","game_id","region_group_course_id","game_subgroup_code","round_no","member_id","point_1","point_2","point_3","point_4","point_10","point_9","point_8","point_7","point_6","point_5"};
		} else if("game_seq_last_member".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","prev_display_type","display_type","member_id","game_id"};
		} else if("game_starttime_category_dtl".equals(tableName)){ return new String[]{"createIp","updateId","updateDt","updateIp","createDt","createId","starttime_id","category_id","game_subgroup_code","region_group_course_id","game_id"};
		} else if("game_starttime_category_member".equals(tableName)){ return new String[]{"createId","createDt","createIp","updateId","updateDt","updateIp","ranking","fixed","game_id","region_group_course_id","game_subgroup_code","category_id","member_id","subcategory_id"};
		} else if("game_starttime_category_mst".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","player_cnt","category_id","game_subgroup_code","region_group_course_id","game_id"};
		} else if("game_starttime_random_member".equals(tableName)){ return new String[]{"createId","createDt","createIp","updateId","updateDt","updateIp","fixed","starttime_tee_order","game_id","region_group_course_id","game_subgroup_code","category_id","starttime_id","member_id"};
		} else if("game_starttime_random_ranking".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","ranking","starttime_tee_order","starttime_id","category_id","game_subgroup_code","region_group_course_id","game_id"};
		} else if("game_starttime_random_setup".equals(tableName)){ return new String[]{"createId","createDt","createIp","updateId","updateDt","updateIp","start_time","start_hole_no","exp_group_no","game_id","region_group_course_id","game_subgroup_code","starttime_id","start_am_pm","start_in_out"};
		} else if("game_starttime_random_setup_default".equals(tableName)){ return new String[]{"pmOutTeamCnt","pmInTeamCnt","createId","createDt","createIp","updateId","updateDt","updateIp","amInTeamCnt","amOutTeamCnt","game_id","region_group_course_id","game_subgroup_code","startIntervalMin","startAmHH","startAmMM","startPmHH","startPmMM"};
		} else if("game_timepar".equals(tableName)){ return new String[]{"hole_time_par_out","hole_time_par_18","hole_time_par_17","hole_time_par_16","hole_time_par_15","hole_time_par_in","remark","createId","createDt","createIp","updateId","updateDt","updateIp","hole_time_par_14","hole_time_par_13","hole_time_par_12","game_id","round_id","hole_time_par_1","hole_time_par_2","hole_time_par_3","hole_time_par_4","hole_time_par_5","hole_time_par_6","hole_time_par_7","hole_time_par_8","hole_time_par_9","hole_time_par_10","hole_time_par_11"};
		} else if("game_trackman".equals(tableName)){ return new String[]{"flatBallSpeed","flatVertAngle","flatSide","flatLength","pointTime","pointHeight","flatFlightTtime","remark","createId","createDt","createIp","updateId","updateDt","updateIp","pointSide","pointLength","game_player_id","game_round_id","game_id","launchClubSpeed","launchBallSpeed","launchSmashFactor","launchVertAngle","launchHorzAngle","flightMaxHeight","launchSideSpin","launchBackSpin","launchSpinRate","launchLiftCoef","launchDragCoef"};
		} else if("game_weather".equals(tableName)){ return new String[]{"tmx","uuu","vvv","wav","vec","wsd","remark","createId","createDt","createIp","updateId","updateDt","updateIp","tmn","t3h","t1h","game_date","game_time","game_round_id","game_id","region_group_course_id","forecast_type","pop","pty","sky","s06","reh","r06","rn1"};
		} else if("game_wt_member".equals(tableName)){ return new String[]{"SMS_tm_id","remark","createId","createDt","createIp","updateId","updateDt","updateIp","Is_wt_SMS_tm","wt_req_datetime","member_id","game_course_id","game_id","class_type","game_turnNo","course_turnNo","wt_requester_type","wt_requester_name"};
		} else if("game_year_gmcnt".equals(tableName)){ return new String[]{"update_id","update_dt","game_season","game_tour_cnt","game_play_cnt","member_id","tour_id","game_year"};
		} else if("game_year_gmcnt_before".equals(tableName)){ return new String[]{"update_id","update_dt","game_season","game_tour_cnt","game_play_cnt","member_id","tour_id","game_year"};
		} else if("game_year_point".equals(tableName)){ return new String[]{"accu_point","create_id","create_dt","update_id","update_dt","point_ranking","point_class","game_year","tour_id","point_type","point_season","member_id"};
		} else if("game_year_prize".equals(tableName)){ return new String[]{"update_dt","update_id","create_dt","create_id","accu_prize_amt","game_ranking","member_id","tour_id","game_year"};
		} else if("golfclub_image".equals(tableName)){ return new String[]{"createDt","createIp","updateId","updateDt","updateIp","createId","deleted","file_path","file_size","virtual_name","real_name","seq_no","golfclub_id"};
		} else if("handicap_certificate".equals(tableName)){ return new String[]{"createDt","createIp","updateId","updateDt","updateIp","createId","remark","pri_hp_no","year","member_id","handicap","apply_yn","underpar"};
		} else if("honors_contents".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","comment","seq_no"};
		} else if("honors_contents_detail".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","deleted","contents","section_type","seq_no"};
		} else if("honors_contents_type".equals(tableName)){ return new String[]{"createIp","updateId","updateDt","updateIp","createDt","createId","deleted","remark","order","section_name","section_type"};
		} else if("honors_file".equals(tableName)){ return new String[]{"createId","createDt","createIp","updateId","updateDt","updateIp","remark","file_div","seq_no","file_path","file_name","file_org_name","file_ext","file_size"};
		} else if("honors_image".equals(tableName)){ return new String[]{"remark","createId","createDt","createIp","updateId","updateDt","updateIp","deleted","readnum","link","seq_no","year","view_type","image","small_image","comment","content_text"};
		} else if("honors_member".equals(tableName)){ return new String[]{"member_no","member_name","member_name_eng","comment","link","image","deleted","updateIp","updateDt","updateId","createIp","createDt","createId","remark"};
		} else if("honors_schedule".equals(tableName)){ return new String[]{"createId","createDt","createIp","updateId","updateDt","updateIp","remark","deleted","seq_no","year","quarter","schedule","schedule_text","contents"};
		} else if("honors_view_type".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","deleted","order","view_name","view_type"};
		} else if("incs_tx_crosschk".equals(tableName)){ return new String[]{"tx_bank_name","tx_ok_date","tx_ok_time","tx_cancel_date","tx_cancle_time","reg_dttm","mod_dttm","tx_goods_name","tx_buyer_name","tx_mid","tx_moid","tx_tid","tx_rslt_nm","tx_mthd","tx_device","tx_amt"};
		} else if("incs_tx_error".equals(tableName)){ return new String[]{"dtinput","tminput","nminput","nminputbank","cancelDate","cancelTime","incs_error_type","resultInfo","createDt","vacct","clientIp","osType","oid","mid","tid","payMethod","price","returnUrl","resultCode","cancelMsg","cancelNum"};
		} else if("incs_tx_item".equals(tableName)){ return new String[]{"adapt_date","adapt_yn","goods_price","goods_name","pay_key_code","pay_key","pay_type","tx_moid"};
		} else if("incs_tx_log".equals(tableName)){ return new String[]{"tx_map","tx_moid","tx_date"};
		} else if("incs_tx_mobile_result".equals(tableName)){ return new String[]{"process_date","err_msg","process_flag","createDate","P_MID","P_AMT","P_NOTI","P_REQ_URL","P_TID","P_RMESG1","P_STATUS","result_seq"};
		} else if("incs_tx_result".equals(tableName)){ return new String[]{"charset","merchantData","createDate","process_flag","err_msg","process_date","netCancelUrl","authUrl","authToken","orderNumber","mid","resultMsg","resultCode","result_seq"};
		} else if("logonuserinfolist".equals(tableName)){ return new String[]{"session_id","web_login_ip","last_login_date","web_login_id","member_id"};
		} else if("main_layout_setting".equals(tableName)){ return new String[]{"tmpl_yn","tmpl_order","tmpl_style","tour_id"};
		} else if("main_layout_style".equals(tableName)){ return new String[]{"tmpl_use_yn","tmpl_sort","tmpl_value","tmpl_type","tmpl_name","tmpl_style","tmpl_seq"};
		} else if("main_setting_pick".equals(tableName)){ return new String[]{"tm_update_ip","tm_update_user","tm_update_dt","tm_mo_real_file_nm","tm_mo_save_file_nm","tm_real_file_nm","tm_save_file_nm","tm_visible","tour_id","tm_style","tm_pick_order","tm_upper_id","tm_detail_id"};
		} else if("main_setting_value".equals(tableName)){ return new String[]{"tm_update_ip","tm_update_user","tm_update_dt","tm_sub_value","tm_value","tm_pick_order","tm_style","tour_id"};
		} else if("media_guide_book".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","image","download_cnt","title_name","attachment_id","year"};
		} else if("mgmt_admission".equals(tableName)){ return new String[]{"createIp","updateId","updateDt","updateIp","createDt","createId","remark","admission_amt","class_type","apc_close_date","apc_open_date"};
		} else if("incs_tx_mst".equals(tableName)){ return new String[]{"dbank_acct_no","dbank_acct_name","dbank_appl_date","incs_chk_amt","tx_rslt_stat","tx_pay_date","dbank_code","dbank_nm","card_comp_code","card_type_code","card_quota","card_auth_no","card_num","vpay_bank_nm","tx_pay_amt","tx_pay_stat","dvc_uesrAgent","dvc_brws","dvc_os","update_dt","update_id","tx_reg_dttm","tx_buyer_nm","xls_ok_stat","xls_dttm","xls_amt","xls_toid","xls_rslt_msg","xls_rslt_cd","xls_mthd","vpay_acct_no","vpay_bank_cd","vpay_acct_nm","tx_rslt_msg","tx_rslt_cd","tx_device","tx_buyer_name","tx_goods_name","tx_dttm","tx_mthd","tx_toid","tx_mid","tx_amt","tx_path_type","tel_hp_no","member_id","tx_moid","tx_rslt_info","tx_payok_yn","vapy_time","vpay_date","vpay_amt","vpay_rslt_info","vpay_rslt_msg","vpay_rslt_cd","vpay_toid","vact_owner_name","tx_payok_dttm","vact_bank_cd","vact_bank_nm","vact_acct_no","vact_limit_date","vact_limit_time"};
		} else if("mgmt_bank".equals(tableName)){ return new String[]{"createIp","updateId","updateDt","updateIp","createDt","createId","remark","hana_code","bank_name","bank_code"};
		} else if("mgmt_channel".equals(tableName)){ return new String[]{"createIp","updateId","updateDt","updateIp","createDt","createId","remark","channel_logo","channel_name","channel_id"};
		} else if("mgmt_company".equals(tableName)){ return new String[]{"create_id","create_dt","company_name","company_id"};
		} else if("mgmt_company_auth_member".equals(tableName)){ return new String[]{"create_id","create_dt","create_ip","result_message","result","return_url","user_dob","user_name","member_no","company_id"};
		} else if("mgmt_cptsteering".equals(tableName)){ return new String[]{"createDt","createIp","updateId","updateDt","updateIp","createId","remark","position_type","duty_type","region_code","dms_date","apt_date","cptSteering_id"};
		} else if("mgmt_dues_cfg".equals(tableName)){ return new String[]{"createDt","createIp","updateId","updateDt","updateIp","createId","remark","aiddues_amt","gendues_amt","class_type","apc_close_month","apc_open_month"};
		} else if("mgmt_dues_yymm".equals(tableName)){ return new String[]{"pr_aiddues_amt","pr_gendues_amt","tp_aiddues_amt","tp_gendues_amt","dues_yymm"};
		} else if("mgmt_fund".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","spcCharges_rate","sclShip_rate","mutualAid_rate","apc_year"};
		} else if("mgmt_golfclub".equals(tableName)){ return new String[]{"golfclub_ny","golfclub_nx","photo_5","photo_4","photo_3","photo_2","latitude","longitude","remark","createId","createDt","createIp","updateId","updateDt","updateIp","photo_1","golfclub_feature","golfclub_facil","golfclub_id","golfclub_type","golfclub_name","en_name","nation_code","region_code","Is_ofc","ofc_no","ofc_year","golfclub_image","homepage_url","address_2","address_1","zipcode","tel_no"};
		} else if("mgmt_golfclub_course".equals(tableName)){ return new String[]{"hole_meter_16","hole_meter_17","hole_meter_18","hole_meter_out","hole_meter_in","hole_meter_sum","green_speed_fast","green_speed_little_fast","green_speed_normal","green_speed_little_slow","green_speed_slow","hole_img_1","hole_meter_15","hole_meter_14","hole_meter_3","hole_meter_4","hole_meter_5","hole_meter_6","hole_meter_7","hole_meter_8","hole_meter_9","hole_meter_10","hole_meter_11","hole_meter_12","hole_meter_13","hole_meter_2","hole_img_2","hole_img_16","hole_img_17","hole_img_18","asset_name","latitude","longitude","create_id","create_dt","update_dt","update_id","create_ip","hole_img_15","hole_img_14","hole_img_3","hole_img_4","hole_img_5","hole_img_6","hole_img_7","hole_img_8","hole_img_9","hole_img_10","hole_img_11","hole_img_12","hole_img_13","update_ip","golfclub_id","hole_par_6","hole_par_7","hole_par_8","hole_par_9","hole_par_10","hole_par_11","hole_par_12","hole_par_13","hole_par_14","hole_par_15","hole_par_16","hole_par_5","hole_par_4","course_id","course_name","course_name_eng","tour_id","course_unuse_yn","course_use_yn","course_cnts","course_group_id","hole_par_1","hole_par_2","hole_par_3","hole_par_17","hole_meter_1","hole_yard_11","hole_yard_12","hole_yard_13","hole_yard_14","hole_yard_15","hole_yard_16","hole_yard_17","hole_yard_18","hole_yard_out","hole_yard_in","hole_yard_sum","hole_yard_10","hole_yard_9","hole_par_18","hole_par_in","hole_par_out","hole_par_sum","hole_yard_1","hole_yard_2","hole_yard_3","hole_yard_8","hole_yard_7","hole_yard_6","hole_yard_5","hole_yard_4"};
		} else if("mgmt_golfclub_member".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","member_id","golfclub_id"};
		} else if("mgmt_insurance_fee".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","refund_yn","insurance_fee","game_year"};
		} else if("mgmt_ktour_mbrship_fee".equals(tableName)){ return new String[]{"create_dttm","create_ip","update_id","update_dttm","update_ip","create_id","refund_end_date","refund_start_date","game_year","mbrship_fee_regular","mbrship_fee_temp","nonmember_fee_temp","refund_yn"};
		} else if("mgmt_location".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","Is_use","location_name","location_type","location_id"};
		} else if("mgmt_mbrship_fee".equals(tableName)){ return new String[]{"refund_start_date","refund_end_date","mbrhhip_fee_tourpro_frgn1","mbrhhip_fee_tourpro_frgn2","mbrship_ment","refund_yn","mbrship_fee_tourpro","mbrship_fee_pro","mbrship_fee_ama","tour_id","game_quarter","game_year"};
		} else if("mgmt_ofc_point_dsbtbl_dtl".equals(tableName)){ return new String[]{"createDt","createIp","updateId","updateDt","updateIp","createId","remark","dsb_point","point_type","apc_year","tour_id","point_id","dsb_rank"};
		} else if("mgmt_ofc_point_dsbtbl_mst".equals(tableName)){ return new String[]{"createDt","createIp","updateId","updateDt","updateIp","createId","remark","point_name","point_type","apc_year","tour_id","point_id"};
		} else if("mgmt_ofc_prize_dsbtbl_dtl".equals(tableName)){ return new String[]{"createId","createDt","createIp","updateId","updateDt","updateIp","remark","dsb_amt","prize_id","tour_id","apc_year","dsb_rank","dsb_rank_type","dsb_rank_label"};
		} else if("mgmt_ofc_prize_dsbtbl_mst".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","prize_amt","prize_name","apc_year","tour_id","prize_id"};
		} else if("mgmt_prize_tax".equals(tableName)){ return new String[]{"createDt","createIp","updateId","updateDt","updateIp","createId","kpga_spc_charges_other","kpga_spc_charges","kpga_sangjo","year","tax_income","tax_income_other","tax_residence","tax_residence_other","kpga_sclship"};
		} else if("mgmt_season".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","season_name","season_type","season_id"};
		} else if("mgmt_serise".equals(tableName)){ return new String[]{"remark","createId","createDt","createIp","updateId","updateDt","updateIp","serise_feature","serise_close_date","serise_id","tour_id","serise_type","serise_name","en_name","Is_open","serise_open_date"};
		} else if("mgmt_sponsor".equals(tableName)){ return new String[]{"remark","createId","createDt","createIp","updateId","updateDt","updateIp","exp_order","sponsor_image_2","sponsor_id","sponsor_type","sponsor_name","en_name","Is_use","sponsor_url","sponsor_image_1"};
		} else if("mgmt_staff".equals(tableName)){ return new String[]{"createId","createDt","createIp","updateId","updateDt","updateIp","remark","web_tester_type","web_manager_type","staff_id","staff_state","company","dept_type","duty_type","position_type"};
		} else if("mgmt_starttime_category".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","background_color","category_order","category_comment","category_name","category_id"};
		} else if("mgmt_starttime_subcategory".equals(tableName)){ return new String[]{"createIp","updateId","updateDt","updateIp","createDt","createId","category_order","category_comment","category_name","category_id","subcategory_id"};
		} else if("mgmt_tour".equals(tableName)){ return new String[]{"remark","createId","createDt","createIp","updateId","updateDt","updateIp","game_history_exp","schedule_exp","tour_id","tour_type","tour_name","Is_open","tour_open_date","tour_close_date","notice_exp"};
		} else if("mgmt_tour_category".equals(tableName)){ return new String[]{"remark","createId","createDt","createIp","updateId","updateDt","updateIp","category_feature","Is_reRanking","Is_min_obligation_cnt","tour_id","category_id","apc_year","year_half","category_name","category_rank","category_sub_rank","apc_period_type"};
		} else if("mgmt_tour_seed".equals(tableName)){ return new String[]{"remark","createId","createDt","createIp","updateId","updateDt","updateIp","tieOrder","Is_exp","reg_reason","member_id","category_id","tour_id","hy_type","apc_year","member_rank","Is_reg"};
		} else if("msg_result".equals(tableName)){ return new String[]{"TELECOM","SAVE_TIME","TCPRECV_TIME","REPORT_TIME","SEND_TIME","REQUEST_TIME","INSERT_TIME","FILECNT_CHECKUP","FILESIZE5","RESULT","REPCNT","EXT_COL3","EXT_COL2","EXT_COL1","EXT_COL0","OPT_NAME","OPT_POST","OPT_CMP","OPT_ID","SERVER_ID","FILELOC5","FILESIZE4","SUBJECT","STAT","CALLBACK","DSTADDR","DCNT","DKEY","SEND_TYPE","MSG_TYPE","MSEQ","TEXT_TYPE","TEXT","FILELOC4","FILESIZE3","FILELOC3","FILESIZE2","FILELOC2","FILESIZE1","FILELOC1","FILECNT","EXPIRETIME"};
		} else if("opi_bill_billkey".equals(tableName)){ return new String[]{"card_num","card_bankcode","ipb_resultmsg","ipb_paydevice","ipb_start_date","ipb_end_date","ipb_resultMap","cardbill_reg_dttm","cardbill_mod_dttm","member_birthdate","cardbill_memo","card_code","card_billkey","member_id","cardbill_state","ipb_moid","ipb_price","ipb_goodname","ipb_buyername","ipb_buyertel","ipb_buyeremail","ipb_signature","ipb_paytypechk","ipb_resultcode"};
		} else if("opi_bill_cardbilling".equals(tableName)){ return new String[]{"incs_tid","incs_bill_date","incs_bill_title","incs_bill_amt","incs_result_msg","incs_result_code","incs_result_info","incs_result_date","card_billkey","sms_date","sms_msg","member_id","req_std_yymm","req_std_yymm_seq","incs_moid","req_fr_yymm","req_to_yymm","req_gendues_amt","req_aiddues_amt"};
		} else if("opi_dues_excel_member".equals(tableName)){ return new String[]{"pay_method_type","pay_remark","createId","createDt","createIp","pay_date","pay_amt","user_name","member_id","upload_seq","upload_date","no"};
		} else if("opi_dues_exception".equals(tableName)){ return new String[]{"createDate","createIp","updateId","updateDate","updateIp","createId","remark","end_yymm","member_id","class_type","req_gendues_amt","req_aiddues_amt","start_yymm"};
		} else if("opi_dues_month".equals(tableName)){ return new String[]{"update_ip","create_ip","update_date","update_id","create_date","create_id","memo_text","member_id"};
		} else if("opi_dues_mst".equals(tableName)){ return new String[]{"req_dues_remark","req_use_yn","req_std_yymm","incs_moid","incs_toid","create_id","create_dt","create_ip","update_id","update_dt","update_ip","req_adjust_amt","req_aiddues_amt","req_gendues_amt","member_id","dues_yymm","member_class_type","gendues_amt","aiddues_amt","pay_ok_yn","pay_date","pay_cnt","pay_method_type","pay_del_yn","pay_remark"};
		} else if("opi_dues_mst_dtl".equals(tableName)){ return new String[]{"incs_toid","incs_moid","pay_remark","pay_plusminus","update_flag","create_id","create_dt","create_ip","update_id","update_dt","update_ip","dues_change_code","pay_del_yn","payment_idx","member_id","dues_yymm","member_class_type","dues_seq","gendues_amt","aiddues_amt","pay_method_type","pay_memo","pay_method_memo","pay_date"};
		} else if("opi_dues_mst_dtl_excel".equals(tableName)){ return new String[]{"incs_toid","incs_moid","pay_remark","pay_plusminus","update_flag","create_id","create_dt","create_ip","update_id","update_dt","update_ip","dues_change_code","pay_del_yn","pay_date","input_dttm","payment_idx","member_id","dues_yymm","member_class_type","dues_seq","gendues_amt","aiddues_amt","pay_method_type","pay_memo","pay_method_memo"};
		} else if("opi_dues_mst_excel".equals(tableName)){ return new String[]{"incs_moid","req_std_yymm","req_use_yn","req_dues_remark","req_adjust_amt","incs_toid","create_id","create_dt","create_ip","update_id","update_dt","update_ip","req_aiddues_amt","req_gendues_amt","input_dttm","member_id","dues_yymm","member_class_type","gendues_amt","aiddues_amt","pay_ok_yn","pay_date","pay_cnt","pay_method_type","pay_del_yn","pay_remark"};
		} else if("opi_dues_req".equals(tableName)){ return new String[]{"create_Id","incs_toid","incs_moid","pay_amt","pay_date","create_dt","create_Ip","update_Id","update_dt","update_Ip","adapt_yn","adapt_date","pay_stat","pay_method_type","member_id","req_std_yymm","req_std_yymm_seq","req_term_type","req_use_yn","req_fr_yymm","req_to_yymm","req_mm_cnt","req_gendues_amt","req_aiddues_amt","req_remark","req_type"};
		} else if("opi_dues_req_excel".equals(tableName)){ return new String[]{"create_Id","incs_toid","incs_moid","pay_amt","pay_date","create_dt","create_Ip","update_Id","update_dt","update_Ip","adapt_yn","adapt_date","pay_stat","pay_method_type","req_type","input_dttm","member_id","req_std_yymm","req_std_yymm_seq","req_term_type","req_use_yn","req_fr_yymm","req_remark","req_aiddues_amt","req_gendues_amt","req_mm_cnt","req_to_yymm"};
		} else if("opi_insurance_fee".equals(tableName)){ return new String[]{"back_acct_name","back_req_date","back_amt","back_date","back_reason_cd","back_memo","back_reg_id","remark","update_id","update_date","update_ip","back_acct_num","back_bank","back_bank_name","insurance_fee_idx","member_id","game_year","incs_moid","pay_date","insurance_fee","apply_state","create_id","create_date","create_ip","back_stat_cd"};
		} else if("opi_join_fee".equals(tableName)){ return new String[]{"create_id","create_date","update_id","update_date","create_ip","update_ip","join_memo","incs_moid","join_ok_date","join_idx","member_id","join_type","join_req_date","join_req_amt","join_ok_yn"};
		} else if("opi_mbrship_amt".equals(tableName)){ return new String[]{"back_acct_num","back_acct_name","back_req_date","back_amt","back_date","back_reason_cd","back_memo","back_reg_id","remark","update_id","update_dt","update_ip","back_bank","back_bank_name","back_stat_cd","mbrship_fee_idx","member_id","tour_id","game_year","game_season","incs_moid","pay_date","mbrship_amt","apply_state","create_id","create_date","create_ip"};
		} else if("opi_mbrship_krtour".equals(tableName)){ return new String[]{"back_reg_id","back_req_date","back_bank","back_bank_name","back_acct_name","back_acct_num","back_mbrship_remark","use_yn","create_id","create_dttm","create_ip","update_id","update_dttm","update_ip","back_bank_info","back_mbrship_date","back_mbrship_fee","mbrship_fee_idx","game_year","member_id","ktour_mbrship_type","req_dttm","req_remark","apply_state","pay_ok_yn","back_mbrship_stat","pay_remark","pay_incs_moid","pay_mthd_type","pay_dttm","pay_mbrship_fee"};
		} else if("penalty_req".equals(tableName)){ return new String[]{"create_ip","update_id","update_date","update_ip","sub_moid","tx_moid","delete_yn","create_date","create_id","penalty_amt","penalty_code","penalty_req_seq","member_id","member_class_type","pay_req_date","pay_in_date","pay_ok_yn"};
		} else if("pension_bereaved".equals(tableName)){ return new String[]{"update_ip","update_id","update_dt","create_ip","create_id","create_dt","bereaved_count","bereaved_date","member_id","bereaved_name","bereaved_profile_dob","bereaved_hp_no","pension_target_type"};
		} else if("pension_dtl".equals(tableName)){ return new String[]{"pension_count","pension_extinction_count","pension_receipt_count","create_dt","create_id","create_ip","update_dt","update_id","update_ip","deductions_reason","deductions","pension_comment","member_id","pension_year","pension_date","pension_subject_type","pension_amt","pension_state","pension_state_reason","pension_accnt","pension_depositor"};
		} else if("pension_mst".equals(tableName)){ return new String[]{"pension_end_flag","pension_end_reason","bereaved_flag","pension_comment","create_dt","create_id","create_ip","update_dt","update_id","update_ip","pension_real_start_month","pension_real_start_date","member_id","kr_name","ageInFull_open_date","first_admission_date","adm20Year_open_date","dues_expire_date","retard_flag","extinction_flag","common_year","pension_start_date"};
		} else if("player_game_cnt".equals(tableName)){ return new String[]{"game_season","game_tot_cnt","game_attend_cnt","member_id","tour_id","game_year"};
		} else if("player_member_certi".equals(tableName)){ return new String[]{"remark","createId","createDt","createIp","updateId","updateDt","updateIp","recvDesk","output_type","certi_use_type","issue_year","mgmt_no","issue_no","member_id","certi_type","issue_IP","issue_date"};
		} else if("player_member_history".equals(tableName)){ return new String[]{"updateIp","updateDt","updateId","createIp","createDt","createId","remark","feature","history_type","history_date","member_id"};
		} else if("player_member_relation".equals(tableName)){ return new String[]{"createId","createDt","createIp","updateId","updateDt","updateIp","remark","etc","relation_2_feature","member_1_id","member_2_id","member_2_name","relation_1_type","relation_2_type","relation_1_feature"};
		} else if("player_member".equals(tableName)){ return new String[]{"penalty_end_date","penalty_start_date","dues_req_yymm_seq","dues_req_yymm","remark","dues_ok_cnt","dues_last_yymm","dues_sangjo_free_to_yymm","dues_sangjo_free_fr_yymm","dues_general_free_to_yymm","dues_general_free_fr_yymm","dues_yn","dues_term_type","penalty_dues_stat","classPro_no","updateIp","updateDt","updateId","createIp","createDt","createId","aagree","is_jr_instructorN","mentalCoach_no","is_mentalCoach","is_jr_instructor","classPro_year","profile_dtl","player_contract_en","member_id","depart_date","leave_date","reinstate_date","expel_date","first_admission_date","pro_admission_date","tourPro_admission_date","pro_no","tourPro_no","Is_classPro","member_dtl_state","member_state","ageInFull_open_date","adm20Year_open_date","player_contract","Is_mutualAid_target","mip_cmpl_caution","Is_tourPro_mip_cmpl","pro_mip_cmpl_type","Is_card_use","debut","rookie_year","usTeaching_close_date","usTeaching_open_date","Is_usTeaching","Is_game_record_exp","pension_open_date"};
		} else if("player_member_0507".equals(tableName)){ return new String[]{"remark","dues_req_yymm","dues_req_yymm_seq","penalty_start_date","penalty_end_date","dues_ok_cnt","dues_last_yymm","dues_sangjo_free_to_yymm","dues_sangjo_free_fr_yymm","dues_general_free_to_yymm","dues_general_free_fr_yymm","dues_yn","dues_term_type","penalty_dues_stat","classPro_no","updateIp","updateDt","updateId","createIp","createDt","createId","aagree","is_jr_instructorN","mentalCoach_no","is_mentalCoach","is_jr_instructor","classPro_year","profile_dtl","player_contract_en","player_contract","leave_date","reinstate_date","expel_date","first_admission_date","pro_admission_date","tourPro_admission_date","pro_no","tourPro_no","Is_classPro","member_dtl_state","member_state","member_id","depart_date","ageInFull_open_date","adm20Year_open_date","Is_mutualAid_target","mip_cmpl_caution","Is_tourPro_mip_cmpl","pro_mip_cmpl_type","Is_card_use","debut","rookie_year","usTeaching_close_date","usTeaching_open_date","Is_usTeaching","Is_game_record_exp","pension_open_date"};
		} else if("player_member_goods".equals(tableName)){ return new String[]{"mg_wedge_code_2","mg_wedge_se2","mg_wedge_scode_2","mg_wedge_loft2","mg_wedge_name3","mg_wedge_code_3","mg_wedge_se3","mg_wedge_scode_3","mg_wedge_loft3","mg_wedge_name2","mg_wedge_loft1","mg_wedge_scode_1","mg_iron_se9","mg_iron_scode_9","mg_hybrid_name","mg_hybrid_code","mg_hybrie_se","mg_hybrie_scode","mg_wedge_name1","mg_wedge_code_1","mg_wedge_se1","mg_wedge_name4","mg_wedge_code_4","mg_wood_name3","mg_wood_code_3","mg_wood_se3","mg_wood_scode_3","mg_create_id","mg_creagte_ip","mg_create_date","mg_update_id","mg_update_ip","mg_wood_scode_2","mg_wood_se2","mg_wedge_se4","mg_wedge_scode_4","mg_wedge_loft4","mg_wood_name1","mg_wood_code_1","mg_wood_se1","mg_wood_scode_1","mg_wood_name2","mg_wood_code_2","mg_update_date","member_id","mg_iron_se1","mg_iron_scode_1","mg_iron_name2","mg_iron_code_2","mg_iron_se2","mg_iron_scode_2","mg_iron_name3","mg_iron_code_3","mg_iron_se3","mg_iron_code_1","mg_iron_name1","mg_driver_name","mg_driver_code","mg_driver_se","mg_driver_scode","mg_drive_loft","mg_ball_name","mg_ball_code","mg_ball_se","mg_ball_scode","mg_iron_scode_3","mg_iron_name4","mg_iron_code_7","mg_iron_se7","mg_iron_scode_7","mg_iron_name8","mg_iron_code_8","mg_iron_se8","mg_iron_scode_8","mg_iron_name9","mg_iron_code_9","mg_iron_name7","mg_iron_scode_6","mg_iron_se6","mg_iron_code_4","mg_iron_se4","mg_iron_scode_4","mg_iron_name5","mg_iron_code_5","mg_iron_se5","mg_iron_scode_5","mg_iron_name6","mg_iron_code_6"};
		} else if("player_nonofficial_record_year".equals(tableName)){ return new String[]{"ry_round_cnt","ry_hole_cnt","create_id","create_dt","update_id","update_dt","ry_point3","ry_point2","game_year","member_id","tour_id","ry_type","ry_rank","ry_point1"};
		} else if("player_nonofficial_record_year_before".equals(tableName)){ return new String[]{"ry_round_cnt","ry_hole_cnt","create_id","create_dt","update_id","update_dt","ry_point3","ry_point2","game_year","member_id","tour_id","ry_type","ry_rank","ry_point1"};
		} else if("player_punishment".equals(tableName)){ return new String[]{"delay_std_type","staff_name","game_round_id","game_id","game_tour_id","game_year","delay_year_cnt","cancel_yn","remark","createId","createDt","createIp","updateId","updateDt","updateIp","tx_moid","paidup_date","paidup_pay_type","punishment_id","member_id","punishment_type","punishment_open_date","punishment_close_date","punishment_organ","punishment_date","punishment_contents","punishment_tour_type","paidup_amt","paidup_state_type","paidup_cnt","penalty_amt","change_reason","Is_punishment_apc"};
		} else if("player_record_year".equals(tableName)){ return new String[]{"ry_round_cnt","ry_hole_cnt","create_id","create_dt","update_id","update_dt","ry_point3","ry_point2","game_year","member_id","tour_id","ry_type","ry_rank","ry_point1"};
		} else if("player_record_year_2008back".equals(tableName)){ return new String[]{"ndate","nid","stat","game_hole","game_round","user_name","member_id","game_name","game_id","game_year"};
		} else if("player_record_year_before".equals(tableName)){ return new String[]{"ry_round_cnt","ry_hole_cnt","create_id","create_dt","update_id","update_dt","ry_point3","ry_point2","game_year","member_id","tour_id","ry_type","ry_rank","ry_point1"};
		} else if("player_user_login_info".equals(tableName)){ return new String[]{"browser","device","web_login_ip","web_login_datetime","web_login_id","member_id","info_seq"};
		} else if("player_user_webservice_info".equals(tableName)){ return new String[]{"create_id","create_dt","web_service_state","web_login_id","member_id"};
		} else if("player_user_withdrawal".equals(tableName)){ return new String[]{"createIp","updateId","updateDt","updateIp","payment_history","game_history","withdrawalId","withdrawalDt","withdrawalIp","createDt","createId","join_route","member_id","class_type","caution","Is_web_service_use","web_service_state","web_login_id","ci","di","remark"};
		} else if("player_year_round".equals(tableName)){ return new String[]{"round_cnt","member_id","tour_id","game_year"};
		} else if("player_year_round_before".equals(tableName)){ return new String[]{"game_year","tour_id","member_id","round_cnt"};
		} else if("player_year_round_top10".equals(tableName)){ return new String[]{"round_cnt","member_id","tour_id","game_year"};
		} else if("player_year_round_top10_before".equals(tableName)){ return new String[]{"round_cnt","member_id","tour_id","game_year"};
		} else if("punishment_member_year".equals(tableName)){ return new String[]{"createIp","createDt","createId","req_to_yymm","req_fr_yymm","req_amt","req_mm_cnt","year","member_id"};
		} else if("search_keyword".equals(tableName)){ return new String[]{"create_ip","create_dt","create_id","browser","device","search_keyword","search_id"};
		} else if("sms_info".equals(tableName)){ return new String[]{"sl_send_code","sl_send_div","sl_recv_code","sl_nid","sl_div","sl_date","sl_smstext","sl_recvnum","sl_sendnum","sms_id"};
		} else if("stats_main_rank".equals(tableName)){ return new String[]{"req_dttm","status_div","tour_id","point","user_name_en","user_name","member_id","rank_seq"};
		} else if("stats_score_rank".equals(tableName)){ return new String[]{"UPD_DATE","CNT","STATS_DIV","E_MM_NAME_DISPLAY","MM_NAME_DISPLAY","MM_CODE","GM_CODE"};
		} else if("sys_cmm_cd".equals(tableName)){ return new String[]{"code_ext_val_1","code_ext_val_2","create_id","create_dttm","create_ip","update_id","update_dttm","update_ip","old_sort_seq","old_code_text","group_id","code_value","code_text","code_text_en","code_memo","sort_seq","old_group_id","old_code_value"};
		} else if("sys_code_nation".equals(tableName)){ return new String[]{"update_dt","update_id","create_dt","create_id","nm_ename","nm_sname","nm_fname","nm_code"};
		} else if("sys_code_old".equals(tableName)){ return new String[]{"reg_date","reg_user","mod_date","mod_user","new_code_group","uid","order_no","use_yn","code_value","code_name","code_id","code_group"};
		} else if("sys_config".equals(tableName)){ return new String[]{"create_ip","create_dttm","update_id","update_ip","update_dttm","create_id","config_remark","config_val_4","config_val_3","config_val_2","config_val_1","config_nm","config_cd"};
		} else if("sys_menu".equals(tableName)){ return new String[]{"menu_use_yn","menu_help_html","menu_target_type","menu_default_cd","menu_link_url","sub_tab_yn","menu_sort5","menu_on_img","menu_off_img","createId","createDt","createIp","updateId","updateDt","updateIp","old_menu_cd","old_menu_upcd","menu_sort4","menu_sort3","menu_sort2","menu_group_cd","menu_cd","menu_upcd","menu_nm","menu_lvl","menu_sort","menu_cd1","menu_cd2","menu_cd3","menu_cd4","menu_sort1","menu_nm5","menu_nm4","menu_nm3","menu_nm2","menu_nm1","menu_cd5"};
		} else if("player_user_mst".equals(tableName)){ return new String[]{"sns_naverBand","sns_naverBlog","sns_youtube","homepage_url","bank_code","accnt","depositor","caution","Is_web_service_use","web_service_state","sns_instagram","sns_facebook","residence_fax_no","workplace_name","workplace_zipcode","workplace_zipNo_1","workplace_zipNo_2","workplace_address","workplace_dtl_address","workplace_tel_no","workplace_fax_no","highEdu","web_login_id","web_login_pw","web_login_pw_encrypt_type","join_route","join_route_remark","withdrawal_req_flag","search_flag","createId","createDt","createIp","updateId","updateDt","remark","di","ci","web_login_IP","web_login_datetime","web_logout_datetime","web_login_pw_change_datetime","web_login_pwQ","web_login_pwA","is_marketing_agree","is_profile_dob_exp","is_inactive_account","updateIp","member_id","profile_photo_3","profile_photo_4","profile_dob","profile_gender","profile_home","profile_blood","profile_height","profile_weight","Is_profile_marriage","profile_photo_2","profile_photo_1","rename_old_name","class_type","user_name","kr_name","en_name","en_first_name","en_middle_name","en_last_name","ch_name","rename_date","profile_religion","profile_license","profile_hobby","Is_email_recv_agree","email","Is_mail_recv_agree","mail_home_type","residence_zipcode","residence_zipNo_1","residence_zipNo_2","residence_address","residence_dtl_address","residence_tel_no","add_relation","add_hp_no","profile_specialty","nation_code","residence_type","military_open_date","military_close_date","Is_SMS_recv_1_agree","Is_SMS_recv_2_agree","Is_SMS_recv_3_agree","pri_hp_no","pri_relation"};
		} else if("player_user_mst_0507".equals(tableName)){ return new String[]{"bank_code","homepage_url","sns_youtube","sns_naverBlog","accnt","depositor","caution","Is_web_service_use","web_service_state","web_login_id","sns_naverBand","sns_instagram","sns_facebook","workplace_name","workplace_zipcode","workplace_zipNo_1","workplace_zipNo_2","workplace_address","workplace_dtl_address","workplace_tel_no","workplace_fax_no","highEdu","residence_fax_no","web_login_pw","web_login_pw_encrypt_type","join_route","join_route_remark","withdrawal_req_flag","search_flag","createId","createDt","createIp","updateId","updateDt","remark","di","ci","web_login_IP","web_login_datetime","web_logout_datetime","web_login_pw_change_datetime","web_login_pwQ","web_login_pwA","is_marketing_agree","is_profile_dob_exp","is_inactive_account","updateIp","member_id","profile_photo_3","profile_photo_4","profile_dob","profile_gender","profile_home","profile_blood","profile_height","profile_weight","Is_profile_marriage","profile_photo_2","profile_photo_1","rename_old_name","class_type","user_name","kr_name","en_name","en_first_name","en_middle_name","en_last_name","ch_name","rename_date","profile_religion","profile_license","profile_hobby","Is_email_recv_agree","email","Is_mail_recv_agree","mail_home_type","residence_zipcode","residence_zipNo_1","residence_zipNo_2","residence_address","residence_dtl_address","residence_tel_no","add_relation","add_hp_no","profile_specialty","nation_code","residence_type","military_open_date","military_close_date","Is_SMS_recv_1_agree","Is_SMS_recv_2_agree","Is_SMS_recv_3_agree","pri_hp_no","pri_relation"};
		} else if("player_user_mst_hist".equals(tableName)){ return new String[]{"updateDt","updateId","createIp","createDt","createId","search_flag","updateIp","member_state","member_dtl_state","Is_classPro","tourPro_no","pro_no","tourPro_admission_date","pro_admission_date","first_admission_date","join_route_remark","join_route","remark","web_service_state","web_login_id","web_login_pw","web_login_pw_encrypt_type","web_login_IP","web_login_datetime","web_logout_datetime","web_login_pw_change_datetime","web_login_pwQ","web_login_pwA","is_marketing_agree","is_inactive_account","ci","di","Is_web_service_use","expel_date","reinstate_date","dues_term_type","dues_yn","dues_general_free_fr_yymm","dues_general_free_to_yymm","dues_sangjo_free_fr_yymm","dues_sangjo_free_to_yymm","dues_last_yymm","dues_ok_cnt","pm_remark","dues_req_yymm","dues_req_yymm_seq","penalty_start_date","penalty_end_date","penalty_dues_stat","profile_dtl","player_contract","Is_mutualAid_target","leave_date","depart_date","ageInFull_open_date","adm20Year_open_date","pension_open_date","Is_game_record_exp","Is_usTeaching","usTeaching_open_date","usTeaching_close_date","rookie_year","Is_card_use","pro_mip_cmpl_type","Is_tourPro_mip_cmpl","mip_cmpl_caution","classPro_no","caution","hist_dttm","profile_home","profile_blood","profile_height","profile_weight","Is_profile_marriage","profile_religion","profile_license","profile_hobby","profile_specialty","nation_code","residence_type","military_open_date","military_close_date","Is_SMS_recv_1_agree","profile_gender","profile_dob","profile_photo_4","member_id","class_type","user_name","kr_name","en_name","en_first_name","en_middle_name","en_last_name","ch_name","rename_date","rename_old_name","profile_photo_1","profile_photo_2","profile_photo_3","Is_SMS_recv_2_agree","Is_SMS_recv_3_agree","pri_hp_no","workplace_zipNo_2","workplace_address","workplace_dtl_address","workplace_tel_no","workplace_fax_no","highEdu","sns_facebook","sns_instagram","sns_naverBand","sns_naverBlog","sns_youtube","homepage_url","bank_code","accnt","depositor","workplace_zipNo_1","workplace_zipcode","pri_relation","add_hp_no","add_relation","Is_email_recv_agree","email","Is_mail_recv_agree","mail_home_type","residence_zipcode","residence_zipNo_1","residence_zipNo_2","residence_address","residence_dtl_address","residence_tel_no","residence_fax_no","workplace_name"};
		} else if("sys_migration_tbl".equals(tableName)){ return new String[]{"migMemo","migSqlCreate","migSqlTxt","migSqlWhere","trgtTblCmmt","trgtTblNm","migSqn"};
		} else if("sys_naver_log".equals(tableName)){ return new String[]{"create_date","create_id","result","round_no","game_id","index"};
		} else if("sys_nextval".equals(tableName)){ return new String[]{"name","currval"};
		} else if("sys_proc_log".equals(tableName)){ return new String[]{"proc_err_msg","proc_end_dttm","proc_start_dttm","proc_user_id","proc_stat_cd","proc_id_param","proc_idx"};
		} else if("sys_record_cd".equals(tableName)){ return new String[]{"hole_diff_yn","sort_seq","record_memo","record_name","record_type","record_group"};
		} else if("sys_schedule_log".equals(tableName)){ return new String[]{"schedule_mode","schedule_dttm"};
		} else if("sys_service_request".equals(tableName)){ return new String[]{"ans_dttm","ans_cnts","reg_mbrId","reg_dttm","atch_file_no","svc_req_stat_cd","svc_req_cnts","screen_url","screen_nm","svc_req_tnm","svc_req_sqn"};
		} else if("tb_error_collect".equals(tableName)){ return new String[]{"cdate","device_id","ip_addr","error_msg","class_name","error_seq"};
		} else if("tb_year_title".equals(tableName)){ return new String[]{"game_id_reRanking","TOP10_NAME","RECOVERY_RATE","AVG_PUTT_NAME","FW_NAME","DRIVE_NAME","GIR_NAME","AVG_NAME","SN","YEAR_TITLE","GOLD_NAME","MONEY_NAME","ROOKIE_NAME"};
		} else if("temp_opi_dues_mst".equals(tableName)){ return new String[]{"req_use_yn","dues_ok_cnt","req_dues_remark","req_aiddues_amt","req_gendues_amt","aiddues_amt","gendues_amt","member_class_type","dues_yymm","member_id"};
		} else if("temp_opi_dues_pay".equals(tableName)){ return new String[]{"next_dues_yymm","pay_cnt","pay_aiddues_amt","pay_gendues_amt","member_id","std_pay_date"};
		} else if("temp_opi_dues_req".equals(tableName)){ return new String[]{"req_use_yn","req_cnt","last_req_yymm","req_aiddues_amt","req_gendues_amt","member_id","std_req_yymm"};
		} else if("temp_player_picture".equals(tableName)){ return new String[]{"name"};
		} else if("t_h_media_player".equals(tableName)){ return new String[]{"KPGA_FLG","YEAR_DATE","MP_NAME","MP_MCODE","MP_PARTSEQ","MP_PART","MP_SEQ"};
		} else if("t_h_srixon".equals(tableName)){ return new String[]{"update_ip","update_dt","update_id","create_ip","create_dt","create_id","use_yn","join_yn","member_id"};
		} else if("union_game".equals(tableName)){ return new String[]{"game_id","union_game_seq"};
		} else if("union_game_dtl".equals(tableName)){ return new String[]{"update_id","update_ip","update_date","create_id","create_ip","create_date","union_type","game_subgroup_code","region_group_course_id","game_id","union_key"};
		} else if("union_game_mst".equals(tableName)){ return new String[]{"create_date","create_ip","create_id","update_date","update_ip","update_id","union_region_code","union_name","union_end_date","union_key","use_yn","union_player_cnt","tour_id","union_year","union_start_date"};
		} else if("union_mst".equals(tableName)){ return new String[]{"seq","union_yn"};
		} else if("union_qlf_mst".equals(tableName)){ return new String[]{"Is_kr_nationality","Is_designation","remark","createId","createDt","createIp","updateId","updateDt","updateIp","check_ageAllow","ageLimit","union_key","Is_tourPro","Is_otherTourPro1","Is_otherTourPro2","Is_ama","ama_kor_yn","ama_frgn_yn","ageAllow","Is_pro"};
		} else if("view_game_player_round".equals(tableName)){ return new String[]{"round_cnt","member_id","tour_id","apc_year"};
		} else if("view_game_year_round".equals(tableName)) { return new String[]{"apc_year", "tour_id", "year_round_cnt"}; }
		return new String[]{};
	}


	public void saveSql(List<Map> list, String tableName, int i) throws Exception {
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		String filePath = "F://dbbak/" + date.format(today);
		File dir = new File(filePath);
		if(!dir.exists()) dir.mkdirs();

		File file = new File(filePath + "/" +tableName + i + ".txt");

		BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

		String insertStr = "insert into " + tableName +" (";
		String[] headerList = getHeaderStringArr(tableName);

		for(String header : headerList){
			insertStr += header + ", ";
		}
		insertStr = insertStr.substring(0, insertStr.length() - 2) + ") VALUES ";

		writer.write(insertStr);
		writer.newLine();
		int cnt = 0;
		for(Map map : list){
			String insertLine = "( ";
			for(String header : headerList){
				insertLine += getMapData(map.get(header));
			}
			insertLine = insertLine.substring(0, insertLine.length()-2) + ") " + (cnt == list.size() -1 ? ";" : ",") ;

			writer.write(insertLine);
			writer.newLine();
			cnt++;
		}
		writer.flush();
		writer.close();
	}

	private String getMapData(Object obj){
		String str = "";

		if(obj == null ){
			str += "null";
		} else if(obj instanceof String){
			str += "'" + obj + "'";
		} else {
			str += obj;
		}
		return str +", ";
	}
}






