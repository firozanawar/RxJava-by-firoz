package com.firozanwar.rxjavabyfiroz.view.list;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.firozanwar.rxjavabyfiroz.R;
import com.firozanwar.rxjavabyfiroz.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends BaseFragment {

    @Override
    protected int layoutRes() {
        return R.layout.screen_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }
}
