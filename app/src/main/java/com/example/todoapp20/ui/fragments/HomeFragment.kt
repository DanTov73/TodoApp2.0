package com.example.todoapp20.ui.fragments


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp20.App
import com.example.todoapp20.R
import com.example.todoapp20.ui.fragments.models.TaskModel
import com.example.todoapp20.ui.inter.OnItemClickHome
import com.example.todoapp20.ui.inter.OnItemClicker
import com.example.todoapp20.databinding.FragmentHomeBinding
import com.example.todoapp20.ui.adapters.TaskAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment(), OnItemClickHome {

    private lateinit var binding: FragmentHomeBinding
    var adapter = TaskAdapter(arrayListOf(), this)
    private val fireStore = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicker()

//        App.appDataBase.taskDao().getAll().observe(viewLifecycleOwner) {data->
        val list = arrayListOf<TaskModel>()

        fireStore.collection("task").get().addOnSuccessListener { result ->
            for (document in result) {
                val task = document.data["task"].toString()
                val date = document.data["date"].toString()
                val regular = document.data["regular"].toString()
                val model = TaskModel(task = task, date = date, regular = regular)
                list.add(model)
            }
            adapter = TaskAdapter(list, this)
            binding.recyclerTask.adapter = adapter
        }
    }

    private fun initClicker() {
        binding.btnAddTask.setOnClickListener {
            val dialog = CreateTaskData()
            dialog.show(requireActivity().supportFragmentManager, "")
        }
    }


//    override fun update(taskModel: TaskModel) {
//        val dialog = CreateTaskData()
//        val bundle = Bundle()
//        bundle.putSerializable("model", taskModel)
//        dialog.arguments = bundle
//        dialog.show(requireActivity().supportFragmentManager, "")
//    }
//
//    override fun delete(taskModel: TaskModel) {
//        val dialog = AlertDialog.Builder(requireActivity())
//        dialog.setTitle("Вы точно хотите удалить задачу?")
//        dialog.setPositiveButton("Да", DialogInterface.OnClickListener { dialogInterface, i ->
//            App.appDataBase.taskDao().deleteData(taskModel)
//            dialogInterface.dismiss()
//        })
//        dialog.setNegativeButton("Нет", DialogInterface.OnClickListener { dialogInterface, i ->
//            dialogInterface.dismiss()
//        })
//        dialog.show()
//    }
}