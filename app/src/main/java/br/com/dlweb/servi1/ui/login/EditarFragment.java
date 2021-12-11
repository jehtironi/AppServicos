package br.com.dlweb.servi1.ui.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import br.com.dlweb.servi1.ui.login.Cadastro;


public class EditarFragment extends Fragment {

    EditText etNome;
    EditText etNascimento;
    EditText etTelefone;
    EditText etCpf;
    EditText etSexo;
    DatabaseHelper databaseHelper;
    Cadastro c;

    public EditarFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_editarc, container, false);
        Bundle b = getArguments();
        int id_cadastro = b.getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        c = databaseHelper.getCadastro(id_cadastro);


        etNome = v.findViewById(R.id.editTextNome);
        etNascimento = v.findViewById(R.id.editTextDate);
        etTelefone = v.findViewById(R.id.editTextPhone);
        etCpf = v.findViewById(R.id.editTextCPF);
        etSexo = v.findViewById(R.id.editTextSexo);

        etNome.setText(c.getNome());
        etNascimento.setText(c.getNascimento());
        etTelefone.setText(c.getTelefone());
        etCpf.setText(c.getCpf());
        etSexo.setText(c.getSexo());

        Button btnEditar = v.findViewById(R.id.button_salvar);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar(id_cadastro);
            }
        });

        Button btnExcluir = v.findViewById(R.id.button_excluir);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Você tem certeza que deseja excluir?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir(id_cadastro);
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Não faz nada
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        return v;
    }

    private void editar (int id) {
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
            Cadastro c = new Cadastro();
            c.setId(0);
            c.setNome(etNome.getText().toString());
            c.setNascimento(etNascimento.getText().toString());
            c.setCpf(etCpf.getText().toString());
            c.setSexo(etSexo.getText().toString());
            c.setTelefone(etTelefone.getText().toString());
            databaseHelper.updateCadastro(c);
            Toast.makeText(getActivity(), "Cadastro editado!", Toast.LENGTH_LONG).show();

        }
    }

    private void excluir (int id) {
        c = new Cadastro();
        c.setId(id);
        databaseHelper.deleteCadastro(c);
        Toast.makeText(getActivity(), "Cadastro ecluido!", Toast.LENGTH_LONG).show();

    }
}