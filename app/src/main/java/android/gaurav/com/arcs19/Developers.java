package android.gaurav.com.arcs19;

import android.gaurav.com.arcs19.Adapters.DevelopersAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Developers extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DevelopersAdapter adapter;
    private List<Developers_data> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new DevelopersAdapter(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        prepareAlbums();
    }

    private void prepareAlbums() {

        Developers_data a = new Developers_data("Allan", "Tech Mentor");
        albumList.add(a);

        a = new Developers_data("Siddharth", "Designer and Developer");
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }
}
