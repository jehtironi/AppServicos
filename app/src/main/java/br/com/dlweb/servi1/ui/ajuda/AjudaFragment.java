package br.com.dlweb.servi1.ui.ajuda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import br.com.dlweb.servi1.databinding.FragmentAjudaBinding;

public class AjudaFragment extends Fragment {

    private FragmentAjudaBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAjudaBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.buttonCadastrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //NavHostFragment.findNavController(LoginFragment.this)
//                      //  .navigate(R.id.action_LoginFragment_to_CadastroFragment);
//            }
//        });
//
//        binding.buttonEntrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              //  NavHostFragment.findNavController(LoginFragment.this)
//                   //     .navigate(R.id.action_LoginFragment_to_EntrarFragment);
//            }
//        });
//
//        binding.buttonHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               // NavHostFragment.findNavController(LoginFragment.this)
//                   //     .navigate(R.id.action_LoginFragment_to_HomeFragment);
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}