package com.shot.community.go;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shot.community.go.http_meth.UserData;

/**
 * Created by user on 2017/11/29.
 */

public class HOME_Inside extends AppCompatActivity {

    ImageView imageView;
    TextView title,Web;
    TextView Step1, Step2, Step3, Step4, Step5, Step6, Step7, Step8, Step9, Step10;
    TextView c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_inside);
        Intent intent = getIntent();
        String Name = intent.getStringExtra("name");
        int Img = Integer.parseInt(intent.getStringExtra("img"));
        int Po = Integer.parseInt(intent.getStringExtra("Posi"));

        Toolbar toolbar = (Toolbar)findViewById(R.id.add_announcement_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView)findViewById(R.id.image);
        title = (TextView)findViewById(R.id.title);
        Web = (TextView)findViewById(R.id.webtext);
        Step1 = (TextView)findViewById(R.id.stap1);
        Step2 = (TextView)findViewById(R.id.stap2);
        Step3 = (TextView)findViewById(R.id.stap3);
        Step4 = (TextView)findViewById(R.id.stap4);
        Step5 = (TextView)findViewById(R.id.stap5);
        Step6 = (TextView)findViewById(R.id.stap6);
        Step7 = (TextView)findViewById(R.id.stap7);
        Step8 = (TextView)findViewById(R.id.stap8);
        Step9 = (TextView)findViewById(R.id.stap9);
        Step10 = (TextView)findViewById(R.id.stap10);

        c1 = (TextView)findViewById(R.id.c1);
        c2 = (TextView)findViewById(R.id.c2);
        c3 = (TextView)findViewById(R.id.c3);
        c4 = (TextView)findViewById(R.id.c4);
        c5 = (TextView)findViewById(R.id.c5);
        c6 = (TextView)findViewById(R.id.c6);
        c7 = (TextView)findViewById(R.id.c7);
        c8 = (TextView)findViewById(R.id.c8);
        c9 = (TextView)findViewById(R.id.c9);
        c10 = (TextView)findViewById(R.id.c10);




        title.setText(Name);
        imageView.setImageResource(Img);

        int mgid = Integer.parseInt(UserData.manger);
        if (mgid==1){
            switch (Po){
                case 0:
                    Web.setText("https://www.youtube.com/watch?v=C-3AL7RxXmQ");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    點選右下角的新增按鈕 ，選擇新增公告");

                    Step2.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    Step2.setText("Step2 :");
                    c2.setText("    進到新增介面後左上角可以選擇類型(社區公告 or 社區活動)");

                    Step3.setVisibility(View.VISIBLE);
                    c3.setVisibility(View.VISIBLE);
                    Step3.setText("Step3 :");
                    c3.setText("    ( 若不要上傳檔案 ，就不須點選 )點選\"選擇檔案\" ，選擇你要上傳的檔案 ，選擇完要上傳的檔案後 ，可以輸入標題及內容");

                    Step4.setVisibility(View.VISIBLE);
                    c4.setVisibility(View.VISIBLE);
                    Step4.setText("Step4 :");
                    c4.setText("    所有資料輸入完畢後 ，點選右上角的傳送鍵 ，新增完畢後APP會回到社區公告的介面 ，重整後( 由上往下拉 ) ，" +
                            "即可以看見您所新增的資料");

                    Step5.setVisibility(View.VISIBLE);
                    c5.setVisibility(View.VISIBLE);
                    Step5.setText("Step5 :");
                    c5.setText("    點選你所新增的資料 ，進去後若有上傳檔案 ，則可以看見檔案名稱 ，" +
                            "點選檔案名稱就可以下載該檔案");
                    break;
                case 1:
                    Web.setText("https://www.youtube.com/watch?v=D9c3LhUeD8g");

                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    點選包裹資料 ，可以看到包裹資料以及照片");

                    break;
                case 2:
                    Web.setText("https://www.youtube.com/watch?v=dG19SUAfckY");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    點選右下方新增鈕 ，包含寄信以及搜尋信件");

                    Step2.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    Step2.setText("Step2 :");
                    c2.setText("    點選寄信  ，選擇收件人 ，選擇完畢後 ，輸入信件的內容 ，輸入完畢後點選" +
                            "右上方傳送鈕 ，按下確定 ，即完成寄信 ，完成寄信後可以至寄信備份看您剛才送出的內容");

                    Step3.setVisibility(View.VISIBLE);
                    c3.setVisibility(View.VISIBLE);
                    Step3.setText("Step3 :");
                    c3.setText("    點選信件內容 ，可以看見您與該住戶間的對話 ，點選右下方按鈕就可以回復對方信件");



                    break;
                case 3:
                    Web.setText("https://www.youtube.com/watch?v=ZNN9baf3zCo");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    在各項紀錄中有包含社區紀錄 ，維修紀錄以及預約紀錄 ，" +
                            "在社區紀錄的右下方可以新增社區紀錄");

                    Step2.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    Step2.setText("Step2 :");
                    c2.setText("    進入介面後可以選擇類型(會議記錄 or 收支紀錄)" +
                            "選擇完畢後可以輸入標題以及可以選擇您要上傳的檔案 ，輸入完畢後按下新增的按鈕 ，即新增成功");

                    Step3.setVisibility(View.VISIBLE);
                    c3.setVisibility(View.VISIBLE);
                    Step3.setText("Step3 :");
                    c3.setText("    點選維修(預約)紀錄 ，在中間可以選擇(\"未處理\" \"處理中\" \"已處理\")三種狀態" +
                            " ，點進維修(預約)資料後 ，即可以查看您的維修(預約)資料");

                    break;
                case 4:
                    Web.setText("https://www.youtube.com/watch?v=cUth8uIJLeA");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    點選右下方新增紐 ，可以新增相簿");

                    Step2.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    Step2.setText("Step2 :");
                    c2.setText("    新增完畢後 ，點選相簿 ，右下方新增鈕可以新增照片 ，進入介面後點選選擇相片 ，" +
                            "一次最多可以上傳9張相片 ，超過的話請分多次上傳 ，選擇完相片後 ，點選上傳 ，及上傳成功" +
                            " ，重整後( 由上往下拉 )即可以查看新增的相片");
                    break;
                case 5:
                    Web.setText("https://www.youtube.com/watch?v=D8Cacj9Xuk8");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    右下方新增紐可以新增物業管理員或者警衛 ，點擊進去輸入完資料後 ，至填寫的信箱內收取啟動碼 ，即可完成登入");

                    break;
                case 6:
                    Web.setText("https://www.youtube.com/watch?v=6hZeL2tbQRw");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    點選該設施(若該設施不開放 ，則無法點擊進入) ，選擇日期(只能預約明天之後的日期) ，" +
                            "選擇時段以及預約的人數 ，按下預約後 ，即預約完成 ，可以至各項紀錄查看是否審核通過");
                    break;
                case 7:
                    Web.setText("https://www.youtube.com/watch?v=uGulL3K1sZk");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    輸入發現地點以及詳述 ，左下方選擇相片 ，將照片上傳 ，輸入完成後 ，點擊送出 ，即完成申請" +
                            " ，可以至各項紀錄查看維修進度");
                    break;
                case 8:
                    Web.setText("https://www.youtube.com/watch?v=EEjsUMC8__c");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    點擊右下方新增紐 ，可以新增文章 ，輸入內容後 ，點擊右上方傳送紐 ，即發文成功 ，" +
                            "發文成功後 ，至介面查看文章 ，自己的文章 ，右上方有叉叉按鈕 ，可以刪除文章");

                    Step2.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    Step2.setText("Step2 :");
                    c2.setText("    點擊文章可以查看該文章 ，進入介面後下方有三個按鈕(\"前一頁\" \"留言\" \"檢舉\")" +
                            " ，點選留言 ，輸入完畢後 ，點擊右上方傳送紐 ，即留言成功  ，而檢舉可以向管理員舉報該文章有任何不雅字眼或者攻擊他人的言論等");
                    break;

            }
        }
        else if (mgid==2){
            switch (Po){

                case 0:
                    Web.setText("https://www.youtube.com/watch?v=hsLPyH7PvzU");

                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    點選公告資料 ，進去後可以看見檔案名稱 ，" +
                            "點選檔案名稱就可以下載該檔案"+" ，若管理者沒有上傳檔案 ，則無法下載");
                    break;
                case 1:
                    Web.setText("https://www.youtube.com/watch?v=Io10Yn4jxnI");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    (包裹功能包含\"待領取\"  \"已領取\"   \"待退貨\"   \"已退貨\")\n點選右下角的新增按鈕 ，" +
                            "包含新增包裹以及搜尋包裹 ，點選搜尋包裹 ，選擇地址後就能搜尋到該住戶的包裹");

                    Step2.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    Step2.setText("Step2 :");
                    c2.setText("    點選新增包裹 ，選擇住戶 ，選擇完畢後可以設定狀態(\"待領取\"  \"已領取\"   \"待退貨\"   \"已退貨\") ，" +
                            "左下方選擇圖片的按鈕可以從相簿選擇圖片或者可以點選右下方的拍照按鈕直接拍照上傳 ，" +
                            "所有資料輸入完畢後點擊下方新增按鈕 ，即完成新增");

                    Step3.setVisibility(View.VISIBLE);
                    c3.setVisibility(View.VISIBLE);
                    Step3.setText("Step3 :");
                    c3.setText("    點選包裹資料的管理(藍色的按鈕) ，可以選擇修改以及刪除");

                    Step4.setVisibility(View.VISIBLE);
                    c4.setVisibility(View.VISIBLE);
                    Step4.setText("Step4 :");
                    c4.setText("    點選包裹資料 ，可以看到包裹資料以及照片 ，點選右上方簽收的按鈕可以進行簽收");

                    Step5.setVisibility(View.VISIBLE);
                    c5.setVisibility(View.VISIBLE);
                    Step5.setText("Step5 :");
                    c5.setText("    進入到簽收的介面後可以直接在手機上簽名 ，在介面下方有三個按鈕(\"清除\"  \"加粗\"  \"簽收\") ，" +
                            "點選清除可以將簽名板的筆跡清除 ，若簽名完後 ，點選簽收 ，按確定 ，即完成簽收 ，" +
                            "完成後可以至已領取或已退貨查看該筆資料");
                    break;
                case 2:
                    Web.setText("https://www.youtube.com/watch?v=dG19SUAfckY");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    點選右下方新增鈕 ，包含寄信以及搜尋信件");

                    Step2.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    Step2.setText("Step2 :");
                    c2.setText("    點選寄信  ，選擇收件人 ，選擇完畢後 ，輸入信件的內容 ，輸入完畢後點選" +
                            "右上方傳送鈕 ，按下確定 ，即完成寄信 ，完成寄信後可以至寄信備份看您剛才送出的內容");

                    Step3.setVisibility(View.VISIBLE);
                    c3.setVisibility(View.VISIBLE);
                    Step3.setText("Step3 :");
                    c3.setText("    點選信件內容 ，可以看見您與該住戶間的對話 ，點選右下方按鈕就可以回復對方信件");



                    break;
            }
        }
        else if (mgid==3){
            switch (Po){
                case 0:
                    Web.setText("https://www.youtube.com/watch?v=C-3AL7RxXmQ");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    點選右下角的新增按鈕 ，選擇新增公告");

                    Step2.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    Step2.setText("Step2 :");
                    c2.setText("    進到新增介面後左上角可以選擇類型(社區公告 or 社區活動)");

                    Step3.setVisibility(View.VISIBLE);
                    c3.setVisibility(View.VISIBLE);
                    Step3.setText("Step3 :");
                    c3.setText("    ( 若不要上傳檔案 ，就不須點選 )點選\"選擇檔案\" ，選擇你要上傳的檔案 ，選擇完要上傳的檔案後 ，可以輸入標題及內容");

                    Step4.setVisibility(View.VISIBLE);
                    c4.setVisibility(View.VISIBLE);
                    Step4.setText("Step4 :");
                    c4.setText("    所有資料輸入完畢後 ，點選右上角的傳送鍵 ，新增完畢後APP會回到社區公告的介面 ，重整後( 由上往下拉 ) ，" +
                            "即可以看見您所新增的資料");

                    Step5.setVisibility(View.VISIBLE);
                    c5.setVisibility(View.VISIBLE);
                    Step5.setText("Step5 :");
                    c5.setText("    點選你所新增的資料 ，進去後若有上傳檔案 ，則可以看見檔案名稱 ，" +
                            "點選檔案名稱就可以下載該檔案");
                    break;
                case 1:
                    Web.setText("https://www.youtube.com/watch?v=Io10Yn4jxnI");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    (包裹功能包含\"待領取\"  \"已領取\"   \"待退貨\"   \"已退貨\")\n點選右下角的新增按鈕 ，" +
                            "包含新增包裹以及搜尋包裹 ，點選搜尋包裹 ，選擇地址後就能搜尋到該住戶的包裹");

                    Step2.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    Step2.setText("Step2 :");
                    c2.setText("    點選新增包裹 ，選擇住戶 ，選擇完畢後可以設定狀態(\"待領取\"  \"已領取\"   \"待退貨\"   \"已退貨\") ，" +
                            "左下方選擇圖片的按鈕可以從相簿選擇圖片或者可以點選右下方的拍照按鈕直接拍照上傳 ，" +
                            "所有資料輸入完畢後點擊下方新增按鈕 ，即完成新增");

                    Step3.setVisibility(View.VISIBLE);
                    c3.setVisibility(View.VISIBLE);
                    Step3.setText("Step3 :");
                    c3.setText("    點選包裹資料的管理(藍色的按鈕) ，可以選擇修改以及刪除");

                    Step4.setVisibility(View.VISIBLE);
                    c4.setVisibility(View.VISIBLE);
                    Step4.setText("Step4 :");
                    c4.setText("    點選包裹資料 ，可以看到包裹資料以及照片 ，點選右上方簽收的按鈕可以進行簽收");

                    Step5.setVisibility(View.VISIBLE);
                    c5.setVisibility(View.VISIBLE);
                    Step5.setText("Step5 :");
                    c5.setText("    進入到簽收的介面後可以直接在手機上簽名 ，在介面下方有三個按鈕(\"清除\"  \"加粗\"  \"簽收\") ，" +
                            "點選清除可以將簽名板的筆跡清除 ，若簽名完後 ，點選簽收 ，按確定 ，即完成簽收 ，" +
                            "完成後可以至已領取或已退貨查看該筆資料");
                    break;
                case 2:
                    Web.setText("https://www.youtube.com/watch?v=dG19SUAfckY");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    點選右下方新增鈕 ，包含寄信以及搜尋信件");

                    Step2.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    Step2.setText("Step2 :");
                    c2.setText("    點選寄信  ，選擇收件人 ，選擇完畢後 ，輸入信件的內容 ，輸入完畢後點選" +
                            "右上方傳送鈕 ，按下確定 ，即完成寄信 ，完成寄信後可以至寄信備份看您剛才送出的內容");

                    Step3.setVisibility(View.VISIBLE);
                    c3.setVisibility(View.VISIBLE);
                    Step3.setText("Step3 :");
                    c3.setText("    點選信件內容 ，可以看見您與該住戶間的對話 ，點選右下方按鈕就可以回復對方信件");



                    break;
                case 3:
                    Web.setText("https://www.youtube.com/watch?v=16HbI5RE4IM");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    維修管理包含未處理以及處理中 ，點擊維修資料進去後可以查看維修資料以及照片 ，" +
                            "輸入負責維修人員的姓名及電話後 ，可以選擇通過或不通過(不通過的話不需填寫維修人員姓名及電話) ，" +
                            "選擇通過後 ，即可至處理中查看");

                    Step2.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    Step2.setText("Step2 :");
                    c2.setText("    點擊處理中 ，選擇一筆維修資料 ，進去後可以查看維修資料以及照片 ，若已維修完畢 ，" +
                            "點選簽收 ，進入到簽收介面 ，簽收完成後點選右下方的\"簽收\" ，即完成簽收 ，完成後可以至各項紀錄查看已完成的維修紀錄");


                    break;
                case 4:
                    Web.setText("https://www.youtube.com/watch?v=piMR_453Bzg");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    預約管理分為預約審核以及公共設施設定 ，點擊預約審核可以查看預約資料 ，可以選擇通過或不通過 ，" +
                            "審核後可以至各項紀錄查看已完成的預約紀錄");

                    Step2.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    Step2.setText("Step2 :");
                    c2.setText("    點選公共設施設定 ，右下方新增紐可以新增公共設施 ， 新增完成後重整(由上往下拉) ，即可看見新增的設施" +
                            " ，點擊該設施可以進行修改 ，修改完畢後重整(由上往下拉) ，即可完成設定 ，而每筆資料右上方都有紅色叉叉紐 ，可以刪除該設施");
                    break;
                case 5:
                    Web.setText("https://www.youtube.com/watch?v=vD1uCE3CI60");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    在各項紀錄中有包含社區紀錄 ，維修紀錄以及預約紀錄 ，" +
                            "點擊社區紀錄的右下方檔案名稱可以下載該檔案");

                    Step2.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    Step2.setText("Step2 :");
                    c2.setText("    點選維修(預約)紀錄 ，點進維修(預約)資料後 ，即可以查看已完成的維修(預約)資料");
                    break;
                case 6:
                    Web.setText("https://www.youtube.com/watch?v=cUth8uIJLeA");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    點選右下方新增紐 ，可以新增相簿");

                    Step2.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    Step2.setText("Step2 :");
                    c2.setText("    新增完畢後 ，點選相簿 ，右下方新增鈕可以新增照片 ，進入介面後點選選擇相片 ，" +
                            "一次最多可以上傳9張相片 ，超過的話請分多次上傳 ，選擇完相片後 ，點選上傳 ，及上傳成功" +
                            " ，重整後( 由上往下拉 )即可以查看新增的相片");
                    break;

                case 7:
                    Web.setText("https://www.youtube.com/watch?v=6hNh372Yxp0");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    點擊右下方新增紐 ，可以新增文章 ，輸入內容後 ，點擊右上方傳送紐 ，即發文成功 ，" +
                            "發文成功後 ，至介面查看文章 ，自己的文章 ，右上方有叉叉按鈕 ，可以刪除文章");

                    Step2.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    Step2.setText("Step2 :");
                    c2.setText("    點擊文章可以查看該文章 ，進入介面後下方有三個按鈕(\"前一頁\" \"留言\" \"檢舉\")" +
                            " ，點選留言 ，輸入完畢後 ，點擊右上方傳送紐 ，即留言成功  ，而檢舉可以向管理員舉報該文章有任何不雅字眼或者攻擊他人的言論等");
                    break;

            }
        }

        else {
            switch (Po){
                case 0:
                    Web.setText("https://www.youtube.com/watch?v=hsLPyH7PvzU");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    點選公告資料 ，進去後可以看見檔案名稱 ，" +
                            "點選檔案名稱就可以下載該檔案"+" ，若管理者沒有上傳檔案 ，則無法下載");
                    break;
                case 1:
                    Web.setText("https://www.youtube.com/watch?v=D9c3LhUeD8g");

                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    點選包裹資料 ，可以看到包裹資料以及照片");


                    break;
                case 2:
                    Web.setText("https://www.youtube.com/watch?v=dG19SUAfckY");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    點選右下方新增鈕 ，包含寄信以及搜尋信件");

                    Step2.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    Step2.setText("Step2 :");
                    c2.setText("    點選寄信  ，選擇收件人 ，選擇完畢後 ，輸入信件的內容 ，輸入完畢後點選" +
                            "右上方傳送鈕 ，按下確定 ，即完成寄信 ，完成寄信後可以至寄信備份看您剛才送出的內容");

                    Step3.setVisibility(View.VISIBLE);
                    c3.setVisibility(View.VISIBLE);
                    Step3.setText("Step3 :");
                    c3.setText("    點選信件內容 ，可以看見您與該住戶間的對話 ，點選右下方按鈕就可以回復對方信件");


                    break;
                case 3:
                    Web.setText("https://www.youtube.com/watch?v=uGulL3K1sZk");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    輸入發現地點以及詳述 ，左下方選擇相片 ，將照片上傳 ，輸入完成後 ，點擊送出 ，即完成申請" +
                            " ，可以至各項紀錄查看維修進度");
                    break;
                case 4:
                    Web.setText("https://www.youtube.com/watch?v=oaxHhrMLz5A");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    點擊相簿 ，可以查看相片 ，點擊相片 ，可以查看放大圖");
                    break;
                case 5:
                    Web.setText("https://www.youtube.com/watch?v=DLryaQJWrHQ");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    在各項紀錄中有包含社區紀錄 ，維修紀錄以及預約紀錄 ，"+
                            "點擊社區紀錄的右下方檔案名稱可以下載該檔案");


                    Step2.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    Step2.setText("Step2 :");
                    c2.setText("    點選維修(預約)紀錄 ，在中間可以選擇(\"未處理\" \"處理中\" \"已處理\")三種狀態" +
                            " ，點進維修(預約)資料後 ，即可以查看您的維修(預約)資料");
                    break;
                case 6:
                    Web.setText("https://www.youtube.com/watch?v=6hZeL2tbQRw");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    點選該設施(若該設施不開放 ，則無法點擊進入) ，選擇日期(只能預約明天之後的日期) ，" +
                            "選擇時段以及預約的人數 ，按下預約後 ，即預約完成 ，可以至各項紀錄查看是否審核通過");
                    break;
                case 7:
                    Web.setText("https://www.youtube.com/watch?v=EEjsUMC8__c");
                    Step1.setVisibility(View.VISIBLE);
                    c1.setVisibility(View.VISIBLE);
                    Step1.setText("Step1 :");
                    c1.setText("    點擊右下方新增紐 ，可以新增文章 ，輸入內容後 ，點擊右上方傳送紐 ，即發文成功 ，" +
                            "發文成功後 ，至介面查看文章 ，自己的文章 ，右上方有叉叉按鈕 ，可以刪除文章");

                    Step2.setVisibility(View.VISIBLE);
                    c2.setVisibility(View.VISIBLE);
                    Step2.setText("Step2 :");
                    c2.setText("    點擊文章可以查看該文章 ，進入介面後下方有三個按鈕(\"前一頁\" \"留言\" \"檢舉\")" +
                            " ，點選留言 ，輸入完畢後 ，點擊右上方傳送紐 ，即留言成功  ，而檢舉可以向管理員舉報該文章有任何不雅字眼或者攻擊他人的言論等");
                    break;


            }
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
