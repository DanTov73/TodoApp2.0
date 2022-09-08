package com.example.todoapp20.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp20.databinding.ItemOnBoardingBinding
import com.example.todoapp20.ui.inter.OnItemClicker
import com.example.todoapp20.ui.fragments.models.OnBoardingModel

class OnBoardingAdapter(private val list: ArrayList<OnBoardingModel>, val listener: OnItemClicker) :
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnBoardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class OnBoardingViewHolder(private val binding: ItemOnBoardingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(model: OnBoardingModel) {
            binding.tvTittleViewPager.text = model.tittle
            binding.lottieImage.setAnimation(model.image)
            binding.btnNext.text = model.txtBtn

            if (binding.btnNext.text.equals("Начинаем")) {
                binding.btnNext.setOnClickListener {
                    listener.onClick()
                }
            }
            else
                binding.btnNext.setOnClickListener {
                    listener.onClickNext()
                }
        }
    }
}