package com.example.whiteboardtest.model.remote;

import android.util.Log;

import com.example.whiteboardtest.model.entities.Message;
import com.example.whiteboardtest.model.local.ILocalDataSource;

import java.util.Date;
import java.util.Random;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MessageGenerator implements Runnable {
    private final static String TAG = "MessageGenerator";

    private MutableLiveData<Message> liveMessage = new MutableLiveData<>();
    private ILocalDataSource local;

    public MessageGenerator(ILocalDataSource local){
        this.local = local;
    }
    @Override
    public void run() {
        while(true) {
            try {
                Random ran = new Random();
                Thread.sleep((ran.nextInt(6) + 5) * 1000);
                Message message = new Message(new Date(), Thread.currentThread().getName(), this.toString());
                Log.e(TAG, "NewMessage " + message.getDate().getTime());
                local.insertMessage(message);
            } catch (InterruptedException iex) {
                Log.e(TAG, iex.toString());
            }
        }
    }

    public LiveData<Message> getLiveMessage(){
        return liveMessage;
    }
}
