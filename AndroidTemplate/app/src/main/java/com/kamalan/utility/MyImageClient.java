package com.kamalan.utility;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Hesam on 15/04/14
 */
public class MyImageClient {

    private static final  String TAG = "ImageDownloader";
    public static final String DIRECTORY_NAME = "DIRECTORY_NAME";

    private static MyImageClient instance = null;
    private ImageLoader imageLoader;

    protected MyImageClient(Context context) {
        // Exists only to defeat instantiation.
        configImageDownloader(context);
    }

    public static MyImageClient getInstance(Context context) {
        if(instance == null) {
            instance = new MyImageClient(context);
        }

        return instance;
    }

    /**
     * This constructor will configure loader object in order to display image.
     * @param context
     */
    private void configImageDownloader(Context context) {

        File cacheDir = StorageUtils.getOwnCacheDirectory(context, DIRECTORY_NAME + "/Cache");

        // Get singleton instance of ImageLoader
        imageLoader = ImageLoader.getInstance();

        // Create configuration for ImageLoader (all options are optional)
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(4 * 1024 * 1024))
                .discCache(new UnlimitedDiscCache(cacheDir))
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .defaultDisplayImageOptions(
                        new DisplayImageOptions.Builder()
                                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                                .build())
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .build();

        // Initialize ImageLoader with created configuration.
        imageLoader.init(config);
    }


    public void displayImage(final ImageView imageView, String imageURI) {
        if(imageView == null  ||  imageURI == null) {
            Log.e(TAG, "Either of image view or image uri is null");
            return;
        }

        // Load and display image
        imageLoader.displayImage(imageURI, imageView, new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String imageUri, View view) {}

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {}

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
//                imageView.setImageBitmap(getRoundedCornerBitmap(bitmap, 30));
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {}
        });
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}
