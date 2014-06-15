package tw.edu.chu.csie.ebus.provider;

import java.text.SimpleDateFormat;
import java.util.Date;

import tw.edu.chu.csie.format.BusService;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBProvider {

	private Context context;
	private DBHelper dbHelper;
    private SQLiteDatabase db;
    
    /**
     * Date物件轉成資料庫存入字串
     * @param _date 日期時間物件
     * @return 符合SQLite存入字串
     */
    private String dateToString(Date _date) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat(
    	        "yyyy-MM-dd HH:mm:ss"); 
    	return dateFormat.format(_date);
    }
    
    /**
     * DBProvider建構子
     * @param _context
     */
    public DBProvider(Context _context) {
        this.context = _context;
        
        dbHelper = new DBHelper(this.context);
        db = dbHelper.getWritableDatabase();
    }
    
    /**
     * 寫入此班次進資料庫
     * @param _theBusService 公車班次物件
     */
    public void insertBusService(BusService _theBusService) {
        
        ContentValues cv = new ContentValues();
        cv.clear();
        cv.put("Route", _theBusService.getRoute());
        cv.put("Back", _theBusService.isBack());
        // TODO
        SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm" );  
        cv.put("Time", sdf.format(_theBusService.getTime()) );
        cv.put("Week", _theBusService.getWeek() );
        cv.put("StertDate", dateToString(_theBusService.getStartDate()) );
        cv.put("EndDate", dateToString(_theBusService.getEndDate()) );
        cv.put("Company", _theBusService.getCompany() );
        db.insert("BusTime", null, cv);
    }
	
    /**
     * 取得所有路線名單
     * @return 所有路線陣列
     * 
     * 取得的名單會依照名稱排序
     */
    public String[] getRouteToStringArray() {
        Cursor cursor = db.query("BusTime", new String[]{"Route"}, null, null, "Route", null, "Route DESC");
        
        String[] output = new String[cursor.getCount()];
        int index=0;
        while(cursor.moveToNext()){
            output[index] = cursor.getString(0);
            index++;
        }
        
        return output;
    }
    
    
    /**
     * 以路線查詢
     * @param _route 路線名稱
     * @return 查學結果物件
     */
    public Cursor queryBusServiceForRoute(String _route) {
        Cursor cursor = db.query("BusTime", null, "Route=?",new String[]{_route}, null, null, "Time");
        return cursor;
    }
    
}
