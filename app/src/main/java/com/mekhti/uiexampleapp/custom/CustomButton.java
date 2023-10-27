package com.mekhti.uiexampleapp.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mekhti.uiexampleapp.R;

public class CustomButton extends LinearLayout {
    private static final int PADDING_XS = 6;
    private static final int PADDING_SM = 14;
    private static final int PADDING_MD = 14;
    private static final int PADDING_LG = 16;
    private boolean isEnabled = true;

    public CustomButton(Context context) {
        super(context);
        init(context, null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private ImageView iconLeft;
    private TextView buttonText;
    private ImageView iconRight;

    private void init(Context context, AttributeSet attrs) {
        // Inflate the custom button layout
        inflate(context, R.layout.my_button, this);

        // Initialize views
        iconLeft = findViewById(R.id.iconLeft);
        buttonText = findViewById(R.id.buttonText);
        iconRight = findViewById(R.id.iconRight);

        // Apply custom attributes
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomButton);
            int backgroundColor = a.getColor(R.styleable.CustomButton_mButtonBackgroundColor, Color.TRANSPARENT);
            setBackgroundWithColorAndRadius(CornerRadius.values()[a.getInt(R.styleable.CustomButton_mCornerR, 0)], backgroundColor);
            setPaddingSize(PaddingSize.values()[a.getInt(R.styleable.CustomButton_mPaddingSize, 0)]);
            int leftIconResource = a.getResourceId(R.styleable.CustomButton_mLeftIcon, 0);
            int rightIconResource = a.getResourceId(R.styleable.CustomButton_mRightIcon, 0);
            setLeftAndRightIcons(leftIconResource,rightIconResource);
            isEnabled = a.getBoolean(R.styleable.CustomButton_mEnabled, true);
            setIsEnabled(isEnabled);
            a.recycle();
        }
    }

    private void setLeftAndRightIcons(int leftIconResource, int rightIconResource) {
        if (leftIconResource==0){
            iconLeft.setVisibility(GONE);
        }else{
            iconLeft.setImageResource(leftIconResource);
        }
        if (rightIconResource==0){
            iconRight.setVisibility(GONE);
        }else{
            iconRight.setImageResource(rightIconResource);
        }
    }

    public void setBackgroundWithColorAndRadius(CornerRadius cornerRadius, int backgroundColor) {
        GradientDrawable shape = new GradientDrawable();
        switch (cornerRadius){
            case SM:{
                shape.setCornerRadius(8);
                break;
            }
            case MD:{
                shape.setCornerRadius(16);
                break;
            }
            case LG:{
                shape.setCornerRadius(24);
                break;
            }
            case ROUNDED:{
                shape.setCornerRadii(new float[]{24, 24, 24, 24, 24, 24, 24, 24});
            }
            default:{

            }
        }
        shape.setColor(backgroundColor);
        setBackground(shape);
    }

    public void setPaddingSize(PaddingSize paddingSize) {
        int leftPadding, topPadding, rightPadding, bottomPadding;
        float iconScaleFactor = 1.0f;
        switch (paddingSize) {
            case XS:
                leftPadding = PADDING_XS;
                topPadding = PADDING_XS;
                rightPadding = PADDING_XS;
                bottomPadding = PADDING_XS;
                buttonText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                iconScaleFactor = 0.5f;
                break;
            case SM:
                leftPadding = PADDING_SM;
                topPadding = PADDING_SM;
                rightPadding = PADDING_SM;
                bottomPadding = PADDING_SM;
                buttonText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                iconScaleFactor = 0.67f;
                break;
            case MD:
                leftPadding = PADDING_MD;
                topPadding = PADDING_MD;
                rightPadding = PADDING_MD;
                bottomPadding = PADDING_MD;
                buttonText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                iconScaleFactor = 0.835f;
                break;
            case LG:
                leftPadding = PADDING_LG;
                topPadding = PADDING_LG;
                rightPadding = PADDING_LG;
                bottomPadding = PADDING_LG;
                buttonText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                iconScaleFactor = 1f;
                break;
            default:
                leftPadding = 0;
                topPadding = 0;
                rightPadding = 0;
                bottomPadding = 0;
                buttonText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                iconScaleFactor = 1f;
                break;
        }
        setPadding(paddingInPixels(leftPadding), 
                paddingInPixels(topPadding), 
                paddingInPixels(rightPadding),
                paddingInPixels(bottomPadding));

        iconLeft.setScaleX(iconScaleFactor);
        iconLeft.setScaleY(iconScaleFactor);

        iconRight.setScaleX(iconScaleFactor);
        iconRight.setScaleY(iconScaleFactor);
    }

    public int paddingInPixels(int dpValue){
        return  (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics()
        );
    }

    @Override
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
        setAlpha(enabled ? 1.0f : 0.5f);
        super.setEnabled(enabled);
    }

    public void setIsEnabled(boolean enabled) {
        setEnabled(enabled);
    }

    public boolean isCustomButtonEnabled() {
        return isEnabled;
    }

    public enum PaddingSize {
        XS, SM, MD, LG
    }

    public enum CornerRadius {
        SM, MD, LG, ROUNDED
    }
}

