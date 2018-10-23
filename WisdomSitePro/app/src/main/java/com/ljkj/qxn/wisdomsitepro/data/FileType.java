package com.ljkj.qxn.wisdomsitepro.data;

public interface FileType {

    /**
     * 安全检查
     */
    interface SafeCheck {
        /** 安全检查内容图片 */
        String TYPE_SAFE_CHECK_DANGER = "ctms_sec_check_danger_img";
        /** 安全检查附件 */
        String TYPE_SAFE_CHECK_ATTACHMENT = "ctms_sec_check_danger_file";

        /** 安全整改内容图片 */
        String TYPE_SAFE_CHECK_RECTIFY = "ctms_sec_check_reform_img";
        /** 安全整改报告书 */
        String TYPE_SAFE_CHECK_REPORT = "ctms_sec_check_reform_file";
        /** 安全整改预案文件 */
        String TYPE_SAFE_CHECK_PLAN = "ctms_sec_check_reform_plan";
        /** 安全整改措施文件 */
        String TYPE_SAFE_CHECK_MEASURE = "ctms_sec_check_reform_fuc";
        /** 安全整改资金文件 */
        String TYPE_SAFE_CHECK_MONEY = "ctms_sec_check_reform_money";
    }

    interface SafeDefend {
        /** 安全防护楼层图片 */
        String TYPE_SAFE_DEFEND = "ctms_sec_def_img";
    }


}
