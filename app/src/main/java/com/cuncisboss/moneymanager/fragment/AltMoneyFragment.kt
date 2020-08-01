package com.cuncisboss.moneymanager.fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuncisboss.moneymanager.R
import com.cuncisboss.moneymanager.adapter.ItemClickListener
import com.cuncisboss.moneymanager.adapter.SpendingAdapter
import com.cuncisboss.moneymanager.data.local.SpendingPref
import com.cuncisboss.moneymanager.databinding.FragmentAltMoneyBinding
import com.cuncisboss.moneymanager.model.Spending
import com.cuncisboss.moneymanager.util.Constants
import com.cuncisboss.moneymanager.util.Constants.ALT_VAL
import com.cuncisboss.moneymanager.util.DateHelper
import com.cuncisboss.moneymanager.util.SeparatorHelper
import com.cuncisboss.moneymanager.util.Utils.Companion.replaceUnusedItem
import com.cuncisboss.moneymanager.util.Utils.Companion.textWatcherNoZero
import com.cuncisboss.moneymanager.viewmodel.SpendingViewModel
import kotlinx.android.synthetic.main.dialog_nominal.*
import kotlinx.android.synthetic.main.dialog_nominal.btn_dialogSubmit
import kotlinx.android.synthetic.main.dialog_nominal.et_dialogName
import kotlinx.android.synthetic.main.dialog_nominal.et_dialogNominal
import kotlinx.android.synthetic.main.dialog_nominal.view.*
import kotlinx.android.synthetic.main.fragment_alt_money.*
import org.koin.android.ext.android.inject


class AltMoneyFragment : Fragment(R.layout.fragment_alt_money), ItemClickListener {

    private lateinit var spendingAdapter: SpendingAdapter
    private var spendingList: ArrayList<Spending> = ArrayList()
    private val spendingViewModel by inject<SpendingViewModel>()

    private lateinit var binding: FragmentAltMoneyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alt_money, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spendingAdapter = SpendingAdapter(this)
        binding.apply {
            altNominal = String.format(getString(R.string.nominal_value), SeparatorHelper.longToString(SpendingPref.getTotalAlt(requireContext())))
            handler = this@AltMoneyFragment
            tvAltNominal.setOnClickListener {
                dialogChangeValue(binding.altNominal.toString().replaceUnusedItem().toLong())
            }
        }
        view.apply {
            initRecyclerView()
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        spendingViewModel.getAllSpending(ALT_VAL).observe(viewLifecycleOwner, Observer {
            var tempTotal = 0L
            spendingList.clear()
            spendingList.addAll(it)
            for (i in spendingList.indices) {
                tempTotal += spendingList[i].nominal
            }
            binding.altNominal = String.format(getString(R.string.nominal_value),
                SeparatorHelper.longToString(SpendingPref.getTotalAlt(requireContext()) - tempTotal)
            )
            spendingList.reverse()
            spendingAdapter.setSpendingList(spendingList)
        })
    }

    private fun initRecyclerView() {
        rv_alt.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = spendingAdapter
        }
    }

    private fun dialogChangeValue(value: Long) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setCancelable(true)
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_nominal, null)
        builder.setView(view)

        val dialog = builder.create()

        view.apply {
            et_dialogName.visibility = View.GONE
            et_dialogNominal.addTextChangedListener { s ->
                if (s.toString().length == 1 && s.toString().startsWith("0")) {
                    s?.clear();
                }
            }
            et_dialogNominal.setText(value.toString())
            btn_dialogSubmit.setOnClickListener {
                if (et_dialogNominal.text.isNotEmpty()) {
                    binding.altNominal = String.format(
                        getString(R.string.nominal_value,
                            SeparatorHelper.longToString(et_dialogNominal.text.toString().toLong())
                        ))
                    SpendingPref.setTotalAlt(requireContext(), et_dialogNominal.text.toString().toLong())
                    dialog.dismiss()
                } else {
                    et_dialogNominal.error = "Field can't be empty"
                }
            }
        }

        dialog.show()
    }

    fun dialogInputNominal() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setCancelable(true)
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_nominal, null)
        builder.setView(view)
        val dialog = builder.create()
        view.apply {
            btn_dialogSubmit.setOnClickListener {
                when {
                    et_dialogName.text.isEmpty() -> {
                        et_dialogName.error = "Field can't be empty"
                    }
                    et_dialogNominal.text.isEmpty() -> {
                        et_dialogNominal.error = "Field can't be empty"
                    }
                    else -> {
                        spendingViewModel.insertData(Spending(
                            et_dialogName.text.toString(),
                            et_dialogNominal.text.toString().toLong(),
                            ALT_VAL,
                            DateHelper.getCurrentDatetime()))
                        dialog.dismiss()
                    }
                }
            }
        }
        dialog.show()
    }

    override fun onItemClick(view: View, spending: Spending) {
        dialogAlert(spending)
    }

    private fun dialogAlert(spending: Spending) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setCancelable(true)

        val list = arrayOf("Update", "Delete")
        builder.setItems(list) { dialog, position ->
            if (list[position].equals("update", true)) {
                dialogUpdate(spending)
            } else {
                dialogDelete(position, spending)
            }
            dialog.dismiss()
        }
        builder.create().show()
    }

    private fun dialogDelete(position: Int, spending: Spending) {
        spendingViewModel.deleteData(spending)
        spendingAdapter.removeItem(position-1)
    }

    private fun dialogUpdate(spending: Spending) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setCancelable(true)
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_nominal, null)
        builder.setView(view)
        val dialog = builder.create()
        view.apply {
            et_dialogName.setText(spending.name)
            et_dialogNominal.setText(spending.nominal.toString())
            btn_dialogSubmit.setOnClickListener {
                when {
                    et_dialogName.text.isEmpty() -> {
                        et_dialogName.error = "Field can't be empty"
                    }
                    et_dialogNominal.text.isEmpty() -> {
                        et_dialogNominal.error = "Field can't be empty"
                    }
                    else -> {
                        spendingViewModel.updateData(Spending(
                            et_dialogName.text.toString(),
                            et_dialogNominal.text.toString().toLong(),
                            ALT_VAL,
                            DateHelper.getCurrentDatetime(),
                            spending.id)
                        )
                        dialog.dismiss()
                    }
                }
            }
        }
        dialog.show()
    }

}