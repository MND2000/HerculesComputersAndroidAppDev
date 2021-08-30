package za.ac.cput.herculescomputers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "registeruser";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "username";
    public static final String COL_3 = "password";

    public static final String TABLE_NAME2 = "Stock";
    public static final String COL_MAN = "Manufacturer";
    public static final String COL_NAME = "Name";
    public static final String COL_PRICE = "Price";
    public static final String COL_QUANTITY = "Quantity";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE stock (ID INTEGER PRIMARY  KEY AUTOINCREMENT, manufacturer TEXT, name TEXT, price TEXT, quantity TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(sqLiteDatabase);
    }

    public long addUser(String user, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user);
        contentValues.put("password", password);
        long res = db.insert("registeruser", null, contentValues);
        db.close();
        return res;
    }

    public boolean checkUser(String username, String password) {
        String[] columns = {COL_1};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count > 0)
            return true;
        else
            return false;
    }

    public boolean insertDetails(String manufacturer, String name, String price, String quantity) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_MAN, manufacturer);
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_PRICE, price);
        contentValues.put(COL_QUANTITY, quantity);

        long result = db.insert(TABLE_NAME2, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME2, null);
        return res;


    }

    public boolean updateData(String manufacturer, String name, String price, String quantity) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_MAN, manufacturer);
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_PRICE, price);
        contentValues.put(COL_QUANTITY, quantity);

        db.update(TABLE_NAME2, contentValues, "Manufacturer = ?", new String[]{manufacturer});
        return true;


    }

    public Integer deleteData(String manufacturer) {

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME2, "Manufacturer = ?", new String[]{manufacturer});


    }

            public Cursor fetchData(String manufacturer, String name, String price, String quantity ){


            SQLiteDatabase database = getReadableDatabase();

            Cursor cursor = database.query(TABLE_NAME2, new String[]{COL_MAN,COL_NAME,COL_PRICE,COL_QUANTITY},COL_MAN + "=?",new String[]{COL_MAN},null,null,null );
            return cursor;
        }



        }


