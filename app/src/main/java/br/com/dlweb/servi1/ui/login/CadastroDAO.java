package br.com.dlweb.servi1.ui.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.com.dlweb.servi1.R;
import br.com.dlweb.servi1.database.SQLiteHelper;

public class CadastroDAO {
    private SQLiteDatabase db;
    private SQLiteHelper dbHelper;
    private static final String table = "cadastro";

    public CadastroDAO (Context ctx) {
        StringBuilder sqlCreate = new StringBuilder();
        sqlCreate.append("CREATE TABLE ");
        sqlCreate.append(table);
        sqlCreate.append("(");
        sqlCreate.append("_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sqlCreate.append("nome VARCHAR(50), ");
        sqlCreate.append("nascimento VARCHAR(20), ");
        sqlCreate.append("telefone VARCHAR(20), ");
        sqlCreate.append("sexo VARCHAR(20), ");
        sqlCreate.append("cpf VARCHAR(50), ");
        sqlCreate.append(");");
        StringBuilder sqlDelete = new StringBuilder();
        sqlDelete.append("DROP TABLE IF NOT EXISTS ");
        sqlDelete.append(table);
        dbHelper = new SQLiteHelper(ctx, "servi.db", 1, sqlCreate.toString(), sqlDelete.toString());
    }

    public long add(Cadastro c) {
        ContentValues cv = new ContentValues();
        cv.put("nome", c.getNome());
        cv.put("nascimento", c.getNascimento());
        cv.put("cpf", c.getCpf());
        cv.put("sexo", c.getSexo());
        cv.put("telefone", c.getTelefone());
        db = dbHelper.getWritableDatabase();
        long id = db.insert(table, null, cv);
        db.close();
        return id;
    }

    public long edit(Cadastro c) {
        ContentValues cv = new ContentValues();
        cv.put("nome", c.getNome());
        cv.put("nascimento", c.getNascimento());
        cv.put("cpf", c.getCpf());
        cv.put("sexo", c.getSexo());
        cv.put("telefone", c.getTelefone());
        db = dbHelper.getWritableDatabase();
        long rows = db.update(table, cv, "_id = ?", new String[]{String.valueOf(c.getId())});
        db.close();
        return rows;
    }

    public long delete(Cadastro c) {
        db = dbHelper.getWritableDatabase();
        long rows = db.delete(table, "_id = ?", new String[]{String.valueOf(c.getId())});
        db.close();
        return rows;
    }

    public void readAll (Context context, ListView lv) {
        db = dbHelper.getWritableDatabase();
        String[] columns = {"_id", "nome", "nascimento", "sexo", "cpf", "telefone"};
        Cursor data = db.query(table, columns, null, null, null,
                null, "nome");
        int[] to = {R.id.textViewIdListarCadastro, R.id.textViewNomeCadastroListar,
                R.id.textViewCpfCadastro, R.id.textViewTelefoneCadastro, R.id.textViewSexoCadastro, R.id.textViewNascimentoCadastro};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.cadastro_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public Cadastro read (int id) {
        db = dbHelper.getWritableDatabase();
        String[] columns = {"_id", "nome", "nascimento", "sexo", "cpf", "telefone"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(table, columns, "_id = ?", args, null,
                null, null);
        data.moveToFirst();
        Cadastro c = new Cadastro();
        c.setId(data.getInt(0));
        c.setNome(data.getString(1));
        c.setNascimento(data.getString(2));
        c.setCpf(data.getString(3));
        c.setSexo(data.getString(4));
        c.setTelefone(data.getString(5));

        data.close();
        db.close();
        return c;
    }
}
