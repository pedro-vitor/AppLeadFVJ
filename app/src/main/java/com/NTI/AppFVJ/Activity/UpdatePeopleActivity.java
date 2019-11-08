package com.NTI.AppFVJ.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.NTI.AppFVJ.Data.DataHelper;
import com.NTI.AppFVJ.Data.Filter;
import com.NTI.AppFVJ.MaskEditUtil.MaskEditUtil;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdatePeopleActivity extends AppCompatActivity {
    private TextView tv_id, tv_createdat;
    private EditText et_nome, et_email, et_telefone, et_endereco, et_cidade;
    private Spinner sp_curso;
    private String[] cursos = {
            "Cursos",
            "Administração",
            "Ciências Contábeis",
            "Direito",
            "Educação Física (Bacharelado)",
            "Educação Física (Licenciatura)",
            "Enfermagem",
            "Farmácia",
            "Fisioterapia",
            "Gastronomia",
            "Letras",
            "Nutrição",
            "Pedagogia",
            "Psicologia",
            "Recursos Humanos",
            "Serviço Social"};

    private DataHelper dataHelper;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_people);

        tv_id = findViewById(R.id.tv_id);
        tv_createdat = findViewById(R.id.tv_createdat);
        et_nome = findViewById(R.id.et_nome);
        et_email = findViewById(R.id.et_email);
        et_telefone = findViewById(R.id.et_telefone);
        et_endereco = findViewById(R.id.et_endereco);
        et_cidade = findViewById(R.id.et_cidade);
        sp_curso = findViewById(R.id.sp_curso);

        et_telefone.addTextChangedListener(MaskEditUtil.mask(et_telefone, "(##) #####-####"));

        dataHelper = new DataHelper(this);

        final List<String> listCursos = new ArrayList<String>(Arrays.asList(cursos));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listCursos) {
            @Override
            public boolean isEnabled(int position) {
                if(position == 0)
                    return false;
                else
                    return true;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textview = (TextView) view;

                if(cursos[position].equals("Cursos"))
                    textview.setTextColor(Color.GRAY);
                else
                    textview.setTextColor(Color.BLACK);

                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_curso.setAdapter(spinnerArrayAdapter);

        sp_curso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                ((TextView) parent.getChildAt(0)).setTextColor(0xFFFFFFFF);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        id = Integer.parseInt(getIntent().getStringExtra("Id"));
        setDatas(id);
    }

    public void setDatas(int id) {
        List<Lead> leadList = dataHelper.GetByIdLeads(id);

        for (Lead lead : leadList) {
            tv_id.setText(String.valueOf(lead.getId()));
            tv_createdat.setText(lead.getCreatedAt());
            et_nome.setText(lead.getName());
            et_email.setText(lead.getEmail());
            et_telefone.setText(lead.getNumberPhone());
            switch (lead.getDesiredCourse()) {
                case "Administração":
                    sp_curso.setSelection(1);
                    break;
                case "Ciências Contábeis":
                    sp_curso.setSelection(2);
                    break;
                case "Direito":
                    sp_curso.setSelection(3);
                    break;
                case "Educação Física (Bacharelado)":
                    sp_curso.setSelection(4);
                    break;
                case "Educação Física (Licenciatura)":
                    sp_curso.setSelection(5);
                    break;
                case "Enfermagem":
                    sp_curso.setSelection(6);
                    break;
                case "Farmácia":
                    sp_curso.setSelection(7);
                    break;
                case "Fisioterapia":
                    sp_curso.setSelection(8);
                    break;
                case "Gastronomia":
                    sp_curso.setSelection(9);
                    break;
                case "Letras":
                    sp_curso.setSelection(10);
                    break;
                case "Nutrição":
                    sp_curso.setSelection(11);
                    break;
                case "Pedagogia":
                    sp_curso.setSelection(12);
                    break;
                case "Psicologia":
                    sp_curso.setSelection(13);
                    break;
                case "Recursos Humanos":
                    sp_curso.setSelection(14);
                    break;
                case "Serviço Social":
                    sp_curso.setSelection(15);
                    break;
            }
            et_cidade.setText(lead.getTown());
            et_endereco.setText(lead.getAddress());
        }
    }

    public void UpdatePeople(View View) {
        if (!Filter.Nome(et_nome.getText().toString()))
            Toast.makeText(this, "O nome do lead deve conter no mínimo 3 caracteres e no máximo 50", Toast.LENGTH_SHORT).show();
        else
        if (!MaskEditUtil.validEmail(et_email.getText().toString().trim()))
            Toast.makeText(this, "Informe um Email valido", Toast.LENGTH_SHORT).show();
        else
        if (!Filter.Endereco(et_endereco.getText().toString()))
            Toast.makeText(this, "Verifique o endereço", Toast.LENGTH_SHORT).show();
        else
        if (!Filter.Cidade(et_cidade.getText().toString()))
            Toast.makeText(this, "Verifique o nome da cidade", Toast.LENGTH_SHORT).show();
        else
        if (et_nome.getText().toString().trim().isEmpty() ||
            et_email.getText().toString().trim().isEmpty() ||
            et_telefone.getText().toString().trim().isEmpty() ||
            et_endereco.getText().toString().trim().isEmpty() ||
            et_cidade.getText().toString().trim().isEmpty() ||
            sp_curso.getSelectedItem().toString().equals("Cursos"))
            Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
        else {
            String name_upcase = et_nome.getText().toString().trim().substring(0, 1).toUpperCase().concat(et_nome.getText().toString().trim().substring(1));
            String town = et_cidade.getText().toString().trim().substring(0, 1).toUpperCase().concat(et_cidade.getText().toString().trim().substring(1));

            Lead lead_r = dataHelper.GetByIdLeads(id).get(0);

            Lead lead = new Lead();
            lead.setId(lead_r.getId());
            lead.setExternId(lead_r.getExternId());
            lead.setUserId(lead_r.getUserId());
            lead.setName(name_upcase);
            lead.setEmail(et_email.getText().toString().trim());
            lead.setNumberPhone(MaskEditUtil.unmask(et_telefone.getText().toString().trim()));
            lead.setDesiredCourse(sp_curso.getSelectedItem().toString());
            lead.setTown(town);
            lead.setAddress(et_endereco.getText().toString().trim());
            lead.setCreatedAt(tv_createdat.getText().toString());
            lead.setActive(1);
            lead.setUpdated(1);

            if (dataHelper.updateLeads(lead) > 0) {
                Toast.makeText(this, "Atualizado com sucesso", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Erro na atualização", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
