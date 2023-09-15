package it.academy.jd2;

public class TimeText {
    private String text;
    private long time;

    public TimeText(String text) {
        this.text = text;
        this.time = System.currentTimeMillis();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Time " + text ;
    }
}
