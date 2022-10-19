package ru.perelyginva.lessonroom.presentation.tabs

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.perelyginva.lessonroom.R
import ru.perelyginva.lessonroom.databinding.FragmentPanelEditCategoryBinding
import ru.perelyginva.lessonroom.viewModels.CategoryViewModel


class PanelEditCategoryFragment : BottomSheetDialogFragment(), View.OnKeyListener {

    private var binding: FragmentPanelEditCategoryBinding? = null
    private val categoryViewModel: CategoryViewModel by viewModel()
    private var idCategory: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentPanelEditCategoryBinding.inflate(inflater, container, false)

        idCategory = arguments?.getString("idCategory")?.toInt()
        binding?.editCategory?.setText(arguments?.getString("nameCategory")).toString()


        binding?.editCategory?.setOnKeyListener(this)

        return binding?.root
    }

    override fun onKey(view: View?, keyCode: Int, event: KeyEvent?): Boolean {
        when (view?.id) {

            R.id.editCategory -> {
                if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                    categoryViewModel.startUpdateCategory(
                        idCategory.toString().toInt(),
                        binding?.editCategory?.text?.toString()!!)
                    binding?.editCategory?.setText("")
                    dismiss()
                    (context as FragmentActivity).supportFragmentManager.beginTransaction()
                        .replace(R.id.content, TabCategoriesFragment()).commit()
                    return true
                }
            }
        }
        return false
    }

}