package br.com.dlweb.servi1.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import br.com.dlweb.servi1.databinding.FragmentFavoritosBinding;
import br.com.dlweb.servi1.R;

public class FavoritosFragment extends Fragment {

    private FragmentFavoritosBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFavoritosBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.imageButtonHomeP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FavoritosFragment.this)
                        .navigate(R.id.action_FavFragment_to_HomeFragment);
            }
        });
        binding.imageButtonConfigP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FavoritosFragment.this)
                        .navigate(R.id.action_FavFragment_to_ConfigFragment);
            }
        });
        binding.imageButtonOrcP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FavoritosFragment.this)
                        .navigate(R.id.action_FavFragment_to_OrcamentosFragment);
            }
        });
        binding.imageButtonProfileP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FavoritosFragment.this)
                        .navigate(R.id.action_FavFragment_to_ProfileFragment);
            }
        });
        binding.imageButtonSearcheP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FavoritosFragment.this)
                        .navigate(R.id.action_FavFragment_to_SearcheFragment);
            }
        });

        binding.buttonOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FavoritosFragment.this)
                        .navigate(R.id.action_FavFragment_to_orcrFragment);
            }
        });

        binding.buttonSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FavoritosFragment.this)
                        .navigate(R.id.action_FavFragment_to_ProfileFragment);
            }
        });

        binding.buttonF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FavoritosFragment.this)
                        .navigate(R.id.action_FavFragment_to_FavFragment);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}