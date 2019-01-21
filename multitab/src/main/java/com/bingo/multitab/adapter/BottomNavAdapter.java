package com.bingo.multitab.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bingo.multitab.R;
import com.bingo.multitab.view.indicator.IndicatorViewPager;

import java.util.List;

/**
 * Created by bingo on 2019/1/21.
 * Time:2019/1/21
 * 底部导航的适配器
 */

public class BottomNavAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
    private LayoutInflater inflater;
    private String[] tabNames;
    private int[] tabIcons;
    private List<Fragment> fragments;

    private BottomNavAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public BottomNavAdapter(Context context, FragmentManager fragmentManager, String[] tabNames, int[] tabIcons, List<Fragment> fragments) {
        this(fragmentManager);
        this.inflater = LayoutInflater.from(context.getApplicationContext());
        this.tabIcons = tabIcons;
        this.tabNames = tabNames;
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return tabNames == null ? 0 : tabNames.length;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.lib_pub_bottom_nav_item/*目标布局*/, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(tabNames[position]);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[position], 0, 0);
        return textView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        Log.e("BottomNavAdapter", "position = " + position);
        if (fragments != null && !fragments.isEmpty()) {
            return fragments.get(position);
        }
        return null;
    }
}
