package hr.ferit.kristinajavorek.tasky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    // Singleton
    private static DBHelper mTaskDBHelper = null;
    private DBHelper (Context context){
        super(context.getApplicationContext(),Schema.DATABASE_NAME,null,Schema.SCHEMA_VERSION);
    }
    public static synchronized DBHelper getInstance(Context context){
        if(mTaskDBHelper == null){
            mTaskDBHelper = new DBHelper(context);
        }
        return mTaskDBHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) { db.execSQL(CREATE_TABLE_MY_TASKS); }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_MY_TASKS);
        this.onCreate(db);
    }
    //SQL statements
    static final String CREATE_TABLE_MY_TASKS = "CREATE TABLE " + Schema.TABLE_MY_TASKS +
            " (" + Schema.DESCRIPTION + " TEXT," + Schema.TITLE + " TEXT," + Schema.PRIORITY + " INTEGER);";
    static final String DROP_TABLE_MY_TASKS = "DROP TABLE IF EXISTS " + Schema.TABLE_MY_TASKS;
    static final String SELECT_ALL_TASKS = "SELECT " + Schema.DESCRIPTION + "," + Schema.TITLE + ","
            + Schema.PRIORITY + " FROM " + Schema.TABLE_MY_TASKS;
    public void insertTask(Task task){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Schema.DESCRIPTION, task.getDescription());
        contentValues.put(Schema.TITLE, task.getTitle());
        contentValues.put(Schema.PRIORITY, task.getPriority());
        SQLiteDatabase writeableDatabase = this.getWritableDatabase();
        writeableDatabase.insert(Schema.TABLE_MY_TASKS, Schema.DESCRIPTION,contentValues);
        writeableDatabase.close();
    }
    public ArrayList<Task> getAllTasks(){
        SQLiteDatabase writeableDatabase = this.getWritableDatabase();
        Cursor taskCursor = writeableDatabase.rawQuery(SELECT_ALL_TASKS,null);
        ArrayList<Task> tasks = new ArrayList<>();
        if(taskCursor.moveToFirst()){
            do{
                String description = taskCursor.getString(0);
                String title = taskCursor.getString(1);
                int priority = taskCursor.getInt(2);
                tasks.add(new Task(description, title, priority));
            }while(taskCursor.moveToNext());
        }
        taskCursor.close();
        writeableDatabase.close();
        return tasks;
    }
    public static class Schema{
        private static final int SCHEMA_VERSION = 1;
        private static final String DATABASE_NAME = "task.db";
        static final String TABLE_MY_TASKS = "my_tasks";
        static final String DESCRIPTION = "description";
        static final String TITLE = "title";
        static final String PRIORITY = "priority";
    }
}