package com.ljkj.qxn.wisdomsitepro.model;

import com.ljkj.qxn.wisdomsitepro.Utils.OkGoHelper;
import com.ljkj.qxn.wisdomsitepro.data.api.HostConfig;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteCompressiveInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.ConcreteEntranceAcceptanceInfo;

import java.util.HashMap;

import cdsp.android.http.JsonCallback;
import cdsp.android.model.BaseModel;

/**
 * 混凝土
 * Created by lxx on 2018/3/10.
 */
public class ConcreteModel extends BaseModel {

    /**
     * 商品混凝土进场验收
     */
    private static final String APPROACH_LIST = "/quality/concreteEnter/list";

    /**
     * 商品混凝土进场验收详情
     */
    private static final String APPROACH_DETAIL = "/quality/concreteEnter/detail";

    /**
     * 商品混凝土进场验收-新增
     */
    private static final String APPROACH_SAVE = "/quality/concreteEnter/add";


    /**
     * 混凝土抗压强度检验
     */
    private static final String COMPRESSIVE_LIST = "/quality/qualityConcreteCompression/list";


    /**
     * 混凝土抗压强度检验-新增
     */
    private static final String COMPRESSIVE_SAVE = "/quality/qualityConcreteCompression/add";


    /**
     * 混凝土抗压强度检验-详情
     */
    private static final String COMPRESSIVE_DETAIL = "/quality/qualityConcreteCompression/detail";

    /**
     * 混凝土抗渗检验
     */
    private static final String PERMEABILITY_LIST = "/quality/qualityConcreteImpermeability/list";

    /**
     * 混凝土抗渗检验-新增
     */
    private static final String PERMEABILITY_SAVE = "/quality/qualityConcreteImpermeability/add";

    /**
     * 混凝土抗渗检验-详情
     */
    private static final String PERMEABILITY_DETAIL = "/quality/qualityConcreteImpermeability/detail";

    private ConcreteModel() {
    }

    public static ConcreteModel newInstance() {
        return ModelHolder.holder;
    }

    private static class ModelHolder {
        private static ConcreteModel holder = new ConcreteModel();
    }

