package co.dapi.wusample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.dapi.wusample.databinding.ItemBankBinding

class BanksAdapter(private val banks: HashMap<String, String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onBankClickListener: OnBankClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BankViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_bank, parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as BankViewHolder) {
            binding.tvBankName.text = banks.keys.elementAt(position)
        }
    }

    override fun getItemCount() = banks.keys.size

    interface OnBankClickListener {
        fun onBankClicked(bankID: String)
    }

    inner class BankViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val binding = ItemBankBinding.bind(itemView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onBankClickListener?.onBankClicked(banks.values.elementAt(adapterPosition))
        }
    }

}
