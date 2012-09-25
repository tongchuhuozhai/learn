package bc.com;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {
    
    final String INDEX = "index";
    public DetailsFragment(int i){
        Bundle b = new Bundle();
        b.putInt(INDEX, i);
        setArguments(b);
        
    }
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details,container , false);
        TextView tv = (TextView) v.findViewById(R.id.textView1);
        Bundle b = getArguments();
        if(b.getInt(INDEX) == 0){
            tv.setText("a");
        }else{
            tv.setText("b");
        }
        return v;
    }

}