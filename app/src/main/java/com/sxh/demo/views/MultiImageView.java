package com.sxh.demo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sxh.demo.R;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName MultiImageView.java
 * @Description: 显示1~N张图片的View
 */

public class MultiImageView extends LinearLayout {
    public  int MAX_WIDTH = 0;
    // 照片的Url列表
    private List<String> imagesList=new ArrayList<>();
    private int pxOneMaxWandH;  // 单张图最大允许宽高
    private int pxMoreWandH = 0;// 多张图的宽高
    private int pxImagePadding = 10;// 图片间的间距
    private int MAX_PER_ROW_COUNT = 3;// 每行显示最大数
    private LayoutParams morePara, moreParaColumnFirst;
    private LayoutParams rowPara;
    private OnItemClickListener mOnItemClickListener;
    private int mWidth;//后台返回的宽高
    private int mHeight;
    private Context mContext;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public MultiImageView(Context context) {
        super(context);
        mContext=context;
    }

    public MultiImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
    }

    public void setList(List<String> lists) throws IllegalArgumentException {
        if (lists == null) {
            throw new IllegalArgumentException("imageList is null...");
        }
        imagesList = lists;
        imageViews = new ImageView[imagesList.size()];
        if (MAX_WIDTH > 0) {
            pxMoreWandH = (MAX_WIDTH - pxImagePadding * 2) / 3; //解决右侧图片和内容对不齐问题
            pxOneMaxWandH = MAX_WIDTH * 2 / 3;
            initImageLayoutParams();
        }
        initView();
    }

    public void setList(List<String> lists, int width, int height) throws IllegalArgumentException {
        if (lists == null) {
            throw new IllegalArgumentException("imageList is null...");
        }
        imagesList = lists;
        this.mWidth = width;
        this.mHeight = height;
        imageViews = new ImageView[imagesList.size()];
        if (MAX_WIDTH > 0) {
            pxMoreWandH = (MAX_WIDTH - pxImagePadding * 2) / 3; //解决右侧图片和内容对不齐问题
            pxOneMaxWandH = MAX_WIDTH * 2 / 3;
            initImageLayoutParams();
        }
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MAX_WIDTH == 0) {
            int width = measureWidth(widthMeasureSpec)-50;
            if (width > 0) {
                MAX_WIDTH = width;
                if (imagesList != null && imagesList.size() > 0) {
                    setList(imagesList);
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * Determines the width of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            // Respect AT_MOST value if that was what is called for by
            // measureSpec
            result = Math.min(result, specSize);
        }
        return result;
    }

    private void initImageLayoutParams() {
        int wrap = LayoutParams.WRAP_CONTENT;
        int match = LayoutParams.MATCH_PARENT;

        moreParaColumnFirst = new LayoutParams(pxMoreWandH, pxMoreWandH);
        morePara = new LayoutParams(pxMoreWandH, pxMoreWandH);
        morePara.setMargins(pxImagePadding, 0, 0, 0);

        rowPara = new LayoutParams(match, wrap);
    }

    // 根据imageView的数量初始化不同的View布局,还要为每一个View作点击效果
    private void initView() {
        this.setOrientation(VERTICAL);
        this.removeAllViews();
        if (MAX_WIDTH == 0) {
            //为了触发onMeasure()来测量MultiImageView的最大宽度，MultiImageView的宽设置为match_parent
            addView(new View(mContext));
            return;
        }
        if (imagesList == null || imagesList.size() == 0) {
            return;
        }
        if (imagesList.size() == 1) {
            addView(createImageView(0, false));
        } else {
            int allCount = imagesList.size();
            if (allCount == 4) {
                MAX_PER_ROW_COUNT = 2;
            } else {
                MAX_PER_ROW_COUNT = 3;
            }
            int rowCount = allCount / MAX_PER_ROW_COUNT
                    + (allCount % MAX_PER_ROW_COUNT > 0 ? 1 : 0);// 行数
            for (int rowCursor = 0; rowCursor < rowCount; rowCursor++) {
                LinearLayout rowLayout = new LinearLayout(mContext);
                rowLayout.setOrientation(LinearLayout.HORIZONTAL);

                rowLayout.setLayoutParams(rowPara);
                if (rowCursor != 0) {
                    rowLayout.setPadding(0, pxImagePadding, 0, 0);
                }

                int columnCount = allCount % MAX_PER_ROW_COUNT == 0 ? MAX_PER_ROW_COUNT
                        : allCount % MAX_PER_ROW_COUNT;//每行的列数
                if (rowCursor != rowCount - 1) {
                    columnCount = MAX_PER_ROW_COUNT;
                }
                addView(rowLayout);

                int rowOffset = rowCursor * MAX_PER_ROW_COUNT;// 行偏移
                for (int columnCursor = 0; columnCursor < columnCount; columnCursor++) {
                    int position = columnCursor + rowOffset;
                    rowLayout.addView(createImageView(position, true));
                }
            }
        }
    }

    private ImageView[] imageViews;

    private ImageView createImageView(int position, final boolean isMultiImage) {
        final String urlStr = imagesList.get(position);
        final ImageView imageView = new ImageView(mContext);
        imageViews[position] = imageView;
        RequestOptions options = new RequestOptions();
        if (isMultiImage) {
            //多图
            imageView.setScaleType(ScaleType.CENTER_CROP);
            imageView.setLayoutParams(position % MAX_PER_ROW_COUNT == 0 ? moreParaColumnFirst : morePara);
            if (urlStr!=null) {
                imageView.setId(urlStr.hashCode());
            }
            imageView.setOnClickListener(new ImageOnClickListener(position));
            options.placeholder(R.mipmap.zhan_one);
            options.error(R.mipmap.zhan_one);
            Glide.with(mContext).load(urlStr).apply(options).into(imageView);
        } else {
            //单图
            imageView.setAdjustViewBounds(true);
            imageView.setLayoutParams(new LayoutParams(pxOneMaxWandH, pxOneMaxWandH));
            if (urlStr!=null) {
                imageView.setOnClickListener(new ImageOnClickListener(position));
                if (mWidth != 0 && mHeight != 0) {
                    if (!urlStr.equals(imageView.getTag(R.id.image_key))) {
                        autoSizeImageView(imageView,options,urlStr);
                    }
                } else {
                    imageView.setBackgroundColor(0xffffffff);
                    if (!urlStr.equals(imageView.getTag(R.id.image_key))) {
                        imageView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        imageView.setMaxWidth(pxOneMaxWandH);
                        imageView.setMaxHeight(pxOneMaxWandH);
                        imageView.setTag(null);
                        options.placeholder(R.mipmap.zhan_one);
                        options.error(R.mipmap.zhan_one);
                        Glide.with(mContext).load(urlStr).apply(options).into(imageView);
                        imageView.setTag(R.id.image_key, urlStr);
                    }
                }
            }
        }
        return imageView;
    }

    /**
     * 根据宽度自动调整(参照图片本身尺寸)<br/>
     * 需要开启 android:adjustViewBounds="true"
     * widths 和heights是图片返回后获取的宽高
     */
    public void autoSizeImageView(ImageView imageView, RequestOptions options, String urlStr) {
        //获取图片真正的宽高
        int width = mWidth;
        int height = mHeight;
        int centerWandH = pxOneMaxWandH*2 / 3;
        if (width!=0 && height!=0){
            float dgress= (float) (width / (height*1.0));
            Log.e("pic",dgress+"");
            if ((0.9 <= dgress) && ( dgress<= 1.2)){
                options.placeholder(R.mipmap.zhan_one);
                options.error(R.mipmap.zhan_one);
                imageView.setLayoutParams(new LayoutParams(pxOneMaxWandH-(pxImagePadding*2),pxOneMaxWandH-(pxImagePadding*2)));
                imageView.setMaxWidth(pxOneMaxWandH-(pxImagePadding*2));
                imageView.setMaxHeight(pxOneMaxWandH-(pxImagePadding*2));
                imageView.setScaleType(ScaleType.CENTER);

            }else if ((0.5 <= dgress) && ( dgress< 0.9)){
                options.placeholder(R.mipmap.zhan_two);
                options.error(R.mipmap.zhan_two);
                imageView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                imageView.setMaxWidth(pxOneMaxWandH-(pxImagePadding*2));
                imageView.setMaxHeight(MAX_WIDTH-(pxImagePadding*2));
                imageView.setScaleType(ScaleType.FIT_START);

            }else if ((1.2 <= dgress) && ( dgress< 3)){
                options.placeholder(R.mipmap.zhan_three);
                options.error(R.mipmap.zhan_three);
                imageView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                imageView.setMaxWidth(MAX_WIDTH-(pxImagePadding*2));
                imageView.setMaxHeight(pxOneMaxWandH-(pxImagePadding*2));
                imageView.setScaleType(ScaleType.FIT_START);

            }else  if (dgress > 3){
                options.placeholder(R.mipmap.zhan_three);
                options.error(R.mipmap.zhan_three);
                imageView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, pxOneMaxWandH/2));
                imageView.setMaxWidth(MAX_WIDTH);
                imageView.setMaxHeight(pxOneMaxWandH/2);
                imageView.setScaleType(ScaleType.MATRIX);
            }else if (dgress < 0.5){
                options.placeholder(R.mipmap.zhan_two);
                options.error(R.mipmap.zhan_two);
                imageView.setLayoutParams(new LayoutParams((pxOneMaxWandH/2+(pxImagePadding*2)),  MAX_WIDTH));
                imageView.setMaxWidth(pxOneMaxWandH-(pxImagePadding*2));
                imageView.setMaxHeight(MAX_WIDTH);
                imageView.setScaleType(ScaleType.CENTER_CROP);
            }

        }
        imageView.setBackgroundColor(0xffffffff);
        imageView.setTag(null);
        Glide.with(mContext)
                .load(urlStr)
                .apply(options)
                .into(imageView);
        imageView.setTag(R.id.image_key, urlStr);
        Log.e("pic",pxOneMaxWandH+"--"+width+"--"+height+"--"+centerWandH+"--"+MAX_WIDTH+"--"+(width/(height*1.0)));

    }

    private class ImageOnClickListener implements OnClickListener {

        private int position;

        public ImageOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, position, imageViews);
            }
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position, ImageView[] imageViews);
    }
}