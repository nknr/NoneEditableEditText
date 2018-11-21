package org.study.noneeditableedittext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StringPatternActivity extends AppCompatActivity implements PatternAdapter.RecyclerListener {


    @BindView(R.id.patternRecyclerView)
    RecyclerView patternRecyclerView;
    private int selectedPosition;
    private PatternAdapter adapter;
    private ArrayList<RegularExpression> mRegularExpressions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_pattern);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            selectedPosition = bundle.getInt("selectedPosition");
        }

        initRecyclerView();

    }

    private void initRecyclerView() {
        mRegularExpressions = getPatternList(selectedPosition);
        patternRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        patternRecyclerView.setHasFixedSize(true);
        patternRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        adapter = new PatternAdapter(mRegularExpressions);
        patternRecyclerView.setAdapter(adapter);
        adapter.setListener(this);
    }


    private ArrayList<RegularExpression> getPatternList(int selectedPosition) {
        ArrayList<RegularExpression> list = new ArrayList<>();
        list.add(new RegularExpression("^xxx", "Matches xxx regex at the beginning of the line", false));
        list.add(new RegularExpression("xxx$", "Matches regex xxx at the end of the line", false));
        list.add(new RegularExpression("[abc]", "Can match any of the letter a, b or c. [] are known as character classes.", false));
        list.add(new RegularExpression("[abc][12]", "Can match a, b or c followed by 1 or 2", false));
        list.add(new RegularExpression("[^abc]", "When ^ is the first character in [], it negates the pattern, matches anything except a, b or c", false));
        list.add(new RegularExpression("[a-e1-8]", "Matches ranges between a to e or 1 to 8", false));
        list.add(new RegularExpression("xx|yy", "Matches regex xx or yy", false));
        list.add(new RegularExpression("\\d", "Any digits, short of [0-9]", false));
        list.add(new RegularExpression("\\D", "Any non-digit, short for [^0-9]", false));
        list.add(new RegularExpression("\\s", "Any whitespace character, short for [\\t\\n\\x0B\\f\\r]", false));
        list.add(new RegularExpression("\\S", "Any non-whitespace character, short for [^\\s]", false));
        list.add(new RegularExpression("\\w", "Any word character, short for [a-zA-Z_0-9]", false));
        list.add(new RegularExpression("\\W", "\tAny non-word character, short for [^\\w]", false));
        list.add(new RegularExpression("\\b", "A word boundary", false));
        list.add(new RegularExpression("\\B", "A non word boundary", false));
        list.add(new RegularExpression("x?", "x occurs once or not at all", false));
        list.add(new RegularExpression("X*", "X occurs zero or more times", false));
        list.add(new RegularExpression("X+", "X occurs one or more times", false));
        list.add(new RegularExpression("X{n}", "X occurs exactly n times", false));
        list.add(new RegularExpression("X{n,}", "X occurs n or more times", false));
        list.add(new RegularExpression("X{n,m}", "X occurs at least n times but not more than m times", false));

        if (selectedPosition >= 0) {
            list.get(selectedPosition).setSelected(true);
        }

        return list;
    }

    @Override
    public void onClick(RecyclerView.ViewHolder viewHolder, int position) {
        Intent intent = new Intent();
        intent.putExtra("pattern",mRegularExpressions.get(position).getName());
        intent.putExtra("selectedPosition",position);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }


    @Override
    public boolean onSupportNavigateUp() {
        setResult(Activity.RESULT_CANCELED);

        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
