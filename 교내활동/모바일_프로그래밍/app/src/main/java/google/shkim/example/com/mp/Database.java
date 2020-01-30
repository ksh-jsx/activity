package google.shkim.example.com.mp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        db.execSQL("CREATE TABLE IF NOT EXISTS infos (_id INTEGER PRIMARY KEY AUTOINCREMENT, PlaceName TEXT, Year INTERGER, Month INTERGER, Date INTERGER, Hour INTERGER, Minute INTERGER, Lat DOUBLE, Lng DOUBLE, PlaceAdd TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS markerPoint (PlaceName TEXT, Lat DOUBLE, Lng DOUBLE);");
        db.execSQL("CREATE TABLE IF NOT EXISTS tempSave (PlaceName TEXT, fULLDATE TEXT, Hour INTERGER, Minute INTERGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS spinnerSelect (SpinnerName TEXT);");
        db.execSQL("CREATE TABLE IF NOT EXISTS baggage (item TEXT);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public Cursor select(String _query){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(_query,null);
    }

    public void delete(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();

    }

}
