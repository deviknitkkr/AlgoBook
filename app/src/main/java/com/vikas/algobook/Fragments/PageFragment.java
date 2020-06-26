package com.vikas.algobook.Fragments;
import android.content.*;
import android.os.*;
import android.support.v4.app.*;
import android.support.v4.widget.*;
import android.support.v7.widget.*;
import android.view.*;
import com.vikas.algobook.*;
import com.vikas.algobook.Adapters.*;
import com.vikas.algobook.Model.*;
import java.util.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import java.io.*;
import org.jsoup.select.*;
import android.widget.*;
import com.vikas.algobook.Decoration.*;

public class PageFragment extends Fragment
{
	private Context mContext;
    private List<Article> list;
	private RecyclerView mRecyclerView;
	private SwipeRefreshLayout mSwipeRefreshLayout;
	private RecyclerViewAdapter mRecyclerViewAdapter;
	private String BASE_URL;
	
	public PageFragment(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View v=inflater.inflate(R.layout.layout_fragment,container,false);
		mContext=container.getContext();
		
		init(v);
		return v;
	}
	
	public void init(View v)
	{
		BASE_URL=this.getArguments().getString("url");
		mSwipeRefreshLayout=v.findViewById(R.id.swipe_refresh);
		
		mRecyclerView=v.findViewById(R.id.recyclerview);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
		mRecyclerView.addItemDecoration(new SpaceItemDecoration(32,mRecyclerView));
		initializeLoadingData();
		
		mRecyclerViewAdapter=new RecyclerViewAdapter(mContext,list);
		mRecyclerView.setAdapter(mRecyclerViewAdapter);
	    
	}
	
	public void initializeLoadingData()
	{
		list=new ArrayList<Article>();
		(new LoadArticleTask()).execute(BASE_URL);
	}
	
	class LoadArticleTask extends AsyncTask<String,Void,String>
	{

		@Override
		protected String doInBackground(String[] p1)
		{
			String str="";
			try
			{
				Document doc=Jsoup.connect(p1[0]).get();
				Elements elements=doc.select("article");
				
				for (Element row : elements) {
					Article tmp=new Article();
					tmp.setTag(row.select("span.post-card-tags").text());
					tmp.setTitle(row.select("h2.post_card_title").text());
					tmp.setDescription(row.select("p").text());
					str+=tmp.getTitle();
					list.add(tmp);
				}
			}
			catch (IOException e){}
			
			return str;
		}

		@Override
		protected void onPostExecute(String result)
		{
			mRecyclerViewAdapter.notifyDataSetChanged();
			Toast.makeText(mContext,"completed:"+list.size(),Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);
		}
		
		

		
	}
}
