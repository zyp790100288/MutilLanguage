package com.intretech.app.mutillanguage.adpater

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.intretech.app.mutillanguage.R

import com.intretech.app.mutillanguage.entity.LanguageInfo

/**
 *   create by yq06171 on 2019/2/2
 **/
class LanguageAdapter(layoutResId: Int, data: MutableList<LanguageInfo>?) : BaseQuickAdapter<LanguageInfo, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder?, item: LanguageInfo?) {
        if (helper?.layoutPosition === mData.size - 1) {
            helper?.setVisible(R.id.layout_dividing, false)
        }
        helper?.setText(R.id.tv_language, item?.name)
        helper?.setChecked(R.id.tb_language, item?.isSelect!!)
    }

    fun changeData(select: Int) {
        if (mData != null && mData.size > 0) {
            for (i in mData.indices) {
                mData[i].isSelect =(mData[i].type === select)
            }
        }
        notifyDataSetChanged()
    }
}