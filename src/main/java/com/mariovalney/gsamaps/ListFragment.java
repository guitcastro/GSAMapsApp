package com.mariovalney.gsamaps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by neo on 18/11/14.
 */
public class ListFragment extends Fragment {

    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Criando apenas dados fantasma
        String[] data = {
                "Fulano de Tal 1",
                "Fulano de Tal 2",
                "Fulano de Tal 3",
                "Fulano de Tal 4",
                "Fulano de Tal 5",
                "Fulano de Tal 6",
                "Fulano de Tal 7",
                "Fulano de Tal 8",
                "Fulano de Tal 9",
                "Fulano de Tal 10",
                "Fulano de Tal 11",
                "Fulano de Tal 12",
                "Fulano de Tal 13",
                "Fulano de Tal 14"
        };
        List<String> listaDeEmbaixadores = new ArrayList<String>(Arrays.asList(data));

        // Criando o adaptador da lista
        final ArrayAdapter<String> listaDeEmbaixadoresAdapter =
                new ArrayAdapter<String>(
                        getActivity(), // The current context (this activity)
                        R.layout.list_item_ambassadors, // The name of the layout ID.
                        R.id.list_item_textview_nome, // The ID of the textview to populate.
                        listaDeEmbaixadores);

        View rootView = inflater.inflate(R.layout.list_fragment, container, false);

        // Seta o adaptador à listview
        ListView listView = (ListView) rootView.findViewById(R.id.listview_ambassadors);
        listView.setAdapter(listaDeEmbaixadoresAdapter);

        // Implementando o Clique no item da listagem de Embaixadores
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String embaixador = listaDeEmbaixadoresAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), AmbassadorActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, embaixador);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
