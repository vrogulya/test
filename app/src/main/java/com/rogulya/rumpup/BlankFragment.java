package com.rogulya.rumpup;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements AdapterView.OnItemClickListener {
    static final String[] FRUITS = new String[]{"Apple", "Avocado", "Banana",
            "Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
            "Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple"};

    public BlankFragment() {
        // Required empty public constructor
    }

    public interface OnListItemSelectedListener {
        void onListItemSelected(int itemIndex);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        ListView lvMain = (ListView) view.findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),
                android.R.layout.simple_list_item_1, FRUITS);
        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view1, int pos, long l) {
        OnListItemSelectedListener listener = (OnListItemSelectedListener) getActivity();
        listener.onListItemSelected(pos);
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("pos", position);
//    }
}
