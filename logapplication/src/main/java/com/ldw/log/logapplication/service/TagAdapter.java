package com.ldw.log.logapplication.service;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.ldw.log.logapplication.MyBaseAdapter;
import com.ldw.log.logapplication.R;
import com.ldw.log.logapplication.ViewHolder;

import java.util.List;


public class TagAdapter extends MyBaseAdapter<String> {
    public TagAdapter(List<String> list, Context activity, int layoutId) {
        super(list, activity, layoutId);
    }

    @Override
    public void setValues(ViewHolder holder, String s, int position) {
        holder.setText(R.id.tv_tag, s);
        TextView textView = (TextView) holder.getView(R.id.tv_tag);
        if (s.equals(WindowUtils.select)) {
            textView.setTextColor(ContextCompat.getColor(activity,R.color.red));
            holder.getConvertView().setBackgroundResource(R.color.green);
        } else {
            textView.setTextColor(ContextCompat.getColor(activity,R.color.black));
            holder.getConvertView().setBackgroundResource(R.color.white);
        }

    }
}
