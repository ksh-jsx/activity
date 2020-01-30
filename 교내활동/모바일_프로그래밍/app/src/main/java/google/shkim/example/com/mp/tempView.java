package google.shkim.example.com.mp;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class tempView {

    private View view;
    private int count = 0;

    public View getView() { return view; }
    public void setView(View view)
    {
         this.view = view;
    }

    public void applyView()
    {
        TextView oTextTitle = (TextView) view.findViewById(R.id.textTitle);
        TextView oimgName = (TextView) view.findViewById(R.id.imgName);
        ImageView lineImage = (ImageView)view.findViewById(R.id.lineImage);
        LinearLayout btns = (LinearLayout)view.findViewById(R.id.btns);
        Button showButton = (Button)view.findViewById(R.id.showLocationBtn);
        view.setBackgroundColor(Color.argb(0,0,0,0));
        if(oimgName.getText().equals("last"))
            lineImage.setImageResource(R.drawable.line_blue_non_bottom);
        else
            lineImage.setImageResource(R.drawable.line_blue);
        btns.setVisibility(View.INVISIBLE);
        oTextTitle.setTextColor(Color.rgb(51, 170, 187));

        return;
    }
}
