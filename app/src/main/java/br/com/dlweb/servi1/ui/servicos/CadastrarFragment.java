package br.com.dlweb.servi1.ui.servicos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import br.com.dlweb.servi1.R;
import br.com.dlweb.servi1.database.DatabaseHelper;


public class CadastrarFragment extends Fragment {

    EditText etNomeServico;
    EditText etValor;
    EditText etTelefone;
    EditText etHorario;
    EditText etEndereco;

    public CadastrarFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cadastrar, container, false);

        etNomeServico = v.findViewById(R.id.editTextServico);
        etValor = v.findViewById(R.id.editTextValor);
        etTelefone = v.findViewById(R.id.editTextPhone);
        etHorario = v.findViewById(R.id.editTextHorario);
        etEndereco = v.findViewById(R.id.editTextEndereco);


        Button btnAdicionar = v.findViewById(R.id.button_salvar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionar();
            }
        });

        return v;
    }

    private void adicionar () {
        if (etNomeServico.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome do serviço!", Toast.LENGTH_LONG).show();
        } else if (etValor.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o valor do serviço!", Toast.LENGTH_LONG).show();
        } else if (etEndereco.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o endereço!", Toast.LENGTH_LONG).show();
        } else if (etTelefone.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o telefone!", Toast.LENGTH_LONG).show();
        } else if (etHorario.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o horario!", Toast.LENGTH_LONG).show();
        } else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Servicos s = new Servicos();
            s.setId(0);
            s.setNomeServico(etNomeServico.getText().toString());
            s.setValor(etValor.getText().toString());
            s.setEndereco(etEndereco.getText().toString());
            s.setHorario(etHorario.getText().toString());
            s.setTelefone(etTelefone.getText().toString());
            databaseHelper.createServicos(s);
            Toast.makeText(getActivity(), "Serviço salvo!", Toast.LENGTH_LONG).show();

        }
    }
}