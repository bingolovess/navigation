package com.bingo.multitab.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.PagerAdapter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bingo.multitab.R;
import com.bingo.multitab.view.indicator.IndicatorViewPager;

/**
 * Created by bingo on 2019/1/21.
 * Time:2019/1/21
 * 水平可滚动的tab+viewpager
 */

public class ScrollTabAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {
    private Context mContext;
    private String[] tabNames;
    private LayoutInflater inflater;

    public ScrollTabAdapter(Context context, String[] tabNames) {
        super();
        this.mContext = context;
        this.tabNames = tabNames;
        this.inflater = LayoutInflater.from(context.getApplicationContext());
    }

    @Override
    public int getCount() {
        return tabNames == null ? 0 : tabNames.length;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.lib_pub_tab_item, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(tabNames[position]);
        int witdh = getTextWidth(textView);
        int padding = dipToPix(mContext, 8);
        //因为wrap的布局 字体大小变化会导致textView大小变化产生抖动，这里通过设置textView宽度就避免抖动现象
        //1.3f是根据上面字体大小变化的倍数1.3f设置
        textView.setWidth((int) (witdh * 1.3f) + padding);
        return convertView;
    }

    @Override
    public View getViewForPage(int position, View convertView, ViewGroup container) {
        return convertView;
    }

    @Override
    public int getItemPosition(Object object) {
        //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
        // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
        return PagerAdapter.POSITION_UNCHANGED;
    }

    private int getTextWidth(TextView textView) {
        if (textView == null) {
            return 0;
        }
        Rect bounds = new Rect();
        String text = textView.getText().toString();
        Paint paint = textView.getPaint();
        paint.getTextBounds(text, 0, text.length(), bounds);
        int width = bounds.left + bounds.width();
        return width;
    }

    /**
     * 根据dip值转化成px值
     *
     * @param context
     * @param dip
     * @return
     */
    public int dipToPix(Context context, int dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
        return size;
    }
}
