package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.R
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.databinding.TileCalculationBinding
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.dto.CalculationWithUserDto

class CalculationHistoryAdapter(
    private val ctx: Context,
    private val historyList: MutableList<CalculationWithUserDto>
) : ArrayAdapter<CalculationWithUserDto>(
    ctx,
    R.layout.tile_calculation,
    historyList
) {

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = historyList[position]
        var tileView = convertView

        if (tileView == null) {
            TileCalculationBinding.inflate(
                ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            ).apply {
                tileView = root
                val viewHolder = TileCalculationViewHolder(
                    userNameTv,
                    imcTv,
                    categoryTv,
                    weightIdealTv,
                    tmbTv
                )
                tileView!!.tag = viewHolder
            }
        }

        val holder = tileView!!.tag as TileCalculationViewHolder

        holder.userNameTv.text = "Usuário: ${item.userName}"
        holder.imcTv.text = "IMC: %.2f".format(item.imc)
        holder.categoryTv.text = "Categoria: ${item.category}"
        holder.weightIdealTv.text = "Peso Ideal: %.2f kg".format(item.idealWeight)
        holder.tmbTv.text = "TMB: %.2f kcal".format(item.tmb)

        return tileView!!
    }

    private data class TileCalculationViewHolder(
        val userNameTv: TextView,
        val imcTv: TextView,
        val categoryTv: TextView,
        val weightIdealTv: TextView,
        val tmbTv: TextView
    )
}
