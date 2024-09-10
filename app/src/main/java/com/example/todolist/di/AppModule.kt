package com.example.todolist.di

import android.content.Context
import androidx.room.Room
import com.example.todolist.db.ToDoDao
import com.example.todolist.db.ToDoDataBase
import com.example.todolist.repository.ToDoRepository
import com.example.todolist.repository.ToDoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun getDataBase(@ApplicationContext context: Context): ToDoDataBase {
        return Room.databaseBuilder(context, ToDoDataBase::class.java, "TODO_DB")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDao(toDoDataBase: ToDoDataBase): ToDoDao {
        return toDoDataBase.getDao()
    }

    @Provides
    @Singleton
    fun provideToDoRepository(toDoDao: ToDoDao): ToDoRepository {
        return ToDoRepositoryImpl(toDoDao)
    }
}