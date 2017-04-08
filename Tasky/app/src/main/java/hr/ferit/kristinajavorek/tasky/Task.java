package hr.ferit.kristinajavorek.tasky;

public class Task {
    private String mTitle, mDescription;
    private int mPriority;
    public Task(String title, String description, int priority) {
        mTitle = title;
        mDescription = description;
        mPriority = priority;
    }
    public String getTitle() { return mTitle; }
    public String getDescription() { return mDescription; }
    public int getPriority() { return mPriority; }
}
