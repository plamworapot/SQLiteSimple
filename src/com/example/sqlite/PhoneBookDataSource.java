package com.example.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PhoneBookDataSource {
	private SQLiteDatabase database;
	private SimpleSQLiteHelper dbHelper;
	
	private String[] allColumns = { SimpleSQLiteHelper.COLUMN_ID,
			SimpleSQLiteHelper.COLUMN_NAME, 
			SimpleSQLiteHelper.COLUMN_TEL,
			SimpleSQLiteHelper.COLUMN_EMAIL};
	
	
	
	public PhoneBookDataSource(Context context) {
		dbHelper = new SimpleSQLiteHelper(context);
	}
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	
	
	
	public MemberData insertMember(MemberData member) {
		ContentValues values = new ContentValues();
		values.put(SimpleSQLiteHelper.COLUMN_NAME, member.getName());
		values.put(SimpleSQLiteHelper.COLUMN_TEL, member.getTel());
		values.put(SimpleSQLiteHelper.COLUMN_EMAIL, member.getEmail());
		
		long insertId = database.insert(SimpleSQLiteHelper.TABLE_PHONES, null,
				values);
		
		Cursor cursor = database.query(SimpleSQLiteHelper.TABLE_PHONES,
			allColumns, SimpleSQLiteHelper.COLUMN_ID + " = " + insertId,
			null, null, null, null);
		cursor.moveToFirst();
		return cursorToMember(cursor);
	}
	
	
	public void deleteMember(MemberData member) {
		long id = member.getId();
		Log.d("MY SQLitte","Book deleted with id: " + id);
		database.delete(SimpleSQLiteHelper.TABLE_PHONES,
				SimpleSQLiteHelper.COLUMN_ID + " = " + id, null);
	}

	public void updateMember(MemberData member) {
		ContentValues values = new ContentValues();
		values.put(SimpleSQLiteHelper.COLUMN_NAME, member.getName());
		values.put(SimpleSQLiteHelper.COLUMN_TEL, member.getTel());
		values.put(SimpleSQLiteHelper.COLUMN_EMAIL, member.getEmail());
		
		database.update(SimpleSQLiteHelper.TABLE_PHONES, values,
				SimpleSQLiteHelper.COLUMN_ID + "=" + member.getId(), null);		
	}
	

	
	
	
	
	public List<MemberData> getAllMember() {
		List<MemberData> allmember = new ArrayList<MemberData>();
		Cursor cursor = database.query(SimpleSQLiteHelper.TABLE_PHONES,
				allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			MemberData member = cursorToMember(cursor);
			allmember.add(member);
			cursor.moveToNext();
		}		
		cursor.close();
		return allmember;
	}
	
	
	private MemberData cursorToMember(Cursor cursor) {
		MemberData phoneBook = new MemberData();
		phoneBook.setId(cursor.getLong(0));
		phoneBook.setName(cursor.getString(1));
		phoneBook.setTel(cursor.getString(2));
		phoneBook.setEmail(cursor.getString(3));		
		return phoneBook;
	}
	
	
	
	
	

}
