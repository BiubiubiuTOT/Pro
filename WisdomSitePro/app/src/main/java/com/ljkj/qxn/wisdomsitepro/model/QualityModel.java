package com.ljkj.qxn.wisdomsitepro.model;

import com.ljkj.qxn.wisdomsitepro.Utils.OkGoHelper;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.data.entity.QualityCheckResultDetail;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import cdsp.android.http.JsonCallback;
import cdsp.android.model.BaseModel;

/**
 * 类描述：质量
 * 创建人：mhl
 * 创建时间：2018/2/27 10:23
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class QualityModel extends BaseModel {
    /**
     * 质量巡检详情
     */
    private static final String QUALITY_PATROL_DETAIL = "/quality/qualityCheck/viewById";
    /**
     * 质量巡检-立即/重新整改
     */
    private static final String QUALITY_INSPECT_RECTIFY = "/quality/qualityCheck/addRreform";
    /**
     * 质量巡检-新增
     */
    private static final String QUALITY_PATROL_ADD = "/quality/qualityCheck/add";
    /**
     * 质量巡检-列表
     */
    private static final String QUALITY_PATROL_LIST_URL = "/quality/qualityCheck/list";

    private static QualityModel model;

    /**
     * 质量管理体系
     */
    private static final String SETUPINDEX_URL = "/quality/qualitySystem/viewByProjId";


    /**
     * 质量/安全检查列表查询
     */
    private static final String INSPECT_LIST = "/qualityController.do?inspectList";

    /**
     * 质量验收列表查询
     */
    private static final String ACCEPT_LIST = "/qualityController.do?acceptList";


    /**
     * 查看检查结果
     */
    private static final String INSPECTION_RESULT_DETAIL = "/qualityController.do?inspectionResultDetail";

    /**
     * 立即整改/重新整改
     */
    private static final String DEAL_WITH_SAVE = " /qualityController.do?dealWithSave";


    /**
     * 立即整改/重新整改文件上传
     */
    private static final String DEAL_WITH_AND_FILE_SAVE = " /qualityController.do?dealWithAndFileSave";


    /**
     * 查看政府端下发的附件
     */
    private static final String HANDLE_DOWN_FILE = "/commController.do?handleDownFile";

    /**
     * 质量监督申报
     */
    private static final String QUA_REGISTER_URL = "/qualityController.do?register";

    /**
     * 质质量监督注册登记
     */
    private static final String QUALITY_SUP_REG_URL = "/qualityController.do?qualitySupReg";


    /**
     * 下发责任整改通知书
     */
    private static final String APPROVAL_SAVE_URL = "/qualityController.do?addCheck";

    /**
     * 班组负责人列表
     */
    private static final String MANAGE_LIST_URL = "/qualityController.do?manageList";

    /**
     * 巡检详情
     */
    private static final String INSPECTION_DETAIL_URL = "/qualityController.do?inspectionDetail";

    /**
     * 立即整改
     */
    private static final String SAVE_APPROVAL_URL = "/qualityController.do?approvalSave";

    /**
     * 安全/质量日志
     */
    private static final String SAFE_QUALITY_LOG_URL = "/proSecurityQualityLogsController.do?list";

    /**
     * 安全/质量日志详情
     */
    private static final String SAFE_QUALITY_LOG_DETAIL_URL = "/proSecurityQualityLogsController.do?show";

    /**
     * 安全/质量日志新增
     */
    private static final String SAFE_QUALITY_LOG_ADD_URL = "/proSecurityQualityLogsController.do?add";

    private QualityModel() {

    }

    public static QualityModel newInstance() {
        if (model == null) {
            model = new QualityModel();
        }
        return model;
    }


    /**
     * 质量管理体系
     *
     * @param proId        项目ID
     * @param jsonCallback
     */
    public void viewSetupindex(String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + SETUPINDEX_URL, params, SETUPINDEX_URL, jsonCallback);

    }

    /**
     * 质量/安全检查列表查询
     *
     * @param page     当前页,默认为1，表示第一页
     * @param proId    当前项目id
     * @param rows     每页展现几条 ,默认为10，表示默认展现10条数据
     * @param type     类型:1安全，2质量
     * @param yhdj     隐患等级
     * @param zglx     整改类型
     * @param jcrqTime 检查日期
     */
    public void getQualityCheckList(int page, String proId, int rows, String type, String yhdj, String zglx, String jcrqTime, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("page", page);
        requestParams.put("proId", proId);
        requestParams.put("rows", rows);
        requestParams.put("type", type);
        requestParams.put("yhdj", yhdj);
        requestParams.put("zglx", zglx);
        requestParams.put("jcrqTime", jcrqTime);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + INSPECT_LIST, requestParams, INSPECT_LIST, jsonCallback);
    }

    /**
     * 质量验收列表查询
     *
     * @param page  当前页,默认为1，表示第一页
     * @param proId 当前项目id
     * @param rows  每页展现几条 ,默认为10，表示默认展现10条数据
     */
    public void getQualityAcceptList(int page, String proId, int rows, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("page", page);
        requestParams.put("proId", proId);
        requestParams.put("rows", rows);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + ACCEPT_LIST, requestParams, ACCEPT_LIST, jsonCallback);
    }


    /**
     * 查看检查结果
     *
     * @param id    id
     * @param proId 当前项目id
     */
    public void getCheckResultDetail(String id, String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);
        requestParams.put("proId", proId);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + INSPECTION_RESULT_DETAIL, requestParams, INSPECTION_RESULT_DETAIL, jsonCallback);
    }

    /**
     * 立即整改/重新整改
     */
    public void dealWithSave(QualityCheckResultDetail.Base base, String proId, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("jldwyj", base.getJldwyj()); //监理单位意见
        requestParams.put("proAddress", base.getProAddress()); //工程地址
        requestParams.put("proId", proId); //当前项目id
        requestParams.put("proName", base.getProName()); //工程名称
        requestParams.put("relId", base.getId()); //关联id	 ==>当前检查记录的id
        requestParams.put("sfzdya", base.getSfzdya()); //是否制定预案
        requestParams.put("zgqk", base.getZgqk()); //整改情况
        requestParams.put("sfzdcs", base.getSfzdcs());//是否制定措施
        requestParams.put("zgtzsbh", base.getZgtzsbh()); //工程质量整改通知书编号
        requestParams.put("zgzjse", base.getZgzjse()); //整改资金数额
        OkGoHelper.getInstance().get(HostConfig.getHost2() + DEAL_WITH_SAVE, requestParams, DEAL_WITH_SAVE, jsonCallback);
    }

    /**
     * 立即整改/重新整改文件上传
     *
     * @param base         base
     * @param proId        项目id
     * @param imageList    整改图片
     * @param reportList   整改报告书扫描件
     * @param planList     制定预案附件
     * @param measureList  制定措施附件
     * @param moneyList    整改资金附件
     * @param jsonCallback jsonCallback
     */
    public void dealWithAndFileSave(QualityCheckResultDetail.Base base, String proId, List<File> imageList,
                                    List<File> reportList, List<File> planList, List<File> measureList, List<File> moneyList, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("jldwyj", base.getJldwyj()); //监理单位意见
        requestParams.put("proAddress", base.getProAddress()); //工程地址
        requestParams.put("proId", proId); //当前项目id
        requestParams.put("proName", base.getProName()); //工程名称
        requestParams.put("relId", base.getId()); //关联id	 ==>当前检查记录的id
        requestParams.put("sfzdya", base.getSfzdya()); //是否制定预案
        requestParams.put("zgqk", base.getZgqk()); //整改情况
        requestParams.put("sfzdcs", base.getSfzdcs());//是否制定措施
        requestParams.put("zgtzsbh", base.getZgtzsbh()); //工程质量整改通知书编号
        requestParams.put("zgzjse", base.getZgzjse()); //整改资金数额


        HashMap<String, List<File>> files = new HashMap<>();
        putFile(files, "sczgtpfile", imageList);
        putFile(files, "file", reportList);
        putFile(files, "zdyafile", planList);
        putFile(files, "zdcsfile", measureList);
        putFile(files, "zgzjfile", moneyList);
        OkGoHelper.getInstance().uploadFiles(HostConfig.getHost2() + DEAL_WITH_AND_FILE_SAVE, requestParams, files, DEAL_WITH_AND_FILE_SAVE, jsonCallback);
    }

    private void putFile(HashMap<String, List<File>> files, String key, List<File> list) {
        if (!list.isEmpty()) {
            files.put(key, list);
        }
    }


    /**
     * 查看政府端下发的附件
     *
     * @param file 这里传QualityCheckResultDetail.proInspect.jcjlb
     * @param type 这里传"fileDown"
     */
    public void handleDownFile(String file, String type, JsonCallback jsonCallback) {

        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("file", file);
        requestParams.put("type", type);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + HANDLE_DOWN_FILE, requestParams, HANDLE_DOWN_FILE, jsonCallback);
    }

    /**
     * 质量监督申报
     *
     * @param proId
     * @param jsonCallback
     */
    public void quaRegister(String proId, JsonCallback jsonCallback) {

        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("proId", proId);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + QUA_REGISTER_URL, requestParams, QUA_REGISTER_URL, jsonCallback);
    }

    /**
     * 下发责任整改通知书
     *
     * @param bzfzr        班组负责人id
     * @param code         质检编号
     * @param fwr          发文人
     * @param lossType     事故造成的人员伤亡或者直接经济损失分类
     * @param status       状态
     * @param upTime       检查日期
     * @param type         检查类型安全或质量	string	必填：2安全巡检；3质量巡检
     * @param whyType      事故发生的原因分类
     * @param proId        项目id
     * @param zgbz         整改班组
     * @param zgnr         整改内容
     * @param jsonCallback
     */
    public void saveApproval(String bzfzr, String code, String fwr, String lossType, String status, String upTime, String type, String whyType, String proId,
                             String zgbz, String zgnr, JsonCallback jsonCallback) {

        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("bzfzr", bzfzr);
        requestParams.put("code", code);
        requestParams.put("fwr", fwr);
        requestParams.put("code", code);
        requestParams.put("lossType", lossType);
        requestParams.put("status", status);
        requestParams.put("upTime", upTime);
        requestParams.put("type", type);
        requestParams.put("whyType", whyType);
        requestParams.put("relId", proId);
        requestParams.put("zgbz", zgbz);
        requestParams.put("zgnr", zgnr);
        requestParams.put("whyType", whyType);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + APPROVAL_SAVE_URL, requestParams, APPROVAL_SAVE_URL, jsonCallback);
    }

    /**
     * 班组负责人列表
     *
     * @param proId
     * @param jsonCallback
     */
    public void listManageList(String proId, JsonCallback jsonCallback) {

        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("proId", proId);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + MANAGE_LIST_URL, requestParams, MANAGE_LIST_URL, jsonCallback);

    }

    /**
     * 巡检详情
     *
     * @param id
     * @param jsonCallback
     */
    public void viewInspectionDetail(String id, JsonCallback jsonCallback) {

        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + INSPECTION_DETAIL_URL, requestParams, INSPECTION_DETAIL_URL, jsonCallback);
    }


    /**
     * 质质量监督注册登记
     *
     * @param prodId proId
     */
    public void quaSupervisorRegister(String prodId, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("proId", prodId);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + QUALITY_SUP_REG_URL, requestParams, QUALITY_SUP_REG_URL, jsonCallback);
    }

    /**
     * @param id
     * @param zgDetail 整改详情
     */
    public void saveRectifyDetail(String id, String zgDetail, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);
        requestParams.put("zgDetail", zgDetail);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + SAVE_APPROVAL_URL, requestParams, SAVE_APPROVAL_URL, jsonCallback);
    }

    /**
     * 立即、重新整改
     *
     * @param bizId         当前巡检详情id
     * @param postMan       发文人
     * @param reformDetail  整改详情
     * @param statusHistory 记录状态，立即整改传2，重新整改传4
     * @param teamLeader    班组负责人
     */
    public void approvalSave(String bizId, String postMan, String reformDetail, String statusHistory, String teamLeader, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("bizId", bizId);
        requestParams.put("postMan", postMan);
        requestParams.put("reformDetail", reformDetail);
        requestParams.put("statusHistory", statusHistory);
        requestParams.put("teamLeader", teamLeader);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + SAVE_APPROVAL_URL, requestParams, SAVE_APPROVAL_URL, jsonCallback);
    }


    /**
     * @param id
     * @param zgDetail 整改详情
     * @param zgFlag   整改标志
     */
    public void saveRectifyDetail(String id, String zgDetail, String zgFlag, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);
        requestParams.put("zgDetail", zgDetail);
        requestParams.put("zgFlag", zgFlag);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + SAVE_APPROVAL_URL, requestParams, SAVE_APPROVAL_URL, jsonCallback);
    }

    /**
     * 立即审批
     *
     * @param id              当前整改记录的id
     * @param statusHistory   状态
     * @param noThroughReason 未通过原因
     * @param bizId           当前巡检详情的id
     * @param jsonCallback    jsonCallback
     */
    public void checkRectifyDetail(String id, String statusHistory, String noThroughReason, String bizId, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);
        requestParams.put("statusHistory", statusHistory);
        requestParams.put("noThroughReason", noThroughReason);
        requestParams.put("bizId", bizId);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + SAVE_APPROVAL_URL, requestParams, SAVE_APPROVAL_URL, jsonCallback);
    }

    /**
     * 安全/质量日志
     *
     * @param proId        项目id
     * @param type         1.安全日志   2.质量日志
     * @param page         第几页
     * @param rows         每页展示几条
     * @param jsonCallback jsonCallback
     */
    public void getSafeQualityLogList(String proId, int type, int page, int rows, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("pro_id", proId);
        requestParams.put("page", page);
        requestParams.put("type", type);
        requestParams.put("rows", rows);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + SAFE_QUALITY_LOG_URL, requestParams, SAFE_QUALITY_LOG_URL, jsonCallback);
    }

    /**
     * 安全/质量日志 详情
     *
     * @param id 日志id
     */
    public void getSafeQualityLogDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);
        OkGoHelper.getInstance().get(HostConfig.getHost2() + SAFE_QUALITY_LOG_DETAIL_URL, requestParams, SAFE_QUALITY_LOG_DETAIL_URL, jsonCallback);
    }

    /**
     * 安全/质量日志 新增
     *
     * @param proId               项目id
     * @param type                1.安全日志   2.质量日志
     * @param date                记录日期
     * @param weather             天气
     * @param constructionSite    施工部位
     * @param constructionDynamic 施工工序动态
     * @param status              状况
     * @param handleSituation     处理情况
     * @param imageList           图片列表
     * @param jsonCallback        jsonCallback
     */
    public void addSafeQualityLog(String proId, int type, String date, String weather, String constructionSite,
                                  String constructionDynamic, String status, String handleSituation, List<File> imageList, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("proId", proId);
        requestParams.put("type", type);
        requestParams.put("conf02", date);
        requestParams.put("weather", weather);
        requestParams.put("recorder", UserManager.getInstance().getUserName());
        requestParams.put("constructionSite", constructionSite);
        requestParams.put("constructionProcess", constructionDynamic);
        if (type == 1) { //安全
            requestParams.put("situation", status);
            requestParams.put("problem", handleSituation);
        } else { //质量
            requestParams.put("conf01", status);
            requestParams.put("qualityProblem", handleSituation);
        }
        HashMap<String, List<File>> files = new HashMap<>();
        if (imageList.size() > 0) {
            files.put("att_imgs", imageList);
        }
        OkGoHelper.getInstance().uploadFiles(HostConfig.getHost2() + SAFE_QUALITY_LOG_ADD_URL, requestParams, files, SAFE_QUALITY_LOG_ADD_URL, jsonCallback);
    }

    /**
     * 获取质量巡检详情
     *
     * @param id           id
     * @param jsonCallback jsonCallback
     */
    public void getQualityPatrolDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("id", id);

        OkGoHelper.getInstance().get2(HostConfig.getHost2() + QUALITY_PATROL_DETAIL, params, QUALITY_PATROL_DETAIL, jsonCallback);
    }

    /**
     * 质量巡检-立即/重新整改
     */
    public void qualityInspectRectify(HashMap params, JsonCallback jsonCallback) {
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + QUALITY_INSPECT_RECTIFY, params, QUALITY_INSPECT_RECTIFY, jsonCallback);
    }


    /**
     * 新增质量巡查
     */
    public void qualityPatrolAdd(HashMap params, JsonCallback jsonCallback) {
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + QUALITY_PATROL_ADD, params, QUALITY_PATROL_ADD, jsonCallback);
    }

    /**
     * 质量巡检V2
     */
    public void getQualityPatrolListV2(String proId, String whyType, String reformTeam, String startDate,
                                       String endDate, int rows, int page, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        params.put("whyType", whyType);
        params.put("checkDateEnd", endDate);
        params.put("checkDateStart", startDate);
        params.put("teamId", reformTeam);
        params.put("pageSize", rows);
        params.put("pageNum", page);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + QUALITY_PATROL_LIST_URL, params, QUALITY_PATROL_LIST_URL, jsonCallback);
    }

    /**
     * 质量巡检 消息V2
     */
    public void getQualityPatrolMessageListV2(String proId, int rows, int page, JsonCallback jsonCallback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", proId);
        params.put("reformStatus", "-1");
        params.put("pageSize", rows);
        params.put("pageNum", page);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + QUALITY_PATROL_LIST_URL, params, QUALITY_PATROL_LIST_URL, jsonCallback);
    }
}
