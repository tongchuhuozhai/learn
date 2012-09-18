package bc.com;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class PhoneInputLayout extends ViewGroup {

	private int count;

	private View[] mTexts;

	private int mTextWidth;
	private int mTextHeight;

	private int[] widths;

	private int totalWidth;
	private int totalHeight;

	public PhoneInputLayout(Context context) {
		super(context);
	}

	public PhoneInputLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PhoneInputLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	int widthInc;

	/**
	 * Cache the buttons in a member array for faster access. Compute the
	 * measurements for the width/height of buttons. The inflate sequence is
	 * called right after the constructor and before the measure/layout phase.
	 */
	// ��ȡ �� ����ͼ��С
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		count = getChildCount();
		widths = new int[count];
		mTexts = new View[count];
		final View[] texts = mTexts;
		for (int i = 0; i < count; i++) {
			texts[i] = getChildAt(i);
			// measure ����������λ��, ������ʾ
			texts[i].measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);

			// ֻ����measure����֮�� �ſ���getMeasuredWidth��ȡ��ͼ�Ĵ�С, ����getMeasuredWidth����0
			widths[i] = texts[i].getMeasuredWidth();
			totalWidth += widths[i] + mPaddingLeft + mPaddingRight;
		}

		mTextHeight = texts[0].getMeasuredHeight();
		totalHeight = mTextHeight + mPaddingTop + mPaddingBottom;

	}

	int mPaddingLeft = 10;
	int mPaddingRight = 10;
	int 	mPaddingTop= 10;
	int mPaddingBottom = 10;
	// ��������ͼ�Ĳ���
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {

		final View[] texts = mTexts;

		final int paddingLeft = mPaddingLeft;
		final int paddingRight = mPaddingRight;
		final int buttonHeight = mTextHeight;

		int y = (bottom - top) - totalHeight + mPaddingTop;
		int x = mPaddingLeft;
		for (int i = 0; i < count; i++) {
			texts[i].layout(x, y, x + widths[i], y + buttonHeight);
			x += widths[i] + paddingLeft + paddingRight;
		}
	}

	/**
	 * This method is called twice in practice. The first time both with and
	 * height are constraint by AT_MOST. The second time, the width is still
	 * AT_MOST and the height is EXACTLY. Either way the full width/height
	 * should be in mWidth and mHeight and we use 'resolveSize' to do the right
	 * thing.
	 */
	// ���������ͼ�Ĵ�С
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		final int width = resolveSize(totalWidth, widthMeasureSpec);
		final int height = resolveSize(totalHeight, heightMeasureSpec);
		setMeasuredDimension(width, height);
	}
}
