package com.houyikj.myapplication.moments;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.houyikj.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatImageView detailsImage;
    private ViewPager detailsViewPager;
    private int positions;
    private List<String> strings = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_details);
        initView();
        setData();
    }


    private void initView() {
        detailsViewPager = findViewById(R.id.detailsViewPager);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        strings.addAll(bundle.getStringArrayList("url"));
        positions = bundle.getInt("position");
    }

    private void setData() {
        detailsViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        detailsViewPager.setCurrentItem(positions);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ImageFragment fragment = new ImageFragment();
            Bundle bd = new Bundle();
            bd.putString("url", strings.get(position));
            bd.putInt("position",positions);
            fragment.setArguments(bd);
            return fragment;
        }

        @Override
        public int getCount() {
            return strings.size();
        }
    }

    //只透明状态栏
    private void hideStatusBar() {
        //得到当前界面的装饰视图
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            //设置让应用主题内容占据状态栏和导航栏
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //设置状态栏和导航栏颜色为透明
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
    }


    public void finishs() {
        if (!isFinishing())
            super.onBackPressed();
    }


    @Override
    public void onClick(View view) {
        finish();
    }
}
