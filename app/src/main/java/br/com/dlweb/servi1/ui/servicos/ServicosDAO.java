package br.com.dlweb.servi1.ui.servicos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.com.dlweb.servi1.R;
import br.com.dlweb.servi1.database.SQLiteHelper;

public class ServicosDAO {
    private SQLiteDatabase db;
    private SQLiteHelper dbHelper;
    private static final String table = "servicos";

    public ServicosDAO (Context ctx) {
        StringBuilder sqlCreate = new StringBuilder();
        sqlCreate.append("CREATE TABLE ");
        sqlCreate.append(table);
        sqlCreate.append("(");
        sqlCreate.append("_id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sqlCreate.append("nomeServico VARCHAR(50), ");
        sqlCreate.append("valor VARCHAR(20), ");
        sqlCreate.append("telefone VARCHAR(20), ");
        sqlCreate.append("horario VARCHAR(20), ");
        sqlCreate.append("endereco VARCHAR(50), ");
        sqlCreate.append(");");
        StringBuilder sqlDelete = new StringBuilder();
        sqlDelete.append("DROP TABLE IF NOT EXISTS ");
        sqlDelete.append(table);
        dbHelper = new SQLiteHelper(ctx, "servi.db", 1, sqlCreate.toString(), sqlDelete.toString());
    }

    public long add(Servicos s) {
        ContentValues cv = new ContentValues();
        cv.put("nomeServico", s.getNomeServico());
        cv.put("valor", s.getValor());
        cv.put("endereco", s.getEndereco());
        cv.put("horario", s.getHorario());
        cv.put("telefone", s.getTelefone());
        db = dbHelper.getWritableDatabase();
        long id = db.insert(table, null, cv);
        db.close();
        return id;
    }

    public long edit(Servicos s) {
        ContentValues cv = new ContentValues();
        cv.put("nomeServico", s.getNomeServico());
        cv.put("valor", s.getValor());
        cv.put("endereco", s.getEndereco());
        cv.put("horario", s.getHorario());
        cv.put("telefone", s.getTelefone());
        db = dbHelper.getWritableDatabase();
        long rows = db.update(table, cv, "_id = ?", new String[]{String.valueOf(s.getId())});
        db.close();
        return rows;
    }

    public long delete(Servicos s) {
        db = dbHelper.getWritableDatabase();
        long rows = db.delete(table, "_id = ?", new String[]{String.valueOf(s.getId())});
        db.close();
        return rows;
    }

    public void readAll (Context context, ListView lv) {
        db = dbHelper.getWritableDatabase();
        String[] columns = {"_id", "nomeServico", "valor", "endereco", "horario", "telefone"};
        Cursor data = db.query(table, columns, null, null, null,
                null, "nome");
        int[] to = {R.id.textViewIdListarServicos, R.id.textViewNomeServicosListarServicos,
                R.id.textViewValorListarServicos, R.id.textViewTelefoneListarServicos, R.id.textViewHorarioListarServicos, R.id.textViewEnderecoListarServicos};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.servicos_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public Servicos read (int id) {
        db = dbHelper.getWritableDatabase();
        String[] columns = {"_id", "nomeServico", "valor", "endereco", "horario", "telefone"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(table, columns, "_id = ?", args, null,
                null, null);
        data.moveToFirst();
        Servicos s = new Servicos();
        s.setId(data.getInt(0));
        s.setNomeServico(data.getString(1));
        s.setValor(data.getString(2));
        s.setEndereco(data.getString(3));
        s.setHorario(data.getString(4));
        s.setTelefone(data.getString(5));

        data.close();
        db.close();
        return s;
    }
}
