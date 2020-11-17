package com.example.whiteboardtest.model.local;

import com.example.whiteboardtest.model.entities.Message;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = Message.class, version = 1)
@TypeConverters({DateConverter.class})
public abstract class MessageDatabase extends RoomDatabase {
    public abstract MessageDao messageDao();
}
