package com.example.whiteboardtest.model.usecases;

import com.example.whiteboardtest.model.entities.Message;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface IGetMessagesUseCase {
    LiveData<List<Message>> getMessages();
}
