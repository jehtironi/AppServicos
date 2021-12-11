package br.com.dlweb.servi1.ui.servicos;

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


public class EditarFragment extends Fragment {

    EditText etNomeServico;
    EditText etValor;
    EditText etTelefone;
    EditText etHorario;
    EditText etEndereco;
    DatabaseHelper databaseHelper;
    Servicos s;

    public EditarFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_editar, container, false);
        Bundle b = getArguments();
        int id_servicos = b.getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        s = databaseHelper.getServicos(id_servicos);


        etNomeServico = v.findViewById(R.id.editTextServico);
        etValor = v.findViewById(R.id.editTextValor);
        etTelefone = v.findViewById(R.id.editTextPhone);
        etHorario = v.findViewById(R.id.editTextHorario);
        etEndereco = v.findViewById(R.id.editTextEndereco);

        etNomeServico.setText(s.getNomeServico());
        etValor.setText(s.getValor());
        etTelefone.setText(s.getTelefone());
        etHorario.setText(s.getHorario());
        etEndereco.setText(s.getEndereco());

        Button btnEditar = v.findViewById(R.id.button_salvar);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar(id_servicos);
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
                        excluir(id_servicos);
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
            Servicos s = new Servicos();
            s.setId(0);
            s.setNomeServico(etNomeServico.getText().toString());
            s.setValor(etValor.getText().toString());
            s.setEndereco(etEndereco.getText().toString());
            s.setHorario(etHorario.getText().toString());
            s.setTelefone(etTelefone.getText().toString());
            databaseHelper.updateServicos(s);
            Toast.makeText(getActivity(), "Serviço editado!", Toast.LENGTH_LONG).show();

        }
    }

    private void excluir (int id) {
        s = new Servicos();
        s.setId(id);
        databaseHelper.deleteServicos(s);
        Toast.makeText(getActivity(), "Serviço excluído!", Toast.LENGTH_LONG).show();

    }
}