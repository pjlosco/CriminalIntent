package com.bignerdranch.android.criminalintent.app.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by patrick on 4/24/14.
 */
public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private Context mAppContext;

    private ArrayList<Crime> mCrimes;

    private CrimeLab(Context appContext) {
        mAppContext = appContext;
        mCrimes = new ArrayList<Crime>();
    }

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context.getApplicationContext());
        }
        return sCrimeLab;
    }

    public void addCrime(Crime c) {
        mCrimes.add(c);
    }

    public ArrayList<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID uuid) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(uuid)) {
                return crime;
            }
        }
        return null;
    }

}
