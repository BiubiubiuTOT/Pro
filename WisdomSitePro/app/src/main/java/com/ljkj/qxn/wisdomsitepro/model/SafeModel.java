package com.ljkj.qxn.wisdomsitepro.model;

import android.text.TextUtils;

import com.ljkj.qxn.wisdomsitepro.Utils.MapUtil;
import com.ljkj.qxn.wisdomsitepro.Utils.OkGoHelper;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.data.entity.SecurityEduTechPerson;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.model.BaseModel;

/**
 * 类描述：安全模块
 * 创建人：liufei
 * 创建时间：2018/3/10 11:29
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SafeModel extends BaseModel {

    private static SafeModel model;

    /**
     * 安全管理人员体系
     */
    private static final String SAFE_SYSTEM_PEOPLE_URL = "/proj/manager/queryManagerAndDeptByProjId";

    /**
     * 安全体系PDF
     */
    private static final String SAFE_SYSTEM_PDF_URL = "/securityController.do?aqtx";

    /**
     * 安全巡检，质量巡检
     */
    private static final String PATROL_LIST_URL = "/qualityController.do?inspectionList";

    /**
     * 安全巡检
     */
    private static final String SAFE_PATROL_LIST_URL = "/security/securityPatrol/list";

    /**
     * 安全教育
     */
    private static final String SAFE_EDU_LIST_URL = "/security/securityEdu/list";

    /**
     * 安全技术交底列表
     */
    private static final String SAFE_EDU_TECHNOLOGY_LIST = "/security/securityTechnology/list";

    /**
     * 安全监督方案
     */
    private static final String SAFETY_SUP_WORKPLAN_URL = "/securityController.do?getAqjdfa";


    /**
     * 安全监督申报
     */
    private static final String SAFETY_SUPERVISION_DECLARATION_URL = "/securityController.do?aqjdsb";

    /**
     * 安全防护
     */
    private static final String SAFE_GUARD_LIST_URL = "/securityController.do?aqfh";

    /**
     * 安全防护楼栋详情
     */
    private static final String SAFE_GUARD_BUILD_LIST_URL = "/securityController.do?aqfhLdDetails";

    /**
     * 安全防护添加楼栋
     */
    private static final String SAFE_GUARD_ADD_BUILDING = "/securityController.do?saveLou";

    /**
     * 安全防护楼层详情
     */
    private static final String SAFE_GUARD_FLOOR_DETAIL = "/securityController.do?cengDetails";


    /**
     * 安全教育详情
     */
    private static final String SAFE_EDUCATION_DETAIL_URL = "/security/securityEdu/selectById";

    /**
     * 安全技术交底详情
     */
    private static final String SAFE_TECHNOLOGY_DETAIL = "/security/securityTechnology/selectById";

    /**
     * 新增安全教育
     */
    private static final String ADD_SAFE_EDUCATION_URL = "/securityController.do?saveEdu";

    /**
     * 安全防护编辑楼层
     */
    private static final String SAFE_GUARD_FLOOR_EDIT = "/securityController.do?saveFh";


    /**
     * 安全教育新增Url
     */
    private static final String SAVE_EDU_URL = "/securityController.do?saveEdu";

    /**
     * 新增安全教育交底
     */
    private static final String ADD_SAFE_EDU = "/security/securityEdu/insertEdu";


    /**
     * 新增安全技术交底
     */
    private static final String ADD_SAFE_TECHNOLOGY = "/security/securityTechnology/insertTec";


    /**
     * 培训人列表
     */
    private static final String LIST_LABORS = "/securityController.do?geLabors";


    /**
     * 安全防护添加楼层
     */
    private static final String SAFE_GUARD_ADD_FLOOR = "/securityController.do?saveCeng";

    /**
     * 安全防护删除楼层
     */
    private static final String SAFE_GUARD_DELETE_FLOOR = "/securityController.do?delCeng";

    /**
     * 安全教育上报到政府端
     */
    private static final String SAVE_EDU_DECLARE = "/securityController.do?saveEduDeclare";

    /**
     * 安全隐患台账
     */
    private static final String SAFE_HIDDEN_ACCOUNT_URL = "/security/securityRiskLedger/list";

    /**
     * 质量隐患台账
     */
    private static final String QUALITY_HIDDEN_ACCOUNT_URL = "/quality/qualityRisk/findRisk";

    /**
     * 安全检查
     */
    private static final String SAFE_CHECK_LIST = "/security/securityCheck/list";
    /**
     * 新增安全巡查
     */
    private static final String SAFE_PATROL_ADD = "/security/securityPatrol/insert";

    /**
     * 质量检查
     */
    private static final String QUALITY_CHECK_LIST = "/quality/qualityInspect/list";

    /**
     * 获取安全检查详情
     */
    private static final String SAFE_CHECK_DETAIL = "/security/securityCheck/detail";

    /**
     * 获取安全巡检详情
     */
    private static final String SAFE_PATROL_DETAIL = "/security/securityPatrol/detail";

    /**
     * 获取质量检查详情
     */
    private static final String QUALITY_CHECK_DETAIL = "/quality/qualityInspect/detail";

    /**
     * 安全整改
     */
    private static final String SAFE_CHECK_RECTIFY = "/security/securityCheckReform/reform";

    /**
     * 质量整改
     */
    private static final String QUALITY_CHECK_RECTIFY = "/quality/qualityInspect/reform";

    /**
     * 安全巡检整改
     */
    private static final String SAFE_INSPECT_RECTIFY = "/security/securityPatrolReform/reform";

    /**
     * 获取监理检查详情
     */
    private static final String SUPERVISOR_CHECK_DETAIL = "/supervision/check/queryReformById";
    /**
     * 监理审核
     */
    private static final String SUPERVISOR_VERIFY = "/supervision/check/audit";

    /**
     * 监理检查列表
     */
    private static final String SUPERVISOR_CHECK_LIST = "/supervision/check/list";

    /**
     * 安全检查撤回
     */
    private static final String SAFE_REVOCATION = ""; //FIXME

    /**
     * 质量检查撤回
     */
    private static final String QUALITY_REVOCATION = "/quality/qualityInspect/revocation";


    private SafeModel() {

    }

    public static SafeModel newInstance() {
        if (model == null) {
            model = new SafeModel();
        }
        return model;
    }

    /**
     * 安全管理人员体系  （调用项目swagger）
     *
     * @param proId        项目id
     * @param jsonCallback
     */
    public void getSafeSystemPeople(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + SAFE_SYSTEM_PEOPLE_URL, params, SAFE_SYSTEM_PEOPLE_URL, jsonCallback);
    }

    /**
     * 安全体系 PDF
     *
     * @param proId        项目id
     * @param marFlag
     * @param jsonCallback
     */
    public void getSafeSystemPdf(String proId, int marFlag, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        params.put("marFlag", marFlag);
        OkGoHelper.getInstance().get(HostConfig.getHost() + SAFE_SYSTEM_PDF_URL, params, SAFE_SYSTEM_PDF_URL, jsonCallback);
    }

    /**
     * 安全巡检，质量巡检
     */
    public void getPatrolList(String proId, String type, String whyType, String zgbz, String jcrqTime, String isDealWith
            , int rows, int page, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        params.put("type", type);
        params.put("whyType", whyType);
        params.put("zgbz", zgbz);
        params.put("jcrqTime", jcrqTime);
        params.put("isDealWith", isDealWith);
        params.put("rows", rows);
        params.put("page", page);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + PATROL_LIST_URL, params, PATROL_LIST_URL, jsonCallback);
    }

    /**
     * 安全巡检V2
     */
    public void getSafePatrolListV2(String proId, String accidentReason,
                                    String changeGroup, String startDate,
                                    String endDate, String isDealWith, int rows, int page, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);

        params.put("accidentCause", accidentReason);
        params.put("endDate", endDate);
        params.put("startDate", startDate);
        params.put("teamId", changeGroup);

        params.put("reformStatus", isDealWith);
        params.put("pageSize", rows);
        params.put("pageNum", page);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SAFE_PATROL_LIST_URL,
                MapUtil.removeMapEmptyValue(params), SAFE_PATROL_LIST_URL, jsonCallback);
    }

    /**
     * 安全巡检 消息V2
     */
    public void getSafePatrolMessageListV2(String proId, int rows, int page, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        params.put("status", "2,4");
        params.put("pageSize", rows);
        params.put("pageNum", page);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SAFE_PATROL_LIST_URL, params, SAFE_PATROL_LIST_URL, jsonCallback);
    }

    /**
     * 安全教育
     */
    public void getSafeEduList(String proId, String isReport, String date, int pageSize, int pageNum, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        if (!TextUtils.isEmpty(isReport)) {
            params.put("isReport", isReport);
        }
        params.put("presentationDate", date);
        params.put("pageSize", pageSize);
        params.put("pageNum", pageNum);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SAFE_EDU_LIST_URL, params, SAFE_EDU_LIST_URL, jsonCallback);
    }

    /**
     * 获取安全技术交底列表
     *
     * @param proId        项目id
     * @param isReport     是否上报 1：上报 0：未上报
     * @param date         交底日期
     * @param pageSize     pageSize
     * @param pageNum      pageNum
     * @param jsonCallback jsonCallback
     */
    public void getSafeEdeTechnologyList(String proId, String isReport, String date, int pageSize, int pageNum, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        if (!TextUtils.isEmpty(isReport)) {
            params.put("isReport", isReport);
        }
        params.put("presentationDate", date);
        params.put("pageSize", pageSize);
        params.put("pageNum", pageNum);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SAFE_EDU_TECHNOLOGY_LIST, params, SAFE_EDU_TECHNOLOGY_LIST, jsonCallback);
    }


    /**
     * 安全监督方案
     *
     * @param proId
     * @param jsonCallback
     */
    public void safetySupWorkplan(String proId, JsonCallback jsonCallback) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + SAFETY_SUP_WORKPLAN_URL, params, SAFETY_SUP_WORKPLAN_URL, jsonCallback);
    }

    /**
     * 安全监督申报
     *
     * @param proId
     * @param jsonCallback
     */
    public void safetySupervisionDeclaration(String proId, JsonCallback jsonCallback) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + SAFETY_SUPERVISION_DECLARATION_URL, params, SAFETY_SUPERVISION_DECLARATION_URL, jsonCallback);

    }

    /**
     * 安全防护列表
     *
     * @param proId        项目id
     * @param jsonCallback
     */
    public void getSafeGuardInfo(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + SAFE_GUARD_LIST_URL, params, SAFE_GUARD_LIST_URL, jsonCallback);
    }

    /**
     * 安全防护楼栋详情
     *
     * @param lou          楼栋名
     * @param proId        项目名
     * @param jsonCallback
     */
    public void getSafeGuardBuild(String lou, String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("lou", lou);
        params.put("proId", proId);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + SAFE_GUARD_BUILD_LIST_URL, params, SAFE_GUARD_BUILD_LIST_URL, jsonCallback);
    }

    /**
     * 新增安全防护楼栋
     *
     * @param name         楼盘名称
     * @param lous         楼栋数量
     * @param proId        项目Id
     * @param jsonCallback
     */
    public void addSafeGuardBuild(String name, int lous, String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("lous", lous);
        params.put("proId", proId);
        OkGoHelper.getInstance().post(HostConfig.getHost2() + SAFE_GUARD_ADD_BUILDING, params, SAFE_GUARD_ADD_BUILDING, jsonCallback);
    }

    /**
     * 编辑安全防护楼层详情
     *
     * @param aqfhsm       安全防护说明
     * @param cengId       楼层ID
     * @param proId        项目ID
     * @param jsonCallback
     */
    public void editSafeGuardFloor(String aqfhsm, String cengId, String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("aqfhsm", aqfhsm);
        params.put("cengId", cengId);
        params.put("proId", proId);
        OkGoHelper.getInstance().post(HostConfig.getHost2() + SAFE_GUARD_FLOOR_EDIT, params, SAFE_GUARD_FLOOR_EDIT, jsonCallback);
    }

    /**
     * 安全防护楼层详情
     *
     * @param cengId       楼层Id
     * @param proId        项目Id
     * @param jsonCallback
     */
    public void getSafeGuardFloorDetail(String cengId, String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("cengId", cengId);
        params.put("proId", proId);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + SAFE_GUARD_FLOOR_DETAIL, params, SAFE_GUARD_FLOOR_DETAIL, jsonCallback);
    }

    /**
     * 安全教育详情
     *
     * @param id
     * @param jsonCallback jsonCallback
     */
    public void safeEducationDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + SAFE_EDUCATION_DETAIL_URL, params, SAFE_EDUCATION_DETAIL_URL, jsonCallback);
    }

    /**
     * 安全技术交底详情
     *
     * @param id           id
     * @param jsonCallback jsonCallback
     */
    public void getSafeTechnologyDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + SAFE_TECHNOLOGY_DETAIL, params, SAFE_TECHNOLOGY_DETAIL, jsonCallback);
    }

    /**
     * 新增安全教育
     *
     * @param context      培训内容
     * @param date         培训时间
     * @param isSb         是否上报
     * @param name         培训名称
     * @param personIds    培训人id
     * @param proId        项目id
     * @param pxPerson     培训人
     * @param jsonCallback
     */
    public void addSafeEducation(String context, String date, String isSb, String name, String personIds, String proId, String pxPerson, JsonCallback jsonCallback) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("context", context);
        params.put("date", date);
        params.put("isSb", isSb);
        params.put("name", name);
        params.put("personIds", personIds);
        params.put("proId", proId);
        params.put("pxPerson", pxPerson);
        OkGoHelper.getInstance().get(HostConfig.getHost() + ADD_SAFE_EDUCATION_URL, params, ADD_SAFE_EDUCATION_URL, jsonCallback);
    }


    /**
     * 新增安全教育
     *
     * @param name         培训名称
     * @param context      培训内容
     * @param date         培训时间
     * @param isSb         是否上报
     * @param personIds    受训人员IDS
     * @param proId        项目id
     * @param pxPerson     培训人
     * @param jsonCallback
     */
    public void saveEdu(String name, String context, String date, String isSb, String personIds, String proId, String pxPerson, JsonCallback jsonCallback) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("context", context);
        params.put("date", date);
        params.put("isSb", isSb);
        params.put("personIds", personIds);
        params.put("proId", proId);
        params.put("pxPerson", pxPerson);
        OkGoHelper.getInstance().post(HostConfig.getHost() + SAVE_EDU_URL, params, SAVE_EDU_URL, jsonCallback);

    }

    /**
     * 新增安全教育交底
     *
     * @param presentationName 教育交底名称
     * @param presentationDate 教育交底日期 格式yyyy -MM - dd
     * @param presentationInfo 教育交底内容
     * @param file             文件
     * @param trainer          培训人
     * @param persons          受训人员
     * @param isReport         是否上报监督机构
     * @param projId           项目id
     * @param projCode         项目编码
     * @param createUserId     创建人id
     * @param createUserName   创建人名称
     * @param jsonCallback     jsonCallback
     */
    public void addSafeEdu(String presentationName, String presentationDate, String presentationInfo, List<FileEntity> file,
                           String trainer, List<SecurityEduTechPerson> persons, int isReport, String projId, String projCode, String createUserId,
                           String createUserName, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("presentationName", presentationName);
        params.put("presentationDate", presentationDate);
        params.put("presentationInfo", presentationInfo);
        params.put("trainer", trainer);
        params.put("isReport", isReport);
        params.put("projId", projId);
        params.put("projCode", projCode);
        params.put("createUserId", createUserId);
        params.put("createUserName", createUserName);
        JSONArray jsonArray = new JSONArray();
        try {
            for (FileEntity fileEntity : file) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("fileExt", fileEntity.fileExt);
                jsonObject.put("fileId", fileEntity.fileId);
                jsonObject.put("fileName", fileEntity.fileName);
                jsonObject.put("fileSize", fileEntity.fileSize);
                jsonObject.put("type", fileEntity.type);
                jsonObject.put("projCode", fileEntity.projCode);
                jsonObject.put("projId", fileEntity.projId);
                jsonArray.put(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("file", jsonArray);

        JSONArray personsArray = new JSONArray();
        try {
            for (SecurityEduTechPerson person : persons) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("teamName", person.teamName);
                jsonObject.put("laborCom", person.laborCom);
                jsonObject.put("isAll", 1);
                jsonObject.put("laborComId", person.laborId);
                jsonObject.put("teamNameId", person.teamId);
                personsArray.put(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("persons", personsArray);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + ADD_SAFE_EDU, params, ADD_SAFE_EDU, jsonCallback);
    }


    /**
     * 新增安全技术交底
     *
     * @param presentationName     交底名称
     * @param presentationDate     交底日期
     * @param presentationPosition 交底部位
     * @param presentationPerson   交底人
     * @param safetyOfficer        专职安全员
     * @param teamLeader           对应班组负责人
     * @param team                 交底班组
     * @param isReport             是否上报监督机构
     * @param presentationInfo     交底内容
     * @param file                 文件
     * @param projId               项目id
     * @param projCode             项目编码
     * @param createUserId         创建人id
     * @param createUserName       创建人
     * @param jsonCallback         jsonCallback
     */
    public void addSafeTechnology(String presentationName, String presentationDate, String presentationPosition,
                                  String presentationPerson, String safetyOfficer, String teamLeader, List<SecurityEduTechPerson> team,
                                  int isReport, String presentationInfo, List<FileEntity> file, String projId,
                                  String projCode, String createUserId, String createUserName, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("presentationName", presentationName);
        params.put("presentationDate", presentationDate);
        params.put("presentationInfo", presentationInfo);
        params.put("presentationPosition", presentationPosition);
        params.put("isReport", isReport);
        params.put("projId", projId);
        params.put("projCode", projCode);
        params.put("createUserId", createUserId);
        params.put("createUserName", createUserName);
        params.put("presentationPerson", presentationPerson);
        params.put("safetyOfficer", safetyOfficer);
        params.put("teamLeader", teamLeader);

        JSONArray personsArray = new JSONArray();
        try {
            for (SecurityEduTechPerson person : team) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("teamName", person.teamName);
                jsonObject.put("laborCom", person.laborCom);
                jsonObject.put("teamPerson", person.teamPerson);
                jsonObject.put("isAll", 1);
                jsonObject.put("laborComId", person.laborId);
                jsonObject.put("teamNameId", person.teamId);
                personsArray.put(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("persons", personsArray);
        JSONArray jsonArray = new JSONArray();
        try {
            for (FileEntity fileEntity : file) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("fileExt", fileEntity.fileExt);
                jsonObject.put("fileId", fileEntity.fileId);
                jsonObject.put("fileName", fileEntity.fileName);
                jsonObject.put("fileSize", fileEntity.fileSize);
                jsonObject.put("type", fileEntity.type);
                jsonObject.put("projCode", fileEntity.projCode);
                jsonObject.put("projId", fileEntity.projId);
                jsonArray.put(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("file", jsonArray);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + ADD_SAFE_TECHNOLOGY, params, ADD_SAFE_TECHNOLOGY, jsonCallback);
    }

    /**
     * 培训人员列表
     *
     * @param proId
     * @param jsonCallback
     */
    public void listLabors(String proId, JsonCallback jsonCallback) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        OkGoHelper.getInstance().get(HostConfig.getHost() + LIST_LABORS, params, LIST_LABORS, jsonCallback);
    }

    /**
     * 添加新楼层
     *
     * @param louOrder 楼栋排序号
     * @param type0    楼层数量
     * @param type1    架空层数量
     * @param type3    地下室层数
     * @param proId    项目id
     */
    public void addFloor(String louOrder, int type0, int type1, int type3, String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("louOrder", louOrder);
        params.put("type0", type0);
        params.put("type1", type1);
        params.put("type3", type3);
        params.put("proId", proId);
        OkGoHelper.getInstance().get(HostConfig.getHost() + SAFE_GUARD_ADD_FLOOR, params, SAFE_GUARD_ADD_FLOOR, jsonCallback);
    }

    /**
     * 删除楼栋楼层
     *
     * @param cengId 楼层ID
     */
    public void deleteSafeGuardBuild(String cengId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("cengId", cengId);
        OkGoHelper.getInstance().get(HostConfig.getHost() + SAFE_GUARD_DELETE_FLOOR, params, SAFE_GUARD_DELETE_FLOOR, jsonCallback);
    }

    /**
     * 安全教育上报到政府端
     *
     * @param id id
     */
    public void saveEduDeclare(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        OkGoHelper.getInstance().get(HostConfig.getHost() + SAVE_EDU_DECLARE, params, SAVE_EDU_DECLARE, jsonCallback);
    }

    /**
     * 新增安全巡查
     */
    public void safePatrolAdd(HashMap params, JsonCallback jsonCallback) {
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SAFE_PATROL_ADD, params, SAFE_PATROL_ADD, jsonCallback);
    }

    /**
     * 安全隐患台账
     *
     * @param proId        项目id
     * @param type         1:本级台账   2:项目台账
     * @param jsonCallback jsonCallback
     */
    public void getSafeHiddenAccountList(String proId, int type, int page, int rows, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        params.put("type", type);
        params.put("pageNum", page);
        params.put("pageSize", rows);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SAFE_HIDDEN_ACCOUNT_URL, params, SAFE_HIDDEN_ACCOUNT_URL, jsonCallback);
    }

    /**
     * 质量隐患台账
     *
     * @param proId        项目id
     * @param type         1:本级台账   2:项目台账
     * @param jsonCallback jsonCallback
     */
    public void getQualityHiddenAccountList(String proId, int type, int page, int rows, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        params.put("type", type);
        params.put("pageNum", page);
        params.put("pageSize", rows);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + QUALITY_HIDDEN_ACCOUNT_URL, params, QUALITY_HIDDEN_ACCOUNT_URL, jsonCallback);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取安全检查列表
     *
     * @param proId       项目id
     * @param page        第几页
     * @param size        每页展示几条
     * @param dangerLevel 隐患等级:1.一般隐患 2.重大隐患 ,
     * @param rectifyType 整改类型:1.限期整改 2.局部停工整改 3.停工挂牌督办
     * @param checkDate   检查日期
     * @param isMessage   是否为消息模块
     */
    public void getSafeCheckList(String proId, int page, int size, String dangerLevel, String rectifyType, String checkDate, boolean isMessage, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        params.put("pageNum", page);
        params.put("pageSize", size);
        params.put("reformGrade", dangerLevel);
        params.put("reformType", rectifyType);
        params.put("checkDate", checkDate);
        if (isMessage) {
            params.put("reformStatus", "1,3");
        }
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SAFE_CHECK_LIST, params, SAFE_CHECK_LIST, jsonCallback);
    }

    /**
     * 获取质量检查列表
     *
     * @param proId       项目id
     * @param page        第几页
     * @param size        每页展示几条
     * @param dangerLevel 隐患等级
     * @param rectifyType 整改类型
     * @param checkDate   检查日期
     */
    public void getQualityCheckList(String proId, int page, int size, String dangerLevel, String rectifyType, String checkDate, boolean isMessage, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        params.put("pageNum", page);
        params.put("pageSize", size);
        params.put("reformGrade", dangerLevel);
        params.put("reformType", rectifyType);
        params.put("checkDate", checkDate);
        if (isMessage) {
            params.put("reformStatus", -1);
        }
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + QUALITY_CHECK_LIST, params, QUALITY_CHECK_LIST, jsonCallback);
    }

    /**
     * 获取安全检查详情
     *
     * @param id           id
     * @param jsonCallback jsonCallback
     */
    public void getSafeCheckDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);

        OkGoHelper.getInstance().get2(HostConfig.getHost2() + SAFE_CHECK_DETAIL, params, SAFE_CHECK_DETAIL, jsonCallback);
    }

    /**
     * 获取安全巡检详情
     *
     * @param id           id
     * @param jsonCallback jsonCallback
     */
    public void getSafePatrolDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);

        OkGoHelper.getInstance().get2(HostConfig.getHost2() + SAFE_PATROL_DETAIL, params, SAFE_PATROL_DETAIL, jsonCallback);
    }

    /**
     * 获取质量检查详情
     *
     * @param id           id
     * @param jsonCallback jsonCallback
     */
    public void getQualityCheckDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + QUALITY_CHECK_DETAIL, params, QUALITY_CHECK_DETAIL, jsonCallback);
    }

    /**
     * 安全巡检-立即/重新整改
     */
    public void safeInspectRectify(HashMap params, JsonCallback jsonCallback) {
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SAFE_INSPECT_RECTIFY, params, SAFE_INSPECT_RECTIFY, jsonCallback);
    }

    /**
     * 安全检查-立即整改
     *
     * @param id           id
     * @param rectifyInfo  整改情况
     * @param isPlan       是否制定预案
     * @param isMeasure    是否制定措施
     * @param money        整改资金金额
     * @param file         文件信息
     * @param reformer     施工单位整改人员
     * @param proId        项目id
     * @param proCode      项目code
     * @param jsonCallback jsonCallback
     */
    public void safeCheckRectify(String id, String rectifyInfo, String isPlan, String isMeasure, String money,
                                 String reformer, String proId, String proCode, List<FileEntity> file, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("securityCheckId", id);
        params.put("reformInfo", rectifyInfo);
        params.put("isEncatPlan", isPlan);
        params.put("isEncatFunc", isMeasure);
        params.put("reformMoney", money);
        params.put("reformer", reformer);
        params.put("createUserId", "0");
        params.put("projCode", proCode);
        params.put("projId", proId);
        JSONArray jsonArray = new JSONArray();
        try {
            for (FileEntity fileEntity : file) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("fileExt", fileEntity.fileExt);
                jsonObject.put("fileId", fileEntity.fileId);
                jsonObject.put("fileName", fileEntity.fileName);
                jsonObject.put("fileSize", fileEntity.fileSize);
                jsonObject.put("type", fileEntity.type);
                jsonObject.put("projCode", fileEntity.projCode);
                jsonObject.put("projId", fileEntity.projId);
                jsonArray.put(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("file", jsonArray);

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SAFE_CHECK_RECTIFY, params, SAFE_CHECK_RECTIFY, jsonCallback);
    }

    /**
     * 质量检查-立即整改
     *
     * @param id           id
     * @param rectifyInfo  整改情况
     * @param isPlan       是否制定预案
     * @param isMeasure    是否制定措施
     * @param money        整改资金金额
     * @param file         文件信息
     * @param reformer     施工单位整改人员
     * @param proId        项目id
     * @param proCode      项目code
     * @param jsonCallback jsonCallback
     */
    public void qualityCheckRectify(String id, String rectifyInfo, String isPlan, String isMeasure, String money,
                                    String reformer, String proId, String proCode, List<FileEntity> file, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("checkId", id);
        params.put("reformInfo", rectifyInfo);
        params.put("isEncatPlan", isPlan);
        params.put("isEncatFunc", isMeasure);
        params.put("reformMoney", money);
        params.put("createUserId",UserManager.getInstance().getUserId());
        params.put("createUserName", reformer);
//        params.put("projCode", proCode);
        params.put("projId", proId);
        JSONArray jsonArray = new JSONArray();
        try {
            for (FileEntity fileEntity : file) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("fileExt", fileEntity.fileExt);
                jsonObject.put("fileId", fileEntity.fileId);
                jsonObject.put("fileName", fileEntity.fileName);
                jsonObject.put("fileSize", fileEntity.fileSize);
                jsonObject.put("type", fileEntity.type);
                jsonObject.put("projCode", fileEntity.projCode);
                jsonObject.put("projId", fileEntity.projId);
                jsonArray.put(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("file", jsonArray);

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + QUALITY_CHECK_RECTIFY, params, QUALITY_CHECK_RECTIFY, jsonCallback);
    }

    /**
     * 获取监理检查详情
     *
     * @param id           id
     * @param jsonCallback jsonCallback
     */
    public void getSupervisorCheckDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + SUPERVISOR_CHECK_DETAIL, params, SUPERVISOR_CHECK_DETAIL, jsonCallback);
    }

    /**
     * 获取监理检查列表
     *
     * @param proId        项目id
     * @param checkType    检查类型1：安全，2：质量
     * @param page         第几页
     * @param size         每页展示几条
     * @param dangerLevel  隐患等级
     * @param rectifyType  整改类型
     * @param checkDate    检查日期
     * @param jsonCallback jsonCallback
     */
    public void getSupervisorCheckList(String proId, int checkType, int page, int size, String dangerLevel, String rectifyType, String checkDate, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        params.put("checkType", checkType);
        params.put("pageNum", page);
        params.put("pageSize", size);
        params.put("reformGrade", dangerLevel);
        params.put("reformType", rectifyType);
        params.put("checkDate", checkDate);

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SUPERVISOR_CHECK_LIST, params, SUPERVISOR_CHECK_LIST, jsonCallback);
    }

    /**
     * 监理审核
     *
     * @param checkType    检查类型1：安全，2：质量
     * @param flag         审核标识（1：同意；0：驳回） ,
     * @param advice       监理单位意见
     * @param verifyName   监理单位审核人
     * @param id           整改id
     * @param jsonCallback jsonCallback
     */
    public void supervisorVerify(int checkType, int flag, String advice, String verifyName, String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("checkType", checkType);
        params.put("flag", flag);
        params.put("supervisorHandleInfo", advice);
        params.put("supervisorHandler", verifyName);
        params.put("id", id);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + SUPERVISOR_VERIFY, params, SUPERVISOR_VERIFY, jsonCallback);
    }

    /**
     * 撤回
     *
     * @param id           整改回复id
     * @param jsonCallback jsonCallback
     */
    public void safeCheckRevocation(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + SAFE_REVOCATION, params, SAFE_REVOCATION, jsonCallback);
    }

    /**
     * 撤回
     *
     * @param id           整改回复id
     * @param jsonCallback jsonCallback
     */
    public void qualityCheckRevocation(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + QUALITY_REVOCATION, params, QUALITY_REVOCATION, jsonCallback);
    }
}
