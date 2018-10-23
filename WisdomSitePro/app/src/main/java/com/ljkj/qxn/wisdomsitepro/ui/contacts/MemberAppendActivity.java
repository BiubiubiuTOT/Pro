package com.ljkj.qxn.wisdomsitepro.ui.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.IDCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.Utils.DateUtil;
import com.ljkj.qxn.wisdomsitepro.Utils.OCRHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.PhotoPickerHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.PickerDialogHelper;
import com.ljkj.qxn.wisdomsitepro.Utils.SpannableStringUtils;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.InputItemView;
import com.ljkj.qxn.wisdomsitepro.Utils.widget.SquareImageView;
import com.ljkj.qxn.wisdomsitepro.contract.common.UploadContract;
import com.ljkj.qxn.wisdomsitepro.contract.contacts.DepartmentListContract;
import com.ljkj.qxn.wisdomsitepro.contract.contacts.MemberAddContract;
import com.ljkj.qxn.wisdomsitepro.data.FileEntity;
import com.ljkj.qxn.wisdomsitepro.data.PermissionConstant;
import com.ljkj.qxn.wisdomsitepro.data.entity.LaborCompanyBean;
import com.ljkj.qxn.wisdomsitepro.data.entity.PageInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.RoleBean;
import com.ljkj.qxn.wisdomsitepro.data.entity.UserCertificateBean;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.DepartmentListInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.DutyListInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.ProjectDeptInfo;
import com.ljkj.qxn.wisdomsitepro.data.entity.contract.QueryMemberInfo;
import com.ljkj.qxn.wisdomsitepro.data.event.RefreshEvent;
import com.ljkj.qxn.wisdomsitepro.manager.UserManager;
import com.ljkj.qxn.wisdomsitepro.model.CommonModel;
import com.ljkj.qxn.wisdomsitepro.model.ContactsModel;
import com.ljkj.qxn.wisdomsitepro.presenter.common.UploadPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.contacts.DepartmentListPresenter;
import com.ljkj.qxn.wisdomsitepro.presenter.contacts.MemberAddPresenter;
import com.ljkj.qxn.wisdomsitepro.ui.contacts.adapter.UserCertificateAdapter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.glide.GlideHelper;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.DateUtils;
import cdsp.android.util.KeyboardUtil;
import cdsp.android.util.ToastUtils;
import cdsp.android.util.ValidationUtils;

/**
 * 类描述：成员添加
 * 创建人：rjf
 * 创建时间：2018/6/25 16:16.
 * 修改人：
 * 修改时间
 * 修改备注
 */

public class MemberAppendActivity extends BaseActivity implements MemberAddContract.View, DepartmentListContract.View, UploadContract.View {

