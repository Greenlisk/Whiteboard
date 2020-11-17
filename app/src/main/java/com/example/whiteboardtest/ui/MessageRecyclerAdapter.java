package com.example.whiteboardtest.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.whiteboardtest.R;
import com.example.whiteboardtest.model.entities.Message;

import java.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

class MessageRecyclerAdapter extends ListAdapter<Message, MessageRecyclerAdapter.MessageViewHolder> {

    public MessageRecyclerAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Message> DIFF_CALLBACK = new DiffUtil.ItemCallback<Message>() {
        @Override
        public boolean areItemsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.getUuid().equals(newItem.getUuid());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.equals(newItem);
        }
    };

    public Message getMessageAt(int position){
        return getItem(position);
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_item, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.name.setText(getItem(position).getName());
        holder.date.setText(new SimpleDateFormat("HH:mm:ss").format(getItem(position).getDate()));
        holder.subject.setText(getItem(position).getSubject());
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView name;
        public TextView subject;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.time);
            name = itemView.findViewById(R.id.name);
            subject = itemView.findViewById(R.id.subject);
        }
    }

}
