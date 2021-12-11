package br.com.dlweb.servi1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

import br.com.dlweb.servi1.R;
import br.com.dlweb.servi1.ui.login.Cadastro;
import br.com.dlweb.servi1.ui.servicos.Servicos;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "servi.db";
    private static final String TABLE_SERVICOS = "servicos";
    private static final String TABLE_CADASTRO = "cadastro";


    private static final String SQL_CREATE_SERVICOS = "CREATE TABLE " + TABLE_SERVICOS + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nomeServico VARCHAR(50), " +
            "valor VARCHAR(20), " +
            "telefone VARCHAR(20), " +
            "horario VARCHAR(20), " +
            "endereco VARCHAR(50))";
    private static final String SQL_CREATE_CADASTRO = "CREATE TABLE " + TABLE_CADASTRO + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(50), " +
            "nascimento VARCHAR(20), " +
            "telefone VARCHAR(20), " +
            "cpf VARCHAR(20), " +
            "sexo VARCHAR(50))";
    private static final String SQL_DELETE_SERVICOS = "DROP TABLE IF EXISTS " + TABLE_SERVICOS;
    private static final String SQL_DELETE_CADASTRO = "DROP TABLE IF EXISTS " + TABLE_CADASTRO;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SERVICOS);
        db.execSQL(SQL_CREATE_CADASTRO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_SERVICOS);
        db.execSQL(SQL_DELETE_CADASTRO);
        onCreate(db);
    }


    /* Início CRUD Servicos*/
    public long createServicos(Servicos s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nomeServico", s.getNomeServico());
        cv.put("valor", s.getValor());
        cv.put("telefone", s.getTelefone());
        cv.put("horario", s.getHorario());
        cv.put("endereco", s.getEndereco());
        long id = db.insert(TABLE_SERVICOS, null, cv);
        db.close();
        return id;
    }

    public long updateServicos(Servicos s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nomeServico", s.getNomeServico());
        cv.put("valor", s.getValor());
        cv.put("telefone", s.getTelefone());
        cv.put("horario", s.getHorario());
        cv.put("endereco", s.getEndereco());
        long rows = db.update(TABLE_SERVICOS, cv, "_id = ?", new String[]{String.valueOf(s.getId())});
        db.close();
        return rows;
    }

    public long deleteServicos(Servicos s) {
        SQLiteDatabase db = this.getWritableDatabase();
        long rows = db.delete(TABLE_SERVICOS, "_id = ?", new String[]{String.valueOf(s.getId())});
        db.close();
        return rows;
    }

    public void getAllServicos(Context context, ListView lv) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id", "nomeServico", "valor", "endereco", "horario", "telefone"};
        Cursor data = db.query(TABLE_SERVICOS, columns, null, null, null,
                null, "nomeServico");
        int[] to = {R.id.textViewIdListarServicos, R.id.textViewNomeServicosListarServicos,
                R.id.textViewValorListarServicos, R.id.textViewTelefoneListarServicos, R.id.textViewHorarioListarServicos, R.id.textViewEnderecoListarServicos};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.servicos_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public void getNameAllServicos(ArrayList<Integer> listServicosId, ArrayList<String> listServicosName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id", "nomeServico"};
        Cursor data = db.query(TABLE_SERVICOS, columns, null, null, null,
                null, "nomeServico");
        while (data.moveToNext()) {
            int idColumnIndex = data.getColumnIndex("_id");
            listServicosId.add(Integer.parseInt(data.getString(idColumnIndex)));
            int nameColumnIndex = data.getColumnIndex("nomeServico");
            listServicosName.add(data.getString(nameColumnIndex));
        }
        db.close();
    }

    public Servicos getServicos(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id", "nomeServico", "valor", "endereco", "horario", "telefone"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_SERVICOS, columns, "_id = ?", args, null,
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
    /* Fim CRUD Servicos */

    /* Início CRUD Cadastro*/
    public long createCadastro(Cadastro c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", c.getNome());
        cv.put("nascimento", c.getNascimento());
        cv.put("telefone", c.getTelefone());
        cv.put("sexo", c.getSexo());
        cv.put("cpf", c.getCpf());
        long id = db.insert(TABLE_CADASTRO, null, cv);
        db.close();
        return id;
    }

    public long updateCadastro(Cadastro c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", c.getNome());
        cv.put("nascimento", c.getNascimento());
        cv.put("telefone", c.getTelefone());
        cv.put("sexo", c.getSexo());
        cv.put("cpf", c.getCpf());
        long rows = db.update(TABLE_CADASTRO, cv, "_id = ?", new String[]{String.valueOf(c.getId())});
        db.close();
        return rows;
    }

    public long deleteCadastro(Cadastro c) {
        SQLiteDatabase db = this.getWritableDatabase();
        long rows = db.delete(TABLE_CADASTRO, "_id = ?", new String[]{String.valueOf(c.getId())});
        db.close();
        return rows;
    }

    public void getAllCadastro(Context context, ListView lv) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id", "nome", "nascimento", "sexo", "cpf", "telefone"};
        Cursor data = db.query(TABLE_CADASTRO, columns, null, null, null,
                null, "nome");
        int[] to = {R.id.textViewIdListarCadastro, R.id.textViewNomeCadastroListar,
                R.id.textViewCpfCadastro, R.id.textViewTelefoneCadastro, R.id.textViewSexoCadastro, R.id.textViewNascimentoCadastro};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.cadastro_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public void getNameAllCadastro(ArrayList<Integer> listCadastroId, ArrayList<String> listCadastroName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id", "nome"};
        Cursor data = db.query(TABLE_CADASTRO, columns, null, null, null,
                null, "nome");
        while (data.moveToNext()) {
            int idColumnIndex = data.getColumnIndex("_id");
            listCadastroId.add(Integer.parseInt(data.getString(idColumnIndex)));
            int nameColumnIndex = data.getColumnIndex("nome");
            listCadastroName.add(data.getString(nameColumnIndex));
        }
        db.close();
    }

    public Cadastro getCadastro(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id", "nome", "nascimento", "sexo", "cpf", "telefone"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_CADASTRO, columns, "_id = ?", args, null,
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
    /* Fim CRUD Cadastro */


}