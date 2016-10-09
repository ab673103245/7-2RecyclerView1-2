package qianfeng.a7_2recyclerview1_2;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class MyDividerItemDecoration extends RecyclerView.ItemDecoration {

    private int space;



    public MyDividerItemDecoration(int space) {
        this.space = space;
    }

    // 绘制分隔线，分隔线和item在同一平面
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {


        super.onDraw(c, parent, state);
    }

    //绘制分隔线，分隔线和item不在同一平面, 如果超过了指定的间隔距离，这是会覆盖掉原来的item的分割线画法
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    // outRect:是你item所在的矩形
    // view: 是你item的控件的view
    // parent:就是父容器recyclerView
    // state:RecyclerView的状态，是滑动还是静止
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        super.getItemOffsets(outRect, view, parent, state);

        int childLayoutPosition = parent.getChildLayoutPosition(view);

        if(childLayoutPosition == 0 || childLayoutPosition == 1 || childLayoutPosition == 2)
        {
            outRect.top = space;
        }else
        {
            outRect.top = 0;
        }
        outRect.bottom = space;
//        outRect.top = space;
//        outRect.left = space;
        outRect.right = space;

        if(childLayoutPosition % 3 == 0)
        {
            outRect.left = space;
        }else
        {
            outRect.left = 0;
        }






    }
}
