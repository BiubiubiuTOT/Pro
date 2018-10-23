package cdsp.android.glide;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

/**
 * 类描述：GlideHelper
 * 创建人：lxx
 * 创建时间：2018/5/24 13:57
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class GlideHelper {
    private static int placeholderResId = -1;
    private static int errorResId = -1;

    public static void init(@DrawableRes int placeholder, @DrawableRes int error) {
        placeholderResId = placeholder;
        errorResId = error;
    }

    public static void loadImage(Context context, ImageView imageView, String url) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderResId)
                .error(errorResId);
        load(context, url, imageView, options);
    }

    public static void loadImageNoneDisk(Context context, ImageView imageView, String url) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(placeholderResId)
                .error(errorResId);
        load(context, url, imageView, options);
    }

    public static void loadImageNoneCache(Context context, ImageView imageView, String url) {
        RequestOptions options = new RequestOptions()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(placeholderResId)
                .error(errorResId);
        load(context, url, imageView, options);
    }

    public static void loadCircleImage(Context context, ImageView imageView, String url, @DrawableRes int placeholderResId) {
        RequestOptions options = new RequestOptions()
                .circleCrop()
                .placeholder(placeholderResId);
        load(context, url, imageView, options);
    }

    public static void loadRadiusImage(Context context, ImageView imageView, String url, int radius, @DrawableRes int placeholderResId) {
        RequestOptions options = new RequestOptions()
                .transform(new GlideRoundTransform(radius))
                .placeholder(placeholderResId);
        load(context, url, imageView, options);
    }

    public static void loadImage(Context context, ImageView imageView, File fileImage) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderResId)
                .error(errorResId);
        load(context, fileImage, imageView, options);
    }

    public static void loadImage(Context context, ImageView imageView, String url, @DrawableRes int placeholderResId, @DrawableRes int errorResId) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholderResId)
                .error(errorResId);
        load(context, url, imageView, options);
    }

    private static void load(Context context, String url, ImageView imageView, RequestOptions options) {
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }

    private static void load(Context context, File fileImage, ImageView imageView, RequestOptions options) {
        Glide.with(context)
                .load(fileImage)
                .apply(options)
                .into(imageView);
    }

}
