package com.zwj.supertools.constant;

/**
 * Created by zwj on 2017/11/1.
 */

public interface UrlConstant {
    String URL_BASE = "http://116.196.72.163/api";
    //    String URL_BASE = "http://192.168.1.101:8088/api";    // 家里
//    String URL_BASE = "http://192.168.0.103:8088/api";    // 宿舍


    String URL_LOGIN = URL_BASE + "/user/login";

    String URL_GET_XS_CONTENT_BY_ID = URL_BASE + "/xs/getContentById";


    // 名称
    String URL_GENERATE_PERSON_NAME = URL_BASE + "/common/generateName/generatePersonName";



    // ------------------------ 基金 begin ------------------------
    String URL_GET_CUR_FUND_INFO = URL_BASE + "/fund/getCurFundInfo";
    // ------------------------ 基金 end ------------------------


    // ------------------------ fperson begin ------------------------
    String URL_GET_4_BIRTHDAY = URL_BASE + "/fperson/get4Birthday";
    // ------------------------ fperson end ------------------------
}
