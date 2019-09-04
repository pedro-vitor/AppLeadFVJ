package com.NTI.AppFVJ.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.NTI.AppFVJ.Models.Comments;
import com.NTI.AppFVJ.Models.Leads;
import com.NTI.AppFVJ.Models.Users;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "FvjDB";

    private static final String TABLE_USERS = "Users";
    private static final String TABLE_LEADS = "Leads";
    private static final String TABLE_COMMENTS = "Comments";

    private static final String KEY_ID_USERS = "Id";
    private static final String KEY_NAME_USERS = "Name";
    private static final String KEY_EMAIL_USERS = "Email";
    private static final String KEY_USER_USERS = "User";
    private static final String KEY_PASSWORD_USERS = "Password";
    private static final String KEY_ACTIVE_USERS = "Active";
    private static final String KEY_TIMESTAMP_USERS = "TimeStamp";

    private static final String KEY_ID_LEADS = "Id";
    private static final String KEY_USERS_ID_LEADS = "Users_Id";
    private static final String KEY_NAME_LEADS = "Name";
    private static final String KEY_NUMBER_PHONE_LEADS = "Number_phone";
    private static final String KEY_DESIRED_COURSE_LEADS = "Desired_course";
    private static final String KEY_TOWN_LEADS = "Town";
    private static final String KEY_ADDRESS_LEADS = "Address";
    private static final String KEY_TIMESTAMP_LEADS = "TimeStamp";

    private static final String KEY_ID_COMMENT = "Id";
    private static final String KEY_LEADS_ID_COMMENT = "Leads_Id";
    private static final String KEY_USERS_ID_COMMENT = "Users_Id";
    private static final String KEY_TEXT_COMMENT = "Text";
    private static final String KEY_TIMESTAMP_COMMENT = "TimeStamp";


    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS
            + "(" + KEY_ID_USERS + " INTEGER  PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME_USERS + " TEXT,"
            + KEY_EMAIL_USERS + " TEXT,"
            + KEY_USER_USERS + "TEXT,"
            + KEY_PASSWORD_USERS + "TEXT,"
            + KEY_ACTIVE_USERS+ "INTEGER, "
            + KEY_TIMESTAMP_USERS+ "TEXT " + ")";

    private static final String CREATE_TABLE_LEADS = "CREATE TABLE " + TABLE_LEADS
            + "(" + KEY_ID_LEADS + " INTEGER  PRIMARY KEY AUTOINCREMENT,"
            + KEY_USERS_ID_LEADS + " INTEGER,"
            + KEY_NAME_LEADS + " TEXT,"
            + KEY_NUMBER_PHONE_LEADS + "TEXT,"
            + KEY_DESIRED_COURSE_LEADS + "TEXT,"
            + KEY_TOWN_LEADS + "INTEGER,"
            + KEY_ADDRESS_LEADS + "TEXT, "
            + KEY_TIMESTAMP_LEADS + "TEXT, "
            + "FOREIGN KEY(" + KEY_USERS_ID_LEADS + ") "
            + "REFERENCES " + TABLE_USERS + "(" + KEY_ID_USERS + ") "
            + "ON DELETE NO ACTION "
            + "ON UPDATE NO ACTION" + ")";

    private static final String CREATE_TABLE_COMMENTS = "CREATE TABLE " + TABLE_COMMENTS
            + "(" + KEY_ID_COMMENT + "INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_LEADS_ID_COMMENT + "INTEGER,"
            + KEY_USERS_ID_COMMENT + "INTEGER,"
            + KEY_TEXT_COMMENT + "TEXT,"
            + KEY_TIMESTAMP_COMMENT + "TEXT,"
            + "FOREIGN KEY (" + KEY_USERS_ID_COMMENT + ") "
            + "REFERENCES " + TABLE_USERS + "(" + KEY_ID_USERS + ") "
            + "ON DELETE NO ACTION ON UPDATE NO ACTION, "
            + "FOREIGN KEY(" + KEY_LEADS_ID_COMMENT + ") "
            + "REFERENCES " + TABLE_LEADS + "(" + KEY_ID_LEADS +")"
            + "ON DELETE NO ACTION ON UPDATE NO ACTION " + ")";



    public DataHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USERS);
        sqLiteDatabase.execSQL(CREATE_TABLE_LEADS);
        sqLiteDatabase.execSQL(CREATE_TABLE_COMMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LEADS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);

        // create new tables
        onCreate(sqLiteDatabase);
    }

    //TODO GetAll
    public List<Users> GetAllUsers(){
        List<Users> users = new ArrayList<Users>();
        String Query = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if(cursor.moveToFirst()){
            while(cursor.moveToFirst()){
                Users user = new Users();
                user.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_USERS)));
                user.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_USERS)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_USERS)));
                user.setUser(cursor.getString(cursor.getColumnIndex(KEY_USER_USERS)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD_USERS)));
                user.setActive(cursor.getInt(cursor.getColumnIndex(KEY_ACTIVE_USERS)));
                user.setTimeStamp(cursor.getString(cursor.getColumnIndex(KEY_TIMESTAMP_USERS)));

                users.add(user);
            }
        }
        return users;
    }

    public List<Leads> GetAllLeads(){
        List<Leads> leads = new ArrayList<Leads>();
        String Query = "SELECT * FROM " + TABLE_LEADS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if(cursor.moveToFirst()){
            while(cursor.moveToFirst()){
                Leads lead = new Leads();
                lead.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_LEADS)));
                lead.setUsers_Id(cursor.getInt(cursor.getColumnIndex(KEY_USERS_ID_LEADS)));
                lead.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_LEADS)));
                lead.setNumber_phone(cursor.getString(cursor.getColumnIndex(KEY_NUMBER_PHONE_LEADS)));
                lead.setDesired_course(cursor.getString(cursor.getColumnIndex(KEY_DESIRED_COURSE_LEADS)));
                lead.setTown(cursor.getString(cursor.getColumnIndex(KEY_TOWN_LEADS)));
                lead.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_LEADS)));

                leads.add(lead);
            }
        }
        return leads;
    }

    public List<Comments> GetAllComments(){
        List<Comments> comments = new ArrayList<Comments>();
        String Query = "SELECT * FROM " + TABLE_COMMENTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if(cursor.moveToFirst()){
            while(cursor.moveToFirst()){
                Comments comment = new Comments();
                comment.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_LEADS)));
                comment.setLeads_Id(cursor.getInt(cursor.getColumnIndex(KEY_LEADS_ID_COMMENT)));
                comment.setUsers_Id(cursor.getInt(cursor.getColumnIndex(KEY_USERS_ID_COMMENT)));
                comment.setText(cursor.getString(cursor.getColumnIndex(KEY_TEXT_COMMENT)));
                comment.setTimeStamp(cursor.getString(cursor.getColumnIndex(KEY_TIMESTAMP_COMMENT)));

                comments.add(comment);
            }
        }
        return comments;
    }

    /*TODO INSERT*/
    public long insertUsers(Users users) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME_USERS, users.getName());
        values.put(KEY_EMAIL_USERS, users.getEmail());
        values.put(KEY_USER_USERS, users.getUser());
        values.put(KEY_PASSWORD_USERS, users.getPassword());
        values.put(KEY_ACTIVE_USERS, users.getActive());
        values.put(KEY_TIMESTAMP_USERS, users.getTimeStamp());

        long id = db.insert(TABLE_USERS, null, values);

        return id;
    }

    public long insertLeads(Leads leads) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERS_ID_LEADS, leads.getUsers_Id());
        values.put(KEY_NAME_LEADS, leads.getName());
        values.put(KEY_NUMBER_PHONE_LEADS, leads.getNumber_phone());
        values.put(KEY_DESIRED_COURSE_LEADS, leads.getDesired_course());
        values.put(KEY_TOWN_LEADS, leads.getTown());
        values.put(KEY_TIMESTAMP_LEADS, leads.getTimeStamp());

        long id = db.insert(TABLE_LEADS, null, values);

        return id;
    }

    public long insertComments(Comments comments) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LEADS_ID_COMMENT, comments.getLeads_Id());
        values.put(KEY_USERS_ID_COMMENT, comments.getUsers_Id());
        values.put(KEY_TEXT_COMMENT, comments.getText());
        values.put(KEY_TIMESTAMP_COMMENT, comments.getTimeStamp());

        long id = db.insert(TABLE_COMMENTS, null, values);

        return id;
    }

    /*TODO UPDATE*/
    public List<Users> updateUsers(Users users){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Users> list;

        ContentValues values = new ContentValues();
        values.put(KEY_NAME_USERS, users.getName());
        values.put(KEY_EMAIL_USERS, users.getEmail());
        values.put(KEY_USER_USERS, users.getUser());
        values.put(KEY_PASSWORD_USERS, users.getPassword());
        values.put(KEY_ACTIVE_USERS, users.getActive());
        values.put(KEY_TIMESTAMP_USERS, users.getTimeStamp());

        db.update(TABLE_USERS,values,KEY_ID_USERS + " = ?", new String[]{String.valueOf(users.getId())});

        list = this.GetAllUsers();

        return list;
    }

    public List<Leads> updateLeads(Leads leads){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Leads> list;

        ContentValues values = new ContentValues();
        values.put(KEY_USERS_ID_LEADS, leads.getUsers_Id());
        values.put(KEY_NAME_LEADS, leads.getName());
        values.put(KEY_NUMBER_PHONE_LEADS, leads.getNumber_phone());
        values.put(KEY_DESIRED_COURSE_LEADS, leads.getDesired_course());
        values.put(KEY_TOWN_LEADS, leads.getTown());
        values.put(KEY_TIMESTAMP_LEADS, leads.getTimeStamp());

        db.update(TABLE_USERS,values,KEY_ID_LEADS + " = ?", new String[]{String.valueOf(leads.getId())});

        list = this.GetAllLeads();

        return list;
    }

    public List<Comments> updateComments(Comments comments){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Comments> list;

        ContentValues values = new ContentValues();
        values.put(KEY_LEADS_ID_COMMENT, comments.getLeads_Id());
        values.put(KEY_USERS_ID_COMMENT, comments.getUsers_Id());
        values.put(KEY_TEXT_COMMENT, comments.getText());
        values.put(KEY_TIMESTAMP_COMMENT, comments.getTimeStamp());

        db.update(TABLE_COMMENTS, values, KEY_ID_COMMENT + "= ?", new String[] {String.valueOf(comments.getId())});

        list = this.GetAllComments();

        return list;
    }
}
