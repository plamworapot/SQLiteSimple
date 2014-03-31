package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SimpleSQLiteHelper extends SQLiteOpenHelper {
	public static final String TABLE_PHONES = "phonebook";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "_name";
	public static final String COLUMN_TEL = "_tel";
	public static final String COLUMN_EMAIL = "_email";	
	private static final String DB_NAME = "phonebook.db";
	private static final int DB_VERSION = 1;
	
	
	
	private static final String DATABASE_CREATE = "create table " 
			+ TABLE_PHONES + "( " 
			+ COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_NAME + " text," 
			+ COLUMN_TEL + " text," 			 
			+ COLUMN_EMAIL + " text" + ");";
	
	
	
	
	public SimpleSQLiteHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SimpleSQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_PHONES);
		onCreate(db);
		
	}

	
}
