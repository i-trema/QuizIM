package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
        private EditText editTextName;
        private TextView textViewAccueil;
        private ImageView imageViewSupprimer;
        private Button btnCommencer;
        private boolean btnsEnabled;
        protected static int score;
        protected static String nomUserActuel;
        Toast m_currentToast = null;
        List<User> users = new ArrayList<>();
        SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewAccueil = findViewById(R.id.textViewAccueil);
        editTextName = findViewById(R.id.editTextName);
        imageViewSupprimer = findViewById(R.id.imageViewSupprimer);
        btnCommencer = findViewById(R.id.buttonCommencer);

        sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
        sharedPreferences.getAll();
        Log.i("users list", sharedPreferences.getAll().toString());

        disableBtns();

        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //TODO
                String nomSaisi = editTextName.getText().toString();

                if (nomSaisi.length() > 12){
                    afficherToast("12 caractères maximum !");
                    editTextName.setText(nomSaisi.substring(0,nomSaisi.length()-1));
                    editTextName.setSelection(nomSaisi.length()-1);

                }
                if (!nomSaisi.isEmpty() && nomSaisi.substring(nomSaisi.length() - 1).matches("[^A-Za-z0-9 _@.-]")) {
                    afficherToast("caractère interdit : "+nomSaisi.substring(nomSaisi.length() - 1));
                    editTextName.setText(nomSaisi.substring(0,nomSaisi.length()-1));
                    editTextName.setSelection(nomSaisi.length()-1);

                } else {
                    String messageScore = "Bonjour " + nomSaisi + " ! \n Votre dernier score est de "
                            + sharedPreferences.getString(nomSaisi, "") + "/4";
                    if (!btnsEnabled && !(nomSaisi.equals(""))) {
                        enableBtns();
                    } else if (nomSaisi.equals("")) {
                        disableBtns();
                        textViewAccueil.setText(R.string.accueil_text);
                    }
                    if (sharedPreferences.getAll().containsKey(nomSaisi)) {
                        Log.i("ok", "l'utilisateur existe dans la liste");
                        textViewAccueil.setText(messageScore);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void enableBtns() {
        btnCommencer.setEnabled(true);
        imageViewSupprimer.setEnabled(true);
        btnCommencer.setBackgroundColor(getColor(R.color.green_accueil));
        btnCommencer.setTextColor(getColor(R.color.grey_disabled_text));
        imageViewSupprimer.setColorFilter(getColor(R.color.red_supprimer));
        btnsEnabled = true;
        Log.i("ok","boutons activés");
    }

    public void disableBtns() {
        btnCommencer.setEnabled(false);
        imageViewSupprimer.setEnabled(false);
        btnCommencer.setBackgroundColor(getColor(R.color.grey_disabled));
        btnCommencer.setTextColor(getColor(R.color.grey_disabled_text));
        imageViewSupprimer.setColorFilter(getColor(R.color.grey_disabled));
        btnsEnabled = false;
        Log.i("ok","boutons désactivés");
    }


    public void commencerQuiz(View view){
        String nom = editTextName.getText().toString();

        if (nom.length()<3){
            afficherToast("le nom doit contenir au moins 3 caractères");
            return;
        }
        sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
//        sharedPreferences.edit().clear().commit();
        nomUserActuel = nom;
        if (btnCommencer.isEnabled()) {
            if (!(sharedPreferences.contains(nomUserActuel))) {

                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(nomUserActuel, "").apply();

                User user = new User();
                user.setNom(nomUserActuel);
                users.add(user);
            }
            score = 0;
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            startActivity(intent);

//            Log.i("utilisateur enregistré ok",users.toString()+users.size());
        }
    }

    public void effacerNom(View view){
        editTextName.setText("");
        disableBtns();
    }

    public void afficherToast(String message){
        if(m_currentToast != null){
            m_currentToast.cancel();
        }
        m_currentToast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG);
        m_currentToast.show();
    }

}