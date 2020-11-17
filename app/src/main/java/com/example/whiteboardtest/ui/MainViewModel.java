package com.example.whiteboardtest.ui;

import android.app.Application;

import com.example.whiteboardtest.WhiteBoardApplication;
import com.example.whiteboardtest.model.entities.Message;
import com.example.whiteboardtest.model.usecases.IGetMessagesUseCase;
import com.example.whiteboardtest.model.usecases.IRemoveMessageUseCase;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


public class MainViewModel extends AndroidViewModel {
    private final static String TAG = "MainViewModel";
    private LiveData<List<Message>> dataStorage;
    private IGetMessagesUseCase getMessagesUseCase;
    private IRemoveMessageUseCase removeMessageUseCase;


    public MainViewModel(Application application){
        super(application);
        getMessagesUseCase = ((WhiteBoardApplication)application).getMessagesUseCase();
        removeMessageUseCase = ((WhiteBoardApplication)application).removeMessageUseCase();
        dataStorage = getMessagesUseCase.getMessages();
    }

    public LiveData<List<Message>> getMessages(){
        return dataStorage;
    }

    public void deleteMessage(Message message){
        removeMessageUseCase.removeMessage(message);
    }
}
