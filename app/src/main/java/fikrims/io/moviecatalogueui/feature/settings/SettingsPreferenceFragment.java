package fikrims.io.moviecatalogueui.feature.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.ButterKnife;
import fikrims.io.moviecatalogueui.R;
import fikrims.io.moviecatalogueui.data.model.response.MovieResult;
import fikrims.io.moviecatalogueui.data.remote.BaseApiService;
import fikrims.io.moviecatalogueui.data.remote.UtilsApi;
import fikrims.io.moviecatalogueui.data.service.reminder.DailyAlarmReceiver;
import fikrims.io.moviecatalogueui.data.service.reminder.UpcomingAlarmReceiver;


public class SettingsPreferenceFragment extends PreferenceFragment
        implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener, SettingsPresenter.SettingsListener {

    @BindString(R.string.key_setting_locale)
    String keySettingLocale;
    @BindString(R.string.key_reminder_daily)
    String keyDailyReminder;
    @BindString(R.string.key_reminder_upcoming)
    String keyUpcomingReminder;

    private SwitchPreference switchDailyReminder;
    private SwitchPreference switchUpcomingReminder;

    private UpcomingAlarmReceiver alarmReceiver ;
    private List<MovieResult> movies = new ArrayList<>();
    private SettingsPresenter settingsPresenter;
    private BaseApiService mApiService;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_notification);

        ButterKnife.bind(this, getActivity());

        alarmReceiver = new UpcomingAlarmReceiver();

        switchDailyReminder = (SwitchPreference) findPreference(keyDailyReminder);
        switchDailyReminder.setOnPreferenceChangeListener(this);
        switchUpcomingReminder = (SwitchPreference) findPreference(keyUpcomingReminder);
        switchUpcomingReminder.setOnPreferenceChangeListener(this);
        findPreference(keySettingLocale).setOnPreferenceClickListener(this);

        setUp();
    }

    void setUp(){
        context = getActivity();
        mApiService = UtilsApi.getAPIService(context);
        settingsPresenter = new SettingsPresenter(context, mApiService, this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();

        if (key.equals(keySettingLocale)) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
            return true;
        }

        return false;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        String key = preference.getKey();
        boolean isOn = (boolean) o;

        DailyAlarmReceiver dailyAlarmReceiver = new DailyAlarmReceiver();

        if (key.equals(keyDailyReminder)) {
            if (isOn) {
                dailyAlarmReceiver.setRepeatingAlarm(getActivity());
            } else {
                dailyAlarmReceiver.cancelAlarm(getActivity());
            }
        } else {
            if (isOn) {
//                presenter.setRepeatingAlarm();
                settingsPresenter.doMovieUpcoming();

            } else {
                settingsPresenter.cancelAlarmNotif();

            }
        }

        return true;

    }

    @Override
    public void getMovie(MovieResult movie) {
        Log.i("MovieDate", movie.getReleaseDate());
        this.movies.clear();
        this.movies.add(movie);
        alarmReceiver.setRepeatingAlarm(context, movies);
    }

    @Override
    public void cancelAlarm() {
        alarmReceiver.cancelAlarm(context);
    }

    @Override
    public void error(Throwable throwable) {

    }
}
