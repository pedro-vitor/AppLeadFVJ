package com.NTI.AppFVJ.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
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

import com.NTI.AppFVJ.Database.DataHelper;
import com.NTI.AppFVJ.MaskEditUtil.MaskEditUtil;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterPeopleActivity extends AppCompatActivity {
    private EditText et_nome, et_email, et_telefone, et_endereco;
    private Spinner sp_curso;
    private String[] cursos = { "Curso", "Informatica"  , "Administração", "Hopedagem"};

    private DataHelper dataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_people);

        et_nome = findViewById(R.id.et_nome);
        et_email = findViewById(R.id.et_email);
        et_telefone = findViewById(R.id.et_telefone);
        et_endereco = findViewById(R.id.et_endereco);
        sp_curso = findViewById(R.id.sp_curso);

        et_telefone.addTextChangedListener(MaskEditUtil.mask(et_telefone,"(##) #####-####"));

        dataHelper = new DataHelper(this);

        final List<String> listCursos = new ArrayList<String>(Arrays.asList(cursos));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, listCursos){
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
                TextView textview = (TextView)view;

                if(position == 0)
                    textview.setTextColor(Color.GRAY);

                    return view;
                }
            };

            spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            sp_curso.setAdapter(spinnerArrayAdapter);

            sp_curso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItemText = (String) parent.getItemAtPosition(position);

                    if(position > 0) {
                        // Ação realizada quando um elemento diferente
                        // do hint é selecionado
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        public void BacktoMain(View view) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        //verificar se o spiner não tem a opção "Curso" marcada, e fazer a inserção no banco
        public void InsertPeople(View View){
            if(et_nome.getText().toString().trim().isEmpty() ||
               et_email.getText().toString().trim().isEmpty() ||
               et_telefone.getText().toString().trim().isEmpty() ||
               et_endereco.getText().toString().trim().isEmpty() ||
               sp_curso.getSelectedItem().toString().equals("Curso")){

                Toast toast = Toast.makeText(this, "Todos os campos devem ser preenchidos",Toast.LENGTH_SHORT);
                toast.show();
            }else {

                String name_upcase = et_nome.getText().toString().trim().substring(0,1).toLowerCase().concat(et_nome.getText().toString().trim().substring(1));

                Lead lead = new Lead();
                lead.setUsers_Id(12);
                lead.setName(name_upcase);
                lead.setEmail(et_email.getText().toString().trim());
                lead.setNumber_phone(MaskEditUtil.unmask(et_telefone.getText().toString().trim()));
                lead.setDesired_course(sp_curso.getSelectedItem().toString());
                lead.setAddress(et_endereco.getText().toString().trim());

                dataHelper.insertLeads(lead);

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

