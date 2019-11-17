package com.example.lenovo.laba;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewFragment;

public class WebSite extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_web_site, container,
                false);

        WebView browser = rootView.findViewById(R.id.webBrowser);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.loadUrl("https://www.tabletka.by");

        return rootView;
    }
}

//public class WebSite extends WebViewFragment {
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View result = super.onCreateView(inflater, container, savedInstanceState);
//        getWebView().getSettings().setJavaScriptEnabled(true);
//        getWebView().getSettings().setSupportZoom(true);
//        getWebView().getSettings().setBuiltInZoomControls(true);
//        getWebView().loadUrl("http://stackoverflow.com");
//        return(result);
//    }
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//    }
//}
