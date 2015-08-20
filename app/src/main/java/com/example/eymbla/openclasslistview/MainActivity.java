package com.example.eymbla.openclasslistview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    /** Affichage de la liste des sexes **/
    private ListView mListSexe = null;
    /** Affichage de la liste des langages connus **/
    private ListView mListProg = null;
    /** Bouton pour envoyer le sondage **/
    private Button mSend = null;
    /** Contient les deux sexes **/
    private String[] mSexes = {"Masculin", "Feminin"};
    /** Contient différents langages de programmation **/
    private String[] mLangages = null;

    private Spinner liste = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //On récupère les trois vues définies dans notre layout
        mListSexe = (ListView)findViewById(R.id.listSexe);
        mListProg = (ListView)findViewById(R.id.listProg);
        mSend = (Button)findViewById(R.id.send);

        //Une autre manière de créer un tableau de chaînes de caractères
        mLangages = new String[]{"C", "Java", "COBOL", "Perl"};

        //On ajoute un adaptateur qui affiche des boutons radio (c'est l'affichage à considérer quand on ne peut
        //sélectionner qu'un élément d'une liste)
        mListSexe.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, mSexes));
        //On déclare qu'on sélectionne de base le premier élément (Masculin)
        mListSexe.setItemChecked(0, true);

        //On ajoute un adaptateur qui affiche des cases à cocher (c'est l'affichage à considérer quand on peut sélectionner
        //autant d'éléments qu'on veut dans une liste)
        mListProg.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, mLangages));
        //On déclare qu'on sélectionne de base le second élément (Féminin)
        mListProg.setItemChecked(1, true);

        //Test Spinner
        liste = (Spinner)findViewById(R.id.spinner1);
        List<String> exemple = new ArrayList<>();
        exemple.add("Element 1");
        exemple.add("Element 2");
        exemple.add("Element 3");
        exemple.add("Element 4");
        exemple.add("Element 5");
        exemple.add("Element 6");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, exemple);
        //Le layout par défaut est android.R.layout.simple_spinner_dropdown_item
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        liste.setAdapter(adapter);

        //Que se passe-t-il dès qu'on clique sur le bouton ?
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Merci ! Les données ont été envoyées !", Toast.LENGTH_LONG).show();
                //On déclare qu'on ne peut plus sélectionner d'élément
                mListSexe.setChoiceMode(ListView.CHOICE_MODE_NONE);
                //On affiche un layout qui ne permet pas de sélection
                mListSexe.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, mSexes));

                //On déclare qu'on ne peut plus sélectionner d'élément
                mListProg.setChoiceMode(ListView.CHOICE_MODE_NONE);
                //On affiche un layout qui ne permet pas de sélection
                mListProg.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, mLangages));

                //On désactive le bouton
                mSend.setEnabled(false);
            }
        });
    }
}
