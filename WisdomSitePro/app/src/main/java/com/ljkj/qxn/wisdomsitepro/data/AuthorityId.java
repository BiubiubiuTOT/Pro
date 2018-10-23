package com.ljkj.qxn.wisdomsitepro.data;

import android.support.annotation.StringDef;

public interface AuthorityId {

    /** 项目概况 */
    String PROJECT_MANAGE = "7729981178133897216";

    /** 安全管理 */
    String SAFE_MANAGE = "7729982562732367872";

    /** 质量管理 */
    String QUALITY_MANAGE = "7729986274552995840";

    /** 劳务管理 */
    String LABOR_MANAGER = "7729986378160693248";

    /** 环境管理 */
    String ENVIRONMENT_MANAGE = "7729986530537832448";

    /** 视频监控 */
    String VIDEO_MANAGE = "7729986704953769984";

    /** 特种设备 */
    String EQUIPMENT_MANAGE = "7729986798100873216";

    /** 资料管理 */
    String DATA_MANAGE = "7729987015961411584";

    /** 安全管理 */
    interface SafeManage {
        /** 安全检查 */
        String SAFE_CHECK = "7729983191720853504";
        /** 安全巡检 */
        String SAFE_INSPECTION = "7729984595910918144";
        /** 安全隐患台账 */
        String SAFE_DANGER = "7729985122386075648";
        /** 安全体系 */
        String SAFE_SYSTEM = "7729984121937788928";
        /** 安全防护 */
        String SAFE_PROTECT = "7729985000466046976";
        /** 安全教育 */
        String SAFE_EDU = "7729984491099455488";
        /** 安全统计 */
        String SAFE_STATISTICS = "7729984820532674560";
        /** 安全日志 */
        String SAFE_LOG = "7729985287452909568";
    }

    /** 质量管理 */
    interface QualityManage {
        /** 质量检查 */
        String QUALITY_CHECK = "7729988183030693888";
        /** 质量巡检 */
        String QUALITY_INSPECTION = "7729989455420874752";
        /** 质量隐患台账 */
        String QUALITY_DANGER = "7731026918014910464";
        /** 质量日志 */
        String QUALITY_LOG = "7731026833864589312";
        /** 质量体系 */
        String QUALITY_SYSTEM = "7729988603767447552";
        /** 质量统计 */
        String QUALITY_STATISTICS = "7731026714100432896";
        /** 砼质量信息 */
        String QUALITY_CONCRETE = "7729989101006381056";
    }

    /** 劳务管理 */
    interface LaborManage {
        /** 人员统计 */
        String LABOR_STATISTICS = "7731010272294535168";
        /** 人员档案 */
        String LABOR_ARCHIVE = "7731010548640448512";
        /** 考勤管理 */
        String LABOR_ATTENDANCE = "7731010645071691776";
        /** 工资发放 */
        String LABOR_WAGES = "7731011318714662912";
    }

    /** 环境管理 */
    interface EnvironmentManage {
        /** 环境监测 */
        String ENVIRONMENT_MONITOR = "7731011524365582336";
    }

    /** 视频监控 */
    interface VideoManage {
        /** 视频监测 */
        String VIDEO_MONITOR = "7731011684931928064";
        /** 移动执法 */
        String MOBILE_LAW = "7731011741307568128";
    }

    /** 特种设备 */
    interface EquipmentManage {
        /** 塔吊 */
        String TOWER_CRANE = "7731011877672779776";
    }

    /** 资料管理 */
    interface DataManage {
        /** 施工日志 */
        String CONSTRUCT_LOG = "7731012467517751296";
    }

    @StringDef({PROJECT_MANAGE, SAFE_MANAGE, QUALITY_MANAGE, LABOR_MANAGER, ENVIRONMENT_MANAGE, VIDEO_MANAGE, EQUIPMENT_MANAGE,
            DATA_MANAGE, SafeManage.SAFE_CHECK, SafeManage.SAFE_INSPECTION, SafeManage.SAFE_DANGER, SafeManage.SAFE_SYSTEM, SafeManage.SAFE_PROTECT, SafeManage.SAFE_EDU,
            SafeManage.SAFE_STATISTICS, SafeManage.SAFE_LOG, QualityManage.QUALITY_CHECK, QualityManage.QUALITY_INSPECTION, QualityManage.QUALITY_DANGER,
            QualityManage.QUALITY_LOG, QualityManage.QUALITY_SYSTEM, QualityManage.QUALITY_STATISTICS, QualityManage.QUALITY_CONCRETE,
            LaborManage.LABOR_STATISTICS, LaborManage.LABOR_ARCHIVE, LaborManage.LABOR_ATTENDANCE, LaborManage.LABOR_WAGES,
            EnvironmentManage.ENVIRONMENT_MONITOR, VideoManage.VIDEO_MONITOR, VideoManage.MOBILE_LAW, EquipmentManage.TOWER_CRANE, DataManage.CONSTRUCT_LOG})
    @interface Id {
    }

}
