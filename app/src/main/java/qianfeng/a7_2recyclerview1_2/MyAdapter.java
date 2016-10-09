package qianfeng.a7_2recyclerview1_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class MyAdapter extends RecyclerView.Adapter {

    private Context context;
    private LayoutInflater inflater;
    private List<User> list;

    public MyAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    // 创建holder，相当于ListView中getView的if(){}else{}
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        switch (){ 实现多种item的布局 }

        // 它里面会自己进行ListView中的if(){}else{}的判断
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    // 给holder中的控件赋值
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // 记得强转啊！卧槽
        MyHolder myHolder = (MyHolder) holder;
        User user = list.get(position);
        myHolder.iv.setImageResource(user.getUserface());
        myHolder.tv.setText(user.getUsername());
    }

//    @Override  实现多种item的布局
//    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv;

        public MyHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            tv = (TextView) itemView.findViewById(R.id.tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null)
                    {
                        onItemClickListener.onItemClick(getLayoutPosition());// getLayoutPosition():得到布局里面真实的position,直接得到的positon是只会在adapter刷新之后，第一次获取的时候才是有效的，往后你的增删操作的positon就会变得不准确。
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(onItemClickListener != null)
                    {
                        onItemClickListener.onItemLongClick( getLayoutPosition() ) ;// 得到最真实的position
                    }
                    return true;
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onItemLongClick(int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

}
