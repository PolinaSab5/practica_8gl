package ru.netology.bookdepository;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookListFragment extends Fragment {
    private RecyclerView mBookRecyclerView;
    private BookAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);
        mBookRecyclerView = (RecyclerView) view
                .findViewById(R.id.book_recycler_view);
        mBookRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        Booklab booklab = Booklab.get(getActivity());
        List<Book> books = booklab.getBooks();
        mAdapter = new BookAdapter(books, getActivity());
        mBookRecyclerView.setAdapter(mAdapter);
    }

    private class BookHolder extends RecyclerView.ViewHolder
       implements OnClickListener{
        private Book mBook;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mReadedCheckBox;

        @Override
        public void onClick(View v) {
            if (getActivity() != null) {
                Intent intent = BookActivity.newIntent(getActivity(), mBook.getId());
                startActivity(intent);
            }
        }
        public BookHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener((OnClickListener) this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_book_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_book_date_text_view);
            mReadedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_book_readed_check_box);
        }


        public void bindBook(Book book) {
            mBook = book;
            mTitleTextView.setText(mBook.getTitle());
            mDateTextView.setText(mBook.getDate().toString());
            mReadedCheckBox.setChecked(mBook.isReaded());
        }
    }

    private class BookAdapter extends RecyclerView.Adapter<BookHolder> { // Удалите "abstract", если это конкретный класс
        private List<Book> mBooks;
        private Context mContext;

        public BookAdapter(List<Book> books, Context context) {
            mBooks = books;
            mContext = context;
        }

        @Override
        public BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            View view = layoutInflater.inflate(R.layout.list_item_book, parent, false);
            return new BookHolder(view);
        }

        @Override
        public void onBindViewHolder(BookHolder holder, int position) {
            Book book = mBooks.get(position);
            holder.bindBook(book);
        }

        @Override
        public int getItemCount() {
            return mBooks.size();
        }
    }

}


