package cdsp.android.banner;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import cdsp.android.banner.holder.Holder;


/**
 * 类描述：本地图片例子
 * 创建人：毛华磊
 * 创建时间：2017/3/4 13:45
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class LocalImageHolderView implements Holder<Integer> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        imageView.setImageResource(data);
    }
}
