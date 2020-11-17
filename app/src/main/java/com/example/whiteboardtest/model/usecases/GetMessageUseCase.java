package com.example.whiteboardtest.model.usecases;

import com.example.whiteboardtest.model.IRepository;
import com.example.whiteboardtest.model.entities.Message;

import java.util.List;

import androidx.lifecycle.LiveData;

public class GetMessageUseCase implements IGetMessagesUseCase {
    private IRepository repository;

    public GetMessageUseCase(IRepository repository){
        this.repository = repository;
    }

    @Override
    public LiveData<List<Message>> getMessages() {
        return repository.getMessages();
    }
}
