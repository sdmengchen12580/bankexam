package com.udit.frame.common.texthtml;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Html;
import android.text.Layout;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.AlignmentSpan;
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.view.View;

import com.udit.frame.utils.MyLogUtils;

import org.xml.sax.XMLReader;

import java.util.Vector;


public class HtmlTagHandler implements Html.TagHandler {

    private final String TAG = this.getClass().getSimpleName();

    private int mListItemCount = 0;

    private  String url;
    private final Vector<String> mListParents = new Vector<String>();

    private Context mContext;

    public HtmlTagHandler(Context mContext) {
        this.mContext = mContext.getApplicationContext();
    }

    public String getUrl() {
        return url;
    }

    private static class Code {
    }

    private static class Center {
    }

    @Override
    public void handleTag(final boolean opening, final String tag, Editable output,
                          final XMLReader xmlReader) {
        if (opening) {
            // opening tag
            if (tag.equalsIgnoreCase("ul") || tag.equalsIgnoreCase("ol")
                    || tag.equalsIgnoreCase("dd")) {
                mListParents.add(tag);
                mListItemCount = 0;
            } else if (tag.equalsIgnoreCase("code")) {
                start(output, new Code());
            } else if (tag.equalsIgnoreCase("center")) {
                start(output, new Center());
            }
        } else {
            // closing tag
            if (tag.equalsIgnoreCase("ul") || tag.equalsIgnoreCase("ol")
                    || tag.equalsIgnoreCase("dd")) {
                mListParents.remove(tag);
                mListItemCount = 0;
            } else if (tag.equalsIgnoreCase("li")) {
                handleListTag(output);
            } else if (tag.equalsIgnoreCase("code")) {
                end(output, Code.class, new TypefaceSpan("monospace"), false);
            } else if (tag.equalsIgnoreCase("center")) {
                end(output, Center.class,
                        new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), true);
            }
        }

        if(tag.toLowerCase().equals("img"))
        {
            int len = output.length();
            ImageSpan[] imgs = output.getSpans(len-1,len,ImageSpan.class);
            this.url = imgs[0].getSource();
            output.setSpan(new ClickImage(url,mContext),len-1,len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    public class ClickImage extends ClickableSpan
    {
        private String url;

        private Context mContext;

        public ClickImage(String url, Context mContext) {
            this.url = url;
            this.mContext = mContext;
        }

        @Override
        public void onClick(View widget) {
            MyLogUtils.e(TAG,"URL:"+url);
        }
    }

    /**
     * Mark the opening tag by using private classes
     *
     * @param output
     * @param mark
     */
    private void start(Editable output, Object mark) {
        int len = output.length();
        output.setSpan(mark, len, len, Spannable.SPAN_MARK_MARK);
    }

    private void end(Editable output, Class kind, Object repl, boolean paragraphStyle) {
        Object obj = getLast(output, kind);
        // start of the tag
        int where = output.getSpanStart(obj);
        // end of the tag
        int len = output.length();

        output.removeSpan(obj);

        if (where != len) {
            // paragraph styles like AlignmentSpan need to end with a new line!
            if (paragraphStyle) {
                output.append("\n");
                len++;
            }
            output.setSpan(repl, where, len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    /**
     * Get last marked position of a specific tag kind (private class)
     *
     * @param text
     * @param kind
     * @return
     */
    private Object getLast(Editable text, Class kind) {
        Object[] objs = text.getSpans(0, text.length(), kind);
        if (objs.length == 0) {
            return null;
        } else {
            for (int i = objs.length; i > 0; i--) {
                if (text.getSpanFlags(objs[i - 1]) == Spannable.SPAN_MARK_MARK) {
                    return objs[i - 1];
                }
            }
            return null;
        }
    }

    private void handleListTag(Editable output) {
        if (mListParents.lastElement().equals("ul")) {
            output.append("\n");
            String[] split = output.toString().split("\n");

            int lastIndex = split.length - 1;
            int start = output.length() - split[lastIndex].length() - 1;
            output.setSpan(new BulletSpan(15 * mListParents.size()), start, output.length(), 0);
        } else if (mListParents.lastElement().equals("ol")) {
            mListItemCount++;

            output.append("\n");
            String[] split = output.toString().split("\n");

            int lastIndex = split.length - 1;
            int start = output.length() - split[lastIndex].length() - 1;
            output.insert(start, mListItemCount + ". ");
            output.setSpan(new LeadingMarginSpan.Standard(15 * mListParents.size()), start,
                    output.length(), 0);
        }
    }
}
