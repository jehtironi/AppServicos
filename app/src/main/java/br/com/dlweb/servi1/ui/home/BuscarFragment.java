package br.com.dlweb.servi1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import br.com.dlweb.servi1.databinding.FragmentBuscarBinding;
import br.com.dlweb.servi1.R;

public class BuscarFragment extends Fragment {

    private FragmentBuscarBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentBuscarBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.imageButtonHomeS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BuscarFragment.this)
                        .navigate(R.id.action_SearcheFragment_to_HomeFragment);
            }
        });
        binding.imageButtonConfigS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BuscarFragment.this)
                        .navigate(R.id.action_SearcheFragment_to_ConfigFragment);
            }
        });
        binding.imageButtonOrcS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BuscarFragment.this)
                        .navigate(R.id.action_SearcheFragment_to_OrcamentosFragment);
            }
        });
        binding.imageButtonProfileS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BuscarFragment.this)
                        .navigate(R.id.action_SearcheFragment_to_ProfileFragment);
            }
        });
        binding.imageButtonSearcheS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BuscarFragment.this)
                        .navigate(R.id.action_SearcheFragment_to_SearcheFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}