package android.gaurav.com.arcs19.Forum;

import android.content.Context;
import android.gaurav.com.arcs19.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ForumAdapter extends RecyclerView.Adapter {

    private static final int QUESTION_VIEW = 1;
    private static final int RESPONSE_VIEW = 2;

    private Context context;
    private List<ForumClass> messageList;

    public ForumAdapter(Context context, List<ForumClass> messageList) {
        this.context = context;
        this.messageList = messageList;
    }


    //To determine the layout depending on the View Type
    @Override
    public int getItemViewType(int position) {

        ForumClass currentItem = messageList.get(position);
        //If the message is by admin
        if(currentItem.getType().equals("user"))
        {
            return QUESTION_VIEW;
        }
        else//If the message is by any user
        {
            return RESPONSE_VIEW;
        }

    }

    //Inflating the appropriate layout to the parent layout
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==QUESTION_VIEW)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_question_adapter,parent,false);
            return new QuestionMessageHolder(view);
        }
        else
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_response_adapter,parent,false);
            return new ResponseMessageHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ForumClass currentMsg = messageList.get(position);

        if(viewHolder.getItemViewType() == QUESTION_VIEW)
        {
            ((QuestionMessageHolder)viewHolder).bind(currentMsg);

        }
        else
        {
            ((ResponseMessageHolder)viewHolder).bind(currentMsg);
        }
    }

    //Question message holder
    private class QuestionMessageHolder extends RecyclerView.ViewHolder{
        TextView details, questionContainer;

        public QuestionMessageHolder(@NonNull View itemView) {
            super(itemView);
            details = itemView.findViewById(R.id.details);
            questionContainer = itemView.findViewById(R.id.question_container);

        }
        void bind(ForumClass currentMsg)
        {
            questionContainer.setText(currentMsg.getMessage());
            details.setText(currentMsg.getUser() + " " + currentMsg.getTimestamp());
        }
    }

    //Response message holder
    private class ResponseMessageHolder extends RecyclerView.ViewHolder{
        TextView details, responseContainer;

        public ResponseMessageHolder(@NonNull View itemView) {
            super(itemView);
            details = itemView.findViewById(R.id.details);
            responseContainer = itemView.findViewById(R.id.response_container);

        }
        void bind(ForumClass currentMsg)
        {
            responseContainer.setText(currentMsg.getMessage());
            details.setText(currentMsg.getUser() + " " + currentMsg.getTimestamp());
        }
    }


    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
