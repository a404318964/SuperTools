package com.zwj.supertools.ui.adapter.fperson;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zwj.supertools.R;
import com.zwj.supertools.bean.fperson.FPerson;
import com.zwj.zwjutils.DateUtil;

import java.util.List;

/**
 * Created by zwj on 2017/12/10.
 */

public class BirthdayAdapter extends CommonAdapter<FPerson> {

    public BirthdayAdapter(Context context, List<FPerson> datas) {
        super(context, R.layout.item_birthday, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, FPerson fPerson, int i) {
        viewHolder.setText(R.id.tv_name, fPerson.getName());
        viewHolder.setText(R.id.tv_age, String.valueOf(fPerson.getAge()));

        if(fPerson.getBirthday() != null) {
            viewHolder.setText(R.id.tv_birthday, "阳历生日："+ DateUtil.date2Str(fPerson.getBirthday(), "YYYY-MM-dd"));
        }else {
            viewHolder.setText(R.id.tv_birthday, "阴历生日："+ DateUtil.date2Str(fPerson.getBirthday2(), "YYYY-MM-dd"));
        }
    }
}
