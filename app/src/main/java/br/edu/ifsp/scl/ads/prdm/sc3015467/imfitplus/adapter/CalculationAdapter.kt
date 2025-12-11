package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ArrayAdapter
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.R
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.databinding.TileCalculationBinding
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.Calculation

class CalculationAdapter(
    context: Context,
    private val calculations: MutableList<Calculation>
) : ArrayAdapter<Calculation>(
    context,
    R.layout.tile_calculation,
    calculations
) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val calculation = calculations[position]
        var tileView = convertView

        if (tileView == null) {
            TileCalculationBinding.inflate(
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            ).apply {
                tileView = root
                val viewHolder = TileCalculationViewHolder(
                    imcTv,
                    categoryTv,
                    weightIdealTv,
                    tmbTv
                )
                tileView!!.tag = viewHolder
            }
        }

        val holder = tileView?.tag as TileCalculationViewHolder
        holder.imcTv.text = "IMC: %.2f".format(calculation.imc)
        holder.categoryTv.text = "Categoria: ${calculation.category}"
        holder.weightIdealTv.text = "Peso ideal: %.2f kg".format(calculation.idealWeight)
        holder.tmbTv.text = "TMB: %.2f kcal".format(calculation.tmb)

        return tileView!!
    }

    private data class TileCalculationViewHolder(
        val imcTv: TextView,
        val categoryTv: TextView,
        val weightIdealTv: TextView,
        val tmbTv: TextView
    )
}
