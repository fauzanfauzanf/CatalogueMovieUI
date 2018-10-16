package fikrims.io.moviecatalogueui.feature.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import fikrims.io.moviecatalogueui.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        setFragment();
    }

    void setFragment(){
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsPreferenceFragment()).commit();
    }
}
