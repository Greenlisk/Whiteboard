package com.example.whiteboardtest;

import android.app.Application;

import com.example.whiteboardtest.model.IRepository;
import com.example.whiteboardtest.model.Repository;
import com.example.whiteboardtest.model.local.ILocalDataSource;
import com.example.whiteboardtest.model.local.LocalDataSource;
import com.example.whiteboardtest.model.remote.IRemoteDataSource;
import com.example.whiteboardtest.model.usecases.GetMessageUseCase;
import com.example.whiteboardtest.model.usecases.IGetMessagesUseCase;
import com.example.whiteboardtest.model.usecases.IRemoveMessageUseCase;
import com.example.whiteboardtest.model.usecases.RemoveMessageUseCase;

public class WhiteBoardApplication extends Application {
    private IRepository repository;
    private IGetMessagesUseCase getMessagesUseCase;
    private IRemoveMessageUseCase removeMessageUseCase;
    private ILocalDataSource localDataSource;
    private IRemoteDataSource remoteDataSource;

    @Override
    public void onCreate() {
        super.onCreate();
        localDataSource = new LocalDataSource(this);
        repository = new Repository(localDataSource, remoteDataSource);
        getMessagesUseCase = new GetMessageUseCase(repository);
        removeMessageUseCase = new RemoveMessageUseCase(repository);
    }

    public IGetMessagesUseCase getMessagesUseCase(){
        return getMessagesUseCase;
    }
    public IRemoveMessageUseCase removeMessageUseCase(){
        return removeMessageUseCase;
    }
}
