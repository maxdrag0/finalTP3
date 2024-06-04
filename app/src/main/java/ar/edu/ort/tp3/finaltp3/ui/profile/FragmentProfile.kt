package ar.edu.ort.tp3.finaltp3.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ar.edu.ort.tp3.finaltp3.R
import ar.edu.ort.tp3.finaltp3.ui._activities.MainActivity

class FragmentProfile : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = ""
        (activity as? AppCompatActivity)?.supportActionBar?.title = ""
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity is MainActivity) {
            (activity as MainActivity).esconderToolbar()
            (activity as MainActivity).mostrarBottom()
        }

        val settingButton: TextView = view.findViewById(R.id.text_view6)

        settingButton.setOnClickListener {
            findNavController().navigate(R.id.fragmentSettings)
        }



        (activity as? AppCompatActivity)?.supportActionBar?.title = ""
    }

}