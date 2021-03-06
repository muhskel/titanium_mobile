package ti.modules.titanium.ui.android;

import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.util.Log;
import org.appcelerator.titanium.util.TiRHelper;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class TiPreferencesActivity extends PreferenceActivity 
{
	private static final String LCAT = "TiPreferencesActivity";
	private static final String DEFAULT_PREFS_RNAME = "preferences";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String prefsName = DEFAULT_PREFS_RNAME;
		if (getIntent().hasExtra("prefsName")) {
			String name = getIntent().getExtras().getString("prefsName");
			if (name != null && name.length() > 0) {
				prefsName = name;
			}
		}
		
		// Find the layout file, do nothing if not found
		try {
			getPreferenceManager().setSharedPreferencesName(TiApplication.APPLICATION_PREFERENCES_NAME);
			int resid = TiRHelper.getResource("xml." + prefsName);
			if (resid != 0) {
				addPreferencesFromResource(resid);
			} else {
				Log.e(LCAT, "xml." + prefsName + " preferences not found.");
				finish();
				return;
			}
		} catch (TiRHelper.ResourceNotFoundException e) {
			Log.e(LCAT, "Error loading preferences: " + e.getMessage());
			finish();
			return;
		}
	}
}
