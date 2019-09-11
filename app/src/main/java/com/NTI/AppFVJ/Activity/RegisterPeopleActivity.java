package com.NTI.AppFVJ.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.NTI.AppFVJ.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterPeopleActivity extends AppCompatActivity {
    private Spinner sp_curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_people);

            sp_curso = findViewById(R.id.sp_curso);

            String[] cursos = {"Curso","Informatica","Administração","Hopedagem"};

            final List<String> listCursos = new ArrayList<String>(Arrays.asList(cursos));

            final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                    this,R.layout.support_simple_spinner_dropdown_item, listCursos){

                @Override
                public boolean isEnabled(int position){

                    if(position == 0){

                        // Disabilita a primeira posição (hint)
                        return false;

                    } else {
                        return true;
                    }
                }

                @Override
                public View getDropDownView(int position, View convertView,
                                            ViewGroup parent) {

                    View view = super.getDropDownView(position, convertView, parent);
                    TextView tv = (TextView) view;

                    if(position == 0){

                        // Deixa o hint com a cor cinza ( efeito de desabilitado)
                        tv.setTextColor(Color.GRAY);

                    }/*else {
                    tv.setTextColor(Color.BLACK);
                }*/

                    return view;
                }
            };

            spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            sp_curso.setAdapter(spinnerArrayAdapter);

            sp_curso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    String selectedItemText = (String) parent.getItemAtPosition(position);

                    if(position > 0){

                        // Ação realizada quando um elemento diferente
                        // do hint é selecionado
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

