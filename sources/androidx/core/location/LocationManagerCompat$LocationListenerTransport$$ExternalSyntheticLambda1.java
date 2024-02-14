package androidx.core.location;

import android.location.Location;
import androidx.core.location.LocationManagerCompat;

public final /* synthetic */ class LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ LocationManagerCompat.LocationListenerTransport f$0;
    public final /* synthetic */ LocationListenerCompat f$1;
    public final /* synthetic */ Location f$2;

    public /* synthetic */ LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda1(LocationManagerCompat.LocationListenerTransport locationListenerTransport, LocationListenerCompat locationListenerCompat, Location location) {
        this.f$0 = locationListenerTransport;
        this.f$1 = locationListenerCompat;
        this.f$2 = location;
    }

    public final void run() {
        this.f$0.m7lambda$onLocationChanged$2$androidxcorelocationLocationManagerCompat$LocationListenerTransport(this.f$1, this.f$2);
    }
}
