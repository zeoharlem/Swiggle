package ng.com.zeoharlem.swopit.util

import androidx.recyclerview.widget.DiffUtil
import ng.com.zeoharlem.swopit.models.Popular

class PopularDiffUtil(private val oldList: List<Popular>, private val newList: List<Popular>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}