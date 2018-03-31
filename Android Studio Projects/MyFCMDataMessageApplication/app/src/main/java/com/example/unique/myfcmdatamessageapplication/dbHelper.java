package com.example.unique.myfcmdatamessageapplication;

import java.util.ArrayList;

//import com.sos.data.ItemModel;
//import com.sos.data.OfferModel;
//import com.sos.data.RouteModel;
//import com.sos.data.RoutemapModel;
//import com.sos.data.SalesmanModel;
//import com.sos.data.StoreModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class dbHelper extends SQLiteOpenHelper {
    SQLiteDatabase _db;
    //  Table salesman
    public static String tbl_Salesman = "tbl_Timer";
    String salesman_id = "_id";
    public static String salesman_code = "_timerid";
    public static String salesman_name = "_time";
    public static String salesman_status = "_status";


    String createSalesman = "CREATE TABLE " + tbl_Salesman + " (" + salesman_id + " INTEGER PRIMARY KEY AUTOINCREMENT," + salesman_code + " TEXT," +
            salesman_name + " TEXT,"+salesman_status+" TEXT);";

    public void insertSalesmen(pojoTimer[] _arrlist) {
        _db = getWritableDatabase();
        String _insert = "insert into " + tbl_Salesman + "(" + salesman_code + "," +
                salesman_name + ")values (?,?)";

        try {
            SQLiteStatement localSQLiteStatement = _db.compileStatement(_insert);
            _db.beginTransaction();
            for (pojoTimer _salesmanModel : _arrlist) {
                ContentValues cv = _salesmanModel.getContentValue();

                    localSQLiteStatement.bindString(1, cv.getAsString(salesman_code));
                    localSQLiteStatement.bindString(2, cv.getAsString(salesman_name));

                    long l = localSQLiteStatement.executeInsert();
                    Log.v("Insert Response : ", l + "- Timer Table");
            }
            _db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _db.endTransaction();
            _db.close();
        }
    }

    public dbHelper(Context context, String name, CursorFactory factory,
                    int version) {
        //		super(context, name, factory, version);
        super(context, "SOS", factory, 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(createSalesman);
        Log.v("ALl Tables", "Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}