package kg.less.less_01_04.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kg.less.less_01_04.data.models.User
import kg.less.less_01_04.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val args by navArgs<SecondFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        initListener()
    }

    private fun getData() {
        binding.txtSecond.text = args.name
    }

    private fun initListener() {
        binding.btnSend.setOnClickListener {

            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else {
                val pass = password.toIntOrNull()
                if (pass == null)
                    Toast.makeText(
                        requireContext(),
                        "Пароль должен быть числом",
                        Toast.LENGTH_SHORT
                    ).show()
                else {
                    findNavController().navigate(
                        SecondFragmentDirections.actionSecondFragmentToDetailFragment(
                            User(name, email, pass)
                        )
                    )
                }
            }
        }
    }


}