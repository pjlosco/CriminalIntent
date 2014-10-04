package com.bignerdranch.android.criminalintent.app.controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.bignerdranch.android.criminalintent.app.R;
import com.bignerdranch.android.criminalintent.app.model.Crime;
import com.bignerdranch.android.criminalintent.app.model.CrimeLab;

import java.util.ArrayList;

/**
 * Created by patrick on 4/24/14.
 */
public class CrimeListFragment extends ListFragment {

    private static final String TAG = CrimeListFragment.class.getName();

    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        mCrimes = CrimeLab.get(getActivity()).getCrimes();

        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Crime crime = ((CrimeAdapter) getListAdapter()).getItem(position);
        Intent intent = new Intent(getActivity(), CrimePagerActivity.class);
        intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }

    private class CrimeAdapter extends ArrayAdapter<Crime> {

        public CrimeAdapter(ArrayList<Crime> crimes) {
            super(getActivity(), 0, crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
            }
            Crime crime = getItem(position);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.crime_list_item_title_textView);
            titleTextView.setText(crime.getTitle());
            TextView dateTextView = (TextView) convertView.findViewById(R.id.crime_list_item_date_textView);
            dateTextView.setText(crime.getDate().toString());
            CheckBox solvedCheckBox = (CheckBox) convertView.findViewById(R.id.crime_list_item_solved_checkBox);
            solvedCheckBox.setChecked(crime.isSolved());
            return convertView;
        }
    }
}
