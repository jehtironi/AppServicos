package br.com.dlweb.servi1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import br.com.dlweb.servi1.databinding.FragmentConfiguracoesBinding;
import br.com.dlweb.servi1.R;

public class ConfiguracoesFragment extends Fragment {

    private FragmentConfiguracoesBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentConfiguracoesBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.imageButtonHomeC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ConfiguracoesFragment.this)
                        .navigate(R.id.action_ConfigFragment_to_HomeFragment);
            }
        });
        binding.imageButtonConfigC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ConfiguracoesFragment.this)
                        .navigate(R.id.action_ConfigFragment_to_ConfigFragment);
            }
        });
        binding.imageButtonOrcC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ConfiguracoesFragment.this)
                        .navigate(R.id.action_ConfigFragment_to_OrcamentosFragment);
            }
        });
        binding.imageButtonProfileC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ConfiguracoesFragment.this)
                        .navigate(R.id.action_ConfigFragment_to_ProfileFragment);
            }
        });
        binding.imageButtonSearcheO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ConfiguracoesFragment.this)
                        .navigate(R.id.action_ConfigFragment_to_SearcheFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}