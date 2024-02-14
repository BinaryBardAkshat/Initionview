package androidx.core.location;

import androidx.core.location.LocationManagerCompat;

public final /* synthetic */ class LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ LocationManagerCompat.LocationListenerTransport f$0;
    public final /* synthetic */ LocationListenerCompat f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda2(LocationManagerCompat.LocationListenerTransport locationListenerTransport, LocationListenerCompat locationListenerCompat, String str) {
        this.f$0 = locationListenerTransport;
        this.f$1 = locationListenerCompat;
        this.f$2 = str;
    }

    public final void run() {
        this.f$0.m9lambda$onProviderDisabled$7$androidxcorelocationLocationManagerCompat$LocationListenerTransport(this.f$1, this.f$2);
    }
}
