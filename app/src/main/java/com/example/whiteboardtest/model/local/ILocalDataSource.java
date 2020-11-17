package com.example.whiteboardtest.model.local;

import com.example.whiteboardtest.model.entities.Message;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface ILocalDataSource {
    void insertMessage(Message message);
    LiveData<List<Message>> getMessages();
    void removeMessage(Message message);
}
