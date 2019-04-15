package com.shot.community.go;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;

import com.shot.community.go.Detail_calss.Detail_fragment_adpater;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailRecord_Fragments extends Fragment {

    ViewPager viewPager;
    Detail_fragment_adpater detail_fragment_adpater;
    Button button1, button2, button3 ;

    public DetailRecord_Fragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_record__fragments , container ,false);

        detail_fragment_adpater = new  Detail_fragment_adpater(getFragmentManager());
        viewPager = (ViewPager)view.findViewById(R.id.detail_viewpager);
        viewPager.setAdapter(detail_fragment_adpater);
        viewPager.setOffscreenPageLimit(10);//防止滑動fragment銷毀
        button1 = (Button)view.findViewById(R.id.dicuss_record_button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
                button1.setBackgroundResource(R.drawable.message_button_style);
                button2.setBackgroundResource(R.drawable.message_button);
                button3.setBackgroundResource(R.drawable.message_button);
            }
        });
        button2 = (Button)view.findViewById(R.id.dicuss_record_button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
                button2.setBackgroundResource(R.drawable.message_button_style);
                button1.setBackgroundResource(R.drawable.message_button);
                button3.setBackgroundResource(R.drawable.message_button);
            }
        });
        button3 = (Button)view.findViewById(R.id.dicuss_record_button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
                button3.setBackgroundResource(R.drawable.message_button_style);
                button1.setBackgroundResource(R.drawable.message_button);
                button2.setBackgroundResource(R.drawable.message_button);
            }
        });



        return view;
    }


    ProgressDialog progress;

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if(requestCode == 10 && resultCode == RESULT_OK){
            progress = new ProgressDialog(getActivity());
            progress.setTitle("Uploading");
            progress.setMessage("Please wait...");
            progress.show();

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    File f  = new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
                    String content_type  = getMimeType(f.getPath());



                    String file_path = f.getAbsolutePath();
                    OkHttpClient client = new OkHttpClient();
                    RequestBody file_body = RequestBody.create(MediaType.parse(content_type),f);

                    RequestBody request_body = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("type",content_type)
                            .addFormDataPart("uploaded_file",file_path.substring(file_path.lastIndexOf("/")+1), file_body)
                            .build();

                    Request request = new Request.Builder()
                            .url("http://140.136.155.79/test01/files_content.php")
                            .post(request_body)
                            .build();

                    try {
                        Response response = client.newCall(request).execute();

                        if(!response.isSuccessful()){
                            throw new IOException("Error : "+response);
                        }

                        progress.dismiss();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            });

            t.start();




        }
    }

    private String getMimeType(String path) {

        String extension = MimeTypeMap.getFileExtensionFromUrl(path);

        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }


}
