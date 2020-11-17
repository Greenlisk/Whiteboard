package com.example.whiteboardtest.model.usecases;

import com.example.whiteboardtest.model.IRepository;
import com.example.whiteboardtest.model.entities.Message;

public class RemoveMessageUseCase implements IRemoveMessageUseCase {
    private IRepository repository;

    public RemoveMessageUseCase(IRepository repository){
        this.repository = repository;
    }

    @Override
    public void removeMessage(Message message) {
        repository.removeMessage(message);
    }
}
