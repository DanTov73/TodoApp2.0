package com.example.todoapp20.ui.inter

import com.example.todoapp20.ui.fragments.models.TaskModel

interface OnItemClickHome {
    interface OnItemClickHome {
        fun update(taskModel: TaskModel)

        fun delete(taskModel: TaskModel)
    }
}