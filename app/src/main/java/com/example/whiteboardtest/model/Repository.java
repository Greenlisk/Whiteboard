package com.example.whiteboardtest.model;

import android.content.Context;

import com.example.whiteboardtest.model.entities.Message;
import com.example.whiteboardtest.model.local.ILocalDataSource;
import com.example.whiteboardtest.model.local.LocalDataSource;
import com.example.whiteboardtest.model.remote.IRemoteDataSource;
import com.example.whiteboardtest.model.remote.MessageGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.LiveData;

public class Repository implements IRepository {

    private ExecutorService executorIO = Executors.newSingleThreadExecutor();
    private ExecutorService executorGenerator = Executors.newFixedThreadPool(5);
    private ILocalDataSource local;
    private IRemoteDataSource remote;

    public Repository(ILocalDataSource local, IRemoteDataSource remote){
        this.local = local;
        ArrayList<MessageGenerator> generatorsList = new ArrayList<>();
        for(int i = 0 ; i < 5; ++i){
            MessageGenerator generator = new MessageGenerator(local);
            generatorsList.add(generator);
            executorGenerator.execute(generator);
        }
    }

    public LiveData<List<Message>> getMessages(){
        return local.getMessages();
    }

    public void removeMessage(final Message message){
        executorIO.execute(new Runnable() {
            @Override
            public void run() {
                local.removeMessage(message);
            }
        });

    }
}
