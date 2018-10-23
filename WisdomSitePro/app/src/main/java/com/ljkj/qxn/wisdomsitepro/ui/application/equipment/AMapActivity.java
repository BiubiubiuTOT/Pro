package com.ljkj.qxn.wisdomsitepro.ui.application.equipment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;
import com.ljkj.qxn.wisdomsitepro.R;
import com.ljkj.qxn.wisdomsitepro.manager.AMapManager;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cdsp.android.ui.base.BaseActivity;
import cdsp.android.util.ToastUtils;

/**
 * 类描述：高德地图
 * 创建人：lxx
 * 创建时间：2018/8/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AMapActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView titleText;

    @BindView(R.id.map_view)
    MapView mapView;

    private AMap aMap;

    private static String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    public static void startActivity(final Context context) {
        AndPermission.with(context)
                .runtime()
                .permission(needPermissions)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        Intent intent = new Intent(context, AMapActivity.class);
                        context.startActivity(intent);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        ToastUtils.showShort("缺少相关权限");
                    }
                })
                .start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amap);
        mapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        init();
    }

    private void init() {
        titleText.setText("高德地图test");

        setupMap();
        setupLocationStyle();

        test();
    }

    private void test() {
        AMapManager.getInstance().startLocation();

    }

    private void setupMap() {
        aMap.setMapType(AMap.MAP_TYPE_NORMAL); //设置地图图层：白昼地图（即普通地图）
        setupMapUI();
        aMap.setMyLocationEnabled(true); // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        //设置SDK 自带定位消息监听
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                Log.e("lxx", location.toString());

                MyLocationStyle myLocationStyle = aMap.getMyLocationStyle();
                if (myLocationStyle != null && myLocationStyle.getMyLocationType() != MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER) {
                    myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER); //连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。
                    aMap.setMyLocationStyle(myLocationStyle);
                }
            }
        });

    }

    private void setupMapUI() {
        aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
        aMap.getUiSettings().setZoomControlsEnabled(true); //是否允许显示缩放按钮
        aMap.getUiSettings().setCompassEnabled(true); //指南针用于向 App 端用户展示地图方向，默认不显示
        aMap.getUiSettings().setScaleControlsEnabled(true); //控制比例尺控件是否显示
        aMap.getUiSettings().setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT); //高德地图的 logo 默认在左下角显示，不可以移除

        aMap.getUiSettings().setZoomGesturesEnabled(true); //缩放手势
        aMap.getUiSettings().setScrollGesturesEnabled(true); //滑动手势
        aMap.getUiSettings().setRotateGesturesEnabled(true); //旋转手势
        aMap.getUiSettings().setTiltGesturesEnabled(true); //倾斜手势
//        aMap.getUiSettings().setAllGesturesEnabled(true); //所有手势
    }

    //设置定位点(定位蓝点指的是进入地图后显示当前位置点的功能。自Android 3D地图 SDK 5.0.0版本之后定位蓝点实现无需依赖 Android 定位 SDK)
    private void setupLocationStyle() {
        MyLocationStyle myLocationStyle = new MyLocationStyle(); // 自定义系统定位蓝点
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.gps_point)); // 自定义定位蓝点图标
        myLocationStyle.strokeColor(Color.parseColor("#B40391FF")); // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeWidth(5); //自定义精度范围的圆形边框宽度
        myLocationStyle.radiusFillColor(Color.parseColor("#0A0000B4")); // 设置圆形的填充颜色
        myLocationStyle.showMyLocation(true); ////设置是否显示定位小蓝点，用于满足只想使用定位，不想使用定位小蓝点的场景，设置false以后图面上不再有定位蓝点的概念，但是会持续回调位置信息。

        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE); //连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（默认1秒1次定位）默认执行此种模式。
        aMap.setMyLocationStyle(myLocationStyle); // 将自定义的 myLocationStyle 对象添加到地图上
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @OnClick({R.id.tv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            default:
                break;
        }
    }


    //设置希望展示的地图缩放级别
    //地图的缩放级别一共分为 17 级，从 3 到 19。数字越大，展示的图面信息越精细
    private void zoomTo(@IntRange(from = 3, to = 19) int level) {
        aMap.moveCamera(CameraUpdateFactory.zoomTo(level));
    }

    @Override
    @Deprecated
    protected void initUI() {
        //do nothing here
    }

    @Override
    @Deprecated
    protected void initData() {
        //do nothing here
    }
}
