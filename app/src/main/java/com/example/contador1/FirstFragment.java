package com.example.contador1;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.contador1.databinding.FragmentFirstBinding;


public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private int contador;
    TextView textoResultado;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        contador = 0;

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        textoResultado = binding.contadorTexto;
        EventoTeclado teclado = new EventoTeclado();
        EditText n_reseteo = binding.nReseteo;
        n_reseteo.setOnEditorActionListener(teclado);
        return binding.getRoot();

    }

    class EventoTeclado implements TextView.OnEditorActionListener{

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if(actionId==EditorInfo.IME_ACTION_DONE){
                resetearContador(null);
            }
            return false;
        }
    }

    public void incrementarContador(View view){
        contador++;
        textoResultado.setText("" + contador);
    }

    public void decrementarContador(View view){
        contador--;
        if(contador < 0){
            CheckBox negativos = binding.negativos;
            if(!negativos.isChecked()){
                contador = 0;
            }
        }
        textoResultado.setText("" + contador);
    }

    public void resetearContador(View view) {
        EditText numero_reset = binding.nReseteo;

        try {
            contador = Integer.parseInt(numero_reset.getText().toString());
        }catch(Exception e){
            contador = 0;
        }
        numero_reset.setText("");
        textoResultado.setText("" + contador);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementarContador(view);
            }
        });
        binding.buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementarContador(view);
            }
        });
        binding.buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetearContador(view);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}