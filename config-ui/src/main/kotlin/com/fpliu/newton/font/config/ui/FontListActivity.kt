package com.fpliu.newton.font.config.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fpliu.newton.font.config.ui.entity.Font
import com.fpliu.newton.http.download.Downloader
import com.fpliu.newton.log.Logger
import com.fpliu.newton.ui.dialog.CircleProgressDialogBuilder
import com.fpliu.newton.ui.list.PullableRecyclerViewActivity
import com.fpliu.newton.ui.pullable.PullType
import com.fpliu.newton.ui.pullable.PullableViewContainer
import com.fpliu.newton.ui.recyclerview.decoration.ListDividerItemDecoration
import com.fpliu.newton.ui.recyclerview.holder.ItemViewHolder
import com.fpliu.newton.util.getColorInt
import com.fpliu.newton.util.getDimensionPixelSize
import com.fpliu.newton.util.myDir
import com.uber.autodispose.autoDisposable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File

class FontListActivity : PullableRecyclerViewActivity<Font>() {

    companion object {
        private val TAG = FontListActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "字体列表"

        val color = getColorInt(R.color.c_ebebeb)
        val height = getDimensionPixelSize(R.dimen.dp750_1)
        val padding = getDimensionPixelSize(R.dimen.dp750_30)
        setItemDecoration(ListDividerItemDecoration().color(color).height(height).padding(padding))
    }

    override fun onRefreshOrLoadMore(pullableViewContainer: PullableViewContainer<RecyclerView>, pullType: PullType, pageNum: Int, pageSize: Int) {
        HTTPRequest
            .requestFontList(pageNum)
            .filter { it.filterIfFalseThenThrowAException() }
            .doOnNext {
                it.list?.forEach {
                    it.type = "file"
                    it.path = "$myDir/fonts/${it.name}"
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .autoDisposable(disposeOnDestroy())
            .subscribe({
                finishRequestSuccessWithErrorImageAndMessageIfItemsEmpty(pullType, it.list, R.mipmap.font_selected, "暂无数据")
            }, {
                Logger.e(TAG, "onRefreshOrLoadMore()", it)
                finishRequestSuccessWithErrorImageAndMessageIfItemsEmpty(pullType, null, R.mipmap.font_selected, "网络异常")
            })
    }

    override fun onBindLayout(parent: ViewGroup, viewType: Int) = R.layout.font_list_item

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int, item: Font) {
        holder.id(R.id.tv).text(item.name)
    }

    override fun onItemClick(holder: ItemViewHolder, position: Int, item: Font) {
        val fontFile = File(item.path)
        if (fontFile.exists()) {
            startFontSettingActivity(item)
        } else {
            downloadFontThenStartFontSettingActivity(item, fontFile)
        }
    }

    private fun downloadFontThenStartFontSettingActivity(item: Font, fontFile: File) {
        val progressDialog = CircleProgressDialogBuilder(this).create().apply {
            setWindowWidth(getDimensionPixelSize(R.dimen.dp750_150))
            setWindowHeight(getDimensionPixelSize(R.dimen.dp750_150))
            show()
        }
        Observable
            .just(item)
            .doOnNext { Downloader.downloadSync(item.url, fontFile) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { progressDialog.dismiss() }
            .autoDisposable(disposeOnDestroy())
            .subscribe({
                startFontSettingActivity(it)
            }, {
                it.printStackTrace()
                showToast("下载失败")
            })
    }

    private fun startFontSettingActivity(item: Font) {
        Font(item.name, item.type, item.path, item.size).let {
            FontConfigActivity.start(this, it)
        }
    }
}
