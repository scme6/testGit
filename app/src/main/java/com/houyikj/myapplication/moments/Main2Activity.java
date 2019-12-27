package com.houyikj.myapplication.moments;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.houyikj.myapplication.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Main2Activity extends AppCompatActivity {

    private TextView emptyTextView;
    private RecyclerView recyclerView;
    private TextView title;
    private RelativeLayout momentsTitleLayout;
    private RelativeLayout momentsInclude;
    private List<MomentBean.ResultBean> strings = null;
    private MomentAdapter momentApdater = null;
    private MomentBean bean = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_main2);
        initView();
        setData();
    }

    private void initView() {
        emptyTextView = findViewById(R.id.emptyTextView);
        recyclerView = findViewById(R.id.recyclerView);
        title = findViewById(R.id.title);
        momentsInclude = findViewById(R.id.momentsInclude);
        momentsTitleLayout = findViewById(R.id.momentsTitleLayout);
    }

    private void setData() {
        strings = new ArrayList<>();
        getJson();
        bean = new Gson().fromJson(getJson(), MomentBean.class);
        strings.addAll(bean.getResult());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        momentApdater = new MomentAdapter(strings, this, Main2Activity.this);
        recyclerView.setAdapter(momentApdater);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    Log.e("result", dy + ">");
                } else Log.e("result", dy + "<");
                Log.e("result", dy + "---");

            }
        });
    }

    private String getJson() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = this.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open("moments.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
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


    private class MomentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<MomentBean.ResultBean> strings = null;
        private Context context = null;
        private AppCompatActivity appCompatActivity;

        public MomentAdapter(List<MomentBean.ResultBean> strings, Context context, AppCompatActivity appCompatActivity) {
            this.strings = strings;
            this.context = context;
            this.appCompatActivity = appCompatActivity;
        }


        @Override
        public int getItemViewType(int position) {
            switch (position) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                default:
                    return 2;
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == 0)
                return new MomentViewHolder2(LayoutInflater.from(context).inflate(R.layout.include_head, parent, false));
            else
                return new MomentViewHolder(LayoutInflater.from(context).inflate(R.layout.item_moments, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MomentViewHolder) {
                MomentViewHolder holder1 = (MomentViewHolder) holder;
                holder1.setData(position, strings.get(position - 1));
            } else {
                MomentViewHolder2 holder2 = (MomentViewHolder2) holder;
                holder2.logic();
            }
        }

        @Override
        public int getItemCount() {
            return strings.size() + 1;
        }

        private class MomentViewHolder extends BaseMomentViewHolder {

            private ImageView itemMomentHeadPortrait;
            private TextView itemMomentName;
            private TextView itemMomentContent;
            private RecyclerView itemMomentRecyclerView;
            private TextView itemMomentTime;
            private RelativeLayout itemMomentMore;
            private LinearLayout itemMomentThumbs;

            public MomentViewHolder(@NonNull View itemView) {
                super(itemView);
                itemMomentHeadPortrait = itemView.findViewById(R.id.itemMomentHeadPortrait);
                itemMomentName = itemView.findViewById(R.id.itemMomentName);
                itemMomentContent = itemView.findViewById(R.id.itemMomentContent);
                itemMomentRecyclerView = itemView.findViewById(R.id.itemMomentRecyclerView);
                itemMomentTime = itemView.findViewById(R.id.itemMomentTime);
                itemMomentMore = itemView.findViewById(R.id.itemMomentMore);
                itemMomentThumbs = itemView.findViewById(R.id.itemMomentThumbs);
                itemMomentMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ExpandAnimation animation = new ExpandAnimation(itemMomentThumbs, 300);
                        animation.setAnimationListener(new MyAnimationListener());
                        itemMomentThumbs.startAnimation(animation);

                    }
                });

            }

            @Override
            public void logic(int position, MomentBean.ResultBean resultBean) {
                Glide.with(context).load(resultBean.getHead()).into(itemMomentHeadPortrait);
                itemMomentName.setText(resultBean.getName());
                if (resultBean.getContent().isEmpty()) itemMomentContent.setVisibility(View.GONE);
                else itemMomentContent.setText(resultBean.getContent());
                itemMomentTime.setText(resultBean.getTime());
                if (resultBean.getImage().size() == 1)
                    itemMomentRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                else if (resultBean.getImage().size() == 2 || resultBean.getImage().size() == 4)
                    itemMomentRecyclerView.setLayoutManager(new GridLayoutManager(context, 2));
                else if (resultBean.getImage().size() >= 5 || resultBean.getImage().size() == 3)
                    itemMomentRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                else if (resultBean.getImage().size() == 0)
                    itemMomentRecyclerView.setVisibility(View.GONE);
                ImageAdapter adapter = new ImageAdapter(context, resultBean, appCompatActivity);
                itemMomentRecyclerView.setAdapter(adapter);
            }
        }

        private class MomentViewHolder2 extends RecyclerView.ViewHolder {
            private AppCompatImageView momentsImage;

            public MomentViewHolder2(@NonNull View itemView) {
                super(itemView);
                momentsImage = itemView.findViewById(R.id.momentsImage);

            }

            public void logic() {
                if (bean != null)
                    Glide.with(context).load(bean.getImg()).into(momentsImage);
            }

        }


    }

    class MyAnimationListener implements Animation.AnimationListener {

        private int position;


        public MyAnimationListener(int position) {
            this.position = position;
        }

        public MyAnimationListener() {
        }

        @Override
        public void onAnimationStart(Animation arg0) {

        }

        @Override
        public void onAnimationRepeat(Animation arg0) {

        }

        @Override
        public void onAnimationEnd(Animation arg0) {

        }
    }

    private static class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

        private Context context;
        private MomentBean.ResultBean list;
        private AppCompatActivity appCompatActivity;

        public ImageAdapter(Context context, MomentBean.ResultBean list, AppCompatActivity appCompatActivity) {
            this.context = context;
            this.list = list;
            this.appCompatActivity = appCompatActivity;
        }

        @Override
        public int getItemViewType(int position) {

            return list.getImage().size();
        }

        @NonNull
        @Override
        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == 1)
                return new ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_moments_img2, parent, false));
            else
                return new ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_moments_img, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ImageViewHolder holder, final int position) {
            holder.setData(position, list);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it = new Intent(context, DetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("url", (ArrayList<String>) list.getImage());
                    bundle.putInt("position", position);
                    it.putExtra("bundle", bundle);
                    context.startActivity(it,
                            ActivityOptions.makeSceneTransitionAnimation(appCompatActivity, view, "btn1").toBundle());
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.getImage().size();
        }

        class ImageViewHolder extends BaseMomentViewHolder {

            private AppCompatImageView itemMomentImg;

            public ImageViewHolder(@NonNull View itemView) {
                super(itemView);
                itemMomentImg = (AppCompatImageView) itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(context, DetailsActivity.class),
                                ActivityOptions.makeSceneTransitionAnimation(appCompatActivity, view, "btn1").toBundle());
                    }
                });
            }

            @Override
            public void logic(int position, MomentBean.ResultBean resultBean) {
                Glide.with(context).load(resultBean.getImage().get(position)).into(itemMomentImg);
            }
        }
    }
}
