package br.com.udf.estoque.retrofit;

import androidx.annotation.NonNull;

import br.com.udf.estoque.retrofit.service.ProdutoService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EstoqueRetrofit {

    private static final String URL_BASE = "http://192.168.0.172:8080/";
    private final ProdutoService produtoService;

    public EstoqueRetrofit() {

        OkHttpClient myClient = configuraClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(myClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        produtoService = retrofit.create(ProdutoService.class);
    }

    @NonNull
    private static OkHttpClient configuraClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient myClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        return myClient;
    }

    public ProdutoService getProdutoService() {
        return produtoService;
    }

}
