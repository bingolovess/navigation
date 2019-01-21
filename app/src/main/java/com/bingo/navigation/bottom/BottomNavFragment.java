package com.bingo.navigation.bottom;

import android.os.Bundle;

import com.bingo.multitab.fragment.LazyFragment;

/**
 * Created by bingo on 2019/1/20.
 * Time:2019/1/20
 */

public class BottomNavFragment extends LazyFragment {

    public static BottomNavFragment getInstance() {
        return (BottomNavFragment) get();
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        //setContentView();
    }

}
