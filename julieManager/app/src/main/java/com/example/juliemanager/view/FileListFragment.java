package com.example.juliemanager.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.juliemanager.R;
import com.example.juliemanager.function.FileListFunction;

import static com.example.juliemanager.utils.FileConstant.ROOT;

/**
 * Created by julie on 2019-10-10
 * 파일 리스트를 보여주는 프래그먼트
 */
public class FileListFragment extends Fragment {
    private FileListFunction fileListFunction;
    private FileAdapter fileAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.filelist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.view_fileList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fileListFunction = FileListFunction.getInstance();
        fileAdapter = new FileAdapter(fileListFunction.getFileItems());

        recyclerView.setAdapter(fileAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //ROOT 경로의 파일 리스트 갱신
        fileListFunction.refreshFileList(ROOT, fileAdapter.getNotifyFileAdapterCallback());
    }

    /**
     * 파일 삭제
     */
    public void delete() {
        fileListFunction.deleteFile(fileAdapter.getNotifyFileAdapterCallback());
    }
}