    /**
     * 商品混凝土进场验收
     *
     * @param keyWord      浇筑部位 非必填
     * @param page         当前页 默认为1，表示第一页
     * @param proId        当前项目id
     * @param rows         每页展现几条 默认为10，表示默认展现10条数据
     * @param jsonCallback jsonCallback
     */
    public void getApproachList(String keyWord, int page, String proId, int rows, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("pouringPart", keyWord);
        requestParams.put("pageNum", page);
        requestParams.put("projId", proId);
        requestParams.put("pageSize", rows);

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + APPROACH_LIST, requestParams, APPROACH_LIST, jsonCallback);
    }

    /**
     * 商品混凝土进场验收-新增
     * <p>
     * hntgys     混凝土供应商
     * hntlb      混凝土类别      ===>下拉框：普通混凝土、泵送混凝土、抗渗混凝土、防水混凝土、粉煤灰混凝土、其他混凝土
     * jldwxcpzry 监理单位现场旁站人员
     * jssjTime   结束时间 	日期格式yyyy-MM-dd
     * jzbw       浇筑部位
     * jzsjTime   浇筑时间 	日期格式yyyy-MM-dd
     * phbbh      配合比编号
     * proId      当前项目id
     * qddj       强度等级 ===>下拉框：C10,C15,C20,C25,C30,C35,C40,C45,C50,C55C60,C65,C70,C75,C80,C85,C90,C95,C100
     * tqqk       天气情况
     * xclwczry   现场劳务操作人员
     * xcsgry     现场施工人员
     * ysfjFile   验收附件 ysfj_file
     */
    public void saveApproach(ConcreteEntranceAcceptanceInfo detail, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("concreteSupplier", detail.getConcreteSupplier());
        requestParams.put("concreteType", detail.getConcreteType());
        requestParams.put("supervisorUnitPersonnel", detail.getSupervisorUnitPersonnel());
        requestParams.put("pouringEndDate", detail.getPouringEndDate());
        requestParams.put("pouringPart", detail.getPouringPart());
        requestParams.put("pouringStartDate", detail.getPouringStartDate());
        requestParams.put("coordinationCode", detail.getCoordinationCode());
        requestParams.put("projId", detail.getProjId());
        requestParams.put("strengthGrade", detail.getStrengthGrade());
        requestParams.put("weatherCondition", detail.getWeatherCondition());
        requestParams.put("sceneOperationPersonnel", detail.getSceneOperationPersonnel());
        requestParams.put("sceneConstructionPersonnel", detail.getSceneConstructionPersonnel());
        requestParams.put("file", detail.getFile());
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + APPROACH_SAVE, requestParams, APPROACH_SAVE, jsonCallback);
    }

    /**
     * 商品混凝土进场验收-详情
     *
     * @param id           当前id
     * @param jsonCallback jsonCallback
     */
    public void getApproachDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + APPROACH_DETAIL, requestParams, APPROACH_DETAIL, jsonCallback);
    }


    /**
     * 混凝土抗压强度检验
     *
     * @param keyWord 检测编号
     * @param page    当前页 默认为1，表示第一页
     * @param proId   当前项目id
     * @param rows    每页展现几条 默认为10，表示默认展现10条数据
     */
    public void getCompressiveList(String keyWord, int page, String proId, int rows, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("checkCode", keyWord);
        requestParams.put("pageNum", page);
        requestParams.put("projId", proId);
        requestParams.put("pageSize", rows);

        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + COMPRESSIVE_LIST, requestParams, COMPRESSIVE_LIST, jsonCallback);
    }


    /**
     * 混凝土抗压强度检验-新增
     * jsonCallback jsonCallback
     */
    public void saveCompressive(ConcreteCompressiveInfo detail, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("formingDate", detail.getFormingDate());
        requestParams.put("checkCode", detail.getCheckCode());
        requestParams.put("checkUnit", detail.getCheckUnit());
        requestParams.put("checkResult", detail.getCheckResult());
        requestParams.put("checkDate", detail.getCheckDate());
        requestParams.put("witnessUnit", detail.getWitnessUnit());
        requestParams.put("witness", detail.getWitness());
        requestParams.put("nearTerm", detail.getNearTerm());
        requestParams.put("projId", detail.getProjId());
        requestParams.put("usePart", detail.getUsePart());
        requestParams.put("appearanceQuality", detail.getAppearanceQuality());
        requestParams.put("designStrengthGrade", detail.getDesignStrengthGrade());
        requestParams.put("designImperviousGrade", detail.getDesignImperviousGrade());
        requestParams.put("entrustUnit", detail.getEntrustUnit());
        requestParams.put("maintenanceMode", detail.getMaintenanceMode());
        requestParams.put("sampleName", detail.getSampleName());
        requestParams.put("file", detail.getFile());
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + COMPRESSIVE_SAVE, requestParams, COMPRESSIVE_SAVE, jsonCallback);
    }

    /**
     * 混凝土抗压强度检验-详情
     *
     * @param id           当前id
     * @param jsonCallback jsonCallback
     */
    public void getCompressiveDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + COMPRESSIVE_DETAIL, requestParams, COMPRESSIVE_DETAIL, jsonCallback);
    }


    /**
     * 混凝土抗渗检验
     *
     * @param keyWord      检测编号
     * @param page         当前页 默认为1，表示第一页
     * @param proId        当前项目id
     * @param rows         每页展现几条
     * @param jsonCallback jsonCallback
     */
    public void getPermeabilityList(String keyWord, int page, String proId, int rows, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("checkCode", keyWord);
        requestParams.put("pageNum", page);
        requestParams.put("projId", proId);
        requestParams.put("pageSize", rows);
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + PERMEABILITY_LIST, requestParams, PERMEABILITY_LIST, jsonCallback);
    }

    /**
     * 混凝土抗渗检验-新增-
     * <p>
     * cxrq         成型日期 日期格式yyyy-MM-dd
     * jybh         检验编号
     * jydw         检验单位
     * jyjg         检验结果
     * jyrq         检验日期
     * jzdw         见证单位
     * jzr          见证人
     * lq           龄期（d）
     * proId        当前项目id
     * sjksdj       设计抗渗等级
     * sjqddj       设计强度等级
     * sybw         使用部位
     * wgzl         外观质量 下拉框：无、露筋 、蜂窝、孔洞、夹渣、疏松、裂缝、连接部位缺陷、外形缺陷、外表缺陷
     * wtdw         委托单位
     * yhfs         养护方式  下拉框：标准养护、同条件养护、拆模同条件养护、自然养护、标准养护20±2℃、同条件养护600℃、同条件养护600℃·d、塔吊基础同条件养护、同养转标养、同条件养护临界
     * ypmc         样品名称
     * ysfjFile     验收附件
     *
     * @param jsonCallback jsonCallback
     */
    public void savePermeability(ConcreteCompressiveInfo detail, JsonCallback jsonCallback) {

        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("formingDate", detail.getFormingDate());
        requestParams.put("checkCode", detail.getCheckCode());
        requestParams.put("checkUnit", detail.getCheckUnit());
        requestParams.put("checkResult", detail.getCheckResult());
        requestParams.put("checkDate", detail.getCheckDate());
        requestParams.put("witnessUnit", detail.getWitnessUnit());
        requestParams.put("witness", detail.getWitness());
        requestParams.put("nearTerm", detail.getNearTerm());
        requestParams.put("projId", detail.getProjId());
        requestParams.put("usePart", detail.getUsePart());
        requestParams.put("appearanceQuality", detail.getAppearanceQuality());
        requestParams.put("designStrengthGrade", detail.getDesignStrengthGrade());
        requestParams.put("designImperviousGrade", detail.getDesignImperviousGrade());
        requestParams.put("entrustUnit", detail.getEntrustUnit());
        requestParams.put("maintenanceMode", detail.getMaintenanceMode());
        requestParams.put("sampleName", detail.getSampleName());
        requestParams.put("file", detail.getFile());
        OkGoHelper.getInstance().postJson(HostConfig.getHost2() + PERMEABILITY_SAVE, requestParams, PERMEABILITY_SAVE, jsonCallback);
    }


    /**
     * 混凝土抗渗检验-详情
     *
     * @param id           当前id
     * @param jsonCallback jsonCallback
     */
    public void getPermeabilityDetail(String id, JsonCallback jsonCallback) {
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("id", id);
        OkGoHelper.getInstance().get2(HostConfig.getHost2() + PERMEABILITY_DETAIL, requestParams, PERMEABILITY_DETAIL, jsonCallback);
    }


}
