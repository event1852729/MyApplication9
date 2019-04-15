package com.shot.community.go.Creat_comunity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shot.community.go.R;

/**
 * Created by god on 2017/11/17.
 */

public class Add_Comunity extends AppCompatActivity {
    Button button , button2;
    EditText editText1 , editText2 ,editText3 ,editText4 , editText5;
    static int flag=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_comunity);
        button = (Button)findViewById(R.id.addadd_creat_comunity_serachbutton);
        button2 = (Button)findViewById(R.id.addcreat_com_button) ;
        editText1 = (EditText)findViewById(R.id.addet_name);
        editText2 = (EditText)findViewById(R.id.addet_cellphone);
        editText3 = (EditText)findViewById(R.id.addet_address);
        editText4 = (EditText)findViewById(R.id.addet_email);
        editText5 = (EditText)findViewById(R.id.addet_com);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_Comunity.this , Serach_comunity.class);
                Serach_comunity.flag_to_search_com=2;
                startActivity(intent);
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Add_Comunity.this,R.style.MyAlertDialogStyle);
                real_delete.setTitle("確定加入社區?");
                real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(editText1.getText().length()>0 && editText2.getText().length()>0 &&editText3.getText().length()>0 &&
                                editText4.getText().length()>0 &&editText5.getText().length()>0 ) {
                            Creat_comunity_http creat_comunity_http = new Creat_comunity_http(2);
                            creat_comunity_http.execute("http://140.136.155.79/applicant/register_finish.php" ,editText5.getText().toString() , editText1.getText().toString() ,
                                    editText2.getText().toString() ,editText3.getText().toString() ,editText4.getText().toString() );
                            final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Add_Comunity.this,R.style.MyAlertDialogStyle);
                            real_delete.setTitle("加入成功 ! ");
                            real_delete.setMessage("審核成功將發送啟動碼至您的信箱 謝謝 !");
                            real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                    dialog.dismiss();
                                }
                            });
                            real_delete.show();

                        }else {
                            final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Add_Comunity.this,R.style.MyAlertDialogStyle);
                            real_delete.setTitle("加入失敗 ! 請重新輸入 !");
                            real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            real_delete.show();
                            editText1.setText(null);
                            editText2.setText(null);
                            editText3.setText(null);
                            editText4.setText(null);
                            editText5.setText(null);
                        }

                        dialog.dismiss();
                    }
                });
                real_delete.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                real_delete.show();

            }
        });
        if (flag==1)
        {
            Intent intent = getIntent();
            String s = intent.getStringExtra("com_name");
            editText5.setText(s);
        }




    }
}
