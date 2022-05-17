package com.udit.bankexam.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.udit.frame.common.pullrefresh.FooterLoadingLayout;
import com.udit.frame.common.pullrefresh.ILoadingLayout;
import com.udit.frame.common.pullrefresh.LoadingLayout;
import com.udit.frame.common.pullrefresh.PullToRefreshBase;
import com.udit.frame.common.pullrefresh.RotateLoadingLayout;
import com.udit.frame.utils.MyLogUtils;

import java.io.PrintStream;

public class PullToRefreshNoDiverListView extends PullToRefreshBase<ListView> implements AbsListView.OnScrollListener {

    private final String TAG = com.udit.frame.common.pullrefresh.PullToRefreshListView.class.getSimpleName();

    private ListView mListView;

    private LoadingLayout mLoadMoreFooterLayout;

    private AbsListView.OnScrollListener mScrollListener;

    public PullToRefreshNoDiverListView(Context paramContext) {
        this(paramContext, null);
    }

    public PullToRefreshNoDiverListView(Context paramContext, AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet, 0);
    }

    public PullToRefreshNoDiverListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        setPullLoadEnabled(true);
    }

    private boolean hasMoreData() {
        return !(this.mLoadMoreFooterLayout != null && this.mLoadMoreFooterLayout.getState() == ILoadingLayout.State.NO_MORE_DATA);
    }

    private boolean isFirstItemVisible() {
        boolean bool;
        ListAdapter listAdapter = this.mListView.getAdapter();
        if (listAdapter == null || listAdapter.isEmpty())
            return (this.mListView.getHeaderViewsCount() > 0) ? ((this.mListView.getChildAt(0).getTop() >= 0)) : true;
        this.mListView.getHeaderViewsCount();
        if (this.mListView.getChildCount() > 0) {
            bool = this.mListView.getChildAt(0).getTop()>0;
        } else {
            bool = false;
        }
        return bool;
    }

    private boolean isLastItemVisible() {
        ListAdapter listAdapter = this.mListView.getAdapter();
        boolean bool = false;
        if (listAdapter == null || listAdapter.isEmpty())
            return (this.mListView.getHeaderViewsCount() > 0) ? false : false;
        int i = listAdapter.getCount();
        int j = this.mListView.getLastVisiblePosition();
        if (j >= i - 1 - 1) {
            i = j - this.mListView.getFirstVisiblePosition();
            j = this.mListView.getChildCount();
            int k = Math.min(i, j - 1);
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("childIndex:");
            stringBuilder.append(i);
            stringBuilder.append("_childCount:");
            stringBuilder.append(j);
            stringBuilder.append("_index:");
            stringBuilder.append(k);
            printStream.println(stringBuilder.toString());
            View view = this.mListView.getChildAt(k);
            if (view != null) {
                if (view.getBottom() <= this.mListView.getBottom())
                    bool = true;
                return bool;
            }
        }
        return false;
    }

    protected LoadingLayout createHeaderLoadingLayout(Context paramContext, AttributeSet paramAttributeSet) {
        return new RotateLoadingLayout(paramContext);
    }

    @SuppressLint({"InlinedApi", "NewApi"})
    protected ListView createRefreshableView(Context paramContext, AttributeSet paramAttributeSet) {
        ListView listView = new ListView(paramContext);
        this.mListView = listView;
        this.mListView.setVerticalScrollBarEnabled(false);
        this.mListView.setOnScrollListener(this);
        this.mListView.setOverScrollMode(2);
        this.mListView.setDivider(new ColorDrawable(getResources().getColor(2131099941)));
        this.mListView.setSelector(new ColorDrawable(0));
        return listView;
    }

    public LoadingLayout getFooterLoadingLayout() {
        return isScrollLoadEnabled() ? this.mLoadMoreFooterLayout : super.getFooterLoadingLayout();
    }

    public int getHeadCount() {
        return (this.mListView == null) ? 0 : this.mListView.getHeaderViewsCount();
    }

    protected boolean isReadyForPullDown() {
        return isFirstItemVisible();
    }

    protected boolean isReadyForPullUp() {
        return isLastItemVisible();
    }

    public void onPullUpRefreshComplete() {
        super.onPullUpRefreshComplete();
        if (this.mLoadMoreFooterLayout != null)
            this.mLoadMoreFooterLayout.setState(ILoadingLayout.State.RESET);
    }

    public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3) {
        if (this.mScrollListener != null)
            this.mScrollListener.onScroll(paramAbsListView, paramInt1, paramInt2, paramInt3);
    }

    public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt) {
        if (isScrollLoadEnabled() && hasMoreData() && (paramInt == 0 || paramInt == 2) && isReadyForPullUp())
            startLoading();
        if (this.mScrollListener != null)
            this.mScrollListener.onScrollStateChanged(paramAbsListView, paramInt);
    }

    public void setHasMoreData(boolean paramBoolean) {
        if (!paramBoolean) {
            if (this.mLoadMoreFooterLayout != null)
                this.mLoadMoreFooterLayout.setState(ILoadingLayout.State.NO_MORE_DATA);
            LoadingLayout loadingLayout = getFooterLoadingLayout();
            if (loadingLayout != null)
                loadingLayout.setState(ILoadingLayout.State.NO_MORE_DATA);
        }
    }

    public void setHeadView(View paramView) {
        if (this.mListView != null) {
            MyLogUtils.e(this.TAG, "------mlistview is not null------");
            this.mListView.addHeaderView(paramView);
            this.mListView.invalidate();
        }
    }

    public void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener) {
        this.mScrollListener = paramOnScrollListener;
    }

    public void setScrollLoadEnabled(boolean paramBoolean) {
        super.setScrollLoadEnabled(paramBoolean);
        if (paramBoolean) {
            if (this.mLoadMoreFooterLayout == null)
                this.mLoadMoreFooterLayout = new FooterLoadingLayout(getContext());
            if (this.mLoadMoreFooterLayout.getParent() == null)
                this.mListView.addFooterView(this.mLoadMoreFooterLayout, null, false);
            this.mLoadMoreFooterLayout.show(true);
            return;
        }
        if (this.mLoadMoreFooterLayout != null)
            this.mLoadMoreFooterLayout.show(false);
    }

    protected void startLoading() {
        super.startLoading();
        if (this.mLoadMoreFooterLayout != null)
            this.mLoadMoreFooterLayout.setState(ILoadingLayout.State.REFRESHING);
    }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\PullToRefreshNoDiverListView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */