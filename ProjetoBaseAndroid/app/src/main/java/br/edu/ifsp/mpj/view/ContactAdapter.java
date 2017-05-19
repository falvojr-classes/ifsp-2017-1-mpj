package br.edu.ifsp.mpj.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.List;

import br.edu.ifsp.mpj.R;
import br.edu.ifsp.mpj.entity.Contact;

class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onCallClick(Contact contact);
        void onEditClick(Contact contact);
        void onMapsClick(Contact contact);
    }

    private List<Contact> mDataSet;
    private OnItemClickListener mOnItemClickListener;

    public void setDataSet(List<Contact> dataSet) {
        mDataSet = dataSet;
    }

    ContactAdapter(List<Contact> dataSet, OnItemClickListener onItemClickListener) {
        mDataSet = dataSet;
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Contact contact = mDataSet.get(position);
        holder.mTvNome.setText(contact.getName());
        holder.mTvPhone.setText(contact.getPhone());
        holder.mIvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(view.getContext(), view);
                //inflating menu from xml resource
                popup.inflate(R.menu.item_options_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.mCall:
                                mOnItemClickListener.onCallClick(contact);
                                break;
                            case R.id.mEdit:
                                mOnItemClickListener.onEditClick(contact);
                                break;
                            case R.id.mMaps:
                                mOnItemClickListener.onMapsClick(contact);
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTvNome;
        TextView mTvPhone;
        ImageView mIvMenu;

        ViewHolder(View view) {
            super(view);
            mTvNome = (TextView) view.findViewById(R.id.lblNome);
            mTvPhone = (TextView) view.findViewById(R.id.lblTelefone);
            mIvMenu = (ImageView) view.findViewById(R.id.ivMenu);
        }
    }
}
