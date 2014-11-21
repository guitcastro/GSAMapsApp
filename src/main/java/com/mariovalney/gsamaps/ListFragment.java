package com.mariovalney.gsamaps;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mariovalney.gsamaps.data.DataManager;
import com.mariovalney.gsamaps.data.DataContract.AmbassadorEntry;

/**
 * Created by neo on 18/11/14.
 */
public class ListFragment extends Fragment {

    private ListAdapter listAdapter;

    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.list_fragment, container, false);
        final ListView listView = (ListView) rootView.findViewById(R.id.listview_lista);

        // Ler os dados armazenados
        DataManager dataManager = new DataManager();
        Cursor cursor = dataManager.readAllData(getActivity());

        if (cursor != null) {

            // tá quebrando aqui

            listAdapter = new ListAdapter(getActivity(), cursor, 0);

            // Seta o adaptador à listview
            listView.setAdapter(listAdapter);

            // Implementando o Clique no item da listagem de Embaixadores
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Cursor cursor = listAdapter.getCursor();

                    if (cursor != null && cursor.moveToPosition(position)) {

                        // Lendo o Cursor
                        String nome = cursor.getString(
                                cursor.getColumnIndex(AmbassadorEntry.COLUMN_NAME_NOME));
                        String instituicao = cursor.getString(
                                cursor.getColumnIndex(AmbassadorEntry.COLUMN_NAME_INSTITUICAO));
                        float lat = cursor.getFloat(
                                cursor.getColumnIndex(AmbassadorEntry.COLUMN_NAME_LATITUDE));
                        float lng = cursor.getFloat(
                                cursor.getColumnIndex(AmbassadorEntry.COLUMN_NAME_LONGITUDE));

                        // Preenchendo e enviando o INTENT
                        Intent intent = new Intent(getActivity(), AmbassadorActivity.class)
                                .putExtra(AmbassadorActivity.INTENT_NOME, nome)
                                .putExtra(AmbassadorActivity.INTENT_INSTITUICAO, instituicao)
                                .putExtra(AmbassadorActivity.INTENT_LAT, lat)
                                .putExtra(AmbassadorActivity.INTENT_LNG, lng);
                        startActivity(intent);
                    }
                }
            });

        } else {
            Log.d("CURSOR in List Fragment", "Cursor is null");
        }

        return rootView;
    }
}
