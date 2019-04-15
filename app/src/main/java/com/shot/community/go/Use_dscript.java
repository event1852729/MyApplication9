package com.shot.community.go;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.shot.community.go.http_meth.UserData;

/**
 * Created by god on 2017/12/1.
 */

public class Use_dscript extends AppCompatActivity {
    private DividerItemDecoration mDivider;
    RecyclerView recyclerView;
    private int[] image = {R.drawable.ic_detail1,R.drawable.ic_page
            ,R.drawable.ic_mgg,R.drawable.ic_detail3,R.drawable.ic_photo,R.drawable.ic_announce_picture
            ,R.drawable.ic_aplica,R.drawable.ic_detail2,R.drawable.ic_addperson};

    private int[] image2 = {R.drawable.ic_detail1,R.drawable.ic_page
            ,R.drawable.ic_mgg,R.drawable.ic_detail2,R.drawable.ic_photo,R.drawable.ic_detail3
            ,R.drawable.ic_aplica,R.drawable.ic_addperson};

    private String[] itemqqq = {"社區公告  > ","我的包裹  > ","我的訊息  > ","各項紀錄  > ","社區相簿  > ","住戶管理  > ","預約設施  > "
            ,"維修申請 > ","社區討論區 > "};

    private String[] item2 = {"社區公告  > ","我的包裹  > ","我的訊息  > ","維修申請  > ","社區相簿  > ","各項紀錄  > ","預約設施  > "
            ,"社區討論區  > "};

    private String[] item3 = {"社區公告  > ","包裹管理  > ","我的訊息  > "};

    private int[] image3 = {R.drawable.ic_detail1,R.drawable.ic_page
            ,R.drawable.ic_mgg};

    private String[] item4 = {"社區公告  > ","包裹管理  > ","我的訊息  > ","維修管理 > ","預約管理 > ","各項紀錄  > ","社區相簿  > ","社區討論區  > "};
    private int[] image4 = {R.drawable.ic_detail1,R.drawable.ic_page
            ,R.drawable.ic_mgg,R.drawable.ic_detail2,R.drawable.ic_aplica,R.drawable.ic_detail3,R.drawable.ic_photo,R.drawable.ic_addperson};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.use_dscript);
        Toolbar toolbar = (Toolbar)findViewById(R.id.add_announcement_toolbar_newfff);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                recyclerView = (RecyclerView)findViewById(R.id.home_RecycleView);



        int mgid = Integer.parseInt(UserData.manger);

        //主委
        if (mgid==1)
        {
            HOME_Model[] homeModels = {new HOME_Model(image[0],itemqqq[0]),
                    new HOME_Model(image[1],itemqqq[1]),new HOME_Model(image[2],itemqqq[2]),
                    new HOME_Model(image[3],itemqqq[3]),new HOME_Model(image[4],itemqqq[4]),
                    new HOME_Model(image[5],itemqqq[5]),new HOME_Model(image[6],itemqqq[6]),
                    new HOME_Model(image[7],itemqqq[7]),new HOME_Model(image[8],itemqqq[8])};
            HOME_Adapter homeAdapter = new HOME_Adapter(Use_dscript.this,homeModels);
            recyclerView.setAdapter(homeAdapter);
            recyclerView.setHasFixedSize(true);
            mDivider = new DividerItemDecoration(Use_dscript.this,DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(mDivider);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(Use_dscript.this, 1);
            recyclerView.setLayoutManager(gridLayoutManager);
            gridLayoutManager.scrollToPosition(0);
        }
        //警衛
        else if (mgid==2){
            HOME_Model[] homeModels = {new HOME_Model(image3[0],item3[0]),
                    new HOME_Model(image3[1],item3[1]),new HOME_Model(image3[2],item3[2])};
            HOME_Adapter homeAdapter = new HOME_Adapter(Use_dscript.this,homeModels);
            recyclerView.setAdapter(homeAdapter);
            recyclerView.setHasFixedSize(true);
            mDivider = new DividerItemDecoration(Use_dscript.this,DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(mDivider);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(Use_dscript.this, 1);
            recyclerView.setLayoutManager(gridLayoutManager);
            gridLayoutManager.scrollToPosition(0);
        }
        //物館
        else if (mgid==3){
            HOME_Model[] homeModels = {new HOME_Model(image4[0],item4[0]),
                    new HOME_Model(image4[1],item4[1]),new HOME_Model(image4[2],item4[2]),
                    new HOME_Model(image4[3],item4[3]),new HOME_Model(image4[4],item4[4])
                    ,new HOME_Model(image4[5],item4[5]),new HOME_Model(image4[6],item4[6])
                    ,new HOME_Model(image4[7],item4[7])};
            HOME_Adapter homeAdapter = new HOME_Adapter(Use_dscript.this,homeModels);
            recyclerView.setAdapter(homeAdapter);
            recyclerView.setHasFixedSize(true);
            mDivider = new DividerItemDecoration(Use_dscript.this,DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(mDivider);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(Use_dscript.this, 1);
            recyclerView.setLayoutManager(gridLayoutManager);
            gridLayoutManager.scrollToPosition(0);
        }
        //住戶
        else
        {
            HOME_Model[] homeModels = {new HOME_Model(image2[0],item2[0]),
                    new HOME_Model(image2[1],item2[1]),new HOME_Model(image2[2],item2[2]),
                    new HOME_Model(image2[3],item2[3]),new HOME_Model(image2[4],item2[4]),
                    new HOME_Model(image2[5],item2[5]),new HOME_Model(image2[6],item2[6]),
                    new HOME_Model(image2[7],item2[7])};
            HOME_Adapter homeAdapter = new HOME_Adapter(Use_dscript.this,homeModels);
            recyclerView.setAdapter(homeAdapter);
            recyclerView.setHasFixedSize(true);
            mDivider = new DividerItemDecoration(Use_dscript.this,DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(mDivider);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(Use_dscript.this, 1);
            recyclerView.setLayoutManager(gridLayoutManager);
            gridLayoutManager.scrollToPosition(0);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.announcement_cotent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectId = item.getItemId();
        switch (selectId){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
