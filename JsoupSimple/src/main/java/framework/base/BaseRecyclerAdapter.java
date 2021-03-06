package framework.base;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * by y on 2016/9/13
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public OnItemClickListener<T> mOnItemClickListener;
    public OnItemLongClickListener<T> mOnLongClickListener;
    public List<T> mDatas = new LinkedList<>();

    public BaseRecyclerAdapter(List<T> mDatas) {
        if (null != mDatas) {
            this.mDatas = mDatas;
        }
    }

    public View getView(ViewGroup viewGroup, int id) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(id, viewGroup, false);
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, int position, T info);
    }

    public interface OnItemLongClickListener<T> {
        void onLongClick(View view, int position, T info);
    }


    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnLongClickListener(OnItemLongClickListener<T> listener) {
        this.mOnLongClickListener = listener;
    }

    public void addAll(List<T> datas) {
        mDatas.addAll(datas);
        this.notifyDataSetChanged();
    }

    public void remove(int position) {
        mDatas.remove(position);
        this.notifyDataSetChanged();
    }

    public void removeAll() {
        if (mDatas.size() != 0) {
            mDatas.clear();
        }
        this.notifyDataSetChanged();
    }

    public int getDataCount() {
        if (null != mDatas) {
            return mDatas.size();
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SuperViewHolder(getView(parent, getLayoutId()));
    }

    protected abstract int getLayoutId();

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        onBind((SuperViewHolder) holder, position, mDatas.get(position));
        final T data = mDatas.get(position);
        if (null != mOnItemClickListener) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(view, position, data);
                }
            });
        }
        if (null != mOnLongClickListener) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnLongClickListener.onLongClick(view, position, data);
                    return true;
                }
            });
        }
    }

    protected abstract void onBind(SuperViewHolder viewHolder, int position, T mDatas);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

}