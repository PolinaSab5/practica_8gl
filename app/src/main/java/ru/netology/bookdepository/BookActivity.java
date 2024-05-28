package ru.netology.bookdepository;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.util.UUID;

public class BookActivity extends SingleFragmentActivity {
    public static final String EXTRA_BOOK_ID="ru.rsue.android.bookdepository.book_id";
    public static Intent newIntent(Context packageContext, UUID bookId)
    {
        Intent intent = new Intent(packageContext, BookActivity.class);
        intent.putExtra(EXTRA_BOOK_ID, bookId);
        return intent;
    }
    @Override
    protected Fragment createFragment(){
        UUID bookId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_BOOK_ID);
        return BookFragment.newInstance(bookId);
    }

    }
