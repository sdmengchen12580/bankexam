package com.udit.bankexam.ui.home.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.ui.home.fragment.dummy.DummyContent;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {
    private final List<DummyContent.DummyItem> mValues;

    public MyItemRecyclerViewAdapter(List<DummyContent.DummyItem> paramList) {
        this.mValues = paramList;
    }

    public int getItemCount() {
        return this.mValues.size();
    }

    public void onBindViewHolder(ViewHolder paramViewHolder, int paramInt) {
        paramViewHolder.mItem = (DummyContent.DummyItem) this.mValues.get(paramInt);
        paramViewHolder.mIdView.setText(((DummyContent.DummyItem) this.mValues.get(paramInt)).id);
        paramViewHolder.mContentView.setText(((DummyContent.DummyItem) this.mValues.get(paramInt)).content);
    }

    public ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new ViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.fragment_ques_data, paramViewGroup, false));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mContentView;

        public final TextView mIdView;

        public DummyContent.DummyItem mItem;

        public final View mView;

        public ViewHolder(View param1View) {
            super(param1View);
            this.mView = param1View;
            this.mIdView = (TextView) param1View.findViewById(R.id.item_number);
            this.mContentView = (TextView) param1View.findViewById(R.id.content);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(super.toString());
            stringBuilder.append(" '");
            stringBuilder.append(this.mContentView.getText());
            stringBuilder.append("'");
            return stringBuilder.toString();
        }
    }
}


