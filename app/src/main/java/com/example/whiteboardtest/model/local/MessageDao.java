package com.example.whiteboardtest.model.local;

import com.example.whiteboardtest.model.entities.Message;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MessageDao {

    @Insert
    void insert(Message message);

    @Delete
    void delete(Message message);

    @Query("SELECT * FROM message_table ORDER BY date DESC")
    LiveData<List<Message>> getMessages();

}
