package qianfeng.a7_2recyclerview1_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<User> list;
    private MyAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initData();

        // 流式布局看起来好像GridView，实际上每个item的大小是可以不同的，而GridView的item大小必须相同。

        adapter = new MyAdapter(this, list);

        // 有三种布局管理器可以设置，分别是线性布局管理器(ListView),网格布局管理器(GridView),流式布局管理器(每个item大小可以不同)

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));// ListView
        // 第二个参数是指定排列的方向，第三个参数是是否反转,false:不反转
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));//ListView的水平排列(水平滚动)


//        recyclerView.setLayoutManager(new GridLayoutManager(this,3));// GridView，每列3个item ，第二个参数表示有多少行或者多少列(具体看你指定的方向是水平还是垂直)

//        recyclerView.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.HORIZONTAL,false));// 表示每行3个item，不反转

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));// 流式布局,水平方向滚动，有三行

        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) { // 这个position实际上是会调用adapter.getLayoutPosition()获取的,注意接口里面实现的方法的实参,
                                            // 接口里面的实参getLayoutPosition传递到这里的形参上
                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int position) {
                list.remove(position);
                // 只会刷新被删除的那个position及之后的位置的数据
                adapter.notifyItemRemoved(position); // 只会局部刷新，效率更高。而不是将画面由头到尾重新刷新一遍
            }
        });


        // 如何在recyclerView中引入分割线？有一个分隔线类：ItemDecoration，自定义的分隔线要继承自它
        recyclerView.addItemDecoration(new MyDividerItemDecoration(4));





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add_item:
                list.add(0,new User(R.mipmap.ic_launcher,"王五"));
                adapter.notifyItemInserted(0); // 只更新 0 position 上的数据, 局部更新，增加数据。另外修改原有item的数据是notifyItemChanged()
                //还要使recylcerview滚动到 0 position的位置上
                recyclerView.scrollToPosition(0);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu); // 加载一个菜单布局
        return super.onCreateOptionsMenu(menu);
    }

    private void initData() {

        list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            list.add(new User(R.mipmap.ic_launcher, "张三" + i));
        }


    }



}
