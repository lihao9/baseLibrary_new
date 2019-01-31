package com.jiacaizichan.baselibrary.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jiacaizichan.baselibrary.activity.BaseActivity;
import com.jiacaizichan.baselibrary.utils.LogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BaseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public abstract class BaseFragment extends Fragment {
    private View mContentView;
    private Context mContext;
    public OnFragmentInteractionListener mListener;
    public Unbinder bind;
    public Toast toast;
    public BaseActivity activity;

    public boolean isInit;
    public boolean isVisible;
    private boolean isLoad;

    public BaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.i("setUserVisibleHint");
        if (getContentView()==null){
            isInit = false;
        }else {
            isInit = true;
        }
        isVisible = isVisibleToUser;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtil.i("onCreateView");
        mContentView = inflater.inflate(setContentView(), container, false);
        isInit = true;
        bind =  ButterKnife.bind(this,mContentView);
        mContext = getContext().getApplicationContext();
        return mContentView;
    }

    public void loadAndData(){

    }

    public void showToast(String content){
        if (toast == null) {
            toast = Toast.makeText(mContext, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        activity = (BaseActivity) getActivity();
        LogUtil.i("onActivityCreated");
        activity = (BaseActivity) getActivity();
        init(savedInstanceState);
        if (isInit&&isVisible&&!isLoad){
            loadAndData();
            isLoad = true;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        activity = (BaseActivity) getActivity();
    }

    /**
     * 其他初始化操作
     * @param savedInstanceState
     */
    public abstract void init(Bundle savedInstanceState);

    public abstract int setContentView();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public boolean isLoad() {
        return isLoad;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    public View getContentView() {
        return mContentView;
    }


    public Context getMContext() {
        return mContext;
    }
}
