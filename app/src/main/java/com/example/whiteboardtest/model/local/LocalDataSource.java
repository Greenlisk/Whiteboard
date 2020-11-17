package com.example.whiteboardtest.model.local;

import android.content.Context;

import com.example.whiteboardtest.model.entities.Message;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class LocalDataSource implements ILocalDataSource {
    private MessageDatabase messageDatabase;

    @Inject
    public LocalDataSource(Context context){
        messageDatabase = Room.databaseBuilder(context.getApplicationContext(),
                MessageDatabase.class,
                "MessageDataBase")
                .fallbackToDestructiveMigration()
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                messageDatabase.clearAllTables();
                            }
                        }).start();

                    }
                })
                .build();
    }

    @Override
    public LiveData<List<Message>> getMessages() {
        return messageDatabase.messageDao().getMessages();
    }

    @Override
    public void removeMessage(Message message) {
        messageDatabase.messageDao().delete(message);
    }

    @Override
    public void insertMessage(Message message) {
        messageDatabase.messageDao().insert(message);
    }
}