    private static final int REQUEST_CODE_CAMERA = 100;

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.svContent)
    ScrollView svContent;
    @BindView(R.id.input_name)
    InputItemView inputName;
    @BindView(R.id.input_gender)
    InputItemView inputGender;
    @BindView(R.id.input_age)
    InputItemView inputAge;
    @BindView(R.id.input_birthday)
    InputItemView inputBirthday;
    @BindView(R.id.input_nationality)
    InputItemView inputNationality;
    @BindView(R.id.input_id_number)
    InputItemView inputIdNumber;
    @BindView(R.id.input_address)
    InputItemView inputAddress;
    @BindView(R.id.input_department)
    InputItemView inputDepartment;
    @BindView(R.id.input_position)
    InputItemView inputPosition;
    @BindView(R.id.input_link_phone)
    InputItemView inputLinkPhone;
    @BindView(R.id.input_certificate_start_date)
    InputItemView inputCertificateStartDate;
    @BindView(R.id.input_certificate_end_date)
    InputItemView inputCertificateEndDate;
    @BindView(R.id.input_educational_attainments)
    InputItemView inputEducationalAttainments;
    @BindView(R.id.input_is_medical_history)
    InputItemView inputIsMedicalHistory;
    @BindView(R.id.input_work_start_date)
    InputItemView inputWorkStartDate;
    @BindView(R.id.input_emergency_contact_person)
    InputItemView inputEmergencyContactPerson;
    @BindView(R.id.input_emergency_contact_number)
    InputItemView inputEmergencyContactNumber;
    @BindView(R.id.input_attachment)
    InputItemView inputAttachment;
    @BindView(R.id.rl_show_other_info)
    RelativeLayout rlShowOtherInfo;
    @BindView(R.id.ll_other_info)
    LinearLayout llOtherInfo;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.btn_append_member)
    Button btnAppendMember;
    @BindView(R.id.tv_scan)
    TextView scanText;
    @BindView(R.id.input_user_type)
    InputItemView inputUserType;
    @BindView(R.id.input_title)
    InputItemView inputTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.img)
    SquareImageView img;

    //添加成员处理逻辑
    private MemberAddPresenter mAddPresenter;

    //获取部门列表
    private DepartmentListPresenter mListPresenter;
    private UploadPresenter uploadPresenter;


    //部门列表
    private List<DepartmentListInfo> mDepartmentList;

    //部门名称表
    private List<String> mDeptNameList = new ArrayList<>();
    //性别
    private String[] mGenders = {"男", "女"};
    //人员类型
    private String[] mUserTypes = {"项目管理员", "部门经理", "部门成员", "班组长"};
    private String[] mUserTypeIndex = {"169", "170", "171", "172"};
    private String[] titles = {"正高级工程师", "高级工程师", "中级工程师", "助理工程师", "技术员"};
    private String[] titleIndex = {"173", "174", "175", "176", "177"};

    private String userType;
    private String title;
    private List<UserCertificateBean> userCertificateBeans = new ArrayList<>();
    private UserCertificateAdapter certificateAdapter;
    private List<FileEntity> entities;
    private ArrayList<String> filePaths;
    private String inputNameContent;
    private String inputIdNumberContent;
    private String phoneContent;
    private String deptId;
    private List<String> roleList = new ArrayList<>();
    private Map<String, String> mapRole = new HashMap<>();
    private String roleId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_append_memeber);
    }

    @Override
    protected void initUI() {
        tvTitle.setText("添加成员");
        scanText.setVisibility(OCRHelper.hasGotToken ? View.VISIBLE : View.GONE);
        inputDepartment.setTitle(SpannableStringUtils.getBuilder("*")
                .setForegroundColor(ContextCompat.getColor(this, R.color.color_red))
                .append("部门")
                .create());

        inputPosition.setTitle(SpannableStringUtils.getBuilder("*")
                .setForegroundColor(ContextCompat.getColor(this, R.color.color_red))
                .append("职位")
                .create());

        inputLinkPhone.setTitle(SpannableStringUtils.getBuilder("*")
                .setForegroundColor(ContextCompat.getColor(this, R.color.color_red))
                .append("联系电话")
                .create());


        inputUserType.setTitle(SpannableStringUtils.getBuilder("*")
                .setForegroundColor(ContextCompat.getColor(this, R.color.color_red))
                .append("人员类型")
                .create());

        inputName.setTitle(SpannableStringUtils.getBuilder("*")
                .setForegroundColor(ContextCompat.getColor(this, R.color.color_red))
                .append("姓名")
                .create());

        inputIdNumber.setTitle(SpannableStringUtils.getBuilder("*")
                .setForegroundColor(ContextCompat.getColor(this, R.color.color_red))
                .append("身份证号")
                .create());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        certificateAdapter = new UserCertificateAdapter(R.layout.item_user_certificate, userCertificateBeans, this);
        recyclerView.setAdapter(certificateAdapter);
        certificateAdapter.addData(new UserCertificateBean());
    }

    @Override
    protected void initData() {
        OCRHelper.init(this);
        deptId = getIntent().getStringExtra("deptId");

        mAddPresenter = new MemberAddPresenter(this, ContactsModel.newInstance());
        mListPresenter = new DepartmentListPresenter(this, ContactsModel.newInstance());
        uploadPresenter = new UploadPresenter(this, CommonModel.newInstance());
        addPresenter(mAddPresenter);
        addPresenter(mListPresenter);
        addPresenter(uploadPresenter);

        mListPresenter.getRoleList(UserManager.getInstance().getProjectId());
    }

    @OnClick({R.id.tv_back, R.id.ll_other_info, R.id.btn_append_member,
            R.id.rl_show_other_info, R.id.tv_scan, R.id.input_position, R.id.input_department
            , R.id.input_gender, R.id.input_birthday, R.id.input_certificate_start_date,
            R.id.input_certificate_end_date, R.id.input_user_type, R.id.input_title,
            R.id.tv_add_certificate, R.id.img})
    public void onViewClicked(View view) {
        KeyboardUtil.closeKeyboard(this);
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.rl_show_other_info:
                if (llOtherInfo.getVisibility() == View.GONE) {
                    llOtherInfo.setVisibility(View.VISIBLE);
                    ivArrow.setImageResource(R.mipmap.ic_arrow_down);
                } else {
                    llOtherInfo.setVisibility(View.GONE);
                    ivArrow.setImageResource(R.mipmap.ic_arrow_up);
                }
                svContent.fullScroll(ScrollView.FOCUS_DOWN);
                break;
            case R.id.btn_append_member:
                if (checkData()) {
                    if (filePaths != null) {
                        uploadImage();
                    } else appendMember();
                }
                break;
            case R.id.tv_scan:
                AndPermission.with(this)
                        .runtime()
                        .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                        .onGranted(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> permissions) {
                                OCRHelper.toIdCardIdentify(MemberAppendActivity.this,
                                        CameraActivity.CONTENT_TYPE_ID_CARD_FRONT, REQUEST_CODE_CAMERA);
                            }
                        })
                        .onDenied(new Action<List<String>>() {
                            @Override
                            public void onAction(List<String> permissions) {
                                ToastUtils.showShort("身份证识别需要打开相机权限");
                            }
                        })
                        .start();
                break;
            case R.id.input_position://
                selectRole();
                break;
            case R.id.input_department:
                PickerDialogHelper.showPickerOption(this, mDeptNameList, 0, false, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        if (!mDeptNameList.isEmpty()) {
                            inputDepartment.setContent(mDeptNameList.get(options1));
                        }
                    }
                });
                break;
            case R.id.input_gender://性别
                PickerDialogHelper.showPickerOption(this, Arrays.asList(mGenders), 0, false, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        inputGender.setContent(mGenders[options1]);
                    }
                });
                break;
            case R.id.input_birthday:
                selectBirthday();
                break;
            case R.id.input_certificate_start_date:
                selectCertificateStartDate();
                break;
            case R.id.input_certificate_end_date:
                selectCertificateEndDate();
                break;
            case R.id.input_user_type:
                selectUserType();
                break;
            case R.id.input_title:
                selectTitle();
                break;
            case R.id.tv_add_certificate:
                addCertificate();
                break;
            case R.id.img:
                selectImg();
                break;
            default:
                break;
        }
    }

    private void selectRole() {
        if (roleList.isEmpty()) {
            showError("项目职务获取失败");
            return;
        }
        PickerDialogHelper.showPickerOption(this, roleList, 0, false, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String content = roleList.get(options1);
                inputPosition.setContent(content);
                roleId = mapRole.get(content);
            }
        });
    }

    private boolean checkData() {
        inputNameContent = inputName.getContent();
        if (inputNameContent.isEmpty()) {
            showError("请填写姓名");
            return false;
        }


        inputIdNumberContent = inputIdNumber.getContent();
        if (inputIdNumberContent.isEmpty()) {
            showError("请填写身份证号");
            return false;
        } else {
            String idCard = ValidationUtils.isIDCard(inputIdNumberContent);
            if (!idCard.isEmpty()) {
                showError(idCard);
                return false;
            }
        }

        String inputPositionContent = inputPosition.getContent();
        if (TextUtils.isEmpty(inputPositionContent)) {
            showError("请选择职位");
            return false;
        }

        String inputUserTypeContent = inputUserType.getContent();
        if (TextUtils.isEmpty(inputUserTypeContent)) {
            showError("请选择人员类型");
            return false;
        }


        phoneContent = inputLinkPhone.getContent();
        if (phoneContent.isEmpty()) {
            showError("请填写联系电话");
            return false;
        } else {
            boolean mobile = ValidationUtils.isMobile(phoneContent);
            if (!mobile) {
                showError("请填写正确的联系电话");
                return false;
            }
        }
        return true;
    }

    private void selectImg() {
        AndPermission.with(this)
                .runtime()
                .permission(PermissionConstant.PERMISSIONS_OF_IMAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        PhotoPickerHelper.startPicker(MemberAppendActivity.this, 0, null, 1, false, true);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        showError("用户已禁止访问图片权限");
                    }
                })
                .start();
    }

    private void setImg(ArrayList<String> filePaths) {
        this.filePaths = filePaths;
        String s = filePaths.get(0);
        GlideHelper.loadImage(this, img, s);
    }

    private void uploadImage() {
        List<File> files = new ArrayList<>();
        for (String path : filePaths) {
            files.add(new File(path));
        }
        uploadPresenter.upload(UserManager.getInstance().getProjectId(), files);
    }

    private void addCertificate() {
        certificateAdapter.addData(new UserCertificateBean());
    }

    private void selectTitle() {
        PickerDialogHelper.showPickerOption(this, Arrays.asList(titles), 0, false, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                inputTitle.setContent(titles[options1]);
                title = Arrays.asList(titleIndex).get(options1);
            }
        });
    }

    private void selectUserType() {
        PickerDialogHelper.showPickerOption(this, Arrays.asList(mUserTypes), 0, false, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                inputUserType.setContent(mUserTypes[options1]);
                userType = Arrays.asList(mUserTypeIndex).get(options1);
            }
        });
    }

    private void selectCertificateEndDate() {
        Calendar currentDate = Calendar.getInstance();
        Date date = new Date();
        currentDate.setTime(date);
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2023, 11, 20);
        PickerDialogHelper.showTimePicker(this, currentDate, startDate, endDate, false, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                inputCertificateEndDate.setContent(DateUtil.format(String.valueOf(date.getTime())));
            }
        });
    }

    private void selectCertificateStartDate() {
        Calendar currentDate = Calendar.getInstance();
        Date date = new Date();
        currentDate.setTime(date);
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2023, 11, 20);
        PickerDialogHelper.showTimePicker(this, currentDate, startDate, endDate, false, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                inputCertificateStartDate.setContent(DateUtil.format(String.valueOf(date.getTime())));
            }
        });
    }

    private void selectBirthday() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(1993, 2, 28);
        Calendar startDate = Calendar.getInstance();
        startDate.set(1949, 1, 1);
        Calendar endDate = Calendar.getInstance();
        Date date = new Date();
        endDate.setTime(date);
        PickerDialogHelper.showTimePicker(this, currentDate, startDate, endDate, false, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                inputBirthday.setContent(DateUtil.format(String.valueOf(date.getTime())));
            }
        });
    }

    /**
     * 添加成员
     */
    private void appendMember() {

        JSONArray jsonArray = new JSONArray();
        List<UserCertificateBean> data = certificateAdapter.getData();
        try {
            for (UserCertificateBean entity : data) {
                if (!entity.getId().isEmpty()) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("code", entity.getCode());
                    jsonObject.put("name", entity.getName());
                    jsonArray.put(jsonObject);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jsonArrayFile = new JSONArray();
        if (entities != null) {
            try {
                for (FileEntity fileEntity : entities) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("fileExt", fileEntity.fileExt);
                    jsonObject.put("fileId", fileEntity.fileId);
                    jsonObject.put("fileName", fileEntity.fileName);
                    jsonObject.put("fileSize", fileEntity.fileSize);
                    jsonObject.put("type", fileEntity.type);
                    jsonObject.put("projCode", fileEntity.projCode);
                    jsonObject.put("projId", fileEntity.projId);
                    jsonArrayFile.put(jsonObject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        HashMap<String, Object> params = new HashMap<>();
        params.put("projId", UserManager.getInstance().getProjectId());
        params.put("name", inputNameContent);
        params.put("phone", phoneContent);
        params.put("deptId", deptId);//部门id
        params.put("roleId", roleId);//职务id
        params.put("idcard", inputIdNumberContent);
        params.put("userType", userType);//人员类型
        params.put("userCertificateList", jsonArray);//证书
        params.put("file", jsonArrayFile);//证书

        params.put("title", title);//职务id
        mAddPresenter.addMember(params);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 0) { //图片选择
                ArrayList<String> filePaths = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                setImg(filePaths);
            } else if (requestCode == REQUEST_CODE_CAMERA) {
                OCRHelper.handleIdCardIdentify(data, new OnResultListener<IDCardResult>() {
                    @Override
                    public void onResult(IDCardResult idCardResult) {
                        setIdCardInfo(idCardResult);
                    }

                    @Override
                    public void onError(OCRError ocrError) {
                        showError("身份证识别失败");
                    }
                });
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    private void setIdCardInfo(IDCardResult idCardResult) {
        String birthday = idCardResult.getBirthday() == null ? "" : idCardResult.getBirthday().getWords();
        if (!TextUtils.isEmpty(birthday) && birthday.length() == 8) {
            int year = Integer.parseInt(birthday.substring(0, 4));
            int month = Integer.parseInt(birthday.substring(4, 6));
            int day = Integer.parseInt(birthday.substring(6, birthday.length()));
            inputBirthday.setContent(year + "-" + month + "-" + day);
            inputAge.setContent(String.valueOf(DateUtils.getAge(year, month, day)));
        }
        inputName.setContent(idCardResult.getName() == null ? "" : idCardResult.getName().getWords());
        inputGender.setContent(idCardResult.getGender() == null ? "" : idCardResult.getGender().getWords());
        inputNationality.setContent(idCardResult.getEthnic() == null ? "" : idCardResult.getEthnic().getWords());
        inputIdNumber.setContent(idCardResult.getIdNumber() == null ? "" : idCardResult.getIdNumber().getWords());
        inputAddress.setContent(idCardResult.getAddress() == null ? "" : idCardResult.getAddress().getWords());
    }

    @Override
    protected void onDestroy() {
        OCRHelper.release();
        super.onDestroy();
    }

    @Override
    public void dealAddMemberResult() {
        EventBus.getDefault().post(new RefreshEvent(getClass().getSimpleName()));
        showError("添加成功");
        setResult(RESULT_OK);

        EventBus.getDefault().post(new RefreshEvent("count", 1));
        finish();
    }

    @Override
    public void showDutyList(PageInfo<DutyListInfo> data) {

    }

    @Override
    public void dealDeleteResult() {

    }

    @Override
    public void showSearchMembers(PageInfo<QueryMemberInfo> data) {

    }


    @Override
    public void showProjectInfo(ProjectDeptInfo memberNumber) {

    }

    @Override
    public void showProjectDepartmentList(List<DepartmentListInfo> listInfo) {
        //部门选择项

    }

    @Override
    public void showBanZuDepartmentList(List<DepartmentListInfo> listInfo) {

    }

    @Override
    public void showUserDepartmentList(List<DepartmentListInfo> listInfo) {

    }

    @Override
    public void showLaborCompanyList(List<LaborCompanyBean> list) {

    }

    @Override
    public void showRoleList(RoleBean roleBean) {
        List<RoleBean.DListBean> dList = roleBean.getDList();
        for (RoleBean.DListBean dListBean : dList) {
            String name = dListBean.getName();
            roleList.add(name);
            mapRole.put(name, dListBean.getId());
        }

        List<RoleBean.TListBean> tList = roleBean.getTList();
        for (RoleBean.TListBean tListBean : tList) {
            String name = tListBean.getName();
            roleList.add(name);
            mapRole.put(name, tListBean.getId());
        }
    }

    @Override
    public void dealAddDepartmentResult() {

    }

    @Override
    public void showUploadInfo(List<FileEntity> entities) {
        for (FileEntity entity : entities) {
            entity.setProjId(UserManager.getInstance().getProjectId());
        }
        this.entities = entities;
        appendMember();
    }
}
