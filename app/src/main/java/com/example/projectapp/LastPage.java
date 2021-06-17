package com.example.projectapp;

import android.content.Context;

public class LastPage {

    private Context lastPage;

    public LastPage(Context lastPage) {
        this.lastPage = lastPage;
    }

    public Context getLastPage() {
        return lastPage;
    }

    public void setLastPage(Context newContext) {
        this.lastPage = newContext;
    }
}
