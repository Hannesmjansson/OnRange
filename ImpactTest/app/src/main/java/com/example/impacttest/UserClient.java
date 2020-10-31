package com.example.impacttest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserClient {

    @Headers({"Accept: application/json",
            "Authorization: Basic T25ib2FyZENCOmFscGVybmE2Nzk5ZW5lcmdp" // tillhhör den andra kommentaren T25ib2FyZENCOmFscGVybmE2Nzk5ZW5lcmdp OnboardCB:alperna6799energi    T25ib2FyZENC:YWxwZXJuYTY3OTllbmVyZ2k=
            })                                                                  // //  MgmtCB:linslus1687pennskrin           TWdtdENCOmxpbnNsdXMxNjg3cGVubnNrcmlu
    @GET (".")// POST alt 6 för debugg
    Call<User> getUser( ); // @Header("Authorization") String authHeader, // @Body User user

// GET /endpoints/{serialNumber}
}
