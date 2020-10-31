package com.example.curltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Base64;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private String credBase64;
    private JsonPlaceHolder jsonPlaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.text_view_result);

        String usernameValue = "OnboardCB";
        String passValue = "alperna6799energi";

        String credentials = usernameValue + passValue;
        credBase64 = Base64.encodeToString(credentials.getBytes(), Base64.DEFAULT).replace("\n", "");

        // https://impact.idc.nokia.com/ui/#/login

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);

       // getPosts();
       // getComments();
        createPost();



/*
        OkHttpClient client = new OkHttpClient();


        String url ="https://impact.idc.nokia.com/m2m/endpoints/protocol";
       // String url = "https://reqres.in/api/users?page=2";
        Request request = new Request.Builder()
                .url(url)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String myResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            mTextViewResult.setText(myResponse);
                        }
                    });
                }
            }
        });

*/
    }

    private void createPost() {
        final Post post = new Post(23, "new TitlE", "New TEXT");

        Map<String, String> fields = new HashMap<>();
        fields.put("userId", "25");
        fields.put("title", "asd");
        Call<Post> call = jsonPlaceHolder.createPost(fields);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    mTextViewResult.setText("asdcode123: " + response.code());
                    return;
                }
                Post postRespone =response.body();

                String content = "";
                content += "CODE: " + response.code() + "\n";  // viktig
                content += "ID: " + postRespone.getId() + "\n";
                content += "UserId: " + postRespone.getUserId() + "\n";
                content += "title: " + postRespone.getTitle() + "\n";
                content += "Text: " + postRespone.getText() + "\n";

                mTextViewResult.setText(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
            mTextViewResult.setText(t.getMessage());
            }
        });
    }

    private void getComments() {
        Call<List<Comment>> call = jsonPlaceHolder.getComments("posts/3/comments"); // 3

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()){
                    mTextViewResult.setText("asdcode: " + response.code());
                    return;
                }

                List<Comment> comments = response.body();
                for(Comment comment : comments){
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "PostID: " + comment.getPostId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n";
                    mTextViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                mTextViewResult.setText(t.getMessage());
            }
        });
    }

    private void getPosts() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("UserId", "1");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");
        Call<List<Post>> call = jsonPlaceHolder.getPost(parameters); // new Integer[]{2,3,6}, null, null

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    mTextViewResult.setText("asdcode: " + response.code());
                    return;
                }

                List<Post> posts = response.body();
                for(Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "UserID: " + post.getUserId() + "\n";
                    content += "TITLE: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n";
                    mTextViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                mTextViewResult.setText(t.getMessage());
            }
        });
    }
}