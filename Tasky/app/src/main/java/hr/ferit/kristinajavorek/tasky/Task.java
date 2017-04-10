package hr.ferit.kristinajavorek.tasky;

import android.util.Log;

import static java.lang.String.valueOf;

public class Task {
    private String mTitle, mDescription;
    private int mPriority, mId;
    public Task(String title, String description, int priority, int id) {
        mTitle = title;
        mDescription = description;
        mPriority = priority;
        mId = id;
    }
    public String getTitle() { return mTitle; }
    public String getDescription() { return mDescription; }
    public int getPriority() { return mPriority; }
    public void setId(int id) { mId=id; }
    public int getId () { return mId; }
}
