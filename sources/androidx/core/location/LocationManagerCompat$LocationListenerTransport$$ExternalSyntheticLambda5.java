package androidx.core.location;

import androidx.core.location.LocationManagerCompat;
import java.util.List;

public final /* synthetic */ class LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ LocationManagerCompat.LocationListenerTransport f$0;
    public final /* synthetic */ LocationListenerCompat f$1;
    public final /* synthetic */ List f$2;

    public /* synthetic */ LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda5(LocationManagerCompat.LocationListenerTransport locationListenerTransport, LocationListenerCompat locationListenerCompat, List list) {
        this.f$0 = locationListenerTransport;
        this.f$1 = locationListenerCompat;
        this.f$2 = list;
    }

    public final void run() {
        this.f$0.m8lambda$onLocationChanged$3$androidxcorelocationLocationManagerCompat$LocationListenerTransport(this.f$1, this.f$2);
    }
}
