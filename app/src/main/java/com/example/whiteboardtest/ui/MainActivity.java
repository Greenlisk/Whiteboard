package com.example.whiteboardtest.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.os.Bundle;

import com.example.whiteboardtest.R;
import com.example.whiteboardtest.model.entities.Message;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Observer<List<Message>> {
    private final static String TAG = "MainActivity";
    private RecyclerView messageRecycler;
    private MessageRecyclerAdapter messageAdapter;
    private MainViewModel messageViewModel;
    private LinearLayoutManager layoutManager;
    private LiveData<List<Message>> messagesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        messagesList = messageViewModel.getMessages();
        messagesList.observe(this, this);
        messageRecycler = findViewById(R.id.message_recycler);
        layoutManager = new LinearLayoutManager(this);
        messageAdapter = new MessageRecyclerAdapter();
        messageAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                if (positionStart == 0 && positionStart == layoutManager.findFirstCompletelyVisibleItemPosition()) {
                    layoutManager.scrollToPosition(0);
                }
            }
        });
        messageRecycler.setAdapter(messageAdapter);
        messageRecycler.setLayoutManager(layoutManager);
        messageRecycler.setHasFixedSize(true);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    if(isCurrentlyActive) {
                        messagesList.removeObservers(MainActivity.this);
                    } else {
                        messagesList.observe(MainActivity.this, MainActivity.this);
                    }
                }
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                messageViewModel.deleteMessage(messageAdapter.getMessageAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(messageRecycler);
    }

    @Override
    public void onChanged(List<Message> messages) {
        messageAdapter.submitList(messages);
    }
}