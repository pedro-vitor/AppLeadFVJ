package com.NTI.AppFVJ.Data;

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

    private static final String DATABASE_NAME = "FvjDB";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_USERS = "Users";
    private static final String TABLE_LEADS = "Leads";
    private static final String TABLE_COMMENTS = "Comments";

    private static final String KEY_ID_USERS = "Id";
    private static final String KEY_EXTERN_ID_USERS = "ExternId";
    private static final String KEY_NAME_USERS = "Name";
    private static final String KEY_EMAIL_USERS = "Email";
    private static final String KEY_PASSWORD_USERS = "Password";
    private static final String KEY_CREATEDAT_USERS = "CreatedAt";
    private static final String KEY_ACTIVE_USERS = "Active";
    private static final String KEY_UPDATED_USERS = "Updated";

    private static final String KEY_ID_LEADS = "Id";
    private static final String KEY_EXTERN_ID_LEADS = "ExternId";
    private static final String KEY_USERS_ID_LEADS = "Users_Id";
    private static final String KEY_NAME_LEADS = "Name";
    private static final String KEY_EMAIL_LEADS = "Email";
    private static final String KEY_NUMBER_PHONE_LEADS = "Number_phone";
    private static final String KEY_DESIRED_COURSE_LEADS = "Desired_course";
    private static final String KEY_TOWN_LEADS = "Town";
    private static final String KEY_ADDRESS_LEADS = "Address";
    private static final String KEY_CREATEDAT_LEADS = "CreatedAt";
    private static final String KEY_ACTIVE_LEADS = "Active";
    private static final String KEY_UPDATED_LEADS = "Updated";

    private static final String KEY_ID_COMMENT = "Id";
    private static final String KEY_EXTERN_ID_COMMENT = "ExternId";
    private static final String KEY_LEADS_ID_COMMENT = "Leads_Id";
    private static final String KEY_USERS_ID_COMMENT = "Users_Id";
    private static final String KEY_TEXT_COMMENT = "Text";
    private static final String KEY_CREATEDAT_COMMENT = "CreatedAt";
    private static final String KEY_ACTIVE_COMMENT = "Active";
    private static final String KEY_UPDATED_COMMENT = "Updated";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS
            + "("
            + KEY_ID_USERS + " INTEGER  PRIMARY KEY AUTOINCREMENT,"
            + KEY_EXTERN_ID_USERS + " INTEGER, "
            + KEY_NAME_USERS + " TEXT,"
            + KEY_EMAIL_USERS + " TEXT,"
            + KEY_PASSWORD_USERS + " TEXT,"
            + KEY_ACTIVE_USERS + " INTEGER, "
            + KEY_UPDATED_USERS + " INTEGER, "
            + KEY_CREATEDAT_USERS + " TEXT "
            + ")";

    private static final String CREATE_TABLE_LEADS = "CREATE TABLE " + TABLE_LEADS
            + "("
            + KEY_ID_LEADS + " INTEGER  PRIMARY KEY AUTOINCREMENT,"
            + KEY_EXTERN_ID_LEADS + " INTEGER, "
            + KEY_USERS_ID_LEADS + " INTEGER,"
            + KEY_NAME_LEADS + " TEXT,"
            + KEY_EMAIL_LEADS + " TEXT,"
            + KEY_NUMBER_PHONE_LEADS + " TEXT,"
            + KEY_DESIRED_COURSE_LEADS + " TEXT,"
            + KEY_TOWN_LEADS + " TEXT,"
            + KEY_ADDRESS_LEADS + " TEXT, "
            + KEY_CREATEDAT_LEADS + " TEXT, "
            + KEY_ACTIVE_LEADS + " INTEGER, "
            + KEY_UPDATED_LEADS + " INTEGER, "
            + "FOREIGN KEY(" + KEY_USERS_ID_LEADS + ") "
            + "REFERENCES " + TABLE_USERS + "(" + KEY_EXTERN_ID_USERS
            + ") "
            + "ON DELETE NO ACTION "
            + "ON UPDATE NO ACTION" + ")";

    private static final String CREATE_TABLE_COMMENTS = "CREATE TABLE " + TABLE_COMMENTS
            + "(" + KEY_ID_COMMENT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_EXTERN_ID_COMMENT + " INTEGER, "
            + KEY_LEADS_ID_COMMENT + " INTEGER, "
            + KEY_USERS_ID_COMMENT + " INTEGER, "
            + KEY_TEXT_COMMENT + " TEXT,"
            + KEY_CREATEDAT_COMMENT + " TEXT,"
            + KEY_ACTIVE_COMMENT + " INTEGER, "
            + KEY_UPDATED_COMMENT + " INTEGER, "
            + "FOREIGN KEY (" + KEY_USERS_ID_COMMENT + ") "
            + "REFERENCES " + TABLE_USERS + "(" + KEY_EXTERN_ID_USERS + ") "
            + "ON DELETE NO ACTION ON UPDATE NO ACTION, "
            + "FOREIGN KEY(" + KEY_LEADS_ID_COMMENT + ") "
            + "REFERENCES " + TABLE_LEADS + "(" + KEY_EXTERN_ID_LEADS +")"
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

            while(cursor.moveToNext()){
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_USERS)));
                user.setExternId(cursor.getInt(cursor.getColumnIndex(KEY_EXTERN_ID_USERS)));
                user.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_USERS)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_USERS)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD_USERS)));
                user.setActive(cursor.getInt(cursor.getColumnIndex(KEY_ACTIVE_USERS)));
                user.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_USERS)));
                user.setUpdated(cursor.getColumnIndex(KEY_UPDATED_USERS));

                users.add(user);
            }
        return users;
    }

    public List<Lead> GetAllLeads(){
        List<Lead> leads = new ArrayList<Lead>();
        String Query = "SELECT * FROM " + TABLE_LEADS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        int i = cursor.getCount()-1;

        while(cursor.moveToPosition(i)) {
                Lead lead = new Lead();
                lead.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_LEADS)));
                lead.setExternId(cursor.getInt(cursor.getColumnIndex(KEY_EXTERN_ID_LEADS)));
                lead.setUserId(cursor.getInt(cursor.getColumnIndex(KEY_USERS_ID_LEADS)));
                lead.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_LEADS)));
                lead.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_LEADS)));
                lead.setNumberPhone(cursor.getString(cursor.getColumnIndex(KEY_NUMBER_PHONE_LEADS)));
                lead.setDesiredCourse(cursor.getString(cursor.getColumnIndex(KEY_DESIRED_COURSE_LEADS)));
                lead.setTown(cursor.getString(cursor.getColumnIndex(KEY_TOWN_LEADS)));
                lead.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_LEADS)));
                lead.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_LEADS)));
                lead.setActive(cursor.getInt(cursor.getColumnIndex(KEY_ACTIVE_LEADS)));
                lead.setUpdated(cursor.getInt(cursor.getColumnIndex(KEY_UPDATED_LEADS)));

                leads.add(lead);

                i--;
            }
        return leads;
    }

    public List<Comment> GetAllComments(){
        List<Comment> comments = new ArrayList<Comment>();
        String Query = "SELECT * FROM " + TABLE_COMMENTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

            while(cursor.moveToNext()){
                Comment comment = new Comment();
                comment.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_COMMENT)));
                comment.setExternId(cursor.getInt(cursor.getColumnIndex(KEY_EXTERN_ID_COMMENT)));
                comment.setLeadsId(cursor.getInt(cursor.getColumnIndex(KEY_LEADS_ID_COMMENT)));
                comment.setUsersId(cursor.getInt(cursor.getColumnIndex(KEY_USERS_ID_COMMENT)));
                comment.setText(cursor.getString(cursor.getColumnIndex(KEY_TEXT_COMMENT)));
                comment.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_COMMENT)));
                comment.setUpdated(cursor.getInt(cursor.getColumnIndex(KEY_UPDATED_COMMENT)));
                comment.setActive(cursor.getInt(cursor.getColumnIndex(KEY_ACTIVE_COMMENT)));

                comments.add(comment);
            }
        return comments;
    }

    /*TODO GETBYID*/

    public List<User> GetByIdUsers(int id){
        List<User> users = new ArrayList<User>();
        String Query = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_ID_USERS + " = " + id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if(cursor != null)
            cursor.moveToFirst();

        User user = new User();
        user.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_USERS)));
        user.setExternId(cursor.getInt(cursor.getColumnIndex(KEY_EXTERN_ID_USERS)));
        user.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_USERS)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_USERS)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD_USERS)));
        user.setActive(cursor.getInt(cursor.getColumnIndex(KEY_ACTIVE_USERS)));
        user.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_USERS)));
        user.setUpdated(cursor.getInt(cursor.getColumnIndex(KEY_UPDATED_USERS)));

        users.add(user);

        return users;
    }

    public List<Lead> GetByIdLeads(int id){
        List<Lead> leads = new ArrayList<Lead>();
        String Query = "SELECT * FROM " + TABLE_LEADS + " WHERE " + KEY_ID_LEADS + " =" + id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if(cursor != null)
            cursor.moveToFirst();

                Lead lead = new Lead();
                lead.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_LEADS)));
                lead.setExternId(cursor.getInt(cursor.getColumnIndex(KEY_EXTERN_ID_LEADS)));
                lead.setUserId(cursor.getInt(cursor.getColumnIndex(KEY_USERS_ID_LEADS)));
                lead.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_LEADS)));
                lead.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_LEADS)));
                lead.setNumberPhone(cursor.getString(cursor.getColumnIndex(KEY_NUMBER_PHONE_LEADS)));
                lead.setDesiredCourse(cursor.getString(cursor.getColumnIndex(KEY_DESIRED_COURSE_LEADS)));
                lead.setTown(cursor.getString(cursor.getColumnIndex(KEY_TOWN_LEADS)));
                lead.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_LEADS)));
                lead.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_LEADS)));
                lead.setActive(cursor.getInt(cursor.getColumnIndex(KEY_ACTIVE_LEADS)));
                lead.setUpdated(cursor.getInt(cursor.getColumnIndex(KEY_UPDATED_LEADS)));

                leads.add(lead);
        return leads;
    }

    public List<Comment> GetByIdComments(int Lead_id){
        List<Comment> comments = new ArrayList<Comment>();
        String Query = "SELECT * FROM " + TABLE_COMMENTS + " WHERE " + KEY_LEADS_ID_COMMENT + " = " + Lead_id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);
        int i = cursor.getCount()-1;

            while(cursor.moveToPosition(i)){
                Comment comment = new Comment();
                comment.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_COMMENT)));
                comment.setExternId(cursor.getInt(cursor.getColumnIndex(KEY_EXTERN_ID_COMMENT)));
                comment.setLeadsId(cursor.getInt(cursor.getColumnIndex(KEY_LEADS_ID_COMMENT)));
                comment.setUsersId(cursor.getInt(cursor.getColumnIndex(KEY_USERS_ID_COMMENT)));
                comment.setText(cursor.getString(cursor.getColumnIndex(KEY_TEXT_COMMENT)));
                comment.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_COMMENT)));
                comment.setActive(cursor.getInt(cursor.getColumnIndex(KEY_ACTIVE_COMMENT)));
                comment.setUpdated(cursor.getInt(cursor.getColumnIndex(KEY_UPDATED_COMMENT)));

                comments.add(comment);
                i--;
            }
        return comments;
    }

    /*TODO GET BY ID*/
    public List<Comment> GetCommentsById(int Comment_id){
        List<Comment> comments = new ArrayList<Comment>();
        String Query = "SELECT * FROM " + TABLE_COMMENTS + " WHERE " + KEY_ID_COMMENT + " = " + Comment_id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        if(cursor != null)
            cursor.moveToFirst();

        Comment comment = new Comment();
        comment.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_COMMENT)));
        comment.setExternId(cursor.getInt(cursor.getColumnIndex(KEY_EXTERN_ID_COMMENT)));
        comment.setLeadsId(cursor.getInt(cursor.getColumnIndex(KEY_LEADS_ID_COMMENT)));
        comment.setUsersId(cursor.getInt(cursor.getColumnIndex(KEY_USERS_ID_COMMENT)));
        comment.setText(cursor.getString(cursor.getColumnIndex(KEY_TEXT_COMMENT)));
        comment.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_COMMENT)));
        comment.setUpdated(cursor.getInt(cursor.getColumnIndex(KEY_UPDATED_COMMENT)));
        comment.setActive(cursor.getInt(cursor.getColumnIndex(KEY_ACTIVE_COMMENT)));

        comments.add(comment);

        return comments;
    }


    /*TODO INSERT*/
    public long insertUsers(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EXTERN_ID_USERS, user.getExternId());
        values.put(KEY_NAME_USERS, user.getName());
        values.put(KEY_EMAIL_USERS, user.getEmail());
        values.put(KEY_PASSWORD_USERS, user.getPassword());
        values.put(KEY_ACTIVE_USERS, user.getActive());
        values.put(KEY_CREATEDAT_USERS, user.getCreatedAt());
        values.put(KEY_UPDATED_USERS, user.getUpdated());

        long id = db.insert(TABLE_USERS, null, values);

        return id;
    }

    public long insertLeads(Lead lead) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EXTERN_ID_LEADS, lead.getExternId());
        values.put(KEY_USERS_ID_LEADS, lead.getUserId());
        values.put(KEY_NAME_LEADS, lead.getName());
        values.put(KEY_EMAIL_LEADS, lead.getEmail());
        values.put(KEY_NUMBER_PHONE_LEADS, lead.getNumberPhone());
        values.put(KEY_DESIRED_COURSE_LEADS, lead.getDesiredCourse());
        values.put(KEY_TOWN_LEADS, lead.getTown());
        values.put(KEY_ADDRESS_LEADS, lead.getAddress());
        values.put(KEY_CREATEDAT_LEADS, lead.getCreatedAt());
        values.put(KEY_ACTIVE_LEADS, lead.getActive());
        values.put(KEY_UPDATED_LEADS, lead.getUpdated());

        long id = db.insert(TABLE_LEADS, null, values);

        return id;
    }

    public long  insertComments(Comment comment) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EXTERN_ID_COMMENT, comment.getExternId());
        values.put(KEY_LEADS_ID_COMMENT, comment.getLeadsId());
        values.put(KEY_USERS_ID_COMMENT, comment.getUsersId());
        values.put(KEY_TEXT_COMMENT, comment.getText());
        values.put(KEY_CREATEDAT_COMMENT, comment.getCreatedAt());
        values.put(KEY_ACTIVE_COMMENT, comment.getActive());
        values.put(KEY_UPDATED_COMMENT, comment.getUpdated());

        long id = db.insert(TABLE_COMMENTS, null, values);

        return id;
    }

    /*TODO UPDATE*/
    public int updateUsers(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME_USERS, user.getName());
        values.put(KEY_EMAIL_USERS, user.getEmail());
        values.put(KEY_PASSWORD_USERS, user.getPassword());
        values.put(KEY_ACTIVE_USERS, user.getActive());
        values.put(KEY_CREATEDAT_USERS, user.getCreatedAt());
        values.put(KEY_UPDATED_USERS, user.getUpdated());
        values.put(KEY_ACTIVE_USERS, user.getUpdated());

        return db.update(TABLE_USERS,values,KEY_ID_USERS + " = ?", new String[]{String.valueOf(user.getId())});
    }

    public int updateLeads(Lead lead){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERS_ID_LEADS, lead.getUserId());
        values.put(KEY_NAME_LEADS, lead.getName());
        values.put(KEY_EMAIL_LEADS, lead.getEmail());
        values.put(KEY_NUMBER_PHONE_LEADS, lead.getNumberPhone());
        values.put(KEY_DESIRED_COURSE_LEADS, lead.getDesiredCourse());
        values.put(KEY_TOWN_LEADS, lead.getTown());
        values.put(KEY_ADDRESS_LEADS, lead.getAddress());
        values.put(KEY_CREATEDAT_LEADS, lead.getCreatedAt());
        values.put(KEY_ACTIVE_LEADS, lead.getActive());
        values.put(KEY_UPDATED_LEADS, lead.getUpdated());

        return db.update(TABLE_LEADS,values,KEY_ID_LEADS + " = ?", new String[]{String.valueOf(lead.getId())});
    }

    public int updateComments(Comment comment){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LEADS_ID_COMMENT, comment.getLeadsId());
        values.put(KEY_USERS_ID_COMMENT, comment.getUsersId());
        values.put(KEY_TEXT_COMMENT, comment.getText());
        values.put(KEY_CREATEDAT_COMMENT, comment.getCreatedAt());
        values.put(KEY_UPDATED_COMMENT, comment.getUpdated());
        values.put(KEY_ACTIVE_COMMENT, comment.getActive());

        return db.update(TABLE_COMMENTS, values, KEY_ID_COMMENT + "= ?", new String[] {String.valueOf(comment.getId())});
    }

    /*TODO LOGIN*/

    public int login(String email, String password){
        String query = "SELECT * FROM "+ TABLE_USERS +" WHERE " + KEY_EMAIL_USERS + " = '" + email + "' " + " AND " + KEY_PASSWORD_USERS + " = '" + password + "'";
        int id = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() > 0)
            cursor.moveToFirst();
                id = cursor.getInt(cursor.getColumnIndex(KEY_EXTERN_ID_USERS));
            return id;
    }

    public List<User> GetByUpdatedUsers(){
        List<User> users = new ArrayList<User>();
        String Query = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_UPDATED_USERS + " = " + 1;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        while(cursor.moveToNext()){
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_USERS)));
            user.setExternId(cursor.getInt(cursor.getColumnIndex(KEY_EXTERN_ID_USERS)));
            user.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_USERS)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_USERS)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD_USERS)));
            user.setActive(cursor.getInt(cursor.getColumnIndex(KEY_ACTIVE_USERS)));
            user.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_USERS)));
            user.setUpdated(cursor.getColumnIndex(KEY_UPDATED_USERS));

            users.add(user);
        }
        return users;
    }

    public List<Lead> GetByUpdatedLeads(){
        List<Lead> leads = new ArrayList<Lead>();
        String Query = "SELECT * FROM " + TABLE_LEADS + " WHERE " + KEY_UPDATED_LEADS + " = " + 1;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        while(cursor.moveToNext()) {
            Lead lead = new Lead();
            lead.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_LEADS)));
            lead.setExternId(cursor.getInt(cursor.getColumnIndex(KEY_EXTERN_ID_LEADS)));
            lead.setUserId(cursor.getInt(cursor.getColumnIndex(KEY_USERS_ID_LEADS)));
            lead.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_LEADS)));
            lead.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_LEADS)));
            lead.setNumberPhone(cursor.getString(cursor.getColumnIndex(KEY_NUMBER_PHONE_LEADS)));
            lead.setDesiredCourse(cursor.getString(cursor.getColumnIndex(KEY_DESIRED_COURSE_LEADS)));
            lead.setTown(cursor.getString(cursor.getColumnIndex(KEY_TOWN_LEADS)));
            lead.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_LEADS)));
            lead.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_LEADS)));
            lead.setActive(cursor.getInt(cursor.getColumnIndex(KEY_ACTIVE_LEADS)));
            lead.setUpdated(cursor.getInt(cursor.getColumnIndex(KEY_UPDATED_LEADS)));

            leads.add(lead);
        }
        return leads;
    }

    public List<Comment> GetByUpdatedComments(){
        List<Comment> comments = new ArrayList<Comment>();
        String Query = "SELECT * FROM " + TABLE_COMMENTS + " WHERE " + KEY_UPDATED_COMMENT + " = " + 1;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        while(cursor.moveToNext()){
            Comment comment = new Comment();
            comment.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_COMMENT)));
            comment.setExternId(cursor.getInt(cursor.getColumnIndex(KEY_EXTERN_ID_COMMENT)));
            comment.setLeadsId(cursor.getInt(cursor.getColumnIndex(KEY_LEADS_ID_COMMENT)));
            comment.setUsersId(cursor.getInt(cursor.getColumnIndex(KEY_USERS_ID_COMMENT)));
            comment.setText(cursor.getString(cursor.getColumnIndex(KEY_TEXT_COMMENT)));
            comment.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_COMMENT)));
            comment.setUpdated(cursor.getInt(cursor.getColumnIndex(KEY_UPDATED_COMMENT)));
            comment.setActive(cursor.getInt(cursor.getColumnIndex(KEY_ACTIVE_COMMENT)));

            comments.add(comment);
        }
        return comments;
    }


    public List<User> GetByCreatedUsers(){
        List<User> users = new ArrayList<User>();
        String Query = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_EXTERN_ID_USERS + " = " + 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        while(cursor.moveToNext()){
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_USERS)));
            user.setExternId(cursor.getInt(cursor.getColumnIndex(KEY_EXTERN_ID_USERS)));
            user.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_USERS)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_USERS)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD_USERS)));
            user.setActive(cursor.getInt(cursor.getColumnIndex(KEY_ACTIVE_USERS)));
            user.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_USERS)));
            user.setUpdated(cursor.getColumnIndex(KEY_UPDATED_USERS));

            users.add(user);
        }
        return users;
    }

    public List<Lead> GetByCreatedLeads(){
        List<Lead> leads = new ArrayList<Lead>();
        String Query = "SELECT * FROM " + TABLE_LEADS + " WHERE " + KEY_EXTERN_ID_LEADS + " = " + 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        while(cursor.moveToNext()) {
            Lead lead = new Lead();
            lead.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_LEADS)));
            lead.setExternId(cursor.getInt(cursor.getColumnIndex(KEY_EXTERN_ID_LEADS)));
            lead.setUserId(cursor.getInt(cursor.getColumnIndex(KEY_USERS_ID_LEADS)));
            lead.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME_LEADS)));
            lead.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_LEADS)));
            lead.setNumberPhone(cursor.getString(cursor.getColumnIndex(KEY_NUMBER_PHONE_LEADS)));
            lead.setDesiredCourse(cursor.getString(cursor.getColumnIndex(KEY_DESIRED_COURSE_LEADS)));
            lead.setTown(cursor.getString(cursor.getColumnIndex(KEY_TOWN_LEADS)));
            lead.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS_LEADS)));
            lead.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_LEADS)));
            lead.setActive(cursor.getInt(cursor.getColumnIndex(KEY_ACTIVE_LEADS)));
            lead.setUpdated(cursor.getInt(cursor.getColumnIndex(KEY_UPDATED_LEADS)));

            leads.add(lead);
        }
        return leads;
    }

    public List<Comment> GetByCreatedComments(){
        List<Comment> comments = new ArrayList<Comment>();
        String Query = "SELECT * FROM " + TABLE_COMMENTS + " WHERE " + KEY_UPDATED_COMMENT + " = " + 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);

        while(cursor.moveToNext()){
            Comment comment = new Comment();
            comment.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_COMMENT)));
            comment.setExternId(cursor.getInt(cursor.getColumnIndex(KEY_EXTERN_ID_COMMENT)));
            comment.setLeadsId(cursor.getInt(cursor.getColumnIndex(KEY_LEADS_ID_COMMENT)));
            comment.setUsersId(cursor.getInt(cursor.getColumnIndex(KEY_USERS_ID_COMMENT)));
            comment.setText(cursor.getString(cursor.getColumnIndex(KEY_TEXT_COMMENT)));
            comment.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATEDAT_COMMENT)));
            comment.setUpdated(cursor.getInt(cursor.getColumnIndex(KEY_UPDATED_COMMENT)));
            comment.setActive(cursor.getInt(cursor.getColumnIndex(KEY_ACTIVE_COMMENT)));

            comments.add(comment);
        }
        return comments;
    }
}
