package com.glee.liverecyclerview;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.recyclerview.extensions.AsyncDifferConfig;
import android.support.v7.recyclerview.extensions.AsyncListDiffer;
import android.support.v7.util.AdapterListUpdateCallback;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author liji
 * @date 11/7/2018 2:39 PM
 * description
 */


public class LiveRecyclerAdapter<T> extends RecyclerView.Adapter<LiveHolder> implements Observer<List<T>> {
    private final AsyncListDiffer<T> asyncListDiffer;
    private final DifferFun<T> differFun;
    private Context context;



    interface DifferFun<T> {
        boolean areItemsTheSame(@NonNull T t, @NonNull T t1);

        boolean areContentsTheSame(@NonNull T t, @NonNull T t1);
    }


    @SuppressLint("RestrictedApi")
    public LiveRecyclerAdapter(DifferFun<T> differFun) {
        this.differFun = differFun;
        asyncListDiffer = new AsyncListDiffer<>(new AdapterListUpdateCallback(this), new AsyncDifferConfig.Builder<>(new DiffUtil.ItemCallback<T>() {
            @Override
            public boolean areItemsTheSame(@NonNull T t, @NonNull T t1) {
                return LiveRecyclerAdapter.this.differFun.areItemsTheSame(t, t1);
            }

            @Override
            public boolean areContentsTheSame(@NonNull T t, @NonNull T t1) {
                return LiveRecyclerAdapter.this.differFun.areContentsTheSame(t, t1);
            }
        }).setMainThreadExecutor(new Executor() {
            private Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    if (msg.what == 1) {
                        ((Runnable) msg.obj).run();
                        return true;
                    }
                    return false;
                }
            });
            private boolean isAsync = true;

            @SuppressLint("NewApi")
            @Override
            public void execute(Runnable command) {
                Message msg = Message.obtain(handler, 1, command);
                if (isAsync) {
                    try {
                        msg.setAsynchronous(true);
                    } catch (Exception e) {
                        isAsync = false;
                    }
                }
                msg.sendToTarget();
            }
        }).build());

    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        this.context = recyclerView.getContext();
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public void onChanged(@Nullable List<T> ts) {
        asyncListDiffer.submitList(ts);
    }
    public void observer(LifecycleOwner owner, LiveData<List<T>> liveData) {
        liveData.observe(owner,this);
    }

    //TODO 封装 onCreateViewHolder onBindViewHolder
    @NonNull
    @Override
    public LiveHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LiveHolder liveHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return asyncListDiffer.getCurrentList().size();
    }
}



