package com.example.sqlitedatabaseapplication.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sqlitedatabaseapplication.R;
import com.example.sqlitedatabaseapplication.model.Receipt;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewReceiptFragment extends Fragment {


    interface ReceiptFragmentListener{
        void makeToast(String price);
    }

    private ReceiptFragmentListener receiptFragmentListener;

    @BindView(R.id.receipt_textView_FrameLayout)
    TextView textViewTitle;
    @BindView(R.id.receipt_cost_textView_frameLayout)
    TextView textViewCost;
    //ImageView dollarSign;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.receipt_fragment_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        Receipt receipt = getArguments().getParcelable("my_key");

        if(receipt!=null){
            textViewTitle.setText(receipt.getTitle());
            textViewCost.setText(receipt.getPrice());

            textViewCost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    receiptFragmentListener.makeToast(receipt.getPrice());
                }
            });
        }



    }

    @OnClick(R.id.dollarSign_FrameLayout)
    public void closeFragment(View view){
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.d("TAG_Y", "onAttach ");

        if(context instanceof MainActivity){
            this.receiptFragmentListener = (MainActivity)context;
        }
    }
}
