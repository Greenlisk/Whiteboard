package com.example.whiteboardtest.model;

import com.example.whiteboardtest.model.entities.Message;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface IRepository {
    LiveData<List<Message>> getMessages();
    void removeMessage(Message message);
}
