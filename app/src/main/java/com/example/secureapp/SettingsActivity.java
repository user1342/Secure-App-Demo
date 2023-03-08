package com.example.secureapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        protected native int calculateSignatureHash(String signature);
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
            Preference signaturePreference = findPreference("signature");
            signaturePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                if (preference.getKey().equals("signature")) {
                    int signatureHash = calculateSignatureHash(newValue.toString());
                    String signature = newValue + " <" + signatureHash + ">";
                    Toast.makeText(this.getContext(), "Signature: " + signature, Toast.LENGTH_SHORT).show();
                    preference.getSharedPreferences().edit().putString("signature", signature).apply();
                    return false;
                }
                return true;
            });
        }
    }
}