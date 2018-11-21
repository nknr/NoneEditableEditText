package org.study.noneeditableedittext;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PatternAdapter extends RecyclerView.Adapter<PatternAdapter.PatternHolder> {

    private ArrayList<RegularExpression> mRegularExpressions;
    private RecyclerListener listener;

    public PatternAdapter(ArrayList<RegularExpression> mRegularExpressions) {
        this.mRegularExpressions = mRegularExpressions;
    }

    public void setListener(RecyclerListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public PatternHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_pattern_item,viewGroup,false);
        return new PatternHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatternHolder patternHolder, int position) {
        RegularExpression expression = mRegularExpressions.get(position);
        patternHolder.name.setText(expression.getName());
        patternHolder.description.setText(expression.getDescription());
        patternHolder.isSelected.setVisibility(expression.isSelected()?View.VISIBLE:View.GONE);
    }

    @Override
    public int getItemCount() {
        return mRegularExpressions.size();
    }

    class PatternHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.isSelected)
        ImageView isSelected;

        public PatternHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.rootLayout)
        protected void onRootClick(){
            if (listener != null){
                listener.onClick(this,getAdapterPosition());

            }
        }
    }

    public interface RecyclerListener {
        void onClick(RecyclerView.ViewHolder viewHolder, int position);
    }
}
