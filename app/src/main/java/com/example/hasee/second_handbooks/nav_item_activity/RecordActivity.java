package com.example.hasee.second_handbooks.nav_item_activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.hasee.second_handbooks.LoginActivity;
import com.example.hasee.second_handbooks.MyneedsMessageAdapter;
import com.example.hasee.second_handbooks.R;
import com.example.hasee.second_handbooks.UserActivity;
import com.example.hasee.second_handbooks.db.ExchangeMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecordActivity extends AppCompatActivity {

    private RecordAdapter adapter;

    private ExchangeMessage[] Messages = {
            new ExchangeMessage("第一行代码","11月09日11点",
                    "旭日楼","本人吴，该书作者郭霖，570页，第二版，学编程安卓入门的第一书"),
            new ExchangeMessage("了不起的盖兹比","11月10日17点",
                    "少康楼","作者菲兹杰拉德，238页，很新的，我还没看过，可能适合小孩看的吧"),
            new ExchangeMessage("瓦尔登湖","11月11日20点",
                    "南苑宿舍","作者大卫-梭罗，280页，语语惊人，字字闪光，动我衷肠。到了夜深人静的时候，万籁俱寂之时，此书清澄见底。有多少人懂梭罗，就有多少人懂生活"),
    };

    private List<ExchangeMessage> exchangeMessagesList = new ArrayList<>();


    private SwipeRefreshLayout swipeRefreshLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        Toolbar toolbar = (Toolbar)findViewById(R.id.record_toolbar);
        setSupportActionBar(toolbar);//获得ToolBar实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);//菜单，默认图片返回图片
        }

        //初始化数据
        initMessages();

        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.record_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(layoutManager);
        adapter = new RecordAdapter(exchangeMessagesList);
        recyclerView1.setAdapter(adapter);

        //刷新
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.record_swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);//进度条颜色
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshMessages();
            }
        });
    }




    private void initMessages(){//随机存入数据
        exchangeMessagesList.clear();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int index = random.nextInt(Messages.length);
            exchangeMessagesList.add(Messages[index]);
        }
    }


    //刷新数据
    private void refreshMessages(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);//停留2秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {//切换为主线程
                    @Override
                    public void run() {
                        initMessages();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home://点击了返回，结束该活动，返回上一个活动
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

