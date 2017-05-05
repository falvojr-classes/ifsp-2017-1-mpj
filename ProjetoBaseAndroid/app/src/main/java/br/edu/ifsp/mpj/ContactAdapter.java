package br.edu.ifsp.mpj;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(String phone);
    }

    private final String[] mDataSet;
    private OnItemClickListener mOnItemClickListener;

    ContactAdapter(String[] dataSet, OnItemClickListener onItemClickListener) {
        mDataSet = dataSet;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String phone = mDataSet[position];
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(phone);
            }
        });
        holder.mTextView.setText(phone);
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(android.R.id.text1);
        }
    }
}
