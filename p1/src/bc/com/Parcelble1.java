package bc.com;

import android.os.Parcel;
import android.os.Parcelable;

public class Parcelble1 implements Parcelable {

	Intent1 i;

	public Parcelble1(Intent1 a) {

	}

	public Parcelble1(Parcel a) {
		i = (Intent1) a.readValue(Parcelble1.class.getClassLoader());
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel destination, int arg1) {
		destination.writeValue(i);

	}

	public static final Parcelable.Creator<Parcelble1> CREATOR = new Parcelable.Creator<Parcelble1>() {
		public Parcelble1 createFromParcel(Parcel in) {
			return new Parcelble1(in);
		}

		public Parcelble1[] newArray(int size) {
			return new Parcelble1[size];
		}
	};
	
	public Intent1 getIntent1(){
		return i;
	}

}
