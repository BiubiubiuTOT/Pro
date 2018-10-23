package com.ljkj.qxn.wisdomsitepro.manager;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.weather.LocalDayWeatherForecast;
import com.amap.api.services.weather.LocalWeatherForecast;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;
import com.ljkj.qxn.wisdomsitepro.BuildConfig;
import com.ljkj.qxn.wisdomsitepro.WApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 类描述：高德地图 管理类
 * 创建人：lxx
 * 创建时间：2018/8/10
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AMapManager {

    private AMapLocationClient locationClient;
    private AMapLocationClientOption locationClientOption;

    private ArrayList<AMapLocationListener> locationListeners = new ArrayList<>();

    private AMapManager() {
        initLocationClient();
    }

    public static AMapManager getInstance() {
        return AMapManager.Holder.holder;
    }

    private static class Holder {
        private static AMapManager holder = new AMapManager();
    }

    public AMapLocationClient getLocationClient() {
        return locationClient;
    }

    public AMapLocationClientOption getLocationClientOption() {
        return locationClientOption;
    }

    private void initLocationClient() {
        locationClient = new AMapLocationClient(WApplication.getApplication()); //初始化定位

        locationClientOption = new AMapLocationClientOption();
        //设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
        ////设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
        locationClientOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        //设置定位模式为AMapLocationMode.Device_Sensors，仅设备模式:这种模式下不支持室内环境的定位
        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式:不会使用GPS和其他传感器，只会使用网络定位（Wi-Fi和基站定位）
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //默认采用连续定位模式,设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        locationClientOption.setInterval(2000);
        //设置是否返回地址信息（默认返回地址信息）
        locationClientOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为true，允许模拟位置
        locationClientOption.setMockEnable(true);
        //设置定位请求超时时间，默认为30秒,单位是毫秒，建议超时时间不要低于8000毫秒。
        //如果单次定位发生超时情况，定位随即终止；连续定位状态下当前这一次定位会返回超时，但按照既定周期的定位请求会继续发起。
        locationClientOption.setHttpTimeOut(20000);
        //设置是否开启定位缓存机制,缓存机制默认开启
        //当开启定位缓存功能，在高精度模式和低功耗模式下进行的网络定位结果均会生成本地缓存，不区分单次定位还是连续定位。GPS定位结果不会被缓存。
        locationClientOption.setLocationCacheEnable(true);

        locationClient.setLocationOption(locationClientOption);
        locationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                for (AMapLocationListener locationListener : locationListeners) {
                    locationListener.onLocationChanged(aMapLocation);
                }
                if (aMapLocation != null) {
                    log(aMapLocation.toString());
                }
                if (aMapLocation != null && false) {
                    if (aMapLocation.getErrorCode() == 0) {
                        aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        aMapLocation.getLatitude();//获取纬度
                        aMapLocation.getLongitude();//获取经度
                        aMapLocation.getAccuracy();//获取精度信息
                        aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                        aMapLocation.getCountry();//国家信息
                        aMapLocation.getProvince();//省信息
                        aMapLocation.getCity();//城市信息
                        aMapLocation.getDistrict();//城区信息
                        aMapLocation.getStreet();//街道信息
                        aMapLocation.getStreetNum();//街道门牌号信息
                        aMapLocation.getCityCode();//城市编码
                        aMapLocation.getAdCode();//地区编码
                        aMapLocation.getAoiName();//获取当前定位点的AOI信息
                        aMapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                        aMapLocation.getFloor();//获取当前室内定位的楼层
                        aMapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                        //获取定位时间
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(aMapLocation.getTime());
                        df.format(date);

                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        log("location Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation.getErrorInfo());
                    }
                }


            }
        });

    }

    /**
     * 启动定位
     */
    public void startLocation() {
        locationClient.startLocation();
    }

    /**
     * 停止定位后，本地定位服务并不会被销毁
     */
    public void stopLocation() {
        locationClient.stopLocation();
    }

    /**
     * 销毁定位客户端，同时销毁本地定位服务
     */
    public void destroyLocation() {
        locationClient.onDestroy();
    }

    /**
     * 查询天气
     *
     * @param context  context
     * @param city     查询的城市名称
     * @param type     查询的天气类型 1:实况天气 2:天气预报
     * @param listener listener
     */
    public void searchWeather(Context context, String city, int type, WeatherSearch.OnWeatherSearchListener listener) {
        //检索参数为城市和天气类型，实况天气为WEATHER_TYPE_LIVE、天气预报为WEATHER_TYPE_FORECAST
        WeatherSearchQuery query = new WeatherSearchQuery(city, type == 1 ? WeatherSearchQuery.WEATHER_TYPE_LIVE : WeatherSearchQuery.WEATHER_TYPE_FORECAST);
        WeatherSearch weatherSearch = new WeatherSearch(context);
        weatherSearch.setOnWeatherSearchListener(listener);
        weatherSearch.setQuery(query);
        weatherSearch.searchWeatherAsyn(); //异步搜索
    }


    /**
     * 逆地理编码（坐标转地址）
     *
     * @param context     context
     * @param latLonPoint 要进行逆地理编码的地理坐标点
     * @param radius      查找范围。默认值为1000，取值范围1-3000，单位米
     */
    public void reverseGeocode(Context context, LatLonPoint latLonPoint, int radius, GeocodeSearch.OnGeocodeSearchListener listener) {
        GeocodeSearch search = new GeocodeSearch(context);
        // 第三个参数表示是高德类型坐标系还是GPS原生坐标系
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, radius, GeocodeSearch.AMAP);
        search.setOnGeocodeSearchListener(listener);
        search.getFromLocationAsyn(query); //逆地理编码查询的异步处理调用
    }

    /**
     * 坐标转换(其他坐标系转到高德坐标系)
     *
     * @param context      context
     * @param sourceLatLng 待转换坐标
     * @param coordType    待转换坐标类型
     */
    public LatLng convertCoordinate(Context context, LatLng sourceLatLng, CoordinateConverter.CoordType coordType) {
        CoordinateConverter converter = new CoordinateConverter(context);
        converter.from(coordType); //待转换坐标类型
        converter.coord(sourceLatLng); //待转换坐标点
        return converter.convert();
    }


    public void addLocationListener(AMapLocationListener locationListener) {
        if (!locationListeners.contains(locationListener)) {
            locationListeners.add(locationListener);
        }
    }

    public void removeLocationListener(AMapLocationListener locationListener) {
        locationListeners.remove(locationListener);
    }

    //////////////////////////////////

    private void testGeoCode() {
        reverseGeocode(null, null, 200, new GeocodeSearch.OnGeocodeSearchListener() {
            /**
             *
             * @param regeocodeResult 逆地理编码返回的结果
             * @param resultID 返回结果成功或者失败的响应码。1000为成功(V3.2.1之后搜索成功响应码为1000，之前版本为0)，其他为失败
             */
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int resultID) {
                //根据给定的经纬度和最大结果数返回逆地理编码的结果列表。逆地理编码兴趣点返回结果最大返回数目为10，道路和交叉路口返回最大数目为3。
                if (resultID == AMapException.CODE_AMAP_SUCCESS) {
                    regeocodeResult.getRegeocodeQuery(); //返回该结果对应的查询参数
                    RegeocodeAddress address = regeocodeResult.getRegeocodeAddress(); //返回逆地理编码搜索的地理结果
                    address.getAdCode(); //逆地理编码结果所在区（县）的编码
                    address.getBuilding(); //逆地理编码返回的建筑物名称
                    address.getBusinessAreas(); //返回商圈对象列表，若服务没有相应数据，则返回列表长度为0。
                    address.getCity(); //逆地理编码返回的所在城市名称
                    address.getCityCode(); //逆地理编码结果所在城市编码
                    address.getCountry(); //获取国家名称
                    address.getCrossroads(); //逆地理编码返回的交叉路口列表
                    address.getDistrict(); //逆地理编码返回的所在区（县）名称
                    address.getFormatAddress(); //逆地理编码返回的格式化地址
                    address.getNeighborhood(); //逆地理编码返回的社区名称
                    address.getPois(); //逆地理编码返回的POI(兴趣点)列表
                    address.getProvince(); //逆地理编码返回的所在省名称、直辖市的名称
                    address.getTownship(); //逆地理编码返回的乡镇名称

                } else {
                    //失败
                }

            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int resultID) {
                //根据给定的地理名称和查询城市，返回地理编码的结果列表。地理编码返回结果集默认最大返回数目为10。
            }
        });

    }

    private void testWeatherCode(Context context) {
        searchWeather(context, "贵阳", 1, new WeatherSearch.OnWeatherSearchListener() {
            /**
             *
             * @param localWeatherLiveResult 实况天气查询的搜索结果
             * @param rCode 返回结果成功或者失败的响应码。1000为成功(V3.2.1之后搜索成功响应码为1000，之前版本为0)，其他为失败
             */
            @Override
            public void onWeatherLiveSearched(LocalWeatherLiveResult localWeatherLiveResult, int rCode) {
                //返回Weather异步处理的实况天气结果
                if (rCode == 1000) {
                    if (localWeatherLiveResult != null && localWeatherLiveResult.getLiveResult() != null) {
                        LocalWeatherLive weatherLive = localWeatherLiveResult.getLiveResult();
                        String code = weatherLive.getAdCode();//行政区划代码
                        String city = weatherLive.getCity(); //返回城市名称
                        String humidity = weatherLive.getHumidity(); // 空气湿度的百分比
                        String province = weatherLive.getProvince(); //省份名称
                        String time = weatherLive.getReportTime(); //实时数据发布时间
                        String temperature = weatherLive.getTemperature(); //实时气温，单位：摄氏度
                        String wind = weatherLive.getWindDirection(); //风向
                        String windPower = weatherLive.getWindPower(); //风力，单位：级

                    } else {
                        //对不起，没有搜索到相关数据！
                    }
                } else {
                    //失败
                }
            }

            @Override
            public void onWeatherForecastSearched(LocalWeatherForecastResult localWeatherForecastResult, int rCode) {
                //返回Weather异步处理的预报天气结果。
                if (rCode == 1000) {
                    if (localWeatherForecastResult != null && localWeatherForecastResult.getForecastResult() != null
                            && localWeatherForecastResult.getForecastResult().getWeatherForecast() != null
                            && localWeatherForecastResult.getForecastResult().getWeatherForecast().size() > 0) {

                        LocalWeatherForecast weatherForecast = localWeatherForecastResult.getForecastResult();
                        List<LocalDayWeatherForecast> forecastList = weatherForecast.getWeatherForecast();
                        String province = weatherForecast.getProvince(); //省份名称
                        weatherForecast.getReportTime();
                        weatherForecast.getCity();

                        for (LocalDayWeatherForecast day : forecastList) {
                            day.getDate(); //预报天气的年月日
                            day.getDayTemp(); //白天天气温度，单位：摄氏度
                            day.getDayWeather(); //白天天气现象，如“晴”、“多云”
                            day.getDayWindDirection(); //白天风向
                            day.getWeek(); //预报天气的星期

                            day.getNightWindPower(); //夜间风力，单位：级
                            day.getNightWindDirection(); //夜间风向
                            day.getNightWeather();//夜间天气现象，如“晴”、“多云”
                            day.getNightTemp();//夜间天气温度，单位：摄氏度
                            day.getDayWindPower(); //白天风力，单位：级
                        }

                    } else {
                        //对不起，没有搜索到相关数据！
                    }

                } else {
                    //失败
                }
            }
        });


    }

    private void log(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d("AMapManager", msg);
        }
    }

}
