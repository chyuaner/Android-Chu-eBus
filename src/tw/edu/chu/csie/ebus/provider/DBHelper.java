package tw.edu.chu.csie.ebus.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private final static String DATABASE_NAME = "Route.db";
    private final static int DATABASE_VERSION = 1;
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS BusTime ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "Route VARCHAR(30) NOT NULL, "
                + "Back BOOLEAN NOT NULL, "
                + "Time time NOT NULL, "
                + "Week INTEGER NOT NULL, "
                + "StertDate date, "
                + "EndDate date, "
                + "Company VARCHAR(100) "
                + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
