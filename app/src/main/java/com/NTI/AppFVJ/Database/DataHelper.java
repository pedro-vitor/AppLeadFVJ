package com.NTI.AppFVJ.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.NTI.AppFVJ.Models.Comment;
import com.NTI.AppFVJ.Models.Lead;
import com.NTI.AppFVJ.Models.User;

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
    private static final String KEY_CREATEDAT_USERS = "CreatedAt";

    private static final String KEY_ID_LEADS = "Id";
    private static final String KEY_USERS_ID_LEADS = "Users_Id";
    private static final String KEY_NAME_LEADS = "Name";
    private static final String KEY_EMAIL_LEADS = "Email";
    private static final String KEY_NUMBER_PHONE_LEADS = "Number_phone";
    private static final String KEY_DESIRED_COURSE_LEADS = "Desired_course";
    private static final String KEY_TOWN_LEADS = "Town";
    private static final String KEY_ADDRESS_LEADS = "Address";
    private static final String KEY_CREATEDAT_LEADS = "CreatedAt";

    private static final String KEY_ID_COMMENT = "Id";
    private static final String KEY_LEADS_ID_COMMENT = "Leads_Id";
    private static final String KEY_USERS_ID_COMMENT = "Users_Id";
    private static final String KEY_TEXT_COMMENT = "Text";
    private static final String KEY_CREATEDAT_COMMENT = "CreatedAt";


    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS
            + "(" + KEY_ID_USERS + " INTEGER  PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAME_USERS + " TEXT,"
            + KEY_EMAIL_USERS + " TEXT,"
            + KEY_USER_USERS + " TEXT,"
            + KEY_PASSWORD_USERS + " TEXT,"
            + KEY_ACTIVE_USERS + " INTEGER, "
            + KEY_CREATEDAT_USERS + " TEXT " + ")";

    private static final String CREATE_TABLE_LEADS = "CREATE TABLE " + TABLE_LEADS
            + "(" + KEY_ID_LEADS + " INTEGER  PRIMARY KEY AUTOINCREMENT,"
            + KEY_USERS_ID_LEADS + " INTEGER,"
            + KEY_NAME_LEADS + " TEXT,"
            + KEY_EMAIL_LEADS + " TEXT,"
            + KEY_NUMBER_PHONE_LEADS + " TEXT,"
            + KEY_DESIRED_COURSE_LEADS + " TEXT,"
            + KEY_TOWN_LEADS + " TEXT,"
            + KEY_ADDRESS_LEADS + " TEXT, "
            + KEY_CREATEDAT_LEADS + " TEXT, "
            + "FOREIGN KEY(" + KEY_USERS_ID_LEADS + ") "
            + "REFERENCES " + TABLE_USERS + "(" + KEY_ID_USERS + ") "
            + "ON DELETE NO ACTION "
            + "ON UPDATE NO ACTION" + ")";

    private static final String CREATE_TABLE_COMMENTS = "CREATE TABLE " + TABLE_COMMENTS
            + "(" + KEY_ID_COMMENT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_LEADS_ID_COMMENT + " INTEGER,"
            + KEY_USERS_ID_COMMENT + " INTEGER,"
            + KEY_TEXT_COMMENT + " TEXT,"
            + KEY_CREATEDAT_COMMENT + " TEXT,"
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
    public List<User> GetAllUsers(){
        List<User> users = new ArrayList<User>();
        String Query = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_USERS)));
                user.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_USERS)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_USERS)));
                user.setUser(cursor.getString(cursor.getColumnIndex(KEY_USER_USERS)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD_USERS)));
                user.setActive(cursor.getInt(cursor.getColumnIndex(KEY_ACTIVE_USERS)));
                user.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_USERS)));

                users.add(user);
            }
        }
        return users;
    }

    public List<Lead> GetAllLeads(){
        List<Lead> leads = new ArrayList<Lead>();
        String Query = "SELECT * FROM " + TABLE_LEADS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                Lead lead = new Lead();
                lead.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_LEADS)));
                lead.setUsers_Id(cursor.getInt(cursor.getColumnIndex(KEY_USERS_ID_LEADS)));
                lead.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_LEADS)));
                lead.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_LEADS)));
                lead.setNumber_phone(cursor.getString(cursor.getColumnIndex(KEY_NUMBER_PHONE_LEADS)));
                lead.setDesired_course(cursor.getString(cursor.getColumnIndex(KEY_DESIRED_COURSE_LEADS)));
                lead.setTown(cursor.getString(cursor.getColumnIndex(KEY_TOWN_LEADS)));
                lead.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_LEADS)));
                lead.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_LEADS)));

                leads.add(lead);
            }
        }
        return leads;
    }

    public List<Comment> GetAllComments(){
        List<Comment> comments = new ArrayList<Comment>();
        String Query = "SELECT * FROM " + TABLE_COMMENTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                Comment comment = new Comment();
                comment.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_COMMENT)));
                comment.setLeads_Id(cursor.getInt(cursor.getColumnIndex(KEY_LEADS_ID_COMMENT)));
                comment.setUsers_Id(cursor.getInt(cursor.getColumnIndex(KEY_USERS_ID_COMMENT)));
                comment.setText(cursor.getString(cursor.getColumnIndex(KEY_TEXT_COMMENT)));
                comment.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_COMMENT)));

                comments.add(comment);
            }
        }
        return comments;
    }

    /*TODO GETBYID*/

    public List<User> GetByIdUsers(int id){
        List<User> users = new ArrayList<User>();
        String Query = "SELECT * FROM " + TABLE_USERS + " = " + id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_USERS)));
                user.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_USERS)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_USERS)));
                user.setUser(cursor.getString(cursor.getColumnIndex(KEY_USER_USERS)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD_USERS)));
                user.setActive(cursor.getInt(cursor.getColumnIndex(KEY_ACTIVE_USERS)));
                user.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_USERS)));

                users.add(user);
            }
        }
        return users;
    }

    public List<Lead> GetByIdLeads(int id){
        List<Lead> leads = new ArrayList<Lead>();
        String Query = "SELECT * FROM " + TABLE_LEADS + " WHERE " + KEY_ID_LEADS + " = " + id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                Lead lead = new Lead();
                lead.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_LEADS)));
                lead.setUsers_Id(cursor.getInt(cursor.getColumnIndex(KEY_USERS_ID_LEADS)));
                lead.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_LEADS)));
                lead.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_LEADS)));
                lead.setNumber_phone(cursor.getString(cursor.getColumnIndex(KEY_NUMBER_PHONE_LEADS)));
                lead.setDesired_course(cursor.getString(cursor.getColumnIndex(KEY_DESIRED_COURSE_LEADS)));
                lead.setTown(cursor.getString(cursor.getColumnIndex(KEY_TOWN_LEADS)));
                lead.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_LEADS)));
                lead.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_LEADS)));

                leads.add(lead);
            }
        }
        return leads;
    }

    public List<Comment> GetByIdComments(int Lead_id){
        List<Comment> comments = new ArrayList<Comment>();
        String Query = "SELECT * FROM " + TABLE_COMMENTS + " WHERE " + KEY_LEADS_ID_COMMENT + " = " + Lead_id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                Comment comment = new Comment();
                comment.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_COMMENT)));
                comment.setLeads_Id(cursor.getInt(cursor.getColumnIndex(KEY_LEADS_ID_COMMENT)));
                comment.setUsers_Id(cursor.getInt(cursor.getColumnIndex(KEY_USERS_ID_COMMENT)));
                comment.setText(cursor.getString(cursor.getColumnIndex(KEY_TEXT_COMMENT)));
                comment.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_COMMENT)));

                comments.add(comment);
            }
        }
        return comments;
    }


    /*TODO INSERT*/
    public long insertUsers(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME_USERS, user.getName());
        values.put(KEY_EMAIL_USERS, user.getEmail());
        values.put(KEY_USER_USERS, user.getUser());
        values.put(KEY_PASSWORD_USERS, user.getPassword());
        values.put(KEY_ACTIVE_USERS, user.getActive());
        values.put(KEY_CREATEDAT_USERS, user.getCreatedAt());

        long id = db.insert(TABLE_USERS, null, values);

        return id;
    }

    public long insertLeads(Lead lead) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERS_ID_LEADS, lead.getUsers_Id());
        values.put(KEY_NAME_LEADS, lead.getName());
        values.put(KEY_EMAIL_LEADS, lead.getEmail());
        values.put(KEY_NUMBER_PHONE_LEADS, lead.getNumber_phone());
        values.put(KEY_DESIRED_COURSE_LEADS, lead.getDesired_course());
        values.put(KEY_TOWN_LEADS, lead.getTown());
        values.put(KEY_ADDRESS_LEADS, lead.getAddress());
        values.put(KEY_CREATEDAT_LEADS, lead.getCreatedAt());

        long id = db.insert(TABLE_LEADS, null, values);

        return id;
    }

    public long insertComments(Comment comment) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LEADS_ID_COMMENT, comment.getLeads_Id());
        values.put(KEY_USERS_ID_COMMENT, comment.getUsers_Id());
        values.put(KEY_TEXT_COMMENT, comment.getText());
        values.put(KEY_CREATEDAT_COMMENT, comment.getCreatedAt());

        long id = db.insert(TABLE_COMMENTS, null, values);

        return id;
    }

    /*TODO UPDATE*/
    public int updateUsers(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME_USERS, user.getName());
        values.put(KEY_EMAIL_USERS, user.getEmail());
        values.put(KEY_USER_USERS, user.getUser());
        values.put(KEY_PASSWORD_USERS, user.getPassword());
        values.put(KEY_ACTIVE_USERS, user.getActive());
        values.put(KEY_CREATEDAT_USERS, user.getCreatedAt());

        return db.update(TABLE_USERS,values,KEY_ID_USERS + " = ?", new String[]{String.valueOf(user.getId())});
    }

    public int updateLeads(Lead lead){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME_LEADS, lead.getName());
        values.put(KEY_EMAIL_LEADS, lead.getEmail());
        values.put(KEY_NUMBER_PHONE_LEADS, lead.getNumber_phone());
        values.put(KEY_DESIRED_COURSE_LEADS, lead.getDesired_course());
        values.put(KEY_TOWN_LEADS, lead.getTown());
        values.put(KEY_ADDRESS_LEADS, lead.getAddress());

        return db.update(TABLE_USERS,values,KEY_ID_LEADS + " = ?", new String[]{String.valueOf(lead.getId())});


    }

    public int updateComments(Comment comment){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LEADS_ID_COMMENT, comment.getLeads_Id());
        values.put(KEY_USERS_ID_COMMENT, comment.getUsers_Id());
        values.put(KEY_TEXT_COMMENT, comment.getText());
        values.put(KEY_CREATEDAT_COMMENT, comment.getCreatedAt());

        return db.update(TABLE_COMMENTS, values, KEY_ID_COMMENT + "= ?", new String[] {String.valueOf(comment.getId())});
    }
}
