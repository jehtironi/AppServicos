package br.com.dlweb.servi1.ui.login;

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


public class CadastroFragment extends Fragment {

    EditText etNome;
    EditText etNascimento;
    EditText etTelefone;
    EditText etCpf;
    EditText etSexo;

    public CadastroFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cadastro, container, false);

        etNome = v.findViewById(R.id.editTextNome);
        etNascimento = v.findViewById(R.id.editTextDate);
        etTelefone = v.findViewById(R.id.editTextPhone);
        etSexo = v.findViewById(R.id.editTextSexo);
        etCpf = v.findViewById(R.id.editTextCPF);


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
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
        } else if (etNascimento.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o seu nascimento!", Toast.LENGTH_LONG).show();
        } else if (etCpf.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o CPF!", Toast.LENGTH_LONG).show();
        } else if (etTelefone.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o telefone!", Toast.LENGTH_LONG).show();
        } else if (etSexo.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o sexo!", Toast.LENGTH_LONG).show();
        } else {
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Cadastro c = new Cadastro();
            c.setId(0);
            c.setNome(etNome.getText().toString());
            c.setNascimento(etNascimento.getText().toString());
            c.setCpf(etCpf.getText().toString());
            c.setSexo(etSexo.getText().toString());
            c.setTelefone(etTelefone.getText().toString());
            databaseHelper.createCadastro(c);
            Toast.makeText(getActivity(), "Cadastro salvo!", Toast.LENGTH_LONG).show();

        }
    }
}