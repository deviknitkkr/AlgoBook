package com.vikas.algobook.Adapters;
import android.content.*;
import android.support.v7.widget.*;
import android.view.*;
import com.vikas.algobook.Model.*;
import java.util.*;
import android.widget.*;
import com.vikas.algobook.*;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{

	private List<Article> list;
	private Context mContext;
	
	public RecyclerViewAdapter(Context mContext,List<Article> list)
	{
		this.mContext=mContext;
		this.list=list;
	}
	
	@Override
	public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup p1, int p2)
	{
		return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_item,p1,false));
	}

	@Override
	public void onBindViewHolder(RecyclerViewAdapter.ViewHolder p1, int p2)
	{
		Article tmp=list.get(p2);
		p1.article_tag.setText(tmp.getTag());
		p1.article_title.setText(tmp.getTitle());
		p1.article_description.setText(tmp.getDescription());
		p1.setIsRecyclable(true);
	}

	@Override
	public int getItemCount()
	{
		return list.size();
	}
	
	
	class ViewHolder extends RecyclerView.ViewHolder{
		TextView article_tag;
		TextView article_title;
		TextView article_description;
		
		public ViewHolder(View v){
			super(v);
			article_tag=v.findViewById(R.id.article_tag);
			article_title=v.findViewById(R.id.article_title);
			article_description=v.findViewById(R.id.article_description);
		}
	}
}
